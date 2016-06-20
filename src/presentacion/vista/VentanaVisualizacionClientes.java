package presentacion.vista;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class VentanaVisualizacionClientes extends JFrame {

	private DefaultTableModel modelClientes;
	private String[] nombreColumnas = { "id", "Nombre", "Apellido", "DNI", "Localidad", "Direcci\u00f3n",
			"Tel\u00e9fono", "Email" };
	private JTable tablaClientes;

	public VentanaVisualizacionClientes() {
		setResizable(false);
		initialize();
	}

	private void initialize() {

		setBounds(100, 100, 700, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPresentacion.class.getResource("/logo.png")));


		JScrollPane clientes_scrollPane = new JScrollPane();
		clientes_scrollPane.setBounds(10, 43, 674, 418);
		getContentPane().add(clientes_scrollPane);

		modelClientes = new DefaultTableModel(null, nombreColumnas) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int fila, int columna) {

				return false;
			}
		};

		tablaClientes = new JTable(modelClientes);
		clientes_scrollPane.setViewportView(tablaClientes);

		JLabel lblSeleccioneUnCliente = new JLabel("Seleccione un cliente");
		lblSeleccioneUnCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccioneUnCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeleccioneUnCliente.setBounds(10, 11, 706, 14);
		getContentPane().add(lblSeleccioneUnCliente);
	}

	public JTable getTablaClientes() {
		return this.tablaClientes;
	}

	public DefaultTableModel getModelClientes() {
		return this.modelClientes;
	}

	public String[] getNombreColumnas() {
		return this.nombreColumnas;
	}
}
