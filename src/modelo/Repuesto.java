package modelo;

import java.util.List;

import dto.RepuestoDTO;
import persistencia.dao.RepuestoDAO;

public class Repuesto {
	
	private RepuestoDAO repuestoDAO;
	
	public Repuesto (){
		
		this.repuestoDAO = new RepuestoDAO();
		
	}
	
	public void agregarRepuesto(RepuestoDTO nuevoRepuesto) {
		repuestoDAO.insert(nuevoRepuesto);
	}

	public void borrarRepuesto(int id_repuesto_a_eliminar) {
		repuestoDAO.delete(id_repuesto_a_eliminar);
	}
	
	public List<RepuestoDTO> obtenerRepuestos() {
		return repuestoDAO.readAll();		
	}
	
	public void  modificarRepuesto(RepuestoDTO repuesto_a_modificar) {
		repuestoDAO.update(repuesto_a_modificar);

	}
	public RepuestoDTO buscarRepuesto(String detalle){
		return repuestoDAO.find(detalle);

	}

	public String buscarDetalleXid(int idRepuesto){
		return repuestoDAO.BuscarDetalleXid(idRepuesto);
	}
	public int buscarIdRepuesto(String detalle){
		return repuestoDAO.findXDetalle(detalle);
	}




}
