package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.ImpresionIngresoDTO;
import persistencia.conexion.Conexion;

public class ImpresionIngresoDAO {
	
	private static final String readall = "SELECT i.id as id, c.nombre as nombre,c.apellido as apellido, c.telefono as telefono, c.direccion,c.localidad,c.mail as mail,mp.detalle as marca,tp.detalle as tipo_producto,i.descripcion_falla as descripcion_falla,i.descripcion_producto as descripcion_producto,if(i.envio=true,'Tiene Envio a domicilio','No tiene envio a domicilio') as envio,if(i.envio_default=true, 'Se lo envia a su direccion', ' Se lo envia a la direccion: ') as envio_default,if(i.envio_default=false,i.direccion_alternativa,'') as direccion_alternativa,if(i.envio=true,concat('El costo del envio es: $',i.monto_envio),'') as monto,i.fecha_creacion as fecha_creacion "
			+ "FROM ingreso i LEFT JOIN marca_producto mp ON(i.idmarca = mp.id) LEFT JOIN tipo_producto tp ON(i.idtipo_producto = tp.id) LEFT JOIN cliente c ON(i.idcliente = c.id) "
			+ "WHERE i.habilitado=true;";
	private static final String find = "SELECT i.id as id, c.nombre as nombre,c.apellido as apellido, c.telefono as telefono, c.direccion,c.localidad,c.mail as mail,mp.detalle as marca,tp.detalle as tipo_producto,i.descripcion_falla as descripcion_falla,i.descripcion_producto as descripcion_producto,if(i.envio=true,'Tiene Envio a domicilio','No tiene envio a domicilio') as envio,if(i.envio_default=true, 'Se lo envia a su direccion', ' Se lo envia a la direccion: ') as envio_default,if(i.envio_default=false,i.direccion_alternativa,c.direccion) as direccion_alternativa,if(i.envio=true,concat('El costo del envio es: $',i.monto_envio),'') as monto,i.fecha_creacion as fecha_creacion "
			+ "FROM ingreso i LEFT JOIN marca_producto mp ON(i.idmarca = mp.id) LEFT JOIN tipo_producto tp ON(i.idtipo_producto = tp.id) LEFT JOIN cliente c ON(i.idcliente = c.id) "
			+ "WHERE i.habilitado=true AND i.id= ? ;";
	
	
	private Conexion conexion = Conexion.getConexion();

	public ArrayList<ImpresionIngresoDTO> readAll() {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<ImpresionIngresoDTO> clientes = new ArrayList<ImpresionIngresoDTO>();

		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				ImpresionIngresoDTO inge = new ImpresionIngresoDTO();
				inge.setId(resultSet.getInt("id"));
				inge.setCliente_nombre(resultSet.getString("nombre")+" "+resultSet.getString("apellido"));
				inge.setCliente_telefono(resultSet.getString("telefono"));
				inge.setCliente_direccion(resultSet.getString("direccion"));
				inge.setCliente_localidad(resultSet.getString("localidad"));
				inge.setCliente_email(resultSet.getString("mail"));
				inge.setMarca(resultSet.getString("marca"));
				inge.setTipo_producto(resultSet.getString("tipo_producto"));
				inge.setDescripcion_falla(resultSet.getString("descripcion_falla"));
				inge.setNombreProducto(resultSet.getString("descripcion_producto"));
				inge.setEnvio(resultSet.getString("envio"));
				inge.setDireccion_cliente(resultSet.getString("envio_default"));
				inge.setDireccion_alternativa(resultSet.getString("direccion_alternativa"));
				inge.setMonto_envio(resultSet.getString("monto"));
				inge.setFecha(resultSet.getDate("fecha_creacion"));
				clientes.add(inge);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return clientes;
	}

	public ArrayList<ImpresionIngresoDTO> find(int id) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<ImpresionIngresoDTO> clientes = new ArrayList<ImpresionIngresoDTO>();

		try {
			statement = conexion.getSQLConexion().prepareStatement(find);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				ImpresionIngresoDTO inge = new ImpresionIngresoDTO();
				inge.setId(resultSet.getInt("id"));
				inge.setCliente_nombre(resultSet.getString("nombre")+" "+resultSet.getString("apellido"));
				inge.setCliente_telefono(resultSet.getString("telefono"));
				inge.setCliente_direccion(resultSet.getString("direccion"));
				inge.setCliente_localidad(resultSet.getString("localidad"));
				inge.setCliente_email(resultSet.getString("mail"));
				inge.setMarca(resultSet.getString("marca"));
				inge.setTipo_producto(resultSet.getString("tipo_producto"));
				inge.setDescripcion_falla(resultSet.getString("descripcion_falla"));
				inge.setNombreProducto(resultSet.getString("descripcion_producto"));
				inge.setEnvio(resultSet.getString("envio"));
				inge.setDireccion_cliente(resultSet.getString("envio_default"));
				inge.setDireccion_alternativa(resultSet.getString("direccion_alternativa"));
				inge.setMonto_envio(resultSet.getString("monto"));
				inge.setFecha(resultSet.getDate("fecha_creacion"));
				clientes.add(inge);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return clientes;
	}
	
	
	/*public static void main(String[] args) {
		ImpresionIngresoDAO imp = new ImpresionIngresoDAO();
		ArrayList<ImpresionIngresoDTO> a = imp.find(6);
		for (int i = 0; i < a.size(); i++) {
			System.out.println(a.get(i).getCliente_nombre());
		}
	}*/
}
