package modelo;


import java.util.List;

import persistencia.dao.PresupuestoDAO;
import dto.PresupuestoDTO;


public class Presupuesto 
{
	private PresupuestoDAO presupuesto;	
	
	public Presupuesto()
	{
		presupuesto = new PresupuestoDAO();
	}
	
	public void agregarPersona(PresupuestoDTO nuevaPresupuesto)
	{
		presupuesto.insert(nuevaPresupuesto);
	}

	public void borrarPersona(PresupuestoDTO presupuesto_a_eliminar) 
	{
		presupuesto.delete(presupuesto_a_eliminar);
	}
	
	public List<PresupuestoDTO> obtenerPresupuestos()
	{
            //return tipoProducto.readAll();	
            return null;
	}
	
	public PresupuestoDTO find (int idingreso){
		
		return presupuesto.find(idingreso);
	}
	
}
