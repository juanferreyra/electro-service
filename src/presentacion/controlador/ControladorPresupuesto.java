package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dto.RepuestoDTO;
import dto.ItemPresupuestoRepuestoDTO;
import dto.PerfilDTO;
import dto.PresupuestoDTO;
import dto.UsuarioDTO;
import modelo.Ingreso;
import modelo.Presupuesto;
import persistencia.dao.PresupuestoDAO;
import presentacion.vista.VentanaPresupuesto;

public class ControladorPresupuesto implements ActionListener{
	
	private VentanaPresupuesto ventanaPresupuesto;
	private Ingreso ingreso;
	private Presupuesto presupuesto;
	private UsuarioDTO usuarioLogueado;
	private Integer cantidad = 0;
	private DefaultTableModel modelTable = new DefaultTableModel();
	private float suma = 0;
	private float sumatotal = 0;
	@SuppressWarnings("unused")
	private Calendar hoy = new GregorianCalendar();
	
	public ControladorPresupuesto( VentanaPresupuesto ventanaPresupuesto, Ingreso ingreso, UsuarioDTO usuario) {
		this.ventanaPresupuesto = ventanaPresupuesto;
		 this.ventanaPresupuesto.getIncrementoCantComponente_btn().addActionListener(this);
		 this.ventanaPresupuesto.getDecrementoCantComponente_btn().addActionListener(this);
		 this.ventanaPresupuesto.getAgregarComponente_btn().addActionListener(this);
		 this.ventanaPresupuesto.getEliminarComponente_btn().addActionListener(this);
		 this.ventanaPresupuesto.getGuardar_btn().addActionListener(this);
		 this.ventanaPresupuesto.getManoDeObra_txf().addActionListener(this);
		 this.ventanaPresupuesto.getCancelar_btn().addActionListener(this); 
		 this.ingreso = ingreso;
		 this.usuarioLogueado = usuario;
		 this.presupuesto = new Presupuesto(ingreso.getIngreso());
		 if(presupuesto.getId()!=-1) {
			 cargarModelo();
		 }
	}
	
	public void inicializar() {
		
		Calendar hoy = new GregorianCalendar();
		ventanaPresupuesto.getFechaIngreso_lbl().setText("Fecha: " + hoy.get(Calendar.DAY_OF_MONTH) +" / "
		+ hoy.get(Calendar.MONTH) +" / "+ hoy.get(Calendar.YEAR));
		ventanaPresupuesto.setVisible(true);
		ventanaPresupuesto.getComponentes_table().setModel(modelTable);
		cargarComboComponentes();
		cargarIngreso();
		//cargo el usuario
		if(presupuesto.getId()!=-1) {
			ventanaPresupuesto.getLbltecnico().setText(this.usuarioLogueado.getNombre()+" "+this.usuarioLogueado.getApellido());
			ventanaPresupuesto.getTotal_lbl().setText((String.valueOf(suma)));
			ventanaPresupuesto.getValorPresupuestado_txf().setText(String.valueOf(sumatotal));
		}
	}
	
	private void cargarIngreso() {
		ventanaPresupuesto.getNombreProductoTexto_lbl().setText(ingreso.ingr.getDescripcion());
		ventanaPresupuesto.getMarcaTexto_lbl().setText(ingreso.getMarca().getDetalle());;
		ventanaPresupuesto.getDescripcionFalla_txtArea().setText(ingreso.getIngreso().getDescripcion_falla());
		ventanaPresupuesto.getTipoTexto_lbl().setText(ingreso.getTipoproducto().getDetalle());
	}

	private void cargarComboComponentes() {
		
		/*for (ComponenteDTO c : presupuesto.getListaDeComponetes()){
			this.ventanaPresupuesto.getComponente_ComboBox().addItem(c.getDetalle());
		}*/
	}
	
