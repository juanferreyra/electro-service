package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dto.ConfigDataBaseDTO;
//import modelo.Presupuesto;
import persistencia.conexion.Conexion;
import persistencia.serializar.SerializadorBD;
import presentacion.vista.VentanaConfigDataBase;

public class ControladorConfiguracion implements ActionListener {

	private VentanaConfigDataBase ventanaConfiguracion;
	private SerializadorBD serializador;
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

			if (!Conexion.isFallo()) {
				JOptionPane.showMessageDialog(this.ventanaConfiguracion, "Coneccion Exitosa", "Atencion!",
						JOptionPane.INFORMATION_MESSAGE);
				this.ventanaConfiguracion.getBtnAceptar().setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(this.ventanaConfiguracion, "Por favor revise los datos ingresados",
						"Atencion Error!", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == this.ventanaConfiguracion.getBtnAceptar()) {
			// GUARDO
			// CONFIGURACION
			// DE
			// CONEXION
			// Y
			// SERIALIZO
			//Conexion.setConection(configuracion);
			serializador = new SerializadorBD();
			serializador.Serializar(configuracion);
			this.ventanaConfiguracion.dispose();
		}
	}
}
