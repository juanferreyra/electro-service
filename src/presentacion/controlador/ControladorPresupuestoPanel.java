package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentacion.vista.PresupuestoPanel;

public class ControladorPresupuestoPanel implements ActionListener {

	private PresupuestoPanel presupuestoPanel;

	public ControladorPresupuestoPanel(PresupuestoPanel presupuestoPanel) {
		this.presupuestoPanel = presupuestoPanel;
		// this.presupuestoPanel.getValorPresupuestado_txf().addActionListener(this);
	}

	// public Object[][] cargarInformacionTabla() {
	// // Hardcodeo, cambiar por datos de la BD
	// Object[][] informacionTabla = { { "Placa", new Integer(5) }, { "Boton",
	// new Integer(3) } };
	// return informacionTabla;
	// }

	@Override
	public void actionPerformed(ActionEvent e) {
		// if (e.getSource() == (this.presupuestoPanel.)) {
		// }
	}

	public PresupuestoPanel getPresupuestoPanel() {
		return presupuestoPanel;
	}

}
