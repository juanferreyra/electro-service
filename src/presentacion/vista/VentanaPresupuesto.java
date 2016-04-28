package presentacion.vista;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import java.awt.SystemColor;

import javax.swing.ScrollPaneConstants;

public class VentanaPresupuesto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nroTecnico_txf;
	private JTextField horasDeTrabajo_txf;
	private JTextField manoDeObra_txf;

	public VentanaPresupuesto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 628);
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

		JLabel titulo_lbl = new JLabel("<html><b>PRESUPUESTO</b></html>");
		titulo_lbl.setBounds(10, 11, 495, 14);
		contentPane.add(titulo_lbl);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 34, 664, 7);
		separator.setForeground(Color.BLUE);
		contentPane.add(separator);

		JLabel nombreProducto_lbl = new JLabel("<html><i>Nombre Producto:</i></html>");
		nombreProducto_lbl.setBounds(10, 52, 116, 14);
		contentPane.add(nombreProducto_lbl);

		JLabel nombreProductoTexto_lbl = new JLabel("");
		nombreProductoTexto_lbl.setBounds(136, 52, 445, 14);
		contentPane.add(nombreProductoTexto_lbl);

		JLabel marca_lbl = new JLabel("<html><i>Marca:</i></html>");
		marca_lbl.setBounds(10, 78, 116, 14);
		contentPane.add(marca_lbl);

		JLabel marcaTexto_lbl = new JLabel("");
		marcaTexto_lbl.setBounds(71, 78, 184, 14);
		contentPane.add(marcaTexto_lbl);

		JLabel tipo_lbl = new JLabel("<html><i>Tipo:</i></html>");
		tipo_lbl.setBounds(303, 78, 116, 14);
		contentPane.add(tipo_lbl);

		JLabel tipoTexto_lbl = new JLabel("");
		tipoTexto_lbl.setBounds(362, 78, 184, 14);
		contentPane.add(tipoTexto_lbl);

		JLabel descripcionFalla_lbl = new JLabel("<html><i>Descripci\u00F3n falla:</i></html>");
		descripcionFalla_lbl.setBounds(10, 103, 146, 14);
		contentPane.add(descripcionFalla_lbl);

		JScrollPane descripcionFalla_jScrollPane = new javax.swing.JScrollPane();
		descripcionFalla_jScrollPane.setBounds(10, 128, 664, 46);
		descripcionFalla_jScrollPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
		descripcionFalla_jScrollPane
				.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		descripcionFalla_jScrollPane
				.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JTextArea descripcionFalla_txtArea = new JTextArea();
		descripcionFalla_txtArea.setEditable(false);
		descripcionFalla_txtArea.setRows(5);
		descripcionFalla_txtArea.setColumns(20);
		descripcionFalla_jScrollPane.setViewportView(descripcionFalla_txtArea);

		contentPane.add(descripcionFalla_jScrollPane);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 184, 664, 7);
		separator_2.setForeground(Color.BLUE);
		contentPane.add(separator_2);

		JLabel lblNroTcnico = new JLabel("Nro. T\u00E9cnico:");
		lblNroTcnico.setBounds(10, 197, 116, 14);
		contentPane.add(lblNroTcnico);

		nroTecnico_txf = new JTextField();
		nroTecnico_txf.setBounds(87, 194, 146, 20);
		contentPane.add(nroTecnico_txf);
		nroTecnico_txf.setColumns(10);

		JButton buscarTecnico_btn = new JButton("");
		buscarTecnico_btn.setBounds(243, 194, 33, 23);
		buscarTecnico_btn
				.setIcon(new ImageIcon("C:\\workspaceLaboratorioCS\\electro-service\\recursos\\icons\\search.png"));
		contentPane.add(buscarTecnico_btn);

		String[] datosClientes_nombreColumnas = { "Nombre", "Apellido" };
		Object[][] datosClientes_informacionTabla = {};

		JTable datosClientes_table = new JTable(datosClientes_informacionTabla, datosClientes_nombreColumnas);
		datosClientes_table.setEnabled(false);
		datosClientes_table.setRowSelectionAllowed(false);
		datosClientes_table.setBorder(new LineBorder(Color.LIGHT_GRAY));
		datosClientes_table.setFont(new Font("Tahoma", Font.PLAIN, 10));

		JPanel panelTablaDatosCliente = new JPanel();
		panelTablaDatosCliente.setBounds(10, 222, 664, 41);
		panelTablaDatosCliente.setBorder(new LineBorder(Color.LIGHT_GRAY));

		JTableHeader datosClientes_header = datosClientes_table.getTableHeader();

		panelTablaDatosCliente.setLayout(new BorderLayout(0, 0));
		panelTablaDatosCliente.add(datosClientes_header, BorderLayout.NORTH);
		panelTablaDatosCliente.add(datosClientes_table, BorderLayout.CENTER);

		contentPane.add(panelTablaDatosCliente);

		JLabel horasDeTrabajo_lbl = new JLabel("Horas de trabajo:");
		horasDeTrabajo_lbl.setBounds(10, 274, 146, 14);
		contentPane.add(horasDeTrabajo_lbl);

		horasDeTrabajo_txf = new JTextField();
		horasDeTrabajo_txf.setBounds(136, 274, 86, 20);
		contentPane.add(horasDeTrabajo_txf);
		horasDeTrabajo_txf.setColumns(10);

		JLabel manoDeObra_lbl = new JLabel("Mano de Obra:");
		manoDeObra_lbl.setBounds(232, 277, 146, 14);
		contentPane.add(manoDeObra_lbl);

		manoDeObra_txf = new JTextField();
		manoDeObra_txf.setBounds(309, 274, 86, 20);
		manoDeObra_txf.setColumns(10);
		contentPane.add(manoDeObra_txf);

		JLabel vencimiento_lbl = new JLabel("Vencimiento:");
		vencimiento_lbl.setBounds(405, 277, 146, 14);
		contentPane.add(vencimiento_lbl);

		JDateChooser vencimiento_Calendario = new JDateChooser();
		vencimiento_Calendario.setBounds(470, 274, 129, 20);
		contentPane.add(vencimiento_Calendario);

		JLabel componentesAUtilizar_lbl = new JLabel("Componentes a utilizar:");
		componentesAUtilizar_lbl.setBounds(10, 302, 129, 14);
		contentPane.add(componentesAUtilizar_lbl);

		JComboBox componente_ComboBox = new JComboBox();
		componente_ComboBox.setBounds(136, 299, 146, 20);
		contentPane.add(componente_ComboBox);

		JButton incrementoCantComponente_btn = new JButton("");
		incrementoCantComponente_btn.setBounds(303, 298, 33, 23);
		incrementoCantComponente_btn.setIcon(
				new ImageIcon("C:\\workspaceLaboratorioCS\\electro-service\\recursos\\icons\\plus-outline.png"));
		contentPane.add(incrementoCantComponente_btn);

		JButton decrementoCantComponente_btn = new JButton("");
		decrementoCantComponente_btn.setBounds(386, 298, 33, 23);
		decrementoCantComponente_btn.setIcon(
				new ImageIcon("C:\\workspaceLaboratorioCS\\electro-service\\recursos\\icons\\minus-outline.png"));
		contentPane.add(decrementoCantComponente_btn);

		JLabel cantidad_lbl = new JLabel("0");
		cantidad_lbl.setBounds(345, 299, 33, 20);
		cantidad_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		cantidad_lbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cantidad_lbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));
		contentPane.add(cantidad_lbl);

		JButton agregarComponente_btn = new JButton("");
		agregarComponente_btn.setBounds(429, 298, 33, 23);
		agregarComponente_btn.setIcon(
				new ImageIcon("C:\\workspaceLaboratorioCS\\electro-service\\recursos\\icons\\tick-outline.png"));
		contentPane.add(agregarComponente_btn);

		JButton eliminarComponente_btn = new JButton("");
		eliminarComponente_btn.setBounds(470, 298, 33, 23);
		eliminarComponente_btn.setIcon(
				new ImageIcon("C:\\workspaceLaboratorioCS\\electro-service\\recursos\\icons\\times-outline.png"));
		contentPane.add(eliminarComponente_btn);

		String[] componentes_nombreColumnas = { "Nombre", "Descripción", "Cantidad", "Precio Unitario",
				"Precio Total" };
		Object[][] componentes_informacionTabla = {};

		JScrollPane componentes_scrollPane = new JScrollPane();
		componentes_scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		componentes_scrollPane.setEnabled(false);
		componentes_scrollPane.setSize(664, 78);
		componentes_scrollPane.setLocation(10, 327);
		componentes_scrollPane.setBackground(Color.WHITE);
		contentPane.add(componentes_scrollPane);

		JTable componentes_table = new JTable(componentes_informacionTabla, componentes_nombreColumnas);
		componentes_table.setEnabled(false);
		componentes_scrollPane.setViewportView(componentes_table);

		JLabel descripcionBreve_lbl = new JLabel("Descripción breve:");
		descripcionBreve_lbl.setBounds(10, 416, 289, 14);
		contentPane.add(descripcionBreve_lbl);
		JScrollPane descripcionBreve_jScrollPane = new JScrollPane();
		descripcionBreve_jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		descripcionBreve_jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		descripcionBreve_jScrollPane.setBounds(new Rectangle(10, 10, 30, 30));
		descripcionBreve_jScrollPane.setBounds(10, 441, 305, 52);
		JTextArea descripcionBreve_jTextArea = new JTextArea();
		descripcionBreve_jTextArea.setBounds(new Rectangle(0, 0, 305, 52));
		descripcionBreve_jScrollPane.add(descripcionBreve_jTextArea);
		descripcionBreve_jScrollPane.setViewportView(descripcionBreve_jTextArea);
		contentPane.add(descripcionBreve_jScrollPane);

		JLabel descripcionTecnica_lbl = new JLabel();
		descripcionTecnica_lbl.setText("Descripci\u00F3n t\u00E9cnica:");
		descripcionTecnica_lbl.setBounds(369, 416, 291, 14);
		contentPane.add(descripcionTecnica_lbl);
		JScrollPane descripcionTecnica_jScrollPane = new JScrollPane();
		descripcionTecnica_jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		descripcionTecnica_jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		descripcionTecnica_jScrollPane.setBounds(369, 441, 305, 52);
		JTextArea descripcionTecnica_jTextArea = new JTextArea();
		descripcionTecnica_jTextArea.setBounds(new Rectangle(0, 0, 305, 52));
		descripcionTecnica_jScrollPane.add(descripcionTecnica_jTextArea);
		descripcionTecnica_jScrollPane.setViewportView(descripcionTecnica_jTextArea);
		contentPane.add(descripcionTecnica_jScrollPane);

		JLabel valorPresupuestado_lbl = new JLabel();
		valorPresupuestado_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		valorPresupuestado_lbl.setText("Valor Presupuestado:");
		valorPresupuestado_lbl.setBounds(10, 522, 196, 14);
		contentPane.add(valorPresupuestado_lbl);
		JTextField valorPresupuestado_txf = new JTextField();
		valorPresupuestado_txf.setBackground(SystemColor.text);
		valorPresupuestado_txf.setBounds(168, 523, 506, 16);
		contentPane.add(valorPresupuestado_txf);
		
		JButton verPresupuesto_btn = new JButton("Ver presupuesto");
		verPresupuesto_btn.setIcon(new ImageIcon("C:\\workspaceLaboratorioCS\\electro-service\\recursos\\icons\\document-text.png"));
		verPresupuesto_btn.setBounds(10, 556, 146, 23);
		contentPane.add(verPresupuesto_btn);
		
		JButton enviarPresupuesto_btn = new JButton("Enviar presupuesto");
		enviarPresupuesto_btn.setIcon(new ImageIcon("C:\\workspaceLaboratorioCS\\electro-service\\recursos\\icons\\mail.png"));
		enviarPresupuesto_btn.setBounds(162, 556, 153, 23);
		contentPane.add(enviarPresupuesto_btn);
		
		JButton cancelar_btn = new JButton("Cancelar");
		cancelar_btn.setBounds(585, 556, 89, 23);
		contentPane.add(cancelar_btn);
		
		JButton guardar_btn = new JButton("Guardar");
		guardar_btn.setBounds(484, 556, 89, 23);
		contentPane.add(guardar_btn);

	}
}
