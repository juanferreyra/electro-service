package modelo;

import dto.PerfilDTO;
import persistencia.dao.PerfilDAO;

public class Perfil {
	
	private PerfilDAO perfilDAO;
	
	public Perfil(){
		
		this.perfilDAO = new PerfilDAO();
	}
	
	public PerfilDTO obtenerPerfilPorDetalle(String detalle){
	
		return perfilDAO.obtenerPerfil(detalle);
	}

}
