package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.BorderLayout;
import java.awt.Color;
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

public class VentanaIngreso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField numeroCliente_txf;
	private JTable table;
	private JTextField nombreProducto_txf;
	private JTextArea descripcionFalla_txtArea;
	private JTextField direccionAlternativa_txf;
	private JButton buscarCliente_btn;
	private JComboBox<String> marca_ComboBox;
	private JComboBox<String> tipo_ComboBox ;
	private JCheckBox envioDomicilio_checkBox;
	private JCheckBox direccionDefecto_checkBox;
	private JButton cancelar_btn;
	private JButton aceptar_btn;
	private JTextField txtMontoEnvio;
	
	public VentanaIngreso() {
		
		super();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel fechaIngreso_lbl = new JLabel("Fecha");
		fechaIngreso_lbl.setBounds(515, 11, 66, 14);
		contentPane.add(fechaIngreso_lbl);

		JLabel fechaIngresoText_lbl = new JLabel("");
		fechaIngresoText_lbl.setBounds(599, 11, 75, 14);
		contentPane.add(fechaIngresoText_lbl);

		JLabel titulo_lbl = new JLabel("<html><b>INGRESO</b></html>");
		titulo_lbl.setBounds(10, 11, 495, 14);
		contentPane.add(titulo_lbl);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLUE);
		separator.setBounds(10, 34, 664, 7);
		contentPane.add(separator);

		JLabel numeroCliente_lbl = new JLabel("N\u00FAmero Cliente:");
		numeroCliente_lbl.setHorizontalAlignment(SwingConstants.RIGHT);
		numeroCliente_lbl.setBounds(10, 50, 89, 20);
		contentPane.add(numeroCliente_lbl);

		numeroCliente_txf = new JTextField();
		numeroCliente_txf.setBounds(100, 50, 146, 20);
		contentPane.add(numeroCliente_txf);
		numeroCliente_txf.setColumns(10);

		buscarCliente_btn = new JButton("");
		buscarCliente_btn
				.setIcon(new ImageIcon(VentanaIngreso.class.getResource("/search.png")));
		buscarCliente_btn.setBounds(270, 50, 33, 20);
		contentPane.add(buscarCliente_btn);

		JButton crearCliente_btn = new JButton("");
		crearCliente_btn
				.setIcon(new ImageIcon(VentanaIngreso.class.getResource("/user-add.png")));
		crearCliente_btn.setBounds(313, 50, 33, 20);
		contentPane.add(crearCliente_btn);

		String[] nombreColumnas = { "Nombre", "Apellido", "Direcciï¿½n", "Email", "Telï¿½fono" };
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
		nombreProducto_lbl.setHorizontalAlignment(SwingConstants.RIGHT);
		nombreProducto_lbl.setBounds(19, 151, 101, 20);
		contentPane.add(nombreProducto_lbl);

		nombreProducto_txf = new JTextField();
		nombreProducto_txf.setColumns(10);
		nombreProducto_txf.setBounds(130, 151, 351, 20);
		contentPane.add(nombreProducto_txf);

		JLabel marca_lbl = new JLabel("Marca:");
		marca_lbl.setHorizontalAlignment(SwingConstants.RIGHT);
		marca_lbl.setBounds(10, 185, 56, 20);
		contentPane.add(marca_lbl);

		marca_ComboBox = new JComboBox<String>();
		marca_ComboBox.setBounds(70, 185, 150, 20);
		contentPane.add(marca_ComboBox);

		JLabel tipo_lbl = new JLabel("Tipo:");
		tipo_lbl.setHorizontalAlignment(SwingConstants.RIGHT);
		tipo_lbl.setBounds(244, 185, 82, 20);
		contentPane.add(tipo_lbl);

		tipo_ComboBox = new JComboBox<String>();
		tipo_ComboBox.setBounds(331, 185, 150, 20);
		contentPane.add(tipo_ComboBox);

		JLabel descripcionFalla_lbl = new JLabel("Descripci\u00F3n falla:");
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
		descripcionFalla_jScrollPane.setViewportView(descripcionFalla_txtArea);

		contentPane.add(descripcionFalla_jScrollPane);
		
		cancelar_btn = new JButton("Cancelar");
		cancelar_btn.setBounds(585, 428, 89, 23);
		contentPane.add(cancelar_btn);
		
		aceptar_btn = new JButton("Aceptar");
		aceptar_btn.setBounds(486, 428, 89, 23);
		contentPane.add(aceptar_btn);
		
		JLabel envioAdomicilio_lbl = new JLabel("Solicita Env\u00EDo");
		envioAdomicilio_lbl.setBounds(30, 322, 176, 20);
		contentPane.add(envioAdomicilio_lbl);
		
		envioDomicilio_checkBox = new JCheckBox("");
		envioDomicilio_checkBox.setBackground(Color.WHITE);
		envioDomicilio_checkBox.setBounds(10, 322, 20, 20);
		contentPane.add(envioDomicilio_checkBox);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 353, 310, 98);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel direccinAlternativa_lbl = new JLabel("Direcci\u00F3n alternativa:");
		direccinAlternativa_lbl.setEnabled(false);
		direccinAlternativa_lbl.setBounds(10, 22, 250, 20);
		panel.add(direccinAlternativa_lbl);
		
		direccionAlternativa_txf = new JTextField();
		direccionAlternativa_txf.setBounds(10, 43, 293, 20);
		panel.add(direccionAlternativa_txf);
		direccionAlternativa_txf.setColumns(10);
		
		JLabel lblMontoEnvio = new JLabel("Monto Envio:");
		lblMontoEnvio.setEnabled(false);
		lblMontoEnvio.setBounds(10, 67, 63, 20);
		panel.add(lblMontoEnvio);
		
		txtMontoEnvio = new JTextField();
		txtMontoEnvio.setBounds(74, 68, 159, 20);
		panel.add(txtMontoEnvio);
		txtMontoEnvio.setColumns(10);
		
		JLabel label = new JLabel("$");
		label.setEnabled(false);
		label.setBounds(240, 68, 20, 20);
		panel.add(label);
		
		JLabel direccionDefecto_lbl = new JLabel("Usar direcci\u00F3n del cliente ");
		direccionDefecto_lbl.setBounds(32, 2, 250, 20);
		panel.add(direccionDefecto_lbl);
		
		direccionDefecto_checkBox = new JCheckBox("");
		direccionDefecto_checkBox.setBounds(8, 2, 20, 20);
		panel.add(direccionDefecto_checkBox);
		direccionDefecto_checkBox.setBackground(Color.WHITE);

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
	
	public JCheckBox getDireccion_default() {
		return direccionDefecto_checkBox;
	}
	
	public JTextField getTxtDireccionAlternativa() {
		return direccionAlternativa_txf;
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

	public void vaciarTodo() {
		numeroCliente_txf.setText("");
		nombreProducto_txf.setText("");
		descripcionFalla_txtArea.setText("");
		envioDomicilio_checkBox.setSelected(false);
		direccionDefecto_checkBox.setSelected(false);
		direccionAlternativa_txf.setText("");
		String[] nombreColumnas = { "Nombre", "Apellido", "Dirección", "Email", "Teléfono" };
		Object[][] informacionTabla = {};
		table.setModel(new DefaultTableModel(informacionTabla, nombreColumnas));
		txtMontoEnvio.setText("");
	}	
}