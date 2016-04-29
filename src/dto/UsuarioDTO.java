package dto;

public class UsuarioDTO {
	
	private int id;
	private String nombre;
	private String apellido;
	private String password;
	private PerfilDTO perfil;
	
	
	public UsuarioDTO(PerfilDTO perfil){
		
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
