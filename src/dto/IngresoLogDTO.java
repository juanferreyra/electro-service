package dto;

import java.util.Date;

public class IngresoLogDTO {
	private int id;
	private int idingreso;
	private int idestado;
	private Date fecha_creacion;
	private int idusuario;
	
	public IngresoLogDTO(int id, int idingreso, int idestado, Date fecha_creacion, int idusuario) {
		this.id = id;
		this.idingreso = idingreso;
		this.idestado = idestado;
		this.fecha_creacion = fecha_creacion;
		this.idusuario = idusuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdingreso() {
		return idingreso;
	}

	public void setIdingreso(int idingreso) {
		this.idingreso = idingreso;
	}

	public int getIdestado() {
		return idestado;
	}

	public void setIdestado(int idestado) {
		this.idestado = idestado;
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
