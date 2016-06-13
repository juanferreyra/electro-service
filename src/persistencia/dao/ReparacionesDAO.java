package persistencia.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.ReparacionDTO;
import persistencia.conexion.Conexion;

public class ReparacionesDAO {

	private static final String insert = "INSERT INTO reparaciones (`id`,"
			+ "`tecnico_asignado`, `fecha_reparacion`, `horas`, `valor_estimado`,`descripcion_final`,`ingreso_id`)"
			+ " VALUES (?, ?, now(), ?, ?, ?, ?); ";
	private static final String nextId = "SELECT Auto_Increment as siguiente FROM INFORMATION_SCHEMA.TABLES WHERE Table_name = 'reparaciones';";
	private Conexion conexion = Conexion.getConexion();

	public boolean insert(ReparacionDTO componente) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, componente.getId());
			statement.setString(2, componente.getTecnico_asignado());
			statement.setDate(3, (Date) componente.getFecha_reparacion());
			//statement.setInt(4, componente.getHoras());
			statement.setInt(4, componente.getValor_estimado());
			statement.setString(5, componente.getDescripcion_final());
			statement.setInt(6, componente.getIngreso_id());

			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return false;
	}

	public int getNextId() {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		int clave = -1;

		try {
			statement = conexion.getSQLConexion().prepareStatement(nextId);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				clave = resultSet.getInt("siguiente");
				;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return clave;
	}

}
