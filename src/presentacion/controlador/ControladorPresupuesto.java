package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dto.ComponenteDTO;
import dto.PresupuestoDTO;
import dto.PresupuestoRepuestoDTO;
import modelo.Componente;
import modelo.Ingreso;
import modelo.PresupuestoRepuestos;
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
	private float sumatotal = 0;
	private float  manoDeObra = 0;
	
	public ControladorPresupuesto( VentanaPresupuesto ventanaPresupuesto, Ingreso ingreso) {
		
		 this.ventanaPresupuesto = ventanaPresupuesto;
		 this.ingreso = ingreso;
		 this.ventanaPresupuesto.getIncrementoCantComponente_btn().addActionListener(this);
		 this.ventanaPresupuesto.getDecrementoCantComponente_btn().addActionListener(this);
		 this.ventanaPresupuesto.getAgregarComponente_btn().addActionListener(this);
		 this.ventanaPresupuesto.getEliminarComponente_btn().addActionListener(this);
		 this.ventanaPresupuesto.getGuardar_btn().addActionListener(this);
		 this.ventanaPresupuesto.getManoDeObra_txf().addActionListener(this);
		 this.ventanaPresupuesto.getBuscarTecnico_btn().addActionListener(this);
		
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
		this.ventanaPresupuesto.getValorPresupuestado_txf().setText(String.valueOf(sumatotal));
		this.ventanaPresupuesto.getManoDeObra_txf().setText(String.valueOf(manoDeObra));
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
			
		}else if (e.getSource() == this.ventanaPresupuesto.getBuscarTecnico_btn()){
			
			llenarTablaUsuarios();
				

		}else if (e.getSource() == this.ventanaPresupuesto.getDecrementoCantComponente_btn()){

			if(cantidad != 1){
				cantidad -=1;
				this.ventanaPresupuesto.getCantidad_lbl().setText(cantidad.toString());
			}
			// agrega a la tabla 
		}else if(e.getSource() == this.ventanaPresupuesto.getAgregarComponente_btn()){
			 
				llenarTablaComponentes();
				ocultarColumnaId();
				sumarTotales();
				sumarTotalComponentes();

			// elimina componete de la tabla
		}else if(e.getSource() == this.ventanaPresupuesto.getEliminarComponente_btn()){
			
			eliminarComponenteDeTabla();
			
			
		}else if(e.getSource() == this.ventanaPresupuesto.getGuardar_btn()){
			
			validarCampos();
			
		}else if (e.getSource() == this.ventanaPresupuesto.getManoDeObra_txf()){
			
			this.ventanaPresupuesto.getManoDeObra_txf().addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					
					if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
							
							sumarTotalComponentes();
							
					}else if (arg0.getKeyCode() == KeyEvent.VK_DELETE){
						
						restarTotalComponentes();
						
					}
					
				}
			});
		}
		
	}
	
	private void llenarTablaUsuarios() {
		
		
		
	}


	private void ocultarColumnaId() {

		this.ventanaPresupuesto.getComponentes_table().getColumnModel().getColumn(0).setMaxWidth(0);
		this.ventanaPresupuesto.getComponentes_table().getColumnModel().getColumn(0).setMinWidth(0);
		this.ventanaPresupuesto.getComponentes_table().getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		this.ventanaPresupuesto.getComponentes_table().getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
	}


	private void validarCampos() {
		
		if(this.ventanaPresupuesto.getNroTecnico_txf().getText().isEmpty()){
			
			JOptionPane.showMessageDialog(ventanaPresupuesto, "Campo NRO DE TECNICO no puede estar vacio ", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
			
		}else if(!soloNumeros(this.ventanaPresupuesto.getNroTecnico_txf().getText().toString())){

			JOptionPane.showMessageDialog(ventanaPresupuesto, "Campo NRO DE TECNICO  admite solo NUMEROS", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
				
		}else if (this.ventanaPresupuesto.getVencimiento_Calendario().getDate() == null){
			
			JOptionPane.showMessageDialog(ventanaPresupuesto, "Campo FECHA DE VENCIMIENTO no puede estar vacio ", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
			
		}else if(this.ventanaPresupuesto.getComponentes_table().getRowCount() == 0){

			JOptionPane.showMessageDialog(ventanaPresupuesto, "Campo NO INGRESADO COMPONENTES AL PRESUPUESTO  no puede estar vacio ", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
			
		}else if(this.ventanaPresupuesto.getDescripcionBreve_jTextArea().getText().isEmpty()){

			JOptionPane.showMessageDialog(ventanaPresupuesto, "Campo CAMPO DESCRIPCION BREVE no puede estar vacio ", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
			
		}else if(this.ventanaPresupuesto.getDescripcionTecnica_jTextArea().getText().isEmpty()){

			JOptionPane.showMessageDialog(ventanaPresupuesto, "Campo CAMPO DESCRIPCION TECNICA no puede estar vacio ", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
			
		}else if(this.ventanaPresupuesto.getHorasDeTrabajo_txf().getText().isEmpty()){

			JOptionPane.showMessageDialog(ventanaPresupuesto, "Campo HORAS DE TRABAJO no puede estar vacio ", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
			
		}else if(!soloNumeros(this.ventanaPresupuesto.getManoDeObra_txf().getText().toString())){

			JOptionPane.showMessageDialog(ventanaPresupuesto, "Campo PRECIO MANO DE OBRA  admite solo NUMEROS", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
			
		}else if(Float.parseFloat(this.ventanaPresupuesto.getManoDeObra_txf().getText()) == 0.0){

			JOptionPane.showMessageDialog(ventanaPresupuesto, "Campo PRECIO MANO DE OBRA  no puede ser CERO ", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);

		}else if(this.ventanaPresupuesto.getHorasDeTrabajo_txf().getText().isEmpty()){

			JOptionPane.showMessageDialog(ventanaPresupuesto, "Campo HORAS DE TRBAJO 	no puede estar vacio ", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);

		}else if(!soloNumeros(this.ventanaPresupuesto.getHorasDeTrabajo_txf().getText().toString())){

			JOptionPane.showMessageDialog(ventanaPresupuesto, "Campo HORAS DE TRABAJO  admite solo NUMEROS", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
			
		
		}else{
			
			if(this.ventanaPresupuesto.getNroTecnico_txf().getText().equals("1") || this.ventanaPresupuesto.getNroTecnico_txf().getText().equals("2")
					|| this.ventanaPresupuesto.getNroTecnico_txf().getText().equals("3")){

				guardarPresupuesto();

				guardarRepuestos(obtenerIdPresupuesto());
			}else{
				
				JOptionPane.showMessageDialog(ventanaPresupuesto, "Campo NRO DE TECNICO  admite solo NUMEROS  1, 2, o 3", "Atencion!",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	
	private boolean soloNumeros(String texto){
		 try { Integer.parseInt(texto); return true; } catch (Exception e) { return false; }
	}



	private void restarTotalComponentes() {
		
		float resta = Float.parseFloat(this.ventanaPresupuesto.getValorPresupuestado_txf().getText()) -
				Float.parseFloat(this.ventanaPresupuesto.getManoDeObra_txf().getText());
		
		this.ventanaPresupuesto.getValorPresupuestado_txf().setText(String.valueOf(resta));
	}


	private void sumarTotalComponentes() {
		
		if(this.ventanaPresupuesto.getManoDeObra_txf().getText().isEmpty()){

			JOptionPane.showMessageDialog(ventanaPresupuesto, "Campo MANO DE OBRA no puede estar vacio ", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
			
		}else{
			
			float suma = Float.parseFloat(this.ventanaPresupuesto.getTotal_lbl().getText()) +
					Float.parseFloat(this.ventanaPresupuesto.getManoDeObra_txf().getText());

			this.ventanaPresupuesto.getValorPresupuestado_txf().setText(String.valueOf(suma));
			
			
		}

	}


	private void guardarRepuestos(int idPresupuesto) {
		
		int idRepuesto;
		int cantidad;
		
		for(int i = 0; i < this.ventanaPresupuesto.getComponentes_table().getRowCount(); i++){
			
			idRepuesto = Integer.parseInt(this.modelo.getValueAt(i, 0).toString());
			cantidad = Integer.parseInt(this.modelo.getValueAt(i, 2).toString());

			PresupuestoRepuestoDTO repuestoNuevo = new PresupuestoRepuestoDTO(idPresupuesto,idRepuesto,cantidad);
				
					
			PresupuestoRepuestos nuevo = new PresupuestoRepuestos();
			nuevo.agregarRepuesto(repuestoNuevo);
		}
	}
	
	private int obtenerIdPresupuesto() {
		
		PresupuestoRepuestos buscar = new PresupuestoRepuestos();
		return buscar.buscarIdPresupuesto(ingreso.getId());
	}

	private void guardarPresupuesto() {

		PresupuestoDTO presupuestoNuevo = new PresupuestoDTO(
				ingreso.getId(),
				this.ventanaPresupuesto.getDescripcionBreve_jTextArea().getText(),
				this.ventanaPresupuesto.getDescripcionTecnica_jTextArea().getText(),
				this.ventanaPresupuesto.getManoDeObra_txf().getText(),

				this.ventanaPresupuesto.getVencimiento_Calendario().getDate(),

				Integer.parseInt(this.ventanaPresupuesto.getNroTecnico_txf().getText()));

		PresupuestoDAO nuevo = new PresupuestoDAO();

		nuevo.insert(presupuestoNuevo);

		this.ventanaPresupuesto.dispose();	
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
		ing.setId(2);
		ing.cargarModeloCompleto();

		ControladorPresupuesto controladorPresupuesto = new ControladorPresupuesto(new VentanaPresupuesto(),ing);
		controladorPresupuesto.inicializar();

	}

}
