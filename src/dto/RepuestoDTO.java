package dto;

import java.util.Date;

public class RepuestoDTO {
	
	private int id;
	private String detalle;
	private float precioUnitario;
	private int stockMinimo;
	private int idmarca;
	private Date fechaCreacion;
	private int idUsuario;
	private int habilitado;
	
	public RepuestoDTO (int id, String detalle, float precioUnitario,
			int stockMinimo, int idmarca,Date fechaCreacion,int idUsuario, int habilitado ){
		
		this.id = id;
		this.detalle = detalle;
		this.precioUnitario = precioUnitario;
		this.stockMinimo = stockMinimo;
		this.idmarca = idmarca;
		this.fechaCreacion = fechaCreacion;
		this.idUsuario = idUsuario;
		this.habilitado = habilitado;
		
	}

	public int getIdmarca() {
		return idmarca;
	}

	public void setIdmarca(int idmarca) {
		this.idmarca = idmarca;
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

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public int getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(int habilitado) {
		this.habilitado = habilitado;
	}
	
	
	
	
	


}
