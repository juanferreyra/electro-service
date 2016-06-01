package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class VentanaABMProveedor extends JFrame{
	
	/**
	 * 
	 */
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
	private String[] nombreColumnas = { "id","Razon Social", "Nro Cuit", "Direccion", "Email",
			"Nombre Contacto", "Telefono Contacto", "Email Contacto", "Email Pedidos" };
	private JTable tablaMarcas;
	private String[] nombreColumnasMarcas={"id", "Detalle"};
	private JButton eliminarItem_btn;
	private JButton guardar_btn;
	private JButton limpiar_btn;
	private JButton eliminarMarca;
	private JComboBox<String> agregarMarca_jcmbox;
	
	
	public VentanaABMProveedor() {
		
		setBounds(100, 100, 887, 705);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel proveedor_lbl = new JLabel("Proveedores");
		proveedor_lbl.setBounds(213, 24, 122, 15);
		getContentPane().add(proveedor_lbl);
		
		JLabel RazonSocial_lbl = new JLabel("Razon Social");
		RazonSocial_lbl.setBounds(25, 61, 108, 15);
		getContentPane().add(RazonSocial_lbl);
		
		razonSocial_txt = new JTextField();
		razonSocial_txt.setBounds(151, 59, 271, 19);
		getContentPane().add(razonSocial_txt);
		razonSocial_txt.setColumns(10);
		
		JLabel lblNroCuit = new JLabel("Nro. Cuit");
		lblNroCuit.setBounds(25, 87, 108, 15);
		getContentPane().add(lblNroCuit);
		
		cuit_txt = new JTextField();
		cuit_txt.setColumns(10);
		cuit_txt.setBounds(151, 85, 271, 19);
		getContentPane().add(cuit_txt);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(25, 116, 108, 15);
		getContentPane().add(lblDireccion);
		
		direccion_txt = new JTextField();
		direccion_txt.setColumns(10);
		direccion_txt.setBounds(151, 114, 271, 19);
		getContentPane().add(direccion_txt);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(25, 145, 108, 15);
		getContentPane().add(lblEmail);
		
		email_txt = new JTextField();
		email_txt.setColumns(10);
		email_txt.setBounds(151, 143, 271, 19);
		getContentPane().add(email_txt);
		
		JLabel lblNombreContacto = new JLabel("Nombre Contacto");
		lblNombreContacto.setBounds(25, 172, 108, 15);
		getContentPane().add(lblNombreContacto);
		
		nombreContacto_txt = new JTextField();
		nombreContacto_txt.setColumns(10);
		nombreContacto_txt.setBounds(151, 170, 271, 19);
		getContentPane().add(nombreContacto_txt);
		
		JLabel lblTelefonoContacto = new JLabel("Telefono Contacto");
		lblTelefonoContacto.setBounds(25, 203, 108, 15);
		getContentPane().add(lblTelefonoContacto);
		
		telefonoContacto_txt = new JTextField();
		telefonoContacto_txt.setColumns(10);
		telefonoContacto_txt.setBounds(151, 201, 271, 19);
		getContentPane().add(telefonoContacto_txt);
		
		JLabel lblEmailContacto = new JLabel("Email Contacto");
		lblEmailContacto.setBounds(25, 228, 108, 15);
		getContentPane().add(lblEmailContacto);
		
		emailContacto_txt = new JTextField();
		emailContacto_txt.setColumns(10);
		emailContacto_txt.setBounds(151, 226, 271, 19);
		getContentPane().add(emailContacto_txt);
		
		JLabel lblEmailPedidos = new JLabel("Email Pedidos");
		lblEmailPedidos.setBounds(25, 257, 108, 15);
		getContentPane().add(lblEmailPedidos);
		
		emailPedidos_txt = new JTextField();
		emailPedidos_txt.setColumns(10);
		emailPedidos_txt.setBounds(151, 255, 271, 19);
		getContentPane().add(emailPedidos_txt);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 340, 800, 215);
		getContentPane().add(scrollPane);
		
		modelProveedores = new DefaultTableModel(null, nombreColumnas);
		tablaProveedores = new JTable(modelProveedores);
		scrollPane.setViewportView(tablaProveedores);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(469, 50, 185, 222);
		getContentPane().add(scrollPane_1);
		
		modelMarcas = new DefaultTableModel(null, nombreColumnasMarcas);
		tablaMarcas = new JTable(modelMarcas);
		scrollPane_1.setViewportView(tablaMarcas);
		
		eliminarItem_btn = new JButton("Eliminar Item");
		eliminarItem_btn.setBounds(469, 620, 117, 25);
		getContentPane().add(eliminarItem_btn);
		
		guardar_btn = new JButton("Guardar");
		guardar_btn.setBounds(598, 620, 117, 25);
		getContentPane().add(guardar_btn);
		
		limpiar_btn = new JButton("Limpiar");
		limpiar_btn.setBounds(734, 620, 117, 25);
		getContentPane().add(limpiar_btn);
		
		agregarMarca_jcmbox = new JComboBox<String>();
		agregarMarca_jcmbox.setBounds(689, 61, 162, 24);
		getContentPane().add(agregarMarca_jcmbox);
		
		eliminarMarca = new JButton("Eliminar Marca");
		eliminarMarca.setBounds(689, 223, 162, 25);
		getContentPane().add(eliminarMarca);
		
		JLabel lblMarcas = new JLabel("Marcas");
		lblMarcas.setBounds(532, 24, 122, 15);
		getContentPane().add(lblMarcas);
		
		JLabel lblAgregarMarca = new JLabel("Agregar Marca");
		lblAgregarMarca.setBounds(716, 40, 122, 15);
		getContentPane().add(lblAgregarMarca);
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
	
}
