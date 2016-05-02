package dto;

import java.util.Date;

public class IngresoEstadoDTO {
	private int id;
	private String detalle;
	private Date fecha_creacion;
	private int idusuario;
	
	public IngresoEstadoDTO(int id, String detalle, Date fecha_creacion, int idusuario){
		this.id = id;
		this.detalle = detalle;
		this.setFecha_creacion(fecha_creacion);
		this.setIdusuario(idusuario);
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

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}
}
