package modelo;

import java.util.List;

//import dto.IngresoDTO;
//import dto.ClienteDTO;
import dto.MarcaDTO;
import dto.TipoProductoDTO;
import persistencia.dao.MarcaDAO;
import persistencia.dao.TipoProductoDAO;

public class Ingreso {
	
	private int id;
	//private IngresoDAO ingreso;
	private MarcaDAO marca;
	private TipoProductoDAO tipoproducto;
	
	public Ingreso(){
		//this.ingreso = new IngresoDAO();
		this.marca = new MarcaDAO();
		this.tipoproducto = new TipoProductoDAO();
	}

	public List<MarcaDTO> obtenerMarcas() {
		return marca.readAll();
	}
	
	public List<TipoProductoDTO> obtenerTiposProductos() {
		return tipoproducto.readAll();
	}
}


