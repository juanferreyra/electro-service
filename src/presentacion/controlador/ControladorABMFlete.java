package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dto.FleteDTO;
import persistencia.dao.FleteDAO;
import presentacion.vista.VentanaABMFlete;

public class ControladorABMFlete implements ActionListener {

	private VentanaABMFlete ventanaABMFlete;
	private FleteDAO fleteDAO = new FleteDAO();
	private List<Object> txts;

	public ControladorABMFlete(VentanaABMFlete ventanaABMFlete) {
		this.ventanaABMFlete = ventanaABMFlete;
		this.ventanaABMFlete.getEliminarItem_btn().addActionListener(this);
		this.ventanaABMFlete.getGuardar_btn().addActionListener(this);
		this.ventanaABMFlete.getLimpiar_btn().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.ventanaABMFlete.getEliminarItem_btn()) {
			if (this.ventanaABMFlete.getTablaFlete().getSelectedRow() >= 0) {
				int fila = this.ventanaABMFlete.getTablaFlete().getSelectedRow();
				eliminarFlete(fila);
				this.limpiartxts();
				this.ventanaABMFlete.getTablaFlete().clearSelection();

			}
		} else if (e.getSource() == this.ventanaABMFlete.getGuardar_btn()) {
			if (!this.ventanaABMFlete.getNombre_txf().getText().equals("")
					&& !this.ventanaABMFlete.getDocumento_txf().equals("")
					&& !this.ventanaABMFlete.getModeloTransporte_txf().equals("")
					&& !this.ventanaABMFlete.getPatenteTransporte_txf().equals("")
					&& !this.ventanaABMFlete.getTelefono_txf().equals("")
					&& this.ventanaABMFlete.getDateChooser().getDate() != null) {
				// Si esta seleccionado, modificar
				if (this.ventanaABMFlete.getTablaFlete().getSelectedRow() >= 0) {

					int filaSeleccionada = this.ventanaABMFlete.getTablaFlete().getSelectedRow();

					FleteDTO flete = fleteDAO
							.findPorNrodoc((int) this.ventanaABMFlete.getTablaFlete().getValueAt(filaSeleccionada, 1));

					if (isTxtsValidos()) {
						flete.setNombre((this.ventanaABMFlete.getNombre_txf().getText()));
						flete.setNroDoc(Integer.parseInt(this.ventanaABMFlete.getDocumento_txf().getText()));
						flete.setModelo(this.ventanaABMFlete.getModeloTransporte_txf().getText());
						flete.setPatente(this.ventanaABMFlete.getPatenteTransporte_txf().getText());
						flete.setTelefono(this.ventanaABMFlete.getTelefono_txf().getText());
						flete.setVtoLicencia(this.ventanaABMFlete.getDateChooser().getDate());

						fleteDAO.update(flete);
						cargarTablaFlete();
						this.limpiartxts();
						this.ventanaABMFlete.getTablaFlete().clearSelection();
					}

				} else {
					if (isTxtsValidos()) {

						ingresarFlete(this.ventanaABMFlete.getNombre_txf().getText(),
								Integer.parseInt(this.ventanaABMFlete.getDocumento_txf().getText()),
								this.ventanaABMFlete.getModeloTransporte_txf().getText(),
								this.ventanaABMFlete.getPatenteTransporte_txf().getText(),
								this.ventanaABMFlete.getTelefono_txf().getText(),
								this.ventanaABMFlete.getDateChooser().getDate());
						this.limpiartxts();
						this.ventanaABMFlete.getTablaFlete().clearSelection();
					}
				}

			} else {// Si existe algun campo vacio
				JOptionPane.showMessageDialog(this.ventanaABMFlete,
						"Disculpe, has ingresado campos vacios. Complete los campos faltantes.");
			}
		} else if (e.getSource() == this.ventanaABMFlete.getLimpiar_btn()) {
			limpiartxts();
			this.ventanaABMFlete.getTablaFlete().clearSelection();
		}
	}

	private boolean isTxtsValidos() {

		boolean ret = true;

		if (!soloNumeros(this.ventanaABMFlete.getDocumento_txf().getText())) { // valida
																				// documento

			JOptionPane.showMessageDialog(this.ventanaABMFlete,
					"Disculpe, has ingresado un nro de documento incorrecto.");
			return false;

		} else {

			ret = ret && soloNumeros(this.ventanaABMFlete.getDocumento_txf().getText());
		}

		if (!soloNumeros(this.ventanaABMFlete.getTelefono_txf().getText())) { // valida
																				// telefono

			JOptionPane.showMessageDialog(this.ventanaABMFlete,
					"Disculpe, has ingresado un nro de telefono incorrecto.");
			return false;

		} else {

			ret = ret && soloNumeros(this.ventanaABMFlete.getTelefono_txf().getText());
		}

		return ret;

	}

	private boolean soloNumeros(String texto) {
		try {
			Integer.parseInt(texto);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void ingresarFlete(String nombre, int documento, String modelo, String patente, String telefono,
			Date vto_licencia) {
		this.fleteDAO.insert(new FleteDTO(documento, nombre, modelo, patente, telefono, vto_licencia));
		cargarTablaFlete();
	}

	private void cargarTablaFlete() {
		// Consigo todos los fletes y genero las filas
		ArrayList<FleteDTO> fletes = (ArrayList<FleteDTO>) this.fleteDAO.readAll();
		ObtenerFilas(fletes);
	}

	private void limpiartxts() {
		for (Object jt : txts) {
			if (jt.getClass() == JTextField.class) {

				((JTextField) jt).setText("");
			} else {
				((JDateChooser) jt).setDate(null);
			}

		}
	}

	private void eliminarFlete(int fila) {
		FleteDTO flete = fleteDAO.findPorNrodoc((int) this.ventanaABMFlete.getTablaFlete().getValueAt(fila, 1));

		flete.setNombre((this.ventanaABMFlete.getNombre_txf().getText()));
		flete.setNroDoc(Integer.parseInt(this.ventanaABMFlete.getDocumento_txf().getText()));
		flete.setModelo(this.ventanaABMFlete.getModeloTransporte_txf().getText());
		flete.setPatente(this.ventanaABMFlete.getPatenteTransporte_txf().getText());
		flete.setTelefono(this.ventanaABMFlete.getTelefono_txf().getText());
		flete.setVtoLicencia(this.ventanaABMFlete.getDateChooser().getDate());

		this.fleteDAO.delete(flete);
		cargarTablaFlete();
	}

	public void inicializar() {

		this.ventanaABMFlete.setVisible(true);

		txts = new ArrayList<Object>();

		txts.add(this.ventanaABMFlete.getNombre_txf());// 0
		txts.add(this.ventanaABMFlete.getDocumento_txf());// 1
		txts.add(this.ventanaABMFlete.getModeloTransporte_txf());// 2
		txts.add(this.ventanaABMFlete.getPatenteTransporte_txf());// 3
		txts.add(this.ventanaABMFlete.getTelefono_txf());// 4
		txts.add(this.ventanaABMFlete.getDateChooser());// 5

		cargarTablaFlete();
		mouseClickedOnTable();

	}

	private void mouseClickedOnTable() {
		this.ventanaABMFlete.getTablaFlete().addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 1 es igual a boton izquierdo del mouse
				if (e.getButton() == 1) {
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
		int filaSeleccionada = this.ventanaABMFlete.getTablaFlete().getSelectedRow();

		((JTextField) this.txts.get(0))
				.setText((String) this.ventanaABMFlete.getTablaFlete().getValueAt(filaSeleccionada, 0));
		((JTextField) this.txts.get(1))
				.setText("" + this.ventanaABMFlete.getTablaFlete().getValueAt(filaSeleccionada, 1));

		((JTextField) this.txts.get(2))
				.setText((String) this.ventanaABMFlete.getTablaFlete().getValueAt(filaSeleccionada, 2));
		((JTextField) this.txts.get(3))
				.setText((String) this.ventanaABMFlete.getTablaFlete().getValueAt(filaSeleccionada, 3));
		((JTextField) this.txts.get(4))
				.setText((String) this.ventanaABMFlete.getTablaFlete().getValueAt(filaSeleccionada, 4));
		((JDateChooser) this.txts.get(5))
				.setDate((Date) this.ventanaABMFlete.getTablaFlete().getValueAt(filaSeleccionada, 5));

	}

	private void ObtenerFilas(ArrayList<FleteDTO> fletes) {
		limpiarTablaFlete();
		for (int i = 0; i <= fletes.size() - 1; i++) {

			this.cargarFila(i, fletes.get(i).getNroDoc(), fletes.get(i).getNombre(), fletes.get(i).getModelo(),
					fletes.get(i).getPatente(), fletes.get(i).getTelefono(), fletes.get(i).getVtoLicencia());
		}
	}

	private void cargarFila(int fila, int nroDoc, String nombre, String modelo, String patente, String telefono,
			Date vtoLicencia) {
		Object[] flete = { nombre, nroDoc, modelo, patente, telefono, vtoLicencia };
		// Agrego fila
		((DefaultTableModel) this.ventanaABMFlete.getTablaFlete().getModel()).addRow(flete);

		// Establezco que no se pueda editar pero si seleccionar una fila
		((DefaultTableModel) this.ventanaABMFlete.getTablaFlete().getModel()).isCellEditable(fila, 0);
		((DefaultTableModel) this.ventanaABMFlete.getTablaFlete().getModel()).isCellEditable(fila, 1);
		((DefaultTableModel) this.ventanaABMFlete.getTablaFlete().getModel()).isCellEditable(fila, 2);
		((DefaultTableModel) this.ventanaABMFlete.getTablaFlete().getModel()).isCellEditable(fila, 3);
		((DefaultTableModel) this.ventanaABMFlete.getTablaFlete().getModel()).isCellEditable(fila, 4);
		((DefaultTableModel) this.ventanaABMFlete.getTablaFlete().getModel()).isCellEditable(fila, 5);

	}

	public void limpiarTablaFlete() {

		int largo = (this.ventanaABMFlete.getTablaFlete().getModel()).getRowCount();

		for (int i = largo - 1; i >= 0; i--) {
			((DefaultTableModel) this.ventanaABMFlete.getTablaFlete().getModel()).removeRow(i);
		}

	}

//	public static void main(String[] args) {
//
//		VentanaABMFlete abm = new VentanaABMFlete();
//		ControladorABMFlete c = new ControladorABMFlete(abm);
//		c.inicializar();
//
//	}
}
