package dto;

public class PresupuestoRepuestoDTO {
	
	private int id;
	private int idPresupuesto;
	private int idComponente;
	private int cantidad;
	private Date fechaCreacion;
	private boolean habilitado;
	
	public PresupuestoRepuestoDTO(int id, int idPresupuesto, int idComponente, 
			int cantidad, Date fechaCreacion, boolean habilitado  ){
		
		this.id = id;
		this.idPresupuesto = idPresupuesto;
		this.idComponente = idComponente;
		this.cantidad = cantidad;
		this.fechaCreacion = fechaCreacion;
		this.habilitado = habilitado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPresupuesto() {
		return idPresupuesto;
	}

	public void setIdPresupuesto(int idPresupuesto) {
		this.idPresupuesto = idPresupuesto;
	}

	public int getIdComponente() {
		return idComponente;
	}

	public void setIdComponente(int idComponente) {
		this.idComponente = idComponente;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	
	
	

}
