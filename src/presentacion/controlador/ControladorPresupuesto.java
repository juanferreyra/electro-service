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
import dto.IngresoLogDTO;
import dto.ItemRepuestoDTO;
import dto.UsuarioDTO;
import modelo.EmailPresupuesto;
import modelo.Ingreso;
import modelo.Presupuesto;
import persistencia.dao.IngresoLogDAO;
import presentacion.vista.VentanaPresupuesto;

public class ControladorPresupuesto implements ActionListener{
	
	private VentanaPresupuesto ventanaPresupuesto;
	private ControladorVentanaPrincipal controladorVentanaPrincipal;
	private Ingreso ingreso;
	private Presupuesto presupuesto;
	private UsuarioDTO usuarioLogueado;
	private Integer cantidad = 0;
	private DefaultTableModel modelTable = new DefaultTableModel();
	private float suma = 0;
	@SuppressWarnings("unused")
	private Calendar hoy = new GregorianCalendar();
	private EmailPresupuesto email;
	private String perfil;
	
	public ControladorPresupuesto( VentanaPresupuesto ventanaPresupuesto, Ingreso ingreso,
			ControladorVentanaPrincipal controladorVentanaPrincipal, UsuarioDTO usuario) {
		this.ingreso = ingreso;
		this.usuarioLogueado = usuario;
		this.perfil = usuario.getPerfilDTO().getPerfil();
		this.presupuesto = new Presupuesto(ingreso.getIngreso());
		this.ventanaPresupuesto = ventanaPresupuesto;
		this.controladorVentanaPrincipal = controladorVentanaPrincipal;
		this.ventanaPresupuesto.getIncrementoCantComponente_btn().addActionListener(this);
		this.ventanaPresupuesto.getDecrementoCantComponente_btn().addActionListener(this);
		this.ventanaPresupuesto.getAgregarComponente_btn().addActionListener(this);
		this.ventanaPresupuesto.getEliminarComponente_btn().addActionListener(this);
		this.ventanaPresupuesto.getGuardar_btn().addActionListener(this);
		this.ventanaPresupuesto.getManoDeObra_txf().addActionListener(this);
		this.ventanaPresupuesto.getCancelar_btn().addActionListener(this);
		this.ventanaPresupuesto.getEnviarPresupuesto_btn().addActionListener(this);
		this.ventanaPresupuesto.getBtnInformado().addActionListener(this);
		this.ventanaPresupuesto.getBtnAceptado().addActionListener(this);
		this.ventanaPresupuesto.getBtnAsignar().addActionListener(this);
		this.ventanaPresupuesto.getBtnRechazado().addActionListener(this);
	}
	
	public void inicializar() {
		
		Calendar hoy = new GregorianCalendar();
		ventanaPresupuesto.getFechaIngreso_lbl().setText("Fecha: " + hoy.get(Calendar.DAY_OF_MONTH) +" / "
		+ hoy.get(Calendar.MONTH) +" / "+ hoy.get(Calendar.YEAR));
		ventanaPresupuesto.setVisible(true);
		ventanaPresupuesto.getComponentes_table().setModel(modelTable);
		cargarComboComponentes();
		cargarIngreso();
		ocultarBotonesEstados();
		if(presupuesto.getId()==-1) {
			ventanaPresupuesto.getLbltecnico().setText(this.usuarioLogueado.getNombre()+" "+this.usuarioLogueado.getApellido());
			ventanaPresupuesto.getManoDeObra_txf().setText("0");
			ventanaPresupuesto.getTotal_lbl().setText("0");
		} else {
			cargarModelo();
			ventanaPresupuesto.getGuardar_btn().setVisible(false);
			ventanaPresupuesto.getCancelar_btn().setText("Cerrar");
			if(this.ingreso.getIngreso().getEstado()==3 && (perfil.equals("ADMINISTRATIVO") || perfil.equals("JEFE"))) {
				mostrarBotonInformado();
			}
			
			if(this.ingreso.getIngreso().getEstado()==4 && (perfil.equals("ADMINISTRATIVO") || perfil.equals("JEFE"))) {
				mostrarBotonAceptado();
			}
			
			if(this.ingreso.getIngreso().getEstado()==5 && (perfil.equals("TECNICO") || perfil.equals("JEFE"))) {
				mostrarBotonAsignar();
			}
		}
	}
	
	private void cargarIngreso() {
		ventanaPresupuesto.getNombreProductoTexto_lbl().setText(ingreso.ingr.getDescripcion());
		ventanaPresupuesto.getMarcaTexto_lbl().setText(ingreso.getMarca().getDetalle());;
		ventanaPresupuesto.getDescripcionFalla_txtArea().setText(ingreso.getIngreso().getDescripcion_falla());
		ventanaPresupuesto.getTipoTexto_lbl().setText(ingreso.getTipoproducto().getDetalle());
	}

