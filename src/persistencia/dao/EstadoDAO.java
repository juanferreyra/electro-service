package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.EstadoDTO;
import dto.PresupuestoDTO;
import persistencia.conexion.Conexion;

public class EstadoDAO {
	private static final String find = "select * from estado where id=?";
	private Conexion conexion = Conexion.getConexion();

	public EstadoDTO find(int idestado) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		EstadoDTO estado = null;

		try {
			statement = conexion.getSQLConexion().prepareStatement(find);
			statement.setInt(1, idestado);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				estado = new EstadoDTO(resultSet.getInt("id"), resultSet.getString("detalle"),
						resultSet.getString("destalle_largo"));
			}
		} catch (

		SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return estado;
	}

}
