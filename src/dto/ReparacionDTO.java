package dto;

import java.util.Date;

public class ReparacionDTO {
	private int id;
	private String tecnico_asignado;
	private Date fecha_reparacion;
	private int horas;
	private int valor_estimado;
	private String descripcion_final;
	private int ingreso_id;

	public ReparacionDTO(int id, String tecnico_asignado, Date fecha_reparacion, int horas, int valor_estimado,
			String descripcion_final, int ingreso_id) {
		this.id = id;
		this.tecnico_asignado = tecnico_asignado;
		this.fecha_reparacion = fecha_reparacion;
		this.horas = horas;
		this.valor_estimado = valor_estimado;
		this.descripcion_final = descripcion_final;
		this.ingreso_id = ingreso_id;
	}

	public ReparacionDTO() {

	}

	public String getTecnico_asignado() {
		return tecnico_asignado;
	}

	public void setTecnico_asignado(String tecnico_asignado) {
		this.tecnico_asignado = tecnico_asignado;
	}

	public Date getFecha_reparacion() {
		return fecha_reparacion;
	}

	public void setFecha_reparacion(Date fecha_reparacion) {
		this.fecha_reparacion = fecha_reparacion;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public int getValor_estimado() {
		return valor_estimado;
	}

	public void setValor_estimado(int valor_estimado) {
		this.valor_estimado = valor_estimado;
	}

	public String getDescripcion_final() {
		return descripcion_final;
	}

	public void setDescripcion_final(String descripcion_final) {
		this.descripcion_final = descripcion_final;
	}

	public int getIngreso_id() {
		return ingreso_id;
	}

	public void setIngreso_id(int ingreso_id) {
		this.ingreso_id = ingreso_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
