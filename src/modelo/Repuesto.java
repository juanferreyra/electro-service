package modelo;

import java.util.List;

import dto.RepuestoDTO;
import persistencia.dao.RepuestoDAO;

public class Repuesto {
	
	private RepuestoDAO repuestoDAO;
	
	public Repuesto (){
		
		this.repuestoDAO = new RepuestoDAO();
		
	}
	
	public void agregarRepuesto(RepuestoDTO nuevoComponente) {
		repuestoDAO.insert(nuevoComponente);
	}

	public void borrarRepuesto(int id_componente_a_eliminar) {
		repuestoDAO.delete(id_componente_a_eliminar);
	}
	
	public List<RepuestoDTO> obtenerRepuestos() {
		return repuestoDAO.readAll();		
	}
	
	public void  modificarRepuesto(RepuestoDTO componente_a_modificar) {
		repuestoDAO.update(componente_a_modificar);
		
	}

	
	

}
