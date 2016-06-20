package main;

import java.util.Properties;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;

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

		Properties props = new Properties();
		props.put("logoString", "");

		AluminiumLookAndFeel.setCurrentTheme(props);
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		ConfigDataBaseDTO configuracionInicial = SerializadorBD.DesSerializar();

		// Si es la primera vez que voy a configurar
		if (configuracionInicial == null) {
			VentanaConfigDataBase configuracion = new VentanaConfigDataBase();
			ControladorConfiguracion controladorConfig = new ControladorConfiguracion(configuracion);

			controladorConfig.iniciar();
		} else {

			if (Conexion.isFallo()) {// si falla la conexion le vuelvo a pedir
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