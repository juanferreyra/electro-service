package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dto.ComponenteDTO;
import modelo.Componente;
import modelo.Ingreso;
import presentacion.vista.VentanaPresupuesto;

public class ControladorPresupuesto implements ActionListener{
	
	private VentanaPresupuesto ventanaPresupuesto;
	private Ingreso ingreso;
	private List<ComponenteDTO> listaDeComponetes; 
	private Componente componente;
	private Integer cantidad = 1;
	private DefaultTableModel modelo = new DefaultTableModel();
	
	public ControladorPresupuesto( VentanaPresupuesto ventanaPresupuesto, Ingreso ingreso) {
		
		 this.ventanaPresupuesto = ventanaPresupuesto;
		 this.ingreso = ingreso;
		 this.ventanaPresupuesto.getIncrementoCantComponente_btn().addActionListener(this);
		 this.ventanaPresupuesto.getDecrementoCantComponente_btn().addActionListener(this);
		 this.ventanaPresupuesto.getAgregarComponente_btn().addActionListener(this);
		 this.ventanaPresupuesto.getEliminarComponente_btn().addActionListener(this);
		
	}
	
	public void inicializar(){
		
		Calendar hoy = new GregorianCalendar();
		
		this.ventanaPresupuesto.getFechaIngreso_lbl().setText("Fecha: " + hoy.get(Calendar.DAY_OF_MONTH) +" / "
		+ hoy.get(Calendar.MONTH) +" / "+ hoy.get(Calendar.YEAR));
	
		this.ventanaPresupuesto.setVisible(true);
		
		this.componente = new Componente();
		this.ventanaPresupuesto.getComponentes_table().setModel(modelo);
		cargarComboComponentes();
		
	}
	
	private void cargarComboComponentes() {
		
		listaDeComponetes = this.componente.obtenercomponentes();
		
		for (ComponenteDTO c : listaDeComponetes){
			this.ventanaPresupuesto.getComponente_ComboBox().addItem(c.getDetalle());
		}
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {


		// suma y resta componentes
		if (e.getSource() == this.ventanaPresupuesto.getIncrementoCantComponente_btn()){

			cantidad +=1;
			this.ventanaPresupuesto.getCantidad_lbl().setText(cantidad.toString());

		}else if (e.getSource() == this.ventanaPresupuesto.getDecrementoCantComponente_btn()){

			if(cantidad != 1){
				cantidad -=1;
				this.ventanaPresupuesto.getCantidad_lbl().setText(cantidad.toString());
			}
			// agrega a la tabla 
		}else if(e.getSource() == this.ventanaPresupuesto.getAgregarComponente_btn()){
			
			llenarTablaComponentes();

			// elimina componete de la tabla
		}else if(e.getSource() == this.ventanaPresupuesto.getEliminarComponente_btn()){
			
			eliminarComponenteDeTabla();


			// modifca precio de la tabla	
		}else if(e.getSource() == this.ventanaPresupuesto.getComponentes_table().getCellEditor()){


		}
	}

	private void eliminarComponenteDeTabla() {
		
		if (this.ventanaPresupuesto.getComponentes_table().getSelectedRow() != -1){

			modelo.removeRow(this.ventanaPresupuesto.getComponentes_table().getSelectedRow());
		}else{
			
			JOptionPane.showMessageDialog(this.ventanaPresupuesto, "debe seleccionar fila para eliminar ", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void llenarTablaComponentes() {
		
		modelo.setColumnIdentifiers(this.ventanaPresupuesto.getComponentes_nombreColumnas());

		this.listaDeComponetes = componente.buscarComponentes((String) this.ventanaPresupuesto.getComponente_ComboBox().getSelectedItem());

		for (int i = 0; i < this.listaDeComponetes.size(); i ++)
		{

			Object[] fila = {this.listaDeComponetes.get(i).getDetalle(),
					this.ventanaPresupuesto.getCantidad_lbl().getText(), "$ "+this.listaDeComponetes.get(i).getPrecioUnitario(),
					 "$ "+Float.parseFloat(this.ventanaPresupuesto.getCantidad_lbl().getText()) *
					this.listaDeComponetes.get(i).getPrecioUnitario()};
			
				modelo.insertRow(0, fila);
		}

	}
	public static void main(String[] args) {
		
		/*ControladorPresupuesto controladorPresupuesto = new ControladorPresupuesto(new VentanaPresupuesto(),
				new Ingreso());
		
		controladorPresupuesto.inicializar();
		*/
	}

}
