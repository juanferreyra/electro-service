package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.IngresoDTO;
import persistencia.conexion.Conexion;

public class IngresoDAO {

	private static final String insert = "INSERT INTO ingreso(`idcliente`, `descripcion_producto`, `idmarca`, `idtipo_producto`,"
			+ " `descripcion_falla`, `envio`, `envio_default`, `direccion_alternativa`, `monto_envio`, `estado`, `fecha_creacion`, `idusuario`, `habilitado`)"
			+ " VALUES ( ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, true)";
	private static final String delete = "UPDATE ingreso SET habilitado='0' WHERE id= ?;";
	private static final String readall = "SELECT * FROM ingreso WHERE habilitado=true";
	private static final String find = "SELECT * FROM ingreso WHERE habilitado=true AND id = ?";
	private static final String nextId = "SELECT Auto_Increment as siguiente FROM INFORMATION_SCHEMA.TABLES WHERE Table_name = 'ingreso';";
	private Conexion conexion = Conexion.getConexion();

	public ArrayList<IngresoDTO> readAll() {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<IngresoDTO> ingresos = new ArrayList<IngresoDTO>();

		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				ingresos.add(new IngresoDTO(resultSet.getInt("id"), resultSet.getInt("idcliente"),
						resultSet.getString("descripcion_producto"), resultSet.getInt("idmarca"),
						resultSet.getInt("idtipo_producto"), resultSet.getString("descripcion_falla"),
						resultSet.getBoolean("envio"), resultSet.getBoolean("envio_default"),
						resultSet.getString("direccion_alternativa"), resultSet.getFloat("monto_envio"),
						resultSet.getDate("fecha_creacion"), resultSet.getInt("estado"), resultSet.getInt("idusuario"),
						resultSet.getInt("tecnico_asignado")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return ingresos;
	}

	public boolean delete(IngresoDTO ingreso_a_eliminar) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(ingreso_a_eliminar.getId()));
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

	public Boolean insert(IngresoDTO ingreso) {
		conexion = Conexion.getConexion();
		try {
			PreparedStatement statement = conexion.getSQLConexion().prepareStatement(insert);

			statement.setInt(1, ingreso.getIdcliente());
			statement.setString(2, ingreso.getDescripcion());
			statement.setInt(3, ingreso.getIdmarca());
			statement.setInt(4, ingreso.getIdtipo_producto());
			statement.setString(5, ingreso.getDescripcion_falla());
			statement.setBoolean(6, ingreso.getEnvio());
			statement.setBoolean(7, ingreso.getEnvio_default());
			statement.setString(8, ingreso.getDireccion_alternativa());
			statement.setFloat(9, ingreso.getMonto_envio());
			statement.setInt(10, ingreso.getEstado());
			statement.setInt(11, ingreso.getIdusuario());

			if (statement.executeUpdate() > 0)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}

		return false;
	}

	public IngresoDTO find(int id) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		IngresoDTO ingreso = null;

		try {
			statement = conexion.getSQLConexion().prepareStatement(find);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				ingreso = new IngresoDTO(resultSet.getInt("id"), resultSet.getInt("idcliente"),
						resultSet.getString("descripcion_producto"), resultSet.getInt("idmarca"),
						resultSet.getInt("idtipo_producto"), resultSet.getString("descripcion_falla"),
						resultSet.getBoolean("envio"), resultSet.getBoolean("envio_default"),
						resultSet.getString("direccion_alternativa"), resultSet.getFloat("monto_envio"),
						resultSet.getDate("fecha_creacion"), resultSet.getInt("estado"), resultSet.getInt("idusuario"),
						resultSet.getInt("tecnico_asignado"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return ingreso;
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
