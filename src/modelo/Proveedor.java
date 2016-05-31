package modelo;

import java.util.ArrayList;
import java.util.List;

import dto.MarcaDTO;
import dto.ProveedorDTO;
import persistencia.dao.MarcaDAO;
import persistencia.dao.ProveedorDAO;

public class Proveedor {
	
	private ProveedorDAO proveedorDAO;
	private MarcaDAO marcaDAO;
	
	public Proveedor(){
		
		this.proveedorDAO = new ProveedorDAO();
		this.marcaDAO = new MarcaDAO();
		
	}
	
	

	public ArrayList<ProveedorDTO> obtenerProveedores() {
		
		return proveedorDAO.readAll();
	}
	
	public ArrayList<MarcaDTO> obtenerMarcasDelProveedor(int id_proveedor){
		
		return marcaDAO.buscarMarcasPorIdProvedor(id_proveedor);
		
	}

}
