package modelo;

import java.util.List;

import dto.PerfilDTO;
import dto.UsuarioDTO;
import persistencia.dao.UsuarioDAO;

public class Usuario {


	private UsuarioDAO usuarioDAO;

	public Usuario() {

		this.usuarioDAO = new UsuarioDAO();
	}

	
	public void agregarUsuario(UsuarioDTO nuevoUsuario) {
		
		usuarioDAO.insert(nuevoUsuario);
	}

	public void borrarUsuario(int id_usuario_a_eliminar) {
		
		usuarioDAO.delete(id_usuario_a_eliminar);
	}
	
	public List<UsuarioDTO> obtenerUsuarios() {
		
		return usuarioDAO.readAll();		
	}
	
	public void  modificarUsuario(UsuarioDTO cliente_a_modificar) {
		
		usuarioDAO.update(cliente_a_modificar);
		
	}
	public PerfilDTO buscarPerfil(int id_perfil){
		
		return usuarioDAO.buscarPerfil(id_perfil);
	
	}
	public List<PerfilDTO> obtenerPerfiles() {

		return usuarioDAO.todosLosPerfiles();		
	}
}
