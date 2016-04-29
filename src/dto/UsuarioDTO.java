package dto;

public class UsuarioDTO {
	
	private int id;
	private String nombre;
	private String apellido;
	private String password;
	private PerfilDTO perfil;
	
	
	public UsuarioDTO(int id, String nombre, String apellido, String password, PerfilDTO perfil){
		
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.perfil = perfil;
		
	}


	public int getId() {
		return id;
	}


	public String getNombre() {
		return nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public String getPassword() {
		return password;
	}


	public PerfilDTO getPerfil() {
		return perfil;
	}
	
	

}
