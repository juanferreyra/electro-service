package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dto.MarcaDTO;
import persistencia.dao.MarcaDAO;
import presentacion.vista.VentanaABMMarcaProducto;

public class ControladorABMMarcaProducto implements ActionListener {

	private VentanaABMMarcaProducto ventanaABMMarcaProducto;
	private MarcaDAO marcaDAO = new MarcaDAO();

	public ControladorABMMarcaProducto(VentanaABMMarcaProducto ventanaABMProducto) {
		this.ventanaABMMarcaProducto = ventanaABMProducto;
		this.ventanaABMMarcaProducto.getEliminarItem_btn().addActionListener(this);
		this.ventanaABMMarcaProducto.getIngresar_btn().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.ventanaABMMarcaProducto.getEliminarItem_btn()) {
			if (this.ventanaABMMarcaProducto.getTablaMarcaProducto().getSelectedRow() >= 0) {
				int fila = this.ventanaABMMarcaProducto.getTablaMarcaProducto().getSelectedRow();
				String marca = (String) this.ventanaABMMarcaProducto.getTablaMarcaProducto().getValueAt(fila, 0);
				eliminarMarcaProducto(marca);
			}
		} else if (e.getSource() == this.ventanaABMMarcaProducto.getIngresar_btn()) {
			if (this.ventanaABMMarcaProducto.getDetalle_txt().getText() != "") {
				ingresarMarcaProducto(this.ventanaABMMarcaProducto.getDetalle_txt().getText());
				this.ventanaABMMarcaProducto.getDetalle_txt().setText("");
			} else {
				JOptionPane.showMessageDialog(this.ventanaABMMarcaProducto,
						"El detalle ingresado no es válido. Por favor, vuelva a intentarlo.");
			}
		}

	}

	private void eliminarMarcaProducto(String marca) {
		this.marcaDAO.delete(this.marcaDAO.findByDetalle(marca));
		cargarTablaMarcaProducto();
	}

	private void ingresarMarcaProducto(String marca) {
		this.marcaDAO.insert(new MarcaDTO(marca, 0));
		cargarTablaMarcaProducto();
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
