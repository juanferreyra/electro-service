package persistencia.dao;

import java.io.FileWriter;
import java.io.PrintWriter;

public class BackUp {

	
	public BackUp(){
		

	}

	public void CrearBackup(String path ,String usuario, String contrasenia){


		Process p = null;
		try {
			Runtime runtime = Runtime.getRuntime();
			p = runtime.exec("mysqldump -u"+usuario+" -p"+contrasenia+" --add-drop-database -B 20161_service_g2 -r " + path );
			//change the dbpass and dbname with your dbpass and dbname
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
}
