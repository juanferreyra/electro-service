package persistencia.serializar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dto.ConfigDataBaseDTO;

public class SerializadorBD {

	public static void Serializar(ConfigDataBaseDTO configuracion) {

		try

		{
			FileOutputStream fos = new FileOutputStream("config.txt");
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(configuracion);
			out.close();
		} catch (Exception ex) {
		}

	}

	public static ConfigDataBaseDTO DesSerializar() {

		ConfigDataBaseDTO nueva = null;
		try {
			FileInputStream fis = new FileInputStream("config.txt");
			ObjectInputStream in = new ObjectInputStream(fis);
			nueva = (ConfigDataBaseDTO) in.readObject();
			in.close();
		} catch (Exception ex) {
		}

		return nueva;

	}
}
