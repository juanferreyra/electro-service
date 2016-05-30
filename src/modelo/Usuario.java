package modelo;

import java.util.List;

import dto.UsuarioDTO;
import persistencia.dao.UsuarioDAO;

public class Usuario {


	private UsuarioDAO usuarioDAO;

	public Usuario() {

		this.usuarioDAO = new UsuarioDAO();
	}

	
	public void agregarUsuaario(UsuarioDTO nuevoCliente) {
		usuarioDAO.insert(nuevoCliente);
	}

	public void borrarCliente(int id_usuario_a_eliminar) {
		usuarioDAO.delete(id_usuario_a_eliminar);
	}
	
	public List<UsuarioDTO> obtenerUsuarios() {
		return usuarioDAO.readAll();		
	}
	
	public void  modificarUsuario(UsuarioDTO cliente_a_modificar) {
		usuarioDAO.update(cliente_a_modificar);
		
	}
}
