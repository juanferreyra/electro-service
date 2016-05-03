package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dto.ConfigDataBaseDTO;
import persistencia.conexion.Conexion;
import persistencia.serializar.SerializadorBD;
import presentacion.vista.VentanaConfigDataBase;

public class ControladorConfiguracion implements ActionListener {

	private VentanaConfigDataBase ventanaConfiguracion;
	private ConfigDataBaseDTO configuracion;

	public ControladorConfiguracion(VentanaConfigDataBase ventanaConfiguracion) {
		this.ventanaConfiguracion = ventanaConfiguracion;
		this.ventanaConfiguracion.getBtnTest().addActionListener(this);
		this.ventanaConfiguracion.getBtnAceptar().addActionListener(this);
	}

	public void iniciar() {
		this.ventanaConfiguracion.setVisible(true);
		this.ventanaConfiguracion.getBtnAceptar().setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.ventanaConfiguracion.getBtnTest()) {
			configuracion = new ConfigDataBaseDTO(this.ventanaConfiguracion.getTxtUrl().getText(),
					this.ventanaConfiguracion.getTxtPuerto().getText(),
					this.ventanaConfiguracion.getTxtUsuario().getText(),
					this.ventanaConfiguracion.getTxtContrasena().getText());

			this.testear(configuracion);
				
		} else if (e.getSource() == this.ventanaConfiguracion.getBtnAceptar()) {
			this.ventanaConfiguracion.dispose();
			
			ControladorVentanaLogin controlLogin = new ControladorVentanaLogin();
			controlLogin.getPantalla();
		}
	}
	
	public void testear(ConfigDataBaseDTO config) {
		SerializadorBD.Serializar(config);
		Conexion.reconectar();
		if (Conexion.isFallo()){
			JOptionPane.showMessageDialog(this.ventanaConfiguracion, "Por favor revise los datos ingresados",
					"Atencion Error!", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this.ventanaConfiguracion, "Coneccion Exitosa", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
			this.ventanaConfiguracion.getBtnAceptar().setEnabled(true);
		}
	}
}