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

import dto.UsuarioDTO;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable ordenesDeTrabajo_table;
	private UsuarioDTO user;
	private JButton ingresarProducto_btn;
	private JButton presupuestar_btn;
	private JButton asignarOrden_btn;
	private JButton reparacion_btn;
	private JMenuBar menuBar;
	private FormatoTablaOrdenesTrabajo formatoTabla;

	@SuppressWarnings("serial")
	public VentanaPrincipal(UsuarioDTO user) {
		contentPane = new JPanel();
		this.user = user;
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

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1028, 50);

		JLabel labelLOGO = new JLabel("");
		labelLOGO.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/logo.png")));
		labelLOGO.setBounds(10, 11, 240, 140);

		JLabel ordenesDeTrabajo_lbl = new JLabel("<html>\u00D3rdenes de Trabajo</html>");
		ordenesDeTrabajo_lbl.setBounds(10, 43, 1005, 36);
		ordenesDeTrabajo_lbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
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
				"Producto", "Cliente", "Env\u00EDo", "", "T\u00E9cnico Asignado", "Estado" }) {
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
		contentPane.add(panelDeBotones, BorderLayout.SOUTH);

		ingresarProducto_btn = new JButton("<html><center>Ingresar Producto</center></html>");
		ingresarProducto_btn.setBounds(10, 461, 158, 36);
		panelDeBotones.add(ingresarProducto_btn);

		presupuestar_btn = new JButton("Presupuestar");
		presupuestar_btn.setBounds(587, 474, 137, 23);
		panelDeBotones.add(presupuestar_btn);

		asignarOrden_btn = new JButton("Asignar Orden");
		asignarOrden_btn.setBounds(734, 474, 137, 23);
		panelDeBotones.add(asignarOrden_btn);

		reparacion_btn = new JButton("Reparaci\u00F3n");
		reparacion_btn.setBounds(881, 474, 137, 23);
		panelDeBotones.add(reparacion_btn);

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

	public UsuarioDTO getUser() {
		return user;
	}

	public JButton getIngresarProducto_btn() {
		return this.ingresarProducto_btn;
	}

	public JButton getPresupuestar_btn() {
		return this.presupuestar_btn;
	}

	public JButton getAsignarOrden_btn() {
		return this.asignarOrden_btn;
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

}
