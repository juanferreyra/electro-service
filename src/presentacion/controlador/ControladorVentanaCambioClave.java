package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dto.UsuarioDTO;
import modelo.Usuario;
import persistencia.dao.UsuarioDAO;
import presentacion.vista.VentanaCambioClave;
import presentacion.vista.VentanaPresentacion;

public class ControladorVentanaCambioClave implements ActionListener {

	private VentanaCambioClave ventanaCambioClave;
	private Usuario usuario;

	public ControladorVentanaCambioClave(VentanaCambioClave ventanaCambioClave) {
		this.ventanaCambioClave = ventanaCambioClave;
		this.ventanaCambioClave.getBtnAceptar().addActionListener(this);
	}

	public void inicializar() {
		this.usuario = new Usuario();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.ventanaCambioClave.getBtnAceptar()) {

			boolean existe = this.usuario.existeUsuario(this.ventanaCambioClave.getUsuario_txf().getText());

			if (!existe) {
				JOptionPane.showMessageDialog(this.ventanaCambioClave,
						"El usuario ingresado es incorrecto. Por favor, vuelva a intentarlo.");
				limpiarCampos();
			} else {
				// Si existe, valido que la pass concuerde.
				UsuarioDTO user = this.usuario.obtenerUsuario(this.ventanaCambioClave.getUsuario_txf().getText());

				boolean coincidePassword = user.getPassword()
						.equals(String.copyValueOf(this.ventanaCambioClave.getPasswordField_Vieja().getPassword()));

				if (!coincidePassword) {
					JOptionPane.showMessageDialog(this.ventanaCambioClave,
							"El usuario y/o contraseña son incorrectos. Por favor, vuelva a intentarlo.");
					limpiarCampos();
				} else {
					// Si coincide pass con user, actualizo la contraseña.

					String[] opciones = { "SI", "NO" };
					int seleccion = JOptionPane.showOptionDialog(this.ventanaCambioClave,
							"¿Está seguro que desea cambiar la contraseña?", "", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);

					if (seleccion == JOptionPane.YES_OPTION) {
						UsuarioDAO userDAO = new UsuarioDAO();
						user.setPassword(this.ventanaCambioClave.getPasswordField_Nueva().getText());

						userDAO.update(user);
						this.ventanaCambioClave.dispose();
					}
					limpiarCampos();

				}
			}

		} else if (e.getSource() == this.ventanaCambioClave.getBtnCancelar()) {

			this.ventanaCambioClave.dispose();
		}

	}

	private void limpiarCampos() {
		this.ventanaCambioClave.getPasswordField_Nueva().setText(null);
		this.ventanaCambioClave.getPasswordField_Vieja().setText(null);
		this.ventanaCambioClave.getUsuario_txf().setText("");
	}

}
