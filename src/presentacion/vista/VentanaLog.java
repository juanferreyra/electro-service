package presentacion.vista;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class VentanaLog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablaLog;
	private DefaultTableModel modelLog;
	private JFrame frame;
	private String[] nombreColumnas = { "Estado", "Fecha/Hora" };
	private JLabel numeroOrden_label;

	public VentanaLog() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPresupuesto.class.getResource("/logo.png")));

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 745, 356);

		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.getContentPane().add(contentPane);

		JLabel lblLogPresupuesto = new JLabel("Historial Orden de Trabajo");
		lblLogPresupuesto.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogPresupuesto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLogPresupuesto.setBounds(10, 11, 709, 26);
		contentPane.add(lblLogPresupuesto);
		frame.setLocationRelativeTo(null);

		JScrollPane log_scrollPane = new JScrollPane();
		log_scrollPane.setBounds(10, 82, 709, 233);
		getContentPane().add(log_scrollPane);

		modelLog = new DefaultTableModel(null, nombreColumnas) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int fila, int columna) {

				return false;
			}
		};
		tablaLog = new JTable(modelLog);
		tablaLog.getColumnModel().getColumn(0).setMaxWidth(0);
		tablaLog.getColumnModel().getColumn(0).setMinWidth(0);
		tablaLog.getColumnModel().getColumn(0).setPreferredWidth(0);
		log_scrollPane.setViewportView(tablaLog);

		numeroOrden_label = new JLabel("");
		numeroOrden_label.setHorizontalAlignment(SwingConstants.CENTER);
		numeroOrden_label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		numeroOrden_label.setBackground(new Color(70, 130, 180));
		numeroOrden_label.setBounds(10, 44, 709, 27);
		contentPane.add(numeroOrden_label);

		frame.setVisible(true);
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public JTable getTablaLog() {
		return tablaLog;
	}

	public void setTablaLog(JTable tablaLog) {
		this.tablaLog = tablaLog;
	}

	public DefaultTableModel getModelLog() {
		return modelLog;
	}

	public void setModelLog(DefaultTableModel modelLog) {
		this.modelLog = modelLog;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public void setNombreColumnas(String[] nombreColumnas) {
		this.nombreColumnas = nombreColumnas;
	}

	public JLabel getNumeroOrden_label() {
		return numeroOrden_label;
	}

	public void setNumeroOrden_label(JLabel numeroOrden_label) {
		this.numeroOrden_label = numeroOrden_label;
	}

}
