package dto;

import java.io.Serializable;

public class ConfigDataBaseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String url;
	private String puerto;
	private String usuario;
	private String contrasena;
	
	public ConfigDataBaseDTO(String url, String puerto, String usuario, String contrasena){
		
		this.url = url;
		this.puerto = puerto;
		this.usuario = usuario;
		this.contrasena = contrasena;
		
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPuerto() {
		return puerto;
	}

	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

}

