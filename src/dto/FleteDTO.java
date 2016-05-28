package dto;

import java.util.Date;

public class FleteDTO {
	int id;
	private int nroDoc;
	private String nombre;
	private String modelo;
	private String patente;
	private String telefono;
	private Date vtoLicencia;
	private Date fecha_creacion;
	private int idusuario;

	public FleteDTO(int nroDoc, String nombre, String modelo, String patente, String telefono, Date vtoLicencia) {
		this.nroDoc = nroDoc;
		this.nombre = nombre;
		this.modelo = modelo;
		this.patente = patente;
		this.telefono = telefono;
		this.vtoLicencia = vtoLicencia;
		this.setIdusuario(0);
	}

	public FleteDTO(int id, int nroDoc, String nombre, String modelo, String patente, String telefono, Date vtoLicencia,
			Date fecha_creacion, int idusuario) {
		this.id = id;
		this.nroDoc = nroDoc;
		this.nombre = nombre;
		this.modelo = modelo;
		this.patente = patente;
		this.telefono = telefono;
		this.vtoLicencia = vtoLicencia;
		this.fecha_creacion = fecha_creacion;
		this.setIdusuario(idusuario);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNroDoc() {
		return nroDoc;
	}

	public void setNroDoc(int nroDoc) {
		this.nroDoc = nroDoc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getVtoLicencia() {
		return vtoLicencia;
	}

	public void setVtoLicencia(Date vtoLicencia) {
		this.vtoLicencia = vtoLicencia;
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
