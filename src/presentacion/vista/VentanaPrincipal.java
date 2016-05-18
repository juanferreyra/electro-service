package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable ordenesDeTrabajo_table;
	private JButton ingresarProducto_btn;
	private JButton presupuestar_btn;
	private JButton reparacion_btn;
	private JButton btnElaborarHojaDe;
	private JMenuBar menuBar;
	private FormatoTablaOrdenesTrabajo formatoTabla;

	@SuppressWarnings("serial")
	public VentanaPrincipal() {
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1044, 546);
		setMinimumSize(new Dimension(1046, 546));

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());

		JPanel contenedorPanelArriba = new JPanel(new BorderLayout());
		FlowLayout flowPanelArriba = new FlowLayout();
		JPanel subpanelArriba = new JPanel(flowPanelArriba);
		subpanelArriba.setBackground(Color.WHITE);
		subpanelArriba.setForeground(Color.WHITE);

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1028, 50);

		JLabel labelLOGO = new JLabel("");
		labelLOGO.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/logo.png")));
		labelLOGO.setBounds(10, 11, 240, 140);

		JLabel ordenesDeTrabajo_lbl = new JLabel("<html>\u00D3rdenes de Trabajo</html>");
		ordenesDeTrabajo_lbl.setBounds(10, 43, 1005, 36);
		ordenesDeTrabajo_lbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		ordenesDeTrabajo_lbl.setHorizontalAlignment(SwingConstants.CENTER);

		flowPanelArriba.setAlignment(FlowLayout.LEADING);

		subpanelArriba.add(labelLOGO);
		subpanelArriba.add(ordenesDeTrabajo_lbl);

		contenedorPanelArriba.add(subpanelArriba, BorderLayout.SOUTH);
		contenedorPanelArriba.add(menuBar, BorderLayout.NORTH);
		contentPane.add(contenedorPanelArriba, BorderLayout.NORTH);

		// TABLA PRINCIPAL DEL SISTEMA

		ordenesDeTrabajo_table = new JTable();
		ordenesDeTrabajo_table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {}, new String[] { "", "Número", "Fecha",
				"Producto", "Cliente", "Envío", "", "Técnico Asignado", "Estado" }) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { JLabel.class, Integer.class, String.class, String.class, String.class,
					String.class, JLabel.class, String.class, String.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			@Override
			public boolean isCellEditable(int fila, int columna) {

				return false;
			}
		};
		ordenesDeTrabajo_table.setModel(modelo);

		setearPropiedadesDeTabla();

		ordenesDeTrabajo_table.setDefaultRenderer(JLabel.class, new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean selected,
					boolean focused, int row, int column) {
				return (Component) value;
			}
		});

		ordenesDeTrabajo_table.setBounds(new Rectangle(0, 0, 992, 301));

		JScrollPane ordenesDeTrabajo_scrollPane = new JScrollPane();
		ordenesDeTrabajo_scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ordenesDeTrabajo_scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		ordenesDeTrabajo_scrollPane.setBounds(10, 134, 1005, 301);
		ordenesDeTrabajo_scrollPane.add(ordenesDeTrabajo_table);
		ordenesDeTrabajo_scrollPane.setViewportView(ordenesDeTrabajo_table);
		contentPane.add(ordenesDeTrabajo_scrollPane, BorderLayout.CENTER);

		JPanel panelDeBotones = new JPanel(new FlowLayout());
		panelDeBotones.setBackground(Color.WHITE);
		contentPane.add(panelDeBotones, BorderLayout.SOUTH);

		ingresarProducto_btn = new JButton("<html><center>Crear</center></html>");
		ingresarProducto_btn.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/pencil.png")));
		ingresarProducto_btn.setBounds(10, 461, 158, 36);
		panelDeBotones.add(ingresarProducto_btn);

		presupuestar_btn = new JButton("<html>Presupuestar</html>");
		presupuestar_btn.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/th-list-outline.png")));
		presupuestar_btn.setBounds(587, 474,  158, 36);
		panelDeBotones.add(presupuestar_btn);

		reparacion_btn = new JButton("Finalizar Reparaci\u00F3n");
		reparacion_btn.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/spanner.png")));
		reparacion_btn.setBounds(881, 474,  158, 36);
		panelDeBotones.add(reparacion_btn);
		
		btnElaborarHojaDe = new JButton("Elaborar Hoja de Ruta");
		btnElaborarHojaDe.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/edit.png")));
		panelDeBotones.add(btnElaborarHojaDe);

	}

	private void setearPropiedadesDeTabla() {
		ordenesDeTrabajo_table.getColumnModel().getColumn(0).setMaxWidth(10);
		ordenesDeTrabajo_table.getColumnModel().getColumn(6).setMaxWidth(10);
		formatoTabla = new FormatoTablaOrdenesTrabajo();
		ordenesDeTrabajo_table.setDefaultRenderer(Object.class, formatoTabla);
		ordenesDeTrabajo_table.getTableHeader().setResizingAllowed(false);
		ordenesDeTrabajo_table.getTableHeader().setReorderingAllowed(false);
		ordenesDeTrabajo_table.getColumnModel().getColumn(1).setCellRenderer(formatoTabla);
		ordenesDeTrabajo_table.getColumnModel().getColumn(5).setCellRenderer(formatoTabla);

	}

	public JButton getIngresarProducto_btn() {
		return this.ingresarProducto_btn;
	}

	public JButton getPresupuestar_btn() {
		return this.presupuestar_btn;
	}

	public JButton getReparacion_btn() {
		return this.reparacion_btn;
	}

	public JMenuBar getMenu() {
		return this.menuBar;
	}

	public JTable getOrdenesDeTrabajo_table() {
		return this.ordenesDeTrabajo_table;
	}

	public JButton getBtnElaborarHojaDe() {
		return btnElaborarHojaDe;
	}

}
