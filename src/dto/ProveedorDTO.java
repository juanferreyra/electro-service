package dto;

import java.util.Date;

public class ProveedorDTO {
	
	private int id;
	private String razonSocial;
	private int cuit;
	private String direccion;
	private String email;
	private String nombreContacto;
	private String telefonoContacto;
	private String emailContacto;
	private String emailPedidos;
	private Date fechaCreacion;
	private int idusuario;
	
	public ProveedorDTO( int id, String razonSocial, int cuit, String direccion, String email, String nombreContacto,
			String telefonoContacto , String emailContacto, String emailPedidos,int idusuario ){
		
		this.id = id;
		this.razonSocial = razonSocial;
		this.cuit = cuit;
		this.direccion = direccion;
		this.email = email;
		this.nombreContacto = nombreContacto;
		this.telefonoContacto = telefonoContacto;
		this.emailContacto = emailContacto;
		this.emailPedidos = emailPedidos;
		this.idusuario = idusuario;
		
	}
	
	public ProveedorDTO (ProveedorDTO proveedor){
		
		this.id = proveedor.getId();
		this.razonSocial = proveedor.getRazonSocial();
		this.cuit = proveedor.getCuit();
		this.direccion = proveedor.getDireccion();
		this.email = proveedor.getEmail();
		this.nombreContacto = proveedor.getNombreContacto();
		this.telefonoContacto = proveedor.getTelefonoContacto();
		this.emailContacto = proveedor.getEmailContacto();
		this.emailPedidos = proveedor.getEmailPedidos();
		this.idusuario = proveedor.getIdusuario();
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public int getCuit() {
		return cuit;
	}

	public void setCuit(int cuit) {
		this.cuit = cuit;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getTelefonoContacto() {
		return telefonoContacto;
	}

	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	public String getEmailContacto() {
		return emailContacto;
	}

	public void setEmailContacto(String emailContacto) {
		this.emailContacto = emailContacto;
	}

	public String getEmailPedidos() {
		return emailPedidos;
	}

	public void setEmailPedidos(String emailPedidos) {
		this.emailPedidos = emailPedidos;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}
	
	
	

}
