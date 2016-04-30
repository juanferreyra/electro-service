package modelo;

import java.util.List;

import dto.MarcaDTO;
import dto.TipoProductoDTO;
import persistencia.dao.MarcaDAO;
import persistencia.dao.TipoProductoDAO;

public class Ingreso {
	private int id;
	private MarcaDAO marca;
	private TipoProductoDAO tipoproducto;
	

	public List<MarcaDTO> obtenerMarcas() {
		return marca.readAll();
	}
	
	public List<TipoProductoDTO> obtenerTiposProductos() {
		return tipoproducto.readAll();
	}
	
	public static void main(String[] args) {
		Ingreso ingr  = new Ingreso();
		List<MarcaDTO> marcas = ingr.obtenerMarcas();
		
		for (int i = 0; i < marcas.size(); i++) {
			System.out.println(marcas.get(i).getDetalle().toString());
		}
	}
}


