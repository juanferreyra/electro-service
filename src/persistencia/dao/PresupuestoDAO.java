package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.PresupuestoDTO;
import persistencia.conexion.Conexion;

public class PresupuestoDAO {

	static final String insert = "INSERT INTO presupuesto (idingreso,descripcion_breve,"
			+ "descripcion_tecnica,importe_mano_obra,fecha_creacion,fecha_vencimiento,idusuario,habilitado) VALUES (?,?,?,?,now(),?,?,true)";

	private static final String delete = "UPDATE ingreso SET habilitado='0' WHERE id= ?;";

	private static final String readall = "SELECT * FROM ingreso WHERE habilitado=true";

	private static final String buscarPresupuesto = "SELECT id FROM presupuesto WHERE idingreso  = ?";

	private Conexion conexion = Conexion.getConexion();

	public ArrayList<PresupuestoDTO> readAll() {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<PresupuestoDTO> localidades = new ArrayList<PresupuestoDTO>();

		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			/*
			 * while (resultSet.next()) { localidades.add(new
			 * PresupuestoDTO(resultSet.getInt("id"),
			 * resultSet.getInt("idingreso"),
			 * resultSet.getString("descripcion_producto"),
			 * resultSet.getInt("idmarca"), resultSet.getInt("idtipo_producto"),
			 * resultSet.getString("descripcion_falla"),
			 * resultSet.getBoolean("envio"),
			 * resultSet.getBoolean("envio_default"),
			 * resultSet.getDate("fecha_creacion"), resultSet.getInt("estado"),
			 * resultSet.getInt("idusuario"))); }
			 */
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return localidades;
	}

	public boolean delete(PresupuestoDTO ingreso_a_eliminar) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			// statement.setString(1,
			// Integer.toString(ingreso_a_eliminar.getId()));
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

			// statement.setInt(1, presupuesto.getId());
			statement.setInt(1, presupuesto.getIdIngreso());
			statement.setString(2, presupuesto.getDescripcionBreve());
			statement.setString(3, presupuesto.getDescripcionTecnica());
			statement.setString(4, presupuesto.getImporteManoObra());
			statement.setDate(5, new java.sql.Date(presupuesto.getFechavencimiento().getTime()));
			statement.setInt(6, presupuesto.getIdUsuario());

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
		PresupuestoDTO presupuesto = null;

		try {
			statement = conexion.getSQLConexion().prepareStatement(buscarPresupuesto);
			statement.setInt(1, idingreso);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				presupuesto = new PresupuestoDTO(resultSet.getInt("idingreso"), resultSet.getString("descripcionBreve"),
						resultSet.getString("descripcionTecnica"), resultSet.getString("importeManoObra"),
						resultSet.getDate("fechaVencimiento"), resultSet.getInt("idUsusario"));
			}
		} catch (

		SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return presupuesto;
	}

}
