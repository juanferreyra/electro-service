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

public class VentanaABMRepuesto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField detalle_txt;
	private JTextField precio_txt;
	private JTextField stockMinimo_txt;
	private JTable tablaRepuesto;
	private DefaultTableModel modelRepuesto;
	private String[] nombreRepuesto = { "Detalle", "Precio", "Stock Mínimo" };

	public VentanaABMRepuesto() {
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 779, 464);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel title_lbl = new JLabel("Nuevo repuesto");
		title_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		title_lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		title_lbl.setBounds(10, 25, 743, 14);
		getContentPane().add(title_lbl);

		JLabel detalle_lbl = new JLabel("Detalle");
		detalle_lbl.setBounds(123, 101, 82, 14);
		getContentPane().add(detalle_lbl);

		detalle_txt = new JTextField();
		detalle_txt.setBounds(199, 98, 383, 20);
		getContentPane().add(detalle_txt);
		detalle_txt.setColumns(10);

		JLabel precio_lbl = new JLabel("Precio");
		precio_lbl.setBounds(123, 146, 82, 14);
		getContentPane().add(precio_lbl);

		precio_txt = new JTextField();
		precio_txt.setColumns(10);
		precio_txt.setBounds(199, 143, 383, 20);
		getContentPane().add(precio_txt);

		JLabel stockMinimo_lbl = new JLabel("Stock M\u00EDnimo");
		stockMinimo_lbl.setBounds(123, 194, 82, 14);
		getContentPane().add(stockMinimo_lbl);

		stockMinimo_txt = new JTextField();
		stockMinimo_txt.setColumns(10);
		stockMinimo_txt.setBounds(199, 191, 383, 20);
		getContentPane().add(stockMinimo_txt);

		JButton cancelar_btn = new JButton("Cancelar");
		cancelar_btn.setBounds(664, 392, 89, 23);
		getContentPane().add(cancelar_btn);

		JButton guardar_btn = new JButton("Guardar");
		guardar_btn.setBounds(565, 392, 89, 23);
		getContentPane().add(guardar_btn);

		JScrollPane repuesto_scrollPane = new JScrollPane();
		repuesto_scrollPane.setBounds(123, 246, 459, 91);
		getContentPane().add(repuesto_scrollPane);

		modelRepuesto = new DefaultTableModel(null, nombreRepuesto);
		tablaRepuesto = new JTable(modelRepuesto);
		repuesto_scrollPane.setViewportView(tablaRepuesto);

		JButton eliminarItem_btn = new JButton("Eliminar Item");
		eliminarItem_btn.setBounds(434, 392, 121, 23);
		getContentPane().add(eliminarItem_btn);
	}
}
