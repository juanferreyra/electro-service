package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
	private JTable tablaUsuario;
	private DefaultTableModel modelUsuario;
	private String[] nombreColumnas = { "id", "Nick", "Nombre", "Apellido", "Perfil", "idPerfil" };
	private JComboBox<String> perfil_comboBox;
	private JButton limpiar_btn;
	private JButton guardar_btn;
	private JButton eliminarItem_btn;
	private JTextField nick_txt;

	public VentanaABMUsuario() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 772, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		JLabel nick_lbl = new JLabel("Nick");
		nick_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nick_lbl.setBounds(142, 82, 146, 14);
		contentPane.add(nick_lbl);

		JLabel nombre_lbl = new JLabel("Nombre");
		nombre_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nombre_lbl.setBounds(142, 113, 129, 14);
		contentPane.add(nombre_lbl);

		JLabel apellido_lbl = new JLabel("Apellido");
		apellido_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		apellido_lbl.setBounds(142, 143, 146, 17);
		contentPane.add(apellido_lbl);

		JLabel pass_lbl = new JLabel("Contrase\u00F1a");
		pass_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pass_lbl.setBounds(142, 171, 178, 14);
		contentPane.add(pass_lbl);

		nick_txt = new JTextField();
		nick_txt.setBounds(221, 81, 373, 20);
		contentPane.add(nick_txt);
		nick_txt.setColumns(10);

		nombre_txt = new JTextField();
		nombre_txt.setBounds(221, 112, 373, 20);
		contentPane.add(nombre_txt);
		nombre_txt.setColumns(10);

		apellido_txt = new JTextField();
		apellido_txt.setColumns(10);
		apellido_txt.setBounds(221, 143, 373, 20);
		contentPane.add(apellido_txt);

		pass_txt = new JPasswordField();
		pass_txt.setBounds(221, 171, 160, 20);
		contentPane.add(pass_txt);

		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPerfil.setBounds(413, 172, 119, 14);
		contentPane.add(lblPerfil);

		perfil_comboBox = new JComboBox<String>();
		perfil_comboBox.setBounds(465, 170, 129, 20);
		contentPane.add(perfil_comboBox);

		JLabel title_lbl = new JLabel("Nuevo usuario");
		title_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		title_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		title_lbl.setBounds(10, 11, 736, 15);
		contentPane.add(title_lbl);

		limpiar_btn = new JButton("Limpiar");
		limpiar_btn.setBounds(445, 389, 129, 23);
		getContentPane().add(limpiar_btn);

		guardar_btn = new JButton("Guardar");
		guardar_btn.setBounds(201, 389, 119, 23);
		getContentPane().add(guardar_btn);

		JScrollPane repuesto_scrollPane = new JScrollPane();
		repuesto_scrollPane.setBounds(10, 230, 736, 121);
		getContentPane().add(repuesto_scrollPane);

		modelUsuario = new DefaultTableModel(null, nombreColumnas) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int fila, int columna) {

				return false;
			}
		};
		tablaUsuario = new JTable(modelUsuario);
		tablaUsuario.getColumnModel().getColumn(0).setMaxWidth(0);
		tablaUsuario.getColumnModel().getColumn(0).setMinWidth(0);
		tablaUsuario.getColumnModel().getColumn(0).setPreferredWidth(0);
		repuesto_scrollPane.setViewportView(tablaUsuario);

		eliminarItem_btn = new JButton("Eliminar");
		eliminarItem_btn.setBounds(330, 389, 105, 23);
		getContentPane().add(eliminarItem_btn);
	}

	public JTextField getNick_txt() {
		return nick_txt;
	}

	public JTextField getNombre_txt() {
		return nombre_txt;
	}

	public JTextField getApellido_txt() {
		return apellido_txt;
	}

	public JPasswordField getPass_txt() {
		return pass_txt;
	}

	public JTable getTablaUsuario() {
		return tablaUsuario;
	}

	public DefaultTableModel getModelUsuario() {
		return modelUsuario;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JComboBox<String> getPerfil_comboBox() {
		return perfil_comboBox;
	}

	public JButton getLimpiar_btn() {
		return limpiar_btn;
	}

	public JButton getGuardar_btn() {
		return guardar_btn;
	}

	public JButton getEliminarItem_btn() {
		return eliminarItem_btn;
	}

}
