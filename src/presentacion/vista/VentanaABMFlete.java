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
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;

public class VentanaABMFlete extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablaFlete;
	private DefaultTableModel modelFlete;
	private String[] nombreColumnas = { "Nombre", "Documento", "Modelo transporte", "Patente transporte", "Teléfono",
			"Fecha vto. licencia" };
	private JTextField nombre_txf;
	private JTextField documento_txf;
	private JTextField modeloTransporte_txf;
	private JTextField patenteTransporte_txf;
	private JTextField telefono_txf;

	public VentanaABMFlete() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 787, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton cancelar_btn = new JButton("Cancelar");
		cancelar_btn.setBounds(627, 363, 89, 23);
		getContentPane().add(cancelar_btn);

		JButton guardar_btn = new JButton("Guardar");
		guardar_btn.setBounds(528, 363, 89, 23);
		getContentPane().add(guardar_btn);

		JScrollPane flete_scrollPane = new JScrollPane();
		flete_scrollPane.setBounds(74, 246, 644, 91);
		getContentPane().add(flete_scrollPane);

		modelFlete = new DefaultTableModel(null, nombreColumnas);
		tablaFlete = new JTable(modelFlete);
		flete_scrollPane.setViewportView(tablaFlete);

		JButton eliminarItem_btn = new JButton("Eliminar Item");
		eliminarItem_btn.setBounds(397, 363, 121, 23);
		getContentPane().add(eliminarItem_btn);
		
		JLabel title_lbl = new JLabel("Nuevo fletero");
		title_lbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		title_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		title_lbl.setBounds(74, 11, 644, 14);
		contentPane.add(title_lbl);
		
		JLabel nombre_lbl = new JLabel("Nombre");
		nombre_lbl.setBounds(74, 62, 126, 14);
		contentPane.add(nombre_lbl);
		
		JLabel documento_lbl = new JLabel("Documento");
		documento_lbl.setBounds(74, 87, 126, 14);
		contentPane.add(documento_lbl);
		
		JLabel modeloTransporte_lbl = new JLabel("Modelo transporte");
		modeloTransporte_lbl.setBounds(74, 112, 126, 14);
		contentPane.add(modeloTransporte_lbl);
		
		JLabel patenteTransporte_lbl = new JLabel("Patente transporte");
		patenteTransporte_lbl.setBounds(74, 137, 168, 14);
		contentPane.add(patenteTransporte_lbl);
		
		JLabel telefono_lbl = new JLabel("Tel\u00E9fono");
		telefono_lbl.setBounds(74, 162, 168, 14);
		contentPane.add(telefono_lbl);
		
		JLabel fechaLicencia_lbl = new JLabel("Fecha vto. licencia ");
		fechaLicencia_lbl.setBounds(74, 193, 168, 14);
		contentPane.add(fechaLicencia_lbl);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(220, 187, 129, 20);
		contentPane.add(dateChooser);
		
		nombre_txf = new JTextField();
		nombre_txf.setBounds(220, 59, 496, 20);
		contentPane.add(nombre_txf);
		nombre_txf.setColumns(10);
		
		documento_txf = new JTextField();
		documento_txf.setColumns(10);
		documento_txf.setBounds(220, 84, 496, 20);
		contentPane.add(documento_txf);
		
		modeloTransporte_txf = new JTextField();
		modeloTransporte_txf.setColumns(10);
		modeloTransporte_txf.setBounds(220, 109, 496, 20);
		contentPane.add(modeloTransporte_txf);
		
		patenteTransporte_txf = new JTextField();
		patenteTransporte_txf.setColumns(10);
		patenteTransporte_txf.setBounds(220, 137, 496, 20);
		contentPane.add(patenteTransporte_txf);
		
		telefono_txf = new JTextField();
		telefono_txf.setColumns(10);
		telefono_txf.setBounds(220, 162, 496, 20);
		contentPane.add(telefono_txf);

	}
}
