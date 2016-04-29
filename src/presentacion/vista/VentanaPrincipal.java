package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.table.DefaultTableModel;

import dto.UsuarioDTO;
public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable ordenesDeTrabajo_table;

	@SuppressWarnings("serial")
	public VentanaPrincipal(UsuarioDTO user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1044, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1028, 21);
		contentPane.add(menuBar);

		JLabel ordenesDeTrabajo_lbl = new JLabel("<html><b>\u00D3rdenes de Trabajo</b></html>");
		ordenesDeTrabajo_lbl.setBounds(10, 43, 992, 14);
		ordenesDeTrabajo_lbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ordenesDeTrabajo_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(ordenesDeTrabajo_lbl);

		// TABLA PRINCIPAL DEL SISTEMA

		ordenesDeTrabajo_table = new JTable();
		ordenesDeTrabajo_table.setModel(new DefaultTableModel(
			new Object[][] {
				{3,"Botón","Fulanito",true,new JButton(),new JButton() , new JButton(), false, false, false, new JButton(), new JButton(),"Presupuestado"},
			},
			new String[] {
				"N\u00FAmero ", "Producto", "Cliente", "Flete", "Constancia", "Comprobante", "Presupuesto", "Avisado", "Aceptado", "Reparado", "Informar", "Retirar", "Estado"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Boolean.class, JButton.class, JButton.class, JButton.class, Boolean.class, Boolean.class, Boolean.class, JButton.class, JButton.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		ordenesDeTrabajo_table.setBounds(new Rectangle(0, 0, 992, 301));
		JScrollPane ordenesDeTrabajo_scrollPane = new JScrollPane();
		ordenesDeTrabajo_scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ordenesDeTrabajo_scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		ordenesDeTrabajo_scrollPane.setBounds(10, 82, 1005, 301);
		ordenesDeTrabajo_scrollPane.add(ordenesDeTrabajo_table);
		ordenesDeTrabajo_scrollPane.setViewportView(ordenesDeTrabajo_table);

		 contentPane.add(ordenesDeTrabajo_scrollPane);
	}
}
