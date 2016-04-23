package persistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion 
{
	public static Conexion instancia;
	private final static String driver = "com.mysql.jdbc.Driver";
	private Connection conexion;
	
	public Conexion()
	{
		try
		{
			Class.forName(driver).newInstance();
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/electro-service-db","root","1234");
			System.out.println("Conexion exitosa");
		}
		catch(Exception e)
		{
			System.out.println("Conexion fallida");
		}
	}
	
	public static Conexion getConexion()   
	{								
		if(instancia == null)
		{
			instancia = new Conexion();
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
}
