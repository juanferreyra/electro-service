package persistencia.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dto.ReparacionRepuestoDTO;
import persistencia.conexion.Conexion;

public class ReparacionesRepuestosDAO {

	private static final String insert = "INSERT INTO reparaciones_repuestos (`id`,"
			+ "`idreparacion`, `idrepuesto`, `cantidad`, `fecha_creacion`,`habilitado`)"
			+ " VALUES (?,?, ?, ?, ?, ?); ";
	private static final String nextId = "SELECT Auto_Increment as siguiente FROM INFORMATION_SCHEMA.TABLES WHERE Table_name = 'reparaciones_repuestos';";
	private Conexion conexion = Conexion.getConexion();

	public boolean insert(ReparacionRepuestoDTO componente) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, componente.getId());
			statement.setInt(2, componente.getIdreparacion());
			statement.setInt(3, componente.getIdrepuesto());
			statement.setInt(4, componente.getCantidad());
			statement.setDate(5, (Date) componente.getFecha_creacion());
			statement.setBoolean(6, componente.isHabilitado());

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
