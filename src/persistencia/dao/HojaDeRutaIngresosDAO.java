package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.HojaDeRutaIngresosDTO;
import dto.ItemPresupuestoRepuestoDTO;
import dto.PresupuestoRepuestoDTO;
import persistencia.conexion.Conexion;

public class HojaDeRutaIngresosDAO {
	private static final String insert = "INSERT INTO hojaDeRuta_ingresos (id,idHojaDeRuta,idIngreso,fecha_creacion,habilitado) VALUES (?,?,?,now(),true)";
	
	private static final String find = "SELECT pr.id, pr.idIngreso, FROM hojaDeRuta_ingresos pr LEFT JOIN ingresos r ON(pr.idIngreso = r.id) WHERE pr.id = ? AND pr.habilitado=true;";
	
	private Conexion conexion = Conexion.getConexion();

	public boolean insert(HojaDeRutaIngresosDTO hojasDeRutaIngresops) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			
			statement.setInt(1, hojasDeRutaIngresops.getId());
			statement.setInt(2, hojasDeRutaIngresops.getIdHojaDeRuta());
			statement.setInt(3, hojasDeRutaIngresops.getIdIngreso());

			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return false;
	}

//	public ArrayList<HojaDeRutaIngresosDAO> readAll(int id) {
//		conexion = Conexion.getConexion();
//		PreparedStatement statement;
//		ResultSet resultSet;
//		ArrayList<HojaDeRutaIngresosDAO> hojasDeRutaIngresos = new ArrayList<HojaDeRutaIngresosDAO>();
//
//		try {
//			statement = conexion.getSQLConexion().prepareStatement(find);
//			statement.setInt(1, id);
//			resultSet = statement.executeQuery();
//
//			while (resultSet.next()) {
//				hojasDeRutaIngresos.add(new ItemPresupuestoRepuestoDTO(
//						//TODO
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			Conexion.cerrarConexion();
//		}
//		return hojasDeRutaIngresos;
//	}

	
	
}
