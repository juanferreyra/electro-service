package modelo;

import java.util.List;

import dto.ComponenteDTO;
import persistencia.dao.ComponenteDAO;
import persistencia.dao.PresupuestoDAO;
import persistencia.dao.PresupuestoRepuestoDAO;

public class Presupuesto 
{
	private int id;
	private PresupuestoDAO presupuesto;
	private PresupuestoRepuestoDAO repuesto;
	private ComponenteDAO componente;
	
	
	public Presupuesto()
	{
		id = -1;
		presupuesto = new PresupuestoDAO();
		repuesto = new PresupuestoRepuestoDAO();
		componente = new ComponenteDAO();
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
