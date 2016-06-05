package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dto.PerfilDTO;
import persistencia.conexion.Conexion;

public class PerfilDAO {
	
	private static final String find = "SELECT id, detalle FROM perfil WHERE id = ?;";
	
	private static final String findPorDetalle = "SELECT id, detalle FROM perfil WHERE detalle = ?;";
	
	private static Conexion conexion = Conexion.getConexion();
	
	public PerfilDTO find(int idperfil) {

		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		PerfilDTO perfil = null;

		try {
			statement = conexion.getSQLConexion().prepareStatement(find);
			statement.setInt(1, idperfil);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				perfil = new PerfilDTO(resultSet.getInt("id"), resultSet.getString("detalle"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return perfil;
	}

	public PerfilDTO obtenerPerfil(String detalle) {
		
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		PerfilDTO perfil = null;

		try {
			statement = conexion.getSQLConexion().prepareStatement(findPorDetalle);
			statement.setString(1, detalle);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				perfil = new PerfilDTO(resultSet.getInt("id"), resultSet.getString("detalle"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return perfil;
	
	}
	
}
