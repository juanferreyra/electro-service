package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
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
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;

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
	private JScrollPane scrollPaneAviso;
	private JMenu _sistema;
	private JMenu _contabilidad;
	private JMenu _gestion;
	private JMenu _reporte;
	private JMenuItem deslogueo;
	private JMenuItem cambioDeClave;
	private FormatoTablaAvisoFaltante formatoTablaAvisoFaltante;
	private JMenuItem ordenCompra;
	private JMenuItem stock;
	private JMenuItem venta;
	private JMenuItem reparado;
	private JMenuItem masInsumido;
	private JLabel lblUsuario;

	@SuppressWarnings("serial")
	public VentanaPrincipal() {

		setTitle("::: ELECTRO R SRL. ::: Software para Servicios Electr\u00f3nicos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPresentacion.class.getResource("/logo.png")));

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1044, 546);
		setMinimumSize(new Dimension(1046, 546));

		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(70, 130, 180), 2, true));
		contentPane.setLayout(new BorderLayout());

		setContentPane(contentPane);

		// MENU PRINCIPAL
		menuBar = new JMenuBar();
		menuBar.setBorder(null);
		menuBar.setMinimumSize(new Dimension(0, 0));
		menuBar.setMaximumSize(new Dimension(0, 0));
		menuBar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		menuBar.setBounds(0, 0, 1028, 50);

		_sistema = new JMenu("Sistema");
		menuBar.add(_sistema);
		cargarItemsDeSistema();

		_gestion = new JMenu("Gesti\u00F3n de Datos");
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
		ordenesDeTrabajo_lbl.setBackground(Color.WHITE);
		ordenesDeTrabajo_lbl.setMinimumSize(new Dimension(1000, 50));
		ordenesDeTrabajo_lbl.setForeground(new Color(70, 130, 180));
		ordenesDeTrabajo_lbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		ordenesDeTrabajo_lbl.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel contenedorPanelArriba = new JPanel(new BorderLayout());

		JPanel subpanelArriba = new JPanel(new BorderLayout());
		subpanelArriba.setOpaque(false);
		subpanelArriba.setBackground(Color.WHITE);
		subpanelArriba.setForeground(Color.WHITE);
		subpanelArriba.add(ordenesDeTrabajo_lbl, BorderLayout.CENTER);

		contenedorPanelArriba.add(subpanelArriba, BorderLayout.SOUTH);

		lblUsuario = new JLabel("");
		lblUsuario.setBorder(new EmptyBorder(4, 4, 4, 4));
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblUsuario.setForeground(Color.GRAY);
		subpanelArriba.add(lblUsuario, BorderLayout.EAST);
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
		ordenesDeTrabajo_scrollPane.setBorder(new LineBorder(new Color(255, 255, 255), 3, true));
		ordenesDeTrabajo_scrollPane.setMinimumSize(new Dimension(50, 50));
		ordenesDeTrabajo_scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		ordenesDeTrabajo_scrollPane.setBounds(10, 134, 1005, 301);
		ordenesDeTrabajo_scrollPane.add(ordenesDeTrabajo_table);
		ordenesDeTrabajo_scrollPane.setViewportView(ordenesDeTrabajo_table);

		// TABLA DE AVISO DE FALTANTE

		table_avisoFaltante = new JTable();
		table_avisoFaltante.setBorder(new LineBorder(Color.WHITE, 1, true));
		table_avisoFaltante.setPreferredScrollableViewportSize(new Dimension(200, 500));

		DefaultTableModel modeloAviso = new DefaultTableModel(new Object[][] {}, new String[] { "" }) {
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

		table_avisoFaltante.getColumnModel().getColumn(0).setMaxWidth(250);
		table_avisoFaltante.getColumnModel().getColumn(0).setMinWidth(250);
		table_avisoFaltante.getColumnModel().getColumn(0).setPreferredWidth(250);

		scrollPaneAviso = new JScrollPane();
		scrollPaneAviso.setBackground(Color.WHITE);
		scrollPaneAviso.setForeground(Color.BLACK);
		scrollPaneAviso.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPaneAviso.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "AVISO DE FALTANTE",
				TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(70, 130, 180)));
		scrollPaneAviso.setMinimumSize(new Dimension(50, 50));
		scrollPaneAviso.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneAviso.setBounds(10, 134, 1005, 301);
		scrollPaneAviso.add(table_avisoFaltante);
		scrollPaneAviso.setViewportView(table_avisoFaltante);

		setearPropiedadesDeTablaAvisoFaltante();

		// AGREGO AVISO FALTANTE Y TABLA ORDENES DE TRABAJO EN UN PANEL
		// CENTRAL
		JPanel contenedorCentral = new JPanel();
		contenedorCentral.setLayout(new BoxLayout(contenedorCentral, BoxLayout.LINE_AXIS));

		contenedorCentral.add(ordenesDeTrabajo_scrollPane);
		contentPane.add(scrollPaneAviso, BorderLayout.WEST);
		contentPane.add(contenedorCentral, BorderLayout.CENTER);

		// FIN CONTENEDOR CENTRAL

		JPanel panelDeBotones = new JPanel(new FlowLayout());
		panelDeBotones.setOpaque(false);
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
		JMenu estadisticos = new JMenu("Generar Reporte Estad\u00EDstico");
		_reporte.add(estadisticos);

		venta = new JMenuItem("De ventas por fecha");
		estadisticos.add(venta);

		reparado = new JMenuItem("De reparaciones");
		estadisticos.add(reparado);

		masInsumido = new JMenuItem("De repuestos m\u00E1s insumidos");
		estadisticos.add(masInsumido);
		_reporte.addSeparator();
	}

	private void cargarItemsContabilidad() {

		stock = new JMenuItem("Control de Stock");
		_contabilidad.add(stock);

		ordenCompra = new JMenuItem("Generar Orden de Compra");

		_contabilidad.add(ordenCompra);
		_contabilidad.addSeparator();
	}

	private void cargarItemsDeGestion() {

		JMenu backup = new JMenu("Generar Backup de Datos");
		_gestion.add(backup);

		JMenuItem _export = new JMenuItem("Exportar Datos");
		backup.add(_export);
		JMenuItem _import = new JMenuItem("Importar Datos");
		backup.add(_import);

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

		cambioDeClave = new JMenuItem("Cambiar Clave");

		cambioDeClave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

			}
		});

		deslogueo = new JMenuItem("Cerrar Sesi\u00F3n");

		_sistema.add(cambioDeClave);
		_sistema.add(deslogueo);

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

	public JMenuItem getOrdenCompra() {
		return ordenCompra;
	}

	public void setOrdenCompra(JMenuItem ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

	public JMenuItem getStock() {
		return stock;
	}

	public JMenuItem getVenta() {
		return venta;
	}

	public JMenuItem getReparado() {
		return reparado;
	}

	public JMenuItem getMasInsumido() {
		return masInsumido;
	}

	public JScrollPane getScrollPaneAviso() {
		return scrollPaneAviso;
	}

	public void setScrollPaneAviso(JScrollPane scrollPaneAviso) {
		this.scrollPaneAviso = scrollPaneAviso;
	}

	public String getLblUsuario() {
		return lblUsuario.getText();
	}

	public void setLblUsuario(String lblUsuario) {
		this.lblUsuario.setText(lblUsuario);
	}

}