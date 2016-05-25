package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;

public class VentanaABMTipoProducto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField detalle_txt;

	public VentanaABMTipoProducto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton cancelar_btn = new JButton("Cancelar");
		cancelar_btn.setBounds(315, 228, 89, 23);
		getContentPane().add(cancelar_btn);

		JButton guardar_btn = new JButton("Guardar");
		guardar_btn.setBounds(216, 228, 89, 23);
		getContentPane().add(guardar_btn);
		
		JLabel title_lbl = new JLabel("Nuevo tipo de producto");
		title_lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		title_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		title_lbl.setBounds(10, 11, 414, 14);
		contentPane.add(title_lbl);
		
		JLabel detalle_lbl = new JLabel("Detalle");
		detalle_lbl.setBounds(42, 76, 76, 14);
		contentPane.add(detalle_lbl);
		
		detalle_txt = new JTextField();
		detalle_txt.setBounds(104, 73, 300, 20);
		contentPane.add(detalle_txt);
		detalle_txt.setColumns(10);
	}

}
