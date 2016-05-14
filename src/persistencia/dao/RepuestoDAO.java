package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.RepuestoDTO;
import persistencia.conexion.Conexion;

public class RepuestoDAO {
	
	private static final String insert = "INSERT INTO repuesto ("
			+ "`nombre`, `detalle`, `precio`, `stock_minimo`,`fecha_creacion`,`idusuario`,`habilitado`)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?); ";
	private static final String delete = "UPDATE repuesto SET habilitado='0' WHERE id = ?";
	
	private static final String readall = "SELECT * FROM repuesto WHERE habilitado = true;";
	
	private static final String update = "";
	
	private static final String find = "SELECT * FROM repuesto WHERE detalle = ? ;";
	
	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(RepuestoDTO componente)
	{
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1,componente.getId());
			statement.setString(2,componente.getDetalle());
			
			if(statement.executeUpdate() > 0) //Si se ejecut� devuelvo true
				return true;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			Conexion.cerrarConexion();
		}
		return false;
	}
	
	public boolean delete(RepuestoDTO marca_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(marca_a_eliminar.getId()));
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
			Conexion.cerrarConexion();
		}
		return false;
	}
	
	public List<RepuestoDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<RepuestoDTO> componentes = new ArrayList<RepuestoDTO>();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			
			while(resultSet.next())
			{
				componentes.add(new RepuestoDTO(resultSet.getInt("id"),
						resultSet.getString("detalle"),resultSet.getFloat("precio"),
						resultSet.getInt("stock_minimo"),resultSet.getDate("fecha_creacion"),
						resultSet.getInt("idusuario"),resultSet.getInt("habilitado")));
			}
		}
		catch (SQLException e) 
		{
			System.out.println("hubo un error");
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			Conexion.cerrarConexion();
		}
		
		return componentes;
	}
	
	public boolean update(RepuestoDTO marca_a_modificar)

	{
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);

			statement.setString(1, marca_a_modificar.getDetalle());
			statement.setInt(2, marca_a_modificar.getId());

			if(statement.executeUpdate() > 0) //Si se ejecut� devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre 
		{
			Conexion.cerrarConexion();
		}
		return false;
	}
	
	public RepuestoDTO find(String aBuscar)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		RepuestoDTO componente = null;
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(find);
			statement.setString(1, aBuscar);
			resultSet = statement.executeQuery();

			
			while(resultSet.next())
			{
				componente = new RepuestoDTO(resultSet.getInt("id"),
						resultSet.getString("detalle"),resultSet.getFloat("precio"),
						resultSet.getInt("stock_minimo"),resultSet.getDate("fecha_creacion"),
						resultSet.getInt("idusuario"),resultSet.getInt("habilitado"));
			}
		}
		catch (SQLException e) 
		{
			System.out.println("hubo un error");
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			Conexion.cerrarConexion();
		}
		
		return componente;
	}
}
