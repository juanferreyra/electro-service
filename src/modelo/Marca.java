package modelo;

import java.util.List;

import dto.MarcaDTO;
import dto.RepuestoDTO;
import persistencia.dao.MarcaDAO;

public class Marca {
	
	private MarcaDAO marcaDAO;
	
	public Marca(){
		
		this.marcaDAO =new MarcaDAO();
		
	}
	
	public void agregarRepuesto(MarcaDTO nuevoComponente) {
		marcaDAO.insert(nuevoComponente);
	}

	public void borrarRepuesto(int id_componente_a_eliminar) {
	}
	
	public List<MarcaDTO> obtenerRepuestos() {
		return marcaDAO.readAll();		
	}
	
	
	
	public void  modificarMarca(MarcaDTO componente_a_modificar) {
		
		
	}

}
