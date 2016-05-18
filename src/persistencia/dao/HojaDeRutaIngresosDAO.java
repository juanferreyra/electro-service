package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.HojaDeRutaIngresosDTO;
import persistencia.conexion.Conexion;

public class HojaDeRutaIngresosDAO {
	
	private static final String insert = "INSERT INTO hojaruta_ingreso (`idhojaruta`, `idingreso`, `en_entrega`, `fecha_creacion`) VALUES (?, ?, '1', now());";
	
	private static final String findList = "SELECT * FROM hojaruta_ingreso WHERE idhojaruta=? AND en_entrega=false;";
	
	private Conexion conexion = Conexion.getConexion();

	public boolean insert(HojaDeRutaIngresosDTO hojasDeRutaIngresos) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			
			statement.setInt(1, hojasDeRutaIngresos.getIdHojaDeRuta());
			statement.setInt(2, hojasDeRutaIngresos.getIdIngreso());

			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return false;
	}
	
	public ArrayList<HojaDeRutaIngresosDTO> findAll(int id) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<HojaDeRutaIngresosDTO> hojasDeRutaIngresos = new ArrayList<HojaDeRutaIngresosDTO>();
		
		try {
			statement = conexion.getSQLConexion().prepareStatement(findList);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) { hojasDeRutaIngresos.add(new HojaDeRutaIngresosDTO(
					resultSet.getInt("id"),
					resultSet.getInt("idhojaruta"),
					resultSet.getInt("idingreso"),
					resultSet.getBoolean("en_entrega"),
					resultSet.getDate("fecha_creacion")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return hojasDeRutaIngresos;
	}
}
