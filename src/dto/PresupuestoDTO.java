package dto;

import java.util.Date;

public class PresupuestoDTO {
	
	private int id;
	private int IdIngreso;
	private String descripcionBreve;
	private String descripcionTecnica;
	private String importeManoObra;
	//private Date fechacreacion;
	private Date fechavencimiento;
	private int idUsuario;
	//private boolean habilitado;

	
	public PresupuestoDTO(int id, int idIngreso, String descripcionBreve, String descripcionTecnica,
			String importeManoObra,/*Date fechaCreacion,*/ Date fechaVencimiento, int idUsusario/*, boolean habilitado*/){
		
		this.id = id;
		this.IdIngreso = idIngreso;
		this.descripcionBreve = descripcionBreve;
		this.descripcionTecnica = descripcionTecnica;
		this.importeManoObra = importeManoObra;
		//this.fechacreacion = fechaCreacion;
		this.fechavencimiento = fechaVencimiento;
		this.idUsuario = idUsusario;
		//this.habilitado = habilitado;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getIdIngreso() {
		return IdIngreso;
	}


	public void setIdIngreso(int idIngreso) {
		IdIngreso = idIngreso;
	}


	public String getDescripcionBreve() {
		return descripcionBreve;
	}


	public void setDescripcionBreve(String descripcionBreve) {
		this.descripcionBreve = descripcionBreve;
	}


	public String getDescripcionTecnica() {
		return descripcionTecnica;
	}


	public void setDescripcionTecnica(String descripcionTecnica) {
		this.descripcionTecnica = descripcionTecnica;
	}


	public String getImporteManoObra() {
		return importeManoObra;
	}


	public void setImporteManoObra(String importeManoObra) {
		this.importeManoObra = importeManoObra;
	}


	/*public Date getFechacreacion() {
		return fechacreacion;
	}


	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
*/

	public Date getFechavencimiento() {
		return fechavencimiento;
	}


	public void setFechavencimiento(Date fechavencimiento) {
		this.fechavencimiento = fechavencimiento;
	}


	public int getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	/*public boolean isHabilitado() {
		return habilitado;
	}


	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	*/
	
	
	

}
