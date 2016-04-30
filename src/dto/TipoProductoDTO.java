package dto;

public class TipoProductoDTO {
	
	private int id;
	private String detalle;
	private int idusuario;
	
	public TipoProductoDTO(int id, String detalle, int idusuario){
		this.id = id;
		this.detalle = detalle;
		this.idusuario = idusuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	public void setIdUsuario(int idusuario){
		this.idusuario = idusuario;
	}
	
	public int getIdUsuario(){
		return this.idusuario;
	}
	
}
