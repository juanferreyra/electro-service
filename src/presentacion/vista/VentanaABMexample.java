package presentacion.vista;
/*
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaABMexample extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtnombre;
	private JButton btnGuardarLoc;
	private JButton btnEditar; 
	private JButton btnBorrar;
	private JTable tablaLocalidades;
	private DefaultTableModel modelLocalidades;
	private  String[] nombreColumnas = {"id", "Localidades"};
	private JTextField txtLocalidadAEditar;
	private JButton btnOk;
	private JButton btnCancel;

	public VentanaABMexample(Controlador controlador) {
		super();
		this.controlador = controlador;
		initialize();
		
	}
	
	private void initialize() {
		
		setTitle("Localidades");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 393, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 0, 370, 371);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnEditar= new JButton("Editar");
		btnEditar.setBounds(217, 94, 112, 23);
		btnEditar.addActionListener(this.controlador);
		panel.add(btnEditar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(217, 160, 112, 23);
		btnBorrar.addActionListener(this.controlador);
		panel.add(btnBorrar);
		
		btnGuardarLoc = new JButton("Añadir");
		btnGuardarLoc.setBounds(217, 42, 112, 25);
		btnGuardarLoc.addActionListener(this.controlador);
		panel.add(btnGuardarLoc);
		
		JLabel lblLocalidadNueva = new JLabel("Localidad nueva");
		lblLocalidadNueva.setBounds(29, 24, 141, 15);
		panel.add(lblLocalidadNueva);
		
		txtnombre = new JTextField();
		txtnombre.setColumns(10);
		txtnombre.setBounds(29, 39, 155, 19);
		panel.add(txtnombre);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 163, 155, 191);
		panel.add(scrollPane);
		
		
		modelLocalidades = new DefaultTableModel(null,nombreColumnas);
		tablaLocalidades = new JTable(modelLocalidades);
		scrollPane.setViewportView(tablaLocalidades);
		
		JLabel lblLocalidadEditar = new JLabel("Localidad a editar");
		lblLocalidadEditar.setBounds(29, 69, 155, 15);
		panel.add(lblLocalidadEditar);
		
		txtLocalidadAEditar = new JTextField();
		txtLocalidadAEditar.setColumns(10);
		txtLocalidadAEditar.setBounds(29, 84, 155, 19);
		
		panel.add(txtLocalidadAEditar);
		
		btnOk = new JButton("ok");
		btnOk.setBounds(29, 115, 51, 25);
		btnOk.addActionListener(this.controlador);
		panel.add(btnOk);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(92, 115, 90, 25);
		btnCancel.addActionListener(this.controlador);
		panel.add(btnCancel);
	}
		

	public JButton getBtnAgregarLocalidad() {
		return btnGuardarLoc;
	}

	public JTextField getTxtNombre() {
		return txtnombre;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}


	public JButton getBtnBorrar() {
		return btnBorrar;
	}


	public DefaultTableModel getModelLocalidades() {
		return modelLocalidades;
	}


	public String[] getNombreColumnas() {
		return nombreColumnas;
	}


	public JTable getTablaLocalidades() {
		return tablaLocalidades;
	}

	public JTextField getTxtLocalidadAEditar() {
		return txtLocalidadAEditar;
	}

	public JButton getBtnOk() {
		return btnOk;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}
}
*/
