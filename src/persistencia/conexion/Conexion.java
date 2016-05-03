package persistencia.conexion;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

import dto.ConfigDataBaseDTO;
import persistencia.serializar.SerializadorBD;

public class Conexion implements Serializable {
	private static final long serialVersionUID = 1L;
	public static Conexion instancia;
	private final static String driver = "com.mysql.jdbc.Driver";
	private Connection conexion;
	private static ConfigDataBaseDTO conf;
	private static boolean fallo;

	public Conexion() {
		Conexion.conf  = SerializadorBD.DesSerializar();
		
		fallo = false;
		
		if (conf != null) {
			try {
				Class.forName(driver).newInstance();
				conexion = DriverManager.getConnection(
						"jdbc:mysql://" + conf.getUrl() + ":" + conf.getPuerto() + "/electro_service_db",
						"" + conf.getUsuario() + "", "" + conf.getContrasena() + "");
			} catch (Exception e) {

				// SI BD NO EXISTE
				if (e.getMessage().equals("Unknown database 'electro_service_db'")) {
					try {
						// CREO BASE DE DATOS
						CargadorDataBase cargar = new CargadorDataBase(conf.getUrl(), conf.getPuerto(),
								"electro_service_db", conf.getUsuario(), conf.getContrasena());

						Boolean cargo = cargar.crearDB();
						cargar.cargar();
						// PRUEBO CONECTARME DE NUEVO
						if (cargo) {
							Class.forName(driver).newInstance();
							conexion = DriverManager.getConnection(
									"jdbc:mysql://" + conf.getUrl() + ":" + conf.getPuerto() + "/electro_service_db",
									"" + conf.getUsuario() + "", "" + conf.getContrasena() + "");
						}
					} catch (Exception e2) {
						System.out.println(e2.getMessage());
						fallo = true;
					}
				}
				else
				{
					fallo = true;
				}
				System.out.println(e.getMessage());
			}
		} else {
			fallo = true;
			System.out.println("La configuración de base de datos no existe.");
		}
	}

	public static Conexion getConexion() {
		if (instancia == null) {
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getSQLConexion() {
		return conexion;
	}

	public static void cerrarConexion() {
		instancia = null;
	}
	
	public static void reconectar() {
		cerrarConexion();
		Conexion.getConexion();
	}

	public static boolean isFallo() {
		return fallo;
	}
}