	private void cargarModelo()
	{
		//cargo la lista de componentes
		actualizarTablaRepuestos();
		//cargo descripcion tecnica
		this.ventanaPresupuesto.getDescripcionTecnica_jTextArea().setText(this.presupuesto.getPresupuesto().getDescripcionTecnica());
		//cargo descripcion Breve
		this.ventanaPresupuesto.getDescripcionBreve_jTextArea().setText(this.presupuesto.getPresupuesto().getDescripcionTecnica());
		//setear fecha de creacion del presupuesto
		this.ventanaPresupuesto.getFechaIngreso_lbl().setText(this.presupuesto.getPresupuesto().getFechacreacion().toString());
		//seteo la fecha de vencimiento
		this.ventanaPresupuesto.getVencimiento_Calendario().setDate(this.presupuesto.getPresupuesto().getFechavencimiento());
		//seteo el total de repuestos
		this.ventanaPresupuesto.getValorPresupuestado_txf().setText(String.valueOf(this.presupuesto.getPresupuesto().getImporteManoObra()));
		//seteo el total de mano de obra
		this.ventanaPresupuesto.getManoDeObra_txf().setText(String.valueOf(this.presupuesto.getPresupuesto().getImporteManoObra()));
		//seteo la cantidad de horas totales
		this.ventanaPresupuesto.getHorasDeTrabajo_txf().setText(String.valueOf(this.presupuesto.getPresupuesto().getHorasTrabajo()));
		//seteo el total del presupuesto (que actualmente se guarda en mano de obra)
		this.ventanaPresupuesto.getTotal_lbl().setText(String.valueOf(this.presupuesto.getPresupuesto().getImporteTotal()));
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
			 
				agregarRepuestoATabla();
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
		}else if (e.getSource() == this.ventanaPresupuesto.getCancelar_btn()){
		
			this.ventanaPresupuesto.dispose();
		}
		
	}

	private void ocultarColumnaId() {

		this.ventanaPresupuesto.getComponentes_table().getColumnModel().getColumn(0).setMaxWidth(0);
		this.ventanaPresupuesto.getComponentes_table().getColumnModel().getColumn(0).setMinWidth(0);
		this.ventanaPresupuesto.getComponentes_table().getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		this.ventanaPresupuesto.getComponentes_table().getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
	}

	private void validarCampos() {
		
		if (this.ventanaPresupuesto.getVencimiento_Calendario().getDate() == null){
			
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
			
		}else if(!soloFloat(this.ventanaPresupuesto.getManoDeObra_txf().getText().toString())){

			JOptionPane.showMessageDialog(ventanaPresupuesto, "Campo PRECIO MANO DE OBRA  admite solo NUMEROS", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
			
		}else if(this.ventanaPresupuesto.getHorasDeTrabajo_txf().getText().isEmpty()){

			JOptionPane.showMessageDialog(ventanaPresupuesto, "Campo HORAS DE TRBAJO 	no puede estar vacio ", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);

		}else if(!soloNumeros(this.ventanaPresupuesto.getHorasDeTrabajo_txf().getText().toString())){

			JOptionPane.showMessageDialog(ventanaPresupuesto, "Campo HORAS DE TRABAJO  admite solo NUMEROS", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
			
		}else{
			
			this.presupuesto.guardarModelo();
		}
	}
	
	
	private boolean soloNumeros(String texto){
		try { 
			Integer.parseInt(texto); 
			return true; 
		} catch (Exception e) { 
			return false; 
		}
	}
	
	private boolean soloFloat(String texto) {
		try {
			Float.parseFloat(texto);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
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

	private void actualizarTablaRepuestos() {
		modelTable.setColumnIdentifiers(ventanaPresupuesto.getComponentes_nombreColumnas());
		 
		List<ItemPresupuestoRepuestoDTO> repuestosAgregados = presupuesto.getListaDeRepuestos();
		
		for(int i = 0; i < repuestosAgregados.size(); i++){
			Object[] fila = {repuestosAgregados.get(i).getId(),repuestosAgregados.get(i).getDetalle(),
					repuestosAgregados.get(i).getCantidad(),repuestosAgregados.get(i).getPrecioUnitario(),
					repuestosAgregados.get(i).getPrecio()};

			modelTable.insertRow(0, fila);
		}
		
		this.ventanaPresupuesto.getComponentes_table().setModel(modelTable);
		
		ocultarColumnaId();
	}

	private void guardarPresupuesto() {
		
		float importeManoObra = Float.parseFloat(ventanaPresupuesto.getValorPresupuestado_txf().getText());
		
		int horasTrabajo = Integer.parseInt(ventanaPresupuesto.getHorasDeTrabajo_txf().getText());
		
		PresupuestoDTO presupuestoNuevo = new PresupuestoDTO(
				0,
				ingreso.getId(),
				ventanaPresupuesto.getDescripcionBreve_jTextArea().getText(),
				ventanaPresupuesto.getDescripcionTecnica_jTextArea().getText(),
				importeManoObra ,
				horasTrabajo,
				sumatotal,
				null,
				ventanaPresupuesto.getVencimiento_Calendario().getDate(),
				this.usuarioLogueado.getId(),
				true);

		PresupuestoDAO nuevo = new PresupuestoDAO();

		nuevo.insert(presupuestoNuevo);

		ventanaPresupuesto.dispose();
	}

	private void eliminarComponenteDeTabla() {

		if(this.ventanaPresupuesto.getComponentes_table().getSelectedRow() != -1) {

			int seleccionado = ventanaPresupuesto.getComponentes_table().getSelectedRow();
			String id = ventanaPresupuesto.getComponentes_table().getValueAt(seleccionado, 2).toString();
			for (int i = 0; i < this.presupuesto.getListaDeRepuestos().size(); i++) {
				if(this.presupuesto.getListaDeRepuestos().get(i).getIdrepuesto() == Integer.parseInt(id)) {
					this.presupuesto.getListaDeRepuestos().remove(i);
				}
			}
			
			sumarTotales();
		} else {

			JOptionPane.showMessageDialog(ventanaPresupuesto, "debe seleccionar fila para eliminar ", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void agregarRepuestoATabla() {

		modelTable.setColumnIdentifiers(ventanaPresupuesto.getComponentes_nombreColumnas());
		
		//agrego el repuesto al objeto Presupuesto con su lista
		int cantidad = Integer.parseInt(ventanaPresupuesto.getCantidad_lbl().getText());
		RepuestoDTO resp = presupuesto.buscarRepuesto((String) this.ventanaPresupuesto.getComponente_ComboBox().getSelectedItem());
		ItemPresupuestoRepuestoDTO itemRepuesto = new ItemPresupuestoRepuestoDTO(-1 ,resp.getId(), resp.getDetalle(),cantidad, resp.getPrecioUnitario(),resp.getPrecioUnitario() * cantidad);
		presupuesto.addRepuestoListaDeComponentes(itemRepuesto);
		//actualizo la tabla de repuestos
		actualizarTablaRepuestos();
	}

	private void sumarTotales() {

		suma = 0;

		for (int i = 0 ; i < ventanaPresupuesto.getComponentes_table().getRowCount(); i++){

			suma += Float.parseFloat(ventanaPresupuesto.getComponentes_table().getValueAt(i, 4).toString());
		}

		ventanaPresupuesto.getTotal_lbl().setText(String.valueOf(suma));

	}
	
	public static void main(String[] args) {
		PerfilDTO perf3 = new PerfilDTO("JEFE");
		UsuarioDTO user3 = new UsuarioDTO(3, "JOAQUIN", "TELECHEA", "jefe", perf3);
		Ingreso ing = new Ingreso();
		ing.setId(7);
		ing.cargarModeloCompleto();

		ControladorPresupuesto controladorPresupuesto = new ControladorPresupuesto(new VentanaPresupuesto(),
				ing, user3);
		controladorPresupuesto.inicializar();
	}
}
