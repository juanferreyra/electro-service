
package presentacion.vista;
import java.awt.SystemColor;

public class PresupuestoPanel extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;

	public PresupuestoPanel() {
		initComponents();
	}

	@SuppressWarnings("serial")
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
		componentes_lbl.setBounds(139, 121, 83, 14);
		componentes_jComboBox = new javax.swing.JComboBox<>();
		componentes_jComboBox.setBounds(266, 118, 152, 20);
		cantidad_lbl = new javax.swing.JLabel();
		cantidad_lbl.setBounds(464, 121, 26, 24);
		iconMas_lbl = new javax.swing.JLabel();
		iconMas_lbl.setBounds(430, 121, 24, 24);
		iconMenos_lbl = new javax.swing.JLabel();
		iconMenos_lbl.setBounds(496, 121, 24, 24);
		iconIngresar_lbl = new javax.swing.JLabel();
		iconIngresar_lbl.setBounds(530, 121, 24, 24);
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
		jTextField1 = new javax.swing.JTextField();
		jTextField1.setBackground(SystemColor.text);
		jTextField1.setBounds(266, 348, 479, 16);

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
		cantidad_lbl.setText("0");
		cantidad_lbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

		iconMas_lbl.setIcon(new javax.swing.ImageIcon(
				"C:\\Users\\pc\\Documents\\NetBeansProjects\\electro-service\\DesarrolloExtra-ElectroService\\src\\desarrolloextra\\icons\\plus-outline.png")); // NOI18N

		iconMenos_lbl.setIcon(new javax.swing.ImageIcon(
				"C:\\Users\\pc\\Documents\\NetBeansProjects\\electro-service\\DesarrolloExtra-ElectroService\\src\\desarrolloextra\\icons\\minus-outline.png")); // NOI18N

		iconIngresar_lbl.setIcon(new javax.swing.ImageIcon(
				"C:\\Users\\pc\\Documents\\NetBeansProjects\\electro-service\\DesarrolloExtra-ElectroService\\src\\desarrolloextra\\icons\\tick-outline.png")); // NOI18N

		descripcionBreve_lbl.setText("Descripci\u00F3n Breve ");

		descripcionTecnica_lbl.setText("Descripci\u00F3n T\u00E9cnica");

		descripcionBreve_jTextArea.setColumns(20);
		descripcionBreve_jTextArea.setRows(5);
		descripcionBreve_jScrollPane.setViewportView(descripcionBreve_jTextArea);

		valorPresupuestado_lbl.setText("<html><b>Valor Presupuestado</b></html>");

		jTextField1.setEditable(false);
		jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
		jTextField1.setOpaque(false);
		listaComponentesSeleccionados_jList = new javax.swing.JList<>();
		listaComponentesSeleccionados_jList.setBounds(139, 151, 606, 109);
		
				listaComponentesSeleccionados_jList
						.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)));
				listaComponentesSeleccionados_jList.setModel(new javax.swing.AbstractListModel<String>() {
					String[] strings = { "Nombre Cantidad", "item3 item4", "item5 item6" };

					public int getSize() {
						return strings.length;
					}

					public String getElementAt(int i) {
						return strings[i];
					}
				});
		descripcionTecnica_jTextArea = new javax.swing.JTextArea();
		descripcionTecnica_jScrollPane.setViewportView(descripcionTecnica_jTextArea);
		
				descripcionTecnica_jTextArea.setColumns(20);
				descripcionTecnica_jTextArea.setRows(5);
		setLayout(null);
		add(listaComponentesSeleccionados_jList);
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
		add(iconMas_lbl);
		add(cantidad_lbl);
		add(iconMenos_lbl);
		add(iconIngresar_lbl);
		add(valorPresupuestado_lbl);
		add(jTextField1);
		add(descripcionBreve_jScrollPane);
		add(descripcionBreve_lbl);
		add(descripcionTecnica_lbl);
		add(descripcionTecnica_jScrollPane);
	}

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
	private javax.swing.JLabel iconIngresar_lbl;
	private javax.swing.JLabel iconMas_lbl;
	private javax.swing.JLabel iconMenos_lbl;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JList<String> listaComponentesSeleccionados_jList;
	private javax.swing.JLabel nroTecnico_lbl;
	private javax.swing.JTextField nroTecnico_txf;
	private javax.swing.JLabel presupuesto_lbl;
	private javax.swing.JLabel valorPresupuestado_lbl;
}
