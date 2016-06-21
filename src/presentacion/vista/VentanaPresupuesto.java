package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import java.awt.SystemColor;
import javax.swing.ScrollPaneConstants;

import java.awt.Toolkit;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class VentanaPresupuesto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField horasDeTrabajo_txf;
	private JTextField manoDeObra_txf;
	private JDateChooser vencimiento_Calendario;
	private JComboBox<String> componente_ComboBox;
	private JButton agregarComponente_btn;
	private JButton eliminarComponente_btn;
	private JTextArea descripcionBreve_jTextArea;
	private JTextArea descripcionTecnica_jTextArea;
	private JTextField valorPresupuestado_txf;
	private JButton enviarPresupuesto_btn;
	private JButton cancelar_btn;
	private JButton guardar_btn;
	private JTable componentes_table;
	private JLabel fechaIngreso_lbl;
	private JLabel nombreProductoTexto_lbl;
	private JLabel tipoTexto_lbl;
	private JLabel marcaTexto_lbl;
	private JTextArea descripcionFalla_txtArea;
	private String[] componentes_nombreColumnas = { "id", "Detalle", "Cantidad", "Precio Unitario", "Precio Total" };
	private JLabel Total_lbl;
	private JLabel estado_lb;
	private JLabel lbltecnico;
	private JButton btnAsignar;
	private JButton btnAceptado;
	private JButton btnInformado;
	private JButton btnRechazado;
	private JButton entregado_btn;
	private JSeparator separator_1;
	private JSpinner spinner;

	@SuppressWarnings("serial")
	public VentanaPresupuesto() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPresupuesto.class.getResource("/logo.png")));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 745, 724);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		fechaIngreso_lbl = new JLabel("");
		fechaIngreso_lbl.setHorizontalAlignment(SwingConstants.TRAILING);
		fechaIngreso_lbl.setBounds(522, 34, 166, 14);
		contentPane.add(fechaIngreso_lbl);

		JLabel fechaIngresoText_lbl = new JLabel("");
		fechaIngresoText_lbl.setBounds(574, 34, 102, 14);
		contentPane.add(fechaIngresoText_lbl);

		JLabel titulo_lbl = new JLabel("<html><i>PRESUPUESTO</i></html>");
		titulo_lbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		titulo_lbl.setBounds(30, 34, 332, 14);
		contentPane.add(titulo_lbl);

		JSeparator separator = new JSeparator();
		separator.setBounds(30, 53, 658, 7);
		separator.setForeground(Color.BLUE);
		contentPane.add(separator);

		JLabel nombreProducto_lbl = new JLabel("<html><i>Nombre Producto:</i></html>");
		nombreProducto_lbl.setBounds(30, 54, 116, 26);
		contentPane.add(nombreProducto_lbl);

		nombreProductoTexto_lbl = new JLabel("");
		nombreProductoTexto_lbl.setForeground(new Color(105, 105, 105));
		nombreProductoTexto_lbl.setBounds(146, 54, 389, 26);
		contentPane.add(nombreProductoTexto_lbl);

		JLabel marca_lbl = new JLabel("<html><i>Marca:</i></html>");
		marca_lbl.setBounds(30, 82, 116, 26);
		contentPane.add(marca_lbl);

		marcaTexto_lbl = new JLabel("");
		marcaTexto_lbl.setForeground(new Color(105, 105, 105));
		marcaTexto_lbl.setBounds(80, 82, 184, 26);
		contentPane.add(marcaTexto_lbl);

		JLabel tipo_lbl = new JLabel("<html><i>Tipo:</i></html>");
		tipo_lbl.setBounds(287, 82, 116, 26);
		contentPane.add(tipo_lbl);

		tipoTexto_lbl = new JLabel("");
		tipoTexto_lbl.setForeground(new Color(105, 105, 105));
		tipoTexto_lbl.setBounds(329, 82, 184, 26);
		contentPane.add(tipoTexto_lbl);

		JLabel descripcionFalla_lbl = new JLabel("<html><i>Descripci\u00F3n falla:</i></html>");
		descripcionFalla_lbl.setBounds(30, 110, 146, 14);
		contentPane.add(descripcionFalla_lbl);

		JScrollPane descripcionFalla_jScrollPane = new javax.swing.JScrollPane();
		descripcionFalla_jScrollPane.setBounds(30, 126, 664, 46);
		descripcionFalla_jScrollPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
		descripcionFalla_jScrollPane
				.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		descripcionFalla_txtArea = new JTextArea();
		descripcionFalla_txtArea.setForeground(new Color(105, 105, 105));
		descripcionFalla_txtArea.setEditable(false);
		descripcionFalla_txtArea.setRows(5);
		descripcionFalla_txtArea.setColumns(20);
		descripcionFalla_jScrollPane.setViewportView(descripcionFalla_txtArea);

		contentPane.add(descripcionFalla_jScrollPane);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(30, 183, 664, 7);
		separator_2.setForeground(Color.BLUE);
		contentPane.add(separator_2);

		JLabel lblTecnico = new JLabel("T\u00E9cnico Asignado:");
		lblTecnico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTecnico.setBounds(30, 190, 116, 26);
		contentPane.add(lblTecnico);

		JLabel horasDeTrabajo_lbl = new JLabel("Horas de trabajo:");
		horasDeTrabajo_lbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		horasDeTrabajo_lbl.setBounds(30, 551, 112, 17);
		contentPane.add(horasDeTrabajo_lbl);

		horasDeTrabajo_txf = new JTextField();
		horasDeTrabajo_txf.setBounds(146, 549, 86, 20);
		contentPane.add(horasDeTrabajo_txf);
		horasDeTrabajo_txf.setColumns(10);

		JLabel manoDeObra_lbl = new JLabel("Mano de Obra:");
		manoDeObra_lbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		manoDeObra_lbl.setBounds(250, 550, 118, 18);
		contentPane.add(manoDeObra_lbl);

		manoDeObra_txf = new JTextField();

		manoDeObra_txf.setBounds(347, 549, 86, 20);
		manoDeObra_txf.setColumns(10);
		contentPane.add(manoDeObra_txf);

		JLabel vencimiento_lbl = new JLabel("Vencimiento:");
		vencimiento_lbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		vencimiento_lbl.setBounds(450, 549, 102, 20);
		contentPane.add(vencimiento_lbl);

		vencimiento_Calendario = new JDateChooser();
		vencimiento_Calendario.setBounds(539, 549, 129, 20);
		contentPane.add(vencimiento_Calendario);

		JLabel componentesAUtilizar_lbl = new JLabel("Componentes a utilizar:");
		componentesAUtilizar_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		componentesAUtilizar_lbl.setBounds(30, 221, 193, 17);
		contentPane.add(componentesAUtilizar_lbl);

		componente_ComboBox = new JComboBox<String>();
		componente_ComboBox.setBounds(193, 218, 146, 20);
		contentPane.add(componente_ComboBox);

		agregarComponente_btn = new JButton("");
		agregarComponente_btn.setBounds(393, 215, 29, 26);
		agregarComponente_btn.setIcon(new ImageIcon(VentanaPresupuesto.class.getResource("/tick-outline.png")));
		contentPane.add(agregarComponente_btn);

		eliminarComponente_btn = new JButton("");
		eliminarComponente_btn.setBounds(432, 215, 29, 26);
		eliminarComponente_btn.setIcon(new ImageIcon(VentanaPresupuesto.class.getResource("/times-outline.png")));
		contentPane.add(eliminarComponente_btn);

		Object[][] componentes_informacionTabla = {};

		JScrollPane componentes_scrollPane = new JScrollPane();
		componentes_scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		componentes_scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		componentes_scrollPane.setEnabled(false);
		componentes_scrollPane.setSize(664, 118);
		componentes_scrollPane.setLocation(30, 246);
		componentes_scrollPane.setBackground(Color.WHITE);
		contentPane.add(componentes_scrollPane);

		componentes_table = new JTable(new DefaultTableModel(componentes_informacionTabla, componentes_nombreColumnas) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			@Override
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		});

		componentes_scrollPane.setViewportView(componentes_table);
		JScrollPane descripcionBreve_jScrollPane = new JScrollPane();
		descripcionBreve_jScrollPane.setBorder(
				new TitledBorder(null, "Descripci\u00F3n Breve", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		descripcionBreve_jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		descripcionBreve_jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		descripcionBreve_jScrollPane.setBounds(new Rectangle(10, 10, 30, 30));
		descripcionBreve_jScrollPane.setBounds(30, 422, 332, 111);
		contentPane.add(descripcionBreve_jScrollPane);
		descripcionBreve_jTextArea = new JTextArea();
		descripcionBreve_jTextArea.setLineWrap(true);
		descripcionBreve_jScrollPane.setViewportView(descripcionBreve_jTextArea);
		JScrollPane descripcionTecnica_jScrollPane = new JScrollPane();
		descripcionTecnica_jScrollPane.setBorder(new TitledBorder(null, "Descripci\u00F3n T\u00E9cnica",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		descripcionTecnica_jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		descripcionTecnica_jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		descripcionTecnica_jScrollPane.setBounds(372, 422, 322, 111);
		contentPane.add(descripcionTecnica_jScrollPane);
		descripcionTecnica_jTextArea = new JTextArea();
		descripcionTecnica_jTextArea.setLineWrap(true);
		descripcionTecnica_jScrollPane.setViewportView(descripcionTecnica_jTextArea);

		JLabel valorPresupuestado_lbl = new JLabel();
		valorPresupuestado_lbl.setForeground(new Color(70, 130, 180));
		valorPresupuestado_lbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		valorPresupuestado_lbl.setText("$");
		valorPresupuestado_lbl.setBounds(287, 594, 86, 24);
		contentPane.add(valorPresupuestado_lbl);
		valorPresupuestado_txf = new JTextField();
		valorPresupuestado_txf.setEditable(false);
		valorPresupuestado_txf.setHorizontalAlignment(SwingConstants.CENTER);
		valorPresupuestado_txf.setForeground(new Color(70, 130, 180));
		valorPresupuestado_txf.setBackground(SystemColor.text);
		valorPresupuestado_txf.setBounds(310, 597, 116, 23);
		contentPane.add(valorPresupuestado_txf);

		enviarPresupuesto_btn = new JButton("Enviar presupuesto");
		enviarPresupuesto_btn.setIcon(new ImageIcon(VentanaPresupuesto.class.getResource("/mail.png")));
		enviarPresupuesto_btn.setBounds(10, 652, 178, 23);
		contentPane.add(enviarPresupuesto_btn);

		cancelar_btn = new JButton("Cancelar");
		cancelar_btn.setBounds(602, 652, 117, 23);
		contentPane.add(cancelar_btn);

		guardar_btn = new JButton("Guardar");
		guardar_btn.setBounds(468, 652, 118, 23);
		contentPane.add(guardar_btn);

		Total_lbl = new JLabel("");
		Total_lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		Total_lbl.setBounds(133, 375, 112, 20);
		contentPane.add(Total_lbl);

		JLabel lblTotalRepuestos = new JLabel("Total repuestos:");
		lblTotalRepuestos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalRepuestos.setBounds(30, 375, 127, 20);
		contentPane.add(lblTotalRepuestos);

		estado_lb = new JLabel("");
		estado_lb.setHorizontalAlignment(SwingConstants.TRAILING);
		estado_lb.setForeground(new Color(105, 105, 105));
		estado_lb.setBounds(572, 8, 116, 15);
		contentPane.add(estado_lb);

		JLabel lblFecha = new JLabel("");
		lblFecha.setBounds(443, 35, 70, 15);
		contentPane.add(lblFecha);

		lbltecnico = new JLabel("");
		lbltecnico.setForeground(new Color(105, 105, 105));
		lbltecnico.setBounds(146, 196, 214, 15);
		contentPane.add(lbltecnico);

		btnAsignar = new JButton("Asignar");
		btnAsignar.setBounds(30, 0, 115, 23);
		contentPane.add(btnAsignar);

		btnAceptado = new JButton("Aceptado");
		btnAceptado.setForeground(new Color(46, 139, 87));
		btnAceptado.setBounds(30, 0, 116, 23);
		contentPane.add(btnAceptado);

		btnInformado = new JButton("Informado");
		btnInformado.setBounds(30, 0, 116, 23);
		contentPane.add(btnInformado);

		btnRechazado = new JButton("Rechazado");
		btnRechazado.setForeground(new Color(255, 0, 0));
		btnRechazado.setBounds(157, 0, 115, 23);
		contentPane.add(btnRechazado);

		entregado_btn = new JButton("Entregado");
		entregado_btn.setBounds(30, 0, 115, 23);
		contentPane.add(entregado_btn);

		separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLUE);
		separator_1.setBounds(30, 576, 664, 7);
		contentPane.add(separator_1);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setBounds(356, 215, 29, 26);
		contentPane.add(spinner);
	}

	public JTextField getHorasDeTrabajo_txf() {
		return horasDeTrabajo_txf;
	}

	public JTextField getManoDeObra_txf() {
		return manoDeObra_txf;
	}

	public JDateChooser getVencimiento_Calendario() {
		return vencimiento_Calendario;
	}

	public JComboBox<String> getComponente_ComboBox() {
		return componente_ComboBox;
	}

	public JButton getAgregarComponente_btn() {
		return agregarComponente_btn;
	}

	public JButton getEliminarComponente_btn() {
		return eliminarComponente_btn;
	}

	public JTextArea getDescripcionBreve_jTextArea() {
		return descripcionBreve_jTextArea;
	}

	public JTextArea getDescripcionTecnica_jTextArea() {
		return descripcionTecnica_jTextArea;
	}

	public JTextField getValorPresupuestado_txf() {
		return valorPresupuestado_txf;
	}

	public JButton getEnviarPresupuesto_btn() {
		return enviarPresupuesto_btn;
	}

	public JButton getCancelar_btn() {
		return cancelar_btn;
	}

	public JButton getGuardar_btn() {
		return guardar_btn;
	}

	public JTable getComponentes_table() {
		return componentes_table;
	}

	public String[] getComponentes_nombreColumnas() {
		return componentes_nombreColumnas;
	}

	public JLabel getFechaIngreso_lbl() {
		return fechaIngreso_lbl;
	}

	public JLabel getNombreProductoTexto_lbl() {
		return nombreProductoTexto_lbl;
	}

	public JLabel getTipoTexto_lbl() {
		return tipoTexto_lbl;
	}

	public JLabel getMarcaTexto_lbl() {
		return marcaTexto_lbl;
	}

	public JTextArea getDescripcionFalla_txtArea() {
		return descripcionFalla_txtArea;
	}

	public JLabel getTotal_lbl() {
		return Total_lbl;
	}

	public JLabel getEstado_lb() {
		return estado_lb;
	}

	public JLabel getLbltecnico() {
		return lbltecnico;
	}

	public void setLbltecnico(JLabel lbltecnico) {
		this.lbltecnico = lbltecnico;
	}

	public JButton getBtnAsignar() {
		return btnAsignar;
	}

	public JButton getBtnAceptado() {
		return btnAceptado;
	}

	public JButton getBtnInformado() {
		return btnInformado;
	}

	public JButton getBtnRechazado() {
		return btnRechazado;
	}

	public JButton getEntregado_btn() {
		return entregado_btn;
	}

	public void setEntregado_btn(JButton entregado_btn) {
		this.entregado_btn = entregado_btn;
	}

	public JSpinner getSpinner() {
		return spinner;
	}

	public void setSpinner(JSpinner spinner) {
		this.spinner = spinner;
	}

}
