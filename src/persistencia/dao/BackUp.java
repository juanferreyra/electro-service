package persistencia.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ibatis.common.jdbc.ScriptRunner;

import persistencia.conexion.Conexion;

public class BackUp {
	private static final String findpath = "select @@basedir";
	private Conexion conexion = Conexion.getConexion();

	public BackUp() {

	}

	public void CrearBackup(String path, String usuario, String contrasenia) {

		Process p = null;
		try {
			Runtime runtime = Runtime.getRuntime();
			p = runtime.exec(pathMysql() + "/bin/mysqldump -u" + usuario + " -p" + contrasenia
					+ " --add-drop-database -B 20161_service_g2 -r " + path);
			int processComplete = p.waitFor();

			if (processComplete == 0) {

				System.out.println("Backup created successfully!");

			} else {

				System.out.println("Could not create the backup");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private String pathMysql() {

		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		String ret = "";

		try {
			statement = conexion.getSQLConexion().prepareStatement(findpath);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				ret = resultSet.getString("@@basedir");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		System.out.println(ret);
		return ret;
	}

	public void restore(String usuario, String contrasenia, String path) {

		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/20161_service_g2", usuario, contrasenia);

			Statement statement = con.createStatement();
			statement.executeUpdate("CREATE DATABASE IF NOT EXISTS 20161_service_g2");// Determina
																						// que
																						// usara
																						// la
																						// BD

			ScriptRunner sr = new ScriptRunner(con, false, false);

			Reader reader = new BufferedReader(new FileReader(path));

			sr.runScript(reader);

		} catch (Exception e) {
			System.err.println("Failed to Execute" + path + " El error es: " + e.getMessage());
		}
	}
}
