package dto;

public class EstadoDTO {
	private int id;
	private String detalle;
	private String destalle_largo;

	public EstadoDTO(int id, String detalle, String destalle_largo) {
		this.id = id;
		this.detalle = detalle;
		this.destalle_largo = destalle_largo;
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

	public String getDestalle_largo() {
		return destalle_largo;
	}

	public void setDestalle_largo(String destalle_largo) {
		this.destalle_largo = destalle_largo;
	}

}
