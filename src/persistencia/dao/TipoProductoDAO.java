package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.conexion.Conexion;
import dto.TipoProductoDTO;

public class TipoProductoDAO
{
		
		private static final String insert = "INSERT INTO tipo_producto ("
				+ "`detalle`, `fecha_creacion`, `idusuario`, `habilitado`)"
				+ " VALUES (?, now(), ?, true); ";
		private static final String delete = "UPDATE tipo_producto SET habilitado='0' WHERE id = ?";
		private static final String readall = "SELECT id, detalle, idusuario FROM tipo_producto WHERE habilitado = true;";
		private static final String update = "UPDATE tipo_producto SET detalle = ?  WHERE id = ? ;";
		private static final Conexion conexion = Conexion.getConexion();
		
		public boolean insert(TipoProductoDTO tipo_producto)
		{
			PreparedStatement statement;
			try 
			{
				statement = conexion.getSQLConexion().prepareStatement(insert);
				statement.setString(1, tipo_producto.getDetalle());
				statement.setInt(2, tipo_producto.getIdUsuario());
				
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
		
		public boolean delete(TipoProductoDTO tipoprodu_a_eliminar)
		{
			PreparedStatement statement;
			int chequeoUpdate=0;
			try 
			{
				statement = conexion.getSQLConexion().prepareStatement(delete);
				statement.setString(1, Integer.toString(tipoprodu_a_eliminar.getId()));
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
		
		public List<TipoProductoDTO> readAll()
		{
			PreparedStatement statement;
			ResultSet resultSet; //Guarda el resultado de la query
			ArrayList<TipoProductoDTO> localidades = new ArrayList<TipoProductoDTO>();
			try 
			{
				statement = conexion.getSQLConexion().prepareStatement(readall);
				resultSet = statement.executeQuery();
				
				while(resultSet.next())
				{
					localidades.add(new TipoProductoDTO(resultSet.getInt("id"), resultSet.getString("detalle"),resultSet.getInt("idusuario")));
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			finally //Se ejecuta siempre
			{
				conexion.cerrarConexion();
			}
			
			return localidades;
		}
		
		public boolean update (TipoProductoDTO tipoprodu_a_modificar)

		{
			PreparedStatement statement;
			try 
			{
				statement = conexion.getSQLConexion().prepareStatement(update);

				statement.setString(1, tipoprodu_a_modificar.getDetalle());
				statement.setInt(2, tipoprodu_a_modificar.getId());

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
