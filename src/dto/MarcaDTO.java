package dto;

public class MarcaDTO {
	private int id;
	private String detalle;
	
	public MarcaDTO(int id, String detalle) {
		this.id = id;
		this.detalle = detalle;
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
}