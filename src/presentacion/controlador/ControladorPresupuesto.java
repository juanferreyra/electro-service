package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
	
	
	public ControladorPresupuesto( VentanaPresupuesto ventanaPresupuesto, Ingreso ingreso) {
		
		 this.ventanaPresupuesto = ventanaPresupuesto;
		 this.ingreso = ingreso;
		 this.ventanaPresupuesto.getIncrementoCantComponente_btn().addActionListener(this);
		 this.ventanaPresupuesto.getDecrementoCantComponente_btn().addActionListener(this);
		
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
		
	
	    
		for (int i = 0; i < this.listaDeComponetes.size(); i ++)
		{
			
			Object[] fila = {this.listaDeComponetes.get(i).getNombre(), this.listaDeComponetes.get(i).getDetalle(),
					this.listaDeComponetes.get(i).getPrecioUnitario()};
			
			this.ventanaPresupuesto.getModelComponentes().addRow(fila);
		}
		
	}

}
