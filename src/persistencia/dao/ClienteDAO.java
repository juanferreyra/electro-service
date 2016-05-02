package persistencia.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.ClienteDTO;
import persistencia.conexion.Conexion;

public class ClienteDAO {

	private static final String insert = "INSERT INTO cliente("
			+ "`nrodoc`, `nombre`, `apellido`, `localidad`, `direccion`, `telefono`, `mail`, `fecha_creacion`, `idusuario`) "
			+ "VALUES (?, ?, ?, ?, ?', ?, ?, ?, now(), ?);";
	private static final String delete = "UPDATE cliente SET habilitado=false WHERE id= ?;";
	private static final String readall = "SELECT * FROM cliente WHERE habilitado=true";
	private static final String find = "SELECT * FROM cliente WHERE habilitado=true AND id=?";
	private static final String find2 = "SELECT * FROM cliente WHERE habilitado=true AND nrodoc=?";
	private Conexion conexion = Conexion.getConexion();

	public ArrayList<ClienteDTO> readAll() {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<ClienteDTO> clientes = new ArrayList<ClienteDTO>();

		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				clientes.add(new ClienteDTO(resultSet.getInt("id"),
						resultSet.getInt("nrodoc"),
						resultSet.getString("nombre"),
						resultSet.getString("apellido"),
						resultSet.getString("localidad"),
						resultSet.getString("direccion"),
						resultSet.getString("telefono"),
						resultSet.getString("mail"),
						resultSet.getDate("fecha_creacion"),
						resultSet.getInt("idusuario")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return clientes;
	}

	public boolean delete(ClienteDTO cliente_a_eliminar) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(cliente_a_eliminar.getId()));
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

	public boolean insert(ClienteDTO cliente) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			
			statement.setInt(1, cliente.getNroDoc());
			statement.setString(2, cliente.getNombre());
			statement.setString(3, cliente.getApellido());
			statement.setString(4, cliente.getLocalidad());
			statement.setString(5, cliente.getDireccion());
			statement.setString(6, cliente.getTelefono());
			statement.setString(7, cliente.getMail());
			statement.setDate(8, (Date) cliente.getFecha_creacion());
			statement.setInt(9, cliente.getIdusuario());

			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return false;
	}

	public ClienteDTO find(int idcliente) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ClienteDTO cliente = null;

		try {
			statement = conexion.getSQLConexion().prepareStatement(find);
			statement.setInt(1, idcliente);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				cliente = new ClienteDTO(resultSet.getInt("id"),
						resultSet.getInt("nrodoc"),
						resultSet.getString("nombre"),
						resultSet.getString("apellido"),
						resultSet.getString("localidad"),
						resultSet.getString("direccion"),
						resultSet.getString("telefono"),
						resultSet.getString("mail"),
						resultSet.getDate("fecha_creacion"),
						resultSet.getInt("idusuario"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return cliente;
	}
	
	public ClienteDTO findPorNrodoc(int nrodoc) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ClienteDTO cliente = null;

		try {
			statement = conexion.getSQLConexion().prepareStatement(find2);
			statement.setInt(1, nrodoc);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				cliente = new ClienteDTO(resultSet.getInt("id"),
						resultSet.getInt("nrodoc"),
						resultSet.getString("nombre"),
						resultSet.getString("apellido"),
						resultSet.getString("localidad"),
						resultSet.getString("direccion"),
						resultSet.getString("telefono"),
						resultSet.getString("mail"),
						resultSet.getDate("fecha_creacion"),
						resultSet.getInt("idusuario"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return cliente;
	}
}
