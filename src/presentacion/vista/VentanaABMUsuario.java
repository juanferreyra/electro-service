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
	private String[] nombreColumnas = { "id","Nick","Nombre", "Apellido", "Perfil", "idPerfil" };
	private JComboBox <String> perfil_comboBox ;
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
		
		JLabel nick_lbl = new JLabel("Nick");
		nick_lbl.setBounds(123, 62, 71, 14);
		contentPane.add(nick_lbl);
		
		JLabel nombre_lbl = new JLabel("Nombre");
		nombre_lbl.setBounds(123, 95, 71, 14);
		contentPane.add(nombre_lbl);

		JLabel apellido_lbl = new JLabel("Apellido");
		apellido_lbl.setBounds(123, 120, 71, 14);
		contentPane.add(apellido_lbl);

		JLabel pass_lbl = new JLabel("Contrase\u00F1a");
		pass_lbl.setBounds(123, 145, 71, 14);
		contentPane.add(pass_lbl);
		
		nick_txt = new JTextField();
		nick_txt.setBounds(190, 59, 392, 20);
		contentPane.add(nick_txt);
		nick_txt.setColumns(10);

		nombre_txt = new JTextField();
		nombre_txt.setBounds(190, 89, 392, 20);
		contentPane.add(nombre_txt);
		nombre_txt.setColumns(10);

		apellido_txt = new JTextField();
		apellido_txt.setColumns(10);
		apellido_txt.setBounds(190, 117, 392, 20);
		contentPane.add(apellido_txt);

		pass_txt = new JPasswordField();
		pass_txt.setBounds(190, 145, 392, 20);
		contentPane.add(pass_txt);

		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setBounds(123, 199, 71, 14);
		contentPane.add(lblPerfil);

		perfil_comboBox = new JComboBox<String>();
		perfil_comboBox.setBounds(190, 193, 392, 20);
		contentPane.add(perfil_comboBox);

		JLabel title_lbl = new JLabel("Nuevo usuario");
		title_lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		title_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		title_lbl.setBounds(34, 24, 712, 14);
		contentPane.add(title_lbl);

		limpiar_btn = new JButton("Limpiar");
		limpiar_btn.setBounds(664, 392, 89, 23);
		getContentPane().add(limpiar_btn);

		guardar_btn = new JButton("Guardar");
		guardar_btn.setBounds(565, 392, 89, 23);
		getContentPane().add(guardar_btn);

		JScrollPane repuesto_scrollPane = new JScrollPane();
		repuesto_scrollPane.setBounds(123, 246, 459, 91);
		getContentPane().add(repuesto_scrollPane);

		modelUsuario = new DefaultTableModel(null, nombreColumnas){

		/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		@Override
		public boolean isCellEditable(int fila, int columna) {

			return false;
		}
	};
		tablaUsuario = new JTable(modelUsuario);
		repuesto_scrollPane.setViewportView(tablaUsuario);

		eliminarItem_btn = new JButton("Eliminar Item");
		eliminarItem_btn.setBounds(434, 392, 121, 23);
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
