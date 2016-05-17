package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dto.HojaDeRutaDTO;
import dto.PresupuestoDTO;
import persistencia.conexion.Conexion;

public class HojaDeRutaDAO {
	
	private static final String insert = "INSERT INTO hojaruta (`idflete`, `fecha_creacion`, `habilitado`, `idusuario`) VALUES (?, now(), true, ?);";

	private static final String readall = "SELECT * FROM hojaruta WHERE habilitado=true";

	private static final String find = "SELECT * FROM hojaruta WHERE id  = ? and habilitado = true";

	private Conexion conexion = Conexion.getConexion();


	public ArrayList<HojaDeRutaDTO> readAll() {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<HojaDeRutaDTO> hojasDeRuta = new ArrayList<HojaDeRutaDTO>();

		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			
			while (resultSet.next()) { hojasDeRuta.add(new HojaDeRutaDTO(
						resultSet.getInt("id"),
						resultSet.getInt("idflete"),
						resultSet.getDate("fecha_creacion"),
						resultSet.getInt("idusuario")));
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
			statement.setInt(1, hojaDeRuta.getIdIngreso());
			statement.setInt(2, hojaDeRuta.getIdUsuario());

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
						resultSet.getInt("id"),
						resultSet.getInt("idflete"),
						resultSet.getDate("fecha_creacion"),
						resultSet.getInt("idusuario"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return hojasDeRuta;
	}
}

	
