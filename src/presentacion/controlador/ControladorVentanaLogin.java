package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dto.PerfilDTO;
import dto.UsuarioDTO;
import presentacion.vista.VentanaIngreso;
import presentacion.vista.VentanaListaIngresos;
import presentacion.vista.VentanaLogin;
import presentacion.vista.VentanaPrincipal;

public class ControladorVentanaLogin implements ActionListener{
	
	private VentanaLogin login;
	private VentanaPrincipal ventanaPrincipal;
	
	
	public ControladorVentanaLogin(VentanaLogin login) {
		
		this.login = login;
		
		this.login.getUsuario_txf().addActionListener(this);
		this.login.getContrasena_txf().addActionListener(this);
		this.login.getLogin_btn().addActionListener(this);
	}
	
	
	
	public void actionPerformed(ActionEvent e) {


		if (e.getSource() == this.login.getLogin_btn()){

			if (this.login.getUsuario_txf().getText().equals("admin")
					& (this.login.getContrasena_txf().getText().equals("admin"))){
				
				this.login.setVisible(false);
				
				PerfilDTO admin = new PerfilDTO("admin");
				UsuarioDTO usuarioAdmin =new UsuarioDTO(admin);		
				ventanaPrincipal = new VentanaPrincipal(usuarioAdmin);
				ventanaPrincipal.setVisible(true);
				
								
			}else if (this.login.getUsuario_txf().getText().equals("tecnico")
					& (this.login.getContrasena_txf().getText().equals("tecnico"))){

				this.login.setVisible(false);
				
				PerfilDTO tecnico = new PerfilDTO("tecnico");
				UsuarioDTO usuarioTecnico =new UsuarioDTO(tecnico);		
				ventanaPrincipal = new VentanaPrincipal(usuarioTecnico);
		

			}else if (this.login.getUsuario_txf().getText().equals("jefe")
					& (this.login.getContrasena_txf().getText().equals("jefe"))){

				this.login.setVisible(false);
				
				PerfilDTO jefe = new PerfilDTO("jefe");
				UsuarioDTO usuarioJefe =new UsuarioDTO(jefe);		
				ventanaPrincipal = new VentanaPrincipal(usuarioJefe);


			}else{
				
				JOptionPane.showMessageDialog(this.login, "Usuario o contraseña incorrectos", "Atencion!", 
						JOptionPane.INFORMATION_MESSAGE);
				
				this.login.getUsuario_txf().setText("");
				this.login.getContrasena_txf().setText("");
				
			}


		}
	}


}
