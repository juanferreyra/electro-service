package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dto.IngresoLogDTO;
import dto.ItemRepuestoDTO;
import dto.RepuestoDTO;
import dto.UsuarioDTO;
import modelo.EmailReparacion;
import modelo.Ingreso;
import modelo.Presupuesto;
import modelo.Reparacion;
import modelo.Reparaciones_repuestos;
import persistencia.dao.IngresoLogDAO;
import presentacion.vista.VentanaReparacion;

public class ControladorReparacion implements ActionListener {

	private VentanaReparacion ventanaReparacion;
	private Ingreso ingreso;
	private Integer cantidad = 0;
	private Presupuesto presupuesto;
	private Reparacion reparacion;
	private Reparaciones_repuestos reparaciones_repuestos;
	private UsuarioDTO usuarioLogueado;
	private DefaultTableModel modelTable = new DefaultTableModel();
	private float suma = 0;
	@SuppressWarnings("unused")
	private Calendar hoy = new GregorianCalendar();
	private ControladorVentanaPrincipal controladorVentanaPrincipal;

	public ControladorReparacion(VentanaReparacion ventanaReparacion,
			ControladorVentanaPrincipal controladorVentanaPrincipal, Ingreso ingreso, UsuarioDTO usuarioLogueado) {
		this.ventanaReparacion = ventanaReparacion;
		this.controladorVentanaPrincipal = controladorVentanaPrincipal;
		this.ingreso = ingreso;
		this.ventanaReparacion.getIncrementoCantComponente_btn().addActionListener(this);
		this.ventanaReparacion.getDecrementoCantComponente_btn().addActionListener(this);
		this.ventanaReparacion.getAgregarComponente_btn().addActionListener(this);
		this.ventanaReparacion.getEliminarComponente_btn().addActionListener(this);
		this.ventanaReparacion.getFinalizar_btn().addActionListener(this);
		this.ventanaReparacion.getCancelar_btn().addActionListener(this);
		this.usuarioLogueado = usuarioLogueado;
		this.presupuesto = new Presupuesto(ingreso.getIngreso());
		this.reparacion = new Reparacion();
		this.reparaciones_repuestos = new Reparaciones_repuestos();
		this.ventanaReparacion.getEnviarAvisoReparacion_btn().addActionListener(this);

	}

	public void inicializar() {

		ventanaReparacion.setVisible(true);
		ventanaReparacion.getComponentes_table().setModel(modelTable);
		cargarComboComponentes();
		setearDatosIngreso();
		cargarMontosPresupuestos();

		cargarModelo();
		ventanaReparacion.getFinalizar_btn().setVisible(true);
		ventanaReparacion.getCancelar_btn().setText("Cerrar");

	}

	private void cargarMontosPresupuestos() {
		
		if (this.ingreso.getIngreso().getEnvio()){
			
			this.ventanaReparacion.getLblMontoEnvio().setText(String.valueOf(this.ingreso.getIngreso().getMonto_envio()));
		}else{
			this.ventanaReparacion.getLblMontoEnvio().setText("0");
		}
		
		this.ventanaReparacion.getLblMontoPresupuestado().setText(String.valueOf(this.presupuesto.getPresupuesto().getImporteTotal()));
		
	}

	private void cargarModelo() {
		// cargo la lista de componentes
		actualizarTablaRepuestos();
	}

	private void setearDatosIngreso() {
		this.ventanaReparacion.setTipoTexto_lbl(ingreso.getTipoproducto().getDetalle());
		this.ventanaReparacion.setMarcaTexto_lbl(ingreso.getMarca().getDetalle());
		this.ventanaReparacion.setNombreProductoTexto_lbl(ingreso.ingr.getDescripcion());
		this.ventanaReparacion.getDescripcionFalla_txtArea().setText(ingreso.getIngreso().getDescripcion_falla());
	}

