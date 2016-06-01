package dto;

public class ItemRepuestoDTO {
	private int id;
	private int idrepuesto;
	private String detalle;
	private int cantidad;
	private Float precioUnitario;
	private Float precio;
	
	public ItemRepuestoDTO(int id ,int idrepuesto,String detalle ,int cantidad ,Float precioUnitario ,Float precio ) {
		this.id = id;
		this.idrepuesto = idrepuesto;
		this.detalle = detalle;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		this.precio = precio;
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

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float valor) {
		this.precio = valor;
	}

	public Float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public int getIdrepuesto() {
		return idrepuesto;
	}

	public void setIdrepuesto(int idrepuesto) {
		this.idrepuesto = idrepuesto;
	}
	
	public void sumarCantidad(int cantidadASumar) {
		this.cantidad += cantidadASumar;
	}
	
	public void sumarTotal(float precioASumar) {
		this.precio += precioASumar;
	}

}
