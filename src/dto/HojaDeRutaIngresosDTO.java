package dto;

import java.util.Date;

public class HojaDeRutaIngresosDTO {
	private int id;
	private int idHojaDeRuta;
	private int idIngreso;
	private Date fechaCreacion;
	private boolean habilitado;
	public HojaDeRutaIngresosDTO(int id, int idHojaDeRuta, int idIngreso, Date fechaCreacion, boolean habilitado) {
		super();
		this.id = id;
		this.idHojaDeRuta = idHojaDeRuta;
		this.idIngreso = idIngreso;
		this.fechaCreacion = fechaCreacion;
		this.habilitado = habilitado;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdHojaDeRuta() {
		return idHojaDeRuta;
	}
	public void setIdHojaDeRuta(int idHojaDeRuta) {
		this.idHojaDeRuta = idHojaDeRuta;
	}
	public int getIdIngreso() {
		return idIngreso;
	}
	public void setIdIngreso(int idIngreso) {
		this.idIngreso = idIngreso;
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
	
	
}
