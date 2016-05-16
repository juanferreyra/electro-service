package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import modelo.JTextFieldLimit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.border.MatteBorder;

public class VentanaIngreso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField numeroCliente_txf;
	private JTable table;
	private JTextField nombreProducto_txf;
	private JTextArea descripcionFalla_txtArea;
	private JTextField direccionNueva_txf;
	private JButton buscarCliente_btn;
	private JComboBox<String> marca_ComboBox;
	private JComboBox<String> tipo_ComboBox;
	private JCheckBox envioDomicilio_checkBox;
	private JCheckBox direccionNueva_checkBox;
	private JButton cancelar_btn;
	private JButton aceptar_btn;
	private JTextField txtMontoEnvio;
	private JButton btnVerIngreso;
	private JPanel direccionNueva_JPanel;
	private JLabel direccinNueva_lbl;
	private JLabel otraDireccion_lbl;

	public VentanaIngreso() {

		super();
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaIngreso.class.getResource("/edit.png")));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel fechaIngreso_lbl = new JLabel("Fecha");
		fechaIngreso_lbl.setBounds(515, 11, 66, 14);
		contentPane.add(fechaIngreso_lbl);

		JLabel fechaIngresoText_lbl = new JLabel("");
		fechaIngresoText_lbl.setBounds(599, 11, 75, 14);
		contentPane.add(fechaIngresoText_lbl);

		JLabel titulo_lbl = new JLabel("<html><b><i>INGRESO DE PRODUCTO</i></b></html>");
		titulo_lbl.setBounds(10, 11, 495, 14);
		contentPane.add(titulo_lbl);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLUE);
		separator.setBounds(10, 34, 664, 7);
		contentPane.add(separator);

		JLabel numeroCliente_lbl = new JLabel("N\u00FAmero Cliente:");
		numeroCliente_lbl.setHorizontalAlignment(SwingConstants.LEFT);
		numeroCliente_lbl.setBounds(10, 52, 205, 20);
		contentPane.add(numeroCliente_lbl);

		numeroCliente_txf = new JTextField();

		numeroCliente_txf.setBounds(124, 52, 233, 20);
		contentPane.add(numeroCliente_txf);
		numeroCliente_txf.setColumns(10);
		numeroCliente_txf.setDocument(new JTextFieldLimit(30));

		buscarCliente_btn = new JButton("");
		buscarCliente_btn.setIcon(new ImageIcon(VentanaIngreso.class.getResource("/search.png")));
		buscarCliente_btn.setBounds(367, 52, 33, 20);
		contentPane.add(buscarCliente_btn);

		JButton crearCliente_btn = new JButton("");
		crearCliente_btn.setIcon(new ImageIcon(VentanaIngreso.class.getResource("/user-add.png")));
		crearCliente_btn.setBounds(410, 52, 33, 20);
		contentPane.add(crearCliente_btn);

		String[] nombreColumnas = { "Nombre", "Apellido", "Direccion", "Email", "Telefono" };
		Object[][] informacionTabla = {};

		table = new JTable(informacionTabla, nombreColumnas);
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setBorder(new LineBorder(Color.LIGHT_GRAY));
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JPanel panelTablaDatosCliente = new JPanel();
		panelTablaDatosCliente.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panelTablaDatosCliente.setBounds(10, 80, 664, 47);

		JTableHeader header = table.getTableHeader();

		panelTablaDatosCliente.setLayout(new BorderLayout(0, 0));
		panelTablaDatosCliente.add(header, BorderLayout.NORTH);
		panelTablaDatosCliente.add(table, BorderLayout.CENTER);

		contentPane.add(panelTablaDatosCliente);

		JLabel nombreProducto_lbl = new JLabel("Nombre Producto:");
		nombreProducto_lbl.setHorizontalAlignment(SwingConstants.LEFT);
		nombreProducto_lbl.setBounds(10, 151, 260, 20);
		contentPane.add(nombreProducto_lbl);

		nombreProducto_txf = new JTextField();
		nombreProducto_txf.setColumns(10);
		nombreProducto_txf.setBounds(141, 151, 533, 20);
		nombreProducto_txf.setDocument(new JTextFieldLimit(150));
		contentPane.add(nombreProducto_txf);

		JLabel marca_lbl = new JLabel("Marca:");
		marca_lbl.setHorizontalAlignment(SwingConstants.LEFT);
		marca_lbl.setBounds(10, 182, 127, 20);
		contentPane.add(marca_lbl);

		marca_ComboBox = new JComboBox<String>();
		marca_ComboBox.setBounds(141, 185, 191, 20);
		contentPane.add(marca_ComboBox);

		JLabel tipo_lbl = new JLabel("Tipo:");
		tipo_lbl.setHorizontalAlignment(SwingConstants.LEFT);
		tipo_lbl.setBounds(396, 182, 150, 20);
		contentPane.add(tipo_lbl);

		tipo_ComboBox = new JComboBox<String>();
		tipo_ComboBox.setBounds(483, 185, 191, 20);
		contentPane.add(tipo_ComboBox);

		JLabel descripcionFalla_lbl = new JLabel("Descripcion falla:");
		descripcionFalla_lbl.setBounds(10, 227, 146, 20);
		contentPane.add(descripcionFalla_lbl);

		JScrollPane descripcionFalla_jScrollPane = new javax.swing.JScrollPane();
		descripcionFalla_jScrollPane.setBounds(10, 252, 664, 63);
		descripcionFalla_jScrollPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
		descripcionFalla_jScrollPane
				.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		descripcionFalla_jScrollPane
				.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		descripcionFalla_txtArea = new JTextArea();
		descripcionFalla_txtArea.setRows(5);
		descripcionFalla_txtArea.setColumns(20);
		descripcionFalla_txtArea.setDocument(new JTextFieldLimit(400));
		descripcionFalla_jScrollPane.setViewportView(descripcionFalla_txtArea);

		contentPane.add(descripcionFalla_jScrollPane);

		cancelar_btn = new JButton("Cancelar");
		cancelar_btn.setBounds(585, 428, 89, 23);
		contentPane.add(cancelar_btn);

		aceptar_btn = new JButton("Aceptar");
		aceptar_btn.setBounds(486, 428, 89, 23);
		contentPane.add(aceptar_btn);

		JLabel envioAdomicilio_lbl = new JLabel("Solicita Envio");
		envioAdomicilio_lbl.setBounds(36, 342, 101, 14);
		contentPane.add(envioAdomicilio_lbl);

		envioDomicilio_checkBox = new JCheckBox("");
		envioDomicilio_checkBox.setBackground(Color.WHITE);
		envioDomicilio_checkBox.setBounds(10, 342, 20, 14);
		contentPane.add(envioDomicilio_checkBox);

		direccionNueva_JPanel = new JPanel();
		direccionNueva_JPanel.setForeground(Color.DARK_GRAY);
		direccionNueva_JPanel.setBorder(new MatteBorder(2, 1, 2, 1, (Color) new Color(191, 205, 219)));
		direccionNueva_JPanel.setBounds(10, 363, 310, 88);
		contentPane.add(direccionNueva_JPanel);
		direccionNueva_JPanel.setLayout(null);

		JLabel direccinNueva_lbl = new JLabel("Direcci\u00F3n nueva:");
		direccinNueva_lbl.setForeground(Color.DARK_GRAY);
		direccinNueva_lbl.setEnabled(false);
		direccinNueva_lbl.setBounds(10, 11, 250, 20);
		direccionNueva_JPanel.add(direccinNueva_lbl);

		direccionNueva_txf = new JTextField();
		direccionNueva_txf.setBounds(10, 30, 293, 20);
		direccionNueva_txf.setDocument(new JTextFieldLimit(60));
		direccionNueva_JPanel.add(direccionNueva_txf);
		direccionNueva_txf.setColumns(10);

		JLabel lblMontoEnvio = new JLabel("Monto de envio:");
		lblMontoEnvio.setForeground(Color.DARK_GRAY);
		lblMontoEnvio.setEnabled(false);
		lblMontoEnvio.setBounds(10, 61, 120, 20);
		direccionNueva_JPanel.add(lblMontoEnvio);

		txtMontoEnvio = new JTextField();
		txtMontoEnvio.setBounds(101, 61, 159, 20);
		txtMontoEnvio.setDocument(new JTextFieldLimit(15));
		direccionNueva_JPanel.add(txtMontoEnvio);
		txtMontoEnvio.setColumns(10);

		JLabel label = new JLabel("$");
		label.setEnabled(false);
		label.setBounds(268, 61, 20, 20);
		direccionNueva_JPanel.add(label);

		btnVerIngreso = new JButton("Imprimir");
		btnVerIngreso.setIcon(new ImageIcon(VentanaIngreso.class.getResource("/printer.png")));
		btnVerIngreso.setBounds(348, 428, 99, 23);
		contentPane.add(btnVerIngreso);

		otraDireccion_lbl = new JLabel("No usar la direccion del cliente");
		otraDireccion_lbl.setBounds(147, 342, 250, 14);
		contentPane.add(otraDireccion_lbl);

		direccionNueva_checkBox = new JCheckBox("");
		direccionNueva_checkBox.setBounds(124, 342, 20, 14);
		contentPane.add(direccionNueva_checkBox);
		direccionNueva_checkBox.setBackground(Color.WHITE);

	}

	public JButton getBtnBuscarCliente() {
		return buscarCliente_btn;
	}

	public JTextField getTxtNroCliente() {
		return numeroCliente_txf;
	}

	public JComboBox<String> getComboMarcas() {
		return marca_ComboBox;
	}

	public JComboBox<String> getComboTiposProductos() {
		return tipo_ComboBox;
	}

	public JTextField getTextNombreProducto() {
		return nombreProducto_txf;
	}

	public JTextArea getTextDescripcionFalla() {
		return descripcionFalla_txtArea;
	}

	public JCheckBox getEnvioDomicilio() {
		return envioDomicilio_checkBox;
	}

	public JCheckBox getDireccion_nueva() {
		return direccionNueva_checkBox;
	}

	public JTextField getTxtDireccionNueva() {
		return direccionNueva_txf;
	}

	public void setTxtDireccionNueva(String valor) {
		direccionNueva_txf.setText(valor);
	}

	public JButton getBtnAceptar() {
		return aceptar_btn;
	}

	public JButton getBtnCancelar() {
		return cancelar_btn;
	}

	public JTable getClienteTable() {
		return this.table;
	}

	public JTextField getMontoEnvio() {
		return txtMontoEnvio;
	}

	public void setMontoEnvio(String valor) {
		txtMontoEnvio.setText(valor);
	}

	public void vaciarTodo() {
		numeroCliente_txf.setText("");
		nombreProducto_txf.setText("");
		descripcionFalla_txtArea.setText("");
		envioDomicilio_checkBox.setSelected(false);
		direccionNueva_checkBox.setSelected(false);
		direccionNueva_txf.setText("");
		String[] nombreColumnas = { "Nombre", "Apellido", "Dirección", "Email", "Teléfono" };
		Object[][] informacionTabla = {};
		table.setModel(new DefaultTableModel(informacionTabla, nombreColumnas));
		txtMontoEnvio.setText("");
	}

	public JButton getBtnReporteDeIngreso() {
		return btnVerIngreso;
	}

	public Component getNumeroCliente_txf() {
		return this.numeroCliente_txf;
	}

	public JPanel getDireccionNueva_JPanel() {
		return this.direccionNueva_JPanel;
	}

	public JLabel getOtraDireccionLabel() {
		return this.otraDireccion_lbl;
	}
}