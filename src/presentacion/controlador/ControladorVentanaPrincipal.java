package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentacion.vista.VentanaListaIngresos;
import presentacion.vista.VentanaPrincipal;

public class ControladorVentanaPrincipal  implements ActionListener{
	
	private VentanaPrincipal principal;
	
	public ControladorVentanaPrincipal(VentanaPrincipal principal) {
		
		this.principal = principal;
		// this.principal.get aca los botones text etc. addActionlistener(this);
		// asi con cada elemento a setear de la pantalla
	}
	
	private  String perfil() {
		return principal.getUser().getPerfil().getPerfil();
		
	}
	

	public void actionPerformed(ActionEvent arg0) {
		
		if (this.perfil().equals("ADMINISTRATIVO")){
			// aca lo que se quiere mostrar de administrativo
			
		}else if(this.perfil().equals("TECNICO")){
			
			// aca lo que se quiere mostrar de tecnico
		}else{
			
			// aca lo que se quiere mostrar de jefe
		}
		
		
		
		
	}
}