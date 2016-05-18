package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.PresupuestoDTO;
import persistencia.conexion.Conexion;

public class PresupuestoDAO {

	static final String insert = "INSERT INTO presupuesto ( idingreso, descripcion_breve,"
			+ "descripcion_tecnica, horas_dedicadas, importe_mano_obra, importe_total, fecha_creacion,"
			+ "fecha_vencimiento, idusuario, habilitado) VALUES (?,?,?,?,?,?,now(),?,?,true)";

	private static final String delete = "UPDATE presupuesto SET habilitado='0' WHERE id= ?;";

	private static final String readall = "SELECT * FROM presupuesto WHERE habilitado=true";

	private static final String find = "SELECT * FROM presupuesto WHERE idingreso  = ? and habilitado = true";
	
	private static final String nextId = "SELECT Auto_Increment as siguiente FROM INFORMATION_SCHEMA.TABLES WHERE Table_name = 'presupuesto';";

	private Conexion conexion = Conexion.getConexion();

	public ArrayList<PresupuestoDTO> readAll() {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<PresupuestoDTO> presupuestos = new ArrayList<PresupuestoDTO>();

		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			
			while (resultSet.next()) { presupuestos.add(new
					PresupuestoDTO(resultSet.getInt("id"),
							resultSet.getInt("idingreso"),
							resultSet.getString("descripcion_breve"),
							resultSet.getString("descripcion_tecnica"),
							resultSet.getFloat("importe_mano_obra"),
							resultSet.getInt("horas_dedicadas"),
							resultSet.getFloat("importe_total"),
							resultSet.getDate("fecha_creacion"),
							resultSet.getDate("fecha_vencimiento"),
							resultSet.getInt("idusuario"),
							resultSet.getBoolean("habilitado"))); }

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return presupuestos;
	}

	public boolean delete(PresupuestoDTO presupuesto_a_eliminar) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1,
			Integer.toString(presupuesto_a_eliminar.getId()));
			
			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return false;
	}

	public boolean insert(PresupuestoDTO presupuesto) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);

			statement.setInt(1, presupuesto.getIdIngreso());
			statement.setString(2, presupuesto.getDescripcionBreve());
			statement.setString(3, presupuesto.getDescripcionTecnica());
			statement.setInt(4, presupuesto.getHorasTrabajo());
			statement.setFloat(5, presupuesto.getImporteManoObra());
			statement.setFloat(6, presupuesto.getImporteTotal());
			statement.setDate(7, new java.sql.Date(presupuesto.getFechavencimiento().getTime()));
			statement.setInt(8, presupuesto.getIdUsuario());

			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return false;
	}

	public PresupuestoDTO find(int idingreso) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		PresupuestoDTO presupuesto = new PresupuestoDTO(-1, idingreso, null, null, 0, 0, 0, null, null, 0, true);

		try {
			statement = conexion.getSQLConexion().prepareStatement(find);
			statement.setInt(1, idingreso);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				presupuesto = new PresupuestoDTO(
						resultSet.getInt("id"),
						resultSet.getInt("idingreso"),
						resultSet.getString("descripcion_breve"),
						resultSet.getString("descripcion_tecnica"),
						resultSet.getFloat("importe_mano_obra"),
						resultSet.getInt("horas_dedicadas"),
						resultSet.getFloat("importe_total"),
						resultSet.getDate("fecha_creacion"),
						resultSet.getDate("fecha_vencimiento"),
						resultSet.getInt("idusuario"),
						resultSet.getBoolean("habilitado"));
			}
		} catch (
				SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return presupuesto;
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
