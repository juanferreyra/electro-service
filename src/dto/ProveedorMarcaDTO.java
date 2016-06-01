package dto;

public class ProveedorMarcaDTO {
	
	private int id;
	private int idProveedor;
	private int idMarca;
	
	public ProveedorMarcaDTO(int id, int idProveedor, int idMarca){
		
		this.id = id;
		this.idProveedor = idProveedor;
		this.idMarca = idMarca;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public int getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}
	
	

}
