package dto;

import java.util.Date;

public class HojaDeRutaIngresosDTO {
	private int id;
	private int idHojaDeRuta;
	private int idIngreso;
	private Date fechaCreacion;
	private boolean entregado;
	
	public HojaDeRutaIngresosDTO(int id, int idHojaDeRuta, int idIngreso, boolean entregado, Date fechaCreacion) {
		super();
		this.id = id;
		this.idHojaDeRuta = idHojaDeRuta;
		this.idIngreso = idIngreso;
		this.setEntregado(entregado);
		this.fechaCreacion = fechaCreacion;
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
	
	public boolean isEntregado() {
		return entregado;
	}

	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}
}
