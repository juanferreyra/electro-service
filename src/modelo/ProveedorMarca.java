package modelo;

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
	

}
