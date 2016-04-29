package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import presentacion.vista.VentanaLogin;

public class ControladorVentanaLogin implements ActionListener{
	
	private VentanaLogin login;
	
	
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

			}else if (this.login.getUsuario_txf().getText().equals("tecnico")
					& (this.login.getContrasena_txf().getText().equals("tecnico"))){

				this.login.setVisible(false);

			}else if (this.login.getUsuario_txf().getText().equals("jefe")
					& (this.login.getContrasena_txf().getText().equals("jefe"))){

				this.login.setVisible(false);

			}else{
				
				JOptionPane.showMessageDialog(this.login, "Usuario o contraseña incorrectos", "Atencion!", 
						JOptionPane.INFORMATION_MESSAGE);
				
				this.login.getUsuario_txf().setText("");
				this.login.getContrasena_txf().setText("");
				
			}


		}
	}


}
