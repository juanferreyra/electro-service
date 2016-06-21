package presentacion.vista;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import modelo.Stock;

public class FormatoTablaStock extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;
	private Stock stock = new Stock();

	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused,
			int row, int column) {

		// Formato de colores de filas segun estado.
		aplicarColorFilas(table, value, selected, focused, row, column);

		super.getTableCellRendererComponent(table, value, selected, focused, row, column);
		return this;
	}

	private void aplicarColorFilas(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
		int existencia = (int)table.getValueAt(row, 2);
		int reservado = (int) table.getValueAt(row, 3);
		int pedido = (int) table.getValueAt(row, 4);
		int minimo = (int) table.getValueAt(row, 6);

		int resultado = existencia - reservado;

		// COLORES
		// amarillo tenue.#F5F6CE
		// amarillo fuerte #F3F781
		// rojo tenue #F6E3CE
		// rojo fuerte. #F5BCA9

		if ((resultado < 1) && (resultado + pedido) < minimo) {
			setBackground(Color.decode("#F5BCA9"));// Rojo fuerte
		} else if ((resultado < 1) && (resultado + pedido) > minimo) {
			setBackground(Color.decode("#F6E3CE"));// Rojo Claro
		} else if ((resultado < minimo) && (resultado + pedido) < minimo) {
			setBackground(Color.decode("#F3F781"));// Amarillo fuerte
		} else if ((resultado < minimo) && (resultado + pedido) > minimo) {
			setBackground(Color.decode("#F5F6CE"));// amarillo claro
		} 
		else {
			setBackground(Color.white);// Blanco
		}

	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

}
