package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import dto.RepuestoDTO;
import modelo.Repuesto;
import presentacion.vista.VentanaABMRepuesto;

public class ControladorABMRepuesto implements ActionListener  {
	
	private VentanaABMRepuesto ventanaABMRepuesto;
	private Repuesto repuesto;
	private List<RepuestoDTO> repuestos_en_tabla;
	private DefaultTableModel modelTable = new DefaultTableModel();
	
	public ControladorABMRepuesto(VentanaABMRepuesto ventanaABMRepuesto) {
		
		this.ventanaABMRepuesto = ventanaABMRepuesto;
		this.ventanaABMRepuesto.getCancelar_btn().addActionListener(this);
		this.ventanaABMRepuesto.getGuardar_btn().addActionListener(this);
		this.ventanaABMRepuesto.getEliminarItem_btn().addActionListener(this);
		
	}
	
	public void iniciar(){
		
		repuesto = new Repuesto();
		cargarTabla();
		ocultarColumnaId();
		this.ventanaABMRepuesto.setVisible(true);
		
		
		
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
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	public static void main(String[] args) {
		
		VentanaABMRepuesto abm = new VentanaABMRepuesto();
		ControladorABMRepuesto c = new ControladorABMRepuesto(abm);
		c.iniciar();
		
	}

}
