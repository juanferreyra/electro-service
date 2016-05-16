package modelo;

import java.util.ArrayList;
import java.util.List;
import dto.RepuestoDTO;
import dto.IngresoDTO;
import dto.IngresoLogDTO;
import dto.ItemPresupuestoRepuestoDTO;
import dto.PresupuestoDTO;
import dto.PresupuestoRepuestoDTO;
import persistencia.dao.RepuestoDAO;
import persistencia.dao.IngresoLogDAO;
import persistencia.dao.PresupuestoDAO;
import persistencia.dao.PresupuestoRepuestoDAO;

public class Presupuesto 
{
	private int id;
	private IngresoDTO ingreso;
	private PresupuestoDTO presupuesto;
	private ArrayList<ItemPresupuestoRepuestoDTO> listaDeRepuestos;
	
	private PresupuestoDAO presupuestoDAO;
	private PresupuestoRepuestoDAO presupuestoRepuestoDAO;
	private RepuestoDAO repuestoDAO;
	private IngresoLogDAO ingresoLogDAO;
	
	public Presupuesto(IngresoDTO ingr) {
		//Recuperacion y guardado de datos DAO's
		presupuestoDAO = new PresupuestoDAO();
		presupuestoRepuestoDAO = new PresupuestoRepuestoDAO();
		repuestoDAO = new RepuestoDAO();
		ingresoLogDAO = new IngresoLogDAO();
		
		ingreso = ingr;
		try {
			presupuesto = presupuestoDAO.find(ingreso.getId());
			id = presupuesto.getId();
			listaDeRepuestos = obtenerItemsRepuestos(id);
		} catch (Exception e) {
			id = -1;
			listaDeRepuestos = new ArrayList<ItemPresupuestoRepuestoDTO>();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Boolean guardarPresupuesto(int idusuario) {
		Boolean ingreso = true;
		//guardo todos los objetos DTO con su respectivo DAO
		
		//busco el siguiente id de la tabla ingreso a insertar
		
			int presupuesto_id = this.presupuestoDAO.getNextId();
			presupuesto.setId(presupuesto_id);
			if(presupuesto_id!=-1)
			{
				IngresoLogDTO ingrLog = new IngresoLogDTO(0,this.ingreso.getId(), 3, null, idusuario);
				//ingreso el estado
				this.ingresoLogDAO.insert(ingrLog);
				//ingreso el presupuesto
				this.presupuestoDAO.insert(presupuesto);
				//ingreso los repuestos del presupuesto
				for (int i = 0; i < listaDeRepuestos.size(); i++) {
					
					PresupuestoRepuestoDTO presuRep = 
							new PresupuestoRepuestoDTO(0, presupuesto.getId(),
									listaDeRepuestos.get(i).getIdrepuesto(),
									listaDeRepuestos.get(i).getCantidad(),
									null,
									true);
					this.presupuestoRepuestoDAO.insert(presuRep);
				}
			}
		
		return ingreso;
	}

	public PresupuestoDTO getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(PresupuestoDTO presupuesto) {
		this.presupuesto = presupuesto;
	}
	
	public IngresoDTO getIngreso() {
		return ingreso;
	}

	public void setIngreso(IngresoDTO ingreso) {
		this.ingreso = ingreso;
	}
	
	public int buscarIdPresupuesto(int idIngeso) {	
		return presupuestoDAO.find(idIngeso).getId();	
	}

	//Funciones Repuestos
	public ArrayList<ItemPresupuestoRepuestoDTO> obtenerItemsRepuestos(int id) {
           return presupuestoRepuestoDAO.readAll(id);
	}
	
	public RepuestoDTO buscarRepuesto(String aBuscar) {
		return repuestoDAO.find(aBuscar);
	}
	
	public List<RepuestoDTO> obtenerRepuestos() {
		return repuestoDAO.readAll();		
	}
	
	public List<ItemPresupuestoRepuestoDTO> getListaDeRepuestos() {
		return listaDeRepuestos;
	}
	
	public void agregarRepuesto(RepuestoDTO nuevoComponente) {
		repuestoDAO.insert(nuevoComponente);
	}
	
	public void addRepuestoListaDeComponentes(ItemPresupuestoRepuestoDTO resp) {
		listaDeRepuestos.add(resp);
	}
}
