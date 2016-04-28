package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
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

public class VentanaIngreso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField numeroCliente_txf;
	private JTable table;
	private JTextField nombreProducto_txf;
	private JTextField direccionAlternativa_txf;

	public VentanaIngreso() {
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

		JLabel numeroCliente_lbl = new JLabel("N\u00FAmero Cliente");
		numeroCliente_lbl.setBounds(10, 52, 120, 14);
		contentPane.add(numeroCliente_lbl);

		numeroCliente_txf = new JTextField();
		numeroCliente_txf.setBounds(140, 49, 120, 20);
		contentPane.add(numeroCliente_txf);
		numeroCliente_txf.setColumns(10);

		JButton buscarCliente_btn = new JButton("");
		buscarCliente_btn
				.setIcon(new ImageIcon("C:\\workspaceLaboratorioCS\\electro-service\\recursos\\icons\\search.png"));
		buscarCliente_btn.setBounds(270, 48, 33, 23);
		contentPane.add(buscarCliente_btn);

		JButton crearCliente_btn = new JButton("");
		crearCliente_btn
				.setIcon(new ImageIcon("C:\\workspaceLaboratorioCS\\electro-service\\recursos\\icons\\user-add.png"));
		crearCliente_btn.setBounds(313, 48, 33, 23);
		contentPane.add(crearCliente_btn);

		String[] nombreColumnas = { "Nombre", "Apellido", "Dirección", "Email", "Teléfono" };
		Object[][] informacionTabla = {};

		table = new JTable(informacionTabla, nombreColumnas);
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setBorder(new LineBorder(Color.LIGHT_GRAY));
		table.setFont(new Font("Tahoma", Font.PLAIN, 10));

		JPanel panelTablaDatosCliente = new JPanel();
		panelTablaDatosCliente.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panelTablaDatosCliente.setBounds(10, 80, 664, 41);

		JTableHeader header = table.getTableHeader();

		panelTablaDatosCliente.setLayout(new BorderLayout(0, 0));
		panelTablaDatosCliente.add(header, BorderLayout.NORTH);
		panelTablaDatosCliente.add(table, BorderLayout.CENTER);

		contentPane.add(panelTablaDatosCliente);

		JLabel nombreProducto_lbl = new JLabel("Nombre Producto");
		nombreProducto_lbl.setBounds(10, 154, 120, 14);
		contentPane.add(nombreProducto_lbl);

		nombreProducto_txf = new JTextField();
		nombreProducto_txf.setColumns(10);
		nombreProducto_txf.setBounds(140, 151, 293, 20);
		contentPane.add(nombreProducto_txf);

		JLabel marca_lbl = new JLabel("Marca");
		marca_lbl.setBounds(10, 191, 120, 14);
		contentPane.add(marca_lbl);

		JComboBox marca_ComboBox = new JComboBox();
		marca_ComboBox.setBounds(140, 188, 120, 20);
		contentPane.add(marca_ComboBox);

		JLabel tipo_lbl = new JLabel("Tipo");
		tipo_lbl.setBounds(270, 191, 120, 14);
		contentPane.add(tipo_lbl);

		JComboBox tipo_ComboBox = new JComboBox();
		tipo_ComboBox.setBounds(313, 188, 120, 20);
		contentPane.add(tipo_ComboBox);

		JScrollPane descripcionFalla_jScrollPane = new javax.swing.JScrollPane();
		descripcionFalla_jScrollPane.setBounds(10, 220, 664, 63);
		descripcionFalla_jScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Descripción de Falla", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 0)));
		descripcionFalla_jScrollPane
				.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		descripcionFalla_jScrollPane
				.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JTextArea descripcionFalla_txtArea = new JTextArea();
		descripcionFalla_txtArea.setRows(5);
		descripcionFalla_txtArea.setColumns(20);
		descripcionFalla_jScrollPane.setViewportView(descripcionFalla_txtArea);

		contentPane.add(descripcionFalla_jScrollPane);
		
		JButton cancelar_btn = new JButton("Cancelar");
		cancelar_btn.setBounds(585, 428, 89, 23);
		contentPane.add(cancelar_btn);
		
		JButton aceptar_btn = new JButton("Aceptar");
		aceptar_btn.setBounds(486, 428, 89, 23);
		contentPane.add(aceptar_btn);
		
		JLabel envioAdomicilio_lbl = new JLabel("Env\u00EDo a domicilio");
		envioAdomicilio_lbl.setBounds(10, 326, 181, 14);
		contentPane.add(envioAdomicilio_lbl);
		
		JCheckBox envioDomicilio_checkBox = new JCheckBox("");
		envioDomicilio_checkBox.setBackground(Color.WHITE);
		envioDomicilio_checkBox.setBounds(132, 326, 21, 14);
		contentPane.add(envioDomicilio_checkBox);
		
		JLabel direccionDefecto_lbl = new JLabel("Usar direcci\u00F3n del cliente ");
		direccionDefecto_lbl.setBounds(10, 351, 163, 14);
		contentPane.add(direccionDefecto_lbl);
		
		JCheckBox direccionDefecto_checkBox = new JCheckBox("");
		direccionDefecto_checkBox.setBackground(Color.WHITE);
		direccionDefecto_checkBox.setBounds(132, 351, 21, 14);
		contentPane.add(direccionDefecto_checkBox);
		
		JLabel direccinAlternativa_lbl = new JLabel("Direcci\u00F3n alternativa");
		direccinAlternativa_lbl.setBounds(10, 376, 120, 14);
		contentPane.add(direccinAlternativa_lbl);
		
		direccionAlternativa_txf = new JTextField();
		direccionAlternativa_txf.setBounds(10, 401, 293, 20);
		contentPane.add(direccionAlternativa_txf);
		direccionAlternativa_txf.setColumns(10);

	}
}
