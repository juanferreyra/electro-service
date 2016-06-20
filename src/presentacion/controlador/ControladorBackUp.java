package presentacion.controlador;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import dto.ConfigDataBaseDTO;
import persistencia.dao.BackUp;
import persistencia.serializar.SerializadorBD;
import presentacion.vista.VentanaPrincipal;

public class ControladorBackUp {

	private String extension = ".sql";
	private BackUp backUp;
	private String usuario;
	private String contrasenia;
	private VentanaPrincipal ventanaPrincipal;

	public ControladorBackUp(VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	public void Inicializar() {

		this.backUp = new BackUp();
		obtenerDatosConexion();
	}

	private void obtenerDatosConexion() {

		ConfigDataBaseDTO datos = SerializadorBD.DesSerializar();

		this.usuario = datos.getUsuario();
		this.contrasenia = datos.getContrasena();

	}

	public void Restaurar() {
		try {
			String path = obtenerPath();

			if (path != null && path != "") {
				int respuesta = JOptionPane.showConfirmDialog(null, "¿ Esta seguro que desea restaurar los datos?", "",
						JOptionPane.YES_NO_OPTION);

				if (respuesta == JOptionPane.YES_OPTION) {

					this.backUp.restore(this.usuario, this.contrasenia, path);

					JOptionPane.showMessageDialog(null,
							"La exportación de datos se ha realizado correctamente, es necesario que reinicie la aplicación.");
				} else
					return;

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error inesperado al importar los datos.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void crearBackUp() {
		try {
			String path = guardarPath();

			if (path != null) {
				if (path != "") {

					// System.out.println(path + usuario + contrasenia);

					backUp.CrearBackup(path, this.usuario, this.contrasenia);

					JOptionPane.showMessageDialog(null, "La copia de seguridad se ha realizado correctamente.");

				} else {

					JOptionPane.showMessageDialog(null, "La exportación de datos debe realizarse en un archivo .sql",
							"ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Error inesperado al intentar exportar los datos.", "ERROR",
					JOptionPane.ERROR_MESSAGE);

		}
	}

	private String obtenerPath() {
		String path = "";
		JFileChooser file = getFileChooser();
		int estado = file.showOpenDialog(this.ventanaPrincipal);
		if (estado == JFileChooser.APPROVE_OPTION) {
			path = getPath(file);
			if (!path.contains(extension)) {
				return "";
			} else {
				// path += extension;
				return path;
			}
		}
		return null;
	}

	private String guardarPath() {

		String path = "";
		JFileChooser file = getFileChooser();

		int estado = file.showSaveDialog(this.ventanaPrincipal);

		if (estado == JFileChooser.APPROVE_OPTION) {
			path = getPath(file);

			if ((path.contains(".") && !path.contains(extension)) || masDeUnPunto(path)) {
				file.cancelSelection();
				return "";
			}
			path = (path.contains(extension)) ? path : path + extension;
			return path;
		}
		return null;
	}

	// seteo del file chooser
	private JFileChooser getFileChooser() {
		JFileChooser file = new JFileChooser();
		FileFilter filtro = new FileNameExtensionFilter("TEXT FILES", "sql");
		file.setFileFilter(filtro);
		return file;
	}

	// path seleccionado
	private String getPath(JFileChooser file) {
		String path;
		path = file.getSelectedFile().getAbsolutePath();
		return path;
	}

	// foramto del path
	private boolean masDeUnPunto(String path) {
		return contadorPuntos(path) > 1;
	}

	private int contadorPuntos(String path) {
		int ret = 0;
		for (int i = 0; i < path.length(); i++)
			if (path.charAt(i) == '.')
				ret++;
		return ret;
	}

}