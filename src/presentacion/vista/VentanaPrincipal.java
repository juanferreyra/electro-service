package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.table.DefaultTableModel;

import dto.UsuarioDTO;
import javax.swing.ImageIcon;

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
	private JButton verIngreso_btn;

	@SuppressWarnings("serial")
	public VentanaPrincipal(UsuarioDTO user) {
		this.user = user;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1044, 518);
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
		ordenesDeTrabajo_lbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ordenesDeTrabajo_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(ordenesDeTrabajo_lbl);

		// TABLA PRINCIPAL DEL SISTEMA

		ordenesDeTrabajo_table = new JTable();
		ordenesDeTrabajo_table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "", "Nro.", "Fecha",
				"Producto", "Cliente", "Env\u00EDo", "Presupuesto", "T\u00E9cnico Asignado", "Estado" }) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { JButton.class, Integer.class, String.class, String.class, String.class,
					Boolean.class, JButton.class, String.class, String.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		ordenesDeTrabajo_table.setBounds(new Rectangle(0, 0, 992, 301));
		JScrollPane ordenesDeTrabajo_scrollPane = new JScrollPane();
		ordenesDeTrabajo_scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ordenesDeTrabajo_scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		ordenesDeTrabajo_scrollPane.setBounds(10, 134, 1005, 301);
		ordenesDeTrabajo_scrollPane.add(ordenesDeTrabajo_table);
		ordenesDeTrabajo_scrollPane.setViewportView(ordenesDeTrabajo_table);

		contentPane.add(ordenesDeTrabajo_scrollPane);

		ingresarProducto_btn = new JButton("Ingresar Producto");
		ingresarProducto_btn.setBounds(157, 446, 137, 23);
		contentPane.add(ingresarProducto_btn);

		presupuestar_btn = new JButton("Presupuestar");
		presupuestar_btn.setBounds(426, 446, 137, 23);
		contentPane.add(presupuestar_btn);

		asignarOrden_btn = new JButton("Asignar Orden");
		asignarOrden_btn.setBounds(573, 446, 137, 23);
		contentPane.add(asignarOrden_btn);
		reparacion_btn = new JButton("Reparaci\u00F3n");
		reparacion_btn.setBounds(720, 446, 137, 23);
		contentPane.add(reparacion_btn);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/logo.png")));
		label.setBounds(10, 11, 240, 140);
		contentPane.add(label);

		verIngreso_btn = new JButton("Ver Ingreso");
		verIngreso_btn.setBounds(10, 446, 137, 23);
		contentPane.add(verIngreso_btn);
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

	public JButton getVerIngreso_btn() {
		return this.verIngreso_btn;
	}

}
