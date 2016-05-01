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
	private static ConfigDataBaseDTO configuracionDB = SerializadorBD.DeSerializar();;
	private static boolean fallo;

	private Conexion(ConfigDataBaseDTO conf) {
		Conexion.configuracionDB = conf;
		fallo = false;
		if (conf != null) {
			try {
				Class.forName(driver).newInstance();
				conexion = DriverManager.getConnection(
						"jdbc:mysql://" + conf.getUrl() + ":" + conf.getPuerto() + "/electro_service_db",
						"" + conf.getUsuario() + "", "" + conf.getContrasena() + "");
			} catch (Exception e) {
				System.out.println(e.getMessage());

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
				} else {
					fallo = true;
				}
			}
		} else {
			System.out.println("La configuración de base de datos no existe.");
		}
	}

	public static Conexion getConexion() {
		if (instancia == null) {
			instancia = new Conexion(configuracionDB);
		}
		return instancia;
	}

	public Connection getSQLConexion() {
		return conexion;
	}

	public void cerrarConexion() {
		instancia = null;
	}

	public static boolean isFallo() {
		return fallo;
	}

}