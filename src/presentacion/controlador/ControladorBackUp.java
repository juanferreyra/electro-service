package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import dto.ConfigDataBaseDTO;
import persistencia.dao.BackUp;
import persistencia.serializar.SerializadorBD;
import presentacion.vista.BackUpVista;

public class ControladorBackUp implements ActionListener {

	private BackUpVista vista;
	private String extension = ".sql";
	private BackUp backUp;
	private String usuario;
	private String contrasenia;

	public ControladorBackUp(BackUpVista vista) {
		
		this.vista = vista;
		
		this.vista.getBtnCrearCopia().addActionListener(this);
		this.vista.getBtnRestaurar().addActionListener(this);
	}

	public void Inicializar() {
		
		this.vista.setVisible(true);
		this.backUp = new BackUp();
		obtenerDatosConexion();
	}

		

	private void obtenerDatosConexion() {
		
		ConfigDataBaseDTO datos = SerializadorBD.DesSerializar();
		
		this.usuario = datos.getUsuario();
		this.contrasenia = datos.getContrasena();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.vista.getBtnCrearCopia()) {
			crearBackUp();
			
		} else if (e.getSource() == this.vista.getBtnRestaurar()) {
			Restaurar();
		}
	}

	private void Restaurar() {
		try {
			String path = obtenerPath();

			if (path != null && path != "") {
				int respuesta = JOptionPane
						.showConfirmDialog(
								null,
								"¿Esta seguro de restaurar los datos ?",
								"Restaurar base de datos",
								JOptionPane.YES_NO_OPTION);

				if (respuesta == JOptionPane.YES_OPTION) {
					
					this.backUp.restore(this.usuario,this.contrasenia, path);
					
					JOptionPane.showMessageDialog(null, "Back Up realizado correctamente, debe reiniciar la aplicacion",
							"Atencion!", JOptionPane.INFORMATION_MESSAGE);
					this.vista.dispose();
				} else
					return;

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error inesperado al restaurar el backup",
					"Error !!", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void crearBackUp() {
		try {
			String path = guardarPath();
			
			if (path != null) {
				if (path != "") {
					
					System.out.println(path + usuario + contrasenia);
					
					backUp.CrearBackup(path, this.usuario, this.contrasenia);
					
					JOptionPane.showMessageDialog(null, "Copia de seguridad realizada correctamente",
							"Atencion !", JOptionPane.INFORMATION_MESSAGE);
					
				} else {
					
					JOptionPane.showMessageDialog(null, "Error de extension, El backUp debe guardarse en un archivo .sql",
							"Error !!", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Error inesperado al generar el backup",
					"Error !!", JOptionPane.ERROR_MESSAGE);
	
		}
	}

	private String obtenerPath() {
		String path = "";
		JFileChooser file = getFileChooser();
		int estado = file.showOpenDialog(this.vista);
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
		
		int estado = file.showSaveDialog(this.vista);
		
		if (estado == JFileChooser.APPROVE_OPTION) {
			path = getPath(file);
			
			if ((path.contains(".") && !path.contains(extension))
					|| masDeUnPunto(path)) {
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
	
	public static void main(String[] args) {
		
		BackUpVista v = new BackUpVista();
		ControladorBackUp c = new ControladorBackUp(v);
		c.Inicializar();
		
		
		
		
	}
}