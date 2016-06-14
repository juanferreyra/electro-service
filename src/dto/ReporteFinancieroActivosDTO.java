package dto;

public class ReporteFinancieroActivosDTO {
		
	public String fecha_ingreso;
	public int nro_ingreso;
	public String marca;
	public String tipo;
	public float valor_componentes;
	public float valor_mano_obra;
	public String valor_flete;
	public float valor_total;
	public String fecha_retiro;
	
	public ReporteFinancieroActivosDTO() {
		
	}
	
	public String getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(String string) {
		this.fecha_ingreso = string;
	}

	public int getNro_ingreso() {
		return nro_ingreso;
	}

	public void setNro_ingreso(int nro_ingreso) {
		this.nro_ingreso = nro_ingreso;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public float getValor_componentes() {
		return valor_componentes;
	}

	public void setValor_componentes(float valor_componentes) {
		this.valor_componentes = valor_componentes;
	}

	public float getValor_mano_obra() {
		return valor_mano_obra;
	}

	public void setValor_mano_obra(float valor_mano_obra) {
		this.valor_mano_obra = valor_mano_obra;
	}

	public String getValor_flete() {
		return valor_flete;
	}

	public void setValor_flete(String valor_flete) {
		this.valor_flete = valor_flete;
	}

	public float getValor_total() {
		return valor_total;
	}

	public void setValor_total(float valor_total) {
		this.valor_total = valor_total;
	}

	public String getFecha_retiro() {
		return fecha_retiro;
	}

	public void setFecha_retiro(String fecha_retiro) {
		this.fecha_retiro = fecha_retiro;
	}
	
}
