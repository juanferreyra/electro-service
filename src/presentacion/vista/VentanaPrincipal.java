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
import java.awt.Toolkit;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import presentacion.controlador.ControladorABMCliente;
import presentacion.controlador.ControladorABMFlete;
import presentacion.controlador.ControladorABMMarcaProducto;
import presentacion.controlador.ControladorABMProveedor;
import presentacion.controlador.ControladorABMRepuesto;
import presentacion.controlador.ControladorABMTipoProducto;
import presentacion.controlador.ControladorVentanaABMUsuario;
import presentacion.controlador.ControladorVentanaLogin;
import presentacion.controlador.ControladorVentanaStock;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import java.awt.Rectangle;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable ordenesDeTrabajo_table;
	private JButton ingresarProducto_btn;
	private JButton presupuestar_btn;
	private JButton reparacion_btn;
	private JButton btnElaborarHojaDe;
	private JMenuBar menuBar;
	private FormatoTablaOrdenesTrabajo formatoTablaOrdenTrabajo;
	private JButton btnOrdenDeCompra;
	private JTable table_avisoFaltante;
	private JScrollPane panelAviso;
	private JMenu _sistema;
	private JMenu _contabilidad;
	private JMenu _gestion;
	private JMenu _reporte;
	private JMenuItem deslogueo;
	private FormatoTablaAvisoFaltante formatoTablaAvisoFaltante;

	@SuppressWarnings("serial")
	public VentanaPrincipal() {

		setTitle("::: ELECTRO R SRL. ::: Software para Servicios Electrónicos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPresentacion.class.getResource("/logo.png")));

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1044, 546);
		setMinimumSize(new Dimension(1046, 546));

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());

		setContentPane(contentPane);

		// MENU PRINCIPAL
		menuBar = new JMenuBar();
		menuBar.setBorder(null);
		menuBar.setMinimumSize(new Dimension(0, 0));
		menuBar.setMaximumSize(new Dimension(0, 0));
		menuBar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		menuBar.setBounds(0, 0, 1028, 50);

		_sistema = new JMenu("Sistema");
		menuBar.add(_sistema);
		cargarItemsDeSistema();

		_gestion = new JMenu("Gestión de Datos");
		menuBar.add(_gestion);
		cargarItemsDeGestion();

		_contabilidad = new JMenu("Contabilidad");
		menuBar.add(_contabilidad);
		cargarItemsContabilidad();

		_reporte = new JMenu("Reportes");
		menuBar.add(_reporte);
		cargarItemsReporte();

		// FIN MENU

		JLabel ordenesDeTrabajo_lbl = new JLabel("<html>\u00D3rdenes de Trabajo</html>");
		ordenesDeTrabajo_lbl.setMinimumSize(new Dimension(1000, 50));
		ordenesDeTrabajo_lbl.setForeground(new Color(105, 105, 105));
		ordenesDeTrabajo_lbl.setFont(new Font("Tahoma", Font.PLAIN, 26));
		ordenesDeTrabajo_lbl.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel contenedorPanelArriba = new JPanel(new BorderLayout());

		JPanel subpanelArriba = new JPanel();
		subpanelArriba.setBackground(Color.WHITE);
		subpanelArriba.setForeground(Color.WHITE);
		subpanelArriba.add(ordenesDeTrabajo_lbl);

		contenedorPanelArriba.add(subpanelArriba, BorderLayout.SOUTH);
		contenedorPanelArriba.add(menuBar, BorderLayout.NORTH);
		contentPane.add(contenedorPanelArriba, BorderLayout.NORTH);

		// TABLA PRINCIPAL DEL SISTEMA

		ordenesDeTrabajo_table = new JTable();
		ordenesDeTrabajo_table.setForeground(Color.DARK_GRAY);
		ordenesDeTrabajo_table.setFont(new Font("Arial", Font.PLAIN, 17));
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
		ordenesDeTrabajo_table.setRowHeight(30);

		ordenesDeTrabajo_table.setModel(modeloOrdenesTrabajo);

		setearPropiedadesDeTablaOrdenesTrabajo();

		ordenesDeTrabajo_table.setDefaultRenderer(JLabel.class, new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean selected,
					boolean focused, int row, int column) {

				if (value.getClass() == JLabel.class) {
					((JLabel) value).setBackground(Color.red);
				}

				return (Component) value;
			}
		});

		JScrollPane ordenesDeTrabajo_scrollPane = new JScrollPane();
		ordenesDeTrabajo_scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ordenesDeTrabajo_scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		ordenesDeTrabajo_scrollPane.setBounds(10, 134, 1005, 301);
		ordenesDeTrabajo_scrollPane.add(ordenesDeTrabajo_table);
		ordenesDeTrabajo_scrollPane.setViewportView(ordenesDeTrabajo_table);

		// TABLA DE AVISO DE FALTANTE

		table_avisoFaltante = new JTable();

		DefaultTableModel modeloAviso = new DefaultTableModel(new Object[][] {}, new String[] { "Aviso de Faltante" }) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			@Override
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};

		table_avisoFaltante.setRowHeight(20);
		table_avisoFaltante.setModel(modeloAviso);

		panelAviso = new JScrollPane();
		panelAviso.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelAviso.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelAviso.setBounds(0, 0, 1005, 301);
		panelAviso.add(table_avisoFaltante);

		panelAviso.setViewportView(table_avisoFaltante);
		setearPropiedadesDeTablaAvisoFaltante();

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

	private void cargarItemsReporte() {
		JMenu estadisticos = new JMenu("Generar Reporte Estadístico");
		_reporte.add(estadisticos);

		JMenuItem venta = new JMenuItem("De ventas por fecha");
		venta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Abrir reporte ventas por fecha
			}
		});
		estadisticos.add(venta);

		JMenuItem reparado = new JMenuItem("De reparaciones");
		reparado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Abrir reporte reparaciones
			}
		});
		estadisticos.add(reparado);

		JMenuItem masInsumido = new JMenuItem("De repuestos más insumidos");
		masInsumido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Abrir reporte repuestos mas insumidos
			}
		});
		estadisticos.add(masInsumido);
		_reporte.addSeparator();
	}

	private void cargarItemsContabilidad() {

		JMenuItem stock = new JMenuItem("Control de Stock");
		stock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaStock stock = new VentanaStock();
				ControladorVentanaStock c = new ControladorVentanaStock(stock);
				c.inicializar();
			}
		});
		_contabilidad.add(stock);

		JMenuItem ordenCompra = new JMenuItem("Generar Orden de Compra");
		ordenCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// VentanaOrdenCompra ventanaOdenCompra = new
				// VentanaOrdenCompra();
				// ControladorOrdenCompra c = new
				// ControladorOrdenCompra(ventanaOdenCompra,usuario);
				// c.inicializar();
			}
		});
		_contabilidad.add(ordenCompra);
		_contabilidad.addSeparator();
	}

	private void cargarItemsDeGestion() {
		// PERSONAS
		JMenu personas = new JMenu("Gestionar Persona");
		_gestion.add(personas);

		JMenuItem cliente = new JMenuItem("Cliente");
		cliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaABMCliente abm = new VentanaABMCliente();
				ControladorABMCliente c = new ControladorABMCliente(abm);
				c.inicializar();
			}
		});
		personas.add(cliente);

		JMenuItem fletero = new JMenuItem("Fletero");
		fletero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaABMFlete abm = new VentanaABMFlete();
				ControladorABMFlete c = new ControladorABMFlete(abm);
				c.inicializar();
			}
		});
		personas.add(fletero);

		JMenuItem proveedor = new JMenuItem("Proveedor");
		proveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaABMProveedor abm = new VentanaABMProveedor();
				ControladorABMProveedor c = new ControladorABMProveedor(abm);
				c.inicializar();
			}
		});
		personas.add(proveedor);

		JMenuItem usuario = new JMenuItem("Usuario");
		usuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaABMUsuario abm = new VentanaABMUsuario();
				ControladorVentanaABMUsuario c = new ControladorVentanaABMUsuario(abm);
				c.inicializar();
			}
		});
		personas.add(usuario);

		// PRODUCTOS
		JMenu productos = new JMenu("Gestionar Producto");
		_gestion.add(productos);

		JMenuItem marca = new JMenuItem("Marca");
		marca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaABMMarcaProducto abm = new VentanaABMMarcaProducto();
				ControladorABMMarcaProducto c = new ControladorABMMarcaProducto(abm);
				c.inicializar();
			}
		});
		productos.add(marca);

		JMenuItem tipo = new JMenuItem("Tipo");
		tipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaABMTipoProducto abm = new VentanaABMTipoProducto();
				ControladorABMTipoProducto c = new ControladorABMTipoProducto(abm);
				c.inicializar();
			}
		});
		productos.add(tipo);

		// REPUESTOS
		JMenuItem repuestos = new JMenuItem("Gestionar Repuesto");
		repuestos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaABMRepuesto abm = new VentanaABMRepuesto();
				ControladorABMRepuesto c = new ControladorABMRepuesto(abm);
				c.inicializar();
			}
		});
		_gestion.add(repuestos);
		_gestion.addSeparator();

	}

	private void cargarItemsDeSistema() {
		deslogueo = new JMenuItem("Cerrar Sesión");

		_sistema.add(deslogueo);

		JMenuItem ayuda = new JMenuItem("Ayuda");
		_sistema.add(ayuda);

		JMenu backup = new JMenu("Generar Backup de Datos");
		_sistema.add(backup);

		JMenuItem _export = new JMenuItem("Exportar Datos");
		backup.add(_export);
		JMenuItem _import = new JMenuItem("Importar Datos");
		backup.add(_import);

		_sistema.addSeparator();

		JMenuItem salir = new JMenuItem("Salir");
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		_sistema.add(salir);
		_sistema.addSeparator();

	}

	private void setearPropiedadesDeTablaOrdenesTrabajo() {
		ordenesDeTrabajo_table.getColumnModel().getColumn(0).setMaxWidth(10);
		ordenesDeTrabajo_table.getColumnModel().getColumn(6).setMaxWidth(10);
		formatoTablaOrdenTrabajo = new FormatoTablaOrdenesTrabajo();
		ordenesDeTrabajo_table.setDefaultRenderer(Object.class, formatoTablaOrdenTrabajo);
		ordenesDeTrabajo_table.getTableHeader().setResizingAllowed(false);
		ordenesDeTrabajo_table.getTableHeader().setReorderingAllowed(false);
		ordenesDeTrabajo_table.getColumnModel().getColumn(1).setCellRenderer(formatoTablaOrdenTrabajo);
		ordenesDeTrabajo_table.getColumnModel().getColumn(5).setCellRenderer(formatoTablaOrdenTrabajo);
	}

	private void setearPropiedadesDeTablaAvisoFaltante() {
		formatoTablaAvisoFaltante = new FormatoTablaAvisoFaltante();
		table_avisoFaltante.setDefaultRenderer(Object.class, formatoTablaAvisoFaltante);
		table_avisoFaltante.getTableHeader().setResizingAllowed(false);
		table_avisoFaltante.getTableHeader().setReorderingAllowed(false);
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

	public JMenuItem getDeslogueo() {
		return deslogueo;
	}

	public void setDeslogueo(JMenuItem deslogueo) {
		this.deslogueo = deslogueo;
	}

	public JTable getTable_avisoFaltante() {
		return table_avisoFaltante;
	}

	public void setTable_avisoFaltante(JTable table_avisoFaltante) {
		this.table_avisoFaltante = table_avisoFaltante;
	}

}
