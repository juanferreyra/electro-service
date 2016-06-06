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
//		if () {
//			setBackground(Color.decode("#D8F6CE"));
//		} else if (String.valueOf(table.getValueAt(row, 8)).equals("PRESUPUESTADO")) {
//			setBackground(Color.decode("#F6E3CE"));
//		} else if (String.valueOf(table.getValueAt(row, 8)).equals("RETIRADO")) {
//			setBackground(Color.decode("#F5A9A9"));
//		} else if (String.valueOf(table.getValueAt(row, 8)).equals("REPARADO")) {
		// setBackground(Color.decode("#CEF6F5"));
		// }

	}

}
