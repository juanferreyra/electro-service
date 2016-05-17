package dto;

import java.util.Date;

public class HojaDeRutaDTO {
	private int id;
	private int fleteId;
	private Date fecha_creacion;
	private int idusuario;
	
	public HojaDeRutaDTO(int id, int fleteId, Date creacion, int idusuario) {
		this.id = id;
		this.fleteId = fleteId;
		this.fecha_creacion = creacion;
		this.setIdusuario(idusuario);
	}
	
	public Date getCreacion() {
		return fecha_creacion;
	}
	
	public void setCreacion(Date creacion) {
		this.fecha_creacion = creacion;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getFleteId() {
		return fleteId;
	}
	
	public void setFleteId(int fleteId) {
		this.fleteId = fleteId;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}
}
