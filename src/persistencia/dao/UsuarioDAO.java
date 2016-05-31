package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ClienteDTO;
import dto.PerfilDTO;
import dto.TipoProductoDTO;
import dto.UsuarioDTO;
import persistencia.conexion.Conexion;

public class UsuarioDAO {

	private static final String find = "select * from usuario where id=?";
	
	private static final String buscarPerfil ="SELECT * FROM 20161_service_g2.perfil where id = ?;";
	
	private static final String ALLPerfil ="SELECT * FROM 20161_service_g2.perfil ;";
	
	private static final String insert = "INSERT INTO usuario (nombre, apellido, password, idperfil, habilitado, fecha_creacion)"
			+ " VALUES (?, ?, ?, ?, true, now());";
	
	private static final String delete = "UPDATE usuario SET habilitado='0' WHERE id = ?";
	
	private static final String readall = "SELECT id, nombre, apellido, password, idperfil  FROM usuario WHERE habilitado = true;";
	
	private static final String update = "UPDATE usuario SET nombre = ?, apellido = ?, password = ?, idperfil = ?"
			+ " WHERE id = ?;";
	
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

	public boolean insert(UsuarioDTO nuevoCliente) {
		
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1,nuevoCliente.getNombre());
			statement.setString(2, nuevoCliente.getApellido());
			statement.setString(3,nuevoCliente.getPassword());
			statement.setInt(4,nuevoCliente.getIdperfil());
	
			
			if(statement.executeUpdate() > 0) //Si se ejecut� devuelvo true
				return true;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			Conexion.cerrarConexion();
		}
		return false;
		
		
	}

	public boolean delete(int id_usuario_a_eliminar) {
		
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(id_usuario_a_eliminar));
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0) //Si se ejecut� devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			Conexion.cerrarConexion();
		}
		return false;
	}
		

	public List<UsuarioDTO> readAll() {
		
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				usuarios.add(new UsuarioDTO(resultSet.getInt("id"),
						resultSet.getString("nombre"),
						resultSet.getString("apellido"),
						resultSet.getString("password"),
						resultSet.getInt("idperfil")));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			Conexion.cerrarConexion();
		}
		
		return usuarios;
	}

	public boolean update(UsuarioDTO usuario_a_modificar) {
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);

			statement.setString(1, usuario_a_modificar.getNombre());
			statement.setString(2, usuario_a_modificar.getApellido());
			statement.setString(3, usuario_a_modificar.getPassword());
			statement.setInt(4, usuario_a_modificar.getIdperfil());
			statement.setInt(5, usuario_a_modificar.getId());

			if(statement.executeUpdate() > 0) //Si se ejecut� devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre 
		{
			Conexion.cerrarConexion();
		}
		return false;
	}

	public PerfilDTO buscarPerfil(int id) {
		
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		PerfilDTO perfil = null;

		try {
			statement = conexion.getSQLConexion().prepareStatement(buscarPerfil);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				perfil = new PerfilDTO(
						resultSet.getInt("id"),
						resultSet.getString("detalle"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return perfil;
		
	}
	
public List<PerfilDTO> todosLosPerfiles() {
		
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PerfilDTO> perfiles = new ArrayList<PerfilDTO>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(ALLPerfil);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				perfiles.add(new PerfilDTO(resultSet.getInt("id"),
						resultSet.getString("detalle")));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			Conexion.cerrarConexion();
		}
		
		return perfiles;
	}

}
