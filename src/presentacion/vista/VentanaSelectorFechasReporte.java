package presentacion.vista;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import java.awt.Toolkit;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class VentanaSelectorFechasReporte extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JRadioButton rdbtnAnual;
	private JRadioButton rdbtnMensual;
	private JRadioButton rdbtnSemanal;
	private JRadioButton rdbtnEntreFechas;
	private JYearChooser anio_Anual;
	private JMonthChooser mes_mensual;
	private JYearChooser anio_mensual;
	private JDateChooser fecha_semana;
	private JDateChooser desde_entrefechas;
	private JDateChooser hasta_entrefechas;
	private ButtonGroup tipoFecha;
	private JButton btnGenerarReporte;

	public VentanaSelectorFechasReporte() {
		setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaSelectorFechasReporte.class.getResource("/chart-pie.png")));
		setType(Type.POPUP);
		setTitle("Selector de Fechas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*
		 * Anual
		 */
		
		rdbtnAnual = new JRadioButton("Anual");
		rdbtnAnual.setBounds(5, 5, 100, 25);
		contentPane.add(rdbtnAnual);
		
		JLabel lblAo_1 = new JLabel("A\u00F1o:");
		lblAo_1.setBounds(111, 35, 27, 25);
		contentPane.add(lblAo_1);
		
		anio_Anual = new JYearChooser();
		anio_Anual.setSize(100, 25);
		anio_Anual.setLocation(150, 35);
		contentPane.add(anio_Anual);
		
		/*
		 * Mensual
		 */
		
		rdbtnMensual = new JRadioButton("Mensual");
		rdbtnMensual.setBounds(5, 65, 100, 25);
		contentPane.add(rdbtnMensual);
		
		JLabel lblMes = new JLabel("Mes:");
		lblMes.setBounds(111, 100, 27, 25);
		contentPane.add(lblMes);
		
		JLabel lblAo = new JLabel("A\u00F1o:");
		lblAo.setBounds(284, 109, 34, 17);
		contentPane.add(lblAo);
		
		mes_mensual = new JMonthChooser();
		mes_mensual.setSize(114,25);
		mes_mensual.setLocation(150, 100);
		contentPane.add(mes_mensual);
		
		anio_mensual = new JYearChooser();
		anio_mensual.setSize(100, 25);
		anio_mensual.setLocation(324, 100);
		contentPane.add(anio_mensual);
		
		/*
		 * Semanal
		 */
		
		rdbtnSemanal = new JRadioButton("Semanal");
		rdbtnSemanal.setBounds(5, 130, 86, 25);
		contentPane.add(rdbtnSemanal);
		
		JLabel lblFechaDeSemana = new JLabel("Fecha de semana:");
		lblFechaDeSemana.setBounds(111, 160, 100, 25);
		contentPane.add(lblFechaDeSemana);
		
		fecha_semana = new JDateChooser();
		fecha_semana.setBounds(210, 160, 100, 25);
		contentPane.add(fecha_semana);		
		
		/*
		 * Entre Fechas
		 */
		
		rdbtnEntreFechas = new JRadioButton("Entre Fechas");
		rdbtnEntreFechas.setBounds(5, 195, 100, 25);
		contentPane.add(rdbtnEntreFechas);
		
		JLabel lblDesde = new JLabel("Desde:");
		lblDesde.setBounds(111, 230, 40, 25);
		contentPane.add(lblDesde);
		
		JLabel lblHasta = new JLabel("Hasta:");
		lblHasta.setBounds(260, 230, 40, 25);
		contentPane.add(lblHasta);
		
		desde_entrefechas = new JDateChooser();
		desde_entrefechas.setBounds(155, 230, 100, 25);
		contentPane.add(desde_entrefechas);
		
		hasta_entrefechas = new JDateChooser();
		hasta_entrefechas.setBounds(302, 230, 100, 25);
		contentPane.add(hasta_entrefechas);
		
		/*
		 * Grupo de Radio Button 
		 */
		
		tipoFecha = new ButtonGroup();
		rdbtnAnual.setSelected(true);
		tipoFecha.add(rdbtnAnual);
		tipoFecha.add(rdbtnMensual);
		tipoFecha.add(rdbtnSemanal);
		tipoFecha.add(rdbtnEntreFechas);
		
		btnGenerarReporte = new JButton("Generar Reporte");
		btnGenerarReporte.setIcon(new ImageIcon(VentanaSelectorFechasReporte.class.getResource("/chart-bar-outline.png")));
		btnGenerarReporte.setBounds(22, 279, 380, 36);
		contentPane.add(btnGenerarReporte);
		
	}
	
	public JButton getBtnGenerarReporte() {
		return btnGenerarReporte;
	}

	public ButtonGroup getTipoFecha() {
		return tipoFecha;
	}

	public JRadioButton getRdbtnAnual() {
		return rdbtnAnual;
	}

	public JRadioButton getRdbtnMensual() {
		return rdbtnMensual;
	}

	public JRadioButton getRdbtnSemanal() {
		return rdbtnSemanal;
	}

	public JRadioButton getRdbtnEntreFechas() {
		return rdbtnEntreFechas;
	}
	
	public JYearChooser getAnio_Anual() {
		return anio_Anual;
	}

	public JMonthChooser getMes_mensual() {
		return mes_mensual;
	}

	public JYearChooser getAnio_mensual() {
		return anio_mensual;
	}

	public JDateChooser getFecha_semana() {
		return fecha_semana;
	}

	public JDateChooser getDesde_entrefechas() {
		return desde_entrefechas;
	}

	public JDateChooser getHasta_entrefechas() {
		return hasta_entrefechas;
	}
}
