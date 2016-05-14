package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dto.ItemPresupuestoRepuestoDTO;
import dto.PresupuestoRepuestoDTO;
import persistencia.conexion.Conexion;

public class PresupuestoRepuestoDAO {
	
	private static final String insert = "INSERT INTO presupuesto_repuestos (id,idpresupuesto,idrepuesto,"
			+ "cantidad,fecha_creacion,habilitado) VALUES (?,?,?,?,now(),true)";
	
	private static final String find = "SELECT pr.id, pr.idrepuesto, r.detalle, pr.cantidad, r.precio, pr.cantidad * r.precio as total FROM presupuesto_repuestos pr LEFT JOIN repuesto r ON(pr.idrepuesto = r.id) WHERE pr.idpresupuesto = ? AND pr.habilitado=true;";
	
	private Conexion conexion = Conexion.getConexion();

	public boolean insert(PresupuestoRepuestoDTO presupuestoRepuesto) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			
			statement.setInt(1, presupuestoRepuesto.getId());
			statement.setInt(2, presupuestoRepuesto.getIdPresupuesto());
			statement.setInt(3, presupuestoRepuesto.getIdComponente());
			statement.setInt(4, presupuestoRepuesto.getCantidad());

			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return false;
	}

	public ArrayList<ItemPresupuestoRepuestoDTO> readAll(int id) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<ItemPresupuestoRepuestoDTO> presupuestoRepuesto = new ArrayList<ItemPresupuestoRepuestoDTO>();

		try {
			statement = conexion.getSQLConexion().prepareStatement(find);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				presupuestoRepuesto.add(new ItemPresupuestoRepuestoDTO(
						resultSet.getInt("id"),
						resultSet.getInt("idrepuesto"),
						resultSet.getString("detalle"),
						resultSet.getInt("cantidad"),
						resultSet.getFloat("precio"),
						resultSet.getFloat("total")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return presupuestoRepuesto;
	}

}
