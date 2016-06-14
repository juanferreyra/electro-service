package dto;

public class ReporteFinancieroPasivosDTO {
	
	public String fecha_orden;
	public int nro_orden;
	public String proveedor;
	public float valor_orden;
	public String fecha_recepcion;
	
	public ReporteFinancieroPasivosDTO() {
		
	}
	
	public String getFecha_orden() {
		return fecha_orden;
	}

	public void setFecha_orden(String string) {
		this.fecha_orden = string;
	}

	public int getNro_orden() {
		return nro_orden;
	}

	public void setNro_orden(int nro_orden) {
		this.nro_orden = nro_orden;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public float getValor_orden() {
		return valor_orden;
	}

	public void setValor_orden(float valor_orden) {
		this.valor_orden = valor_orden;
	}

	public String getFecha_recepcion() {
		return fecha_recepcion;
	}

	public void setFecha_recepcion(String fecha_recepcion) {
		this.fecha_recepcion = fecha_recepcion;
	}
	
}
