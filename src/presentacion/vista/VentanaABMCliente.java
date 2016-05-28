package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VentanaABMCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField nombre_txt;
	private JTextField apellido_txt;
	private JTextField localidad_txt;
	private JTextField direccion_txt;
	private JTextField telefono_txt;
	private JTextField email_txt;
	private JTextField documento_txt;
	private JTable tablaClientes;
	private DefaultTableModel modelClientes;
	private String[] nombreColumnas = { "id", "Nombre", "Apellido", "Documento", "Localidad", "Direccion", "Telefono",
			"Email" };
	private JButton guardar_btn;
	private JButton eliminarItem_btn;
	private JButton limpiar_btn;

	public VentanaABMCliente() {

		initialize();
	}

	private void initialize() {

		setBounds(100, 100, 742, 454);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel title_lbl = new JLabel("Nuevo cliente");
		title_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		title_lbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		title_lbl.setBounds(10, 11, 706, 14);
		getContentPane().add(title_lbl);

		JLabel nombre_lbl = new JLabel("Nombre");
		nombre_lbl.setBounds(123, 52, 100, 14);
		getContentPane().add(nombre_lbl);

		nombre_txt = new JTextField();
		nombre_txt.setBounds(188, 49, 394, 20);
		getContentPane().add(nombre_txt);
		nombre_txt.setColumns(10);

		JLabel apellido_lbl = new JLabel("Apellido");
		apellido_lbl.setBounds(123, 80, 100, 14);
		getContentPane().add(apellido_lbl);

		apellido_txt = new JTextField();
		apellido_txt.setColumns(10);
		apellido_txt.setBounds(188, 77, 394, 20);
		getContentPane().add(apellido_txt);

		JLabel localidad_lbl = new JLabel("Localidad");
		localidad_lbl.setBounds(123, 139, 100, 14);
		getContentPane().add(localidad_lbl);

		localidad_txt = new JTextField();
		localidad_txt.setColumns(10);
		localidad_txt.setBounds(188, 136, 394, 20);
		getContentPane().add(localidad_txt);

		JLabel direccion_lbl = new JLabel("Direcci\u00F3n");
		direccion_lbl.setBounds(123, 167, 100, 14);
		getContentPane().add(direccion_lbl);

		direccion_txt = new JTextField();
		direccion_txt.setColumns(10);
		direccion_txt.setBounds(188, 164, 394, 20);
		getContentPane().add(direccion_txt);

		JLabel telefono_lbl = new JLabel("Tel\u00E9fono");
		telefono_lbl.setBounds(123, 193, 100, 14);
		getContentPane().add(telefono_lbl);

		telefono_txt = new JTextField();
		telefono_txt.setColumns(10);
		telefono_txt.setBounds(188, 190, 394, 20);
		getContentPane().add(telefono_txt);

		JLabel email_lbl = new JLabel("E-mail");
		email_lbl.setBounds(123, 218, 100, 14);
		getContentPane().add(email_lbl);

		email_txt = new JTextField();
		email_txt.setColumns(10);
		email_txt.setBounds(188, 215, 394, 20);
		getContentPane().add(email_txt);

		JLabel documento_lbl = new JLabel("Documento");
		documento_lbl.setBounds(123, 108, 100, 14);
		getContentPane().add(documento_lbl);

		documento_txt = new JTextField();
		documento_txt.setColumns(10);
		documento_txt.setBounds(188, 105, 394, 20);
		getContentPane().add(documento_txt);

		guardar_btn = new JButton("Ingresar Item");
		guardar_btn.setBounds(285, 382, 139, 23);
		getContentPane().add(guardar_btn);

		JScrollPane clientes_scrollPane = new JScrollPane();
		clientes_scrollPane.setBounds(123, 280, 459, 91);
		getContentPane().add(clientes_scrollPane);

		modelClientes = new DefaultTableModel(null, nombreColumnas);
		tablaClientes = new JTable(modelClientes);
		clientes_scrollPane.setViewportView(tablaClientes);

		eliminarItem_btn = new JButton("Eliminar Item");
		eliminarItem_btn.setBounds(123, 382, 139, 23);
		getContentPane().add(eliminarItem_btn);

		limpiar_btn = new JButton("Limpiar");
		limpiar_btn.setBounds(443, 382, 139, 23);
		getContentPane().add(limpiar_btn);

	}

	public JTextField getNombre_txt() {
		return nombre_txt;
	}

	public JTextField getApellido_txt() {
		return apellido_txt;
	}

	public JTextField getLocalidad_txt() {
		return localidad_txt;
	}

	public JTextField getDireccion_txt() {
		return direccion_txt;
	}

	public JTextField getTelefono_txt() {
		return telefono_txt;
	}

	public JTextField getEmail_txt() {
		return email_txt;
	}

	public JTextField getDocumento_txt() {
		return documento_txt;
	}

	public JTable getTablaClientes() {
		return tablaClientes;
	}

	public DefaultTableModel getModelClientes() {
		return modelClientes;
	}

	public JButton getGuardar_btn() {
		return guardar_btn;
	}

	public JButton getEliminarItem_btn() {
		return eliminarItem_btn;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JButton getLimpiar_btn() {
		return limpiar_btn;
	}

	public void setLimpiar_btn(JButton limpiar_btn) {
		this.limpiar_btn = limpiar_btn;
	}

}
