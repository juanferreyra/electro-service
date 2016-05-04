package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.UsuarioDTO;
import persistencia.conexion.Conexion;

public class UsuarioDAO {

	private static final String find = "select * from usuario where id=?";
	private Conexion conexion = Conexion.getConexion();

	public UsuarioDTO find(int idusuario) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		UsuarioDTO usuario = null;

		try {
			statement = conexion.getSQLConexion().prepareStatement(find);
			statement.setInt(1, idusuario);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				usuario = new UsuarioDTO(resultSet.getInt("id"), resultSet.getString("nombre"),
						resultSet.getString("apellido"), resultSet.getString("password"), resultSet.getInt("idperfil"));
			}
		} catch (

		SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return usuario;
	}

}
