package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.MarcaDTO;
import dto.RepuestoDTO;
import persistencia.conexion.Conexion;

public class RepuestoDAO {
	
	private static final String insert = "INSERT INTO repuesto ("
			+ "detalle, precio, stock_minimo, idmarca, fecha_creacion, idusuario, habilitado)"
			+ " VALUES (?, ?, ?, ?, now(), ?, true); ";
	
	private static final String delete = "UPDATE repuesto SET habilitado='0' WHERE id = ?";
	
	private static final String readall = "SELECT * FROM repuesto WHERE habilitado = true;";
	
	private static final String update = "UPDATE repuesto SET detalle = ?, precio = ?, stock_minimo = ?, idmarca = ? WHERE  id = ?;";
	
	private static final String findXDetalle = "SELECT * FROM repuesto WHERE detalle = ? ;";
	
	private static final String findXid = "SELECT * FROM repuesto WHERE id = ? ;";
	
	private static final Conexion conexion = Conexion.getConexion();
	
	private static final String insert1 = "INSERT INTO repuesto ("
			+ "`nombre`, `detalle`, `precio`, `stock_minimo`, idmarca, now(),`idusuario`,true)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?); ";
	
	public boolean agregar(RepuestoDTO componente){
		
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(insert1);
			statement.setInt(1,componente.getId());
			statement.setString(2,componente.getDetalle());
			statement.setFloat(3, componente.getPrecioUnitario());
			statement.setInt(4, componente.getIdmarca());
			statement.setInt(5,componente.getStockMinimo());
			;
	
			
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
	
	public boolean insert(RepuestoDTO componente)
	{
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(insert);
			
			statement.setString(1,componente.getDetalle());
			statement.setFloat(2,componente.getPrecioUnitario());
			statement.setInt(3, componente.getStockMinimo());
			statement.setInt(4, componente.getIdmarca());
			statement.setInt(5, 0);
			
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
	
	public boolean delete(int id_componente_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(id_componente_a_eliminar));
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
						resultSet.getInt("stock_minimo"),resultSet.getInt("idmarca"),resultSet.getDate("fecha_creacion"),
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
	
	public boolean update(RepuestoDTO componente_a_modificar)

	{
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);

			statement.setString(1, componente_a_modificar.getDetalle());
			statement.setFloat(2, componente_a_modificar.getPrecioUnitario());
			statement.setInt(3, componente_a_modificar.getStockMinimo());
			statement.setInt(4, componente_a_modificar.getIdmarca());
			statement.setInt(5, componente_a_modificar.getId());

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
			statement = conexion.getSQLConexion().prepareStatement(findXDetalle);
			statement.setString(1, aBuscar);
			resultSet = statement.executeQuery();

			
			while(resultSet.next())
			{
				componente = new RepuestoDTO(
						resultSet.getInt("id"),
						resultSet.getString("detalle"),
						resultSet.getFloat("precio"),
						resultSet.getInt("stock_minimo"),
						resultSet.getInt("idmarca"),
						resultSet.getDate("fecha_creacion"),	
						resultSet.getInt("idusuario"),
						resultSet.getInt("habilitado"));
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
	
	
	public int findXDetalle(String aBuscar)
		{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		int  id = 0;
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(findXDetalle);
			statement.setString(1, aBuscar);
			resultSet = statement.executeQuery();

			
			while(resultSet.next())
			{
				id = resultSet.getInt("idmarca");
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
		System.out.println(id);
		return id;
	}
	
	
	public ArrayList<RepuestoDTO> readAllInMarca(ArrayList<MarcaDTO> marcas)
	{
		if(marcas.size()==0)
		{
			return new ArrayList<RepuestoDTO>();
		}
		
		String readAllInMarcas = "SELECT * FROM repuesto WHERE habilitado = true ";
		
		for (int i = 0; i < marcas.size(); i++) {
			if(i==0) {
				readAllInMarcas += " AND idmarca = "+marcas.get(i).getId();
			}else {
				readAllInMarcas += " OR idmarca = "+marcas.get(i).getId();
			}
		}
		
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<RepuestoDTO> componentes = new ArrayList<RepuestoDTO>();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readAllInMarcas);
			resultSet = statement.executeQuery();

			
			while(resultSet.next())
			{
				componentes.add(new RepuestoDTO(resultSet.getInt("id"),
						resultSet.getString("detalle"),resultSet.getFloat("precio"),
						resultSet.getInt("stock_minimo"),resultSet.getInt("idmarca"),
						resultSet.getDate("fecha_creacion"),resultSet.getInt("idusuario"),
						resultSet.getInt("habilitado")));
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
	
	public RepuestoDTO findXid(int idrepuesto)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		RepuestoDTO componente = null;
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(findXid);
			statement.setInt(1, idrepuesto);
			resultSet = statement.executeQuery();

			
			while(resultSet.next())
			{
				componente = new RepuestoDTO(resultSet.getInt("id"),
						resultSet.getString("detalle"),resultSet.getFloat("precio"),
						resultSet.getInt("stock_minimo"), resultSet.getInt("idmarca"),resultSet.getDate("fecha_creacion"),
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
	public String BuscarDetalleXid(int idrepuesto)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		String detalle = "";
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(findXid);
			statement.setInt(1, idrepuesto);
			resultSet = statement.executeQuery();

			
			while(resultSet.next())
			{
				detalle = resultSet.getString("detalle");
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
		
		return detalle;
	}
	
}