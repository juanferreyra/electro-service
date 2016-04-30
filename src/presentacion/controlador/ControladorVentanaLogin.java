package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dto.PerfilDTO;
import dto.UsuarioDTO;
import modelo.Usuario;
import presentacion.vista.VentanaIngreso;
import presentacion.vista.VentanaListaIngresos;
import presentacion.vista.VentanaLogin;
import presentacion.vista.VentanaPrincipal;

public class ControladorVentanaLogin implements ActionListener{
	
	private VentanaLogin login;
	private VentanaPrincipal ventanaPrincipal;
	private ControladorVentanaPrincipal controlVentanaPrincipal;
	
	
	public ControladorVentanaLogin(VentanaLogin login) {
		
		this.login = login;
		
		this.login.getUsuario_txf().addActionListener(this);
		this.login.getContrasena_txf().addActionListener(this);
		this.login.getLogin_btn().addActionListener(this);
	}
	
	
	
	public void actionPerformed(ActionEvent e) {


		if (e.getSource() == this.login.getLogin_btn()){

			if (this.login.getUsuario_txf().getText().equals("admin")
					& (this.login.getContrasena_txf().getText().equals("1234"))){
				
				this.login.setVisible(false);
				
				PerfilDTO perf1 = new PerfilDTO("ADMINISTRATIVO");
				UsuarioDTO user1 = new UsuarioDTO(1,"ROBERTO","CARLOS","1234",perf1);
				
				ventanaPrincipal = new VentanaPrincipal(user1);
				ventanaPrincipal.setVisible(true);
				
				controlVentanaPrincipal = new ControladorVentanaPrincipal(ventanaPrincipal);
				controlVentanaPrincipal.iniciar();
				
								
			}else if (this.login.getUsuario_txf().getText().equals("tecnico")
					& (this.login.getContrasena_txf().getText().equals("1212"))){

				this.login.setVisible(false);
				
				PerfilDTO perf2 = new PerfilDTO("TECNICO");
				UsuarioDTO user2 = new UsuarioDTO(2,"OSCAR","PINTOS","1212",perf2);
				
				ventanaPrincipal = new VentanaPrincipal(user2);
				ventanaPrincipal.setVisible(true);
				
				controlVentanaPrincipal = new ControladorVentanaPrincipal(ventanaPrincipal);
				controlVentanaPrincipal.iniciar();
				
		

			}else if (this.login.getUsuario_txf().getText().equals("jefe")
					& (this.login.getContrasena_txf().getText().equals("0000"))){

				this.login.setVisible(false);
				
				PerfilDTO perf3 = new PerfilDTO("JEFE");
				UsuarioDTO user3 = new UsuarioDTO(3,"JOAQUIN","TELECHEA","0000",perf3);
				
				ventanaPrincipal = new VentanaPrincipal(user3);
				ventanaPrincipal.setVisible(true);
				
				controlVentanaPrincipal = new ControladorVentanaPrincipal(ventanaPrincipal);
				controlVentanaPrincipal.iniciar();
				
				


			}else{
				
				JOptionPane.showMessageDialog(this.login, "Usuario o contraseña incorrectos", "Atencion!", 
						JOptionPane.INFORMATION_MESSAGE);
				
				this.login.getUsuario_txf().setText("");
				//this.login.getContrasena_txf().setText("");
				
			}


		}
	}


}
