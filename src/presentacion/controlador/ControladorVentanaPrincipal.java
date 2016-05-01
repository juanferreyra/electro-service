package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import modelo.Ingreso;
import presentacion.vista.VentanaIngreso;
import presentacion.vista.VentanaPresupuesto;
import presentacion.vista.VentanaPrincipal;

public class ControladorVentanaPrincipal implements ActionListener {

	private VentanaPrincipal principal;
	private String perfil;

	public ControladorVentanaPrincipal(VentanaPrincipal principal) {

		this.principal = principal;
		this.principal.getIngresarProducto_btn().addActionListener(this);
		this.principal.getPresupuestar_btn().addActionListener(this);
		this.perfil = this.principal.getUser().getPerfilDTO().getPerfil();

	}

	public void iniciar() {

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
			// Visualizacion modo jefe
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
		// Eliminar ingreso,presupuesto.
	}

	private void cargar_tablaOrdenesTrabajo() {
		// conseguir todos los ingresos y cargar tabla
		// this.principal.getOrdenesDeTrabajo_table()
		// .setModel(new DefaultTableModel(new Object[][] {}, new String[] { "",
		// "Nro.", "Fecha", "Producto",
		// "Cliente", "Env\u00EDo", "Presupuesto", "T\u00E9cnico", "Asignado",
		// "Estado" }) {
		// Class[] columnTypes = new Class[] { JButton.class, Integer.class,
		// String.class, String.class,
		// String.class, Boolean.class, JButton.class, String.class,
		// String.class };
		//
		// public Class getColumnClass(int columnIndex) {
		// return columnTypes[columnIndex];
		// }
		// });
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.principal.getIngresarProducto_btn()) {
			ControladorVentanaIngreso controladorVentanaIngreso = new ControladorVentanaIngreso(new VentanaIngreso(),
					new Ingreso());
			
			controladorVentanaIngreso.inicializar();
			
		} else if (e.getSource() == this.principal.getPresupuestar_btn()) {
			
			ControladorPresupuesto ControladorPresupuesto = new ControladorPresupuesto(new VentanaPresupuesto(),
					new Ingreso());
			
			ControladorPresupuesto.inicializar();
			
		} else if (e.getSource() == this.principal.getReparacion_btn()) {
			// this.controladorVentanaReparacion = new
			// ControladorVentanaReparacion(new VentanaReparacion(), new
			// Ingreso());
		} else if (e.getSource() == this.principal.getAsignarOrden_btn()) {

			// conseguir nroOrden y tecnico
			// this.cargarAsignacionTecnico_tablaOrdenesTrabajo(nroOrden,
			// tecnico);
		}
	}
}