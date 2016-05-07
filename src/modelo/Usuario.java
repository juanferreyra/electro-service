package modelo;

import persistencia.dao.UsuarioDAO;

public class Usuario {

	@SuppressWarnings("unused")
	private UsuarioDAO usuario;

	public Usuario() {

		this.usuario = new UsuarioDAO();
	}

}
