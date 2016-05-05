
package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import dto.IngresoDTO;
import modelo.Ingreso;
import persistencia.dao.ClienteDAO;
import persistencia.dao.EstadoDAO;
import persistencia.dao.IngresoDAO;
import persistencia.dao.PresupuestoDAO;
import persistencia.dao.UsuarioDAO;
import presentacion.vista.VentanaIngreso;
import presentacion.vista.VentanaPresupuesto;
import presentacion.vista.VentanaPrincipal;

public class ControladorVentanaPrincipal implements ActionListener {

	private VentanaPrincipal principal;
	private String perfil;
	private IngresoDAO ingresoDAO;
	private ClienteDAO clienteDAO;
	private PresupuestoDAO presupuestoDAO;
	private EstadoDAO estadoDAO;
	private UsuarioDAO usuarioDAO;

	public ControladorVentanaPrincipal(VentanaPrincipal principal) {

		this.principal = principal;
		this.principal.getIngresarProducto_btn().addActionListener(this);
		this.principal.getPresupuestar_btn().addActionListener(this);
		this.perfil = this.principal.getUser().getPerfilDTO().getPerfil();

	}

	public void iniciar() {
		ingresoDAO = new IngresoDAO();
		clienteDAO = new ClienteDAO();
		presupuestoDAO = new PresupuestoDAO();
		estadoDAO = new EstadoDAO();
		usuarioDAO = new UsuarioDAO();
		this.adecuarVentanaPrincipal();
		this.cargar_tablaOrdenesTrabajo();

	}

	public void cargarOrden_tablaOrdenesTrabajo(Ingreso ingreso) {
		/**
		 * Estructura de la tabla ordenes de trabajo:
		 * ingreso_btn/nro/fecha/producto/cliente/envio/presupuesto_btn/
		 * tecnico_asignado/estado
		 **/

	}

	public void cargarAsignacionTecnico_tablaOrdenesTrabajo(int nroOrden, String tecnico) {
		/**
		 * Estructura de la tabla ordenes de trabajo:
		 * ingreso_btn/nro/fecha/producto/cliente/envio/presupuesto_btn/
		 * tecnico_asignado/estado
		 **/

	}

	public void cargarEstado_tablaOrdenesTrabajo(int nroOrden, String estado) {
		/**
		 * Estructura de la tabla ordenes de trabajo:
		 * ingreso_btn/nro/fecha/producto/cliente/envio/presupuesto_btn/
		 * tecnico_asignado/estado
		 **/

	}

	private void adecuarVentanaPrincipal() {

		if (this.perfil.equals("ADMINISTRATIVO")) {
			// Visualizacion modo administrativo
			this.principal.getPresupuestar_btn().setVisible(false);
			this.principal.getAsignarOrden_btn().setVisible(false);
			this.principal.getReparacion_btn().setVisible(false);
			this.principal.setVisible(true);

		} else if (this.perfil.equals("TECNICO")) {
			// Visualizacion modo tecnico
			this.principal.getIngresarProducto_btn().setVisible(false);
			this.principal.setVisible(true);

		} else {
			// Visualizacion modo jefe/admin
			this.principal.setVisible(true);
		}
	}

