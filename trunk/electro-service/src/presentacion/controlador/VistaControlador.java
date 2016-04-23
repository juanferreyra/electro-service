/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import presentacion.vista.Vista;
import modelo.Presupuesto;

public class VistaControlador implements ActionListener
{
		private Vista vista;
		private Presupuesto presu;
		
		public VistaControlador(Vista vista, Presupuesto presu)
		{
                    
		}
		
		public void inicializar()
		{
                    
		}

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}