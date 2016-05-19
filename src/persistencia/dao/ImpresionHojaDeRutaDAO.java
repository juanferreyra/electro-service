package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.ImpresionHojaDeRutaDTO;
import persistencia.conexion.Conexion;

public class ImpresionHojaDeRutaDAO {
	
	private static final String readall = "SELECT h.id as idhojaruta, f.telefono as flete_telefono, f.patente as flete_patente, f.modelo as flete_modelo, f.nombre_conductor as flete_conductor, "
									+"DATE(h.fecha_creacion) as fecha, TIME(h.fecha_creacion) as hora, i.id as nro, i.descripcion_producto as producto,  concat(c.nombre,concat('',c.apellido)) as cliente, "
									+"c.localidad as localidad, if(i.envio_default,i.direccion_alternativa,c.direccion) as direccion, p.importe_total + i.monto_envio as importe,  if(hi.en_entrega, '', 'Entregado') as entrega_estado "
									+"FROM hojaruta h LEFT JOIN  hojaruta_ingreso hi ON(h.id = hi.idhojaruta) LEFT JOIN   flete f ON(h.idflete = f.id) LEFT JOIN  ingreso i ON(hi.idingreso=i.id) LEFT JOIN " 
									+"cliente c ON(i.idcliente = c.id) LEFT JOIN  presupuesto p ON(p.idingreso = i.id AND p.habilitado=true);";
			
	private static final String find = "SELECT h.id as idhojaruta, f.telefono as flete_telefono, f.patente as flete_patente, f.modelo as flete_modelo, f.nombre_conductor as flete_conductor, "
									+"DATE(h.fecha_creacion) as fecha, TIME(h.fecha_creacion) as hora, i.id as nro, i.descripcion_producto as producto,  concat(c.nombre,concat('',c.apellido)) as cliente, "
									+"c.localidad as localidad, if(i.envio_default,i.direccion_alternativa,c.direccion) as direccion, p.importe_total + i.monto_envio as importe,  if(hi.en_entrega, '', 'Entregado') as entrega_estado "
									+"FROM hojaruta h LEFT JOIN  hojaruta_ingreso hi ON(h.id = hi.idhojaruta) LEFT JOIN   flete f ON(h.idflete = f.id) LEFT JOIN  ingreso i ON(hi.idingreso=i.id) LEFT JOIN " 
									+"cliente c ON(i.idcliente = c.id) LEFT JOIN  presupuesto p ON(p.idingreso = i.id AND p.habilitado=true) WHERE h.id=?;";
	
	
	private Conexion conexion = Conexion.getConexion();

	public ArrayList<ImpresionHojaDeRutaDTO> readAll() {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<ImpresionHojaDeRutaDTO> reparaciones = new ArrayList<ImpresionHojaDeRutaDTO>();

		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				ImpresionHojaDeRutaDTO reparacion = new ImpresionHojaDeRutaDTO();
				reparacion.setIdhojaruta(resultSet.getInt("idhojaruta"));
				reparacion.setFlete_telefono(resultSet.getString("flete_telefono"));
				reparacion.setFlete_patente(resultSet.getString("flete_patente"));
				reparacion.setFlete_modelo(resultSet.getString("flete_modelo"));
				reparacion.setFlete_conductor(resultSet.getString("flete_conductor"));
				reparacion.setFecha(resultSet.getDate("fecha"));
				reparacion.setHora(resultSet.getString("hora"));
				reparacion.setNro(resultSet.getInt("nro"));
				reparacion.setProducto(resultSet.getString("producto"));
				reparacion.setCliente(resultSet.getString("cliente"));
				reparacion.setLocalidad(resultSet.getString("localidad"));
				reparacion.setDireccion(resultSet.getString("direccion"));
				reparacion.setImporte(resultSet.getFloat("importe"));
				reparacion.setEstado(resultSet.getString("entrega_estado"));
				reparaciones.add(reparacion);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return reparaciones;
	}

	public ArrayList<ImpresionHojaDeRutaDTO> find(int id) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<ImpresionHojaDeRutaDTO> reparaciones = new ArrayList<ImpresionHojaDeRutaDTO>();

		try {
			statement = conexion.getSQLConexion().prepareStatement(find);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				ImpresionHojaDeRutaDTO reparacion = new ImpresionHojaDeRutaDTO();
				reparacion.setIdhojaruta(resultSet.getInt("idhojaruta"));
				reparacion.setFlete_telefono(resultSet.getString("flete_telefono"));
				reparacion.setFlete_patente(resultSet.getString("flete_patente"));
				reparacion.setFlete_modelo(resultSet.getString("flete_modelo"));
				reparacion.setFlete_conductor(resultSet.getString("flete_conductor"));
				reparacion.setFecha(resultSet.getDate("fecha"));
				reparacion.setHora(resultSet.getString("hora"));
				reparacion.setNro(resultSet.getInt("nro"));
				reparacion.setProducto(resultSet.getString("producto"));
				reparacion.setCliente(resultSet.getString("cliente"));
				reparacion.setLocalidad(resultSet.getString("localidad"));
				reparacion.setDireccion(resultSet.getString("direccion"));
				reparacion.setImporte(resultSet.getFloat("importe"));
				reparacion.setEstado(resultSet.getString("entrega_estado"));
				reparaciones.add(reparacion);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return reparaciones;
	}
}
