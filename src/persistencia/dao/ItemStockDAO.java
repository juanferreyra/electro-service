package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.InsumoStockDTO;
import dto.RepuestoDTO;
import persistencia.conexion.Conexion;

public class ItemStockDAO {
	private static final String insert = "INSERT INTO itemStock (" + "`InsumoId`, `Existencias`, `habilitado`)"
			+ " VALUES (?, ?, 1); ";
	private static final String delete = "UPDATE itemStock SET habilitado='0' WHERE Id = ?";
	private static final String readall = "SELECT st.Id,mar.detalle AS Marca, st.InsumoId, re.detalle AS Nombre, st.Existencias, re.stock_minimo AS Minimo FROM itemStock st INNER JOIN repuesto re INNER JOIN  marca_producto mar ON  st.InsumoId = re.id and re.idmarca = mar.id WHERE st.habilitado = true;";
	private static final String update = "update itemStock SET Existencias = Existencias + ? WHERE Id = ? ;";
	private static final String find = "SELECT st.Id,mar.detalle AS Marca, st.InsumoId, re.detalle AS Nombre, st.Existencias FROM itemStock st INNER JOIN repuesto re INNER JOIN  marca_producto mar ON  st.InsumoId = re.id and re.idmarca = mar.id WHERE st.habilitado = true AND Id = ?;";
	private static final String INSUMOSAUSAR = "SELECT idrepuesto,  sum(cantidad) as cant "
			+ "FROM presupuesto_repuestos " + "WHERE idpresupuesto IN (SELECT id " + "FROM presupuesto "
			+ "WHERE idingreso IN (SELECT id " + "FROM ingreso " + "WHERE estado = 5)" + " ) GROUP BY idrepuesto;";

	private static final String INSUMOSAINGRESAR = "SELECT idrepuesto, sum(cantidad) AS cant "
			+ "FROM orden_compra_repuestos " + "WHERE idorden_compra IN (SELECT id " + "FROM orden_compra "
			+ "WHERE estado = 'NUEVA')GROUP BY idrepuesto ; ";

	private static Conexion conexion = Conexion.getConexion();

	public boolean insert(RepuestoDTO nuevoComponente) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setLong(1, nuevoComponente.getId());
			statement.setInt(2, 0);

			if (statement.executeUpdate() > 0) { // Si se ejecuta devuelvo true
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // Se ejecuta siempre
			Conexion.cerrarConexion();
		}
		return false;
	}

	public boolean delete(int insumo_a_eliminar) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setLong(1, insumo_a_eliminar);
			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0) // Si se ejecuto devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // Se ejecuta siempre
			Conexion.cerrarConexion();
		}
		return false;
	}

	public ArrayList<InsumoStockDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<InsumoStockDTO> insumosStock = new ArrayList<InsumoStockDTO>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				// st.Id,mar.detalle AS Marca, st.InsumoId, re.detalle AS
				// Nombre, st.Existencias
				insumosStock.add(new InsumoStockDTO(resultSet.getInt("Id"), resultSet.getLong("InsumoID"),
						resultSet.getString("Marca"), resultSet.getString("Nombre"), resultSet.getInt("Existencias"),resultSet.getInt("Minimo")));
			}
		} catch (SQLException e) {
			System.out.println("hubo un error");
			e.printStackTrace();
		} finally { // Se ejecuta siempre
			Conexion.cerrarConexion();
		}

		return insumosStock;
	}

	public ArrayList<InsumoStockDTO> CargarInsumosReservados(ArrayList<InsumoStockDTO> insumosStock) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		// ArrayList<InsumoStockDTO> insumosStock = new
		// ArrayList<InsumoStockDTO>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(INSUMOSAUSAR);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				for (InsumoStockDTO i : insumosStock) {
					if (resultSet.getInt("idrepuesto") == i.getInsumoID()) {
						i.setaUsar(resultSet.getInt("cant"));
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("hubo un error");
			e.printStackTrace();
		} finally { // Se ejecuta siempre
			Conexion.cerrarConexion();
		}

		return insumosStock;
	}

	public ArrayList<InsumoStockDTO> cargarInsumosPedidos(ArrayList<InsumoStockDTO> insumosStock) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		// ArrayList<InsumoStockDTO> insumosStock = new
		// ArrayList<InsumoStockDTO>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(INSUMOSAINGRESAR);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				for (InsumoStockDTO i : insumosStock) {
					if (resultSet.getInt("idrepuesto") == i.getInsumoID()) {
						i.setSolicitada(resultSet.getInt("cant"));
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("hubo un error");
			e.printStackTrace();
		} finally { // Se ejecuta siempre
			Conexion.cerrarConexion();
		}

		return insumosStock;
	}

	public boolean update(long insumoID, int variacion) { // Toma el ID del
															// insumo y le suma
															// directamente la
															// variacion.
															// Por ende si le
															// pasas una
															// variacion
															// negativa se lo
															// resta al total.
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setLong(1,variacion);
			statement.setLong(2, insumoID);

			if (statement.executeUpdate() > 0) // Si se ejecut� devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // Se ejecuta siempre
			Conexion.cerrarConexion();
		}
		return false;
	}

	public InsumoStockDTO find(long insumoID) {

		conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet;
		InsumoStockDTO insumoStock = null;

		try {
			statement = conexion.getSQLConexion().prepareStatement(find);
			statement.setLong(1, insumoID);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				insumoStock = (new InsumoStockDTO(resultSet.getInt("Id"), resultSet.getLong("InsumoID"),
						resultSet.getString("Marca"), resultSet.getString("Nombre"), resultSet.getInt("Existencias"),resultSet.getInt("Minimo")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return insumoStock;
	}

}
