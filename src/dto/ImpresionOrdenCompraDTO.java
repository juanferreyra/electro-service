package dto;

import java.util.Date;

public class ImpresionOrdenCompraDTO {
	
	public int orden_id;
	public Float orden_importe;
	public Date orden_fecha;
	public String orden_hora;
	public String orden_estado;
	//campos de proveedor
	public String proveedor_razonSocial;
	public int proveedor_cuit;
	public String proveedor_direccion;
	public String proveedor_email;
	public String proveedor_nombreContacto;
	public String proveedor_telefonoContacto;
	public String proveedor_emailContacto;
	public String proveedor_emailPedidos;
	//campos de repuestos
	public String resp_nombre;
	public int resp_cantidad;
	public Float resp_preciounitario;
	public Float resp_preciototal;
	public String resp_marca;
	
	public ImpresionOrdenCompraDTO() {
		
	}

	public int getOrden_id() {
		return orden_id;
	}

	public void setOrden_id(int orden_id) {
		this.orden_id = orden_id;
	}

	public Float getOrden_importe() {
		return orden_importe;
	}

	public void setOrden_importe(Float orden_importe) {
		this.orden_importe = orden_importe;
	}

	public Date getOrden_fecha() {
		return orden_fecha;
	}

	public void setOrden_fecha(Date orden_fecha) {
		this.orden_fecha = orden_fecha;
	}

	public String getOrden_hora() {
		return orden_hora;
	}

	public void setOrden_hora(String orden_hora) {
		this.orden_hora = orden_hora;
	}

	public String getOrden_estado() {
		return orden_estado;
	}

	public void setOrden_estado(String orden_estado) {
		this.orden_estado = orden_estado;
	}

	public String getProveedor_razonSocial() {
		return proveedor_razonSocial;
	}

	public void setProveedor_razonSocial(String proveedor_razonSocial) {
		this.proveedor_razonSocial = proveedor_razonSocial;
	}

	public int getProveedor_cuit() {
		return proveedor_cuit;
	}

	public void setProveedor_cuit(int proveedor_cuit) {
		this.proveedor_cuit = proveedor_cuit;
	}

	public String getProveedor_direccion() {
		return proveedor_direccion;
	}

	public void setProveedor_direccion(String proveedor_direccion) {
		this.proveedor_direccion = proveedor_direccion;
	}

	public String getProveedor_email() {
		return proveedor_email;
	}

	public void setProveedor_email(String proveedor_email) {
		this.proveedor_email = proveedor_email;
	}

	public String getProveedor_nombreContacto() {
		return proveedor_nombreContacto;
	}

	public void setProveedor_nombreContacto(String proveedor_nombreContacto) {
		this.proveedor_nombreContacto = proveedor_nombreContacto;
	}

	public String getProveedor_telefonoContacto() {
		return proveedor_telefonoContacto;
	}

	public void setProveedor_telefonoContacto(String proveedor_telefonoContacto) {
		this.proveedor_telefonoContacto = proveedor_telefonoContacto;
	}

	public String getProveedor_emailContacto() {
		return proveedor_emailContacto;
	}

	public void setProveedor_emailContacto(String proveedor_emailContacto) {
		this.proveedor_emailContacto = proveedor_emailContacto;
	}

	public String getProveedor_emailPedidos() {
		return proveedor_emailPedidos;
	}

	public void setProveedor_emailPedidos(String proveedor_emailPedidos) {
		this.proveedor_emailPedidos = proveedor_emailPedidos;
	}

	public String getResp_nombre() {
		return resp_nombre;
	}

	public void setResp_nombre(String resp_nombre) {
		this.resp_nombre = resp_nombre;
	}

	public int getResp_cantidad() {
		return resp_cantidad;
	}

	public void setResp_cantidad(int resp_cantidad) {
		this.resp_cantidad = resp_cantidad;
	}

	public Float getResp_preciounitario() {
		return resp_preciounitario;
	}

	public void setResp_preciounitario(Float resp_preciounitario) {
		this.resp_preciounitario = resp_preciounitario;
	}

	public Float getResp_preciototal() {
		return resp_preciototal;
	}

	public void setResp_preciototal(Float resp_preciototal) {
		this.resp_preciototal = resp_preciototal;
	}

	public String getResp_marca() {
		return resp_marca;
	}

	public void setResp_marca(String resp_marca) {
		this.resp_marca = resp_marca;
	}
}
