package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dto.ReporteFinancieroActivosDTO;
import dto.ReporteFinancieroPasivosDTO;
import persistencia.conexion.Conexion;

public class ReporteFinancieroDAO {

	private static final String Activos ="SELECT DATE(i.fecha_creacion) AS fecha_creacion, i.id AS nro_ingreso, mp.detalle AS marca, tp.detalle AS tipo,"
			+" pr.monto_repuestos, pr.monto_mano_obra, IF(i.envio,i.monto_envio,'Sin envio') as monto_envio, monto_envio+pr.monto_repuestos+pr.monto_mano_obra as monto_total, "
			+" DATE(log.fecha) as fecha_retiro FROM ingreso i LEFT JOIN (SELECT il.idingreso, (SELECT fl.idestado FROM ingreso_log fl WHERE fl.idingreso = il.idingreso ORDER BY fl.id DESC "
			+" LIMIT 1) AS estado, (SELECT f2.fecha_creacion FROM ingreso_log f2 WHERE f2.idingreso = il.idingreso AND f2.idestado=11 ORDER BY f2.id DESC "
			+" LIMIT 1) AS fecha FROM ingreso_log il GROUP BY il.idingreso) log ON (i.id = log.idingreso) LEFT JOIN marca_producto mp ON(mp.id=i.idmarca) LEFT JOIN "
			+" tipo_producto tp ON(tp.id=i.idtipo_producto) LEFT JOIN (SELECT p.id as id, p.idingreso as idingreso, SUM(pr.cantidad * r.precio) as monto_repuestos, "
			+" p.importe_mano_obra as monto_mano_obra FROM presupuesto p LEFT JOIN presupuesto_repuestos pr ON(p.id=pr.idpresupuesto) LEFT JOIN repuesto r ON(pr.idrepuesto=r.id) "
			+" GROUP BY p.id) pr ON(pr.idingreso=i.id) WHERE i.habilitado = TRUE AND log.estado = 11  AND DATE(i.fecha_creacion) "
			+ " between ? AND ? GROUP BY i.id ;";
	
	private static final String Pasivos ="SELECT DATE(oc.fecha_creacion) AS orden_fecha, oc.id AS nro_orden, p.razon_social AS proveedor,"
										+ " oc.importe_total AS orden_importe, oc.fecha_modificacion AS fecha_recepcion FROM orden_compra oc LEFT JOIN "
	                                    + " proveedor p ON (p.id = oc.idproveedor) WHERE oc.habilitado = TRUE AND oc.estado = 'RECIBIDA' AND DATE(oc.fecha_creacion) "
	                                    + " between ? AND ? ;";
	
	private Conexion conexion = Conexion.getConexion();

	public ArrayList<ReporteFinancieroActivosDTO> findActivos(Date desde, Date hasta) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<ReporteFinancieroActivosDTO> reparaciones = new ArrayList<ReporteFinancieroActivosDTO>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			statement = conexion.getSQLConexion().prepareStatement(Activos);
			statement.setDate(1, new java.sql.Date(desde.getTime()));
			statement.setDate(2, new java.sql.Date(hasta.getTime()));
			
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				ReporteFinancieroActivosDTO reparacion = new ReporteFinancieroActivosDTO();
				reparacion.setFecha_ingreso(sdf.format(resultSet.getDate("fecha_creacion")));
				reparacion.setNro_ingreso(resultSet.getInt("nro_ingreso"));
				reparacion.setMarca(resultSet.getString("marca"));
				reparacion.setTipo(resultSet.getString("tipo"));
				reparacion.setValor_componentes(resultSet.getFloat("monto_repuestos"));
				reparacion.setValor_mano_obra(resultSet.getFloat("monto_mano_obra"));
				reparacion.setValor_flete(resultSet.getString("monto_envio"));
				reparacion.setValor_total(resultSet.getFloat("monto_total"));
				reparacion.setFecha_retiro(sdf.format(resultSet.getDate("fecha_retiro")));
				reparaciones.add(reparacion);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return reparaciones;
	}
	
	public ArrayList<ReporteFinancieroPasivosDTO> findPasivos(Date desde, Date hasta) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<ReporteFinancieroPasivosDTO> compras = new ArrayList<ReporteFinancieroPasivosDTO>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			statement = conexion.getSQLConexion().prepareStatement(Pasivos);
			statement.setDate(1, new java.sql.Date(desde.getTime()));
			statement.setDate(2, new java.sql.Date(hasta.getTime()));
			
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				ReporteFinancieroPasivosDTO compra = new ReporteFinancieroPasivosDTO();
				compra.setFecha_orden(sdf.format(resultSet.getDate("orden_fecha")));
				compra.setNro_orden(resultSet.getInt("nro_orden"));
				compra.setProveedor(resultSet.getString("proveedor"));
				compra.setValor_orden(resultSet.getFloat("orden_importe"));
				compra.setFecha_recepcion(sdf.format(resultSet.getDate("fecha_recepcion")));
				compras.add(compra);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return compras;
	}
	
}
