package modelo;
import java.util.List;

import persistencia.dao.PresupuestoDAO;
import dto.PresupuestoDTO;

public class Presupuesto 
{
	private PresupuestoDAO presupuetoDAO;	
	
	public Presupuesto()
	{
		this.presupuetoDAO = new PresupuestoDAO();
	}
	
	public void agregarPresupuesto(PresupuestoDTO nuevoPresupuesto) {
		presupuetoDAO.insert(nuevoPresupuesto);
	}

	public void borrarPresupuesto(PresupuestoDTO presupuesto_a_eliminar) {
		presupuetoDAO.delete(presupuesto_a_eliminar);
	}
	
	public List<PresupuestoDTO> obtenerPresupuestos() {
		return presupuetoDAO.readAll();		
	}
	
	/*public void  modificarPresupuesto(PresupuestoDTO presupuesto_a_modificar) {
		presupuetoDAO.update(presupuesto_a_modificar);
		
	}*/
	
	public PresupuestoDTO buscarComponentes(int aBuscar) {
		return presupuetoDAO.find(aBuscar);
	}
	
}
