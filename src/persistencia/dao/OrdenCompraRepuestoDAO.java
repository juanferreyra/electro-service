package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dto.ItemRepuestoDTO;
import dto.OrdenCompraRepuestoDTO;
import persistencia.conexion.Conexion;

public class OrdenCompraRepuestoDAO {
	
	private static final String insert = "INSERT INTO orden_compra_repuestos (idorden_compra,idrepuesto,"
			+ "cantidad,cantidad_real,fecha_creacion,habilitado) VALUES (?,?,?,?,now(),true);";
	
	private static final String find = "SELECT ocr.id, ocr.idrepuesto, r.detalle, ocr.cantidad, "
			+ "ocr.cantidad_real  FROM orden_compra_repuestos ocr LEFT JOIN repuesto r ON "
			+ "(ocr.idrepuesto = r.id) WHERE ocr.idorden_compra = ? AND ocr.habilitado = TRUE;";
	
	private static final String findRepuesto = "SELECT * FROM orden_compra_repuestos WHERE idorden_compra = ? AND idrepuesto = ? AND habilitado=true;";
	
	private static final String updateCantidadReal = "UPDATE orden_compra_repuestos SET cantidad_real=? WHERE idorden_compra=? AND idrepuesto=?;";
	
	private Conexion conexion = Conexion.getConexion();

	public boolean insert(OrdenCompraRepuestoDTO OrdenCompraRepuestoDTO) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, OrdenCompraRepuestoDTO.getIdOrdenCompra());
			statement.setInt(2, OrdenCompraRepuestoDTO.getIdComponente());
			statement.setInt(3, OrdenCompraRepuestoDTO.getCantidad());
			statement.setFloat(4, OrdenCompraRepuestoDTO.getCantidad_real());

			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return false;
	}

	public ArrayList<ItemRepuestoDTO> readAll(int id) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<ItemRepuestoDTO> ordenCompraRepuesto = new ArrayList<ItemRepuestoDTO>();

		try {
			statement = conexion.getSQLConexion().prepareStatement(find);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				ordenCompraRepuesto.add(new ItemRepuestoDTO(
						resultSet.getInt("id"),
						resultSet.getInt("idrepuesto"),
						resultSet.getString("detalle"),
						resultSet.getInt("cantidad"),
						null,
						null));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return ordenCompraRepuesto;
	}
	
	public boolean updateCantReal(int idordenCompra, int idRepuesto, int cantidadReal) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(updateCantidadReal);
			statement.setInt(1, cantidadReal);
			statement.setInt(2, idordenCompra);
			statement.setInt(3, idRepuesto);

			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return false;
	}
	
	public OrdenCompraRepuestoDTO findRepuestoEnOrden(int idrepuesto, int idorden) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		OrdenCompraRepuestoDTO repuestoEnOrden = null;

		try {
			statement = conexion.getSQLConexion().prepareStatement(findRepuesto);
			statement.setInt(1, idorden);
			statement.setInt(2, idrepuesto);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				repuestoEnOrden = new OrdenCompraRepuestoDTO(
						resultSet.getInt("id"),
						resultSet.getInt("idorden_compra"),
						resultSet.getInt("idrepuesto"),
						resultSet.getInt("cantidad"),
						resultSet.getInt("cantidad_real"),
						resultSet.getDate("fecha_creacion"),
						true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return repuestoEnOrden;
	}
	
}
