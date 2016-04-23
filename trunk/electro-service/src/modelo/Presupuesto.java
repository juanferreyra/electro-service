package modelo;


import java.util.List;

import persistencia.dao.TipoProductoDAO;
import dto.TipoProductoDTO;


public class Presupuesto 
{
	private TipoProductoDAO tipoProducto;	
	
	public Presupuesto()
	{
		tipoProducto = new TipoProductoDAO();
	}
	
	public void agregarPersona(TipoProductoDTO nuevaPersona)
	{
		tipoProducto.insert(nuevaPersona);
	}

	public void borrarPersona(TipoProductoDTO persona_a_eliminar) 
	{
		tipoProducto.delete(persona_a_eliminar);
	}
	
	public List<TipoProductoDTO> obtenerTiposProducto()
	{
            //return tipoProducto.readAll();	
            return null;
	}
	
}
