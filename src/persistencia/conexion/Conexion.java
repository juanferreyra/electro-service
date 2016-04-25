package persistencia.conexion;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

import dto.ConfigDataBaseDTO;

public class Conexion implements Serializable
{
	private static final long serialVersionUID = 1L;
	public static Conexion instancia;
	private final static String driver = "com.mysql.jdbc.Driver";
	private Connection conexion;
	private static ConfigDataBaseDTO conection;
	private static boolean fallo; 
	
	public Conexion(ConfigDataBaseDTO con)
	{
		Conexion.conection = con;
		fallo = false;
		try
		{
			Class.forName(driver).newInstance();
			conexion = DriverManager.getConnection("jdbc:mysql://"+con.getUrl()+":"+con.getPuerto() 
			+"/electro_service_db",""+con.getUsuario()+"",""+con.getContrasena()+"");	
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			
			//si me doy cuenta que la base no existe
			if(e.getMessage().equals("Unknown database 'electro_service_db'"))
			{
				try
				{
					//la creo entera
					CargadorDataBase cargar = new CargadorDataBase(con.getUrl(), con.getPuerto(), "electro_service_db", con.getUsuario(), con.getContrasena());;
					Boolean cargo = cargar.crearDB();
					cargar.cargar();
					//pruebo conectarme de nuevo
					if(cargo)
					{
						Class.forName(driver).newInstance();
						conexion = DriverManager.getConnection("jdbc:mysql://"+con.getUrl()+":"+con.getPuerto() 
						+"/electro_service_db",""+con.getUsuario()+"",""+con.getContrasena()+"");
					}
				}
				catch (Exception e2)
				{
					System.out.println(e2.getMessage());
					fallo = true;
				}
			}
			else
			{
				fallo = true;
			}
		}
	}
	
	public static Conexion getConexion()   
	{								
		if(instancia == null)
		{
			instancia = new Conexion(conection);
		}
		return instancia;
	}

	public Connection getSQLConexion() 
	{
		return conexion;
	}
	
	public void cerrarConexion()
	{
		instancia = null;
	}
	
	public static boolean isFallo()
	{
		return fallo;
	}

	public static void setConection(ConfigDataBaseDTO configuracion)
	{
		Conexion.conection = configuracion;
			
		instancia = new Conexion(configuracion);
	}
	
}