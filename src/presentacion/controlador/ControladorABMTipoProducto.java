package presentacion.controlador;

import presentacion.vista.VentanaABMTipoProducto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dto.TipoProductoDTO;
import persistencia.dao.TipoProductoDAO;

public class ControladorABMTipoProducto implements ActionListener {

	private VentanaABMTipoProducto ventanaABMTipoProducto;
	private TipoProductoDAO tipoProductoDAO = new TipoProductoDAO();
	private List<JTextField> txts;

	public ControladorABMTipoProducto(VentanaABMTipoProducto ventanaABMTipoProducto) {
		this.ventanaABMTipoProducto = ventanaABMTipoProducto;
		this.ventanaABMTipoProducto.getEliminarItem_btn().addActionListener(this);
		this.ventanaABMTipoProducto.getIngresar_btn().addActionListener(this);
		this.ventanaABMTipoProducto.getLimpiar_btn().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.ventanaABMTipoProducto.getEliminarItem_btn()) {
			if (this.ventanaABMTipoProducto.getTablaTipoProducto().getSelectedRow() >= 0) {
				int fila = this.ventanaABMTipoProducto.getTablaTipoProducto().getSelectedRow();
				String tipo = (String) this.ventanaABMTipoProducto.getTablaTipoProducto().getValueAt(fila, 0);
				eliminarTipoProducto(tipo);
				this.ventanaABMTipoProducto.getDetalle_txt().setText("");
				this.ventanaABMTipoProducto.getTablaTipoProducto().clearSelection();
			}
		} else if (e.getSource() == this.ventanaABMTipoProducto.getIngresar_btn()) {
			if (this.ventanaABMTipoProducto.getDetalle_txt().getText() != "") {

				// Si esta seleccionado, modificar
				if (this.ventanaABMTipoProducto.getTablaTipoProducto().getSelectedRow() >= 0) {

					int filaSeleccionada = this.ventanaABMTipoProducto.getTablaTipoProducto().getSelectedRow();

					TipoProductoDTO tipo = tipoProductoDAO.findByDetalle((String) this.ventanaABMTipoProducto
							.getTablaTipoProducto().getValueAt(filaSeleccionada, 0));
					tipo.setDetalle(this.ventanaABMTipoProducto.getDetalle_txt().getText());
					tipoProductoDAO.update(tipo);
					limpiartxts();
					cargarTablaTipoProducto();

				} else {
					ingresarTipoProducto(this.ventanaABMTipoProducto.getDetalle_txt().getText());

				}
				this.ventanaABMTipoProducto.getDetalle_txt().setText("");
				this.ventanaABMTipoProducto.getTablaTipoProducto().clearSelection();

			} else {
				JOptionPane.showMessageDialog(this.ventanaABMTipoProducto,
						"El detalle ingresado no es válido. Por favor, vuelva a intentarlo.");
			}

			this.ventanaABMTipoProducto.getDetalle_txt().setText("");
		} else if (e.getSource() == this.ventanaABMTipoProducto.getLimpiar_btn()) {
			this.ventanaABMTipoProducto.getDetalle_txt().setText("");
			this.ventanaABMTipoProducto.getTablaTipoProducto().clearSelection();
		}
	}

	private void limpiartxts() {

		for (JTextField jt : txts) {
			jt.setText("");

		}
	}

	private void eliminarTipoProducto(String tipo) {
		this.tipoProductoDAO.delete(this.tipoProductoDAO.findByDetalle(tipo));
		cargarTablaTipoProducto();
	}

	private void ingresarTipoProducto(String tipo) {
		this.tipoProductoDAO.insert(new TipoProductoDTO(tipo, 0));// HARDCODEO
																	// USUARIO
		cargarTablaTipoProducto();
	}

	public void inicializar() {

		this.ventanaABMTipoProducto.setVisible(true);

		this.txts = new ArrayList<JTextField>();

		txts.add(this.ventanaABMTipoProducto.getDetalle_txt());// 0

		cargarTablaTipoProducto();
		mouseClickedOnTable();

	}

	private void mouseClickedOnTable() {
		this.ventanaABMTipoProducto.getTablaTipoProducto().addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 1 es igual a boton izquierdo del mouse
				if (arg0.getButton() == 1) {
					cargartxts();
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}
		});
	}

	private void cargartxts() {
		int filaSeleccionada = this.ventanaABMTipoProducto.getTablaTipoProducto().getSelectedRow();

		this.txts.get(0)
				.setText((String) this.ventanaABMTipoProducto.getTablaTipoProducto().getValueAt(filaSeleccionada, 0));

	}

	public void cargarTablaTipoProducto() {
		// Consigo todas las marcas y genero las filas
		ArrayList<TipoProductoDTO> tipoProductos = (ArrayList<TipoProductoDTO>) this.tipoProductoDAO.readAll();
		ObtenerFilas(tipoProductos);
	}

	private void ObtenerFilas(ArrayList<TipoProductoDTO> tipoProductos) {
		limpiarTablaTipoProducto();
		for (int i = 0; i <= tipoProductos.size() - 1; i++) {

			this.cargarFila(i, tipoProductos.get(i).getDetalle());
		}
	}

	private void cargarFila(int fila, String detalle) {

		String[] tipo = { detalle };

		// Agrego fila
		((DefaultTableModel) this.ventanaABMTipoProducto.getTablaTipoProducto().getModel()).addRow(tipo);

		// Establezco que no se pueda editar pero si seleccionar una fila
		((DefaultTableModel) this.ventanaABMTipoProducto.getTablaTipoProducto().getModel()).isCellEditable(fila, 0);

	}

	public void limpiarTablaTipoProducto() {

		int largo = ((DefaultTableModel) this.ventanaABMTipoProducto.getTablaTipoProducto().getModel()).getRowCount();

		for (int i = largo - 1; i >= 0; i--) {
			((DefaultTableModel) this.ventanaABMTipoProducto.getTablaTipoProducto().getModel()).removeRow(i);
		}

	}

	// public static void main(String[] args) {
	//
	// VentanaABMTipoProducto abm = new VentanaABMTipoProducto();
	// ControladorABMTipoProducto c = new ControladorABMTipoProducto(abm);
	// c.inicializar();
	//
	// }

}
