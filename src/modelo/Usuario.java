package modelo;

import persistencia.dao.UsuarioDAO;

public class Usuario {

	private UsuarioDAO usuario;

	public Usuario() {

		this.usuario = new UsuarioDAO();
	}

}
