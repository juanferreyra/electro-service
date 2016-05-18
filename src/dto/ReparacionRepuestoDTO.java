package dto;

import java.util.Date;

public class ReparacionRepuestoDTO {
	private int id;
	private int idreparacion;
	private int idrepuesto;
	private int cantidad;
	private Date fecha_creacion;
	private boolean habilitado;

	public ReparacionRepuestoDTO() {

	}

	public ReparacionRepuestoDTO(int id, int idreparacion, int idrepuesto, int cantidad, Date fecha_creacion,
			boolean habilitado) {
		this.idreparacion = idreparacion;
		this.idrepuesto = idrepuesto;
		this.cantidad = cantidad;
		this.fecha_creacion = fecha_creacion;
		this.habilitado = habilitado;
	}

	public int getIdreparacion() {
		return idreparacion;
	}

	public void setIdreparacion(int idreparacion) {
		this.idreparacion = idreparacion;
	}

	public int getIdrepuesto() {
		return idrepuesto;
	}

	public void setIdrepuesto(int idrepuesto) {
		this.idrepuesto = idrepuesto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
