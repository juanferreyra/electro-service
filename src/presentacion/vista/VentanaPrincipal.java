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

import java.awt.Color;
import java.awt.Component;
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

	@SuppressWarnings("serial")
	public VentanaPrincipal(UsuarioDTO user) {
		this.user = user;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1044, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1028, 21);
		contentPane.add(menuBar);

		JLabel ordenesDeTrabajo_lbl = new JLabel("<html><b>\u00D3rdenes de Trabajo</b></html>");
		ordenesDeTrabajo_lbl.setBounds(10, 43, 1005, 36);
		ordenesDeTrabajo_lbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ordenesDeTrabajo_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(ordenesDeTrabajo_lbl);

		// TABLA PRINCIPAL DEL SISTEMA

		ordenesDeTrabajo_table = new JTable();
		ordenesDeTrabajo_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		ordenesDeTrabajo_table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Orden", "Número",
				"Fecha", "Producto", "Cliente", "Env\u00EDo", "Presupuesto", "T\u00E9cnico Asignado", "Estado" }) {
			Class[] columnTypes = new Class[] { JButton.class, Integer.class, String.class, String.class, String.class,
					Boolean.class, JButton.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

		});
		ordenesDeTrabajo_table.setDefaultRenderer(Object.class, new FormatoTablaOrdenesTrabajo());

		ordenesDeTrabajo_table.getColumnModel().getColumn(0).setPreferredWidth(52);
		ordenesDeTrabajo_table.getColumnModel().getColumn(1).setPreferredWidth(80);
		ordenesDeTrabajo_table.getColumnModel().getColumn(2).setPreferredWidth(80);
		ordenesDeTrabajo_table.getColumnModel().getColumn(3).setPreferredWidth(200);
		ordenesDeTrabajo_table.getColumnModel().getColumn(4).setPreferredWidth(200);
		ordenesDeTrabajo_table.getColumnModel().getColumn(5).setPreferredWidth(45);
		ordenesDeTrabajo_table.getColumnModel().getColumn(6).setPreferredWidth(80);
		ordenesDeTrabajo_table.getColumnModel().getColumn(7).setPreferredWidth(200);
		ordenesDeTrabajo_table.getColumnModel().getColumn(8).setPreferredWidth(50);

		ordenesDeTrabajo_table.getTableHeader().setResizingAllowed(false);
		ordenesDeTrabajo_table.getTableHeader().setReorderingAllowed(false);

		ordenesDeTrabajo_table.getColumnModel().getColumn(1).setCellRenderer(new FormatoTablaOrdenesTrabajo());
		ordenesDeTrabajo_table.getColumnModel().getColumn(5).setCellRenderer(new FormatoTablaOrdenesTrabajo());

		ordenesDeTrabajo_table.setDefaultRenderer(JButton.class, new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado,
					boolean tieneElFoco, int fila, int columna) {

				return (Component) objeto;
			}
		});

		ordenesDeTrabajo_table.setBounds(new Rectangle(0, 0, 992, 301));
		JScrollPane ordenesDeTrabajo_scrollPane = new JScrollPane();
		ordenesDeTrabajo_scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ordenesDeTrabajo_scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		ordenesDeTrabajo_scrollPane.setBounds(10, 134, 1005, 301);
		ordenesDeTrabajo_scrollPane.add(ordenesDeTrabajo_table);
		ordenesDeTrabajo_scrollPane.setViewportView(ordenesDeTrabajo_table);

		contentPane.add(ordenesDeTrabajo_scrollPane);

		ingresarProducto_btn = new JButton("<html><center>Ingresar <br>\r\nOrden de Trabajo</center></html>");
		ingresarProducto_btn.setBounds(10, 461, 158, 36);
		contentPane.add(ingresarProducto_btn);

		presupuestar_btn = new JButton("Presupuestar");
		presupuestar_btn.setBounds(587, 474, 137, 23);
		contentPane.add(presupuestar_btn);

		asignarOrden_btn = new JButton("Asignar Orden");
		asignarOrden_btn.setBounds(734, 474, 137, 23);
		contentPane.add(asignarOrden_btn);
		reparacion_btn = new JButton("Reparaci\u00F3n");
		reparacion_btn.setBounds(881, 474, 137, 23);
		contentPane.add(reparacion_btn);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/logo.png")));
		label.setBounds(10, 11, 240, 140);
		contentPane.add(label);

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
