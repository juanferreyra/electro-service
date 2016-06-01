package dto;

import java.util.Date;

public class OrdenCompraDTO {
	
	public int id;
	private int idproveedor;
	private float importe_total;
	private float importe_validado;
	private int idusuario;
	private Date fecha_creacion;
	private String hora_creacion;
	
	public OrdenCompraDTO() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdproveedor() {
		return idproveedor;
	}

	public void setIdproveedor(int idproveedor) {
		this.idproveedor = idproveedor;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public String getHora_creacion() {
		return hora_creacion;
	}

	public void setHora_creacion(String hora_creacion) {
		this.hora_creacion = hora_creacion;
	}

	public float getImporte_total() {
		return importe_total;
	}

	public void setImporte_total(float importe_total) {
		this.importe_total = importe_total;
	}

	public float getImporte_validado() {
		return importe_validado;
	}

	public void setImporte_validado(float importe_validado) {
		this.importe_validado = importe_validado;
	}
	
}
