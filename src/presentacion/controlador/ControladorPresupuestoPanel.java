package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentacion.vista.PresupuestoPanel;

public class ControladorPresupuestoPanel implements ActionListener {

	private PresupuestoPanel presupuestoPanel;
	private int cantidadComponente;

	public ControladorPresupuestoPanel(PresupuestoPanel presupuestoPanel) {
		this.presupuestoPanel = presupuestoPanel;
		this.setCantidadComponente(0);
		// this.presupuestoPanel.getValorPresupuestado_txf().addActionListener(this);
		this.presupuestoPanel.getIconMas_btn().addActionListener(this);
		this.presupuestoPanel.getIconMenos_btn().addActionListener(this);
		this.presupuestoPanel.getIconIngreso_btn().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == (this.presupuestoPanel.getIconIngreso_btn())) {
		} else if (e.getSource() == (this.presupuestoPanel.getIconMas_btn())) {
			incrementoCantidadComponente();
			setCantidadComponente(this.cantidadComponente);
		} else if (e.getSource() == (this.presupuestoPanel.getIconMenos_btn())) {
			decrementoCantidadComponente();
			setCantidadComponente(this.cantidadComponente);
		}
	}

	public PresupuestoPanel getPresupuestoPanel() {
		return presupuestoPanel;
	}

	public Object[][] cargarInformacionTabla() {
		// Hardcodeo, cambiar por datos de la BD
		Object[][] informacionTabla = { { "Placa", new Integer(5) }, { "Boton", new Integer(3) } };
		return informacionTabla;
	}

	public void incrementoCantidadComponente() {
		this.setCantidadComponente(this.getCantidadComponente() + 1);
	}

	public void decrementoCantidadComponente() {
		if (this.getCantidadComponente() >= 1) {
			this.setCantidadComponente(this.getCantidadComponente() - 1);
		}
	}

	public int getCantidadComponente() {
		return this.cantidadComponente;
	}

	public void setCantidadComponente(int cantidad) {
		this.cantidadComponente = cantidad;
		this.presupuestoPanel.setCantidad_lbl(cantidad);
	}

}
