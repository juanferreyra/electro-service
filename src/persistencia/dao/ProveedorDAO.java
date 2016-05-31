package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ClienteDTO;
import dto.ProveedorDTO;
import persistencia.conexion.Conexion;

public class ProveedorDAO {
	
	private static final String insert = "INSERT INTO cliente("
			+ "nrodoc, nombre, apellido, localidad, direccion, telefono, mail, fecha_creacion, idusuario, habilitado) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, now(), ?, true);";
	
	private static final String delete = "UPDATE cliente SET habilitado=false WHERE id= ?;";
	
	private static final String readall = "SELECT * FROM proveedor WHERE habilitado = true";
	
	private static final String find = "SELECT * FROM cliente WHERE habilitado=true AND id=?";
	
	private static final String find2 = "SELECT * FROM cliente WHERE habilitado=true AND nrodoc=?";
	
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

}
