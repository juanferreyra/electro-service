package dto;

import java.util.Date;

public class OrdenCompraRepuestoDTO {
	
	private int id;
	private int idOrdenCompra;
	private int idComponente;
	private int cantidad;
	private float precio_unitario;
	private Date fechaCreacion;
	private boolean habilitado;

	public OrdenCompraRepuestoDTO(int id, int idOrdenCompra, int idComponente, int cantidad, float precio_unitario,Date fechaCreacion,
			boolean habilitado) {

		this.id = id;
		this.idOrdenCompra = idOrdenCompra;
		this.idComponente = idComponente;
		this.cantidad = cantidad;
		this.precio_unitario = precio_unitario;
		this.fechaCreacion = fechaCreacion;
		this.habilitado = habilitado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdOrdenCompra() {
		return idOrdenCompra;
	}

	public void setIdOrdenCompra(int idPresupuesto) {
		this.idOrdenCompra = idPresupuesto;
	}

	public int getIdComponente() {
		return idComponente;
	}

	public void setIdComponente(int idComponente) {
		this.idComponente = idComponente;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public float getPrecio_unitario() {
		return precio_unitario;
	}

	public void setPrecio_unitario(float precio_unitario) {
		this.precio_unitario = precio_unitario;
	}


}
