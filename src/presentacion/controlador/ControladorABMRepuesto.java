package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dto.MarcaDTO;
import dto.RepuestoDTO;
import modelo.Marca;
import modelo.Repuesto;
import modelo.Stock;
import presentacion.vista.VentanaABMRepuesto;

public class ControladorABMRepuesto implements ActionListener {

	private VentanaABMRepuesto ventanaABMRepuesto;
	private Repuesto repuesto;
	private List<RepuestoDTO> repuestos_en_tabla;
	@SuppressWarnings("unused")
	private DefaultTableModel modelTable = new DefaultTableModel();
	private List<JTextField> txts;
	private Stock stock;
	private Marca marca;
	private List<MarcaDTO> listaDeMarcas;

	public ControladorABMRepuesto(VentanaABMRepuesto ventanaABMRepuesto) {

		this.ventanaABMRepuesto = ventanaABMRepuesto;
		this.ventanaABMRepuesto.getGuardar_btn().addActionListener(this);
		this.ventanaABMRepuesto.getEliminarItem_btn().addActionListener(this);
		this.ventanaABMRepuesto.getLimpiar_btn().addActionListener(this);

	}

	public void inicializar() {

		this.repuesto = new Repuesto();
		this.marca = new Marca();
		this.stock = new Stock();

		cargarTabla();
		cargarCombo();

		this.ventanaABMRepuesto.setVisible(true);

		this.txts = new ArrayList<JTextField>();

		txts.add(this.ventanaABMRepuesto.getDetalle_txt());// 0
		txts.add(this.ventanaABMRepuesto.getPrecio_txt());// 1
		txts.add(this.ventanaABMRepuesto.getStockMinimo_txt());// 2

		mouseClickedOnTable();
	}

	@SuppressWarnings("unchecked")
	private void cargarCombo() {
		
		this.listaDeMarcas = marca.obtenerMarcas();
		
		for(int i = 0; i < this.listaDeMarcas.size(); i++){
			
			this.ventanaABMRepuesto.getCmb_marca().addItem(this.listaDeMarcas.get(i).getDetalle());	
		}
		this.ventanaABMRepuesto.getCmb_marca().setSelectedIndex(0);
		
		
	}

