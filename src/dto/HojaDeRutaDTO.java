package dto;

import java.sql.Date;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class HojaDeRutaDTO {
	private Date creacion;
	private int Id;
	private int fleteId;
	private List destinos;
	public HojaDeRutaDTO(Date creacion, int id, int fleteId) {
		super();
		this.creacion = creacion;
		Id = id;
		this.fleteId = fleteId;
		this.destinos = destinos;
	}
	
	public Date getCreacion() {
		return creacion;
	}
	public void setCreacion(Date creacion) {
		this.creacion = creacion;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getFleteId() {
		return fleteId;
	}
	public void setFleteId(int fleteId) {
		this.fleteId = fleteId;
	}
	public List getDestinos() {
		return destinos;
	}
	public void setDestinos(List destinos) {
		this.destinos = destinos;
	}
	
	
	
	
	
}
