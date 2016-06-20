package presentacion.vista;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class FormatoTablaOrdenesTrabajo extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused,
			int row, int column) {

		setHorizontalAlignment(SwingConstants.CENTER);

		// Formato de colores de filas segun estado.
		aplicarColorFilas(table, value, selected, focused, row, column);

		super.getTableCellRendererComponent(table, value, selected, focused, row, column);
		return this;
	}

	private void aplicarColorFilas(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
		if (String.valueOf(table.getValueAt(row, 8)).equals("NUEVO")) {
			setBackground(Color.decode("#CEF6CE"));
		} else if (String.valueOf(table.getValueAt(row, 8)).equals("PRESUPUESTADO")) {
			setBackground(Color.decode("#F5F6CE"));
		} else if (String.valueOf(table.getValueAt(row, 8)).equals("RETIRADO")) {
			setBackground(Color.decode("#F6E3CE"));
		} else if (String.valueOf(table.getValueAt(row, 8)).equals("REPARADO")) {
			setBackground(Color.decode("#CEF6F5"));
		} else if (String.valueOf(table.getValueAt(row, 8)).equals("IRREPARABLE")) {
			setBackground(Color.decode("#CED8F6"));
		} else if (String.valueOf(table.getValueAt(row, 8)).equals("INFORMADO")) {
			setBackground(Color.decode("#E3CEF6"));
		} else if (String.valueOf(table.getValueAt(row, 8)).equals("ACEPTADO")) {
			setBackground(Color.decode("#81F7BE"));
		} else if (String.valueOf(table.getValueAt(row, 8)).equals("RECHAZADO")) {
			setBackground(Color.decode("#F5BCA9"));
		}

	}

}
