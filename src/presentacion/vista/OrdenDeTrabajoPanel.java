package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

public class OrdenDeTrabajoPanel extends JPanel {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private javax.swing.JScrollPane descripcionFalla_jScrollPane;
	    private javax.swing.JLabel fecha_OrdenDeTrabajo_lbl;
	    private javax.swing.JTextField fecha_OrdenDeTrabajo_txf;
	    private javax.swing.JButton generarDocumentos_Btn;
	    private javax.swing.JLabel id_OrdenDeTrabajo_lbl;
	    private javax.swing.JTextField id_OrdenDeTrabajo_txf;
	    private javax.swing.JSeparator jSeparator1;
	    private javax.swing.JComboBox<String> marca_jCBox;
	    private javax.swing.JLabel marca_lbl;
	    private javax.swing.JLabel nroCliente_lbl;
	    private javax.swing.JLabel ordenDeTrabajo_lbl;
	    private javax.swing.JLabel producto_lbl;
	    private javax.swing.JComboBox<String> tipoProducto_jCBox;
	    private javax.swing.JLabel tipoProducto_lbl;
	/**
	 * Create the panel.
	 */
	public OrdenDeTrabajoPanel() {
		ordenDeTrabajo_lbl = new javax.swing.JLabel();
		ordenDeTrabajo_lbl.setBounds(150, 17, 508, 28);
        id_OrdenDeTrabajo_lbl = new javax.swing.JLabel();
        id_OrdenDeTrabajo_lbl.setBounds(731, 11, 17, 14);
        fecha_OrdenDeTrabajo_lbl = new javax.swing.JLabel();
        fecha_OrdenDeTrabajo_lbl.setBounds(731, 31, 29, 14);
        fecha_OrdenDeTrabajo_txf = new javax.swing.JTextField();
        fecha_OrdenDeTrabajo_txf.setBounds(802, 31, 83, 14);
        id_OrdenDeTrabajo_txf = new javax.swing.JTextField();
        id_OrdenDeTrabajo_txf.setBounds(802, 11, 83, 14);
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator1.setBounds(10, 56, 873, 11);
        nroCliente_lbl = new javax.swing.JLabel();
        nroCliente_lbl.setBounds(142, 73, 53, 14);
        producto_lbl = new javax.swing.JLabel();
        producto_lbl.setBounds(142, 96, 53, 14);
        marca_lbl = new javax.swing.JLabel();
        marca_lbl.setBounds(142, 124, 53, 14);
        marca_jCBox = new javax.swing.JComboBox<>();
        marca_jCBox.setBounds(199, 121, 163, 20);
        tipoProducto_lbl = new javax.swing.JLabel();
        tipoProducto_lbl.setBounds(397, 124, 76, 14);
        tipoProducto_jCBox = new javax.swing.JComboBox<>();
        tipoProducto_jCBox.setBounds(491, 121, 183, 20);
        descripcionFalla_jScrollPane = new javax.swing.JScrollPane();
        descripcionFalla_jScrollPane.setBounds(142, 163, 606, 181);
        generarDocumentos_Btn = new javax.swing.JButton();
        generarDocumentos_Btn.setBounds(325, 355, 161, 33);

        setBackground(new java.awt.Color(255, 255, 255));

        ordenDeTrabajo_lbl.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ordenDeTrabajo_lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ordenDeTrabajo_lbl.setText("ORDEN DE TRABAJO");

        id_OrdenDeTrabajo_lbl.setText("Nro");

        fecha_OrdenDeTrabajo_lbl.setText("Fecha");

        fecha_OrdenDeTrabajo_txf.setBorder(null);
        fecha_OrdenDeTrabajo_txf.setEnabled(false);
        fecha_OrdenDeTrabajo_txf.setOpaque(false);

        id_OrdenDeTrabajo_txf.setBorder(null);
        id_OrdenDeTrabajo_txf.setEnabled(false);
        id_OrdenDeTrabajo_txf.setOpaque(false);

        jSeparator1.setBackground(new java.awt.Color(0, 102, 255));

        nroCliente_lbl.setText("Nro Cliente");

        producto_lbl.setText("Producto");

        marca_lbl.setText("Marca");

        marca_jCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tipoProducto_lbl.setText("Tipo Producto");

        tipoProducto_jCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        descripcionFalla_jScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descripción de Falla", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 0)));
        descripcionFalla_jScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        descripcionFalla_jScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        generarDocumentos_Btn.setIcon(new ImageIcon("C:\\REPO-LABORATORIO\\electro-service\\recursos\\icons\\document-text.png")); // NOI18N
        generarDocumentos_Btn.setText("Generar Documentos");
        setLayout(null);
        add(ordenDeTrabajo_lbl);
        add(id_OrdenDeTrabajo_lbl);
        add(id_OrdenDeTrabajo_txf);
        add(fecha_OrdenDeTrabajo_lbl);
        add(fecha_OrdenDeTrabajo_txf);
        add(nroCliente_lbl);
        add(producto_lbl);
        add(marca_lbl);
        add(marca_jCBox);
        add(tipoProducto_lbl);
        add(tipoProducto_jCBox);
        add(descripcionFalla_jScrollPane);
        
        JTextArea textArea = new JTextArea();
        textArea.setRows(5);
        textArea.setColumns(20);
        descripcionFalla_jScrollPane.setViewportView(textArea);
        add(jSeparator1);
        add(generarDocumentos_Btn);
	}
}
