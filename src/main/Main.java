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

		ConfigDataBaseDTO configuracionInicial = SerializadorBD.DeSerializar();

		if (configuracionInicial == null) {
			VentanaConfigDataBase configuracion = new VentanaConfigDataBase();
			ControladorConfiguracion controladorConfig = new ControladorConfiguracion(configuracion);

			controladorConfig.iniciar();
		} else {

			ConfigDataBaseDTO otra = SerializadorBD.DeSerializar();
			

			if (Conexion.isFallo()) {// si falla la conexion le vuelvo a pedir
										// los datos de conexion a la base

				VentanaConfigDataBase configuracion = new VentanaConfigDataBase();
				ControladorConfiguracion controladorConfig = new ControladorConfiguracion(configuracion);

				controladorConfig.iniciar();

			} else {

				ControladorVentanaLogin controlLogin = new ControladorVentanaLogin();
				controlLogin.getPantalla();

			}
		}

	}
}