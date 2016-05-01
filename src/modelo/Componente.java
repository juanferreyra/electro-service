package modelo;

import java.util.List;

import dto.ComponenteDTO;
import persistencia.dao.ComponenteDAO;

public class Componente {
	private ComponenteDAO componente;
	
	public Componente(){
		
		this.componente = new ComponenteDAO();
		
	}
	
	public void agregarComponente(ComponenteDTO nuevoComponente) {
		componente.insert(nuevoComponente);
	}

	public void borrarcomponente(ComponenteDTO componente_a_eliminar) {
		componente.delete(componente_a_eliminar);
	}
	
	public List<ComponenteDTO> obtenercomponentes() {
		return componente.readAll();		
	}
	
	public void  modificarcomponente(ComponenteDTO componente_a_modificar) {
		componente.update(componente_a_modificar);
		
	}
	public List<ComponenteDTO> buscarComponentes(String aBuscar) {
		return componente.search(aBuscar);
	}
}
