package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentacion.vista.OrdenDeTrabajoPanel;

public class ControladorOrdenDeTrabajoPanel implements ActionListener {

	private OrdenDeTrabajoPanel ordenDeTrabajoPanel;

	public ControladorOrdenDeTrabajoPanel(OrdenDeTrabajoPanel ordenDeTrabajoPanel) {
		this.ordenDeTrabajoPanel = ordenDeTrabajoPanel;
		this.ordenDeTrabajoPanel.getGenerarDocumentos_Btn().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == (this.ordenDeTrabajoPanel.getGenerarDocumentos_Btn())) {
			// Generar reportes ticket cliente y Comprobante tecnico
		}
	}

	public int getIDOrdenDeTrabajo() {
		// Conseguir el ID de BD para una orden de trabajo.
		return 0;
	}

	public OrdenDeTrabajoPanel getOrdenDeTrabajoPanel() {
		return ordenDeTrabajoPanel;
	}

	public void setOrdenDeTrabajoPanel(OrdenDeTrabajoPanel ordenDeTrabajoPanel) {
		this.ordenDeTrabajoPanel = ordenDeTrabajoPanel;
	}


}
