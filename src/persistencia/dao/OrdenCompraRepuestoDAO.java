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
			+ "cantidad,precio_unitario,fecha_creacion,habilitado) VALUES (?,?,?,?,now(),true);";
	
	private static final String find = "SELECT ocr.id, ocr.idrepuesto, r.detalle, ocr.cantidad, r.precio, "
			+ "ocr.cantidad * r.precio AS totalFROM orden_compra_repuestos ocr LEFT JOIN repuesto r ON "
			+ "(ocr.idrepuesto = r.id) WHERE ocr.idorden_compra = ? AND ocr.habilitado = TRUE;";
	
	private Conexion conexion = Conexion.getConexion();

	public boolean insert(OrdenCompraRepuestoDTO OrdenCompraRepuestoDTO) {
		conexion = Conexion.getConexion();
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, OrdenCompraRepuestoDTO.getIdOrdenCompra());
			statement.setInt(2, OrdenCompraRepuestoDTO.getIdComponente());
			statement.setInt(3, OrdenCompraRepuestoDTO.getCantidad());
			statement.setFloat(4, OrdenCompraRepuestoDTO.getPrecio_unitario());

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
						resultSet.getFloat("precio"),
						resultSet.getFloat("total")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return ordenCompraRepuesto;
	}

}
