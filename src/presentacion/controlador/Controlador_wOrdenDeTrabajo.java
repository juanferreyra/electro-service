package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import presentacion.vista.wOrdenDeTrabajo;

public class Controlador_wOrdenDeTrabajo implements ActionListener {

	private wOrdenDeTrabajo wOrdenDeTrabajo;

	public Controlador_wOrdenDeTrabajo(wOrdenDeTrabajo wOrdenDeTrabajo) {
		this.wOrdenDeTrabajo = wOrdenDeTrabajo;
		
	}

	public Object[][] cargarInformacionTabla() {
		// Hardcodeo, cambiar por datos de la BD
		Object[][] informacionTabla = { { "Placa", new Integer(5) }, { "Boton", new Integer(3) } };
		return informacionTabla;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == ( this.wOrdenDeTrabajo.getSubPanel_ordenDeTrabajo().getG)) {
//
//		}
	}

}
