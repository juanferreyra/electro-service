package persistencia.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.ClienteDTO;
import dto.FleteDTO;
import persistencia.conexion.Conexion;

public class FleteDAO {
	private static final String insert = "INSERT INTO flete("
			+ "`nrodoc`, `nombre`, `apellido`, `modelo`, `patente`, `telefono`, `vtoLicencia`, `fecha_creacion`, `idusuario`) "
			+ "VALUES (?, ?, ?, ?, ?', ?, ?, ?, now(), ?);";
	private static final String delete = "UPDATE flete SET habilitado=false WHERE id= ?;";
	private static final String readall = "SELECT * FROM flete WHERE habilitado=true";
	private static final String find = "SELECT * FROM flete WHERE habilitado=true AND id=?";
	private static final String findDNI = "SELECT * FROM flete WHERE habilitado=true AND nrodoc=?";
	private Conexion conexion = Conexion.getConexion();
	
	public ArrayList<FleteDTO> readAll() {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<FleteDTO> fletes = new ArrayList<FleteDTO>();

		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				fletes.add(new FleteDTO(resultSet.getInt("id"),
						resultSet.getInt("nrodoc"),
						resultSet.getString("nombre"),
						resultSet.getString("apellido"),
						resultSet.getString("modelo"),
						resultSet.getString("patente"),
						resultSet.getString("telefono"),
						resultSet.getDate("vtoLicencia"),
						resultSet.getDate("fecha_creacion")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return fletes;
	}
	
	
	public boolean delete(ClienteDTO flete_a_eliminar) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(flete_a_eliminar.getId()));
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
	
	public boolean insert(FleteDTO flete) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			
			statement.setInt(1, flete.getNroDoc());
			statement.setString(2, flete.getNombre());
			statement.setString(3, flete.getApellido());
			statement.setString(4, flete.getModelo());
			statement.setString(5, flete.getPatente());
			statement.setString(6, flete.getTelefono());
			statement.setDate(7, (Date) flete.getVtoLicencia());
			statement.setDate(8, (Date) flete.getFecha_creacion());

			if (statement.executeUpdate() > 0)
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return false;
	}
	

	public FleteDTO find(int idFlete) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		FleteDTO flete = null;

		try {
			statement = conexion.getSQLConexion().prepareStatement(find);
			statement.setInt(1, idFlete);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				flete = new FleteDTO(resultSet.getInt("id"),
						resultSet.getInt("nrodoc"),
						resultSet.getString("nombre"),
						resultSet.getString("apellido"),
						resultSet.getString("modelo"),
						resultSet.getString("patente"),
						resultSet.getString("telefono"),
						resultSet.getDate("vtoLicencia"),
						resultSet.getDate("fecha_creacion"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return flete;
	}
	
	public FleteDTO findPorNrodoc(int nrodoc) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		FleteDTO fletes = null;

		try {
			statement = conexion.getSQLConexion().prepareStatement(findDNI);
			statement.setInt(1, nrodoc);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				fletes = new FleteDTO(resultSet.getInt("id"),
						resultSet.getInt("nrodoc"),
						resultSet.getString("nombre"),
						resultSet.getString("apellido"),
						resultSet.getString("modelo"),
						resultSet.getString("patente"),
						resultSet.getString("telefono"),
						resultSet.getDate("vtoLicencia"),
						resultSet.getDate("fecha_creacion"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return fletes;
	}
	
	
	
}

