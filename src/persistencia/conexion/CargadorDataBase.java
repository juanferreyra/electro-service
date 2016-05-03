package persistencia.conexion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.ibatis.common.jdbc.ScriptRunner;

public class CargadorDataBase
{
	private String path_SQLscript = "db.sql";
	private String host;
	//private String port;
	private String schema;
	private String user;
	private String pass;

	public CargadorDataBase(String host, String port, String schema, String user, String pass)
	{
		this.host = host;
		//this.port = port;
		this.schema = schema;
		this.user = user;
		this.pass = pass;
	}

	public Boolean crearDB()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con;
			con = DriverManager.getConnection("jdbc:mysql://" + host + "/?user=" + user + "&password=" + pass);

			Statement statement;

			try
			{
				statement = con.createStatement();
				statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + this.schema);

			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				con.close();
			}

		}
		catch (Exception e)
		{
			System.err.println("Failed to Execute" + path_SQLscript + " El error es: " + e.getMessage());
			return false;
		}
		
		return true;
	}

	public void use()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con;
			con = DriverManager.getConnection("jdbc:mysql://" + host + "/?user=" + user + "&password=" + pass);

			Statement statement;

			try
			{
				statement = con.createStatement();
				statement.executeUpdate("USE " + this.schema);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				con.close();
			}

		}
		catch (Exception e)
		{
			System.err.println("Failed to Execute" + path_SQLscript + " El error es: " + e.getMessage());
		}
	}

	public void cargar()
	{
		try
		{

			Class.forName("com.mysql.jdbc.Driver");
			Connection con;
			con = DriverManager.getConnection("jdbc:mysql://" + host + "/?user=" + user + "&password=" + pass);

			Statement statement = con.createStatement();
			statement.executeUpdate("USE " + this.schema);// Determina que usara la BD

			ScriptRunner sr = new ScriptRunner(con, false, false);

			Reader reader = new BufferedReader(new FileReader(path_SQLscript));

			sr.runScript(reader);

		}
		catch (Exception e)
		{
			System.err.println("Failed to Execute" + path_SQLscript + " El error es: " + e.getMessage());
		}
	}
//
}
