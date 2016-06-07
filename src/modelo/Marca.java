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
	
	public void agregarMarca(MarcaDTO nuevoComponente) {
		marcaDAO.insert(nuevoComponente);
	}

	public void borrarMarca(int id_componente_a_eliminar) {
	}
	
	public List<MarcaDTO> obtenerMarcas() {
		return marcaDAO.readAll();		
	}
	
	
	
	public void  modificarMarca(MarcaDTO componente_a_modificar) {
		
		
	}
	
	public String buscarDetalleMarcaXid(int idMarca){
		return marcaDAO.BuscarDetalleXid(idMarca); 
	}

	public int obtenerIdMarca(String detalle) {
		return marcaDAO.buscarIdMarca(detalle);
		
	}

}
