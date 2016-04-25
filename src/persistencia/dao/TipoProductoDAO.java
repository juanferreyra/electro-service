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
	private static final String insert = "INSERT INTO tipo_producto(detalle, fecha_creacion, idusuario, habilitado) VALUES( ?, now(), ?,'1')";
	private static final String delete = " ";
	private static final String readall = "SELECT * FROM tipo_producto";
	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(TipoProductoDTO persona)
	{
            return true;
	}
	
	public boolean delete(TipoProductoDTO persona_a_eliminar)
	{
            return true;
	}
        
        public void readAll()
        {
            
        }
}
