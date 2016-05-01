package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
	private Integer cantidad = 0;
	DefaultTableModel modelo = new DefaultTableModel();
	
	public ControladorPresupuesto( VentanaPresupuesto ventanaPresupuesto, Ingreso ingreso) {
		
		 this.ventanaPresupuesto = ventanaPresupuesto;
		 this.ingreso = ingreso;
		 this.ventanaPresupuesto.getIncrementoCantComponente_btn().addActionListener(this);
		 this.ventanaPresupuesto.getDecrementoCantComponente_btn().addActionListener(this);
		 this.ventanaPresupuesto.getAgregarComponente_btn().addActionListener(this);
		
	}
	
	public void inicializar(){
		
		this.ventanaPresupuesto.setVisible(true);
		this.componente = new Componente();
		cargarComboComponentes();
		
	}
	
	private void cargarComboComponentes() {
		
		listaDeComponetes = this.componente.obtenercomponentes();
		
		for (ComponenteDTO c : listaDeComponetes){
			this.ventanaPresupuesto.getComponente_ComboBox().addItem(c.getNombre());
		}
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {


		// suma y resta componentes
		if (e.getSource() == this.ventanaPresupuesto.getIncrementoCantComponente_btn()){

			cantidad +=1;
			this.ventanaPresupuesto.getCantidad_lbl().setText(cantidad.toString());

		}else if (e.getSource() == this.ventanaPresupuesto.getDecrementoCantComponente_btn()){

			if(cantidad != 0){
				cantidad -=1;
				this.ventanaPresupuesto.getCantidad_lbl().setText(cantidad.toString());
			}
			// agrega a la tabla 
		}else if(e.getSource() == this.ventanaPresupuesto.getAgregarComponente_btn()){
			
			llenarTablaComponentes();

			// elimina componete de la tabla
		}else if(e.getSource() == this.ventanaPresupuesto.getEliminarComponente_btn()){


			// modifca precio de la tabla	
		}else if(e.getSource() == this.ventanaPresupuesto.getComponentes_table().getCellEditor()){


		}
	}

	private void llenarTablaComponentes() {
		
		
		this.ventanaPresupuesto.getComponentes_table().setModel(modelo);
		
		
		modelo.setColumnIdentifiers(this.ventanaPresupuesto.getComponentes_nombreColumnas());

		this.listaDeComponetes = componente.buscarComponentes((String) this.ventanaPresupuesto.getComponente_ComboBox().getSelectedItem());

		for (int i = 0; i < this.listaDeComponetes.size(); i ++)
		{

			Object[] fila = {this.listaDeComponetes.get(i).getNombre(), this.listaDeComponetes.get(i).getDetalle(),
					this.ventanaPresupuesto.getCantidad_lbl().getText(), "$ "+this.listaDeComponetes.get(i).getPrecioUnitario(),
					 "$ "+Float.parseFloat(this.ventanaPresupuesto.getCantidad_lbl().getText()) *
					this.listaDeComponetes.get(i).getPrecioUnitario()};
			
				modelo.insertRow(0, fila);
		}

	}
	public static void main(String[] args) {
		
		ControladorPresupuesto ControladorPresupuesto = new ControladorPresupuesto(new VentanaPresupuesto(),
				new Ingreso());
		
		ControladorPresupuesto.inicializar();
		
	}

}
