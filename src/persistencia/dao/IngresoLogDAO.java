package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.IngresoEstadoDTO;
import dto.IngresoLogDTO;
import persistencia.conexion.Conexion;

public class IngresoLogDAO {
	
	private static final String insert = "INSERT INTO ingreso_log("
			+ "`idingreso`, `idestado`, `fecha_creacion`, `idusuario`, `habilitado`)"
			+ "VALUES (?,?, now(), ?, true);";
	private static final String delete = "UPDATE ingreso_log SET habilitado='0' WHERE id = ?";
	private static final String readall = "SELECT * FROM ingreso_log WHERE habilitado = true;";
	private static final String find = "SELECT il.id, e.detalle, il.fecha_creacion, il.idusuario FROM "
			+ "ingreso_log il LEFT JOIN estado e ON(il.idestado = e.id) WHERE il.idingreso = ? AND il.habilitado=true;";
	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(IngresoLogDTO ingreso_log)
	{
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, ingreso_log.getIdingreso());
			statement.setInt(2, ingreso_log.getIdestado());
			statement.setInt(2, ingreso_log.getIdusuario());
			
			if(statement.executeUpdate() > 0) //Si se ejecut� devuelvo true
				return true;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	
	public boolean delete(IngresoLogDTO ingreso_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(ingreso_a_eliminar.getId()));
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0) //Si se ejecut� devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	
	public List<IngresoLogDTO> readAll(int idIngreso)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<IngresoLogDTO> marcas = new ArrayList<IngresoLogDTO>();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				marcas.add(new IngresoLogDTO(resultSet.getInt("id"),resultSet.getInt("idingreso"),resultSet.getInt("idestado"),resultSet.getDate("fecha_creacion"),resultSet.getInt("idusuario")));
			}
		}
		catch (SQLException e) 
		{
			System.out.println("hubo un error");
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		
		return marcas;
	}

	public ArrayList<IngresoEstadoDTO> find(int idIngreso) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<IngresoEstadoDTO> ingreso_log = new ArrayList<IngresoEstadoDTO>();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(find);
			statement.setInt(1, idIngreso);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				ingreso_log.add(new IngresoEstadoDTO(resultSet.getInt("id"),resultSet.getString("detalle"),resultSet.getDate("fecha_creacion"),resultSet.getInt("idusuario")));
			}
		}
		catch (SQLException e) 
		{
			System.out.println("hubo un error");
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		
		return ingreso_log;
	}
		
}
