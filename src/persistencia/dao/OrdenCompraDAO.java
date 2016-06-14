package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dto.OrdenCompraDTO;
import persistencia.conexion.Conexion;

public class OrdenCompraDAO {

	private static final String insert = "INSERT INTO orden_compra(`idproveedor`, `importe_total`, `idusuario`, `fecha_creacion`, `fecha_modificacion`, `habilitado`,`estado`) VALUES (?, ?, ?, now(), now(), true,'NUEVA');";
	
	private static final String readall = "SELECT id, idproveedor, idusuario, importe_total, "
			+ "date(fecha_creacion) as fecha_creacion, time(fecha_creacion) as hora_creacion, "
			+ "date(fecha_modificacion) as fecha_modificacion, time(fecha_modificacion) as hora_modificacion, estado "
			+ "FROM orden_compra WHERE habilitado=true;";
	
	private static final String find =  "SELECT id, idproveedor, idusuario, importe_total, "
			+ "date(fecha_creacion) as fecha_creacion, time(fecha_creacion) as hora_creacion , "
			+ "date(fecha_modificacion) as fecha_modificacion, time(fecha_modificacion) as hora_modificacion, estado "
			+ "FROM orden_compra WHERE habilitado=true AND id= ? ;";
	
	private static final String updateEstado = "UPDATE orden_compra SET estado = ? WHERE id = ? ;";
	
	private static final String updateMonto = "UPDATE orden_compra set importe_total = ? , fecha_modificacion = now() WHERE id = ?";
	
	private static final String nextId = "SELECT Auto_Increment as siguiente FROM INFORMATION_SCHEMA.TABLES WHERE Table_name = 'orden_compra';";
	
	private Conexion conexion = Conexion.getConexion();

	public ArrayList<OrdenCompraDTO> readAll() {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<OrdenCompraDTO> presupuestos = new ArrayList<OrdenCompraDTO>();

		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			
			while (resultSet.next()){
				java.util.Date fecha_creacion = new java.util.Date(resultSet.getDate("fecha_creacion").getTime());
				java.util.Date fecha_modificacion = new java.util.Date(resultSet.getDate("fecha_modificacion").getTime());
				
				OrdenCompraDTO orden = new OrdenCompraDTO();
				orden.setId(resultSet.getInt("id"));
				orden.setIdproveedor(resultSet.getInt("idproveedor"));
				orden.setImporte_total(resultSet.getFloat("importe_total"));
				//orden.setImporte_validado(resultSet.getFloat("importe_validado"));
				orden.setIdusuario(resultSet.getInt("idusuario"));
				orden.setFecha_creacion(fecha_creacion);
				orden.setHora_creacion(resultSet.getString("hora_creacion"));
				orden.setFecha_modificacion(fecha_modificacion);
				orden.setHora_modificacion(resultSet.getString("hora_modificacion"));
				orden.setEstado(resultSet.getString("estado"));
				
				presupuestos.add(orden);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return presupuestos;
	}

	public boolean insert(OrdenCompraDTO orden) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);

			statement.setInt(1, orden.getIdproveedor());
			statement.setFloat(2, orden.getImporte_total());
			statement.setInt(3, orden.getIdusuario());
			
			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return false;
	}
	
	public boolean updateEstado(String estado,int id) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(updateEstado);

			statement.setString(1, estado);
			statement.setInt(2, id);
			
			if (statement.executeUpdate() > 0)
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return false;
	}
	
	public boolean updateImporteTotal(Float importe, int id) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(updateMonto);

			statement.setFloat(1, importe);
			statement.setInt(2, id);
			
			if (statement.executeUpdate() > 0)
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return false;
	}

	public OrdenCompraDTO find(int idorden) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		OrdenCompraDTO orden = null;

		try {
			statement = conexion.getSQLConexion().prepareStatement(find);
			statement.setInt(1, idorden);
			resultSet = statement.executeQuery();

			while (resultSet.next()){
				
				java.util.Date fecha_creacion = new java.util.Date(resultSet.getDate("fecha_creacion").getTime());
				java.util.Date fecha_modificacion = new java.util.Date(resultSet.getDate("fecha_modificacion").getTime());
				
				orden = new OrdenCompraDTO();
				orden.setId(resultSet.getInt("id"));
				orden.setIdproveedor(resultSet.getInt("idproveedor"));
				orden.setImporte_total(resultSet.getFloat("importe_total"));
				orden.setIdusuario(resultSet.getInt("idusuario"));
				orden.setFecha_creacion(fecha_creacion);
				orden.setHora_creacion(resultSet.getString("hora_creacion"));
				orden.setFecha_modificacion(fecha_modificacion);
				orden.setHora_modificacion(resultSet.getString("hora_modificacion"));
				orden.setEstado(resultSet.getString("estado"));
			}
			
		} catch (
				SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		
		return orden;
	}
	
	public int getNextId() {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		int clave = -1;

		try {
			statement = conexion.getSQLConexion().prepareStatement(nextId);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				clave = resultSet.getInt("siguiente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return clave;
	}
}
