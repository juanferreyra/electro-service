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

		int existencia = Integer.valueOf((String) table.getValueAt(row, 2));
		int reservado = Integer.valueOf((String) table.getValueAt(row, 3));
		int pedido = Integer.valueOf((String) table.getValueAt(row, 4));
		int minimo = Integer.valueOf((String) table.getValueAt(row, 6));

		int resultado = existencia - (reservado + pedido);
		int resultado2 = (existencia - reservado);
//		int resultado3= re
		
		//amarillo tenue.#F5F6CE
		//amarillo fuerte #F3F781
		// rojo tenue #F6E3CE
		// rojo fuerte. #F5BCA9
		
		if (resultado > minimo) {// 
			// 0 ok
			// 1
			// amarillo
			// claro
			// 2
			// amarillo
			// fuerte
			// 3
			// rojo
			// claro
			// 4
			// rojo
			// fuerte
			setBackground(Color.decode("#BCF5A9"));
		}
		// else if (1) {
		// setBackground(Color.decode("#F6E3CE"));
		// } else if (2) {
		// setBackground(Color.decode("#F5A9A9"));
		// } else if (3) {
		// setBackground(Color.decode("#F5A9A9"));
		// } else if (4) {
		// setBackground(Color.decode("#F5A9A9"));
		// }
	}

}
