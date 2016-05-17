package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dto.ItemPresupuestoRepuestoDTO;
import dto.RepuestoDTO;
import dto.UsuarioDTO;
import modelo.Ingreso;
import modelo.Presupuesto;
import presentacion.vista.VentanaReparacion;

public class ControladorReparacion implements ActionListener {

	private VentanaReparacion ventanaReparacion;
	private Ingreso ingreso;
	private Integer cantidad = 0;
	private Presupuesto presupuesto;
	private UsuarioDTO usuarioLogueado;
	private DefaultTableModel modelTable = new DefaultTableModel();
	private float suma = 0;
	@SuppressWarnings("unused")
	private Calendar hoy = new GregorianCalendar();

	public ControladorReparacion(VentanaReparacion ventanaReparacion, Ingreso ingreso, UsuarioDTO usuarioLogueado) {
		this.ventanaReparacion = ventanaReparacion;
		this.ingreso = ingreso;
		this.ventanaReparacion.getIncrementoCantComponente_btn().addActionListener(this);
		this.ventanaReparacion.getDecrementoCantComponente_btn().addActionListener(this);
		this.ventanaReparacion.getAgregarComponente_btn().addActionListener(this);
		this.ventanaReparacion.getEliminarComponente_btn().addActionListener(this);
		this.ventanaReparacion.getFinalizar_btn().addActionListener(this);
		this.ventanaReparacion.getCancelar_btn().addActionListener(this);
		this.usuarioLogueado = usuarioLogueado;
		this.presupuesto = new Presupuesto(ingreso.getIngreso());
	}

	// public void inicializar() {
	// setearDatosIngreso();
	// this.ventanaReparacion.setVisible(true);
	// }

	public void inicializar() {

		Calendar hoy = new GregorianCalendar();
		// ventanaReparacion.getFechaIngreso_lbl().setText("Fecha: " +
		// hoy.get(Calendar.DAY_OF_MONTH) + " / "
		// + hoy.get(Calendar.MONTH) + " / " + hoy.get(Calendar.YEAR));
		ventanaReparacion.setVisible(true);
		ventanaReparacion.getComponentes_table().setModel(modelTable);
		cargarComboComponentes();
		setearDatosIngreso();
		// cargo el usuario
		if (presupuesto.getId() == -1) {
			// ventanaReparacion.getLbltecnico()
			// .setText(this.usuarioLogueado.getNombre() + " " +
			// this.usuarioLogueado.getApellido());
		} else {
			cargarModelo();
			ventanaReparacion.getFinalizar_btn().setVisible(false);
			ventanaReparacion.getCancelar_btn().setText("Cerrar");
		}
	}

	private void cargarModelo() {

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
			// sumarTotales();
			// sumarTotalComponentes();

			// elimina componete de la tabla
		} else if (e.getSource() == this.ventanaReparacion.getEliminarComponente_btn()) {

			eliminarComponenteDeTabla();

		}

	}

	// private void restarTotalComponentes() {
	//
	// float resta =
	// Float.parseFloat(this.ventanaReparacion.getValorPresupuestado_txf().getText())
	// - Float.parseFloat(this.ventanaReparacion.getManoDeObra_txf().getText());
	//
	// this.ventanaReparacion.getValorPresupuestado_txf().setText(String.valueOf(resta));
	// }
	//
	// private void sumarTotalComponentes() {
	//
	// if (this.ventanaReparacion.getManoDeObra_txf().getText().isEmpty()) {
	//
	// JOptionPane.showMessageDialog(ventanaReparacion, "Campo MANO DE OBRA no
	// puede estar vacio ", "Atencion!",
	// JOptionPane.INFORMATION_MESSAGE);
	//
	// } else {
	//
	// float suma =
	// Float.parseFloat(this.ventanaReparacion.getTotal_lbl().getText())
	// + Float.parseFloat(this.ventanaReparacion.getManoDeObra_txf().getText());
	//
	// this.ventanaReparacion.getValorPresupuestado_txf().setText(String.valueOf(suma));
	// }
	// }

	private void agregarRepuestoATabla() {

		modelTable.setColumnIdentifiers(ventanaReparacion.getComponentes_nombreColumnas());
		Boolean existe = false;
		// agrego el repuesto al objeto Presupuesto con su lista
		int cantidad = Integer.parseInt(ventanaReparacion.getCantidad_lbl().getText());
		RepuestoDTO resp = presupuesto
				.buscarRepuesto((String) this.ventanaReparacion.getComponente_ComboBox().getSelectedItem());
		ItemPresupuestoRepuestoDTO itemRepuesto = new ItemPresupuestoRepuestoDTO(-1, resp.getId(), resp.getDetalle(),
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

	// private void sumarTotales() {
	//
	// suma = 0;
	//
	// for (int i = 0; i <
	// ventanaReparacion.getComponentes_table().getRowCount(); i++) {
	//
	// suma +=
	// Float.parseFloat(ventanaReparacion.getComponentes_table().getValueAt(i,
	// 4).toString());
	// }
	//
	// ventanaReparacion.getTotal_lbl().setText(String.valueOf(suma));
	//
	// }

	private void actualizarTablaRepuestos() {
		modelTable = new DefaultTableModel();
		modelTable.setColumnIdentifiers(ventanaReparacion.getComponentes_nombreColumnas());

		List<ItemPresupuestoRepuestoDTO> repuestosAgregados = presupuesto.getListaDeRepuestos();

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

			// sumarTotales();
			actualizarTablaRepuestos();
		} else {

			JOptionPane.showMessageDialog(ventanaReparacion, "debe seleccionar fila para eliminar ", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
