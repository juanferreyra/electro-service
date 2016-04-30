package modelo;

import java.util.List;

import dto.MarcaDTO;
import dto.TipoProductoDTO;
import persistencia.dao.MarcaDAO;
import persistencia.dao.TipoProductoDAO;

public class Ingreso {
	
	private MarcaDAO marca;
	private TipoProductoDAO tipoproducto;
	

	public List<MarcaDTO> obtenerMarcas() {
		return marca.readAll();
	}
	
	public List<TipoProductoDTO> obtenerTiposProductos() {
		return tipoproducto.readAll();
	}
}
