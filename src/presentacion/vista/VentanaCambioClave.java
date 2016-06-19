package presentacion.vista;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class VentanaCambioClave extends JFrame {
	public VentanaCambioClave() {
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 887, 608);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPresentacion.class.getResource("/logo.png")));
		getContentPane().setLayout(null);
		
		JLabel lblModificarClave = new JLabel("Modificar clave");
		lblModificarClave.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificarClave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblModificarClave.setBounds(10, 25, 851, 14);
		getContentPane().add(lblModificarClave);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario.setBounds(289, 97, 127, 14);
		getContentPane().add(lblUsuario);
		
		JLabel lblContraseaVieja = new JLabel("Contrase\u00F1a Vieja");
		lblContraseaVieja.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContraseaVieja.setBounds(289, 134, 127, 14);
		getContentPane().add(lblContraseaVieja);

	}

	private static final long serialVersionUID = 1L;

}
