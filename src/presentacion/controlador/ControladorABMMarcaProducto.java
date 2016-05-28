package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import dto.MarcaDTO;
import persistencia.dao.MarcaDAO;
import presentacion.vista.VentanaABMMarcaProducto;

public class ControladorABMMarcaProducto implements ActionListener {

	private VentanaABMMarcaProducto ventanaABMMarcaProducto;
	private MarcaDAO marcaDAO = new MarcaDAO();

	public ControladorABMMarcaProducto(VentanaABMMarcaProducto ventanaABMProducto) {
		this.ventanaABMMarcaProducto = ventanaABMProducto;
		this.ventanaABMMarcaProducto.getCancelar_btn().addActionListener(this);
		this.ventanaABMMarcaProducto.getEliminarItem_btn().addActionListener(this);
		this.ventanaABMMarcaProducto.getGuardar_btn().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.ventanaABMMarcaProducto.getCancelar_btn()) {
			this.ventanaABMMarcaProducto.dispose();
		} else if (e.getSource() == this.ventanaABMMarcaProducto.getEliminarItem_btn()) {

		} else if (e.getSource() == this.ventanaABMMarcaProducto.getGuardar_btn()) {

		}

	}

	private void inicializar() {

		this.ventanaABMMarcaProducto.setVisible(true);

		cargarTablaMarcaProducto();

	}

	public void cargarTablaMarcaProducto() {
		// Consigo todas las marcas y genero las filas
		ArrayList<MarcaDTO> marcas = (ArrayList<MarcaDTO>) this.marcaDAO.readAll();
		ObtenerFilas(marcas);
	}

	private void ObtenerFilas(ArrayList<MarcaDTO> marcas) {
		limpiarTablaMarcaProducto();
		for (int i = 0; i <= marcas.size() - 1; i++) {

			this.cargarFila(i, marcas.get(i).getDetalle());
		}
	}

	private void cargarFila(int fila, String detalle) {

		String[] marca = { detalle };

		// Agrego fila
		((DefaultTableModel) this.ventanaABMMarcaProducto.getTablaMarcaProducto().getModel()).addRow(marca);

		// Establezco que no se pueda editar pero si seleccionar una fila
		((DefaultTableModel) this.ventanaABMMarcaProducto.getTablaMarcaProducto().getModel()).isCellEditable(fila, 0);

	}

	public void limpiarTablaMarcaProducto() {

		int largo = ((DefaultTableModel) this.ventanaABMMarcaProducto.getTablaMarcaProducto().getModel()).getRowCount();

		for (int i = largo - 1; i >= 0; i--) {
			((DefaultTableModel) this.ventanaABMMarcaProducto.getTablaMarcaProducto().getModel()).removeRow(i);
		}

	}

	public static void main(String[] args) {

		VentanaABMMarcaProducto abm = new VentanaABMMarcaProducto();
		ControladorABMMarcaProducto c = new ControladorABMMarcaProducto(abm);
		c.inicializar();

	}
}
