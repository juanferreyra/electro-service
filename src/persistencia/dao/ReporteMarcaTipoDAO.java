package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import dto.ReporteMarcaTipoDTO;
import persistencia.conexion.Conexion;

public class ReporteMarcaTipoDAO {

	private static final String marca ="SELECT datos.fecha_creacion, m.detalle AS detalle, count(datos.id) AS cantidad, (SELECT count(datos.idmarca) * 100 / count(datos2.id)"
			+ " FROM (SELECT DATE(i.fecha_creacion) AS fecha_creacion, i.id AS id, i.idtipo_producto, i.idmarca FROM ingreso i LEFT JOIN (SELECT il.idingreso,"
			+ " (SELECT fl.idestado FROM ingreso_log fl WHERE fl.idingreso = il.idingreso ORDER BY fl.id DESC LIMIT 1) AS estado FROM ingreso_log il GROUP BY il.idingreso) "
			+ " log ON (i.id = log.idingreso) WHERE i.habilitado = TRUE AND log.estado = 11) AS datos2 ) AS porcentaje FROM (SELECT DATE(i.fecha_creacion) as fecha_creacion,"
			+ " i.id AS id, i.idtipo_producto, i.idmarca FROM ingreso i LEFT JOIN (SELECT il.idingreso, (SELECT fl.idestado FROM ingreso_log fl WHERE "
			+ " fl.idingreso = il.idingreso ORDER BY fl.id DESC LIMIT 1) AS estado FROM ingreso_log il GROUP BY il.idingreso) log ON (i.id = log.idingreso)"
			+ " WHERE i.habilitado = TRUE AND log.estado = 11) datos LEFT JOIN marca_producto m ON(datos.idmarca = m.id) WHERE datos.fecha_creacion "
			+ " BETWEEN ? AND ? GROUP BY datos.idmarca";
	
	private static final String tipo ="SELECT datos.fecha_creacion, t.detalle AS detalle, count(datos.id) AS cantidad, (SELECT count(datos.idtipo_producto) * 100 / count(datos2.id)"
			+ " FROM (SELECT DATE(i.fecha_creacion) AS fecha_creacion, i.id AS id, i.idtipo_producto, i.idmarca FROM ingreso i LEFT JOIN (SELECT il.idingreso,"
			+ " (SELECT fl.idestado FROM ingreso_log fl WHERE fl.idingreso = il.idingreso ORDER BY fl.id DESC LIMIT 1) AS estado FROM ingreso_log il GROUP BY il.idingreso) "
			+ " log ON (i.id = log.idingreso) WHERE i.habilitado = TRUE AND log.estado = 11) AS datos2 ) AS porcentaje FROM (SELECT DATE(i.fecha_creacion) as fecha_creacion,"
			+ " i.id AS id, i.idtipo_producto, i.idmarca FROM ingreso i LEFT JOIN (SELECT il.idingreso, (SELECT fl.idestado FROM ingreso_log fl WHERE "
			+ " fl.idingreso = il.idingreso ORDER BY fl.id DESC LIMIT 1) AS estado FROM ingreso_log il GROUP BY il.idingreso) log ON (i.id = log.idingreso)"
			+ " WHERE i.habilitado = TRUE AND log.estado = 11) datos LEFT JOIN tipo_producto t ON(datos.idtipo_producto = t.id) WHERE datos.fecha_creacion "
			+ " BETWEEN ? AND ? GROUP BY datos.idtipo_producto";
	
	private static final String marcaTipo = "SELECT datos.fecha_creacion , CONCAT(m.detalle,'/', t.detalle) AS detalle, count(datos.id) as cantidad, "
			+ "(SELECT count(datos.marcaTipo) * 100 / count(datos2.id) FROM (SELECT DATE(i.fecha_creacion) as fecha_creacion, i.id AS id, "
			+ " i.idmarca, i.idtipo_producto FROM ingreso i LEFT JOIN (SELECT il.idingreso, (SELECT fl.idestado FROM ingreso_log fl WHERE "
			+ " fl.idingreso = il.idingreso ORDER BY fl.id DESC LIMIT 1) AS estado FROM ingreso_log il GROUP BY il.idingreso) log ON "
			+ " (i.id = log.idingreso) WHERE i.habilitado = TRUE AND log.estado = 11) AS datos2 ) AS porcentaje FROM (SELECT "
			+ " DATE(i.fecha_creacion) as fecha_creacion, i.id AS id, i.idtipo_producto, i.idmarca, concat(i.idmarca,i.idtipo_producto) "
			+ " as marcaTipo FROM ingreso i LEFT JOIN (SELECT il.idingreso, (SELECT fl.idestado FROM ingreso_log fl WHERE fl.idingreso = il.idingreso"
			+ " ORDER BY fl.id DESC LIMIT 1) AS estado FROM ingreso_log il GROUP BY il.idingreso) log ON (i.id = log.idingreso) WHERE i.habilitado = TRUE "
			+ " AND log.estado = 11) datos LEFT JOIN marca_producto m ON(datos.idmarca = m.id) LEFT JOIN tipo_producto t ON(datos.idtipo_producto = t.id)  WHERE datos.fecha_creacion "
			+ " BETWEEN ? AND ?  GROUP BY datos.marcaTipo";
	
	private Conexion conexion = Conexion.getConexion();
	
	public ArrayList<ReporteMarcaTipoDTO> porMarca(Date desde, Date hasta) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<ReporteMarcaTipoDTO> items = new ArrayList<ReporteMarcaTipoDTO>();

		try {
			statement = conexion.getSQLConexion().prepareStatement(marca);
			statement.setDate(1, new java.sql.Date(desde.getTime()));
			statement.setDate(2, new java.sql.Date(hasta.getTime()));
			
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				ReporteMarcaTipoDTO item = new ReporteMarcaTipoDTO();
				item.setDetalle(resultSet.getString("detalle"));
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
	}
	
	public ArrayList<ReporteMarcaTipoDTO> porTipo(Date desde, Date hasta) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<ReporteMarcaTipoDTO> items = new ArrayList<ReporteMarcaTipoDTO>();

		try {
			statement = conexion.getSQLConexion().prepareStatement(tipo);
			statement.setDate(1, new java.sql.Date(desde.getTime()));
			statement.setDate(2, new java.sql.Date(hasta.getTime()));
			
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				ReporteMarcaTipoDTO item = new ReporteMarcaTipoDTO();
				item.setDetalle(resultSet.getString("detalle"));
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
	}
	
	public ArrayList<ReporteMarcaTipoDTO> porMarcaTipo(Date desde, Date hasta) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<ReporteMarcaTipoDTO> items = new ArrayList<ReporteMarcaTipoDTO>();

		try {
			statement = conexion.getSQLConexion().prepareStatement(marcaTipo);
			statement.setDate(1, new java.sql.Date(desde.getTime()));
			statement.setDate(2, new java.sql.Date(hasta.getTime()));
			
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				ReporteMarcaTipoDTO item = new ReporteMarcaTipoDTO();
				item.setDetalle(resultSet.getString("detalle"));
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
	}
	
}
