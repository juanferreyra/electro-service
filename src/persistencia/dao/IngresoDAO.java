package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.IngresoDTO;
import persistencia.conexion.Conexion;

public class IngresoDAO {

	private static final String insert = "INSERT INTO ingreso(`idcliente`, `descripcion_producto`, `idmarca`, `idtipo_producto`,"
			+ " `descripcion_falla`, `envio`, `envio_default`, `estado`, `fecha_creacion`, `idusuario`, `habilitado`)"
			+ " VALUES ( ? , ?, ?, ?, ?, ?, ?, ?, now(), ?, true)";
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
						resultSet.getString("descripcion_falla"),
						resultSet.getBoolean("envio"),
						resultSet.getBoolean("envio_default"),
						resultSet.getDate("fecha_creacion"),
						resultSet.getInt("estado"),
						resultSet.getInt("idusuario")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
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
			Conexion.cerrarConexion();
		}
		return false;
	}

	public Boolean insert(IngresoDTO ingreso){
		conexion = Conexion.getConexion();
		try {
			PreparedStatement statement = conexion.getSQLConexion().prepareStatement(insert);
			
			statement.setInt(1, ingreso.getIdcliente());
			statement.setString(2, ingreso.getDescripcion());
			statement.setInt(3, ingreso.getIdmarca());
			statement.setInt(4, ingreso.getIdtipo_producto());
			statement.setString(5, ingreso.getDescripcion_falla());
			statement.setBoolean(6, ingreso.getEnvio());
			statement.setBoolean(7, ingreso.getEnvio_default());
			statement.setInt(8,ingreso.getEstado());
			statement.setInt(9, ingreso.getIdusuario());
		
			if (statement.executeUpdate() > 0)
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
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
						resultSet.getString("descripcion_falla"),
						resultSet.getBoolean("envio"),
						resultSet.getBoolean("envio_default"),
						resultSet.getDate("fecha_creacion"),
						resultSet.getInt("estado"),
						resultSet.getInt("idusuario"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return ingreso;
	}
}
