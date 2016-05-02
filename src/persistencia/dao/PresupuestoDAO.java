package persistencia.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dto.PresupuestoDTO;
import persistencia.conexion.Conexion;

public class PresupuestoDAO {

	private static final String insert = "INSERT INTO presupuesto (id,idingreso,descripcion_breve,"
			+ "descripcion_tecnica,importe_mano_obra,fecha_creacion,fecha_vencimiento,idusuario,habilitado) VALUES (?,?,?,?,?,now(),?,?,true)";
	
	private static final String delete = "UPDATE ingreso SET habilitado='0' WHERE id= ?;";
	
	private static final String readall = "SELECT * FROM ingreso WHERE habilitado=true";
	
	private static final String find = "SELECT * FROM ingreso WHERE habilitado=true AND id = ?";
	
	private Conexion conexion = Conexion.getConexion();

	public ArrayList<PresupuestoDTO> readAll() {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<PresupuestoDTO> localidades = new ArrayList<PresupuestoDTO>();

		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			/*while (resultSet.next()) {
				localidades.add(new PresupuestoDTO(resultSet.getInt("id"),
						resultSet.getInt("idingreso"),
						resultSet.getString("descripcion_producto"),
						resultSet.getInt("idmarca"),
						resultSet.getInt("idtipo_producto"),
						resultSet.getString("descripcion_falla"),
						resultSet.getBoolean("envio"),
						resultSet.getBoolean("envio_default"),
						resultSet.getDate("fecha_creacion"),
						resultSet.getInt("estado"),
						resultSet.getInt("idusuario")));
			}*/
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return localidades;
	}

	public boolean delete(PresupuestoDTO ingreso_a_eliminar) {
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
			conexion.cerrarConexion();
		}
		return false;
	}

	public boolean insert(PresupuestoDTO presupuesto) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			
			statement.setInt(1, presupuesto.getId());
			statement.setInt(2, presupuesto.getIdIngreso());
			statement.setString(3, presupuesto.getDescripcionBreve());
			statement.setString(4, presupuesto.getDescripcionTecnica());
			statement.setString(5, presupuesto.getImporteManoObra());
			statement.setDate(6, (java.sql.Date) presupuesto.getFechavencimiento());
			statement.setInt(7,presupuesto.getIdUsuario());

			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return false;
	}

	public PresupuestoDTO find(int id) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		PresupuestoDTO ingreso = null;

		try {
			statement = conexion.getSQLConexion().prepareStatement(find);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			/*while (resultSet.next()) {
				ingreso = new PresupuestoDTO(resultSet.getInt("id"),
						resultSet.getInt("idcliente"),
						resultSet.getString("descripcion_producto"),
						resultSet.getInt("idmarca"),
						resultSet.getInt("idtipo_producto"),
						resultSet.getString("descripcion_falla"),
						resultSet.getBoolean("envio"),
						resultSet.getBoolean("envio_default"),
						resultSet.getDate("fecha_creacion"),
						resultSet.getInt("estado"),
						resultSet.getInt("idusuario"));
			}*/
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return ingreso;
	}
}
