package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.MarcaDTO;
import dto.ProveedorMarcaDTO;
import persistencia.conexion.Conexion;

public class ProveedorMarcaDAO {
	
	private static final String insert = "INSERT INTO proveedor_marca (idproveedor, idmarca) VALUES (?, ?);";
	
	private static final String find = "SELECT m.id , m.detalle FROM marca_producto m left join proveedor_marca pm"
			+ "on (m.id = pm.idmarca) where pm.idproveedor = ? ";
	
	private static final String delete = "DELETE FROM proveedor_marca  WHERE idproveedor = ?;"; 
	
	private static final String contarIdProveedor = "SELECT count(*) FROM proveedor_marca where idproveedor = ?;"; 
	
	private Conexion conexion = Conexion.getConexion();

	public boolean insert(ProveedorMarcaDTO proveedorMarca) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			
			statement.setInt(1, proveedorMarca.getId());
			statement.setInt(2, proveedorMarca.getIdProveedor());
			statement.setInt(3, proveedorMarca.getIdMarca());

			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return false;
	}

	public ArrayList<MarcaDTO> readAll(int id) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<MarcaDTO> presupuestoRepuesto = new ArrayList<MarcaDTO>();

		try {
			statement = conexion.getSQLConexion().prepareStatement(find);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				presupuestoRepuesto.add(new MarcaDTO(
						resultSet.getInt("id"),
						resultSet.getString("detalle"),
						resultSet.getInt("idUsuario")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return presupuestoRepuesto;
	}
	
	public boolean delete(int id) {
		
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, id);
			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0) // Si se ejecut� devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally // Se ejecuta siempre
		{
			Conexion.cerrarConexion();
		}
		return false;
	}
	
	public int contarIdProveedor(int id){
		
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		int ret = 0;

		try {
			statement = conexion.getSQLConexion().prepareStatement(contarIdProveedor);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
						ret = resultSet.getInt("count(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return ret;
	}
	
	public void borrarTodos(int id ){
		
		int loop = contarIdProveedor(id);
		System.out.println(id);
		
		for(int i = 0; i < loop; i ++){
			
			delete(id);
		}
		
	}
}
