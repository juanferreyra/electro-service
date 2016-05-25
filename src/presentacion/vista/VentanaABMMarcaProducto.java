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

	public VentanaABMMarcaProducto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton cancelar_btn = new JButton("Cancelar");
		cancelar_btn.setBounds(500, 252, 89, 23);
		getContentPane().add(cancelar_btn);

		JButton guardar_btn = new JButton("Guardar");
		guardar_btn.setBounds(401, 252, 89, 23);
		getContentPane().add(guardar_btn);

		JLabel title_lbl = new JLabel("Nuevo marca de producto");
		title_lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		title_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		title_lbl.setBounds(10, 11, 622, 14);
		contentPane.add(title_lbl);

		JLabel detalle_lbl = new JLabel("Detalle");
		detalle_lbl.setBounds(83, 76, 76, 14);
		contentPane.add(detalle_lbl);

		detalle_txt = new JTextField();
		detalle_txt.setBounds(128, 73, 455, 20);
		contentPane.add(detalle_txt);
		detalle_txt.setColumns(10);

		JScrollPane marcaProducto_scrollPane = new JScrollPane();
		marcaProducto_scrollPane.setBounds(83, 135, 504, 91);
		getContentPane().add(marcaProducto_scrollPane);

		modelMarcaProducto = new DefaultTableModel(null, nombreColumnas);
		tablaMarcaProducto = new JTable(modelMarcaProducto);
		marcaProducto_scrollPane.setViewportView(tablaMarcaProducto);

		JButton eliminarItem_btn = new JButton("Eliminar Item");
		eliminarItem_btn.setBounds(269, 252, 121, 23);
		getContentPane().add(eliminarItem_btn);
	}

}
