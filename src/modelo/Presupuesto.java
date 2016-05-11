package modelo;

import java.util.List;
import dto.ComponenteDTO;
import dto.IngresoDTO;
import dto.PresupuestoDTO;
import persistencia.dao.ComponenteDAO;
import persistencia.dao.PresupuestoDAO;
import persistencia.dao.PresupuestoRepuestoDAO;

public class Presupuesto 
{
	private int id;
	private IngresoDTO ingreso;
	private PresupuestoDAO presupuestoDAO;
	private PresupuestoRepuestoDAO repuestoDAO;
	private ComponenteDAO componenteDAO;
	
	private PresupuestoDTO presupuesto;
	private List<ComponenteDTO> listaDeComponetes;
	
	public Presupuesto(IngresoDTO ingr) {
		ingreso = ingr;
		presupuestoDAO = new PresupuestoDAO();
		presupuesto = presupuestoDAO.find(ingreso.getId());
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
	
	public PresupuestoDTO getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(PresupuestoDTO presupuesto) {
		this.presupuesto = presupuesto;
	}

	public List<ComponenteDTO> getListaDeComponetes() {
		return listaDeComponetes;
	}

	public void setListaDeComponetes(List<ComponenteDTO> listaDeComponetes) {
		this.listaDeComponetes = listaDeComponetes;
	}

	public IngresoDTO getIngreso() {
		return ingreso;
	}

	public void setIngreso(IngresoDTO ingreso) {
		this.ingreso = ingreso;
	}

}
