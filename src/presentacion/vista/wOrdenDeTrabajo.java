package presentacion.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;

public class wOrdenDeTrabajo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					wOrdenDeTrabajo frame = new wOrdenDeTrabajo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public wOrdenDeTrabajo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 915, 812);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(0, 0, 899, 774);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panelPrincipal.add(splitPane);

		splitPane.setDividerLocation(400);
		splitPane.setDividerSize(5);


		JPanel subPanel_ordenDeTrabajo = new OrdenDeTrabajoPanel();
		subPanel_ordenDeTrabajo.setBounds(10, 423, 879, 340);

		JPanel subPanel_presupuesto = new PresupuestoPanel();
		subPanel_presupuesto.setBounds(10, 423, 879, 340);

		splitPane.setTopComponent(subPanel_ordenDeTrabajo);
		splitPane.setBottomComponent(subPanel_presupuesto);

	}
}
