package dto;

import java.util.Date;

public class IngresoDTO {
	private int id;
	private String descripcion;
	private int idcliente;
	private int idmarca;
	private int idtipo_producto;
	private Date fecha_creacion;
	
	public IngresoDTO(int id, String descripcion, int idcliente,int idmarca,int idtipo_producto, Date fecha_creacion,int idusuario)
	{
		this.id = id;
		this.descripcion = descripcion;
		this.idcliente = idcliente;
		this.idmarca = idmarca;
		this.idtipo_producto = idtipo_producto;
		this.fecha_creacion = fecha_creacion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public int getIdmarca() {
		return idmarca;
	}

	public void setIdmarca(int idmarca) {
		this.idmarca = idmarca;
	}

	public int getIdtipo_producto() {
		return idtipo_producto;
	}

	public void setIdtipo_producto(int idtipo_producto) {
		this.idtipo_producto = idtipo_producto;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
}
