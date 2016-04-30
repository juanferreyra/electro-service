package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class VentanaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usuario_txf;
	private JPasswordField contrasena_txf;
	private JButton login_btn;

	
	public VentanaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 316);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String path = "/logo.png";
		URL url = this.getClass().getResource(path);
		ImageIcon icon = new ImageIcon(url);

		JLabel logo_lbl = new JLabel("");
		logo_lbl.setIcon(new ImageIcon(VentanaLogin.class.getResource("/logo.png")));
		logo_lbl.setBounds(22, 56, 243, 145);
		contentPane.add(logo_lbl);

		usuario_txf = new JTextField();
		usuario_txf.setBounds(409, 83, 157, 20);
		contentPane.add(usuario_txf);
		usuario_txf.setColumns(10);

		JLabel usuario_lbl = new JLabel("Usuario");
		usuario_lbl.setBounds(298, 86, 101, 14);
		contentPane.add(usuario_lbl);

		JLabel contrasena_lbl = new JLabel("Contrase\u00F1a");
		contrasena_lbl.setBounds(298, 146, 101, 14);
		contentPane.add(contrasena_lbl);

		contrasena_txf = new JPasswordField();
		contrasena_txf.setBounds(409, 143, 156, 20);
		contentPane.add(contrasena_txf);

		login_btn = new JButton("Login");
		login_btn.setBounds(409, 202, 157, 23);
		contentPane.add(login_btn);
		
		
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
