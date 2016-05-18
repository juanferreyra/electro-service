package dto;

import java.util.Date;

public class HojaDeRutaIngresosDTO {
	private int id;
	private int idHojaDeRuta;
	private int idIngreso;
	private Date fechaCreacion;
	private boolean en_entrega;
	
	public HojaDeRutaIngresosDTO(int id, int idHojaDeRuta, int idIngreso, boolean en_entrega, Date fechaCreacion) {
		super();
		this.id = id;
		this.idHojaDeRuta = idHojaDeRuta;
		this.idIngreso = idIngreso;
		this.en_entrega = en_entrega;
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
	
	public boolean isEnEntrega() {
		return en_entrega;
	}

	public void setEnEntrega(boolean en_entrega) {
		this.en_entrega = en_entrega;
	}
}