	private void cargarComboComponentes() {

		for (RepuestoDTO c : presupuesto.obtenerRepuestos()) {
			this.ventanaReparacion.getComponente_ComboBox().addItem(c.getDetalle());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// suma y resta componentes
		if (e.getSource() == this.ventanaReparacion.getIncrementoCantComponente_btn()) {

			cantidad += 1;
			this.ventanaReparacion.getCantidad_lbl().setText(cantidad.toString());

		} else if (e.getSource() == this.ventanaReparacion.getDecrementoCantComponente_btn()) {

			if (cantidad != 1) {
				cantidad -= 1;
				this.ventanaReparacion.getCantidad_lbl().setText(cantidad.toString());
			}
			// agrega a la tabla
		} else if (e.getSource() == this.ventanaReparacion.getAgregarComponente_btn()) {

			agregarRepuestoATabla();
			ocultarColumnaId();

			// elimina componete de la tabla
		} else if (e.getSource() == this.ventanaReparacion.getEliminarComponente_btn()) {

			eliminarComponenteDeTabla();

		} else if (e.getSource() == this.ventanaReparacion.getFinalizar_btn()) {

			String tecnicoAsignado = this.usuarioLogueado.getNombre() + " " + this.usuarioLogueado.getApellido();
			Date fecha_reparacion = null;
			int horas = 0;
			int valor_estimado = 0;
			String descripcionFinal = this.ventanaReparacion.getDescripcionFinal();
			int ingresoId = this.ingreso.getId();

			this.reparacion.guardarReparacion(tecnicoAsignado, fecha_reparacion, horas, valor_estimado,
					descripcionFinal, ingresoId);

			guardarReparacionesRepuestosEnBD(this.reparacion.getIdReparacion());

			int tipoEstado = determinarEstado();
			if (tipoEstado != 0) {
				// Actualizo el estado del ingreso
				IngresoLogDTO ingrLog = new IngresoLogDTO(0, this.ingreso.getId(), tipoEstado, null,
						usuarioLogueado.getId());
				IngresoLogDAO ingresoLogDAO = new IngresoLogDAO();
				ingresoLogDAO.insert(ingrLog);
			}

			this.controladorVentanaPrincipal.cargar_tablaOrdenesTrabajo();
			// ENVIAR MAIL DE AVISO AUTOMATiCAMENTE ACA.
			EmailReparacion email = new EmailReparacion(ingreso, usuarioLogueado, ventanaReparacion, reparacion);
			email.start();

			this.ventanaReparacion.dispose();

		} else if (e.getSource() == this.ventanaReparacion.getCancelar_btn()) {
			this.ventanaReparacion.dispose();

		} else if (e.getSource() == this.ventanaReparacion.getEnviarAvisoReparacion_btn()) {

			// ENVIAR MAIL
			EmailReparacion email = new EmailReparacion(ingreso, usuarioLogueado, ventanaReparacion, reparacion);
			email.start();

		}

	}

	private int determinarEstado() {
		int tipoEstado = this.ingreso.getIngreso().getEstado();
		if (this.ventanaReparacion.getReparable_CheckBox().isSelected()) {
			tipoEstado = 7;
		} else {
			tipoEstado = 8;
		}
		return tipoEstado;

	}

	private void guardarReparacionesRepuestosEnBD(int idReparacion) {
		int idrepuesto;
		Date fecha_creacion = null;
		boolean habilitado = true;
		for (int i = 0; i < presupuesto.getListaDeRepuestos().size(); i++) {
			idrepuesto = presupuesto.getListaDeRepuestos().get(i).getIdrepuesto();

			this.reparaciones_repuestos.guardarReparacion_repuesto(idReparacion, idrepuesto,
					(int) (this.ventanaReparacion.getComponentes_table().getValueAt(i, 2)), fecha_creacion, habilitado);
		}

	}

	private void agregarRepuestoATabla() {

		modelTable.setColumnIdentifiers(ventanaReparacion.getComponentes_nombreColumnas());
		Boolean existe = false;
		// agrego el repuesto al objeto Presupuesto con su lista
		int cantidad = Integer.parseInt(ventanaReparacion.getCantidad_lbl().getText());
		RepuestoDTO resp = presupuesto
				.buscarRepuesto((String) this.ventanaReparacion.getComponente_ComboBox().getSelectedItem());
		ItemRepuestoDTO itemRepuesto = new ItemRepuestoDTO(-1, resp.getId(), resp.getDetalle(),
				cantidad, resp.getPrecioUnitario(), resp.getPrecioUnitario() * cantidad);
		// me fijo si existe en la tabla
		for (int i = 0; i < presupuesto.getListaDeRepuestos().size(); i++) {
			if (presupuesto.getListaDeRepuestos().get(i).getDetalle().equals(resp.getDetalle())) {
				existe = true;
				this.presupuesto.getListaDeRepuestos().get(i).sumarCantidad(cantidad);
				this.presupuesto.getListaDeRepuestos().get(i).sumarTotal(resp.getPrecioUnitario() * cantidad);
			}
		}
		if (!existe) {
			presupuesto.addRepuestoListaDeComponentes(itemRepuesto);
		}
		// actualizo la tabla de repuestos
		actualizarTablaRepuestos();
	}

	private void actualizarTablaRepuestos() {
		modelTable = new DefaultTableModel();
		modelTable.setColumnIdentifiers(ventanaReparacion.getComponentes_nombreColumnas());

		List<ItemRepuestoDTO> repuestosAgregados = presupuesto.getListaDeRepuestos();

		for (int i = 0; i < repuestosAgregados.size(); i++) {
			Object[] fila = { repuestosAgregados.get(i).getId(), repuestosAgregados.get(i).getDetalle(),
					repuestosAgregados.get(i).getCantidad(), repuestosAgregados.get(i).getPrecioUnitario(),
					repuestosAgregados.get(i).getPrecio() };

			modelTable.insertRow(0, fila);
		}

		this.ventanaReparacion.getComponentes_table().setModel(modelTable);

		ocultarColumnaId();
	}

	private void ocultarColumnaId() {

		this.ventanaReparacion.getComponentes_table().getColumnModel().getColumn(0).setMaxWidth(0);
		this.ventanaReparacion.getComponentes_table().getColumnModel().getColumn(0).setMinWidth(0);
		this.ventanaReparacion.getComponentes_table().getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		this.ventanaReparacion.getComponentes_table().getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
	}

	private void eliminarComponenteDeTabla() {

		if (this.ventanaReparacion.getComponentes_table().getSelectedRow() != -1) {

			int seleccionado = ventanaReparacion.getComponentes_table().getSelectedRow();
			String nombre = ventanaReparacion.getComponentes_table().getValueAt(seleccionado, 1).toString();

			System.out.println(nombre);
			for (int i = 0; i < this.presupuesto.getListaDeRepuestos().size(); i++) {
				if (this.presupuesto.getListaDeRepuestos().get(i).getDetalle().equals(nombre)) {
					this.presupuesto.getListaDeRepuestos().remove(i);
				}
			}

			actualizarTablaRepuestos();
		} else {

			JOptionPane.showMessageDialog(ventanaReparacion, "debe seleccionar fila para eliminar ", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
