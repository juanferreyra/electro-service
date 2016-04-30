package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		this.perfil = this.principal.getUser().getPerfilDTO().getPerfil();

	}

	public void iniciar() {

		this.adecuarVentanaPrincipal();

	}

	public void cargarOrden_tablaOrdenesTrabajo(String fecha, String producto, String cliente, Boolean envio) {
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

	public void limpiarTabla() {

	}

	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.principal.getIngresarProducto_btn()) {
			ControladorVentanaIngreso controladorVentanaIngreso = new ControladorVentanaIngreso(new VentanaIngreso(),
					new Ingreso());
		} else if (e.getSource() == this.principal.getPresupuestar_btn()) {
			// this.controladorVentanaPresupuesto = new
			// ControladorPresupuesto(new VentanaPresupuesto(), new Ingreso());
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