package modelo;

import java.util.ArrayList;
import java.util.List;
import dto.RepuestoDTO;
import dto.IngresoDTO;
import dto.ItemPresupuestoRepuestoDTO;
import dto.PresupuestoDTO;
import persistencia.dao.RepuestoDAO;
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
	
	public Presupuesto(IngresoDTO ingr) {
		//Recuperacion y guardado de datos DAO's
		presupuestoDAO = new PresupuestoDAO();
		presupuestoRepuestoDAO = new PresupuestoRepuestoDAO();
		repuestoDAO = new RepuestoDAO();
		
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
	
	public void guardarModelo() {
		// TODO Aca agarraria el modelo cargado con todos los datos y lo guardaria con su correspondiente dao
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
