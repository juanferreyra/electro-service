package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentacion.vista.VentanaListaIngresos;

public class ControladorListaIngresos  implements ActionListener{
	
	private VentanaListaIngresos listaIngresos;
	
	public ControladorListaIngresos(VentanaListaIngresos vista) {
		this.listaIngresos = vista;
	}

	public void inicializar() {
		this.listaIngresos.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}