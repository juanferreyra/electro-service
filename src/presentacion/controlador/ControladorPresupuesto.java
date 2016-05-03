package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dto.ComponenteDTO;
import dto.PresupuestoDTO;
import dto.PresupuestoRepuestoDTO;
import modelo.Componente;
import modelo.Ingreso;
import persistencia.dao.PresupuestoDAO;
import presentacion.vista.VentanaPresupuesto;

public class ControladorPresupuesto implements ActionListener{
	
	private VentanaPresupuesto ventanaPresupuesto;
	private Ingreso ingreso;
	private List<ComponenteDTO> listaDeComponetes; 
	private Componente componente;
	private Integer cantidad = 1;
	private DefaultTableModel modelo = new DefaultTableModel();
	private float suma = 0;
	
	public ControladorPresupuesto( VentanaPresupuesto ventanaPresupuesto, Ingreso ingreso) {
		
		 this.ventanaPresupuesto = ventanaPresupuesto;
		 this.ingreso = ingreso;
		 this.ventanaPresupuesto.getIncrementoCantComponente_btn().addActionListener(this);
		 this.ventanaPresupuesto.getDecrementoCantComponente_btn().addActionListener(this);
		 this.ventanaPresupuesto.getAgregarComponente_btn().addActionListener(this);
		 this.ventanaPresupuesto.getEliminarComponente_btn().addActionListener(this);
		 this.ventanaPresupuesto.getGuardar_btn().addActionListener(this);
		
	}
	
	public void inicializar(){
		
		Calendar hoy = new GregorianCalendar();
		
		this.ventanaPresupuesto.getFechaIngreso_lbl().setText("Fecha: " + hoy.get(Calendar.DAY_OF_MONTH) +" / "
		+ hoy.get(Calendar.MONTH) +" / "+ hoy.get(Calendar.YEAR));
	
		this.ventanaPresupuesto.setVisible(true);
		
		this.componente = new Componente();
		this.ventanaPresupuesto.getComponentes_table().setModel(modelo);
		cargarComboComponentes();
		cargarIngreso();
		this.ventanaPresupuesto.getTotal_lbl().setText((String.valueOf(suma)));
	}
	
	

	private void cargarIngreso() {
		
		this.ventanaPresupuesto.getNombreProductoTexto_lbl().setText(ingreso.getTipoproducto().getDetalle());
		this.ventanaPresupuesto.getMarcaTexto_lbl().setText(ingreso.getMarca().getDetalle());;
		this.ventanaPresupuesto.getDescripcionFalla_txtArea().setText(ingreso.getIngreso().getDescripcion_falla());
		this.ventanaPresupuesto.getTipoTexto_lbl().setText(ingreso.getTipoproducto().getDetalle());
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
				sumarTotales();

			// elimina componete de la tabla
		}else if(e.getSource() == this.ventanaPresupuesto.getEliminarComponente_btn()){
			
			eliminarComponenteDeTabla();

			// modifca precio de la tabla	
		}else if(e.getSource() == this.ventanaPresupuesto.getComponentes_table().getCellEditor()){


		}else if(e.getSource() == this.ventanaPresupuesto.getGuardar_btn()){
			
			guardarRepuestos(guardarPresupuesto());
			
		}
	}



	
	private void guardarRepuestos(int idPresupuesto) {
		
		/*PresupuestoRepuestoDTO repuestoNuevo = new PresupuestoRepuestoDTO(
				0,
				idPresupuesto,
				idComponente,
				cantidad);
		*/
		
		
	
		
	}

	private int  guardarPresupuesto() {
		
		
		PresupuestoDTO presupuestoNuevo = new PresupuestoDTO(
				0,
				ingreso.getId(),
				this.ventanaPresupuesto.getDescripcionBreve_jTextArea().getText(),
				this.ventanaPresupuesto.getDescripcionTecnica_jTextArea().getText(),
				this.ventanaPresupuesto.getManoDeObra_txf().getText(),
				
				this.ventanaPresupuesto.getVencimiento_Calendario().getDate(),
				
				Integer.parseInt(this.ventanaPresupuesto.getNroTecnico_txf().getText()));
		
		PresupuestoDAO nuevo = new PresupuestoDAO();
		
		nuevo.insert(presupuestoNuevo);
		
		this.ventanaPresupuesto.dispose();
		
		return presupuestoNuevo.getId();
		
	}

	private void eliminarComponenteDeTabla() {
		
		if (this.ventanaPresupuesto.getComponentes_table().getSelectedRow() != -1){

			modelo.removeRow(this.ventanaPresupuesto.getComponentes_table().getSelectedRow());
			sumarTotales();
			
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

			Object[] fila = {this.listaDeComponetes.get(i).getId(),this.listaDeComponetes.get(i).getDetalle(),
					this.ventanaPresupuesto.getCantidad_lbl().getText(), this.listaDeComponetes.get(i).getPrecioUnitario(),
					 Float.parseFloat(this.ventanaPresupuesto.getCantidad_lbl().getText()) *
					this.listaDeComponetes.get(i).getPrecioUnitario()};
			
				modelo.insertRow(0, fila);
		}
		
		
	}
	
	private void sumarTotales() {

		suma = 0;
		
		for (int i = 0 ; i < this.ventanaPresupuesto.getComponentes_table().getRowCount(); i++){

			suma += Float.parseFloat(this.ventanaPresupuesto.getComponentes_table().getValueAt(i, 4).toString());
		}

		this.ventanaPresupuesto.getTotal_lbl().setText(String.valueOf(suma));

	}
	
	public static void main(String[] args) {
		
		Ingreso ing = new Ingreso();
		ing.setId(1);
		ing.cargarModeloCompleto();
		
		ControladorPresupuesto controladorPresupuesto = new ControladorPresupuesto(new VentanaPresupuesto(),ing);
		controladorPresupuesto.inicializar();
		
	}

}
