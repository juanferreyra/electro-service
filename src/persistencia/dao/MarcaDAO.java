
package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.conexion.Conexion;
import dto.MarcaDTO;

public class MarcaDAO {

	private static final String insert = "INSERT INTO marca_producto ("
			+ "`detalle`, `fecha_creacion`, `idusuario`, `habilitado`)" + " VALUES (?, now(), ?, true); ";
	private static final String delete = "UPDATE marca_producto SET habilitado='0' WHERE id = ?";
	private static final String readall = "SELECT id, detalle, idusuario FROM marca_producto WHERE habilitado = true;";
	private static final String update = "UPDATE marca_producto SET detalle = ?  WHERE id = ? ;";
	private static final String find = "SELECT id, detalle, idusuario FROM marca_producto WHERE habilitado = true AND id = ?;";
	private static final String findByDetalle = "SELECT id, detalle, idusuario FROM marca_producto WHERE habilitado = true AND detalle = ?;";
	
	private static final String buscarMarcasPorIdProveedor =" SELECT m.id , m.detalle FROM 20161_service_g2.marca_producto m "
			+ "left join 20161_service_g2.proveedor_marca pm  on (m.id = pm.idmarca) where pm.idproveedor = ?  ; ";
	
	private static Conexion conexion = Conexion.getConexion();

	public boolean insert(MarcaDTO marca) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, marca.getDetalle());
			statement.setInt(2, marca.getIdusuario());

			if (statement.executeUpdate() > 0) // Si se ejecut� devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			Conexion.cerrarConexion();
		}
		return false;
	}

	public boolean delete(MarcaDTO marca_a_eliminar) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(marca_a_eliminar.getId()));
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

	public List<MarcaDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<MarcaDTO> marcas = new ArrayList<MarcaDTO>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				marcas.add(new MarcaDTO(resultSet.getInt("id"), resultSet.getString("detalle"),
						resultSet.getInt("idusuario")));
			}
		} catch (SQLException e) {
			System.out.println("hubo un error");
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			Conexion.cerrarConexion();
		}

		return marcas;
	}
	
	public ArrayList<MarcaDTO> buscarMarcasPorIdProvedor(int idProveedor) {
		PreparedStatement statement;
		
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<MarcaDTO> marcas = new ArrayList<MarcaDTO>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(buscarMarcasPorIdProveedor);
			statement.setInt(1, idProveedor);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				marcas.add(new MarcaDTO(resultSet.getInt("id"), resultSet.getString("detalle"),1));
			}
		} catch (SQLException e) {
			System.out.println("hubo un error");
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			Conexion.cerrarConexion();
		}

		return marcas;
	}

	public boolean update(MarcaDTO marca_a_modificar)

	{
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);

			statement.setString(1, marca_a_modificar.getDetalle());
			statement.setInt(2, marca_a_modificar.getId());

			if (statement.executeUpdate() > 0) // Si se ejecut� devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			Conexion.cerrarConexion();
		}
		return false;
	}

	public MarcaDTO find(int idmarca) {

		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		MarcaDTO marca = null;

		try {
			statement = conexion.getSQLConexion().prepareStatement(find);
			statement.setInt(1, idmarca);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				marca = new MarcaDTO(resultSet.getInt("id"), resultSet.getString("detalle"),
						resultSet.getInt("idusuario"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return marca;
	}
	
	public String BuscarDetalleXid(int idmarca) {

		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		String marca = "";

		try {
			statement = conexion.getSQLConexion().prepareStatement(find);
			statement.setInt(1, idmarca);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				marca = resultSet.getString("detalle");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return marca;
	}

	public MarcaDTO findByDetalle(String detalle) {

		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		MarcaDTO marca = null;

		try {
			statement = conexion.getSQLConexion().prepareStatement(findByDetalle);
			statement.setString(1, detalle);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				marca = new MarcaDTO(resultSet.getInt("id"), resultSet.getString("detalle"),
						resultSet.getInt("idusuario"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return marca;
	}

	public int buscarIdMarca(String detalle) {
		
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		int id = 0;

		try {
			statement = conexion.getSQLConexion().prepareStatement(findByDetalle);
			statement.setString(1, detalle);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				id = resultSet.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return id;
	}
}
