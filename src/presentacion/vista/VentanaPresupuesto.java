package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
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
import java.awt.Toolkit;

public class VentanaPresupuesto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField horasDeTrabajo_txf;
	private JTextField manoDeObra_txf;
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
	private JLabel nombreProductoTexto_lbl;
	private JLabel tipoTexto_lbl;
	private JLabel marcaTexto_lbl;
	private JTextArea descripcionFalla_txtArea;
	private String[] componentes_nombreColumnas = { "id", "Detalle", "Cantidad", "Precio Unitario", "Precio Total" };
	private JLabel Total_lbl;
	private JLabel estado_lb;
	private JLabel lbltecnico;

	public VentanaPresupuesto() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPresupuesto.class.getResource("/calculator.png")));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 745, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		fechaIngreso_lbl = new JLabel("");
		fechaIngreso_lbl.setBounds(505, 11, 166, 14);
		contentPane.add(fechaIngreso_lbl);

		JLabel fechaIngresoText_lbl = new JLabel("");
		fechaIngresoText_lbl.setBounds(572, 11, 102, 14);
		contentPane.add(fechaIngresoText_lbl);

		JLabel titulo_lbl = new JLabel("<html><i>PRESUPUESTO</i></html>");
		titulo_lbl.setBounds(30, 11, 495, 14);
		contentPane.add(titulo_lbl);

		JSeparator separator = new JSeparator();
		separator.setBounds(30, 36, 658, 21);
		separator.setForeground(Color.BLUE);
		contentPane.add(separator);

		JLabel nombreProducto_lbl = new JLabel("<html><i>Nombre Producto:</i></html>");
		nombreProducto_lbl.setBounds(47, 37, 116, 29);
		contentPane.add(nombreProducto_lbl);

		nombreProductoTexto_lbl = new JLabel("");
		nombreProductoTexto_lbl.setBounds(179, 37, 445, 26);
		contentPane.add(nombreProductoTexto_lbl);

		JLabel marca_lbl = new JLabel("<html><i>Marca:</i></html>");
		marca_lbl.setBounds(52, 78, 116, 14);
		contentPane.add(marca_lbl);

		marcaTexto_lbl = new JLabel("");
		marcaTexto_lbl.setBounds(153, 70, 184, 14);
		contentPane.add(marcaTexto_lbl);

		JLabel tipo_lbl = new JLabel("<html><i>Tipo:</i></html>");
		tipo_lbl.setBounds(338, 78, 116, 14);
		contentPane.add(tipo_lbl);

		tipoTexto_lbl = new JLabel("");
		tipoTexto_lbl.setBounds(490, 78, 184, 14);
		contentPane.add(tipoTexto_lbl);

		JLabel descripcionFalla_lbl = new JLabel("<html><i>Descripci\u00F3n falla:</i></html>");
		descripcionFalla_lbl.setBounds(30, 103, 146, 14);
		contentPane.add(descripcionFalla_lbl);

		JScrollPane descripcionFalla_jScrollPane = new javax.swing.JScrollPane();
		descripcionFalla_jScrollPane.setBounds(30, 126, 664, 46);
		descripcionFalla_jScrollPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
		descripcionFalla_jScrollPane
				.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		descripcionFalla_txtArea = new JTextArea();
		descripcionFalla_txtArea.setEditable(false);
		descripcionFalla_txtArea.setRows(5);
		descripcionFalla_txtArea.setColumns(20);
		descripcionFalla_jScrollPane.setViewportView(descripcionFalla_txtArea);

		contentPane.add(descripcionFalla_jScrollPane);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(30, 183, 664, 7);
		separator_2.setForeground(Color.BLUE);
		contentPane.add(separator_2);

		JLabel lblTecnico = new JLabel("Tecnico Asignado:");
		lblTecnico.setBounds(40, 192, 153, 14);
		contentPane.add(lblTecnico);

		JLabel horasDeTrabajo_lbl = new JLabel("Horas de trabajo:");
		horasDeTrabajo_lbl.setBounds(10, 550, 146, 14);
		contentPane.add(horasDeTrabajo_lbl);

		horasDeTrabajo_txf = new JTextField();
		horasDeTrabajo_txf.setBounds(146, 548, 86, 20);
		contentPane.add(horasDeTrabajo_txf);
		horasDeTrabajo_txf.setColumns(10);

		JLabel manoDeObra_lbl = new JLabel("Mano de Obra:");
		manoDeObra_lbl.setBounds(250, 550, 146, 14);
		contentPane.add(manoDeObra_lbl);

		manoDeObra_txf = new JTextField();

		manoDeObra_txf.setBounds(370, 548, 86, 20);
		manoDeObra_txf.setColumns(10);
		contentPane.add(manoDeObra_txf);

		JLabel vencimiento_lbl = new JLabel("Vencimiento:");
		vencimiento_lbl.setBounds(479, 550, 102, 14);
		contentPane.add(vencimiento_lbl);

		vencimiento_Calendario = new JDateChooser();
		vencimiento_Calendario.setBounds(583, 544, 129, 20);
		contentPane.add(vencimiento_Calendario);

		JLabel componentesAUtilizar_lbl = new JLabel("Componentes a utilizar:");
		componentesAUtilizar_lbl.setBounds(30, 279, 193, 14);
		contentPane.add(componentesAUtilizar_lbl);

		componente_ComboBox = new JComboBox<String>();
		componente_ComboBox.setBounds(193, 276, 146, 20);
		contentPane.add(componente_ComboBox);

		incrementoCantComponente_btn = new JButton("");
		incrementoCantComponente_btn.setBounds(360, 275, 33, 23);
		incrementoCantComponente_btn.setIcon(new ImageIcon(VentanaPresupuesto.class.getResource("/plus-outline.png")));
		contentPane.add(incrementoCantComponente_btn);

		decrementoCantComponente_btn = new JButton("");
		decrementoCantComponente_btn.setBounds(443, 275, 33, 23);
		decrementoCantComponente_btn.setIcon(new ImageIcon(VentanaPresupuesto.class.getResource("/minus-outline.png")));
		contentPane.add(decrementoCantComponente_btn);

		cantidad_lbl = new JLabel("1");
		cantidad_lbl.setBounds(402, 276, 33, 20);
		cantidad_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		cantidad_lbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cantidad_lbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));
		contentPane.add(cantidad_lbl);

		agregarComponente_btn = new JButton("");
		agregarComponente_btn.setBounds(486, 275, 33, 23);
		agregarComponente_btn.setIcon(new ImageIcon(VentanaPresupuesto.class.getResource("/tick-outline.png")));
		contentPane.add(agregarComponente_btn);

		eliminarComponente_btn = new JButton("");
		eliminarComponente_btn.setBounds(527, 275, 33, 23);
		eliminarComponente_btn.setIcon(new ImageIcon(VentanaPresupuesto.class.getResource("/times-outline.png")));
		contentPane.add(eliminarComponente_btn);

		Object[][] componentes_informacionTabla = {};

		JScrollPane componentes_scrollPane = new JScrollPane();
		componentes_scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		componentes_scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		componentes_scrollPane.setEnabled(false);
		componentes_scrollPane.setSize(664, 95);
		componentes_scrollPane.setLocation(30, 304);
		componentes_scrollPane.setBackground(Color.WHITE);
		contentPane.add(componentes_scrollPane);

		componentes_table = new JTable(componentes_informacionTabla, componentes_nombreColumnas);
		// componentes_table.setEnabled(false);
		componentes_scrollPane.setViewportView(componentes_table);

		JLabel descripcionBreve_lbl = new JLabel("Descripcion breve:");
		descripcionBreve_lbl.setBounds(47, 445, 156, 14);
		contentPane.add(descripcionBreve_lbl);
		JScrollPane descripcionBreve_jScrollPane = new JScrollPane();
		descripcionBreve_jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		descripcionBreve_jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		descripcionBreve_jScrollPane.setBounds(new Rectangle(10, 10, 30, 30));
		descripcionBreve_jScrollPane.setBounds(30, 471, 291, 52);
		contentPane.add(descripcionBreve_jScrollPane);
		descripcionBreve_jTextArea = new JTextArea();
		descripcionBreve_jScrollPane.setViewportView(descripcionBreve_jTextArea);

		JLabel descripcionTecnica_lbl = new JLabel();
		descripcionTecnica_lbl.setText("Descripci\u00F3n t\u00E9cnica:");
		descripcionTecnica_lbl.setBounds(412, 445, 291, 14);
		contentPane.add(descripcionTecnica_lbl);
		JScrollPane descripcionTecnica_jScrollPane = new JScrollPane();
		descripcionTecnica_jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		descripcionTecnica_jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		descripcionTecnica_jScrollPane.setBounds(394, 471, 300, 52);
		contentPane.add(descripcionTecnica_jScrollPane);
		descripcionTecnica_jTextArea = new JTextArea();
		descripcionTecnica_jScrollPane.setViewportView(descripcionTecnica_jTextArea);

		JLabel valorPresupuestado_lbl = new JLabel();
		valorPresupuestado_lbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		valorPresupuestado_lbl.setText("Valor Presupuestado:");
		valorPresupuestado_lbl.setBounds(10, 580, 196, 20);
		contentPane.add(valorPresupuestado_lbl);
		valorPresupuestado_txf = new JTextField();
		valorPresupuestado_txf.setBackground(SystemColor.text);
		valorPresupuestado_txf.setBounds(193, 580, 146, 23);
		contentPane.add(valorPresupuestado_txf);

		verPresupuesto_btn = new JButton("Imprimir");
		verPresupuesto_btn.setIcon(new ImageIcon("/printer.png"));
		verPresupuesto_btn.setBounds(12, 615, 146, 23);
		contentPane.add(verPresupuesto_btn);

		enviarPresupuesto_btn = new JButton("Enviar presupuesto");
		enviarPresupuesto_btn.setIcon(new ImageIcon(VentanaPresupuesto.class.getResource("/mail.png")));
		enviarPresupuesto_btn.setBounds(164, 615, 212, 23);
		contentPane.add(enviarPresupuesto_btn);

		cancelar_btn = new JButton("Cancelar");
		cancelar_btn.setBounds(582, 615, 117, 23);
		contentPane.add(cancelar_btn);

		guardar_btn = new JButton("Guardar");
		guardar_btn.setBounds(438, 615, 118, 23);
		contentPane.add(guardar_btn);

		Total_lbl = new JLabel("");
		Total_lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		Total_lbl.setBounds(572, 418, 112, 15);
		contentPane.add(Total_lbl);

		JLabel lblTotalRepuestos = new JLabel("Total repuestos:");
		lblTotalRepuestos.setBounds(412, 418, 127, 15);
		contentPane.add(lblTotalRepuestos);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(402, 192, 145, 15);
		contentPane.add(lblEstado);

		estado_lb = new JLabel("");
		estado_lb.setBounds(465, 192, 116, 15);
		contentPane.add(estado_lb);

		JLabel lblFecha = new JLabel("");
		lblFecha.setBounds(435, 10, 70, 15);
		contentPane.add(lblFecha);

		lbltecnico = new JLabel("");
		lbltecnico.setBounds(140, 191, 214, 15);
		contentPane.add(lbltecnico);
	}

	public JLabel getCantidad_lbl() {
		return cantidad_lbl;
	}

	public JTextField getHorasDeTrabajo_txf() {
		return horasDeTrabajo_txf;
	}

	public JTextField getManoDeObra_txf() {
		return manoDeObra_txf;
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

	public JLabel getNombreProductoTexto_lbl() {
		return nombreProductoTexto_lbl;
	}

	public JLabel getTipoTexto_lbl() {
		return tipoTexto_lbl;
	}

	public JLabel getMarcaTexto_lbl() {
		return marcaTexto_lbl;
	}

	public JTextArea getDescripcionFalla_txtArea() {
		return descripcionFalla_txtArea;
	}

	public JLabel getTotal_lbl() {
		return Total_lbl;
	}

	public JLabel getEstado_lb() {
		return estado_lb;
	}

	public JLabel getLbltecnico() {
		return lbltecnico;
	}

	public void setLbltecnico(JLabel lbltecnico) {
		this.lbltecnico = lbltecnico;
	}

}
