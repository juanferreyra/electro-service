package persistencia.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dto.FleteDTO;

import persistencia.conexion.Conexion;

public class FleteDAO {
	private static final String insert = "INSERT INTO flete (`nrodoc`, `nombre_conductor`, `modelo`, `patente`, `telefono`, `vto_licencia`, "
			+ "`fecha_creacion`, `idusuario`, `habilitado`)" + " VALUES (?, ?, ?, ?, ?, ?, now(), ?, '1'); ";
	private static final String readall = "SELECT * FROM flete WHERE habilitado=true";
	private static final String find = "SELECT * FROM flete WHERE habilitado=true AND id=?";
	private static final String findDNI = "SELECT * FROM flete WHERE habilitado=true AND nrodoc=?";
	private static final String update = "UPDATE flete SET nrodoc = ? , nombre_conductor= ? , modelo= ? , patente= ? , telefono= ? , vto_licencia= ? , fecha_creacion=now() , idusuario=? , habilitado=1  WHERE id = ? ;";
	private static final String delete = "UPDATE flete SET habilitado='0' WHERE id = ?";

	private Conexion conexion = Conexion.getConexion();

	public boolean delete(FleteDTO flete_a_eliminar) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(flete_a_eliminar.getId()));
			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0) // Si se ejecuta devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			Conexion.cerrarConexion();
		}
		return false;
	}

	public boolean update(FleteDTO flete_a_modificar)

	{
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);

			statement.setInt(1, flete_a_modificar.getNroDoc());
			statement.setString(2, flete_a_modificar.getNombre());
			statement.setString(3, flete_a_modificar.getModelo());
			statement.setString(4, flete_a_modificar.getPatente());
			statement.setString(5, flete_a_modificar.getTelefono());
			statement.setDate(6, (Date) new java.sql.Date(flete_a_modificar.getVtoLicencia().getTime()));
			statement.setInt(7, flete_a_modificar.getIdusuario());
			statement.setInt(8, flete_a_modificar.getId());

			if (statement.executeUpdate() > 0) { // Si se ejecuta devuelvo true
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			Conexion.cerrarConexion();
		}
		return false;
	}

	public ArrayList<FleteDTO> readAll() {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<FleteDTO> fletes = new ArrayList<FleteDTO>();

		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				fletes.add(new FleteDTO(resultSet.getInt("id"), resultSet.getInt("nrodoc"),
						resultSet.getString("nombre_conductor"), resultSet.getString("modelo"),
						resultSet.getString("patente"), resultSet.getString("telefono"),
						resultSet.getDate("vto_licencia"), resultSet.getDate("fecha_creacion"),
						resultSet.getInt("idusuario")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return fletes;
	}

	public boolean insert(FleteDTO flete) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, flete.getNroDoc());
			statement.setString(2, flete.getNombre());
			statement.setString(3, flete.getModelo());
			statement.setString(4, flete.getPatente());
			statement.setString(5, flete.getTelefono());
			statement.setDate(6, (Date) new java.sql.Date(flete.getVtoLicencia().getTime()));
			statement.setInt(7, flete.getIdusuario());

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
				flete = new FleteDTO(resultSet.getInt("id"), resultSet.getInt("nrodoc"),
						resultSet.getString("nombre_conductor"), resultSet.getString("modelo"),
						resultSet.getString("patente"), resultSet.getString("telefono"),
						resultSet.getDate("vto_licencia"), resultSet.getDate("fecha_creacion"),
						resultSet.getInt("idusuario"));
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
				fletes = new FleteDTO(resultSet.getInt("id"), resultSet.getInt("nrodoc"),
						resultSet.getString("nombre_conductor"), resultSet.getString("modelo"),
						resultSet.getString("patente"), resultSet.getString("telefono"),
						resultSet.getDate("vto_licencia"), resultSet.getDate("fecha_creacion"),
						resultSet.getInt("idusuario"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return fletes;
	}
}
