package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Ingreso;
import presentacion.vista.VentanaPresupuesto;

public class ControladorPresupuesto implements ActionListener{
	
	private VentanaPresupuesto ventanaPresupuesto;
	private Ingreso ingreso;
	
	
	public ControladorPresupuesto( VentanaPresupuesto ventanaPresupuesto, Ingreso ingreso) {
		
		 this.ventanaPresupuesto = ventanaPresupuesto;
		 this.ingreso = ingreso;
		
	}
	
	public void inicializar(){
		
		this.ventanaPresupuesto.setVisible(true);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
