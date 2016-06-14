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
import javax.swing.JTextField;
import java.awt.Font;

public class VentanaABMMarcaProducto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField detalle_txt;
	private JTable tablaMarcaProducto;
	private DefaultTableModel modelMarcaProducto;
	private String[] nombreColumnas = { "Detalle" };
	private JButton eliminarItem_btn;
	private JButton ingresar_btn;
	private JButton limpiar_btn;

	public VentanaABMMarcaProducto() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 658, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel title_lbl = new JLabel("Nueva marca de producto");
		title_lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		title_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		title_lbl.setBounds(10, 11, 622, 14);
		contentPane.add(title_lbl);

		JLabel detalle_lbl = new JLabel("Detalle");
		detalle_lbl.setBounds(83, 76, 76, 14);
		contentPane.add(detalle_lbl);

		detalle_txt = new JTextField();
		detalle_txt.setBounds(128, 73, 459, 20);
		contentPane.add(detalle_txt);
		detalle_txt.setColumns(10);

		JScrollPane marcaProducto_scrollPane = new JScrollPane();
		marcaProducto_scrollPane.setBounds(83, 135, 504, 91);
		getContentPane().add(marcaProducto_scrollPane);

		modelMarcaProducto = new DefaultTableModel(null, nombreColumnas) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int fila, int columna) {

				return false;
			}
		};

		tablaMarcaProducto = new JTable(modelMarcaProducto);
		marcaProducto_scrollPane.setViewportView(tablaMarcaProducto);

		eliminarItem_btn = new JButton("Eliminar");
		eliminarItem_btn.setBounds(147, 237, 121, 23);
		getContentPane().add(eliminarItem_btn);

		ingresar_btn = new JButton("Ingresar");
		ingresar_btn.setBounds(280, 237, 121, 23);
		contentPane.add(ingresar_btn);

		limpiar_btn = new JButton("Limpiar");
		limpiar_btn.setBounds(411, 237, 121, 23);
		contentPane.add(limpiar_btn);
	}

	public JTextField getDetalle_txt() {
		return detalle_txt;
	}

	public void setDetalle_txt(JTextField detalle_txt) {
		this.detalle_txt = detalle_txt;
	}

	public JButton getEliminarItem_btn() {
		return eliminarItem_btn;
	}

	public void setEliminarItem_btn(JButton eliminarItem_btn) {
		this.eliminarItem_btn = eliminarItem_btn;
	}

	public JTable getTablaMarcaProducto() {
		return tablaMarcaProducto;
	}

	public void setTablaMarcaProducto(JTable tablaMarcaProducto) {
		this.tablaMarcaProducto = tablaMarcaProducto;
	}

	public JButton getIngresar_btn() {
		return ingresar_btn;
	}

	public void setIngresar_btn(JButton ingresar_btn) {
		this.ingresar_btn = ingresar_btn;
	}

	public JButton getLimpiar_btn() {
		return limpiar_btn;
	}

	public void setLimpiar_btn(JButton limpiar_btn) {
		this.limpiar_btn = limpiar_btn;
	}

}
