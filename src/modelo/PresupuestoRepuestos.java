package modelo;

import java.util.List;

import dto.PresupuestoRepuestoDTO;
import persistencia.dao.PresupuestoRepuestoDAO;

public class PresupuestoRepuestos {
	
private PresupuestoRepuestoDAO repuesto;

	
	public PresupuestoRepuestos() {
		
		this.repuesto = new PresupuestoRepuestoDAO();
	
	}
	
	
	public void agregarRepuesto(PresupuestoRepuestoDTO nuevoRepuesto)
	{
		repuesto.insert(nuevoRepuesto);
	}

	public void borrarRepuesto(PresupuestoRepuestoDTO repuesto_a_eliminar) 
	{
		repuesto.delete(repuesto_a_eliminar);
	}
	
	public List<PresupuestoRepuestoDTO> obtenerRepuestos()
	{
           return repuesto.readAll();	
	}
		 
		


}
