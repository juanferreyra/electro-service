package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dto.ImpresionOrdenCompraDTO;
import persistencia.conexion.Conexion;

public class ImpresionOrdenCompraDAO {
	
	private static final String readall = "SELECT oc.id as orden_id, oc.importe_total as orden_importe, DATE(oc.fecha_creacion) as orden_fecha, "
			+ " TIME(oc.fecha_creacion) as orden_hora, oc.estado as orden_estado, p.razon_social as proveedor_razonSocial,  p.cuit as proveedor_cuit,"
			+ " p.direccion as proveedor_direccion, p.mail as proveedor_mail, p.contacto_nombre as proveedor_nombreContacto, p.contacto_telefono as proveedor_telefonoContacto,"
			+ " p.contacto_mail as proveedor_emailContacto, p.mail_para_pedidos as proveedor_emailPedidos, r.detalle as resp_nombre, ocr.cantidad as resp_cantidad,"
			+ " ocr.precio_unitario as resp_preciounitario, ocr.precio_unitario * ocr.cantidad as resp_preciototal, m.detalle as resp_marca"
			+ " FROM orden_compra oc LEFT JOIN orden_compra_repuestos ocr ON(ocr.idorden_compra=oc.id) LEFT JOIN repuesto r ON(ocr.idrepuesto=r.id) LEFT JOIN"
			+ " proveedor p ON(p.id=oc.idproveedor) LEFT JOIN marca_producto m ON(m.id=r.idmarca) WHERE oc.habilitado=true; ";
			
	private static final String find = "SELECT oc.id as orden_id, oc.importe_total as orden_importe, DATE(oc.fecha_creacion) as orden_fecha, "
			+ " TIME(oc.fecha_creacion) as orden_hora, oc.estado as orden_estado, p.razon_social as proveedor_razonSocial,  p.cuit as proveedor_cuit,"
			+ " p.direccion as proveedor_direccion, p.mail as proveedor_mail, p.contacto_nombre as proveedor_nombreContacto, p.contacto_telefono as proveedor_telefonoContacto,"
			+ " p.contacto_mail as proveedor_emailContacto, p.mail_para_pedidos as proveedor_emailPedidos, r.detalle as resp_nombre, ocr.cantidad as resp_cantidad,"
			+ " ocr.precio_unitario as resp_preciounitario, ocr.precio_unitario * ocr.cantidad as resp_preciototal, m.detalle as resp_marca"
			+ " FROM orden_compra oc LEFT JOIN orden_compra_repuestos ocr ON(ocr.idorden_compra=oc.id) LEFT JOIN repuesto r ON(ocr.idrepuesto=r.id) LEFT JOIN"
			+ " proveedor p ON(p.id=oc.idproveedor) LEFT JOIN marca_producto m ON(m.id=r.idmarca) WHERE oc.id = ? AND oc.habilitado=true;";
	
	
	private Conexion conexion = Conexion.getConexion();

	public ArrayList<ImpresionOrdenCompraDTO> readAll() {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<ImpresionOrdenCompraDTO> ordenes = new ArrayList<ImpresionOrdenCompraDTO>();

		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				//java.util.Date orden_fecha = new java.util.Date(resultSet.getDate("orden_fecha").getTime());
				
				ImpresionOrdenCompraDTO orden = new ImpresionOrdenCompraDTO();
				orden.setOrden_id(resultSet.getInt("orden_id"));
				orden.setOrden_importe(resultSet.getFloat("orden_importe"));
				orden.setOrden_fecha(resultSet.getDate("orden_fecha"));
				orden.setOrden_hora(resultSet.getString("orden_hora"));
				orden.setOrden_estado(resultSet.getString("orden_estado"));
				orden.setProveedor_razonSocial(resultSet.getString("proveedor_razonSocial"));
				orden.setProveedor_cuit(resultSet.getInt("proveedor_cuit"));
				orden.setProveedor_direccion(resultSet.getString("proveedor_direccion"));
				orden.setProveedor_email(resultSet.getString("proveedor_mail"));
				orden.setProveedor_nombreContacto(resultSet.getString("proveedor_nombreContacto"));
				orden.setProveedor_telefonoContacto(resultSet.getString("proveedor_telefonoContacto"));
				orden.setProveedor_emailContacto(resultSet.getString("proveedor_emailContacto"));
				orden.setProveedor_emailPedidos(resultSet.getString("proveedor_emailPedidos"));
				orden.setResp_nombre(resultSet.getString("resp_nombre"));
				orden.setResp_cantidad(resultSet.getInt("resp_cantidad"));
				orden.setResp_preciounitario(resultSet.getFloat("resp_preciounitario"));
				orden.setResp_preciototal(resultSet.getFloat("resp_preciototal"));
				orden.setResp_marca(resultSet.getString("resp_marca"));
				
				ordenes.add(orden);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return ordenes;
	}

	public ArrayList<ImpresionOrdenCompraDTO> find(int id) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<ImpresionOrdenCompraDTO> ordenes = new ArrayList<ImpresionOrdenCompraDTO>();

		try {
			statement = conexion.getSQLConexion().prepareStatement(find);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				//java.util.Date orden_fecha = new java.util.Date(resultSet.getDate("orden_fecha").getTime());
				
				ImpresionOrdenCompraDTO orden = new ImpresionOrdenCompraDTO();
				orden.setOrden_id(resultSet.getInt("orden_id"));
				orden.setOrden_importe(resultSet.getFloat("orden_importe"));
				orden.setOrden_fecha(resultSet.getDate("orden_fecha"));
				orden.setOrden_hora(resultSet.getString("orden_hora"));
				orden.setOrden_estado(resultSet.getString("orden_estado"));
				orden.setProveedor_razonSocial(resultSet.getString("proveedor_razonSocial"));
				orden.setProveedor_cuit(resultSet.getInt("proveedor_cuit"));
				orden.setProveedor_direccion(resultSet.getString("proveedor_direccion"));
				orden.setProveedor_email(resultSet.getString("proveedor_mail"));
				orden.setProveedor_nombreContacto(resultSet.getString("proveedor_nombreContacto"));
				orden.setProveedor_telefonoContacto(resultSet.getString("proveedor_telefonoContacto"));
				orden.setProveedor_emailContacto(resultSet.getString("proveedor_emailContacto"));
				orden.setProveedor_emailPedidos(resultSet.getString("proveedor_emailPedidos"));
				orden.setResp_nombre(resultSet.getString("resp_nombre"));
				orden.setResp_cantidad(resultSet.getInt("resp_cantidad"));
				orden.setResp_preciounitario(resultSet.getFloat("resp_preciounitario"));
				orden.setResp_preciototal(resultSet.getFloat("resp_preciototal"));
				orden.setResp_marca(resultSet.getString("resp_marca"));
				
				ordenes.add(orden);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return ordenes;
	}
}
