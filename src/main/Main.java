package main;

import dto.ConfigDataBaseDTO;
import persistencia.conexion.Conexion;
import persistencia.serializar.SerializadorBD;

/*
 * CONTROLADORES
 */
import presentacion.controlador.ControladorConfiguracion;
import presentacion.controlador.ControladorVentanaLogin;
/*
 * VISTAS
 */
import presentacion.vista.VentanaConfigDataBase;

public class Main {

	public static void main(String[] args) {

		ConfigDataBaseDTO configuracionInicial = SerializadorBD.DesSerializar();
		
		//Si es la primera vez que voy a configurar
		if (configuracionInicial == null) {
			System.out.println("La configuracion era nula, entonces la pido");
			VentanaConfigDataBase configuracion = new VentanaConfigDataBase();
			ControladorConfiguracion controladorConfig = new ControladorConfiguracion(configuracion);

			controladorConfig.iniciar();
		} else {
			System.out.println("La configuracion no es nula");
					
			if (Conexion.isFallo()) {// si falla la conexion le vuelvo a pedir
										// los datos de conexion a la base
				System.out.println(" Probo la configuracion pero fallo asi que la pido de nuevo");
				VentanaConfigDataBase configuracion = new VentanaConfigDataBase();
				ControladorConfiguracion controladorConfig = new ControladorConfiguracion(configuracion);

				controladorConfig.iniciar();
			} else {
				System.out.println(" Esta todo bien asi que pido el login");
				ControladorVentanaLogin controlLogin = new ControladorVentanaLogin();
				controlLogin.getPantalla();
			}
		}

	}
}





