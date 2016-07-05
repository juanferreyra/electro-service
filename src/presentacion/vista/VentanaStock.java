package presentacion.vista;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class VentanaStock extends JFrame {

	private JPanel contentPane;
	private JTable stock_table;
	private FormatoTablaStock formatoTabla;
	private JButton generarOC_btn;
	private DefaultTableModel modelo;

	public VentanaStock() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 706, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPresentacion.class.getResource("/logo.png")));

		JLabel lblControlDeStock = new JLabel("Control de Stock");
		lblControlDeStock.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblControlDeStock.setHorizontalAlignment(SwingConstants.CENTER);
		lblControlDeStock.setBounds(10, 34, 670, 14);
		contentPane.add(lblControlDeStock);

		// TABLA STOCK

		stock_table = new JTable();
		stock_table.setSize(670, 288);
		stock_table.setLocation(10, 72);
		stock_table.setBounds(10, 93, 670, 271);
		stock_table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

		modelo = new DefaultTableModel(new Object[][] {},
				new String[] { "Marca", "Repuesto", "Existencias", "Reservado", "Pedido", "Disponible", "Minimo" }) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, Integer.class, Integer.class, Integer.class,
					Integer.class, Integer.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			@Override
			public boolean isCellEditable(int fila, int columna) {

				return false;
			}
		};

		stock_table.setModel(modelo);

		formatoTabla = new FormatoTablaStock();
		stock_table.setDefaultRenderer(Object.class, formatoTabla);

		JScrollPane ordenesDeTrabajo_scrollPane = new JScrollPane();
		ordenesDeTrabajo_scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ordenesDeTrabajo_scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		ordenesDeTrabajo_scrollPane.setBounds(10, 93, 670, 271);
		ordenesDeTrabajo_scrollPane.add(stock_table);
		ordenesDeTrabajo_scrollPane.setViewportView(stock_table);
		contentPane.add(ordenesDeTrabajo_scrollPane, BorderLayout.CENTER);

		generarOC_btn = new JButton("Generar orden de compra");
		generarOC_btn.setBounds(234, 395, 209, 23);
		contentPane.add(generarOC_btn);

	}

	public JButton getGenerarOC_btn() {
		return generarOC_btn;
	}

	public void setGenerarOC_btn(JButton generarOC_btn) {
		this.generarOC_btn = generarOC_btn;
	}

	public DefaultTableModel getModelo() {
		return modelo;
	}

	public void setModelo(DefaultTableModel modelo) {
		this.modelo = modelo;
	}

}
