package persistencia.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.IngresoDTO;
import persistencia.conexion.Conexion;

public class IngresoDAO {

	private static final String insert = "INSERT INTO ingreso(idcliente,descripcion_producto,idmarca,"
			+ "idtipo_producto,fecha_creacion,estado,idusuario) VALUES (?,?,?,?,?,?,?)";
	private static final String delete = "UPDATE ingreso SET habilitado='0' WHERE id= ?;";
	private static final String readall = "SELECT * FROM ingreso WHERE habilitado=true";
	private static final String find = "SELECT * FROM ingreso WHERE habilitado=true AND id = ?";
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
				localidades.add(new IngresoDTO(resultSet.getInt("id"),
						resultSet.getInt("idcliente"),
						resultSet.getString("descripcion_producto"),
						resultSet.getInt("idmarca"),
						resultSet.getInt("idtipo_producto"),
						resultSet.getDate("fecha_creacion"),
						resultSet.getInt("estado"),
						resultSet.getInt("idusuario")));
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
			
			statement.setInt(1, ingreso.getIdcliente());
			statement.setString(2, ingreso.getDescripcion());
			statement.setInt(3, ingreso.getIdmarca());
			statement.setInt(4, ingreso.getIdtipo_producto());
			statement.setDate(5, (Date) ingreso.getFecha_creacion());
			statement.setInt(6,ingreso.getEstado());
			statement.setInt(7, ingreso.getIdusuario());

			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return false;
	}

	public IngresoDTO find(int id) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		IngresoDTO ingreso = null;

		try {
			statement = conexion.getSQLConexion().prepareStatement(find);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				ingreso = new IngresoDTO(resultSet.getInt("id"),
						resultSet.getInt("idcliente"),
						resultSet.getString("descripcion_producto"),
						resultSet.getInt("idmarca"),
						resultSet.getInt("idtipo_producto"),
						resultSet.getDate("fecha_creacion"),
						resultSet.getInt("estado"),
						resultSet.getInt("idusuario"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return ingreso;
	}
}
