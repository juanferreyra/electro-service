package dto;

import java.sql.Date;

public class ImpresionIngresoDTO {

	private int id;
	private String cliente_nombre;
	private String cliente_telefono;
	private String cliente_direccion;
	private String cliente_localidad;
	private String cliente_email;
	
	private String nombreProducto;
	private String marca;
	private String tipo_producto;
	private String descripcion_falla;
	
	private String envio;
	private String direccion_cliente;
	private String direccion_alternativa;
	
	private String monto_envio;
	private Date fecha;
	
	public ImpresionIngresoDTO(){
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCliente_nombre() {
		return cliente_nombre;
	}

	public void setCliente_nombre(String cliente_nombre) {
		this.cliente_nombre = cliente_nombre;
	}

	public String getCliente_telefono() {
		return cliente_telefono;
	}

	public void setCliente_telefono(String cliente_telefono) {
		this.cliente_telefono = cliente_telefono;
	}

	public String getCliente_direccion() {
		return cliente_direccion;
	}

	public void setCliente_direccion(String cliente_direccion) {
		this.cliente_direccion = cliente_direccion;
	}

	public String getCliente_localidad() {
		return cliente_localidad;
	}

	public void setCliente_localidad(String cliente_localidad) {
		this.cliente_localidad = cliente_localidad;
	}

	public String getCliente_email() {
		return cliente_email;
	}

	public void setCliente_email(String cliente_email) {
		this.cliente_email = cliente_email;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getTipo_producto() {
		return tipo_producto;
	}

	public void setTipo_producto(String tipo_producto) {
		this.tipo_producto = tipo_producto;
	}

	public String getDescripcion_falla() {
		return descripcion_falla;
	}

	public void setDescripcion_falla(String descripcion_falla) {
		this.descripcion_falla = descripcion_falla;
	}

	public String getEnvio() {
		return envio;
	}

	public void setEnvio(String envio) {
		this.envio = envio;
	}

	public String getDireccion_cliente() {
		return direccion_cliente;
	}

	public void setDireccion_cliente(String direccion_cliente) {
		this.direccion_cliente = direccion_cliente;
	}

	public String getDireccion_alternativa() {
		return direccion_alternativa;
	}

	public void setDireccion_alternativa(String direccion_alternativa) {
		this.direccion_alternativa = direccion_alternativa;
	}

	public String getMonto_envio() {
		return monto_envio;
	}

	public void setMonto_envio(String monto_envio) {
		this.monto_envio = monto_envio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date date) {
		this.fecha = date;
	}

}
