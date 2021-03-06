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
	private UsuarioDTO usuarioLogueado;

	public ControladorVentanaStock(VentanaStock ventanaStock, UsuarioDTO usuario) {
		this.ventanaStock = ventanaStock;
		this.ventanaStock.getGenerarOC_btn().addActionListener(this);
		this.usuarioLogueado = usuario;
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

		Object[] dato = new Object[7];
		dato[0] = marca;
		dato[1] = repuesto;
		dato[2] = existencia;
		dato[3] = reservado;
		dato[4] = pedido;
		dato[5] = disponible;
		dato[6] = minimo;
//********************************************************************		
		//int resultado = existencia - reservado;
		//TODO Aca estan las condiciones.
//		if ((resultado<1) && (resultado+pedido)<minimo){
//			//ROJO FUERTE
//		}else if((resultado<1) && (resultado+pedido)>minimo){
//			//ROJO TENUE
//		}else if((resultado<minimo) && (resultado+pedido)<minimo){
//			//AMARILLO FUERTE
//		}else if((resultado<minimo) && (resultado+pedido)>minimo){
//			//AMARILLO TENUE
//		}else{
//			//NORMAL
//		}
//*********************************************************************
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
			VentanaOrdenCompra ventanaOdenCompra = new VentanaOrdenCompra();
			ControladorOrdenCompra c = new ControladorOrdenCompra(ventanaOdenCompra, usuarioLogueado);
			c.inicializar();
		}
	}
}
