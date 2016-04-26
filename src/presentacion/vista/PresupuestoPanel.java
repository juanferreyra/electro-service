
package presentacion.vista;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PresupuestoPanel extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	private javax.swing.JLabel cantidad_lbl;
	private javax.swing.JComboBox<String> componentes_jComboBox;
	private javax.swing.JLabel componentes_lbl;
	private javax.swing.JScrollPane descripcionBreve_jScrollPane;
	private javax.swing.JTextArea descripcionBreve_jTextArea;
	private javax.swing.JLabel descripcionBreve_lbl;
	private javax.swing.JScrollPane descripcionTecnica_jScrollPane;
	private javax.swing.JTextArea descripcionTecnica_jTextArea;
	private javax.swing.JLabel descripcionTecnica_lbl;
	private javax.swing.JLabel fecha_Presupuesto_lbl;
	private javax.swing.JTextField fecha_Presupuesto_txf;
	private javax.swing.JLabel horasTrabajo_lbl;
	private javax.swing.JTextField horasTrabajo_txf;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JTextField valorPresupuestado_txf;
	private javax.swing.JLabel nroTecnico_lbl;
	private javax.swing.JTextField nroTecnico_txf;
	private javax.swing.JLabel presupuesto_lbl;
	private javax.swing.JLabel valorPresupuestado_lbl;
	private JTable tablaComponentes;
	private JScrollPane tabla_scrollPane;
	private JButton iconMas_btn;
	private JButton iconMenos_btn;
	private JButton iconIngreso_btn;
	private JTextField fechaVencimiento_txf;

	public PresupuestoPanel() {
		initComponents();

	}

	private void initComponents() {

		presupuesto_lbl = new javax.swing.JLabel();
		presupuesto_lbl.setBounds(138, 11, 508, 28);
		jSeparator1 = new javax.swing.JSeparator();
		jSeparator1.setBounds(10, 45, 873, 13);
		fecha_Presupuesto_lbl = new javax.swing.JLabel();
		fecha_Presupuesto_lbl.setBounds(668, 21, 56, 14);
		fecha_Presupuesto_txf = new javax.swing.JTextField();
		fecha_Presupuesto_txf.setBounds(800, 21, 83, 14);
		nroTecnico_lbl = new javax.swing.JLabel();
		nroTecnico_lbl.setBounds(139, 67, 117, 14);
		nroTecnico_txf = new javax.swing.JTextField();
		nroTecnico_txf.setBounds(266, 64, 152, 20);
		horasTrabajo_lbl = new javax.swing.JLabel();
		horasTrabajo_lbl.setBounds(139, 93, 117, 14);
		horasTrabajo_txf = new javax.swing.JTextField();
		horasTrabajo_txf.setBounds(266, 90, 152, 20);
		componentes_lbl = new javax.swing.JLabel();
		componentes_lbl.setBounds(138, 121, 83, 14);
		componentes_jComboBox = new javax.swing.JComboBox<>();
		componentes_jComboBox.setBounds(266, 118, 152, 27);
		cantidad_lbl = new javax.swing.JLabel();
		cantidad_lbl.setBounds(473, 121, 26, 24);
		descripcionBreve_lbl = new javax.swing.JLabel();
		descripcionBreve_lbl.setBounds(139, 266, 289, 14);
		descripcionTecnica_lbl = new javax.swing.JLabel();
		descripcionTecnica_lbl.setBounds(454, 266, 291, 14);
		descripcionTecnica_jScrollPane = new javax.swing.JScrollPane();
		descripcionTecnica_jScrollPane.setBounds(456, 286, 289, 52);
		descripcionBreve_jScrollPane = new javax.swing.JScrollPane();
		descripcionBreve_jScrollPane.setBounds(139, 286, 289, 52);
		descripcionBreve_jTextArea = new javax.swing.JTextArea();
		valorPresupuestado_lbl = new javax.swing.JLabel();
		valorPresupuestado_lbl.setBounds(139, 349, 127, 14);
		valorPresupuestado_txf = new javax.swing.JTextField();
		valorPresupuestado_txf.setBackground(SystemColor.text);
		valorPresupuestado_txf.setBounds(266, 348, 479, 16);

		setBackground(new java.awt.Color(255, 255, 255));

		presupuesto_lbl.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		presupuesto_lbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		presupuesto_lbl.setText("<html><i>Presupuesto</i></html>");

		jSeparator1.setBackground(new java.awt.Color(0, 102, 255));

		fecha_Presupuesto_lbl.setText("Fecha");

		fecha_Presupuesto_txf.setBorder(null);
		fecha_Presupuesto_txf.setEnabled(false);
		fecha_Presupuesto_txf.setOpaque(false);

		nroTecnico_lbl.setText("Nro T\u00E9cnico");

		horasTrabajo_lbl.setText("Horas de Trabajo");

		componentes_lbl.setText("Componentes");

		componentes_jComboBox.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		cantidad_lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		cantidad_lbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

		String[] nombreColumnas = { "Nombre", "Cantidad" };
		Object[][] informacionTabla = {};
		tablaComponentes = new JTable(informacionTabla, nombreColumnas);
		tablaComponentes.setBounds(138, 146, 605, 111);

		tabla_scrollPane = new JScrollPane();
		tabla_scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tabla_scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tabla_scrollPane.setBounds(138, 159, 605, 100);
		tabla_scrollPane.setViewportView(tablaComponentes);

		descripcionBreve_lbl.setText("Descripci\u00F3n Breve ");

		descripcionTecnica_lbl.setText("Descripci\u00F3n T\u00E9cnica");

		descripcionBreve_jTextArea.setColumns(20);
		descripcionBreve_jTextArea.setRows(5);
		descripcionBreve_jScrollPane.setViewportView(descripcionBreve_jTextArea);

		valorPresupuestado_lbl.setText("<html><b>Valor Presupuestado</b></html>");

		valorPresupuestado_txf.setEditable(false);
		valorPresupuestado_txf.setBorder(new LineBorder(new Color(0, 0, 205)));
		valorPresupuestado_txf.setOpaque(false);
		descripcionTecnica_jTextArea = new javax.swing.JTextArea();
		descripcionTecnica_jScrollPane.setViewportView(descripcionTecnica_jTextArea);

		descripcionTecnica_jTextArea.setColumns(20);
		descripcionTecnica_jTextArea.setRows(5);
		setLayout(null);
		add(presupuesto_lbl);
		add(fecha_Presupuesto_lbl);
		add(fecha_Presupuesto_txf);
		add(jSeparator1);
		add(nroTecnico_lbl);
		add(componentes_lbl);
		add(horasTrabajo_lbl);
		add(nroTecnico_txf);
		add(horasTrabajo_txf);
		add(componentes_jComboBox);
		add(cantidad_lbl);
		add(tabla_scrollPane);
		add(valorPresupuestado_lbl);
		add(valorPresupuestado_txf);
		add(descripcionBreve_jScrollPane);
		add(descripcionBreve_lbl);
		add(descripcionTecnica_lbl);
		add(descripcionTecnica_jScrollPane);

		iconMas_btn = new JButton("");
		iconMas_btn.setIcon(
				new ImageIcon("C:\\workspaceLaboratorioCS\\electro-service\\recursos\\icons\\plus-outline.png"));
		iconMas_btn.setBounds(428, 118, 37, 27);
		add(iconMas_btn);

		iconMenos_btn = new JButton("");
		iconMenos_btn.setIcon(
				new ImageIcon("C:\\workspaceLaboratorioCS\\electro-service\\recursos\\icons\\minus-outline.png"));
		iconMenos_btn.setBounds(509, 118, 37, 27);
		add(iconMenos_btn);

		iconIngreso_btn = new JButton("");
		iconIngreso_btn.setIcon(
				new ImageIcon("C:\\workspaceLaboratorioCS\\electro-service\\recursos\\icons\\tick-outline.png"));
		iconIngreso_btn.setBounds(556, 118, 37, 27);
		add(iconIngreso_btn);
		
		JLabel FechaVencimiento_lbl = new JLabel();
		FechaVencimiento_lbl.setText("Fecha Vencimiento");
		FechaVencimiento_lbl.setBounds(462, 69, 117, 14);
		add(FechaVencimiento_lbl);
		
		fechaVencimiento_txf = new JTextField();
		fechaVencimiento_txf.setBounds(589, 64, 152, 20);
		add(fechaVencimiento_txf);

	}

	public JComboBox<String> getComponentes_jComboBox() {
		return componentes_jComboBox;
	}

	public String getDescripcionBreve_jTextArea() {
		return descripcionBreve_jTextArea.getText();
	}

	public String getDescripcionTecnica_jTextArea() {
		return descripcionTecnica_jTextArea.getText();
	}

	public String getFecha_Presupuesto_txf() {
		return fecha_Presupuesto_txf.getText();
	}

	public void setFecha_Presupuesto_txf(String fecha_Presupuesto) {
		this.fecha_Presupuesto_txf.setText(fecha_Presupuesto);
	}

	public String getHorasTrabajo_txf() {
		return horasTrabajo_txf.getText();
	}

	public void setHorasTrabajo_txf(String horasTrabajo) {
		this.horasTrabajo_txf.setText(horasTrabajo);
	}

	public String getValorPresupuestado_txf() {
		return valorPresupuestado_txf.getText();
	}

	public void setValorPresupuestado_txf(String valorPresupuestado) {
		this.valorPresupuestado_txf.setText(valorPresupuestado);
	}

	public String getNroTecnico_txf() {
		return nroTecnico_txf.getText();
	}

	public void setNroTecnico_txf(String nroTecnico) {
		this.nroTecnico_txf.setText(nroTecnico);
	}

	public JTable getTablaComponentes() {
		return tablaComponentes;
	}

	public void setCantidad_lbl(int cantidad) {
		this.cantidad_lbl.setText(cantidad + "");
	}

	public JButton getIconIngreso_btn() {
		return iconIngreso_btn;
	}

	public JButton getIconMas_btn() {
		return iconMas_btn;
	}

	public JButton getIconMenos_btn() {
		return iconMenos_btn;
	}
}
