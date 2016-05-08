package presentacion.vista;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class FormatoTablaOrdenesTrabajo extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused,
			int row, int column) {
		// Formato "SI"/"NO" para envio.
		if (String.valueOf(table.getValueAt(row, 5)).equals("true")) {
			table.setValueAt("SI", row, 5);
		} else if (String.valueOf(table.getValueAt(row, 5)).equals("false")) {
			table.setValueAt("NO", row, 5);
		}
		// Formato de colores de filas segun estado.
		if (String.valueOf(table.getValueAt(row, 8)).equals("NUEVO")) {
			setBackground(Color.green);
		} else if (String.valueOf(table.getValueAt(row, 8)).equals("PRESUPUESTADO")) {
			setBackground(Color.cyan);
		}
		super.getTableCellRendererComponent(table, value, selected, focused, row, column);
		return this;
	}

}
