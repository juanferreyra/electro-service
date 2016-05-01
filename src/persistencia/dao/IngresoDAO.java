package persistencia.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.IngresoDTO;
import persistencia.conexion.Conexion;

public class IngresoDAO {

	private static final String insert = "INSERT INTO ingreso(id, idcliente,descripcion_producto,idmarca,idtipo_producto,fecha_creacion,estado,idusuario) VALUES(?,?,?,?,?,?,?,?)";
	private static final String delete = "DELETE FROM ingreso WHERE id = ?";
	private static final String readall = "SELECT * FROM ingreso";
	private Conexion conexion = Conexion.getConexion();

	public ArrayList<IngresoDTO> readAll() {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<IngresoDTO> localidades = new ArrayList<IngresoDTO>();

		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				localidades.add(new IngresoDTO(Integer.parseInt(resultSet.getString("id")),
						Integer.parseInt(resultSet.getString("idcliente")), resultSet.getString("descripcion_producto"),
						Integer.parseInt(resultSet.getString("idmarca")),
						Integer.parseInt(resultSet.getString("idtipo_producto")), resultSet.getDate("fecha_creacion"),
						Integer.parseInt(resultSet.getString("estado")),
						Integer.parseInt(resultSet.getString("idusuario"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return localidades;
	}

	public boolean delete(IngresoDTO ingreso_a_eliminar) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(ingreso_a_eliminar.getId()));
			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return false;
	}

	public boolean insert(IngresoDTO ingreso) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, Integer.toString(ingreso.getId()));
			statement.setString(2, Integer.toString(ingreso.getIdcliente()));
			statement.setString(3, ingreso.getDescripcion());
			statement.setString(4, Integer.toString(ingreso.getIdmarca()));
			statement.setString(5, Integer.toString(ingreso.getIdtipo_producto()));
			statement.setDate(6, (Date) ingreso.getFecha_creacion());
			statement.setInt(7, ingreso.getEstado());
			statement.setString(8, Integer.toString(ingreso.getIdusuario()));

			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return false;
	}
}
