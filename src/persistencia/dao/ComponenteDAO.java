package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ComponenteDTO;
import persistencia.conexion.Conexion;

public class ComponenteDAO {
	
	private static final String insert = "INSERT INTO repuesto ("
			+ "`nombre`, `detalle`, `precio`, `stock_minimo`,`fecha_creacion`,`idusuario`,`habilitado`)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?); ";
	private static final String delete = "UPDATE repuesto SET habilitado='0' WHERE id = ?";
	
	private static final String readall = "SELECT * FROM electro_service_db.repuesto;";
	
	private static final String update = "UPDATE marca_producto SET detalle = ?  WHERE id = ? ;";
	
	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(ComponenteDTO componente)
	{
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1,componente.getId());
			statement.setString(2,componente.getNombre());
			statement.setString(3,componente.getDetalle());
			
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
	
	public boolean delete(ComponenteDTO marca_a_eliminar)
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
			conexion.cerrarConexion();
		}
		return false;
	}
	
	public List<ComponenteDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<ComponenteDTO> componentes = new ArrayList<ComponenteDTO>();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			
			while(resultSet.next())
			{
				componentes.add(new ComponenteDTO(resultSet.getInt("id"),resultSet.getString("nombre"),
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
			conexion.cerrarConexion();
		}
		
		return componentes;
	}
	
	public boolean update (ComponenteDTO marca_a_modificar)

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
			conexion.cerrarConexion();
		}
		return false;
	}

}
