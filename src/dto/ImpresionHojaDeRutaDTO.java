package dto;

import java.util.Date;

public class ImpresionHojaDeRutaDTO {
	//de la cabecera del informe
	public int idhojaruta;
	public String flete_telefono;
	public String flete_patente;
	public String flete_modelo;
	public String flete_conductor;
    public Date fecha;
    public String hora;
    //del detalle
    public int nro;
	public String producto;
	public String cliente;
	public String localidad;
	public String direccion;
	public float importe;
	public String estado;

	public ImpresionHojaDeRutaDTO(int nro, String producto, String cliente, String localidad, String direccion, float importe, String estado) {
		this.nro = nro;
		this.producto = producto;
		this.cliente = cliente;
		this.localidad = localidad;
		this.direccion = direccion;
		this.importe = importe;
		this.estado = estado;
	}
	
	public ImpresionHojaDeRutaDTO() {
		
	}
	
	public int getNro() {
		return nro;
	}

	public void setNro(int nro) {
		this.nro = nro;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public int getIdhojaruta() {
		return idhojaruta;
	}

	public void setIdhojaruta(int idhojaruta) {
		this.idhojaruta = idhojaruta;
	}

	public String getFlete_telefono() {
		return flete_telefono;
	}

	public void setFlete_telefono(String flete_telefono) {
		this.flete_telefono = flete_telefono;
	}

	public String getFlete_patente() {
		return flete_patente;
	}

	public void setFlete_patente(String flete_patente) {
		this.flete_patente = flete_patente;
	}

	public String getFlete_modelo() {
		return flete_modelo;
	}

	public void setFlete_modelo(String flete_modelo) {
		this.flete_modelo = flete_modelo;
	}

	public String getFlete_conductor() {
		return flete_conductor;
	}

	public void setFlete_conductor(String flete_conductor) {
		this.flete_conductor = flete_conductor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
}
