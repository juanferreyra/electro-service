package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dto.ProveedorDTO;
import persistencia.conexion.Conexion;

public class ProveedorDAO {
	
	private static final String insert = "INSERT INTO proveedor (razon_social, cuit, direccion, mail, contacto_nombre, contacto_telefono,"
			+ " contacto_mail, mail_para_pedidos, fecha_creacion, idusuario, habilitado)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, now(), 1, true);";
	
	private static final String delete = "DELETE FROM proveedor WHERE `id`= ?;;";
	
	private static final String readall = "SELECT * FROM proveedor WHERE habilitado = true";
	
	private static final String find = "SELECT * FROM proveedor WHERE habilitado=true AND id = ?";
	
	private static final String update =" UPDATE cliente SET nrodoc = ?, nombre = ?, apellido = ?, localidad = ?,"
			+ " direccion = ?, telefono = ?, mail = ? WHERE id = ?;";
	
	private Conexion conexion = Conexion.getConexion();

	public ArrayList<ProveedorDTO> readAll() {
		
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<ProveedorDTO> proveedores = new ArrayList<ProveedorDTO>();

		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				proveedores.add(new ProveedorDTO(
						resultSet.getInt("id"),
						resultSet.getString("razon_social"),
						resultSet.getInt("cuit"),
						resultSet.getString("direccion"),
						resultSet.getString("mail"),
						resultSet.getString("contacto_nombre"),
						resultSet.getString("contacto_telefono"),
						resultSet.getString("contacto_mail"),
						resultSet.getString("mail_para_pedidos"),
						resultSet.getInt("idusuario")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return proveedores;
	}

	public boolean borrarProveedor(int id_proveedor) {
		
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, id_proveedor);
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
	
	public boolean insert(ProveedorDTO proveedor) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			
			statement.setString(1, proveedor.getRazonSocial());
			statement.setInt(2, proveedor.getCuit());
			statement.setString(3, proveedor.getDireccion());
			statement.setString(4, proveedor.getEmail());
			statement.setString(5, proveedor.getNombreContacto());
			statement.setString(6, proveedor.getTelefonoContacto());
			statement.setString(7, proveedor.getEmailContacto());
			statement.setString(8, proveedor.getEmailPedidos());
			statement.setInt(9, 1);

			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return false;
	}

	public ProveedorDTO find(int idcliente) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ProveedorDTO proveedor = null;

		try {
			statement = conexion.getSQLConexion().prepareStatement(find);
			statement.setInt(1, idcliente);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				proveedor = new ProveedorDTO(resultSet.getInt("id"),
						resultSet.getString("razon_social"),
						resultSet.getInt("cuit"),
						resultSet.getString("direccion"),
						resultSet.getString("mail"),
						resultSet.getString("contacto_nombre"),
						resultSet.getString("contacto_telefono"),
						resultSet.getString("contacto_mail"),
						resultSet.getString("mail_para_pedidos"),
						resultSet.getInt("idusuario"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return proveedor;
	}
}
