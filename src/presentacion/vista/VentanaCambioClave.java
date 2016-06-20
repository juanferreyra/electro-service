package presentacion.vista;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class VentanaCambioClave extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField usuario_txf;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JPasswordField passwordField_Vieja;
	private JPasswordField passwordField_Nueva;

	public VentanaCambioClave() {
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 542, 262);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPresentacion.class.getResource("/logo.png")));
		getContentPane().setLayout(null);

		setVisible(true);

		JLabel lblModificarClave = new JLabel("Modificar clave");
		lblModificarClave.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificarClave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblModificarClave.setBounds(10, 24, 503, 14);
		getContentPane().add(lblModificarClave);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario.setBounds(77, 81, 127, 14);
		getContentPane().add(lblUsuario);

		JLabel lblContraseaVieja = new JLabel("Contrase\u00F1a Vieja");
		lblContraseaVieja.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContraseaVieja.setBounds(77, 106, 127, 19);
		getContentPane().add(lblContraseaVieja);

		JLabel lblContraseaNueva = new JLabel("Contrase\u00F1a Nueva");
		lblContraseaNueva.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContraseaNueva.setBounds(77, 131, 127, 14);
		getContentPane().add(lblContraseaNueva);

		usuario_txf = new JTextField();
		usuario_txf.setBounds(214, 80, 242, 20);
		getContentPane().add(usuario_txf);
		usuario_txf.setColumns(10);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(168, 190, 89, 23);
		getContentPane().add(btnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(267, 190, 89, 23);
		getContentPane().add(btnCancelar);

		passwordField_Vieja = new JPasswordField();
		passwordField_Vieja.setBounds(214, 107, 242, 20);
		getContentPane().add(passwordField_Vieja);

		passwordField_Nueva = new JPasswordField();
		passwordField_Nueva.setBounds(214, 130, 242, 20);
		getContentPane().add(passwordField_Nueva);

	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(JButton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JTextField getUsuario_txf() {
		return usuario_txf;
	}

	public void setUsuario_txf(JTextField usuario_txf) {
		this.usuario_txf = usuario_txf;
	}

	public JPasswordField getPasswordField_Vieja() {
		return passwordField_Vieja;
	}

	public void setPasswordField_Vieja(JPasswordField passwordField_Vieja) {
		this.passwordField_Vieja = passwordField_Vieja;
	}

	public JPasswordField getPasswordField_Nueva() {
		return passwordField_Nueva;
	}

	public void setPasswordField_Nueva(JPasswordField passwordField_Nueva) {
		this.passwordField_Nueva = passwordField_Nueva;
	}

}
