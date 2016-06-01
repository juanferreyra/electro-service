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

public class VentanaOrdenCompra extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> repuesto_ComboBox;
	private JButton incrementoCantRepuesto_btn;
	private JButton decrementoCantRepuesto_btn;
	private JButton agregarRepuesto_btn;
	private JButton eliminarRepuesto_btn;
	private JTextField valorPresupuestado_txf;
	private JButton cancelar_btn;
	private JButton guardar_btn;
	private JLabel cantidad_lbl;
	private JTable repuestos_table;
	private JLabel razonsocialTexto_lbl;
	private JLabel mailTexto_lbl;
	private JLabel direccionTexto_lbl;
	private String[] componentes_nombreColumnas = { "id", "Detalle", "Cantidad", "Precio Unitario", "Precio Total" };
	private JButton btnCancelada;
	private JButton btnRecibido;
	private JTextField textField;

	public VentanaOrdenCompra() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaOrdenCompra.class.getResource("/calculator.png")));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 745, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		btnCancelada = new JButton("Cancelada");
		btnCancelada.setBounds(376, 11, 116, 23);
		contentPane.add(btnCancelada);
		
		btnRecibido = new JButton("Recibido");
		btnRecibido.setBounds(257, 11, 116, 23);
		contentPane.add(btnRecibido);

		JLabel titulo_lbl = new JLabel("<html><i>ORDEN DE COMPRA</i></html>");
		titulo_lbl.setBounds(34, 15, 332, 14);
		contentPane.add(titulo_lbl);

		JSeparator separator = new JSeparator();
		separator.setBounds(30, 45, 658, 7);
		separator.setForeground(Color.BLUE);
		contentPane.add(separator);

		JLabel razonsocial_lbl = new JLabel("<html><i>Razón Social:</i></html>");
		razonsocial_lbl.setBounds(30, 71, 116, 26);
		contentPane.add(razonsocial_lbl);

		razonsocialTexto_lbl = new JLabel("");
		razonsocialTexto_lbl.setBounds(123, 71, 565, 26);
		contentPane.add(razonsocialTexto_lbl);

		JLabel direccion_lbl = new JLabel("<html><i>Dirección:</i></html>");
		direccion_lbl.setBounds(30, 99, 116, 26);
		contentPane.add(direccion_lbl);

		direccionTexto_lbl = new JLabel("");
		direccionTexto_lbl.setBounds(123, 99, 184, 26);
		contentPane.add(direccionTexto_lbl);

		JLabel mail_lbl = new JLabel("<html><i> Mail:</i></html>");
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
		repuestosASolicitar_lbl.setHorizontalAlignment(SwingConstants.LEFT);
		repuestosASolicitar_lbl.setBounds(30, 149, 139, 23);
		contentPane.add(repuestosASolicitar_lbl);

		repuesto_ComboBox = new JComboBox<String>();
		repuesto_ComboBox.setBounds(179, 149, 305, 23);
		contentPane.add(repuesto_ComboBox);

		incrementoCantRepuesto_btn = new JButton("");
		incrementoCantRepuesto_btn.setBounds(494, 149, 33, 23);
		incrementoCantRepuesto_btn.setIcon(new ImageIcon(VentanaOrdenCompra.class.getResource("/plus-outline.png")));
		contentPane.add(incrementoCantRepuesto_btn);

		decrementoCantRepuesto_btn = new JButton("");
		decrementoCantRepuesto_btn.setBounds(577, 149, 33, 23);
		decrementoCantRepuesto_btn.setIcon(new ImageIcon(VentanaOrdenCompra.class.getResource("/minus-outline.png")));
		contentPane.add(decrementoCantRepuesto_btn);

		cantidad_lbl = new JLabel("1");
		cantidad_lbl.setBounds(536, 150, 33, 20);
		cantidad_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		cantidad_lbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cantidad_lbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));
		contentPane.add(cantidad_lbl);

		agregarRepuesto_btn = new JButton("");
		agregarRepuesto_btn.setBounds(620, 149, 33, 23);
		agregarRepuesto_btn.setIcon(new ImageIcon(VentanaOrdenCompra.class.getResource("/tick-outline.png")));
		contentPane.add(agregarRepuesto_btn);

		eliminarRepuesto_btn = new JButton("");
		eliminarRepuesto_btn.setBounds(661, 149, 33, 23);
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
		monto_pedido_lbl.setHorizontalAlignment(SwingConstants.RIGHT);
		monto_pedido_lbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		monto_pedido_lbl.setText("Monto de orden:");
		monto_pedido_lbl.setBounds(30, 618, 146, 20);
		contentPane.add(monto_pedido_lbl);

		cancelar_btn = new JButton("Cancelar");
		cancelar_btn.setBounds(577, 618, 117, 23);
		contentPane.add(cancelar_btn);

		guardar_btn = new JButton("Guardar");
		guardar_btn.setBounds(444, 618, 118, 23);
		contentPane.add(guardar_btn);
		
		JButton btnBuscarProveedor = new JButton("");
		btnBuscarProveedor.setToolTipText("Buscar proveedor");
		btnBuscarProveedor.setIcon(new ImageIcon(VentanaOrdenCompra.class.getResource("/search.png")));
		btnBuscarProveedor.setBounds(30, 49, 23, 23);
		contentPane.add(btnBuscarProveedor);
		
		textField = new JTextField();
		textField.setBounds(60, 50, 155, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("");
		button.setToolTipText("Ver Proveedores Ingresados");
		button.setIcon(new ImageIcon(VentanaOrdenCompra.class.getResource("/eye-outline.png")));
		button.setBounds(220, 49, 23, 23);
		contentPane.add(button);
	}

	public JLabel getCantidad_lbl() {
		return cantidad_lbl;
	}

	public JComboBox<String> getComponente_ComboBox() {
		return repuesto_ComboBox;
	}

	public JButton getIncrementoCantComponente_btn() {
		return incrementoCantRepuesto_btn;
	}

	public JButton getDecrementoCantComponente_btn() {
		return decrementoCantRepuesto_btn;
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
}
