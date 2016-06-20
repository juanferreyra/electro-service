package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import javax.swing.ScrollPaneConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Font;

public class VentanaReparacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> componente_ComboBox;
	private JButton agregarComponente_btn;
	private JButton eliminarComponente_btn;
	private JTextArea descripcionFinal_jTextArea;
	private JButton enviarAvisoReparacion_btn;
	private JButton cancelar_btn;
	private JButton finalizar_btn;
	private JTable componentes_table;
	private JLabel fechaIngreso_lbl;
	private JLabel nombreProductoTexto_lbl;
	private JLabel tipoTexto_lbl;
	private JLabel marcaTexto_lbl;
	private JTextArea descripcionFalla_txtArea;
	private String[] componentes_nombreColumnas = { "id", "Detalle", "Cantidad", "Precio Unitario", "Precio Total" };
	private JLabel lblMontoPresupuestado;
	private JLabel lblMontoEnvio;
	private JSpinner spinner;
	private JButton btnReparado;
	private JButton btnIrreparado;
	private JLabel mensajeEstadoReparacion;

	public VentanaReparacion() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 745, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		fechaIngreso_lbl = new JLabel("");
		fechaIngreso_lbl.setBounds(522, 11, 166, 14);
		contentPane.add(fechaIngreso_lbl);

		JLabel fechaIngresoText_lbl = new JLabel("");
		fechaIngresoText_lbl.setBounds(572, 11, 102, 14);
		contentPane.add(fechaIngresoText_lbl);

		JLabel titulo_lbl = new JLabel("<html><i>DETALLE DE REPARACION</i></html>");
		titulo_lbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		titulo_lbl.setBounds(30, 11, 495, 14);
		contentPane.add(titulo_lbl);

		JSeparator separator = new JSeparator();
		separator.setBounds(30, 36, 658, 14);
		separator.setForeground(Color.BLUE);
		contentPane.add(separator);

		nombreProductoTexto_lbl = new JLabel("");
		nombreProductoTexto_lbl.setForeground(new Color(105, 105, 105));
		nombreProductoTexto_lbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nombreProductoTexto_lbl.setHorizontalTextPosition(SwingConstants.LEFT);
		nombreProductoTexto_lbl.setBounds(30, 42, 445, 21);
		contentPane.add(nombreProductoTexto_lbl);

		marcaTexto_lbl = new JLabel("");
		marcaTexto_lbl.setForeground(new Color(105, 105, 105));
		marcaTexto_lbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		marcaTexto_lbl.setBounds(30, 68, 184, 14);
		contentPane.add(marcaTexto_lbl);

		tipoTexto_lbl = new JLabel("");
		tipoTexto_lbl.setForeground(new Color(105, 105, 105));
		tipoTexto_lbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tipoTexto_lbl.setBounds(280, 68, 219, 14);
		contentPane.add(tipoTexto_lbl);

		JLabel descripcionFalla_lbl = new JLabel("<html><i>Descripci\u00F3n falla:</i></html>");
		descripcionFalla_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		descripcionFalla_lbl.setBounds(30, 101, 146, 14);
		contentPane.add(descripcionFalla_lbl);

		JScrollPane descripcionFalla_jScrollPane = new javax.swing.JScrollPane();
		descripcionFalla_jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		descripcionFalla_jScrollPane.setBounds(30, 126, 664, 46);
		descripcionFalla_jScrollPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
		descripcionFalla_jScrollPane
				.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		descripcionFalla_txtArea = new JTextArea();
		descripcionFalla_txtArea.setEditable(false);
		descripcionFalla_txtArea.setRows(5);
		descripcionFalla_txtArea.setColumns(20);
		descripcionFalla_jScrollPane.setViewportView(descripcionFalla_txtArea);

		contentPane.add(descripcionFalla_jScrollPane);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(30, 183, 664, 7);
		separator_2.setForeground(Color.BLUE);
		contentPane.add(separator_2);

		JLabel componentesUtilizados_lbl = new JLabel("Componentes utilizados:");
		componentesUtilizados_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		componentesUtilizados_lbl.setBounds(30, 210, 193, 22);
		contentPane.add(componentesUtilizados_lbl);

		componente_ComboBox = new JComboBox<String>();
		componente_ComboBox.setBounds(191, 206, 146, 26);
		contentPane.add(componente_ComboBox);

		agregarComponente_btn = new JButton("");
		agregarComponente_btn.setBounds(386, 206, 33, 26);
		agregarComponente_btn.setIcon(new ImageIcon(VentanaPresupuesto.class.getResource("/tick-outline.png")));
		contentPane.add(agregarComponente_btn);

		eliminarComponente_btn = new JButton("");
		eliminarComponente_btn.setBounds(429, 206, 33, 26);
		eliminarComponente_btn.setIcon(new ImageIcon(VentanaPresupuesto.class.getResource("/times-outline.png")));
		contentPane.add(eliminarComponente_btn);

		Object[][] componentes_informacionTabla = {};

		JScrollPane componentes_scrollPane = new JScrollPane();
		componentes_scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		componentes_scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		componentes_scrollPane.setEnabled(false);
		componentes_scrollPane.setSize(664, 152);
		componentes_scrollPane.setLocation(30, 255);
		componentes_scrollPane.setBackground(Color.WHITE);
		contentPane.add(componentes_scrollPane);

		componentes_table = new JTable(componentes_informacionTabla, componentes_nombreColumnas);

		componentes_scrollPane.setViewportView(componentes_table);

		JLabel descripcionFinal_lbl = new JLabel("Descripcion final:");
		descripcionFinal_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		descripcionFinal_lbl.setBounds(30, 473, 156, 14);
		contentPane.add(descripcionFinal_lbl);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 502, 651, 49);
		contentPane.add(scrollPane);
		descripcionFinal_jTextArea = new JTextArea();
		scrollPane.setViewportView(descripcionFinal_jTextArea);

		enviarAvisoReparacion_btn = new JButton("Enviar Aviso");
		enviarAvisoReparacion_btn.setIcon(new ImageIcon(VentanaPresupuesto.class.getResource("/mail.png")));
		enviarAvisoReparacion_btn.setBounds(30, 615, 212, 23);
		contentPane.add(enviarAvisoReparacion_btn);

		cancelar_btn = new JButton("Cancelar");
		cancelar_btn.setBounds(582, 615, 117, 23);
		contentPane.add(cancelar_btn);

		finalizar_btn = new JButton("Finalizar");
		finalizar_btn.setBounds(438, 615, 118, 23);
		contentPane.add(finalizar_btn);

		JLabel lblFecha = new JLabel("");
		lblFecha.setBounds(435, 10, 70, 15);
		contentPane.add(lblFecha);

		lblMontoPresupuestado = new JLabel("");
		lblMontoPresupuestado.setBounds(208, 440, 146, 15);
		contentPane.add(lblMontoPresupuestado);

		lblMontoEnvio = new JLabel("");
		lblMontoEnvio.setBounds(455, 440, 146, 15);
		contentPane.add(lblMontoEnvio);

		JLabel lblMontoEnvio_1 = new JLabel("Monto Envio: $ ");
		lblMontoEnvio_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMontoEnvio_1.setBounds(345, 440, 117, 15);
		contentPane.add(lblMontoEnvio_1);

		JLabel lblMontoPresupuesto = new JLabel("Monto Presupuesto : $");
		lblMontoPresupuesto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMontoPresupuesto.setBounds(30, 440, 166, 15);
		contentPane.add(lblMontoPresupuesto);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setBounds(347, 206, 29, 26);
		contentPane.add(spinner);

		btnReparado = new JButton("Reparado");
		btnReparado.setForeground(new Color(46, 139, 87));
		btnReparado.setBounds(470, 40, 102, 23);
		contentPane.add(btnReparado);

		btnIrreparado = new JButton("Irreparado");
		btnIrreparado.setForeground(Color.RED);
		btnIrreparado.setBounds(582, 40, 106, 23);
		contentPane.add(btnIrreparado);

		mensajeEstadoReparacion = new JLabel("");
		mensajeEstadoReparacion.setFont(new Font("Tahoma", Font.ITALIC, 13));
		mensajeEstadoReparacion.setBounds(208, 11, 317, 14);
		contentPane.add(mensajeEstadoReparacion);
	}

	public JButton getEnviarAvisoReparacion_btn() {
		return enviarAvisoReparacion_btn;
	}

	public JButton getCancelar_btn() {
		return cancelar_btn;
	}

	public void setCancelar_btn(JButton cancelar_btn) {
		this.cancelar_btn = cancelar_btn;
	}

	public JButton getFinalizar_btn() {
		return finalizar_btn;
	}

	public void setFinalizar_btn(JButton finalizar_btn) {
		this.finalizar_btn = finalizar_btn;
	}

	public JLabel getNombreProductoTexto_lbl() {
		return nombreProductoTexto_lbl;
	}

	public void setNombreProductoTexto_lbl(String nombreProducto) {
		this.nombreProductoTexto_lbl.setText(nombreProducto);
	}

	public JLabel getTipoTexto_lbl() {
		return tipoTexto_lbl;
	}

	public void setTipoTexto_lbl(String tipo) {
		this.tipoTexto_lbl.setText(tipo);
	}

	public JLabel getMarcaTexto_lbl() {
		return marcaTexto_lbl;
	}

	public void setMarcaTexto_lbl(String marca) {
		this.marcaTexto_lbl.setText(marca);
	}

	public JTextArea getDescripcionFalla_txtArea() {
		return descripcionFalla_txtArea;
	}

	public void setDescripcionFalla_txtArea(JTextArea descripcionFalla_txtArea) {
		this.descripcionFalla_txtArea = descripcionFalla_txtArea;
	}

	public JButton getAgregarComponente_btn() {
		return agregarComponente_btn;
	}

	public JTable getComponentes_table() {
		return componentes_table;
	}

	public String[] getComponentes_nombreColumnas() {
		return componentes_nombreColumnas;
	}

	public JButton getEliminarComponente_btn() {
		return eliminarComponente_btn;
	}

	public JComboBox<String> getComponente_ComboBox() {
		return componente_ComboBox;
	}

	public String getDescripcionFinal() {
		return descripcionFinal_jTextArea.getText();
	}

	public void setDescripcionFinal_jTextArea(String descripcionFinal) {
		this.descripcionFinal_jTextArea.setText(descripcionFinal);
	}

	public JLabel getLblMontoPresupuestado() {
		return lblMontoPresupuestado;
	}

	public JLabel getLblMontoEnvio() {
		return lblMontoEnvio;
	}

	public JSpinner getSpinner() {
		return spinner;
	}

	public void setSpinner(JSpinner spinner) {
		this.spinner = spinner;
	}

	public JButton getBtnReparado() {
		return btnReparado;
	}

	public void setBtnReparado(JButton btnReparado) {
		this.btnReparado = btnReparado;
	}

	public JButton getBtnIrreparado() {
		return btnIrreparado;
	}

	public void setBtnIrreparado(JButton btnIrreparado) {
		this.btnIrreparado = btnIrreparado;
	}

	public JLabel getMensajeEstadoReparacion() {
		return mensajeEstadoReparacion;
	}

	public void setMensajeEstadoReparacion(JLabel mensajeEstadoReparacion) {
		this.mensajeEstadoReparacion = mensajeEstadoReparacion;
	}

}
