package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class VentanaABMRepuesto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField detalle_txt;
	private JTextField precio_txt;
	private JTextField stockMinimo_txt;
	private JTable tablaRepuesto;
	private DefaultTableModel modelRepuesto;
	private String[] nombreColumnas = { "id", "Marca", "Detalle", "Precio", "Stock Minimo" };
	private JButton guardar_btn;
	private JButton eliminarItem_btn;
	private JButton limpiar_btn;
	private JComboBox<String> cmb_marca;

	public VentanaABMRepuesto() {
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 779, 426);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel title_lbl = new JLabel("Nuevo repuesto");
		title_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		title_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		title_lbl.setBounds(10, 25, 743, 14);
		getContentPane().add(title_lbl);

		JLabel detalle_lbl = new JLabel("Detalle");
		detalle_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		detalle_lbl.setBounds(123, 64, 82, 14);
		getContentPane().add(detalle_lbl);

		detalle_txt = new JTextField();
		detalle_txt.setBounds(210, 61, 372, 20);
		getContentPane().add(detalle_txt);
		detalle_txt.setColumns(10);

		JLabel precio_lbl = new JLabel("Precio");
		precio_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		precio_lbl.setBounds(123, 109, 82, 14);
		getContentPane().add(precio_lbl);

		precio_txt = new JTextField();
		precio_txt.setColumns(10);
		precio_txt.setBounds(210, 106, 372, 20);
		getContentPane().add(precio_txt);

		JLabel stockMinimo_lbl = new JLabel("Stock M\u00EDnimo");
		stockMinimo_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		stockMinimo_lbl.setBounds(123, 157, 82, 14);
		getContentPane().add(stockMinimo_lbl);

		stockMinimo_txt = new JTextField();
		stockMinimo_txt.setColumns(10);
		stockMinimo_txt.setBounds(210, 154, 372, 20);
		getContentPane().add(stockMinimo_txt);

		guardar_btn = new JButton("Ingresar");
		guardar_btn.setBounds(317, 354, 121, 23);
		getContentPane().add(guardar_btn);

		JScrollPane repuesto_scrollPane = new JScrollPane();
		repuesto_scrollPane.setBounds(10, 246, 743, 91);
		getContentPane().add(repuesto_scrollPane);

		modelRepuesto = new DefaultTableModel(null, nombreColumnas);
		tablaRepuesto = new JTable(modelRepuesto);
		repuesto_scrollPane.setViewportView(tablaRepuesto);

		eliminarItem_btn = new JButton("Eliminar");
		eliminarItem_btn.setBounds(185, 354, 121, 23);
		getContentPane().add(eliminarItem_btn);

		limpiar_btn = new JButton("Limpiar");
		limpiar_btn.setBounds(448, 354, 121, 23);
		getContentPane().add(limpiar_btn);
		
		cmb_marca = new JComboBox<String>();
		cmb_marca.setBounds(210, 186, 372, 24);
		getContentPane().add(cmb_marca);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMarca.setBounds(123, 191, 82, 14);
		getContentPane().add(lblMarca);
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getCmb_marca() {
		return cmb_marca;
	}

	public JTextField getDetalle_txt() {
		return detalle_txt;
	}

	public JTextField getPrecio_txt() {
		return precio_txt;
	}

	public JTextField getStockMinimo_txt() {
		return stockMinimo_txt;
	}

	public JTable getTablaRepuesto() {
		return tablaRepuesto;
	}

	public DefaultTableModel getModelRepuesto() {
		return modelRepuesto;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JButton getGuardar_btn() {
		return guardar_btn;
	}

	public JButton getEliminarItem_btn() {
		return eliminarItem_btn;
	}

	public JButton getLimpiar_btn() {
		return limpiar_btn;
	}

	public void setLimpiar_btn(JButton limpiar_btn) {
		this.limpiar_btn = limpiar_btn;
	}
}
