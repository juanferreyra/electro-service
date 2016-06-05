package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import dto.UsuarioDTO;
import modelo.Usuario;
import presentacion.vista.VentanaLogin;
import presentacion.vista.VentanaPresentacion;

public class ControladorVentanaLogin implements ActionListener {

	private VentanaLogin login;
	private Usuario usuario;

	public ControladorVentanaLogin() {
		this.usuario = new Usuario();
		this.login = new VentanaLogin();
		this.login.setVisible(true);
		this.login.getUsuario_txf().addActionListener(this);
		this.login.getContrasena_txf().addActionListener(this);
		this.login.getLogin_btn().addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.login.getLogin_btn()) {
					
			boolean existe = this.usuario.existeUsuario(this.login.getUsuario_txf().getText());
			
			if(!existe){
				JOptionPane.showMessageDialog(this.login, "Usuario o contraseña incorrectos", "Atencion!",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				UsuarioDTO user = this.usuario.obtenerUsuario(this.login.getUsuario_txf().getText());
				
				boolean coincidePassword = user.getPassword().equals(String.copyValueOf(this.login.getContrasena_txf().getPassword()));
				
				if(!coincidePassword) {
					JOptionPane.showMessageDialog(this.login, "Usuario o contraseña incorrectos", "Atencion!",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					this.login.setVisible(false);
					VentanaPresentacion presentacion = new VentanaPresentacion(user);
					presentacion.start();
				}
			}
		}
	}

	public void getPantalla() {
		login.setVisible(true);
	}

}
