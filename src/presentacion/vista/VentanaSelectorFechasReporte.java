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
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;

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
	private JRadioButton rdbtnTipoproducto;
	private JRadioButton rdbtnPorMarca;
	private JRadioButton rdbtnPorMarcaY;
	private ButtonGroup tipoMarca;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_3;

	public VentanaSelectorFechasReporte() {
		setAlwaysOnTop(true);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(VentanaSelectorFechasReporte.class.getResource("/chart-pie.png")));
		setType(Type.POPUP);
		setTitle("Selector de Fechas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 622, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		setLocationRelativeTo(null);

		/*
		 * Anual
		 */
		contentPane.setLayout(null);

		rdbtnAnual = new JRadioButton("Anual");
		rdbtnAnual.setForeground(new Color(70, 130, 180));
		rdbtnAnual.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnAnual.setBounds(22, 21, 163, 25);
		contentPane.add(rdbtnAnual);

		JLabel lblAo_1 = new JLabel("A\u00F1o");
		lblAo_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAo_1.setBounds(355, 21, 144, 25);
		contentPane.add(lblAo_1);

		anio_Anual = new JYearChooser();
		anio_Anual.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 12));
		anio_Anual.getSpinner().setSize(100, 25);
		anio_Anual.getSpinner().setLocation(0, 35);
		anio_Anual.setBounds(420, 20, 100, 25);
		contentPane.add(anio_Anual);

		/*
		 * Mensual
		 */

		rdbtnMensual = new JRadioButton("Mensual");
		rdbtnMensual.setForeground(new Color(70, 130, 180));
		rdbtnMensual.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnMensual.setBounds(22, 90, 80, 25);
		contentPane.add(rdbtnMensual);

		JLabel lblMes = new JLabel("Mes");
		lblMes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMes.setBounds(120, 92, 80, 25);
		contentPane.add(lblMes);

		JLabel lblAo = new JLabel("A\u00F1o");
		lblAo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAo.setBounds(355, 94, 144, 17);
		contentPane.add(lblAo);

		mes_mensual = new JMonthChooser();
		mes_mensual.getSpinner().setBounds(0, 0, 153, 25);
		mes_mensual.setBounds(155, 92, 153, 25);
		contentPane.add(mes_mensual);
		mes_mensual.setLayout(null);

		anio_mensual = new JYearChooser();
		anio_mensual.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 12));
		anio_mensual.getSpinner().setLocation(0, 98);
		anio_mensual.setBounds(420, 92, 100, 25);
		contentPane.add(anio_mensual);

		/*
		 * Semanal
		 */

		rdbtnSemanal = new JRadioButton("Semanal");
		rdbtnSemanal.setForeground(new Color(70, 130, 180));
		rdbtnSemanal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnSemanal.setBounds(22, 162, 86, 25);
		contentPane.add(rdbtnSemanal);

		JLabel lblFechaDeSemana = new JLabel("Semana");
		lblFechaDeSemana.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaDeSemana.setBounds(355, 162, 133, 25);
		contentPane.add(lblFechaDeSemana);

		fecha_semana = new JDateChooser();
		fecha_semana.setBounds(425, 160, 100, 25);
		contentPane.add(fecha_semana);

		/*
		 * Entre Fechas
		 */

		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDesde.setBounds(145, 232, 40, 25);
		contentPane.add(lblDesde);

		rdbtnEntreFechas = new JRadioButton("Entre Fechas");
		rdbtnEntreFechas.setForeground(new Color(70, 130, 180));
		rdbtnEntreFechas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnEntreFechas.setBounds(22, 232, 163, 25);
		contentPane.add(rdbtnEntreFechas);

		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHasta.setBounds(355, 232, 40, 25);
		contentPane.add(lblHasta);

		desde_entrefechas = new JDateChooser();
		desde_entrefechas.setBounds(200, 232, 100, 25);
		contentPane.add(desde_entrefechas);

		hasta_entrefechas = new JDateChooser();
		hasta_entrefechas.setBounds(425, 232, 100, 25);
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
		btnGenerarReporte.setBounds(190, 385, 212, 36);
		btnGenerarReporte
				.setIcon(new ImageIcon(VentanaSelectorFechasReporte.class.getResource("/chart-bar-outline.png")));
		contentPane.add(btnGenerarReporte);
		
				separator_3 = new JSeparator();
				separator_3.setBounds(10, 273, 586, 9);
				contentPane.add(separator_3);

		rdbtnTipoproducto = new JRadioButton("Por tipo de producto");
		rdbtnTipoproducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnTipoproducto.setBounds(190, 300, 286, 23);
		contentPane.add(rdbtnTipoproducto);

		rdbtnPorMarca = new JRadioButton("Por marca");
		rdbtnPorMarca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnPorMarca.setBounds(190, 326, 286, 23);
		contentPane.add(rdbtnPorMarca);

		rdbtnPorMarcaY = new JRadioButton("Por marca y tipo de producto");
		rdbtnPorMarcaY.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnPorMarcaY.setBounds(190, 353, 286, 25);
		contentPane.add(rdbtnPorMarcaY);

		tipoMarca = new ButtonGroup();
		rdbtnTipoproducto.setSelected(true);
		tipoMarca.add(rdbtnTipoproducto);
		tipoMarca.add(rdbtnPorMarca);
		tipoMarca.add(rdbtnPorMarcaY);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 67, 586, 9);
		contentPane.add(separator);

		separator_1 = new JSeparator();
		separator_1.setBounds(10, 132, 586, 9);
		contentPane.add(separator_1);

		separator_2 = new JSeparator();
		separator_2.setBounds(10, 202, 586, 9);
		contentPane.add(separator_2);

	}

	public JRadioButton getRdbtnTipoproducto() {
		return rdbtnTipoproducto;
	}

	public JRadioButton getRdbtnPorMarca() {
		return rdbtnPorMarca;
	}

	public JRadioButton getRdbtnPorMarcaY() {
		return rdbtnPorMarcaY;
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
