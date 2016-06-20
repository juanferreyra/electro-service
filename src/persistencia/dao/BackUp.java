package persistencia.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.ibatis.common.jdbc.ScriptRunner;

public class BackUp {

	public BackUp() {

	}

	public void CrearBackup(String path, String usuario, String contrasenia) {

		Process p = null;
		try {
			Runtime runtime = Runtime.getRuntime();

			p = runtime.exec("mysqldump -u" + usuario + " -p" + contrasenia
					+ " --add-drop-database -B 20161_service_g2 -r " + path);
			// change the dbpass and dbname with your dbpass and dbname
			String executeCmd = "mysqldump -u" + usuario + " -p" + contrasenia
					+ " --add-drop-database -B 20161_service_g2 -r " + path;
			Process runtime2 = Runtime.getRuntime().exec(executeCmd);
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
