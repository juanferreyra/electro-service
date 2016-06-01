package modelo;

import java.util.ArrayList;
import java.util.List;

import dto.ItemRepuestoDTO;
import dto.OrdenCompraDTO;
import dto.ProveedorDTO;
import dto.RepuestoDTO;
import persistencia.dao.OrdenCompraDAO;
import persistencia.dao.OrdenCompraRepuestoDAO;
import persistencia.dao.ProveedorDAO;
import persistencia.dao.RepuestoDAO;

public class OrdenCompra {

	private OrdenCompraDTO ordenCompraDTO;
	private ArrayList<ItemRepuestoDTO> listaRepuestos; 
	private ProveedorDTO proveedorDTO;
	
	private ProveedorDAO proveedorDAO;
	private OrdenCompraDAO ordenCompraDAO;
	private OrdenCompraRepuestoDAO ordenCompraRepuestoDAO;
	private RepuestoDAO repuestoDAO;
	

	public OrdenCompraDTO getOrdenCompraDTO() {
		return ordenCompraDTO;
	}


	public ArrayList<ItemRepuestoDTO> getListaDeRepuestos() {
		return listaRepuestos;
	}
	
	public RepuestoDTO buscarRepuesto(String aBuscar) {
		return repuestoDAO.find(aBuscar);
	}


	public void addRepuestoListaDeComponentes(ItemRepuestoDTO itemRepuesto) {
		listaRepuestos.add(itemRepuesto);
	}
	
	
}