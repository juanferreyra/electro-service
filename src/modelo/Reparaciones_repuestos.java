package modelo;

import java.util.Date;

import dto.ReparacionRepuestoDTO;
import persistencia.dao.ReparacionesRepuestosDAO;

public class Reparaciones_repuestos {

	private ReparacionesRepuestosDAO reparacion_repuestosDAO = new ReparacionesRepuestosDAO();
	private ReparacionRepuestoDTO reparacion_repuestosDTO = new ReparacionRepuestoDTO();

	public void guardarReparacion_repuesto(int idReparacion, int idrepuesto, Integer cantidad, Date fecha_creacion,
			boolean habilitado) {

		reparacion_repuestosDTO.setIdreparacion(idReparacion);
		reparacion_repuestosDTO.setIdrepuesto(idrepuesto);
		reparacion_repuestosDTO.setCantidad(cantidad);
		reparacion_repuestosDTO.setFecha_creacion(fecha_creacion);
		reparacion_repuestosDTO.setHabilitado(habilitado);
		reparacion_repuestosDAO.insert(reparacion_repuestosDTO);

	}

}
