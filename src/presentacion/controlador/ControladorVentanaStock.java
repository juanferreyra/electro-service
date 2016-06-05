package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import dto.UsuarioDTO;
import presentacion.vista.VentanaOrdenCompra;
import presentacion.vista.VentanaStock;

public class ControladorVentanaStock implements ActionListener {

	private VentanaStock ventanaStock;

	public ControladorVentanaStock(VentanaStock ventanaStock) {
		this.ventanaStock = ventanaStock;
		this.ventanaStock.getGenerarOC_btn().addActionListener(this);

	}

	public void inicializar() {
		// Crear todos los news de los datos
		this.cargar_tablaStock();
	}

	private void cargar_tablaStock() {
		// Consigo todos los datos de stock y genero las filas
		// ArrayList<datosStock> datosStock = datosStockDAO.readAll();
		// ObtenerFilas(datos);
		//

	}

	// private void ObtenerFilas(ArrayList<datosStock> datos) {
	// limpiar_tablaStock();
	// for (int i = 0; i <= datos.size() - 1; i++) {
	// this.cargarFila(i, marca, repuesto, existencia, reservado, pedido,
	// disponible);
	// }
	// }

	private void cargarFila(int fila, String marca, String repuesto, int existencia, int reservado, int pedido,
			int disponible) {

		Object[] dato = new Object[6];
		dato[0] = marca;
		dato[1] = repuesto;
		dato[2] = existencia;
		dato[3] = reservado;
		dato[4] = pedido;
		dato[5] = disponible;

		// Establezco que no se pueda editar pero si seleccionar una fila
		((DefaultTableModel) this.ventanaStock.getModelo()).isCellEditable(fila, 0);
		((DefaultTableModel) this.ventanaStock.getModelo()).isCellEditable(fila, 1);
		((DefaultTableModel) this.ventanaStock.getModelo()).isCellEditable(fila, 2);
		((DefaultTableModel) this.ventanaStock.getModelo()).isCellEditable(fila, 3);
		((DefaultTableModel) this.ventanaStock.getModelo()).isCellEditable(fila, 4);
		((DefaultTableModel) this.ventanaStock.getModelo()).isCellEditable(fila, 5);

	}

	public void limpiar_tablaStock() {

		int largo = ((DefaultTableModel) this.ventanaStock.getModelo()).getRowCount();

		for (int i = largo - 1; i >= 0; i--) {
			((DefaultTableModel) this.ventanaStock.getModelo()).removeRow(i);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.ventanaStock.getGenerarOC_btn()) {
			//ControladorOrdenCompra controladorOC = new ControladorOrdenCompra(new VentanaOrdenCompra(),
					//new UsuarioDTO(0, null, null, null, 0));// Ingresar el
															// usuario correcto
															// !!!!!
			//controladorOC.inicializar();
		}

	}

}
