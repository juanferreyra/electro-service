package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentacion.vista.VentanaABMMarcaProducto;

public class ControladorABMMarcaProducto implements ActionListener {

	private VentanaABMMarcaProducto ventanaABMMarcaProducto;

	public ControladorABMMarcaProducto(VentanaABMMarcaProducto ventanaABMProducto) {
		this.ventanaABMMarcaProducto = ventanaABMProducto;
		this.ventanaABMMarcaProducto.getCancelar_btn().addActionListener(this);
		this.ventanaABMMarcaProducto.getEliminarItem_btn().addActionListener(this);
		this.ventanaABMMarcaProducto.getGuardar_btn().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.ventanaABMMarcaProducto.getCancelar_btn()) {
			this.ventanaABMMarcaProducto.dispose();
		} else if (e.getSource() == this.ventanaABMMarcaProducto.getEliminarItem_btn()) {

		} else if (e.getSource() == this.ventanaABMMarcaProducto.getGuardar_btn()) {

		}

	}

	public static void main(String[] args) {

		VentanaABMMarcaProducto abm = new VentanaABMMarcaProducto();
		ControladorABMMarcaProducto c = new ControladorABMMarcaProducto(abm);
		c.inicializar();

	}

	private void inicializar() {

		this.ventanaABMMarcaProducto.setVisible(true);

		cargarTablaMarcaProducto();

	}

	private void cargarTablaMarcaProducto() {
		// TODO Auto-generated method stub

	}
}
