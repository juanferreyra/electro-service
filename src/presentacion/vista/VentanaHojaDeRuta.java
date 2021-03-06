package presentacion.vista;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.Date;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.Toolkit;
import javax.swing.JComboBox;

public class VentanaHojaDeRuta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable listaEnviosTable;
	private JTextField txtflBuscarConductor;
	private JTextField txtfldNombreConductor;
	private JTextField txtfldMovil;
	private JTextField txtfldPatente;
	private JTextField txtfldTelefono;
	private JTextField txtfldCargarHoja;
	private JButton btnBuscarConductor;
	private JButton btnImprimir;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnCargarHoja;
	private JButton btnBorrarCarga;
	private JButton btnMarcarEntregados;
	private JButton crearFletero_btn;
	private JComboBox<String> listaHojasRuta_cmb;

	@SuppressWarnings("serial")
	public VentanaHojaDeRuta() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaHojaDeRuta.class.getResource("/logo.png")));
		setBackground(Color.WHITE);
		setTitle("Preparar Hoja de ruta");
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(Color.BLACK);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 698, 614);
		setMinimumSize(new Dimension(700, 546));
		setLocationRelativeTo(null);

		contentPane.setBorder(null);
		setContentPane(contentPane);

		// TABLA PRINCIPAL DE CHECKBOX

		listaEnviosTable = new JTable();
		listaEnviosTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {},
				new String[] { "Nro", "Fecha", "Producto", "Cliente", "Localidad", "Direccion Envio", "" }) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Integer.class, Date.class, String.class, String.class, String.class,
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
		contentPane.setLayout(null);

		listaEnviosTable.setBounds(new Rectangle(10, 10, 992, 301));

		JScrollPane ordenesDeTrabajo_scrollPane = new JScrollPane();
		ordenesDeTrabajo_scrollPane.setBounds(5, 161, 684, 372);
		ordenesDeTrabajo_scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ordenesDeTrabajo_scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		ordenesDeTrabajo_scrollPane.add(listaEnviosTable);
		ordenesDeTrabajo_scrollPane.setViewportView(listaEnviosTable);
		contentPane.add(ordenesDeTrabajo_scrollPane);

		JLabel lblBuscarConductor = new JLabel("Buscar conductor: ");
		lblBuscarConductor.setIcon(new ImageIcon(VentanaHojaDeRuta.class.getResource("/business-card.png")));
		lblBuscarConductor.setBounds(13, 54, 166, 23);
		lblBuscarConductor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBuscarConductor.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblBuscarConductor);

		txtflBuscarConductor = new JTextField();
		txtflBuscarConductor.setBounds(170, 54, 194, 23);
		contentPane.add(txtflBuscarConductor);
		txtflBuscarConductor.setColumns(10);

		btnBuscarConductor = new JButton("");
		btnBuscarConductor.setBounds(366, 54, 33, 23);
		btnBuscarConductor.setIcon(new ImageIcon(VentanaHojaDeRuta.class.getResource("/search.png")));
		contentPane.add(btnBuscarConductor);

		JLabel lblNombreConductor = new JLabel("Conductor");
		lblNombreConductor.setBounds(26, 85, 153, 23);
		lblNombreConductor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombreConductor.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblNombreConductor);

		JLabel lblMovil = new JLabel("Movil");
		lblMovil.setBounds(26, 119, 120, 23);
		lblMovil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMovil.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblMovil);

		txtfldNombreConductor = new JTextField();
		txtfldNombreConductor.setForeground(Color.BLACK);
		txtfldNombreConductor.setBounds(126, 85, 228, 23);
		txtfldNombreConductor.setEditable(false);
		contentPane.add(txtfldNombreConductor);
		txtfldNombreConductor.setColumns(10);

		txtfldMovil = new JTextField();
		txtfldMovil.setForeground(Color.BLACK);
		txtfldMovil.setBounds(126, 119, 228, 23);
		txtfldMovil.setEditable(false);
		txtfldMovil.setColumns(10);
		contentPane.add(txtfldMovil);

		JLabel lblPatente = new JLabel("Patente");
		lblPatente.setBounds(366, 119, 85, 23);
		lblPatente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPatente.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblPatente);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(366, 85, 85, 23);
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblTelefono);

		txtfldPatente = new JTextField();
		txtfldPatente.setForeground(Color.BLACK);
		txtfldPatente.setBounds(443, 119, 223, 23);
		txtfldPatente.setEditable(false);
		txtfldPatente.setColumns(10);
		contentPane.add(txtfldPatente);

		txtfldTelefono = new JTextField();
		txtfldTelefono.setForeground(Color.BLACK);
		txtfldTelefono.setBounds(443, 85, 223, 23);
		txtfldTelefono.setEditable(false);
		txtfldTelefono.setColumns(10);
		contentPane.add(txtfldTelefono);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLUE);
		separator.setBounds(5, 46, 664, 10);
		contentPane.add(separator);

		JLabel lblHojaDeRuta = new JLabel("Hoja de Ruta");
		lblHojaDeRuta.setIcon(new ImageIcon(VentanaHojaDeRuta.class.getResource("/pin.png")));
		lblHojaDeRuta.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblHojaDeRuta.setHorizontalAlignment(SwingConstants.LEFT);
		lblHojaDeRuta.setBounds(5, 11, 278, 30);
		contentPane.add(lblHojaDeRuta);

		btnImprimir = new JButton("Imprimir");
		btnImprimir.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnImprimir.setIcon(new ImageIcon(VentanaHojaDeRuta.class.getResource("/printer.png")));
		btnImprimir.setBounds(5, 544, 130, 30);
		contentPane.add(btnImprimir);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnGuardar.setBounds(459, 544, 110, 30);
		contentPane.add(btnGuardar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnCancelar.setBounds(579, 544, 110, 30);
		contentPane.add(btnCancelar);

		JLabel lblCargar = new JLabel("Cargar:");
		lblCargar.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		lblCargar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCargar.setBounds(344, 12, 70, 23);
		contentPane.add(lblCargar);

		txtfldCargarHoja = new JTextField();
		txtfldCargarHoja.setToolTipText("Ingresar el Nro de hoja de ruta");
		txtfldCargarHoja.setBounds(558, 12, 48, 23);
		contentPane.add(txtfldCargarHoja);
		txtfldCargarHoja.setColumns(10);

		btnCargarHoja = new JButton("");
		btnCargarHoja.setIcon(new ImageIcon(VentanaHojaDeRuta.class.getResource("/download.png")));
		btnCargarHoja.setBounds(608, 10, 25, 25);
		contentPane.add(btnCargarHoja);

		btnBorrarCarga = new JButton("");
		btnBorrarCarga.setIcon(new ImageIcon(VentanaHojaDeRuta.class.getResource("/delete.png")));
		btnBorrarCarga.setBounds(641, 10, 25, 25);
		contentPane.add(btnBorrarCarga);

		btnMarcarEntregados = new JButton("Marcar Entregados");
		btnMarcarEntregados.setIcon(new ImageIcon(VentanaHojaDeRuta.class.getResource("/tick-outline.png")));
		btnMarcarEntregados.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnMarcarEntregados.setBounds(145, 544, 211, 30);
		contentPane.add(btnMarcarEntregados);

		crearFletero_btn = new JButton("");
		crearFletero_btn.setIcon(new ImageIcon(VentanaHojaDeRuta.class.getResource("/user-add.png")));
		crearFletero_btn.setBounds(401, 54, 33, 23);
		contentPane.add(crearFletero_btn);

		listaHojasRuta_cmb = new JComboBox<String>();
		listaHojasRuta_cmb.setBounds(420, 11, 130, 24);
		contentPane.add(listaHojasRuta_cmb);

	}

	public JButton getCrearFletero_btn() {
		return crearFletero_btn;
	}

	public JComboBox<String> getListaHojasRuta_cmb() {
		return listaHojasRuta_cmb;
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

	public JTextField getTxtflBuscarConductor() {
		return txtflBuscarConductor;
	}

	public JTextField getTxtfldNombreConductor() {
		return txtfldNombreConductor;
	}

	public JTextField getTxtfldMovil() {
		return txtfldMovil;
	}

	public JTextField getTxtfldPatente() {
		return txtfldPatente;
	}

	public JTextField getTxtfldTelefono() {
		return txtfldTelefono;
	}

	public JTextField getTxtfldCargarHoja() {
		return txtfldCargarHoja;
	}

	public JButton getBtnBuscarConductor() {
		return btnBuscarConductor;
	}

	public JButton getBtnImprimir() {
		return btnImprimir;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JButton getBtnCargarHoja() {
		return btnCargarHoja;
	}

	public JButton getBtnBorrarCarga() {
		return btnBorrarCarga;
	}

	public JButton getBtnMarcarEntregados() {
		return btnMarcarEntregados;
	}
}
