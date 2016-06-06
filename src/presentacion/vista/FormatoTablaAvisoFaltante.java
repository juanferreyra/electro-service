package presentacion.vista;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import modelo.Stock;

public class FormatoTablaAvisoFaltante extends DefaultTableCellRenderer {

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
//		if (2) {//amarillo fuerte
//			setBackground(Color.decode("#D8F6CE"));
//		} else if (4) {//rojo fuerte
//			setBackground(Color.decode("#F6E3CE"));
//		} 

	}

}
