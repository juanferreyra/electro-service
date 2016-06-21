package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.ScrollPaneConstants;
import java.awt.Toolkit;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class VentanaOrdenCompra extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> repuesto_ComboBox;
	private JButton agregarRepuesto_btn;
	private JButton eliminarRepuesto_btn;
	private JTextField valorPresupuestado_txf;
	private JButton cancelar_btn;
	private JButton guardar_btn;
	private JTable repuestos_table;
	private JLabel razonsocialTexto_lbl;
	private JLabel mailTexto_lbl;
	private JLabel direccionTexto_lbl;
	private String[] componentes_nombreColumnas = { "id", "Marca","Detalle", "Cantidad", "Cantidad Real"};
	private JButton btnCancelada;
	private JButton btnRecibido;
	private JTextField txtfldNroProveedor;
	private JButton btnBuscarProveedor;
	private JButton btnVerProveedores;
	private JTextField txtfldCargarOrden;
	private JButton btnCargarOrden;
	private JButton BtnVaciarVentanaOrden;
	private JButton btnImprimir;
	private JButton btnEnviarEmial;
	private JComboBox<String> listaOrdenesCompras_cmb;
	private JSpinner spinner;

	public VentanaOrdenCompra() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaHojaDeRuta.class.getResource("/logo.png")));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 736, 777);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		btnCancelada = new JButton("Cancelado");
		btnCancelada.setBounds(314, 13, 116, 23);
		contentPane.add(btnCancelada);
		
		btnRecibido = new JButton("Recibido");
		btnRecibido.setBounds(195, 13, 116, 23);
		contentPane.add(btnRecibido);

		JLabel titulo_lbl = new JLabel("<html><i>ORDEN DE COMPRA</i></html>");
		titulo_lbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		titulo_lbl.setBounds(34, 22, 332, 14);
		contentPane.add(titulo_lbl);

		JSeparator separator = new JSeparator();
		separator.setBounds(30, 45, 658, 7);
		separator.setForeground(Color.BLUE);
		contentPane.add(separator);

		JLabel razonsocial_lbl = new JLabel("<html><i>Razon Social:</i></html>");
		razonsocial_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		razonsocial_lbl.setBounds(30, 71, 116, 26);
		contentPane.add(razonsocial_lbl);

		razonsocialTexto_lbl = new JLabel("");
		razonsocialTexto_lbl.setBounds(123, 71, 565, 26);
		contentPane.add(razonsocialTexto_lbl);

		JLabel direccion_lbl = new JLabel("<html><i>Direccion:</i></html>");
		direccion_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		direccion_lbl.setBounds(30, 99, 116, 26);
		contentPane.add(direccion_lbl);

		direccionTexto_lbl = new JLabel("");
		direccionTexto_lbl.setBounds(123, 99, 184, 26);
		contentPane.add(direccionTexto_lbl);

		JLabel mail_lbl = new JLabel("<html><i> Mail:</i></html>");
		mail_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mail_lbl.setBounds(317, 99, 116, 26);
		contentPane.add(mail_lbl);

		mailTexto_lbl = new JLabel("");
		mailTexto_lbl.setBounds(402, 99, 286, 26);
		contentPane.add(mailTexto_lbl);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(24, 131, 664, 7);
		separator_2.setForeground(Color.BLUE);
		contentPane.add(separator_2);

		JLabel repuestosASolicitar_lbl = new JLabel("Repuestos a solicitar:");
		repuestosASolicitar_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		repuestosASolicitar_lbl.setHorizontalAlignment(SwingConstants.LEFT);
		repuestosASolicitar_lbl.setBounds(30, 152, 139, 23);
		contentPane.add(repuestosASolicitar_lbl);

		repuesto_ComboBox = new JComboBox<String>();
		repuesto_ComboBox.setBounds(179, 152, 305, 23);
		contentPane.add(repuesto_ComboBox);

		agregarRepuesto_btn = new JButton("");
		agregarRepuesto_btn.setBounds(544, 149, 29, 26);
		agregarRepuesto_btn.setIcon(new ImageIcon(VentanaOrdenCompra.class.getResource("/tick-outline.png")));
		contentPane.add(agregarRepuesto_btn);

		eliminarRepuesto_btn = new JButton("");
		eliminarRepuesto_btn.setBounds(583, 149, 29, 26);
		eliminarRepuesto_btn.setIcon(new ImageIcon(VentanaOrdenCompra.class.getResource("/times-outline.png")));
		contentPane.add(eliminarRepuesto_btn);

		Object[][] repuestos_informacionTabla = {};

		JScrollPane repuestos_scrollPane = new JScrollPane();
		repuestos_scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		repuestos_scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		repuestos_scrollPane.setEnabled(false);
		repuestos_scrollPane.setSize(664, 417);
		repuestos_scrollPane.setLocation(30, 183);
		repuestos_scrollPane.setBackground(Color.WHITE);
		contentPane.add(repuestos_scrollPane);

		repuestos_table = new JTable(repuestos_informacionTabla, componentes_nombreColumnas);
		repuestos_scrollPane.setViewportView(repuestos_table);
		valorPresupuestado_txf = new JTextField();
		valorPresupuestado_txf.setBackground(SystemColor.text);
		valorPresupuestado_txf.setBounds(179, 618, 146, 20);
		contentPane.add(valorPresupuestado_txf);
		
		JLabel monto_pedido_lbl = new JLabel();
		monto_pedido_lbl.setHorizontalAlignment(SwingConstants.LEFT);
		monto_pedido_lbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		monto_pedido_lbl.setText("Monto de orden:");
		monto_pedido_lbl.setBounds(30, 618, 146, 20);
		contentPane.add(monto_pedido_lbl);

		cancelar_btn = new JButton("Cancelar");
		cancelar_btn.setBounds(598, 688, 90, 23);
		contentPane.add(cancelar_btn);

		guardar_btn = new JButton("Guardar");
		guardar_btn.setBounds(494, 688, 90, 23);
		contentPane.add(guardar_btn);
		
		btnBuscarProveedor = new JButton("");
		btnBuscarProveedor.setToolTipText("Buscar proveedor");
		btnBuscarProveedor.setIcon(new ImageIcon(VentanaOrdenCompra.class.getResource("/search.png")));
		btnBuscarProveedor.setBounds(30, 49, 23, 23);
		contentPane.add(btnBuscarProveedor);
		
		txtfldNroProveedor = new JTextField();
		txtfldNroProveedor.setBounds(60, 52, 155, 20);
		contentPane.add(txtfldNroProveedor);
		txtfldNroProveedor.setColumns(10);
		
		btnVerProveedores = new JButton("");
		btnVerProveedores.setToolTipText("Ver Proveedores Ingresados");
		btnVerProveedores.setIcon(new ImageIcon(VentanaOrdenCompra.class.getResource("/eye-outline.png")));
		btnVerProveedores.setBounds(220, 49, 23, 23);
		contentPane.add(btnVerProveedores);
		
		JLabel label = new JLabel("Cargar:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		label.setBounds(427, 13, 63, 23);
		contentPane.add(label);
		
		txtfldCargarOrden = new JTextField();
		txtfldCargarOrden.setToolTipText("Ingresar el Nro de hoja de ruta");
		txtfldCargarOrden.setColumns(10);
		txtfldCargarOrden.setBounds(619, 13, 39, 23);
		contentPane.add(txtfldCargarOrden);
		
		btnCargarOrden = new JButton("");
		btnCargarOrden.setIcon(new ImageIcon(VentanaOrdenCompra.class.getResource("/download.png")));
		btnCargarOrden.setBounds(661, 11, 25, 25);
		contentPane.add(btnCargarOrden);
		
		BtnVaciarVentanaOrden = new JButton("");
		BtnVaciarVentanaOrden.setIcon(new ImageIcon(VentanaOrdenCompra.class.getResource("/delete.png")));
		BtnVaciarVentanaOrden.setBounds(685, 11, 25, 25);
		contentPane.add(BtnVaciarVentanaOrden);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setIcon(new ImageIcon(VentanaOrdenCompra.class.getResource("/printer.png")));
		btnImprimir.setBounds(34, 688, 139, 23);
		contentPane.add(btnImprimir);
		
		btnEnviarEmial = new JButton("Enviar Emial");
		btnEnviarEmial.setIcon(new ImageIcon(VentanaOrdenCompra.class.getResource("/mail.png")));
		btnEnviarEmial.setBounds(179, 688, 127, 23);
		contentPane.add(btnEnviarEmial);
		
		listaOrdenesCompras_cmb = new JComboBox<String>();
		listaOrdenesCompras_cmb.setBounds(494, 12, 123, 24);
		contentPane.add(listaOrdenesCompras_cmb);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setBounds(505, 149, 29, 26);
		contentPane.add(spinner);
	}

	public JComboBox<String> getListaOrdenesCompras_cmb() {
		return listaOrdenesCompras_cmb;
	}

	public JButton getBtnImprimir() {
		return btnImprimir;
	}

	public JTextField getTxtfldCargarOrden() {
		return txtfldCargarOrden;
	}

	public JButton getBtnVaciarVentanaOrden() {
		return BtnVaciarVentanaOrden;
	}

	public JButton getBtnCargarOrden() {
		return btnCargarOrden;
	}

	public JTextField getTxtfldNroProveedor() {
		return txtfldNroProveedor;
	}

	public JButton getBtnVerProveedores() {
		return btnVerProveedores;
	}

	

	public JComboBox<String> getComponente_ComboBox() {
		return repuesto_ComboBox;
	}

	public JButton getAgregarComponente_btn() {
		return agregarRepuesto_btn;
	}

	public JButton getEliminarComponente_btn() {
		return eliminarRepuesto_btn;
	}

	public JTextField getValorPresupuestado_txf() {
		return valorPresupuestado_txf;
	}

	public JButton getCancelar_btn() {
		return cancelar_btn;
	}

	public JButton getGuardar_btn() {
		return guardar_btn;
	}

	public JTable getComponentes_table() {
		return repuestos_table;
	}

	public String[] getComponentes_nombreColumnas() {
		return componentes_nombreColumnas;
	}
	
	public JLabel getNombreRazonSocialTexto_lbl() {
		return razonsocialTexto_lbl;
	}

	public JLabel getTipoTexto_lbl() {
		return mailTexto_lbl;
	}

	public JLabel getMarcaTexto_lbl() {
		return direccionTexto_lbl;
	}

	public JButton getBtnCancelada() {
		return btnCancelada;
	}

	public JButton getBtnRecibido() {
		return btnRecibido;
	}

	public JButton getBtnBuscarProveedor() {
		return btnBuscarProveedor;
	}
	
	public JLabel getDireccionTexto_lbl() {
		return direccionTexto_lbl;
	}
	
	public JLabel getMailTexto_lbl() {
		return mailTexto_lbl;
	}

	public JButton getBtnEnviarEmial() {
		return btnEnviarEmial;
	}

	public JSpinner getSpinner() {
		return spinner;
	}

	public void setSpinner(JSpinner spinner) {
		this.spinner = spinner;
	}
}
