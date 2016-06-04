package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import presentacion.controlador.ControladorABMCliente;
import presentacion.controlador.ControladorABMFlete;
import presentacion.controlador.ControladorABMMarcaProducto;
import presentacion.controlador.ControladorABMProveedor;
import presentacion.controlador.ControladorABMRepuesto;
import presentacion.controlador.ControladorABMTipoProducto;
import presentacion.controlador.ControladorVentanaABMUsuario;
import presentacion.controlador.ControladorVentanaStock;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

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
	private JButton btnOrdenDeCompra;
	private JLabel avisoFaltanteLabel;
	private JPanel panelAviso;

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
		flowPanelArriba.setAlignment(FlowLayout.LEADING);

		JPanel subpanelArriba = new JPanel(flowPanelArriba);
		subpanelArriba.setBackground(Color.WHITE);
		subpanelArriba.setForeground(Color.WHITE);

		// MENU PRINCIPAL
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1028, 50);

		JMenu ABMsMenu = new JMenu("ABM");
		menuBar.add(ABMsMenu);
		JMenu stockMenu = new JMenu("Stock");
		menuBar.add(stockMenu);

		JMenuItem stockItem = new JMenuItem("Control de stock");
		stockItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaStock stock = new VentanaStock();
				ControladorVentanaStock c = new ControladorVentanaStock(stock);
				c.inicializar();
			}
		});
		stockMenu.add(stockItem);

		JMenuItem ABMCliente = new JMenuItem("ABM Cliente");
		ABMCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaABMCliente abm = new VentanaABMCliente();
				ControladorABMCliente c = new ControladorABMCliente(abm);
				c.inicializar();
			}
		});
		ABMsMenu.add(ABMCliente);

		JMenuItem ABMFlete = new JMenuItem("ABM Flete");
		ABMFlete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaABMFlete abm = new VentanaABMFlete();
				ControladorABMFlete c = new ControladorABMFlete(abm);
				c.inicializar();
			}
		});
		ABMsMenu.add(ABMFlete);

		JMenuItem ABMMarcaProducto = new JMenuItem("ABM Marca Producto");
		ABMMarcaProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaABMMarcaProducto abm = new VentanaABMMarcaProducto();
				ControladorABMMarcaProducto c = new ControladorABMMarcaProducto(abm);
				c.inicializar();
			}
		});
		ABMsMenu.add(ABMMarcaProducto);

		JMenuItem ABMProveedor = new JMenuItem("ABM Proveedor");
		ABMProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaABMProveedor abm = new VentanaABMProveedor();
				ControladorABMProveedor c = new ControladorABMProveedor(abm);
				c.inicializar();
			}
		});
		ABMsMenu.add(ABMProveedor);

		JMenuItem ABMRepuesto = new JMenuItem("ABM Repuesto");
		ABMRepuesto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaABMRepuesto abm = new VentanaABMRepuesto();
				ControladorABMRepuesto c = new ControladorABMRepuesto(abm);
				c.inicializar();
			}
		});
		ABMsMenu.add(ABMRepuesto);

		JMenuItem ABMUsuario = new JMenuItem("ABM Usuario");
		ABMUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaABMUsuario abm = new VentanaABMUsuario();
				ControladorVentanaABMUsuario c = new ControladorVentanaABMUsuario(abm);
				c.inicializar();
			}
		});
		ABMsMenu.add(ABMUsuario);

		JMenuItem ABMTipoProducto = new JMenuItem("ABMTipoProducto");
		ABMTipoProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaABMTipoProducto abm = new VentanaABMTipoProducto();
				ControladorABMTipoProducto c = new ControladorABMTipoProducto(abm);
				c.inicializar();
			}
		});
		ABMsMenu.add(ABMTipoProducto);
		// FIN MENU PRINCIPAL

		JLabel labelLOGO = new JLabel("");
		labelLOGO.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/logo.png")));
		// labelLOGO.setBounds(10, 11, 140, 70);

		JLabel ordenesDeTrabajo_lbl = new JLabel("<html>\u00D3rdenes de Trabajo</html>");
		// ordenesDeTrabajo_lbl.setBounds(10, 43, 1005, 36);
		ordenesDeTrabajo_lbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		ordenesDeTrabajo_lbl.setHorizontalAlignment(SwingConstants.CENTER);

		subpanelArriba.add(labelLOGO);
		subpanelArriba.add(ordenesDeTrabajo_lbl);

		contenedorPanelArriba.add(subpanelArriba, BorderLayout.SOUTH);
		contenedorPanelArriba.add(menuBar, BorderLayout.NORTH);
		contentPane.add(contenedorPanelArriba, BorderLayout.NORTH);

		// TABLA PRINCIPAL DEL SISTEMA

		ordenesDeTrabajo_table = new JTable();
		ordenesDeTrabajo_table.setForeground(Color.DARK_GRAY);
		ordenesDeTrabajo_table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ordenesDeTrabajo_table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

		DefaultTableModel modeloOrdenesTrabajo = new DefaultTableModel(new Object[][] {}, new String[] { "", "Número",
				"Fecha", "Producto", "Cliente", "Envío", "", "Técnico Asignado", "Estado" }) {
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
		ordenesDeTrabajo_table.setRowHeight(25);

		ordenesDeTrabajo_table.setModel(modeloOrdenesTrabajo);

		setearPropiedadesDeTabla();

		ordenesDeTrabajo_table.setDefaultRenderer(JLabel.class, new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean selected,
					boolean focused, int row, int column) {
				return (Component) value;
			}
		});

		JScrollPane ordenesDeTrabajo_scrollPane = new JScrollPane();
		ordenesDeTrabajo_scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ordenesDeTrabajo_scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		ordenesDeTrabajo_scrollPane.setBounds(10, 134, 1005, 301);
		ordenesDeTrabajo_scrollPane.add(ordenesDeTrabajo_table);
		ordenesDeTrabajo_scrollPane.setViewportView(ordenesDeTrabajo_table);

		// LABEL DE AVISO DE FALTANTE

		panelAviso = new JPanel();
		panelAviso.setMaximumSize(new Dimension(2000, 50));
		avisoFaltanteLabel = new JLabel(
				"\u00A1 Atenci\u00F3n !  Se han terminado los repuestos de botones WTG negros.");// HARDCODEO
		avisoFaltanteLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelAviso.add(avisoFaltanteLabel);
		this.actualizarAvisoFaltante();

		// AGREGO LABEL AVISO FALTANTE Y TABLA ORDENES DE TRABAJO EN UN PANEL
		// CENTRAL
		JPanel contenedorCentral = new JPanel();
		contenedorCentral.setLayout(new BoxLayout(contenedorCentral, BoxLayout.PAGE_AXIS));

		contenedorCentral.add(panelAviso);
		contenedorCentral.add(ordenesDeTrabajo_scrollPane);

		contentPane.add(contenedorCentral, BorderLayout.CENTER);

		// FIN CONTENEDOR CENTRAL

		JPanel panelDeBotones = new JPanel(new FlowLayout());
		panelDeBotones.setBackground(Color.WHITE);
		contentPane.add(panelDeBotones, BorderLayout.SOUTH);

		ingresarProducto_btn = new JButton("<html><center>Crear</center></html>");
		ingresarProducto_btn.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/pencil.png")));
		ingresarProducto_btn.setBounds(10, 461, 158, 36);
		panelDeBotones.add(ingresarProducto_btn);

		presupuestar_btn = new JButton("<html>Presupuestar</html>");
		presupuestar_btn.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/th-list-outline.png")));
		presupuestar_btn.setBounds(587, 474, 158, 36);
		panelDeBotones.add(presupuestar_btn);

		reparacion_btn = new JButton("Finalizar Reparaci\u00F3n");
		reparacion_btn.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/spanner.png")));
		reparacion_btn.setBounds(881, 474, 158, 36);
		panelDeBotones.add(reparacion_btn);

		btnElaborarHojaDe = new JButton("Elaborar Hoja de Ruta");
		btnElaborarHojaDe.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/edit.png")));
		panelDeBotones.add(btnElaborarHojaDe);

		btnOrdenDeCompra = new JButton("Elaborar Orden de Compra");
		btnOrdenDeCompra.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/upload.png")));
		panelDeBotones.add(btnOrdenDeCompra);

	}

	private void actualizarAvisoFaltante() {
		panelAviso.setBackground(Color.decode("#F6D8CE"));
		panelAviso.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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

	public JButton getBtnOrdenDeCompra() {
		return btnOrdenDeCompra;
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

	public String getAvisoFaltanteLabel() {
		return avisoFaltanteLabel.getText();
	}

	public void setAvisoFaltanteLabel(String aviso) {
		this.avisoFaltanteLabel.setText(aviso);
	}

}
