package dto;

public class ClienteDTO {
	private int tipoDoc;
	private int nroDoc;
	private String nombre;
	private String apellido;
	private String localidad;
	private String direccion;
	private String telefono;
	private String mail;
	private int idusuario;
	
	public ClienteDTO(int tipoDoc, int nroDoc, String nombre, String apellido, String localidad, String direccion, String telefono, String mail, int idusuario)
	{
		this.tipoDoc = tipoDoc;
		this.nroDoc = nroDoc;
		this.nombre = nombre;
		this.apellido = apellido;
		this.localidad = localidad;
		this.direccion = direccion;
		this.telefono = telefono;
		this.mail = mail;
		this.idusuario = idusuario;
	}

	public int getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(int tipoDoc) {
		this.tipoDoc = tipoDoc;
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
