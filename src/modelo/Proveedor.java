package modelo;

import java.util.ArrayList;
import java.util.List;

import dto.ProveedorDTO;
import persistencia.dao.ProveedorDAO;

public class Proveedor {
	
	private ProveedorDAO proveedorDAO;
	
	public Proveedor(){
		
		this.proveedorDAO = new ProveedorDAO();
		
	}
	
	

	public ArrayList<ProveedorDTO> obtenerProveedores() {
		
		return proveedorDAO.readAll();
	}

}
