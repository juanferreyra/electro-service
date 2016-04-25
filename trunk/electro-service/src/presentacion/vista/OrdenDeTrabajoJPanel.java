/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.vista;

/**
 *
 * @author pc
 */
public class OrdenDeTrabajoJPanel extends javax.swing.JPanel {

    /**
     * Creates new form OrdenDeTrabajoJPanel
     */
    public OrdenDeTrabajoJPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ordenDeTrabajo_lbl = new javax.swing.JLabel();
        id_OrdenDeTrabajo_lbl = new javax.swing.JLabel();
        fecha_OrdenDeTrabajo_lbl = new javax.swing.JLabel();
        fecha_OrdenDeTrabajo_txf = new javax.swing.JTextField();
        id_OrdenDeTrabajo_txf = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        nroCliente_lbl = new javax.swing.JLabel();
        producto_lbl = new javax.swing.JLabel();
        marca_lbl = new javax.swing.JLabel();
        marca_jCBox = new javax.swing.JComboBox<>();
        tipoProducto_lbl = new javax.swing.JLabel();
        tipoProducto_jCBox = new javax.swing.JComboBox<>();
        descripcionFalla_jScrollPane = new javax.swing.JScrollPane();
        descripcionFalla_jTextArea = new javax.swing.JTextArea();
        generarDocumentos_Btn = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        ordenDeTrabajo_lbl.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ordenDeTrabajo_lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ordenDeTrabajo_lbl.setText("Orden de Trabajo");

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

        descripcionFalla_jTextArea.setColumns(20);
        descripcionFalla_jTextArea.setRows(5);
        descripcionFalla_jScrollPane.setViewportView(descripcionFalla_jTextArea);

        generarDocumentos_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/icons/document-text.png"))); // NOI18N
        generarDocumentos_Btn.setText("Generar Documentos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ordenDeTrabajo_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(id_OrdenDeTrabajo_lbl)
                                .addGap(22, 22, 22)
                                .addComponent(id_OrdenDeTrabajo_txf, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fecha_OrdenDeTrabajo_lbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fecha_OrdenDeTrabajo_txf, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(144, 144, 144)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(nroCliente_lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(producto_lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(marca_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(marca_jCBox, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(tipoProducto_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(tipoProducto_jCBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(descripcionFalla_jScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 873, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(338, 338, 338)
                .addComponent(generarDocumentos_Btn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(id_OrdenDeTrabajo_lbl)
                            .addComponent(id_OrdenDeTrabajo_txf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fecha_OrdenDeTrabajo_txf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fecha_OrdenDeTrabajo_lbl)))
                    .addComponent(ordenDeTrabajo_lbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nroCliente_lbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(producto_lbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(marca_lbl)
                    .addComponent(marca_jCBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoProducto_lbl)
                    .addComponent(tipoProducto_jCBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(descripcionFalla_jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(generarDocumentos_Btn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane descripcionFalla_jScrollPane;
    private javax.swing.JTextArea descripcionFalla_jTextArea;
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
    // End of variables declaration//GEN-END:variables
}
