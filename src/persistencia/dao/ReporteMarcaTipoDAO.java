package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import dto.ReporteMarcaTipoDTO;
import persistencia.conexion.Conexion;

public class ReporteMarcaTipoDAO {

	private static final String marcaTmp ="CREATE TEMPORARY TABLE porcentajes AS (SELECT datos.fecha_creacion, m.detalle AS detalle, count(datos.id) AS cantidad, (SELECT count(datos.idmarca) * 100 / count(datos2.id)"
			+ " FROM (SELECT DATE(i.fecha_creacion) AS fecha_creacion, i.id AS id, i.idtipo_producto, i.idmarca FROM ingreso i LEFT JOIN (SELECT il.idingreso,"
			+ " (SELECT fl.idestado FROM ingreso_log fl WHERE fl.idingreso = il.idingreso ORDER BY fl.id DESC LIMIT 1) AS estado FROM ingreso_log il GROUP BY il.idingreso) "
			+ " log ON (i.id = log.idingreso) WHERE i.habilitado = TRUE AND log.estado = 11) AS datos2 ) AS porcentaje FROM (SELECT DATE(i.fecha_creacion) as fecha_creacion,"
			+ " i.id AS id, i.idtipo_producto, i.idmarca FROM ingreso i LEFT JOIN (SELECT il.idingreso, (SELECT fl.idestado FROM ingreso_log fl WHERE "
			+ " fl.idingreso = il.idingreso ORDER BY fl.id DESC LIMIT 1) AS estado FROM ingreso_log il GROUP BY il.idingreso) log ON (i.id = log.idingreso)"
			+ " WHERE i.habilitado = TRUE AND log.estado = 11) datos LEFT JOIN marca_producto m ON(datos.idmarca = m.id) WHERE datos.fecha_creacion "
			+ " BETWEEN ? AND ? GROUP BY datos.idmarca)";
	
	private static final String tipoTmp ="CREATE TEMPORARY TABLE porcentajes AS (SELECT datos.fecha_creacion, t.detalle AS detalle, count(datos.id) AS cantidad, (SELECT count(datos.idtipo_producto) * 100 / count(datos2.id)"
			+ " FROM (SELECT DATE(i.fecha_creacion) AS fecha_creacion, i.id AS id, i.idtipo_producto, i.idmarca FROM ingreso i LEFT JOIN (SELECT il.idingreso,"
			+ " (SELECT fl.idestado FROM ingreso_log fl WHERE fl.idingreso = il.idingreso ORDER BY fl.id DESC LIMIT 1) AS estado FROM ingreso_log il GROUP BY il.idingreso) "
			+ " log ON (i.id = log.idingreso) WHERE i.habilitado = TRUE AND log.estado = 11) AS datos2 ) AS porcentaje FROM (SELECT DATE(i.fecha_creacion) as fecha_creacion,"
			+ " i.id AS id, i.idtipo_producto, i.idmarca FROM ingreso i LEFT JOIN (SELECT il.idingreso, (SELECT fl.idestado FROM ingreso_log fl WHERE "
			+ " fl.idingreso = il.idingreso ORDER BY fl.id DESC LIMIT 1) AS estado FROM ingreso_log il GROUP BY il.idingreso) log ON (i.id = log.idingreso)"
			+ " WHERE i.habilitado = TRUE AND log.estado = 11) datos LEFT JOIN tipo_producto t ON(datos.idtipo_producto = t.id) WHERE datos.fecha_creacion "
			+ " BETWEEN ? AND ? GROUP BY datos.idtipo_producto)";
	
	private static final String marcaTipoTmp = "CREATE TEMPORARY TABLE porcentajes AS (SELECT datos.fecha_creacion , CONCAT(m.detalle,'/', t.detalle) AS detalle, count(datos.id) as cantidad, "
			+ "(SELECT count(datos.marcaTipo) * 100 / count(datos2.id) FROM (SELECT DATE(i.fecha_creacion) as fecha_creacion, i.id AS id, "
			+ " i.idmarca, i.idtipo_producto FROM ingreso i LEFT JOIN (SELECT il.idingreso, (SELECT fl.idestado FROM ingreso_log fl WHERE "
			+ " fl.idingreso = il.idingreso ORDER BY fl.id DESC LIMIT 1) AS estado FROM ingreso_log il GROUP BY il.idingreso) log ON "
			+ " (i.id = log.idingreso) WHERE i.habilitado = TRUE AND log.estado = 11) AS datos2 ) AS porcentaje FROM (SELECT "
			+ " DATE(i.fecha_creacion) as fecha_creacion, i.id AS id, i.idtipo_producto, i.idmarca, concat(i.idmarca,i.idtipo_producto) "
			+ " as marcaTipo FROM ingreso i LEFT JOIN (SELECT il.idingreso, (SELECT fl.idestado FROM ingreso_log fl WHERE fl.idingreso = il.idingreso"
			+ " ORDER BY fl.id DESC LIMIT 1) AS estado FROM ingreso_log il GROUP BY il.idingreso) log ON (i.id = log.idingreso) WHERE i.habilitado = TRUE "
			+ " AND log.estado = 11) datos LEFT JOIN marca_producto m ON(datos.idmarca = m.id) LEFT JOIN tipo_producto t ON(datos.idtipo_producto = t.id)  WHERE datos.fecha_creacion "
			+ " BETWEEN ? AND ?  GROUP BY datos.marcaTipo)";
	
