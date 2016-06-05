package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;

public class VentanaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usuario_txf;
	private JPasswordField contrasena_txf;
	private JButton login_btn;

	public VentanaLogin() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 280);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPresentacion.class.getResource("/logo.png")));

		usuario_txf = new JTextField();
		usuario_txf.setBounds(204, 97, 228, 20);
		contentPane.add(usuario_txf);
		usuario_txf.setColumns(10);

		JLabel usuario_lbl = new JLabel("Usuario");
		usuario_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		usuario_lbl.setBounds(102, 98, 173, 14);
		contentPane.add(usuario_lbl);

		JLabel contrasena_lbl = new JLabel("Contrase\u00F1a");
		contrasena_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contrasena_lbl.setBounds(102, 144, 173, 14);
		contentPane.add(contrasena_lbl);

		contrasena_txf = new JPasswordField();
		contrasena_txf.setBounds(204, 143, 228, 20);
		contentPane.add(contrasena_txf);

		login_btn = new JButton("Login");
		login_btn.setBounds(204, 196, 228, 23);
		contentPane.add(login_btn);

		JLabel lblDebeLoguearsePara = new JLabel("<html><b>Por favor, debe loguearse para continuar.</b><br></html>");
		lblDebeLoguearsePara.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDebeLoguearsePara.setBounds(176, 49, 292, 14);
		contentPane.add(lblDebeLoguearsePara);

	}

	public JTextField getUsuario_txf() {
		return usuario_txf;
	}

	public JPasswordField getContrasena_txf() {
		return contrasena_txf;
	}

	public JButton getLogin_btn() {
		return login_btn;
	}
}
