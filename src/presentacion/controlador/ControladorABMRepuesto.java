package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dto.RepuestoDTO;
import modelo.Repuesto;
import presentacion.vista.VentanaABMRepuesto;

public class ControladorABMRepuesto implements ActionListener  {
	
	private VentanaABMRepuesto ventanaABMRepuesto;
	private Repuesto repuesto;
	private List<RepuestoDTO> repuestos_en_tabla;
	private DefaultTableModel modelTable = new DefaultTableModel();
	private List<JTextField> txts;
	
	public ControladorABMRepuesto(VentanaABMRepuesto ventanaABMRepuesto) {
		
		this.ventanaABMRepuesto = ventanaABMRepuesto;
		this.ventanaABMRepuesto.getCancelar_btn().addActionListener(this);
		this.ventanaABMRepuesto.getGuardar_btn().addActionListener(this);
		this.ventanaABMRepuesto.getEliminarItem_btn().addActionListener(this);
		
	}
	
	public void iniciar(){
		
		repuesto = new Repuesto();
		
		cargarTabla();
		
		this.ventanaABMRepuesto.setVisible(true);
		
		this.txts = new ArrayList<JTextField>();
		
		txts.add(this.ventanaABMRepuesto.getDetalle_txt());//0
		txts.add(this.ventanaABMRepuesto.getPrecio_txt());//1
		txts.add(this.ventanaABMRepuesto.getStockMinimo_txt());//2
		
		mouseClickedOnTable();
	}

	private void mouseClickedOnTable() {
		
		this.ventanaABMRepuesto.getTablaRepuesto().addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 1 es igual a boton izquierdo del mouse
				if(arg0.getButton() == 1 ){
					cargartxts();	
				}
			}
		});
	}
	
	private void cargartxts() {
		
		int filaSeleccionada = this.ventanaABMRepuesto.getTablaRepuesto().getSelectedRow();

		this.txts.get(0).setText((String)this.ventanaABMRepuesto.getModelRepuesto().getValueAt(filaSeleccionada, 1));
		this.txts.get(1).setText(String.valueOf(this.ventanaABMRepuesto.getModelRepuesto().getValueAt(filaSeleccionada, 2)));
		this.txts.get(2).setText(String.valueOf(this.ventanaABMRepuesto.getModelRepuesto().getValueAt(filaSeleccionada, 3)));
		
		
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

	    
		for (int i = 0; i < this.repuestos_en_tabla.size(); i ++)
		{
			
			Object[] fila = {this.repuestos_en_tabla.get(i).getId(), 
					this.repuestos_en_tabla.get(i).getDetalle(), 
					this.repuestos_en_tabla.get(i).getPrecioUnitario(),
					this.repuestos_en_tabla.get(i).getStockMinimo()};
			
			this.ventanaABMRepuesto.getModelRepuesto().addRow(fila);
			
		}
		
		ocultarColumnaId();	
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.ventanaABMRepuesto.getCancelar_btn()){ // boton cancelar 

			this.ventanaABMRepuesto.dispose();
		}

		}
	
	public static void main(String[] args) {
		
		VentanaABMRepuesto abm = new VentanaABMRepuesto();
		ControladorABMRepuesto c = new ControladorABMRepuesto(abm);
		c.iniciar();
		
	}

}
