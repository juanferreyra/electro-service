package presentacion.vista;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import presentacion.controlador.ControladorOrdenDeTrabajoPanel;
import presentacion.controlador.ControladorPresupuestoPanel;

import javax.swing.JSplitPane;
import java.awt.Color;
import javax.swing.JButton;

public class wOrdenDeTrabajo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private JPanel subPanel_ordenDeTrabajo;
	private JPanel subPanel_presupuesto;
	private JButton ImprimirPresupuesto_btn;



	public wOrdenDeTrabajo(ControladorPresupuestoPanel controladorPresupuestoPanel,
			ControladorOrdenDeTrabajoPanel controladorOrdenDeTrabajoPanel) {
		setBackground(Color.WHITE);
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 905, 858);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Color.WHITE);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		setLocationRelativeTo(null);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(0, 0, 897, 774);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panelPrincipal.add(splitPane);

		splitPane.setDividerLocation(400);
		splitPane.setDividerSize(5);

		subPanel_ordenDeTrabajo = controladorOrdenDeTrabajoPanel.getOrdenDeTrabajoPanel();
		subPanel_ordenDeTrabajo.setBounds(10, 423, 879, 340);

		subPanel_presupuesto = controladorPresupuestoPanel.getPresupuestoPanel();
		subPanel_presupuesto.setBounds(10, 423, 879, 340);

		splitPane.setTopComponent(subPanel_ordenDeTrabajo);
		splitPane.setBottomComponent(subPanel_presupuesto);
		
		ImprimirPresupuesto_btn = new JButton("Imprimir Presupuesto");
		ImprimirPresupuesto_btn.setBounds(359, 785, 169, 23);
		panelPrincipal.add(ImprimirPresupuesto_btn);

	}

	public JPanel getSubPanel_ordenDeTrabajo() {
		return subPanel_ordenDeTrabajo;
	}

	public void setSubPanel_ordenDeTrabajo(JPanel subPanel_ordenDeTrabajo) {
		this.subPanel_ordenDeTrabajo = subPanel_ordenDeTrabajo;
	}

	public JPanel getSubPanel_presupuesto() {
		return subPanel_presupuesto;
	}

	public void setSubPanel_presupuesto(JPanel subPanel_presupuesto) {
		this.subPanel_presupuesto = subPanel_presupuesto;
	}
}
