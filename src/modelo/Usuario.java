package modelo;

import java.util.List;

import persistencia.dao.UsuarioDAO;

public class Usuario {

	private UsuarioDAO usuario;
	
	public Usuario(){
		
		this.usuario = new UsuarioDAO();
	}

	public List<String> buscarUsuario(int id) {
		return usuario.find(id);
	}
	
	
}