	private void mouseClickedOnTable() {

		this.ventanaABMRepuesto.getTablaRepuesto().addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 1 es igual a boton izquierdo del mouse
				if (arg0.getButton() == 1) {
					cargartxts();
				}
			}
		});
	}

	private void cargartxts() {

		int filaSeleccionada = this.ventanaABMRepuesto.getTablaRepuesto().getSelectedRow();

		this.txts.get(0).setText((String) this.ventanaABMRepuesto.getModelRepuesto().getValueAt(filaSeleccionada, 2));
		this.txts.get(1)
				.setText(String.valueOf(this.ventanaABMRepuesto.getModelRepuesto().getValueAt(filaSeleccionada, 3)));
		this.txts.get(2)
				.setText(String.valueOf(this.ventanaABMRepuesto.getModelRepuesto().getValueAt(filaSeleccionada, 4)));
		
		cargarComboRepuestoSeleccionado(filaSeleccionada);
		

	}

	private void cargarComboRepuestoSeleccionado(int fila) {
		
		//this.ventanaABMRepuesto.getCmb_marca().removeAllItems();
		
		this.listaDeMarcas = marca.obtenerMarcas();

		for(int i = 0; i < this.listaDeMarcas.size(); i++){
			
			if(this.listaDeMarcas.get(i).getDetalle().equals(this.ventanaABMRepuesto.getModelRepuesto().getValueAt(fila, 1))){
				
				this.ventanaABMRepuesto.getCmb_marca().setSelectedIndex(i);
			}
		}
		


	}

	private void ocultarColumnaId() {

		this.ventanaABMRepuesto.getTablaRepuesto().getColumnModel().getColumn(0).setMaxWidth(0);
		this.ventanaABMRepuesto.getTablaRepuesto().getColumnModel().getColumn(0).setMinWidth(0);
		this.ventanaABMRepuesto.getTablaRepuesto().getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		this.ventanaABMRepuesto.getTablaRepuesto().getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

	}

	private void cargarTabla() {

		this.ventanaABMRepuesto.getModelRepuesto().setRowCount(0);
		this.ventanaABMRepuesto.getModelRepuesto().setColumnCount(0);
		this.ventanaABMRepuesto.getModelRepuesto().setColumnIdentifiers(this.ventanaABMRepuesto.getNombreColumnas());

		this.repuestos_en_tabla = repuesto.obtenerRepuestos();

		for (int i = 0; i < this.repuestos_en_tabla.size(); i++) {
			
			Object[] fila = { this.repuestos_en_tabla.get(i).getId(),
					
					marca.buscarDetalleMarcaXid(this.repuestos_en_tabla.get(i).getIdmarca()),
					this.repuestos_en_tabla.get(i).getDetalle(),
					this.repuestos_en_tabla.get(i).getPrecioUnitario(),
					this.repuestos_en_tabla.get(i).getStockMinimo() };

			this.ventanaABMRepuesto.getModelRepuesto().addRow(fila);

		}

		ocultarColumnaId();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.ventanaABMRepuesto.getLimpiar_btn()) {

			this.limpiartxts();
			this.ventanaABMRepuesto.getTablaRepuesto().clearSelection();
			
		} else if (e.getSource() == this.ventanaABMRepuesto.getEliminarItem_btn()) {

			// si la tabla no esta vacia
			if (this.ventanaABMRepuesto.getTablaRepuesto().getRowCount() != 0) {

				// si se selecciona una fila
				if (this.ventanaABMRepuesto.getTablaRepuesto().getSelectedRow() != -1) {

					int filaSeleccionada = this.ventanaABMRepuesto.getTablaRepuesto().getSelectedRow();
					int id_repuesto_a_eliminar = (int) this.ventanaABMRepuesto.getModelRepuesto()
							.getValueAt(filaSeleccionada, 0);

					this.repuesto.borrarRepuesto(id_repuesto_a_eliminar);

					//this.stock.borrarRepuestoDelStock(id_cliente_a_eliminar);
					
					cargarTablaRepuestos();
					limpiartxts();

				} else {

					JOptionPane.showMessageDialog(this.ventanaABMRepuesto, "Debe seleccionar un  a eliminar");
				}
			} else {

				JOptionPane.showMessageDialog(this.ventanaABMRepuesto, "No hay Repuestos a eliminar");
			}
			
		} else if (e.getSource() == this.ventanaABMRepuesto.getGuardar_btn()) {

			// si esta seleccionado de la tabla
			// modificar repuesto
			if (this.ventanaABMRepuesto.getTablaRepuesto().getSelectedRow() != -1) {

				int filaSeleccionada = this.ventanaABMRepuesto.getTablaRepuesto().getSelectedRow();

				if (!isTxtsVacios()) {

					if (isTxtsValidos()) {

						repuesto.modificarRepuesto(obteneRepuesto(
								(int) this.ventanaABMRepuesto.getModelRepuesto().getValueAt(filaSeleccionada, 0)));

						limpiartxts();
						cargarTablaRepuestos();
					}

				} else {

					JOptionPane.showMessageDialog(this.ventanaABMRepuesto,
							"No se permiten campos vacios. Vuelva a intentarlo.");
				}

			} else {
				// nuevo repuesto

				if (!isTxtsVacios()) {

					if (isTxtsValidos()) {
						RepuestoDTO repuestoNuevo = obteneRepuesto(0);

						repuesto.agregarRepuesto(repuestoNuevo);
						
						 RepuestoDTO repuestoCompleto = repuesto.buscarRepuesto(repuestoNuevo.getDetalle());
						
						stock.agregarRepuesto(repuestoCompleto);
						
						limpiartxts();
						
						cargarTablaRepuestos();
					}

				} else {

					JOptionPane.showMessageDialog(this.ventanaABMRepuesto,
							"No se permiten campos vacios. Vuelva a intentarlo.");
				}

			}
		}

	}

	private RepuestoDTO obteneRepuesto(int id) {
		
		int idMarca = marca.obtenerIdMarca((String) this.ventanaABMRepuesto.getCmb_marca().getSelectedItem());;

		RepuestoDTO repuestoDTO = new RepuestoDTO(id, this.txts.get(0).getText(), // detalle
				Float.parseFloat(this.txts.get(1).getText()), // precioUniario
				Integer.parseInt(this.txts.get(2).getText()), // StockMinimo
				idMarca,
				null, 0, 1);

		return repuestoDTO;
	}

	private boolean isTxtsValidos() {

		boolean ret = true;

		if (!soloNumeros(this.txts.get(2).getText())) { // valida StockMinimo

			JOptionPane.showMessageDialog(this.ventanaABMRepuesto,
					"Has ingresado un stock minimo invalido. Vuelva a intentarlo.");
			return false;

		} else {

			ret = soloNumeros(this.txts.get(2).getText());
		}

		if (!soloFloats(this.txts.get(1).getText())) { // valida precioUnitario

			JOptionPane.showMessageDialog(this.ventanaABMRepuesto,
					"Has ingresado un precio unitario invalido. Vuelva a intentarlo.");
			return false;
		} else {

			ret = soloFloats(this.txts.get(1).getText());
		}

		return ret;
	}

	private boolean isTxtsVacios() {

		boolean ret = false;

		for (JTextField jt : txts) {

			if (jt.getText().isEmpty()) {

				ret = true;
			}
		}
		return ret;
	}

	private void limpiartxts() {

		for (JTextField jt : txts) {
			jt.setText("");

		}

		this.ventanaABMRepuesto.getCmb_marca().setSelectedIndex(-1);

	}

	private void cargarTablaRepuestos() {

		this.ventanaABMRepuesto.getModelRepuesto().setRowCount(0);
		this.ventanaABMRepuesto.getModelRepuesto().setColumnCount(0);
		this.ventanaABMRepuesto.getModelRepuesto().setColumnIdentifiers(this.ventanaABMRepuesto.getNombreColumnas());

		this.repuestos_en_tabla = repuesto.obtenerRepuestos();

		for (int i = 0; i < this.repuestos_en_tabla.size(); i++) {

			
			Object[] fila = { this.repuestos_en_tabla.get(i).getId(),
					
					marca.buscarDetalleMarcaXid(this.repuestos_en_tabla.get(i).getIdmarca()),
					
					this.repuestos_en_tabla.get(i).getDetalle(),
					this.repuestos_en_tabla.get(i).getPrecioUnitario(),
					this.repuestos_en_tabla.get(i).getStockMinimo() };

			this.ventanaABMRepuesto.getModelRepuesto().addRow(fila);
		}

		ocultarColumnaId();

	}

	private boolean soloNumeros(String texto) {
		try {
			Integer.parseInt(texto);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean soloFloats(String texto) {
		try {
			Float.parseFloat(texto);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void main(String[] args) {

		VentanaABMRepuesto abm = new VentanaABMRepuesto();
		ControladorABMRepuesto c = new ControladorABMRepuesto(abm);
		c.inicializar();

	}

}