	private void cargarComboComponentes() {
		
		for (RepuestoDTO c : presupuesto.obtenerRepuestos()){
			this.ventanaPresupuesto.getComponente_ComboBox().addItem(c.getDetalle());
		}
	}
	
	private void cargarModelo()
	{
		//cargo la lista de componentes
		actualizarTablaRepuestos();
		//cargo descripcion Breve
		this.ventanaPresupuesto.getDescripcionBreve_jTextArea().setText(this.presupuesto.getPresupuesto().getDescripcionBreve());
		//cargo descripcion tecnica
		this.ventanaPresupuesto.getDescripcionTecnica_jTextArea().setText(this.presupuesto.getPresupuesto().getDescripcionTecnica());
		//setear fecha de creacion del presupuesto
		this.ventanaPresupuesto.getFechaIngreso_lbl().setText(this.presupuesto.getPresupuesto().getFechacreacion().toString());
		//seteo la fecha de vencimiento
		this.ventanaPresupuesto.getVencimiento_Calendario().setDate(this.presupuesto.getPresupuesto().getFechavencimiento());
		//seteo el total de repuestos
		sumarTotales();
		//seteo el total de mano de obra
		this.ventanaPresupuesto.getManoDeObra_txf().setText(String.valueOf(this.presupuesto.getPresupuesto().getImporteManoObra()));
		//seteo la cantidad de horas totales
		this.ventanaPresupuesto.getHorasDeTrabajo_txf().setText(String.valueOf(this.presupuesto.getPresupuesto().getHorasTrabajo()));
		//seteo el total del presupuesto (que actualmente se guarda en mano de obra)
		this.ventanaPresupuesto.getValorPresupuestado_txf().setText(String.valueOf(this.presupuesto.getPresupuesto().getImporteTotal()));
		//TODO:Deberia setear el usuario que creo el presupuesto, por ahora seteo el lodeado hasta que hagamos el ABM de usuarios
		ventanaPresupuesto.getLbltecnico().setText(this.usuarioLogueado.getNombre()+" "+this.usuarioLogueado.getApellido());
		this.bloquearCampos();
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
			
		}else if (e.getSource() == this.ventanaPresupuesto.getEnviarPresupuesto_btn()){
			
			// envia email
			email = new EmailPresupuesto(this.ingreso, this.usuarioLogueado, this.ventanaPresupuesto);
			email.start();
			
		}else if(e.getSource() == this.ventanaPresupuesto.getBtnInformado()) {
			int response = JOptionPane.showConfirmDialog(null, "Ud. va a dar el presupuesto como Informado al cliente.<br> Esta seguro?", "Confirmar",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    if (response == JOptionPane.NO_OPTION) {
		      
		    } else if (response == JOptionPane.YES_OPTION) {
		    		IngresoLogDTO ingrLog = new IngresoLogDTO(0,this.ingreso.getId(), 4, null, usuarioLogueado.getId());
					IngresoLogDAO ingresoLogDAO = new IngresoLogDAO();
					//ingreso el estado
				ingresoLogDAO.insert(ingrLog);
				mostrarBotonAceptado();
				this.controladorVentanaPrincipal.cargar_tablaOrdenesTrabajo();
		    } else if (response == JOptionPane.CLOSED_OPTION) {
		      
		    }
		} else if(e.getSource() == this.ventanaPresupuesto.getBtnAceptado()) {
			int response = JOptionPane.showConfirmDialog(null, "Ud. va a dar el presupuesto como aceptado por el cliente. Esta seguro?", "Confirmar",
			        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    if (response == JOptionPane.NO_OPTION) {
		      
		    } else if (response == JOptionPane.YES_OPTION) {
		    		IngresoLogDTO ingrLog = new IngresoLogDTO(0,this.ingreso.getId(), 5, null, usuarioLogueado.getId());
					IngresoLogDAO ingresoLogDAO = new IngresoLogDAO();
					//ingreso el estado
				ingresoLogDAO.insert(ingrLog);
				if(perfil.equals("TECNICO") || perfil.equals("JEFE")){
					mostrarBotonAsignar();
				}else {
					ocultarBotonesEstados();
				}
				
				this.controladorVentanaPrincipal.cargar_tablaOrdenesTrabajo();
		    } else if (response == JOptionPane.CLOSED_OPTION) {
		      
		    }
		} else if(e.getSource() == this.ventanaPresupuesto.getBtnRechazado()) {
			int response = JOptionPane.showConfirmDialog(null, "Ud. va a dar el presupuesto como rechazado por el cliente. Esta seguro?", "Confirmar",
			        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    if (response == JOptionPane.NO_OPTION) {
		      
		    } else if (response == JOptionPane.YES_OPTION) {
		    	IngresoLogDTO ingrLog = new IngresoLogDTO(0,this.ingreso.getId(), 9, null, usuarioLogueado.getId());
				IngresoLogDAO ingresoLogDAO = new IngresoLogDAO();
					//ingreso el estado
				ingresoLogDAO.insert(ingrLog);
				ocultarBotonesEstados();
				
				this.controladorVentanaPrincipal.cargar_tablaOrdenesTrabajo();
		    } else if (response == JOptionPane.CLOSED_OPTION) {
		      
		    }   
		} else if(e.getSource() == this.ventanaPresupuesto.getBtnAsignar()) {
			int response = JOptionPane.showConfirmDialog(null, "Esta seguro que desea asignarse esta tarea para realizarla?", "Confirmar",
			        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    if (response == JOptionPane.NO_OPTION) {
		      
		    } else if (response == JOptionPane.YES_OPTION) {
		    		IngresoLogDTO ingrLog = new IngresoLogDTO(0,this.ingreso.getId(), 6, null, usuarioLogueado.getId());
					IngresoLogDAO ingresoLogDAO = new IngresoLogDAO();
					//ingreso el estado
				ingresoLogDAO.insert(ingrLog);
				ocultarBotonesEstados();
				this.controladorVentanaPrincipal.cargar_tablaOrdenesTrabajo();
		    } else if (response == JOptionPane.CLOSED_OPTION) {
		      
		    }
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
			this.presupuesto.getPresupuesto().setFechavencimiento(this.ventanaPresupuesto.getVencimiento_Calendario().getDate());
			this.presupuesto.getPresupuesto().setDescripcionBreve(this.ventanaPresupuesto.getDescripcionBreve_jTextArea().getText());
			this.presupuesto.getPresupuesto().setDescripcionTecnica(this.ventanaPresupuesto.getDescripcionTecnica_jTextArea().getText());
			this.presupuesto.getPresupuesto().setHorasTrabajo(Integer.parseInt(this.ventanaPresupuesto.getHorasDeTrabajo_txf().getText()));
			this.presupuesto.getPresupuesto().setImporteManoObra(Float.parseFloat(this.ventanaPresupuesto.getManoDeObra_txf().getText()));
			this.presupuesto.getPresupuesto().setImporteTotal(Float.parseFloat(this.ventanaPresupuesto.getTotal_lbl().getText()));
			this.presupuesto.getPresupuesto().setIdUsuario(this.usuarioLogueado.getId());
			
			Boolean ingreso = this.presupuesto.guardarPresupuesto(this.usuarioLogueado.getId());
			
			if(ingreso){
				JOptionPane.showMessageDialog(ventanaPresupuesto, "Presupuesto guardado correctamente", "Atencion!",
						JOptionPane.INFORMATION_MESSAGE);
				
				// envia email
				email = new EmailPresupuesto(this.ingreso, this.usuarioLogueado, this.ventanaPresupuesto);
				email.start();
				
				this.ventanaPresupuesto.dispose();
				this.controladorVentanaPrincipal.cargar_tablaOrdenesTrabajo();
			} else {
				JOptionPane.showMessageDialog(ventanaPresupuesto, "Ocurrio un error al guardar el presupuesto", "Atencion!",
						JOptionPane.ERROR_MESSAGE);
			}
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
		modelTable = new DefaultTableModel();
		modelTable.setColumnIdentifiers(ventanaPresupuesto.getComponentes_nombreColumnas());
		 
		List<ItemRepuestoDTO> repuestosAgregados = presupuesto.getListaDeRepuestos();
		
		for(int i = 0; i < repuestosAgregados.size(); i++){
			Object[] fila = {repuestosAgregados.get(i).getId(),repuestosAgregados.get(i).getDetalle(),
					repuestosAgregados.get(i).getCantidad(),repuestosAgregados.get(i).getPrecioUnitario(),
					repuestosAgregados.get(i).getPrecio()};

			modelTable.insertRow(0, fila);
		}
		
		this.ventanaPresupuesto.getComponentes_table().setModel(modelTable);
		
		ocultarColumnaId();
	}

	private void eliminarComponenteDeTabla() {

		if(this.ventanaPresupuesto.getComponentes_table().getSelectedRow() != -1) {

			int seleccionado = ventanaPresupuesto.getComponentes_table().getSelectedRow();
			String nombre = ventanaPresupuesto.getComponentes_table().getValueAt(seleccionado, 1).toString();
			
			for (int i = 0; i < this.presupuesto.getListaDeRepuestos().size(); i++) {
				if(this.presupuesto.getListaDeRepuestos().get(i).getDetalle().equals(nombre)) {
					this.presupuesto.getListaDeRepuestos().remove(i);
				}
			}
			
			actualizarTablaRepuestos();
			sumarTotales();
			sumarTotalComponentes();
		} else {

			JOptionPane.showMessageDialog(ventanaPresupuesto, "debe seleccionar fila para eliminar ", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void agregarRepuestoATabla() {

		modelTable.setColumnIdentifiers(ventanaPresupuesto.getComponentes_nombreColumnas());
		Boolean existe = false;
		//agrego el repuesto al objeto Presupuesto con su lista
		int cantidad = Integer.parseInt(ventanaPresupuesto.getCantidad_lbl().getText());
		RepuestoDTO resp = presupuesto.buscarRepuesto((String) this.ventanaPresupuesto.getComponente_ComboBox().getSelectedItem());
		ItemRepuestoDTO itemRepuesto = new ItemRepuestoDTO(-1 ,resp.getId(), resp.getDetalle(),cantidad, resp.getPrecioUnitario(),resp.getPrecioUnitario() * cantidad);
		//me fijo si existe en la tabla
		for (int i = 0; i < presupuesto.getListaDeRepuestos().size(); i++) {
			if(presupuesto.getListaDeRepuestos().get(i).getDetalle().equals(resp.getDetalle())) {
				existe = true;
				this.presupuesto.getListaDeRepuestos().get(i).sumarCantidad(cantidad);
				this.presupuesto.getListaDeRepuestos().get(i).sumarTotal(resp.getPrecioUnitario() * cantidad);
			}
		}
		if(!existe) {
			presupuesto.addRepuestoListaDeComponentes(itemRepuesto);
		}
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
	
	private void mostrarBotonInformado() {
		ventanaPresupuesto.getBtnInformado().setVisible(true);
		ventanaPresupuesto.getBtnAceptado().setVisible(false);
		ventanaPresupuesto.getBtnAsignar().setVisible(false);
		ventanaPresupuesto.getBtnRechazado().setVisible(false);
	}
	
	private void mostrarBotonAceptado() {
		ventanaPresupuesto.getBtnInformado().setVisible(false);
		ventanaPresupuesto.getBtnAceptado().setVisible(true);
		ventanaPresupuesto.getBtnAsignar().setVisible(false);
		ventanaPresupuesto.getBtnRechazado().setVisible(true);
	}
	
	private void mostrarBotonAsignar() {
		ventanaPresupuesto.getBtnInformado().setVisible(false);
		ventanaPresupuesto.getBtnAceptado().setVisible(false);
		ventanaPresupuesto.getBtnAsignar().setVisible(true);
		ventanaPresupuesto.getBtnRechazado().setVisible(false);
	}
	
	private void ocultarBotonesEstados() {
		ventanaPresupuesto.getBtnInformado().setVisible(false);
		ventanaPresupuesto.getBtnAceptado().setVisible(false);
		ventanaPresupuesto.getBtnAsignar().setVisible(false);
		ventanaPresupuesto.getBtnRechazado().setVisible(false);
	}
	private void  bloquearCampos(){
		
		this.ventanaPresupuesto.getComponente_ComboBox().setVisible(false);
		this.ventanaPresupuesto.getEliminarComponente_btn().setVisible(false);
		this.ventanaPresupuesto.getAgregarComponente_btn().setVisible(false);
		this.ventanaPresupuesto.getDescripcionBreve_jTextArea().setEditable(false);
		this.ventanaPresupuesto.getDescripcionTecnica_jTextArea().setEditable(false);
		this.ventanaPresupuesto.getDescripcionFalla_txtArea().setEditable(false);
		this.ventanaPresupuesto.getIncrementoCantComponente_btn().setVisible(false);
		this.ventanaPresupuesto.getCantidad_lbl().setVisible(false);
		this.ventanaPresupuesto.getDecrementoCantComponente_btn().setVisible(false);
		this.ventanaPresupuesto.getAgregarComponente_btn().setVisible(false);
		this.ventanaPresupuesto.getHorasDeTrabajo_txf().setEditable(false);
		this.ventanaPresupuesto.getManoDeObra_txf().setEditable(false);
		this.ventanaPresupuesto.getVencimiento_Calendario().setEnabled(false);
		this.ventanaPresupuesto.getValorPresupuestado_txf().setEditable(false);
		
		
	}
}
