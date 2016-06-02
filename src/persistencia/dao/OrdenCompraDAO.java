package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dto.OrdenCompraDTO;
import persistencia.conexion.Conexion;

public class OrdenCompraDAO {

	private static final String insert = "INSERT INTO orden_compra(`idproveedor`, `importe_total`, `idusuario`, `fecha_creacion`, `habilitado`,`estado`) VALUES (?, ?, ?, now(), true,'NUEVA');";

	//TODO:private static final String updateImporte = "";
	
	private static final String readall = "SELECT id, idproveedor, idusuario, importe_total, importe_validado, "
			+ "date(fecha_creacion) as fecha_creacion, time(fecha_creacion) as hora_creacion ,estado "
			+ "FROM orden_compra WHERE habilitado=true;";
	
	private static final String find =  "SELECT id, idproveedor, idusuario, importe_total, importe_validado, "
			+ "date(fecha_creacion) as fecha_creacion, time(fecha_creacion) as hora_creacion , estado "
			+ "FROM orden_compra WHERE habilitado=true AND id= ? ;";
	
	private static final String updateEstado = "UPDATE orden_compra SET `estado`=? WHERE `id`= ? ;";
	
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
				
				OrdenCompraDTO orden = new OrdenCompraDTO();
				orden.setId(resultSet.getInt("id"));
				orden.setIdproveedor(resultSet.getInt("idproveedor"));
				orden.setImporte_total(resultSet.getFloat("importe_total"));
				orden.setImporte_validado(resultSet.getFloat("importe_validado"));
				orden.setIdusuario(resultSet.getInt("idusuario"));
				orden.setFecha_creacion(fecha_creacion);
				orden.setHora_creacion(resultSet.getString("hora_creacion"));
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
				
				orden = new OrdenCompraDTO();
				orden.setId(resultSet.getInt("id"));
				orden.setIdproveedor(resultSet.getInt("idproveedor"));
				orden.setImporte_total(resultSet.getFloat("importe_total"));
				orden.setImporte_validado(resultSet.getFloat("importe_validado"));
				orden.setIdusuario(resultSet.getInt("idusuario"));
				orden.setFecha_creacion(fecha_creacion);
				orden.setHora_creacion(resultSet.getString("hora_creacion"));
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
