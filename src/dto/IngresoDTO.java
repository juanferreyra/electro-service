package dto;

import java.util.Date;

public class IngresoDTO {
	private int id;
	private String descripcion;
	private int idcliente;
	private int idmarca;
	private int idtipo_producto;
	private String descripcion_falla;
	private Boolean envio;
	private Boolean envio_default;
	private String direccion_alternativa;
	private float monto_envio;
	private Date fecha_creacion;
	private int estado;
	private int idusuario;
	
	public IngresoDTO(int id,  int idcliente, String descripcion, int idmarca,int idtipo_producto,String descripcion_falla, Boolean envio, Boolean envioDefault, String direccion_alternativa, float monto_envio, Date fecha_creacion, int estado, int idusuario)
	{
		this.id = id;
		this.idcliente = idcliente;
		this.descripcion = descripcion;
		this.idmarca = idmarca;
		this.idtipo_producto = idtipo_producto;
		this.descripcion_falla = descripcion_falla;
		this.envio = envio;
		this.envio_default = envioDefault;
		this.direccion_alternativa = direccion_alternativa;
		this.monto_envio = monto_envio;
		this.fecha_creacion = fecha_creacion;
		this.estado = estado;
		this.idusuario = idusuario;
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

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getDescripcion_falla() {
		return descripcion_falla;
	}

	public void setDescripcion_falla(String descripcion_falla) {
		this.descripcion_falla = descripcion_falla;
	}

	public Boolean getEnvio() {
		return envio;
	}

	public void setEnvio(Boolean envio) {
		this.envio = envio;
	}

	public Boolean getEnvio_default() {
		return envio_default;
	}

	public void setEnvio_default(Boolean envio_default) {
		this.envio_default = envio_default;
	}

	public String getDireccion_alternativa() {
		return direccion_alternativa;
	}

	public void setDireccion_alternativa(String direccion_alternativa) {
		this.direccion_alternativa = direccion_alternativa;
	}

	public float getMonto_envio() {
		return monto_envio;
	}

	public void setMonto_envio(float monto_envio) {
		this.monto_envio = monto_envio;
	}
	
	public String getMontoEnvioToString()
	{
		return envio.toString();
	}
	
}
