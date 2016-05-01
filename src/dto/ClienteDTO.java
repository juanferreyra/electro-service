package dto;

import java.util.Date;

public class ClienteDTO {
	private int id;
	private int nroDoc;
	private String nombre;
	private String apellido;
	private String localidad;
	private String direccion;
	private String telefono;
	private String mail;
	private Date fecha_creacion;
	private int idusuario;
	
	public ClienteDTO(int id, int nroDoc, String nombre, String apellido, String localidad, String direccion, String telefono, String mail, Date fecha_creacion, int idusuario)
	{
		this.id = id;
		this.nroDoc = nroDoc;
		this.nombre = nombre;
		this.apellido = apellido;
		this.localidad = localidad;
		this.direccion = direccion;
		this.telefono = telefono;
		this.mail = mail;
		this.fecha_creacion = fecha_creacion;
		this.idusuario = idusuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}
	
	
	
}
