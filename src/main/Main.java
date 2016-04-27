package main;

import dto.ConfigDataBaseDTO;
import persistencia.conexion.Conexion;
import persistencia.serializar.SerializarConfigDataBase;

/*
 * CONTROLADORES
 */
import presentacion.controlador.ControladorConfiguracion;
import presentacion.controlador.ControladorListaIngresos;
import presentacion.controlador.ControladorOrdenDeTrabajoPanel;
import presentacion.controlador.ControladorPresupuestoPanel;

/*
 * VISTAS
 */
import presentacion.vista.OrdenDeTrabajoPanel;
import presentacion.vista.PresupuestoPanel;
import presentacion.vista.VentanaConfigDataBase;
import presentacion.vista.VentanaListaIngresos;
import presentacion.vista.wOrdenDeTrabajo;

public class Main{

	public static void main(String[] args) {
		SerializarConfigDataBase serializarConf = new SerializarConfigDataBase();

		ConfigDataBaseDTO  configuracionInicial = serializarConf.DeSerializar();

		if (configuracionInicial == null) {
			VentanaConfigDataBase configuracion = new VentanaConfigDataBase();
			ControladorConfiguracion controladorConfig= new ControladorConfiguracion(configuracion);

			controladorConfig.iniciar();
		} else {
			
			ConfigDataBaseDTO otra = serializarConf.DeSerializar();
			Conexion.setConection(otra);
			
			if(Conexion.isFallo()) {//si falla la conexion le vuelvo a pedir los datos de conexion a la base
			
				VentanaConfigDataBase configuracion = new VentanaConfigDataBase();
				ControladorConfiguracion controladorConfig= new ControladorConfiguracion(configuracion);
				
				controladorConfig.iniciar();
				
			} else {
				
				VentanaListaIngresos vista = new VentanaListaIngresos();

				ControladorListaIngresos controlador = new ControladorListaIngresos(vista);
				controlador.inicializar();
				
				// SOLO PARA DEBUG
				OrdenDeTrabajoPanel ordenDeTrabajoPanel = new OrdenDeTrabajoPanel();
				PresupuestoPanel presupuestoPanel = new PresupuestoPanel();

				ControladorPresupuestoPanel controladorPresupuestoPanel = new ControladorPresupuestoPanel(presupuestoPanel);
				ControladorOrdenDeTrabajoPanel controladorOrdenDeTrabajoPanel = new ControladorOrdenDeTrabajoPanel(
						ordenDeTrabajoPanel);

				wOrdenDeTrabajo frame = new wOrdenDeTrabajo(controladorPresupuestoPanel, controladorOrdenDeTrabajoPanel);
			}
		}

		
	}
}