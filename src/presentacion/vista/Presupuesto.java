
package presentacion.vista;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Presupuesto extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;

	public Presupuesto() {
		initComponents();
	}

	@SuppressWarnings("serial")
	private void initComponents() {

		presupuesto_lbl = new javax.swing.JLabel();
		jSeparator1 = new javax.swing.JSeparator();
		fecha_Presupuesto_lbl = new javax.swing.JLabel();
		fecha_Presupuesto_txf = new javax.swing.JTextField();
		nroTecnico_lbl = new javax.swing.JLabel();
		nroTecnico_txf = new javax.swing.JTextField();
		horasTrabajo_lbl = new javax.swing.JLabel();
		horasTrabajo_txf = new javax.swing.JTextField();
		componentes_lbl = new javax.swing.JLabel();
		componentes_jComboBox = new javax.swing.JComboBox<>();
		cantidad_lbl = new javax.swing.JLabel();
		iconMas_lbl = new javax.swing.JLabel();
		iconMenos_lbl = new javax.swing.JLabel();
		iconIngresar_lbl = new javax.swing.JLabel();
		listaComponentesSeleccionados_jScrollPane = new javax.swing.JScrollPane();
		listaComponentesSeleccionados_jList = new javax.swing.JList<>();
		descripcionBreve_lbl = new javax.swing.JLabel();
		descripcionTecnica_lbl = new javax.swing.JLabel();
		descripcionTecnica_jScrollPane = new javax.swing.JScrollPane();
		descripcionTecnica_jTextArea = new javax.swing.JTextArea();
		descripcionBreve_jScrollPane = new javax.swing.JScrollPane();
		descripcionBreve_jTextArea = new javax.swing.JTextArea();
		valorPresupuestado_lbl = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();

		setBackground(new java.awt.Color(255, 255, 255));

		presupuesto_lbl.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		presupuesto_lbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		presupuesto_lbl.setText("Presupuesto");

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
		listaComponentesSeleccionados_jScrollPane.setViewportView(listaComponentesSeleccionados_jList);

		descripcionBreve_lbl.setText("Descripci\u00F3n Breve ");

		descripcionTecnica_lbl.setText("Descripci\u00F3n T\u00E9cnica");

		descripcionTecnica_jTextArea.setColumns(20);
		descripcionTecnica_jTextArea.setRows(5);
		descripcionTecnica_jScrollPane.setViewportView(descripcionTecnica_jTextArea);

		descripcionBreve_jTextArea.setColumns(20);
		descripcionBreve_jTextArea.setRows(5);
		descripcionBreve_jScrollPane.setViewportView(descripcionBreve_jTextArea);

		valorPresupuestado_lbl.setText("<html><b>Valor Presupuestado</b></html>");

		jTextField1.setEditable(false);
		jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
		jTextField1.setOpaque(false);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 873, GroupLayout.PREFERRED_SIZE)
							.addGap(0, 1, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
							.addGap(0, 129, Short.MAX_VALUE)
							.addComponent(presupuesto_lbl, GroupLayout.PREFERRED_SIZE, 508, GroupLayout.PREFERRED_SIZE)
							.addGap(115)
							.addComponent(fecha_Presupuesto_lbl)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(fecha_Presupuesto_txf, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(11, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup()
					.addGap(139)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addGroup(layout.createSequentialGroup()
									.addComponent(nroTecnico_lbl)
									.addGap(37))
								.addGroup(layout.createSequentialGroup()
									.addGroup(layout.createParallelGroup(Alignment.TRAILING)
										.addComponent(componentes_lbl, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
										.addComponent(horasTrabajo_lbl))
									.addPreferredGap(ComponentPlacement.UNRELATED)))
							.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(nroTecnico_txf)
								.addComponent(horasTrabajo_txf)
								.addComponent(componentes_jComboBox, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(iconMas_lbl)
							.addGap(4)
							.addComponent(cantidad_lbl, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(iconMenos_lbl)
							.addGap(10)
							.addComponent(iconIngresar_lbl))
						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
							.addGroup(layout.createSequentialGroup()
								.addComponent(valorPresupuestado_lbl, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
								.addGap(277))
							.addComponent(listaComponentesSeleccionados_jScrollPane, GroupLayout.PREFERRED_SIZE, 608, GroupLayout.PREFERRED_SIZE)
							.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
									.addComponent(descripcionBreve_jScrollPane, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)
									.addComponent(descripcionBreve_lbl))
								.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
									.addComponent(descripcionTecnica_lbl)
									.addComponent(descripcionTecnica_jScrollPane, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(148, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(presupuesto_lbl, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(fecha_Presupuesto_lbl)
						.addComponent(fecha_Presupuesto_txf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(nroTecnico_lbl)
						.addComponent(nroTecnico_txf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(horasTrabajo_lbl)
						.addComponent(horasTrabajo_txf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(componentes_lbl)
						.addComponent(componentes_jComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(iconMas_lbl, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(cantidad_lbl, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(iconMenos_lbl)
						.addComponent(iconIngresar_lbl))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(listaComponentesSeleccionados_jScrollPane, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(descripcionBreve_lbl)
						.addComponent(descripcionTecnica_lbl))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(descripcionTecnica_jScrollPane, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addComponent(descripcionBreve_jScrollPane, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(valorPresupuestado_lbl)
						.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		this.setLayout(layout);
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
	private javax.swing.JScrollPane listaComponentesSeleccionados_jScrollPane;
	private javax.swing.JLabel nroTecnico_lbl;
	private javax.swing.JTextField nroTecnico_txf;
	private javax.swing.JLabel presupuesto_lbl;
	private javax.swing.JLabel valorPresupuestado_lbl;
}
