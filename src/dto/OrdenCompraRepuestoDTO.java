package dto;

import java.util.Date;

public class OrdenCompraRepuestoDTO {
	
	private int id;
	private int idOrdenCompra;
	private int idComponente;
	private int cantidad;
	private int cantidad_real;
	private Date fechaCreacion;
	private boolean habilitado;

	public OrdenCompraRepuestoDTO(int id, int idOrdenCompra, int idComponente, int cantidad, int cantidad_real,Date fechaCreacion,
			boolean habilitado) {

		this.id = id;
		this.idOrdenCompra = idOrdenCompra;
		this.idComponente = idComponente;
		this.cantidad = cantidad;
		this.cantidad_real = cantidad_real;
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

	public int getCantidad_real() {
		return cantidad_real;
	}

	public void setCantidad_real(int cantidad_real) {
		this.cantidad_real = cantidad_real;
	}


}
