package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

public class VentanaABMProveedor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField razonSocial_txt;
	private JTextField cuit_txt;
	private JTextField direccion_txt;
	private JTextField email_txt;
	private JTextField nombreContacto_txt;
	private JTextField telefonoContacto_txt;
	private JTextField emailContacto_txt;
	private JTextField emailPedidos_txt;
	private JTable tablaProveedores;
	private DefaultTableModel modelProveedores;
	private DefaultTableModel modelMarcas;
	private String[] nombreColumnas = { "Proveedor", "Razon Social", "CUIT", "Direcci\u00f3n", "Email", "Nombre",
			"Tel\u00e9fono", "Email Contacto", "Email Pedidos" };
	private JTable tablaMarcas;
	private String[] nombreColumnasMarcas = { "id", "Detalle" };
	private JButton eliminarItem_btn;
	private JButton guardar_btn;
	private JButton limpiar_btn;
	private JButton eliminarMarca;
	private JComboBox<String> agregarMarca_jcmbox;
	private JButton agregarMarca;
	private JButton btnMarcaNueva;

	public VentanaABMProveedor() {

		setBounds(100, 100, 887, 608);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPresentacion.class.getResource("/logo.png")));

		JLabel proveedor_lbl = new JLabel("Proveedores");
		proveedor_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		proveedor_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		proveedor_lbl.setBounds(10, 27, 851, 15);
		getContentPane().add(proveedor_lbl);

		JLabel RazonSocial_lbl = new JLabel("Razon Social");
		RazonSocial_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RazonSocial_lbl.setBounds(10, 94, 177, 15);
		getContentPane().add(RazonSocial_lbl);

		razonSocial_txt = new JTextField();
		razonSocial_txt.setBounds(128, 94, 334, 19);
		getContentPane().add(razonSocial_txt);
		razonSocial_txt.setColumns(10);

		JLabel lblNroCuit = new JLabel("CUIT");
		lblNroCuit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNroCuit.setBounds(10, 123, 160, 15);
		getContentPane().add(lblNroCuit);

		cuit_txt = new JTextField();
		cuit_txt.setColumns(10);
		cuit_txt.setBounds(128, 123, 334, 19);
		getContentPane().add(cuit_txt);

		JLabel lblDireccion = new JLabel("Direcci\u00F3n");
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDireccion.setBounds(10, 149, 108, 15);
		getContentPane().add(lblDireccion);

		direccion_txt = new JTextField();
		direccion_txt.setColumns(10);
		direccion_txt.setBounds(128, 149, 334, 19);
		getContentPane().add(direccion_txt);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(10, 179, 108, 15);
		getContentPane().add(lblEmail);

		email_txt = new JTextField();
		email_txt.setColumns(10);
		email_txt.setBounds(128, 179, 334, 19);
		getContentPane().add(email_txt);

		JLabel lblNombreContacto = new JLabel("Nombre");
		lblNombreContacto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombreContacto.setBounds(10, 205, 160, 15);
		getContentPane().add(lblNombreContacto);

		nombreContacto_txt = new JTextField();
		nombreContacto_txt.setColumns(10);
		nombreContacto_txt.setBounds(128, 205, 334, 19);
		getContentPane().add(nombreContacto_txt);

		JLabel lblTelefonoContacto = new JLabel("Tel\u00E9fono");
		lblTelefonoContacto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefonoContacto.setBounds(10, 235, 108, 15);
		getContentPane().add(lblTelefonoContacto);

		telefonoContacto_txt = new JTextField();
		telefonoContacto_txt.setColumns(10);
		telefonoContacto_txt.setBounds(128, 235, 334, 19);
		getContentPane().add(telefonoContacto_txt);

		JLabel lblEmailContacto = new JLabel("Email Contacto");
		lblEmailContacto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmailContacto.setBounds(10, 261, 160, 15);
		getContentPane().add(lblEmailContacto);

		emailContacto_txt = new JTextField();
		emailContacto_txt.setColumns(10);
		emailContacto_txt.setBounds(128, 261, 334, 19);
		getContentPane().add(emailContacto_txt);

		JLabel lblEmailPedidos = new JLabel("Email Pedidos");
		lblEmailPedidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmailPedidos.setBounds(10, 290, 200, 15);
		getContentPane().add(lblEmailPedidos);

		emailPedidos_txt = new JTextField();
		emailPedidos_txt.setColumns(10);
		emailPedidos_txt.setBounds(128, 290, 334, 19);
		getContentPane().add(emailPedidos_txt);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 337, 851, 173);
		getContentPane().add(scrollPane);

		modelProveedores = new DefaultTableModel(null, nombreColumnas) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int fila, int columna) {

				return false;
			}
		};
		tablaProveedores = new JTable(modelProveedores);
		scrollPane.setViewportView(tablaProveedores);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(472, 154, 389, 151);
		getContentPane().add(scrollPane_1);

		modelMarcas = new DefaultTableModel(null, nombreColumnasMarcas) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int fila, int columna) {

				return false;
			}
		};
		tablaMarcas = new JTable(modelMarcas);
		scrollPane_1.setViewportView(tablaMarcas);

		eliminarItem_btn = new JButton("Eliminar");
		eliminarItem_btn.setBounds(244, 535, 117, 25);
		getContentPane().add(eliminarItem_btn);

		guardar_btn = new JButton("Guardar");
		guardar_btn.setBounds(371, 535, 117, 25);
		getContentPane().add(guardar_btn);

		limpiar_btn = new JButton("Limpiar");
		limpiar_btn.setBounds(495, 535, 117, 25);
		getContentPane().add(limpiar_btn);

		agregarMarca_jcmbox = new JComboBox<String>();
		agregarMarca_jcmbox.setBounds(521, 123, 160, 22);
		getContentPane().add(agregarMarca_jcmbox);

		eliminarMarca = new JButton("Quitar");
		eliminarMarca.setBounds(775, 120, 86, 25);
		getContentPane().add(eliminarMarca);

		JLabel lblMarcas = new JLabel("Marcas asociadas");
		lblMarcas.setHorizontalAlignment(SwingConstants.CENTER);
		lblMarcas.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblMarcas.setBounds(472, 94, 389, 15);
		getContentPane().add(lblMarcas);

		agregarMarca = new JButton("Agregar");
		agregarMarca.setBounds(691, 120, 74, 25);
		getContentPane().add(agregarMarca);

		btnMarcaNueva = new JButton("*");
		btnMarcaNueva.setBounds(472, 123, 39, 22);
		getContentPane().add(btnMarcaNueva);
	}

	public JTextField getRazonSocial_txt() {
		return razonSocial_txt;
	}

	public JTextField getCuit_txt() {
		return cuit_txt;
	}

	public JTextField getDireccion_txt() {
		return direccion_txt;
	}

	public JTextField getEmail_txt() {
		return email_txt;
	}

	public JTextField getNombreContacto_txt() {
		return nombreContacto_txt;
	}

	public JTextField getTelefonoContacto_txt() {
		return telefonoContacto_txt;
	}

	public JTextField getEmailContacto_txt() {
		return emailContacto_txt;
	}

	public JTextField getEmailPedidos_txt() {
		return emailPedidos_txt;
	}

	public DefaultTableModel getModelProveedores() {
		return modelProveedores;
	}

	public DefaultTableModel getModelMarcas() {
		return modelMarcas;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JTable getTablaMarcas() {
		return tablaMarcas;
	}

	public String[] getNombreColumnasMarcas() {
		return nombreColumnasMarcas;
	}

	public JButton getEliminarItem_btn() {
		return eliminarItem_btn;
	}

	public JButton getGuardar_btn() {
		return guardar_btn;
	}

	public JButton getLimpiar_btn() {
		return limpiar_btn;
	}

	public JTable getTablaProveedores() {
		return tablaProveedores;
	}

	public JButton getEliminarMarca() {
		return eliminarMarca;
	}

	public JComboBox<String> getAgregarMarca_jcmbox() {
		return agregarMarca_jcmbox;
	}

	public JButton getAgregarMarca() {
		return agregarMarca;
	}

	public JButton getBtnMarcaNueva() {
		return btnMarcaNueva;
	}
}
