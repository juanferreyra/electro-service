package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;

public class VentanaABMFlete extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablaFlete;
	private DefaultTableModel modelFlete;
	private String[] nombreColumnas = { "Nombre", "DNI", "Modelo transporte", "Patente transporte", "Teléfono",
			"Vencimiento licencia" };
	private JTextField nombre_txf;
	private JTextField documento_txf;
	private JTextField modeloTransporte_txf;
	private JTextField patenteTransporte_txf;
	private JTextField telefono_txf;
	private JButton eliminarItem_btn;
	private JButton limpiar_btn;
	private JButton guardar_btn;
	private JDateChooser dateChooser;

	public VentanaABMFlete() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPresentacion.class.getResource("/logo.png")));
		setBounds(100, 100, 742, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		limpiar_btn = new JButton("Limpiar");
		limpiar_btn.setBounds(433, 382, 121, 23);
		getContentPane().add(limpiar_btn);

		guardar_btn = new JButton("Ingresar");
		guardar_btn.setBounds(302, 382, 121, 23);
		getContentPane().add(guardar_btn);

		JScrollPane flete_scrollPane = new JScrollPane();
		flete_scrollPane.setBounds(10, 269, 705, 91);
		getContentPane().add(flete_scrollPane);

		modelFlete = new DefaultTableModel(null, nombreColumnas) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int fila, int columna) {

				return false;
			}
		};
		tablaFlete = new JTable(modelFlete);
		flete_scrollPane.setViewportView(tablaFlete);

		eliminarItem_btn = new JButton("Eliminar");
		eliminarItem_btn.setBounds(152, 382, 140, 23);
		getContentPane().add(eliminarItem_btn);

		JLabel title_lbl = new JLabel("Nuevo fletero");
		title_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		title_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		title_lbl.setBounds(10, 11, 708, 14);
		contentPane.add(title_lbl);

		JLabel nombre_lbl = new JLabel("Nombre");
		nombre_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nombre_lbl.setBounds(74, 84, 186, 14);
		contentPane.add(nombre_lbl);

		JLabel documento_lbl = new JLabel("Documento");
		documento_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		documento_lbl.setBounds(74, 109, 168, 14);
		contentPane.add(documento_lbl);

		JLabel modeloTransporte_lbl = new JLabel("Modelo transporte");
		modeloTransporte_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		modeloTransporte_lbl.setBounds(74, 134, 156, 14);
		contentPane.add(modeloTransporte_lbl);

		JLabel patenteTransporte_lbl = new JLabel("Patente transporte");
		patenteTransporte_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		patenteTransporte_lbl.setBounds(74, 159, 168, 14);
		contentPane.add(patenteTransporte_lbl);

		JLabel telefono_lbl = new JLabel("Tel\u00E9fono");
		telefono_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		telefono_lbl.setBounds(74, 189, 168, 14);
		contentPane.add(telefono_lbl);

		JLabel fechaLicencia_lbl = new JLabel("Vencimiento licencia ");
		fechaLicencia_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fechaLicencia_lbl.setBounds(74, 214, 168, 14);
		contentPane.add(fechaLicencia_lbl);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(220, 214, 129, 20);
		contentPane.add(dateChooser);

		nombre_txf = new JTextField();
		nombre_txf.setBounds(220, 81, 440, 20);
		contentPane.add(nombre_txf);
		nombre_txf.setColumns(10);

		documento_txf = new JTextField();
		documento_txf.setColumns(10);
		documento_txf.setBounds(219, 106, 441, 20);
		contentPane.add(documento_txf);

		modeloTransporte_txf = new JTextField();
		modeloTransporte_txf.setColumns(10);
		modeloTransporte_txf.setBounds(219, 131, 440, 20);
		contentPane.add(modeloTransporte_txf);

		patenteTransporte_txf = new JTextField();
		patenteTransporte_txf.setColumns(10);
		patenteTransporte_txf.setBounds(219, 158, 441, 20);
		contentPane.add(patenteTransporte_txf);

		telefono_txf = new JTextField();
		telefono_txf.setColumns(10);
		telefono_txf.setBounds(220, 188, 440, 20);
		contentPane.add(telefono_txf);

	}

	public JTable getTablaFlete() {
		return tablaFlete;
	}

	public void setTablaFlete(JTable tablaFlete) {
		this.tablaFlete = tablaFlete;
	}

	public JTextField getNombre_txf() {
		return nombre_txf;
	}

	public void setNombre_txf(JTextField nombre_txf) {
		this.nombre_txf = nombre_txf;
	}

	public JTextField getDocumento_txf() {
		return documento_txf;
	}

	public void setDocumento_txf(JTextField documento_txf) {
		this.documento_txf = documento_txf;
	}

	public JTextField getPatenteTransporte_txf() {
		return patenteTransporte_txf;
	}

	public void setPatenteTransporte_txf(JTextField patenteTransporte_txf) {
		this.patenteTransporte_txf = patenteTransporte_txf;
	}

	public JTextField getTelefono_txf() {
		return telefono_txf;
	}

	public void setTelefono_txf(JTextField telefono_txf) {
		this.telefono_txf = telefono_txf;
	}

	public JButton getEliminarItem_btn() {
		return eliminarItem_btn;
	}

	public void setEliminarItem_btn(JButton eliminarItem_btn) {
		this.eliminarItem_btn = eliminarItem_btn;
	}

	public JButton getLimpiar_btn() {
		return limpiar_btn;
	}

	public void setLimpiar_btn(JButton limpiar_btn) {
		this.limpiar_btn = limpiar_btn;
	}

	public JButton getGuardar_btn() {
		return guardar_btn;
	}

	public void setGuardar_btn(JButton guardar_btn) {
		this.guardar_btn = guardar_btn;
	}

	public JTextField getModeloTransporte_txf() {
		return modeloTransporte_txf;
	}

	public void setModeloTransporte_txf(JTextField modeloTransporte_txf) {
		this.modeloTransporte_txf = modeloTransporte_txf;
	}

	public JDateChooser getDateChooser() {
		return dateChooser;
	}

	public void setDateChooser(JDateChooser dateChooser) {
		this.dateChooser = dateChooser;
	}

}
