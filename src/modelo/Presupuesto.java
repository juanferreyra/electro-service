package modelo;

import java.util.List;
import dto.ComponenteDTO;
import dto.PresupuestoDTO;
import persistencia.dao.ComponenteDAO;
import persistencia.dao.PresupuestoDAO;
import persistencia.dao.PresupuestoRepuestoDAO;

public class Presupuesto 
{
	private int id;
	private PresupuestoDAO presupuestoDAO;
	private PresupuestoRepuestoDAO repuestoDAO;
	private ComponenteDAO componenteDAO;
	private PresupuestoDTO presupuesto;
	private Ingreso ingreso;
	
	public Presupuesto() {
		id = -1;//lo inicio asi para consultar cuando no existe ahun
		presupuestoDAO = new PresupuestoDAO();
		repuestoDAO = new PresupuestoRepuestoDAO();
		componenteDAO = new ComponenteDAO();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void agregarComponente(ComponenteDTO nuevoComponente) {
		componenteDAO.insert(nuevoComponente);
	}
	
	public void borrarcomponente(ComponenteDTO componente_a_eliminar) {
		componenteDAO.delete(componente_a_eliminar);
	}
	
	public List<ComponenteDTO> obtenercomponentes() {
		return componenteDAO.readAll();		
	}
	
	public void  modificarcomponente(ComponenteDTO componente_a_modificar) {
		componenteDAO.update(componente_a_modificar);
		
	}
	public List<ComponenteDTO> buscarComponentes(String aBuscar) {
		return componenteDAO.search(aBuscar);
	}
	
}