	public void limpiar_tablaOrdenesTrabajo() {
		
		int largo = ((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).getRowCount();
		
		for (int i = largo - 1; i >= 0; i--) {
			((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).removeRow(i);
		}

	}

	public void eliminarFilaSeleccionada_tablaOrdenesTrabajo() {
		int fila = this.principal.getOrdenesDeTrabajo_table().getSelectedRow();

		this.eliminarDatosAsociados(fila);

		DefaultTableModel modelo = (DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel();
		modelo.removeRow(fila);
	}

	private void eliminarDatosAsociados(int fila) {
		// Eliminar ingreso,presupuesto. Es decir, cambiar el estado habilitado.
	}

	public void cargar_tablaOrdenesTrabajo() {
		// Consigo todos los ingresos y genero las filas
		ArrayList<IngresoDTO> ingresos = ingresoDAO.readAll();
		ObtenerFilas(ingresos);

	}

	private void ObtenerFilas(ArrayList<IngresoDTO> ingresos) {
		limpiar_tablaOrdenesTrabajo();
		for (int i = 0; i <= ingresos.size() - 1; i++) {
			String nombreCompletoTecnicoAsignado = "";
			if (ingresos.get(i).getTecnico_asignado() != 0) {
				nombreCompletoTecnicoAsignado = usuarioDAO.find(ingresos.get(i).getTecnico_asignado()).getNombre() + " "
						+ usuarioDAO.find(ingresos.get(i).getTecnico_asignado()).getApellido();

			}
			this.cargarFila(i, new JButton(), ingresos.get(i).getId(), ingresos.get(i).getFecha_creacion(),
					ingresos.get(i).getDescripcion(), clienteDAO.find(ingresos.get(i).getIdcliente()).getNombre(),
					ingresos.get(i).getEnvio(), new JButton(), nombreCompletoTecnicoAsignado,
					estadoDAO.find(ingresos.get(i).getEstado()).getDetalle());
		}
	}

	private void cargarFila(int fila, JButton botonIngreso, int id, Date fecha, String descripcion, String cliente,
			Boolean envio, JButton botonPresupuesto, String tecnico_asignado, String estado) {

		Object[] ingreso = new Object[9];
		ingreso[0] = (JButton) botonIngreso;
		ingreso[1] = id;
		ingreso[2] = fecha;
		ingreso[3] = descripcion;
		ingreso[4] = cliente;
		ingreso[5] = envio;
		ingreso[6] = (JButton) botonPresupuesto;
		ingreso[7] = tecnico_asignado;
		ingreso[8] = estado;

		// Agrego fila solo en caso de que lo permita el perfil (hardcodeo luego
		// modificar)
		if (this.perfil.equals("ADMINISTRATIVO") && (estado.equals("NUEVO") || estado.equals("INFORMADO")
				|| estado.equals("IRREPARABLE") || estado.equals("REPARADO") || estado.equals("RECHAZADO")
				|| estado.equals("AVISO DE RETIRO") || estado.equals("RETIRADO") || estado.equals("PRESUPUESTADO"))) {// HARDCODEO

			((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).addRow(ingreso);
		} else if (this.perfil.equals("TECNICO") && (estado.equals("NUEVO") || estado.equals("ACEPTADO")
				|| estado.equals("EN REPARACION") || estado.equals("PRESUPUESTANDO") || estado.equals("REPARADO"))) {// HARDCODEO

			((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).addRow(ingreso);
		} else if (this.perfil.equals("JEFE")) {
			((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).addRow(ingreso);
		}

		// Establezco que no se pueda editar pero si seleccionar una fila
		((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).isCellEditable(fila, 0);
		((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).isCellEditable(fila, 1);
		((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).isCellEditable(fila, 2);
		((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).isCellEditable(fila, 3);
		((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).isCellEditable(fila, 4);
		((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).isCellEditable(fila, 5);
		((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).isCellEditable(fila, 6);
		((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).isCellEditable(fila, 7);
		((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).isCellEditable(fila, 8);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.principal.getIngresarProducto_btn()) {
			ControladorVentanaIngreso controladorVentanaIngreso = new ControladorVentanaIngreso(new VentanaIngreso(),
					new Ingreso(), this);

			controladorVentanaIngreso.inicializar();

		} else if (e.getSource() == this.principal.getPresupuestar_btn()) {

			Ingreso ing = new Ingreso();
			ing.setId(1);
			ing.cargarModeloCompleto();

			ControladorPresupuesto controladorPresupuesto = new ControladorPresupuesto(new VentanaPresupuesto(), ing);
			controladorPresupuesto.inicializar();

		} else if (e.getSource() == this.principal.getReparacion_btn()) {
			// this.controladorVentanaReparacion = new
			// ControladorVentanaReparacion(new VentanaReparacion(), new
			// Ingreso());
		} else if (e.getSource() == this.principal.getAsignarOrden_btn()) {

			// conseguir nroOrden y tecnico(mediante el user actual del tecnico)
			// this.cargarAsignacionTecnico_tablaOrdenesTrabajo(nroOrden,
			// tecnico);
		}
	}
}