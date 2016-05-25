package presentacion.vista;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Font;

public class VentanaABMUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nombre_txt;
	private JTextField apellido_txt;
	private JPasswordField pass_txt;

	public VentanaABMUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel nombre_lbl = new JLabel("Nombre");
		nombre_lbl.setBounds(34, 67, 71, 14);
		contentPane.add(nombre_lbl);
		
		JLabel apellido_lbl = new JLabel("Apellido");
		apellido_lbl.setBounds(34, 105, 71, 14);
		contentPane.add(apellido_lbl);
		
		JLabel pass_lbl = new JLabel("Contrase\u00F1a");
		pass_lbl.setBounds(34, 148, 71, 14);
		contentPane.add(pass_lbl);
		
		nombre_txt = new JTextField();
		nombre_txt.setBounds(115, 64, 269, 20);
		contentPane.add(nombre_txt);
		nombre_txt.setColumns(10);
		
		apellido_txt = new JTextField();
		apellido_txt.setColumns(10);
		apellido_txt.setBounds(115, 102, 269, 20);
		contentPane.add(apellido_txt);
		
		pass_txt = new JPasswordField();
		pass_txt.setBounds(115, 145, 269, 20);
		contentPane.add(pass_txt);
		
		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setBounds(34, 184, 71, 14);
		contentPane.add(lblPerfil);
		
		JComboBox perfil_comboBox = new JComboBox();
		perfil_comboBox.setBounds(115, 181, 269, 20);
		contentPane.add(perfil_comboBox);
		
		JLabel title_lbl = new JLabel("Nuevo usuario");
		title_lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		title_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		title_lbl.setBounds(34, 11, 350, 14);
		contentPane.add(title_lbl);
		
		JButton cancelar_btn = new JButton("Cancelar");
		cancelar_btn.setBounds(315, 228, 89, 23);
		getContentPane().add(cancelar_btn);
		
		JButton guardar_btn = new JButton("Guardar");
		guardar_btn.setBounds(216, 228, 89, 23);
		getContentPane().add(guardar_btn);
	}
}
