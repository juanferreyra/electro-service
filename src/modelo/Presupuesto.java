package modelo;


import java.util.List;

import persistencia.dao.PresupuestoDAO;
import dto.PresupuestoDTO;


public class Presupuesto 
{
	private PresupuestoDAO presupuesto;;	
	
	public Presupuesto()
	{
		presupuesto = new PresupuestoDAO();
	}
	
	public void agregarPresupuesto(PresupuestoDTO nuevoPresupuesto)
	{
		presupuesto.insert(nuevoPresupuesto);
	}

	public void borrarPresupuesto(PresupuestoDTO presupuesto_a_eliminar) 
	{
		presupuesto.delete(presupuesto_a_eliminar);
	}
	
	public List<PresupuestoDTO> obtenerPresupuestos()
	{
           return presupuesto.readAll();	
	}
	
}
