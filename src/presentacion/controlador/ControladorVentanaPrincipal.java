package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentacion.vista.VentanaListaIngresos;
import presentacion.vista.VentanaPrincipal;

public class ControladorVentanaPrincipal  implements ActionListener{
	
	private VentanaPrincipal principal;
	private String perfil;
	
	public ControladorVentanaPrincipal(VentanaPrincipal principal) {
		
		this.principal = principal;
		// this.principal.get aca los botones text etc. addActionlistener(this);
		// asi con cada elemento a setear de la pantalla
	}
	
	public void iniciar(){
		
		this.perfil = principal.getUser().getPerfilDTO().getPerfil();
		this.adecuarVentanaPrincipal();
		
	}
	
	private void adecuarVentanaPrincipal(){
		
		if (this.perfil.equals("ADMINISTRATIVO")){
			System.out.println(this.perfil);
			// aca lo que se quiere mostrar de administrativo
			
		}else if(this.perfil.equals("TECNICO")){
			System.out.println(this.perfil);
			
			// aca lo que se quiere mostrar de tecnico
		}else{
			System.out.println(this.perfil);
			
			// aca lo que se quiere mostrar de jefe
		}
	}
	
	

	public void actionPerformed(ActionEvent arg0) {
		
		
	}
}