package modelo;

import java.util.List;

import dto.ClienteDTO;
import persistencia.dao.ClienteDAO;


public class Cliente {
	
	private ClienteDAO clienteDAO;
	
	public Cliente(){
		
		clienteDAO = new ClienteDAO();
		
	}
	
	public void agregarCliente(ClienteDTO nuevoCliente) {
		clienteDAO.insert(nuevoCliente);
	}

	public void borrarCliente(ClienteDTO cliente_a_eliminar) {
		clienteDAO.delete(cliente_a_eliminar);
	}
	
	public List<ClienteDTO> obtenerClientes() {
		return clienteDAO.readAll();		
	}
	
	public void  modificarCliente(ClienteDTO cliente_a_modificar) {
		clienteDAO.update(cliente_a_modificar);
		
	}

}
