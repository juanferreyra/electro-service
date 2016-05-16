package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.HojaDeRutaDTO;
import dto.PresupuestoDTO;
import persistencia.conexion.Conexion;

public class HojaDeRutaDAO {
	static final String insert = "INSERT INTO hojaDeRuta ( id, fleteId , creacion)";

	private static final String delete = "UPDATE hojaDeRuta SET habilitado='0' WHERE id= ?;";

	private static final String readall = "SELECT * FROM hojaDeRuta WHERE habilitado=true";

	private static final String find = "SELECT * FROM hojaDeRuta WHERE id  = ? and habilitado = true";

	private Conexion conexion = Conexion.getConexion();


	public ArrayList<HojaDeRutaDTO> readAll() {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<HojaDeRutaDTO> hojasDeRuta = new ArrayList<HojaDeRutaDTO>();

		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			
			while (resultSet.next()) { hojasDeRuta.add(new
					HojaDeRutaDTO(resultSet.getDate("creacion"),
							resultSet.getInt("idingreso"),
							resultSet.getInt("id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return hojasDeRuta;
	}
	
	public boolean insert(PresupuestoDTO hojaDeRuta) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);

			statement.setInt(1, hojaDeRuta.getId());
			statement.setInt(2, hojaDeRuta.getIdIngreso());
			statement.setDate(3, new java.sql.Date(hojaDeRuta.getFechavencimiento().getTime()));

			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return false;
	}

	public HojaDeRutaDTO find(int id) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		HojaDeRutaDTO hojasDeRuta = null;

		try {
			statement = conexion.getSQLConexion().prepareStatement(find);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				hojasDeRuta = new HojaDeRutaDTO(
						resultSet.getDate("fecha_creacion"),
						resultSet.getInt("idingreso"),
						resultSet.getInt("id"));
			}
		} catch (
				SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return hojasDeRuta;
	}
}

	
