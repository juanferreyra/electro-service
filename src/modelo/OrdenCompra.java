package modelo;

import java.util.ArrayList;
import java.util.List;
import dto.ImpresionOrdenCompraDTO;
import dto.ItemRepuestoDTO;
import dto.MarcaDTO;
import dto.OrdenCompraDTO;
import dto.OrdenCompraRepuestoDTO;
import dto.ProveedorDTO;
import dto.RepuestoDTO;
import persistencia.dao.ImpresionOrdenCompraDAO;
import persistencia.dao.MarcaDAO;
import persistencia.dao.OrdenCompraDAO;
import persistencia.dao.OrdenCompraRepuestoDAO;
import persistencia.dao.ProveedorDAO;
import persistencia.dao.RepuestoDAO;

public class OrdenCompra {

	private OrdenCompraDTO ordenCompraDTO;
	private ArrayList<ItemRepuestoDTO> listaRepuestos;
	private ProveedorDTO proveedorDTO;
	private ArrayList<MarcaDTO> listaMarcasProveedor;
	
	private static ImpresionOrdenCompraDAO impresionOrdenCompraStatic;
	
	private ProveedorDAO proveedorDAO;
	private OrdenCompraDAO ordenCompraDAO;
	private OrdenCompraRepuestoDAO ordenCompraRepuestoDAO;
	private RepuestoDAO repuestoDAO;
	private MarcaDAO marcaDAO;
	
	public OrdenCompra() {
		proveedorDAO = new ProveedorDAO();
		ordenCompraDAO = new OrdenCompraDAO();
		ordenCompraRepuestoDAO = new OrdenCompraRepuestoDAO();
		repuestoDAO = new RepuestoDAO();
		marcaDAO = new MarcaDAO();
		
		ordenCompraDTO = new OrdenCompraDTO();
		ordenCompraDTO.setId(-1);
		proveedorDTO = null;
		listaRepuestos = new ArrayList<ItemRepuestoDTO>();
		listaMarcasProveedor = new ArrayList<MarcaDTO>();
	}

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

	public ProveedorDTO getProveedorDTO() {
		return proveedorDTO;
	}

	public void setProveedorDTO(ProveedorDTO proveedor) {
		proveedorDTO = proveedor;
	}
	
	public void actualizarListaMarcas() {
		if(proveedorDTO!=null) {
			listaMarcasProveedor = this.marcaDAO.buscarMarcasPorIdProvedor(this.proveedorDTO.getId()); 
		}
	}

	public OrdenCompraDAO getOrdenCompraDAO() {
		return ordenCompraDAO;
	}

	public ProveedorDAO getProveedorDAO() {
		return proveedorDAO;
	}

	public OrdenCompraRepuestoDAO getOrdenCompraRepuestoDAO() {
		return ordenCompraRepuestoDAO;
	}

	public ArrayList<MarcaDTO> getListaMarcasProveedor() {
		return listaMarcasProveedor;
	}

	public MarcaDAO getMarcaDAO() {
		return marcaDAO;
	}

	public List<RepuestoDTO> obtenerRepuestos() {
		return repuestoDAO.readAllInMarca(listaMarcasProveedor);
	}

	public Boolean crearOrdenCompra() {
		try {
			int idOrden = ordenCompraDAO.getNextId();
			ordenCompraDTO.setId(idOrden);
			for (int i = 0; i < listaRepuestos.size(); i++) {
				OrdenCompraRepuestoDTO orden = new OrdenCompraRepuestoDTO(0, idOrden,  listaRepuestos.get(i).getIdrepuesto(), listaRepuestos.get(i).getCantidad(),listaRepuestos.get(i).getCantidad(), null, true);
				ordenCompraRepuestoDAO.insert(orden);
			}
			
			ordenCompraDTO.setIdproveedor(proveedorDTO.getId());
			ordenCompraDAO.insert(this.ordenCompraDTO);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public boolean existeOrdenCompra(int id) {
		if(ordenCompraDAO.find(id) == null) {
			return false;	
		}
		return true;
	}
	
	
	public void cargarVariables() {
		if(ordenCompraDTO.getId()!=-1) {
			int id = ordenCompraDTO.getId();
			ordenCompraDTO = ordenCompraDAO.find(id);
			proveedorDTO = proveedorDAO.find(ordenCompraDTO.getIdproveedor());
			listaRepuestos = ordenCompraRepuestoDAO.readAll(id);
		}
	}
	
	public void actualizarEstado(String estado) {
		ordenCompraDAO.updateEstado(estado,ordenCompraDTO.getId());
	}
	
	public ArrayList<ImpresionOrdenCompraDTO> getDatosImpresion(){
		impresionOrdenCompraStatic = new ImpresionOrdenCompraDAO();
		return impresionOrdenCompraStatic.find(ordenCompraDTO.getId());
	}
	
}