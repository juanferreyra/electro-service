package presentacion.vista;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.Date;
import java.awt.Color;

public class VentanaHojaDeRuta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable listaEnviosTable;

	@SuppressWarnings("serial")
	public VentanaHojaDeRuta() {
		setBackground(Color.WHITE);
		setTitle("Preparar Hoja de ruta");
		setAlwaysOnTop(true);
		contentPane = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		setMinimumSize(new Dimension(700, 546));

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel contenedorPanelArriba = new JPanel(new BorderLayout());
		contenedorPanelArriba.setBounds(5, 5, 674, 35);
		FlowLayout flowPanelArriba = new FlowLayout();
		JPanel subpanelArriba = new JPanel(flowPanelArriba);

		JLabel ordenesDeTrabajo_lbl = new JLabel("<html>Hoja de Ruta</html>");
		ordenesDeTrabajo_lbl.setBounds(10, 43, 1005, 36);
		ordenesDeTrabajo_lbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ordenesDeTrabajo_lbl.setHorizontalAlignment(SwingConstants.CENTER);

		flowPanelArriba.setAlignment(FlowLayout.LEADING);
		subpanelArriba.add(ordenesDeTrabajo_lbl);

		contenedorPanelArriba.add(subpanelArriba, BorderLayout.SOUTH);
		contentPane.add(contenedorPanelArriba);

		// TABLA PRINCIPAL DE CHECKBOX

		listaEnviosTable = new JTable();
		listaEnviosTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {}, new String[] { "Nº", "Fecha",
				"Producto", "Cliente",  "Direccion Envio", "Estado" }) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Integer.class, Date.class, String.class, String.class,
					String.class, JCheckBox.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			@Override
			public boolean isCellEditable(int fila, int columna) {

				return false;
			}
		};
		listaEnviosTable.setModel(modelo);
		
		setearPropiedadesDeTabla();

		listaEnviosTable.setDefaultRenderer(JCheckBox.class, new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean selected,
					boolean focused, int row, int column) {
				return (Component) value;
			}
		});

		listaEnviosTable.setBounds(new Rectangle(10, 10, 992, 301));

		JScrollPane ordenesDeTrabajo_scrollPane = new JScrollPane();
		ordenesDeTrabajo_scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ordenesDeTrabajo_scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		ordenesDeTrabajo_scrollPane.setBounds(5, 107, 679, 400);
		ordenesDeTrabajo_scrollPane.add(listaEnviosTable);
		ordenesDeTrabajo_scrollPane.setViewportView(listaEnviosTable);
		contentPane.add(ordenesDeTrabajo_scrollPane);

	}

	private void setearPropiedadesDeTabla() {
		listaEnviosTable.getColumnModel().getColumn(0).setMaxWidth(50);
		listaEnviosTable.getColumnModel().getColumn(1).setMaxWidth(200);
		listaEnviosTable.getTableHeader().setResizingAllowed(false);
		listaEnviosTable.getTableHeader().setReorderingAllowed(false);
	}

	public JTable getOrdenesDeTrabajo_table() {
		return this.listaEnviosTable;
	}
}
