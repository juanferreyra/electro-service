package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.PresupuestoRepuestoDTO;
import persistencia.conexion.Conexion;

public class PresupuestoRepuestoDAO {
	
	private static final String insert = "INSERT INTO presupuesto_repuestos (id,idpresupuesto,idrepuesto,"
			+ "cantidad,fecha_creacion,habilitado) VALUES (?,?,?,?,now(),true)";
	
	@SuppressWarnings("unused")
	private static final String delete = "UPDATE ingreso SET habilitado='0' WHERE id= ?;";
	
	private static final String readall = "SELECT * FROM ingreso WHERE habilitado=true";
	
	private static final String find = "SELECT * FROM ingreso WHERE habilitado=true AND id = ?";
	
	private static final String buscarPresupuesto = "SELECT id FROM electro_service_db.presupuesto WHERE idingreso  = ?";
	
	private Conexion conexion = Conexion.getConexion();

	public ArrayList<PresupuestoRepuestoDTO> readAll() {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		@SuppressWarnings("unused")
		ResultSet resultSet;
		ArrayList<PresupuestoRepuestoDTO> presupuestoRepuesto = new ArrayList<PresupuestoRepuestoDTO>();

		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			/*while (resultSet.next()) {
				localidades.add(new PresupuestoRepuestoDTO(resultSet.getInt("id"),
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
			Conexion.cerrarConexion();
		}
		return presupuestoRepuesto;
	}

	public boolean delete(PresupuestoRepuestoDTO ingreso_a_eliminar) {
		/*conexion = Conexion.getConexion();
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
		}*/
		return false;
	}

	public boolean insert(PresupuestoRepuestoDTO presupuestoRepuesto) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			
			statement.setInt(1, presupuestoRepuesto.getId());
			statement.setInt(2, presupuestoRepuesto.getIdPresupuesto());
			statement.setInt(3, presupuestoRepuesto.getIdComponente());
			statement.setInt(4, presupuestoRepuesto.getCantidad());

			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return false;
	}

	public PresupuestoRepuestoDTO find(int id) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		@SuppressWarnings("unused")
		ResultSet resultSet;
		PresupuestoRepuestoDTO ingreso = null;

		try {
			statement = conexion.getSQLConexion().prepareStatement(find);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			/*while (resultSet.next()) {
				ingreso = new PresupuestoRepuestoDTO(resultSet.getInt("id"),
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
			Conexion.cerrarConexion();
		}
		return ingreso;
	}
	
	public int buscarPresupuesto(int id) {
		
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		int idIngreso = 0;

		try {
			statement = conexion.getSQLConexion().prepareStatement(buscarPresupuesto);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				
				idIngreso = resultSet.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return idIngreso;
	}
	

}
