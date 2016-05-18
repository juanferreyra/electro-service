package modelo;

import java.util.Date;

import dto.ReparacionDTO;
import persistencia.dao.ReparacionesDAO;

public class Reparacion {
	private ReparacionesDAO reparacionDAO = new ReparacionesDAO();
	private ReparacionDTO reparacionDTO = new ReparacionDTO();

	public void guardarReparacion(String tecnicoAsignado, Date fecha_reparacion, int horas, int valor_estimado,
			String descripcionFinal, int ingresoId) {
		reparacionDTO.setId(reparacionDAO.getNextId());
		reparacionDTO.setTecnico_asignado(tecnicoAsignado);
		reparacionDTO.setFecha_reparacion(fecha_reparacion);
		reparacionDTO.setHoras(horas);
		reparacionDTO.setValor_estimado(valor_estimado);
		reparacionDTO.setDescripcion_final(descripcionFinal);
		reparacionDTO.setIngreso_id(ingresoId);
		reparacionDAO.insert(reparacionDTO);
	}

	public int getIdReparacion() {
		return reparacionDTO.getId();
	}

}
