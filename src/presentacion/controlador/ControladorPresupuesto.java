package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentacion.vista.VentanaPresupuesto;

public class ControladorPresupuesto implements ActionListener{
	
	private VentanaPresupuesto ventanaPresupuesto;
	
	
	public ControladorPresupuesto( VentanaPresupuesto ventanaPresupuesto) {
		
		 this.ventanaPresupuesto = ventanaPresupuesto;
		
	}
	
	public void inicializar(){
		
		this.ventanaPresupuesto.setVisible(true);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
