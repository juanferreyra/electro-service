package presentacion.controlador;

/*
 * Razón Social: Alfanumérico 50 caracteres.
 * CUIT: 12 caracteres, solo numérico.
 * Dirección: 60 caracteres alfanuméricos.
 * Mail: (mail general de la empresa) control de formato válido. (si se complica desestimar el control)
 * Contacto:(Nombre del contacto) 40 caracteres alfanumérico.
 * Teléfono contacto: 50 caracteres alfanuméricos ( alfanumérico por si quiere ingresar interno o alguna acotación como barras y demás)
 * Email Contacto:  control de formato válido. (si se complica desestimar el control)
 * Email Pedido:(De acá hay que tomar el email para enviar el pedido).
 * Marcas: Una lista de las marcas. que será otra tabla que relaciona ProveedorID con InsumoID.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dto.RepuestoDTO;
import dto.ItemPresupuestoRepuestoDTO;
import dto.UsuarioDTO;
import modelo.Ingreso;
import modelo.Presupuesto;
import presentacion.vista.VentanaOrdenCompra;
import presentacion.vista.VentanaPresupuesto;

public class ControladorOrdenCompra implements ActionListener{
	
	private VentanaOrdenCompra ventanaOrdenCompra;
	private Presupuesto presupuesto;
	private UsuarioDTO usuarioLogueado;
	private DefaultTableModel modelTable = new DefaultTableModel();
	private Integer cantidad = 0;
	private float suma = 0;
	@SuppressWarnings("unused")
	private String perfil;
	
	public ControladorOrdenCompra( VentanaPresupuesto ventanaPresupuesto, Ingreso ingreso,
			ControladorVentanaPrincipal controladorVentanaPrincipal, UsuarioDTO usuario) {
		this.usuarioLogueado = usuario;
		this.perfil = usuario.getPerfilDTO().getPerfil();
	}
	
	public void inicializar() {
		ventanaOrdenCompra.setVisible(true);
		ventanaOrdenCompra.getComponentes_table().setModel(modelTable);
		cargarComboComponentes();
	}

	private void cargarComboComponentes() {
		
		for (RepuestoDTO c : presupuesto.obtenerRepuestos()){
			this.ventanaOrdenCompra.getComponente_ComboBox().addItem(c.getDetalle());
		}
	}
	
	@SuppressWarnings("unused")
	private void cargarModelo()
	{
		//TODO:Deberia setear todos los campos si se carga una
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		// suma y resta componentes
		if (e.getSource() == this.ventanaOrdenCompra.getIncrementoCantComponente_btn()){

			cantidad +=1;
			this.ventanaOrdenCompra.getCantidad_lbl().setText(cantidad.toString());
			
		}else if (e.getSource() == this.ventanaOrdenCompra.getDecrementoCantComponente_btn()){

			if(cantidad != 1){
				cantidad -=1;
				this.ventanaOrdenCompra.getCantidad_lbl().setText(cantidad.toString());
			}
			
		}else if(e.getSource() == this.ventanaOrdenCompra.getAgregarComponente_btn()){
			 
				agregarRepuestoATabla();
				ocultarColumnaId();
				sumarTotales();

		}else if(e.getSource() == this.ventanaOrdenCompra.getEliminarComponente_btn()){
			
			eliminarRepuestoDeTabla();
			
		}else if(e.getSource() == this.ventanaOrdenCompra.getGuardar_btn()){
			//TODO: logica para guardar la orden de compra
			validarCampos();
			
		}else if (e.getSource() == this.ventanaOrdenCompra.getCancelar_btn()){
		
			this.ventanaOrdenCompra.dispose();
			
		}else if(e.getSource() == this.ventanaOrdenCompra.getBtnRecibido()) {
			int response = JOptionPane.showConfirmDialog(null, "Ud. va a dar el presupuesto como Informado al cliente.<br> Esta seguro?", "Confirmar",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    if (response == JOptionPane.NO_OPTION) {
		      
		    } else if (response == JOptionPane.YES_OPTION) {
		    		//TODO: agrego la orden de trabajo en estado recibida
		    } else if (response == JOptionPane.CLOSED_OPTION) {
		      
		    }
		} else if(e.getSource() == this.ventanaOrdenCompra.getBtnCancelada()) {
			int response = JOptionPane.showConfirmDialog(null, "Ud. va a dar el presupuesto como aceptado por el cliente. Esta seguro?", "Confirmar",
			        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    if (response == JOptionPane.NO_OPTION) {
		      
		    } else if (response == JOptionPane.YES_OPTION) {
		    		
					//TODO: agrego la orden de compra en estado cancelado
		    } else if (response == JOptionPane.CLOSED_OPTION) {
		      
		    }
		}
    }

	private void ocultarColumnaId() {

		this.ventanaOrdenCompra.getComponentes_table().getColumnModel().getColumn(0).setMaxWidth(0);
		this.ventanaOrdenCompra.getComponentes_table().getColumnModel().getColumn(0).setMinWidth(0);
		this.ventanaOrdenCompra.getComponentes_table().getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		this.ventanaOrdenCompra.getComponentes_table().getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
	}

	private void validarCampos() {
		
		if(this.ventanaOrdenCompra.getComponentes_table().getRowCount() == 0){

			JOptionPane.showMessageDialog(ventanaOrdenCompra, "Campo NO INGRESADO COMPONENTES AL PRESUPUESTO  no puede estar vacio ", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
			
		}else{
			
			this.presupuesto.getPresupuesto().setImporteTotal(Float.parseFloat(this.ventanaOrdenCompra.getValorPresupuestado_txf().getText()));
			this.presupuesto.getPresupuesto().setIdUsuario(this.usuarioLogueado.getId());
			//TODO aca deberia guardar la orden de compra y mostrar el boton de imprimir
		}
	}
	
	@SuppressWarnings("unused")
	private boolean soloNumeros(String texto){
		try { 
			Integer.parseInt(texto); 
			return true; 
		} catch (Exception e) { 
			return false; 
		}
	}
	
	@SuppressWarnings("unused")
	private boolean soloFloat(String texto) {
		try {
			Float.parseFloat(texto);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	private void sumarTotales() {
		suma = 0;
		for (int i = 0 ; i < ventanaOrdenCompra.getComponentes_table().getRowCount(); i++){
			suma += Float.parseFloat(ventanaOrdenCompra.getComponentes_table().getValueAt(i, 4).toString());
		}
		this.ventanaOrdenCompra.getValorPresupuestado_txf().setText(String.valueOf(suma));
	}

	private void actualizarTablaRepuestos() {
		modelTable = new DefaultTableModel();
		modelTable.setColumnIdentifiers(ventanaOrdenCompra.getComponentes_nombreColumnas());
		 
		List<ItemPresupuestoRepuestoDTO> repuestosAgregados = presupuesto.getListaDeRepuestos();
		
		for(int i = 0; i < repuestosAgregados.size(); i++){
			Object[] fila = {repuestosAgregados.get(i).getId(),repuestosAgregados.get(i).getDetalle(),
					repuestosAgregados.get(i).getCantidad(),repuestosAgregados.get(i).getPrecioUnitario(),
					repuestosAgregados.get(i).getPrecio()};

			modelTable.insertRow(0, fila);
		}
		
		this.ventanaOrdenCompra.getComponentes_table().setModel(modelTable);
		
		ocultarColumnaId();
	}

	private void eliminarRepuestoDeTabla() {

		if(this.ventanaOrdenCompra.getComponentes_table().getSelectedRow() != -1) {

			int seleccionado = ventanaOrdenCompra.getComponentes_table().getSelectedRow();
			String nombre = ventanaOrdenCompra.getComponentes_table().getValueAt(seleccionado, 1).toString();
			
			for (int i = 0; i < this.presupuesto.getListaDeRepuestos().size(); i++) {
				if(this.presupuesto.getListaDeRepuestos().get(i).getDetalle().equals(nombre)) {
					this.presupuesto.getListaDeRepuestos().remove(i);
				}
			}
			
			actualizarTablaRepuestos();
			sumarTotales();
		} else {

			JOptionPane.showMessageDialog(ventanaOrdenCompra, "Debe seleccionar fila para eliminar ", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void agregarRepuestoATabla() {

		modelTable.setColumnIdentifiers(ventanaOrdenCompra.getComponentes_nombreColumnas());
		Boolean existe = false;
		//agrego el repuesto al objeto Presupuesto con su lista
		int cantidad = Integer.parseInt(ventanaOrdenCompra.getCantidad_lbl().getText());
		RepuestoDTO resp = presupuesto.buscarRepuesto((String) this.ventanaOrdenCompra.getComponente_ComboBox().getSelectedItem());
		ItemPresupuestoRepuestoDTO itemRepuesto = new ItemPresupuestoRepuestoDTO(-1 ,resp.getId(), resp.getDetalle(),cantidad, resp.getPrecioUnitario(),resp.getPrecioUnitario() * cantidad);
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



}
