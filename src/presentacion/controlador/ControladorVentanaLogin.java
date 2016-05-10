package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dto.PerfilDTO;
import dto.UsuarioDTO;
import presentacion.vista.VentanaLogin;
import presentacion.vista.VentanaPrincipal;

public class ControladorVentanaLogin implements ActionListener {

	private VentanaLogin login;
	private ControladorVentanaPrincipal controladorVentanaPrincipal;

	public ControladorVentanaLogin() {

		this.login = new VentanaLogin();
		this.login.setVisible(true);
		this.login.getUsuario_txf().addActionListener(this);
		this.login.getContrasena_txf().addActionListener(this);
		this.login.getLogin_btn().addActionListener(this);
	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.login.getLogin_btn()) {

			if (this.login.getUsuario_txf().getText().equals("admin")
					& (this.login.getContrasena_txf().getText().equals("admin"))) {

				PerfilDTO perf1 = new PerfilDTO("ADMINISTRATIVO");
				UsuarioDTO user1 = new UsuarioDTO(1, "ROBERTO", "CARLOS", "admin", perf1);
				
				this.login.setVisible(false);
				controladorVentanaPrincipal = new ControladorVentanaPrincipal(new VentanaPrincipal(),user1);
				controladorVentanaPrincipal.iniciar();

			} else if (this.login.getUsuario_txf().getText().equals("tecnico")
					& (this.login.getContrasena_txf().getText().equals("tecnico"))) {

				

				PerfilDTO perf2 = new PerfilDTO("TECNICO");
				UsuarioDTO user2 = new UsuarioDTO(2, "OSCAR", "PINTOS", "tecnico", perf2);

				this.login.setVisible(false);
				controladorVentanaPrincipal = new ControladorVentanaPrincipal(new VentanaPrincipal(),user2);
				controladorVentanaPrincipal.iniciar();

			} else if (this.login.getUsuario_txf().getText().equals("jefe")
					& (this.login.getContrasena_txf().getText().equals("jefe"))) {


				PerfilDTO perf3 = new PerfilDTO("JEFE");
				UsuarioDTO user3 = new UsuarioDTO(3, "JOAQUIN", "TELECHEA", "jefe", perf3);
				
				this.login.setVisible(false);
				controladorVentanaPrincipal = new ControladorVentanaPrincipal(new VentanaPrincipal(),user3);
				controladorVentanaPrincipal.iniciar();

			} else {

				JOptionPane.showMessageDialog(this.login, "Usuario o contraseña incorrectos", "Atencion!",
						JOptionPane.INFORMATION_MESSAGE);

				this.login.getUsuario_txf().setText("");
				// this.login.getContrasena_txf().setText("");

			}

		}
	}

	public void getPantalla() {
		login.setVisible(true);
	}

}
