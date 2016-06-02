package modelo;

import dto.ProveedorMarcaDTO;
import persistencia.dao.ProveedorMarcaDAO;

public class ProveedorMarca {
	
	private ProveedorMarcaDAO proveedorMarca;
	
	public ProveedorMarca(){
		
		this.proveedorMarca = new ProveedorMarcaDAO();
		
	}
	
	public void borrarTodos(int idProveedor){
		
		proveedorMarca.borrarTodos(idProveedor);
		
	}
	
	public void borrar(int idproveedor){
		proveedorMarca.delete(idproveedor);
	}
	
	public void borrarMarca(int idProveedor, int idMarca){
		proveedorMarca.deleteItem(idProveedor, idMarca);
	}
	
	public void agregar(ProveedorMarcaDTO ProveedorMarca){
		
		proveedorMarca.insert(ProveedorMarca);
		
	}
	

}
