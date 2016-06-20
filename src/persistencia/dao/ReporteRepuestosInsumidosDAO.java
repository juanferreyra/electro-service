package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import dto.ReporteRepuestosInsumidosDTO;
import persistencia.conexion.Conexion;

public class ReporteRepuestosInsumidosDAO {
	
	private static final String respTemp = "CREATE TEMPORARY TABLE porcentajes AS (SELECT DATE(p.fecha_creacion) AS fecha_creacion, "
			+ "  m.detalle as marca, r.detalle as repuesto, sum(pr.cantidad) as cantidad, (SELECT sum(pr.cantidad) * 100 / sum(datos2.cantidad)  FROM (SELECT"
			+ " DATE(p2.fecha_creacion) AS fecha_creacion, sum(pr2.cantidad) as cantidad FROM (SELECT  il.idingreso, (SELECT  fl.idestado FROM ingreso_log fl  WHERE"
			+ " fl.idingreso = il.idingreso ORDER BY fl.id DESC LIMIT 1) AS estado FROM ingreso_log il LEFT JOIN ingreso i ON(il.idingreso=i.id) WHERE i.habilitado=true"
			+ " GROUP BY il.idingreso) log LEFT JOIN presupuesto p2 ON(log.idingreso=p2.idingreso) LEFT JOIN presupuesto_repuestos pr2 ON(pr2.idpresupuesto = p2.id)"
			+ " WHERE log.estado = 11 AND DATE(p2.fecha_creacion) BETWEEN ? AND ? GROUP BY pr2.idrepuesto) AS datos2) as porcentaje FROM (SELECT il.idingreso,"
			+ " (SELECT fl.idestado FROM ingreso_log fl WHERE fl.idingreso = il.idingreso ORDER BY fl.id DESC LIMIT 1) AS estado FROM ingreso_log il LEFT JOIN"
			+ " ingreso i ON(il.idingreso=i.id) WHERE i.habilitado=true GROUP BY il.idingreso) log LEFT JOIN presupuesto p ON(log.idingreso=p.idingreso) LEFT JOIN"
			+ " presupuesto_repuestos pr ON(pr.idpresupuesto = p.id) LEFT JOIN repuesto r ON(pr.idrepuesto = r.id) LEFT JOIN marca_producto m ON(r.idmarca = m.id) WHERE"
			+ " log.estado = 11 AND DATE(p.fecha_creacion) BETWEEN ? AND ? GROUP BY pr.idrepuesto ORDER BY cantidad DESC);";
	
	private static final String insumos10 = "SELECT marca, IF(porcentaje <= 10, 'OTROS', repuesto) AS descripcion,"
			+ "  SUM(cantidad) AS cantidad, SUM(porcentaje) AS porcentaje FROM porcentajes GROUP BY  descripcion"
			+ " order by porcentaje DESC, marca DESC;";
	
	private static final String insumos20 = "SELECT marca, IF(porcentaje <= 20, 'OTROS', repuesto) AS descripcion,"
			+ "  SUM(cantidad) AS cantidad, SUM(porcentaje) AS porcentaje FROM porcentajes GROUP BY  descripcion"
			+ " order by porcentaje DESC, marca DESC;";
	
private Conexion conexion = Conexion.getConexion();
	
	public ArrayList<ReporteRepuestosInsumidosDTO> insumosAl10(Date desde, Date hasta) {
		
		boolean consultar = false;
		PreparedStatement statementTemp;
		
		try {
			statementTemp = conexion.getSQLConexion().prepareStatement(respTemp);
			statementTemp.setDate(1, new java.sql.Date(desde.getTime()));
			statementTemp.setDate(2, new java.sql.Date(hasta.getTime()));
			statementTemp.setDate(3, new java.sql.Date(desde.getTime()));
			statementTemp.setDate(4, new java.sql.Date(hasta.getTime()));

			if (statementTemp.executeUpdate() > 0) // Si se ejecuto la tabla temporal asigno true
				consultar = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(consultar) {
			
			conexion = Conexion.getConexion();
			PreparedStatement statement;
			ResultSet resultSet;
			ArrayList<ReporteRepuestosInsumidosDTO> items = new ArrayList<ReporteRepuestosInsumidosDTO>();

			try {
				statement = conexion.getSQLConexion().prepareStatement(insumos10);
	
				resultSet = statement.executeQuery();

				while (resultSet.next()) {
					ReporteRepuestosInsumidosDTO item = new ReporteRepuestosInsumidosDTO();
					item.setMarca(resultSet.getString("marca"));
					item.setRepuesto(resultSet.getString("descripcion"));
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
	
	
public ArrayList<ReporteRepuestosInsumidosDTO> insumosAl20(Date desde, Date hasta) {
		
		boolean consultar = false;
		PreparedStatement statementTemp;
		
		try {
			statementTemp = conexion.getSQLConexion().prepareStatement(respTemp);
			statementTemp.setDate(1, new java.sql.Date(desde.getTime()));
			statementTemp.setDate(2, new java.sql.Date(hasta.getTime()));
			statementTemp.setDate(3, new java.sql.Date(desde.getTime()));
			statementTemp.setDate(4, new java.sql.Date(hasta.getTime()));

			if (statementTemp.executeUpdate() > 0) // Si se ejecuto la tabla temporal asigno true
				consultar = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(consultar) {
			
			conexion = Conexion.getConexion();
			PreparedStatement statement;
			ResultSet resultSet;
			ArrayList<ReporteRepuestosInsumidosDTO> items = new ArrayList<ReporteRepuestosInsumidosDTO>();

			try {
				statement = conexion.getSQLConexion().prepareStatement(insumos20);
	
				resultSet = statement.executeQuery();

				while (resultSet.next()) {
					ReporteRepuestosInsumidosDTO item = new ReporteRepuestosInsumidosDTO();
					item.setMarca(resultSet.getString("marca"));
					item.setRepuesto(resultSet.getString("descripcion"));
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