	private static final String porcentajes10 = "SELECT if(porcentaje<=10, 'OTROS', detalle) as descripcion, sum(cantidad) as cantidad, sum(porcentaje) as porcentaje FROM porcentajes GROUP BY descripcion;";
	
	private static final String porcentajes20 = "SELECT if(porcentaje<=20, 'OTROS', detalle) as descripcion, sum(cantidad) as cantidad, sum(porcentaje) as porcentaje FROM porcentajes GROUP BY descripcion;";
	
	private Conexion conexion = Conexion.getConexion();
	
	public ArrayList<ReporteMarcaTipoDTO> porMarca10(Date desde, Date hasta) {
		
		boolean consultar = false;
		PreparedStatement statementTemp;
		
		try {
			statementTemp = conexion.getSQLConexion().prepareStatement(marcaTmp);
			statementTemp.setDate(1, new java.sql.Date(desde.getTime()));
			statementTemp.setDate(2, new java.sql.Date(hasta.getTime()));

			if (statementTemp.executeUpdate() > 0) // Si se ejecuto la tabla temporal asigno true
				consultar = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(consultar) {
			
			conexion = Conexion.getConexion();
			PreparedStatement statement;
			ResultSet resultSet;
			ArrayList<ReporteMarcaTipoDTO> items = new ArrayList<ReporteMarcaTipoDTO>();

			try {
				statement = conexion.getSQLConexion().prepareStatement(porcentajes10);
	
				resultSet = statement.executeQuery();

				while (resultSet.next()) {
					ReporteMarcaTipoDTO item = new ReporteMarcaTipoDTO();
					item.setDetalle(resultSet.getString("descripcion"));
					item.setCantidad(resultSet.getInt("cantidad"));
					item.setPorcentaje(resultSet.getFloat("porcentaje"));
					items.add(item);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Conexion.cerrarConexion();
			}
			return items;
		}else {
			Conexion.cerrarConexion();
		}
		
		return null;
	}
	
	public ArrayList<ReporteMarcaTipoDTO> porTipo10(Date desde, Date hasta) {
		
		boolean consultar = false;
		PreparedStatement statementTemp;
		
		try {
			statementTemp = conexion.getSQLConexion().prepareStatement(tipoTmp);
			statementTemp.setDate(1, new java.sql.Date(desde.getTime()));
			statementTemp.setDate(2, new java.sql.Date(hasta.getTime()));

			if (statementTemp.executeUpdate() > 0) // Si se ejecuto la tabla temporal asigno true
				consultar = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(consultar) {
			
			conexion = Conexion.getConexion();
			PreparedStatement statement;
			ResultSet resultSet;
			ArrayList<ReporteMarcaTipoDTO> items = new ArrayList<ReporteMarcaTipoDTO>();

			try {
				statement = conexion.getSQLConexion().prepareStatement(porcentajes10);
	
				resultSet = statement.executeQuery();

				while (resultSet.next()) {
					ReporteMarcaTipoDTO item = new ReporteMarcaTipoDTO();
					item.setDetalle(resultSet.getString("descripcion"));
					item.setCantidad(resultSet.getInt("cantidad"));
					item.setPorcentaje(resultSet.getFloat("porcentaje"));
					items.add(item);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Conexion.cerrarConexion();
			}
			return items;
		}else {
			Conexion.cerrarConexion();
		}
		
		return null;
	}
	
	public ArrayList<ReporteMarcaTipoDTO> porMarcaTipo10(Date desde, Date hasta) {
		
		boolean consultar = false;
		PreparedStatement statementTemp;
		
		try {
			statementTemp = conexion.getSQLConexion().prepareStatement(marcaTipoTmp);
			statementTemp.setDate(1, new java.sql.Date(desde.getTime()));
			statementTemp.setDate(2, new java.sql.Date(hasta.getTime()));

			if (statementTemp.executeUpdate() > 0) // Si se ejecuto la tabla temporal asigno true
				consultar = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(consultar) {
			
			conexion = Conexion.getConexion();
			PreparedStatement statement;
			ResultSet resultSet;
			ArrayList<ReporteMarcaTipoDTO> items = new ArrayList<ReporteMarcaTipoDTO>();

			try {
				statement = conexion.getSQLConexion().prepareStatement(porcentajes10);
	
				resultSet = statement.executeQuery();

				while (resultSet.next()) {
					ReporteMarcaTipoDTO item = new ReporteMarcaTipoDTO();
					item.setDetalle(resultSet.getString("descripcion"));
					item.setCantidad(resultSet.getInt("cantidad"));
					item.setPorcentaje(resultSet.getFloat("porcentaje"));
					items.add(item);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Conexion.cerrarConexion();
			}
			return items;
		}else {
			Conexion.cerrarConexion();
		}
		
		return null;
	}
	
	public ArrayList<ReporteMarcaTipoDTO> porMarca20(Date desde, Date hasta) {
		
		boolean consultar = false;
		PreparedStatement statementTemp;
		
		try {
			statementTemp = conexion.getSQLConexion().prepareStatement(marcaTmp);
			statementTemp.setDate(1, new java.sql.Date(desde.getTime()));
			statementTemp.setDate(2, new java.sql.Date(hasta.getTime()));

			if (statementTemp.executeUpdate() > 0) // Si se ejecuto la tabla temporal asigno true
				consultar = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(consultar) {
			
			conexion = Conexion.getConexion();
			PreparedStatement statement;
			ResultSet resultSet;
			ArrayList<ReporteMarcaTipoDTO> items = new ArrayList<ReporteMarcaTipoDTO>();

			try {
				statement = conexion.getSQLConexion().prepareStatement(porcentajes20);
	
				resultSet = statement.executeQuery();

				while (resultSet.next()) {
					ReporteMarcaTipoDTO item = new ReporteMarcaTipoDTO();
					item.setDetalle(resultSet.getString("descripcion"));
					item.setCantidad(resultSet.getInt("cantidad"));
					item.setPorcentaje(resultSet.getFloat("porcentaje"));
					items.add(item);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Conexion.cerrarConexion();
			}
			return items;
		}else {
			Conexion.cerrarConexion();
		}
		
		return null;
	}
	
	public ArrayList<ReporteMarcaTipoDTO> porTipo20(Date desde, Date hasta) {
		
		boolean consultar = false;
		PreparedStatement statementTemp;
		
		try {
			statementTemp = conexion.getSQLConexion().prepareStatement(tipoTmp);
			statementTemp.setDate(1, new java.sql.Date(desde.getTime()));
			statementTemp.setDate(2, new java.sql.Date(hasta.getTime()));

			if (statementTemp.executeUpdate() > 0) // Si se ejecuto la tabla temporal asigno true
				consultar = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(consultar) {
			
			conexion = Conexion.getConexion();
			PreparedStatement statement;
			ResultSet resultSet;
			ArrayList<ReporteMarcaTipoDTO> items = new ArrayList<ReporteMarcaTipoDTO>();

			try {
				statement = conexion.getSQLConexion().prepareStatement(porcentajes20);
	
				resultSet = statement.executeQuery();

				while (resultSet.next()) {
					ReporteMarcaTipoDTO item = new ReporteMarcaTipoDTO();
					item.setDetalle(resultSet.getString("descripcion"));
					item.setCantidad(resultSet.getInt("cantidad"));
					item.setPorcentaje(resultSet.getFloat("porcentaje"));
					items.add(item);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Conexion.cerrarConexion();
			}
			return items;
		}else {
			Conexion.cerrarConexion();
		}
		
		return null;
	}
	
	public ArrayList<ReporteMarcaTipoDTO> porMarcaTipo20(Date desde, Date hasta) {
		
		boolean consultar = false;
		PreparedStatement statementTemp;
		
		try {
			statementTemp = conexion.getSQLConexion().prepareStatement(marcaTipoTmp);
			statementTemp.setDate(1, new java.sql.Date(desde.getTime()));
			statementTemp.setDate(2, new java.sql.Date(hasta.getTime()));

			if (statementTemp.executeUpdate() > 0) // Si se ejecuto la tabla temporal asigno true
				consultar = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(consultar) {
			
			conexion = Conexion.getConexion();
			PreparedStatement statement;
			ResultSet resultSet;
			ArrayList<ReporteMarcaTipoDTO> items = new ArrayList<ReporteMarcaTipoDTO>();

			try {
				statement = conexion.getSQLConexion().prepareStatement(porcentajes20);
	
				resultSet = statement.executeQuery();

				while (resultSet.next()) {
					ReporteMarcaTipoDTO item = new ReporteMarcaTipoDTO();
					item.setDetalle(resultSet.getString("descripcion"));
					item.setCantidad(resultSet.getInt("cantidad"));
					item.setPorcentaje(resultSet.getFloat("porcentaje"));
					items.add(item);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Conexion.cerrarConexion();
			}
			return items;
		}else {
			Conexion.cerrarConexion();
		}
		
		return null;
	}
	
}
