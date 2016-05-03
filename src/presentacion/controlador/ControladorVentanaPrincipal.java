package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import dto.PresupuestoDTO;
import modelo.Ingreso;
import persistencia.dao.PresupuestoDAO;
import presentacion.vista.VentanaIngreso;
import presentacion.vista.VentanaPresupuesto;
import presentacion.vista.VentanaPrincipal;

public class ControladorVentanaPrincipal implements ActionListener {

	private VentanaPrincipal principal;
	private String perfil;
	private PresupuestoDAO presupuestoDAO;

	public ControladorVentanaPrincipal(VentanaPrincipal principal) {

		this.principal = principal;
		this.principal.getIngresarProducto_btn().addActionListener(this);
		this.principal.getPresupuestar_btn().addActionListener(this);
		this.perfil = this.principal.getUser().getPerfilDTO().getPerfil();

	}

	public void iniciar() {
		presupuestoDAO = new PresupuestoDAO();
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
		DefaultTableModel modelo = (DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel();
		int largo = modelo.getRowCount();
		for (int i = largo - 1; i >= 0; i--) {
			this.eliminarDatosAsociados(i);
			modelo.removeRow(i);
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

	@SuppressWarnings("serial")
	private void cargar_tablaOrdenesTrabajo() {
		// Consigo todos los presupuestos y ordeno los datos
		ArrayList<PresupuestoDTO> presupuestos = presupuestoDAO.readAll();

		Object[][] datos = ordenarDatos(presupuestos);
		// Seteo a la tabla un nuevo modelo con los datos de los
		// ingresos(ordenes de trabajo)
		this.principal.getOrdenesDeTrabajo_table().setModel(new DefaultTableModel(datos, new String[] { "", "Nro.",
				"Fecha", "Producto", "Cliente", "Env\u00EDo", "Presupuesto", "T\u00E9cnico", "Asignado", "Estado" }) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { JButton.class, Integer.class, String.class, String.class, String.class,
					Boolean.class, JButton.class, String.class, String.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
	}

	private Object[][] ordenarDatos(ArrayList<PresupuestoDTO> presupuestos) {
		// A partir de los presupuestos obtener los ingresos
		// correspondientes(idingreso) se debe obtener todos los datos para
		// setear en formato=
		// ingreso_btn/nro/fecha/producto/cliente/envio/presupuesto_btn/tecnico_asignado/estado

		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Ingreso ingr = new Ingreso();
		ingr.setId(1);
		if (e.getSource() == this.principal.getIngresarProducto_btn()) {
			ControladorVentanaIngreso controladorVentanaIngreso = new ControladorVentanaIngreso(new VentanaIngreso(),
					ingr);

			controladorVentanaIngreso.inicializar();

		} else if (e.getSource() == this.principal.getPresupuestar_btn()) {

			Ingreso ing = new Ingreso();
			ing.setId(1);
			ing.cargarModeloCompleto();
			
			ControladorPresupuesto controladorPresupuesto = new ControladorPresupuesto(new VentanaPresupuesto(),
					ing);
			
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