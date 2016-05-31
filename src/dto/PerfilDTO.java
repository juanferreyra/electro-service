package dto;


public class PerfilDTO {
	
	private String perfil;
	private int id;
	
	public PerfilDTO(int id,String perfil){
		
		this.id = id;
		this.perfil = perfil;
		
	}
	public PerfilDTO(PerfilDTO perfil){
		
		this.id = perfil.getId();
		this.perfil = perfil.getPerfil();
		
	}

	public String getPerfil() {
		return perfil;
	}

	public int getId() {
		return id;
	}
	
	
	
}



