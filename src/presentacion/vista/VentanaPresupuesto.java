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
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
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
	private JButton buscarTecnico_btn;
	private JTable datosClientes_table;
	private JDateChooser vencimiento_Calendario;
	private JComboBox<String> componente_ComboBox; 
	private JButton incrementoCantComponente_btn;
	private JButton decrementoCantComponente_btn;
	private JButton agregarComponente_btn;
	private JButton eliminarComponente_btn;
	private JTextArea descripcionBreve_jTextArea;
	private JTextArea descripcionTecnica_jTextArea;
	private JTextField valorPresupuestado_txf;
	private JButton verPresupuesto_btn;
	private JButton enviarPresupuesto_btn;
	private JButton cancelar_btn;
	private JButton guardar_btn; 
	private JLabel cantidad_lbl;
	private JTable componentes_table;
	private JLabel fechaIngreso_lbl;
	private String[] componentes_nombreColumnas = { "Nombre", "Descripcion", "Cantidad", "Precio Unitario",
	"Precio Total" };
	

	public VentanaPresupuesto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 698, 660);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		fechaIngreso_lbl = new JLabel("Fecha");
		fechaIngreso_lbl.setBounds(495, 11, 166, 14);
		contentPane.add(fechaIngreso_lbl);

		JLabel fechaIngresoText_lbl = new JLabel("");
		fechaIngresoText_lbl.setBounds(572, 11, 102, 14);
		contentPane.add(fechaIngresoText_lbl);

		JLabel titulo_lbl = new JLabel("<html><b>PRESUPUESTO</b></html>");
		titulo_lbl.setBounds(10, 11, 495, 14);
		contentPane.add(titulo_lbl);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 34, 664, 7);
		separator.setForeground(Color.BLUE);
		contentPane.add(separator);

		JLabel nombreProducto_lbl = new JLabel("<html><i>Nombre Producto:</i></html>");
		nombreProducto_lbl.setBounds(10, 37, 116, 29);
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
		nroTecnico_txf.setBounds(123, 195, 146, 20);
		contentPane.add(nroTecnico_txf);
		nroTecnico_txf.setColumns(10);

		buscarTecnico_btn = new JButton("");
		buscarTecnico_btn.setBounds(281, 188, 33, 23);
		buscarTecnico_btn
				.setIcon(new ImageIcon(VentanaPresupuesto.class.getResource("/search.png")));
		contentPane.add(buscarTecnico_btn);

		String[] datosClientes_nombreColumnas = { "Nombre", "Apellido" };
		Object[][] datosClientes_informacionTabla = {};

		datosClientes_table = new JTable(datosClientes_informacionTabla, datosClientes_nombreColumnas);
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
		horasDeTrabajo_lbl.setBounds(10, 480, 146, 14);
		contentPane.add(horasDeTrabajo_lbl);

		horasDeTrabajo_txf = new JTextField();
		horasDeTrabajo_txf.setBounds(122, 478, 86, 20);
		contentPane.add(horasDeTrabajo_txf);
		horasDeTrabajo_txf.setColumns(10);

		JLabel manoDeObra_lbl = new JLabel("Mano de Obra:");
		manoDeObra_lbl.setBounds(216, 480, 146, 14);
		contentPane.add(manoDeObra_lbl);

		manoDeObra_txf = new JTextField();
		manoDeObra_txf.setBounds(303, 477, 86, 20);
		manoDeObra_txf.setColumns(10);
		contentPane.add(manoDeObra_txf);

		JLabel vencimiento_lbl = new JLabel("Vencimiento:");
		vencimiento_lbl.setBounds(399, 480, 146, 14);
		contentPane.add(vencimiento_lbl);

		vencimiento_Calendario = new JDateChooser();
		vencimiento_Calendario.setBounds(470, 477, 129, 20);
		contentPane.add(vencimiento_Calendario);

		JLabel componentesAUtilizar_lbl = new JLabel("Componentes a utilizar:");
		componentesAUtilizar_lbl.setBounds(10, 278, 129, 14);
		contentPane.add(componentesAUtilizar_lbl);

		componente_ComboBox = new JComboBox<String>();
		componente_ComboBox.setBounds(136, 275, 146, 20);
		contentPane.add(componente_ComboBox);

		incrementoCantComponente_btn = new JButton("");
		incrementoCantComponente_btn.setBounds(303, 274, 33, 23);
		incrementoCantComponente_btn.setIcon(
				new ImageIcon(VentanaPresupuesto.class.getResource("/plus-outline.png")));
		contentPane.add(incrementoCantComponente_btn);

		decrementoCantComponente_btn = new JButton("");
		decrementoCantComponente_btn.setBounds(386, 274, 33, 23);
		decrementoCantComponente_btn.setIcon(
				new ImageIcon(VentanaPresupuesto.class.getResource("/minus-outline.png")));
		contentPane.add(decrementoCantComponente_btn);

		cantidad_lbl = new JLabel("0");
		cantidad_lbl.setBounds(345, 275, 33, 20);
		cantidad_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		cantidad_lbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cantidad_lbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));
		contentPane.add(cantidad_lbl);

		agregarComponente_btn = new JButton("");
		agregarComponente_btn.setBounds(429, 274, 33, 23);
		agregarComponente_btn.setIcon(
				new ImageIcon(VentanaPresupuesto.class.getResource("/tick-outline.png")));
		contentPane.add(agregarComponente_btn);

		eliminarComponente_btn = new JButton("");
		eliminarComponente_btn.setBounds(470, 274, 33, 23);
		eliminarComponente_btn.setIcon(
				new ImageIcon(VentanaPresupuesto.class.getResource("/times-outline.png")));
		contentPane.add(eliminarComponente_btn);

		
		Object[][] componentes_informacionTabla = {};

		JScrollPane componentes_scrollPane = new JScrollPane();
		componentes_scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		componentes_scrollPane.setEnabled(false);
		componentes_scrollPane.setSize(664, 78);
		componentes_scrollPane.setLocation(10, 303);
		componentes_scrollPane.setBackground(Color.WHITE);
		contentPane.add(componentes_scrollPane);
		
		componentes_table = new JTable(componentes_informacionTabla, componentes_nombreColumnas);
		//componentes_table.setEnabled(false);
		componentes_scrollPane.setViewportView(componentes_table);

		JLabel descripcionBreve_lbl = new JLabel("Descripci�n breve:");
		descripcionBreve_lbl.setBounds(10, 392, 289, 14);
		contentPane.add(descripcionBreve_lbl);
		JScrollPane descripcionBreve_jScrollPane = new JScrollPane();
		descripcionBreve_jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		descripcionBreve_jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		descripcionBreve_jScrollPane.setBounds(new Rectangle(10, 10, 30, 30));
		descripcionBreve_jScrollPane.setBounds(10, 417, 305, 52);
		descripcionBreve_jTextArea = new JTextArea();
		descripcionBreve_jTextArea.setBounds(new Rectangle(0, 0, 305, 52));
		descripcionBreve_jScrollPane.add(descripcionBreve_jTextArea);
		descripcionBreve_jScrollPane.setViewportView(descripcionBreve_jTextArea);
		contentPane.add(descripcionBreve_jScrollPane);

		JLabel descripcionTecnica_lbl = new JLabel();
		descripcionTecnica_lbl.setText("Descripci\u00F3n t\u00E9cnica:");
		descripcionTecnica_lbl.setBounds(370, 392, 291, 14);
		contentPane.add(descripcionTecnica_lbl);
		JScrollPane descripcionTecnica_jScrollPane = new JScrollPane();
		descripcionTecnica_jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		descripcionTecnica_jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		descripcionTecnica_jScrollPane.setBounds(369, 417, 305, 52);
		descripcionTecnica_jTextArea = new JTextArea();
		descripcionTecnica_jTextArea.setBounds(new Rectangle(0, 0, 305, 52));
		descripcionTecnica_jScrollPane.add(descripcionTecnica_jTextArea);
		descripcionTecnica_jScrollPane.setViewportView(descripcionTecnica_jTextArea);
		contentPane.add(descripcionTecnica_jScrollPane);

		JLabel valorPresupuestado_lbl = new JLabel();
		valorPresupuestado_lbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		valorPresupuestado_lbl.setText("Valor Presupuestado:");
		valorPresupuestado_lbl.setBounds(148, 520, 196, 20);
		contentPane.add(valorPresupuestado_lbl);
		valorPresupuestado_txf = new JTextField();
		valorPresupuestado_txf.setBackground(SystemColor.text);
		valorPresupuestado_txf.setBounds(313, 517, 146, 23);
		contentPane.add(valorPresupuestado_txf);
		
		verPresupuesto_btn = new JButton("Imprimir");
		verPresupuesto_btn.setIcon(new ImageIcon("/printer.png"));
		verPresupuesto_btn.setBounds(10, 556, 146, 23);
		contentPane.add(verPresupuesto_btn);
		
		enviarPresupuesto_btn = new JButton("Enviar presupuesto");
		enviarPresupuesto_btn.setIcon(new ImageIcon(VentanaPresupuesto.class.getResource("/mail.png")));
		enviarPresupuesto_btn.setBounds(162, 556, 153, 23);
		contentPane.add(enviarPresupuesto_btn);
		
		cancelar_btn = new JButton("Cancelar");
		cancelar_btn.setBounds(585, 556, 89, 23);
		contentPane.add(cancelar_btn);
		
		guardar_btn = new JButton("Guardar");
		guardar_btn.setBounds(484, 556, 89, 23);
		contentPane.add(guardar_btn);

	}


	public JLabel getCantidad_lbl() {
		return cantidad_lbl;
	}


	public JTextField getNroTecnico_txf() {
		return nroTecnico_txf;
	}


	public JTextField getHorasDeTrabajo_txf() {
		return horasDeTrabajo_txf;
	}


	public JTextField getManoDeObra_txf() {
		return manoDeObra_txf;
	}


	public JButton getBuscarTecnico_btn() {
		return buscarTecnico_btn;
	}


	public JTable getDatosClientes_table() {
		return datosClientes_table;
	}


	public JDateChooser getVencimiento_Calendario() {
		return vencimiento_Calendario;
	}


	public JComboBox<String> getComponente_ComboBox() {
		return componente_ComboBox;
	}


	public JButton getIncrementoCantComponente_btn() {
		return incrementoCantComponente_btn;
	}


	public JButton getDecrementoCantComponente_btn() {
		return decrementoCantComponente_btn;
	}


	public JButton getAgregarComponente_btn() {
		return agregarComponente_btn;
	}


	public JButton getEliminarComponente_btn() {
		return eliminarComponente_btn;
	}


	public JTextArea getDescripcionBreve_jTextArea() {
		return descripcionBreve_jTextArea;
	}


	public JTextArea getDescripcionTecnica_jTextArea() {
		return descripcionTecnica_jTextArea;
	}


	public JTextField getValorPresupuestado_txf() {
		return valorPresupuestado_txf;
	}


	public JButton getVerPresupuesto_btn() {
		return verPresupuesto_btn;
	}


	public JButton getEnviarPresupuesto_btn() {
		return enviarPresupuesto_btn;
	}


	public JButton getCancelar_btn() {
		return cancelar_btn;
	}


	public JButton getGuardar_btn() {
		return guardar_btn;
	}


	public JTable getComponentes_table() {
		return componentes_table;
	}


	public String[] getComponentes_nombreColumnas() {
		return componentes_nombreColumnas;
	}


	public JLabel getFechaIngreso_lbl() {
		return fechaIngreso_lbl;
	}
	
	
	
	
}
