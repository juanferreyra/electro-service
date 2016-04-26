package main;

import presentacion.controlador.ControladorOrdenDeTrabajoPanel;
import presentacion.controlador.ControladorPresupuestoPanel;
import presentacion.vista.OrdenDeTrabajoPanel;
import presentacion.vista.PresupuestoPanel;
import presentacion.vista.wOrdenDeTrabajo;

public class Main {

	public static void main(String[] args) {
		/*
		 * Vista vista = new Vista(); Presupuesto modelo = new Presupuesto();
		 * VistaControlador controlador = new VistaControlador(vista, modelo);
		 * controlador.inicializar();
		 */

		// SOLO PARA DEBUG
		OrdenDeTrabajoPanel ordenDeTrabajoPanel = new OrdenDeTrabajoPanel();
		PresupuestoPanel presupuestoPanel = new PresupuestoPanel();

		ControladorPresupuestoPanel controladorPresupuestoPanel = new ControladorPresupuestoPanel(presupuestoPanel);
		ControladorOrdenDeTrabajoPanel controladorOrdenDeTrabajoPanel = new ControladorOrdenDeTrabajoPanel(
				ordenDeTrabajoPanel);

		wOrdenDeTrabajo frame = new wOrdenDeTrabajo(controladorPresupuestoPanel, controladorOrdenDeTrabajoPanel);
	}
}