package main;

import dto.ConfigDataBaseDTO;
import modelo.Ingreso;
import persistencia.conexion.Conexion;
import persistencia.serializar.SerializarConfigDataBase;

/*
 * CONTROLADORES
 */
import presentacion.controlador.ControladorConfiguracion;
import presentacion.controlador.ControladorVentanaIngreso;
import presentacion.controlador.ControladorVentanaPrincipal;
import presentacion.controlador.ControladorVentanaLogin;
/*
 * VISTAS
 */
import presentacion.vista.VentanaConfigDataBase;
import presentacion.vista.VentanaIngreso;
import presentacion.vista.VentanaListaIngresos;
import presentacion.vista.VentanaLogin;

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
				/*
				VentanaLogin login = new VentanaLogin();
				ControladorVentanaLogin controlLogin = new ControladorVentanaLogin(login);
				login.setVisible(true);
				*/
				
				//para probar la ventana de ingreso
				VentanaIngreso vent  = new  VentanaIngreso();
				Ingreso ing = new Ingreso();
				ControladorVentanaIngreso contr = new ControladorVentanaIngreso(vent, ing);
				
				contr.inicializar();
				
				
				
				
			}
		}

		
	}
}