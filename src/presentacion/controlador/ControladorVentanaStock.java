package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import dto.InsumoStockDTO;
import dto.UsuarioDTO;
import modelo.Stock;
import presentacion.vista.VentanaOrdenCompra;
import presentacion.vista.VentanaStock;

public class ControladorVentanaStock implements ActionListener {

	private VentanaStock ventanaStock;
	private Stock stock;

	public ControladorVentanaStock(VentanaStock ventanaStock) {
		this.ventanaStock = ventanaStock;
		this.ventanaStock.getGenerarOC_btn().addActionListener(this);

	}

	public void inicializar() {
		// Crear todos los news de los datos
		this.stock = new Stock();
		this.cargar_tablaStock();

	}

	private void cargar_tablaStock() {
		// Consigo todos los datos de stock y genero las filas
		ArrayList<InsumoStockDTO> datosStock = new ArrayList<InsumoStockDTO>();
		
		datosStock = stock.obtenerStock();
		ObtenerFilas(datosStock);

	}

	private void ObtenerFilas(ArrayList<InsumoStockDTO> datos) {
		limpiar_tablaStock();
		for (int i = 0; i <= datos.size() - 1; i++) {
			this.cargarFila(i, datos.get(i).getMarca(), datos.get(i).getNombre(), datos.get(i).getExistencias(),
					datos.get(i).getaUsar(), datos.get(i).getSolicitada(), datos.get(i).getRestante(), datos.get(i).getMinimo());
		}
	}

	private void cargarFila(int fila, String marca, String repuesto, int existencia, int reservado, int pedido,
			int disponible, int minimo) {

		Object[] dato = new Object[6];
		dato[0] = marca;
		dato[1] = repuesto;
		dato[2] = existencia;
		dato[3] = reservado;
		dato[4] = pedido;
		dato[5] = disponible;
		dato[6] = minimo;
		
		((DefaultTableModel) this.ventanaStock.getModelo()).addRow(dato);
		
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
			// ControladorOrdenCompra controladorOC = new
			// ControladorOrdenCompra(new VentanaOrdenCompra(),
			// new UsuarioDTO(0, null, null, null, 0));// Ingresar el
			// usuario correcto
			// !!!!!
			// controladorOC.inicializar();
		}

	}

}
