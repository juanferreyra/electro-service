package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import dto.RepuestoDTO;
import dto.ItemRepuestoDTO;
import dto.PerfilDTO;
import dto.ProveedorDTO;
import dto.UsuarioDTO;
import modelo.OrdenCompra;
import persistencia.dao.ProveedorDAO;
import presentacion.vista.VentanaABMProveedor;
import presentacion.vista.VentanaOrdenCompra;

public class ControladorOrdenCompra implements ActionListener{
	
	private VentanaOrdenCompra ventanaOrdenCompra;
	private OrdenCompra ordenCompra;
	private UsuarioDTO usuarioLogueado;
	private DefaultTableModel modelTable = new DefaultTableModel();
	private Integer cantidad = 0;
	private float suma = 0;
	@SuppressWarnings("unused")
	private String perfil;
	
	public ControladorOrdenCompra( VentanaOrdenCompra VentanaOrdenCompra, UsuarioDTO usuario) {
		this.ventanaOrdenCompra = VentanaOrdenCompra;
		this.usuarioLogueado = usuario;
		this.perfil = usuario.getPerfilDTO().getPerfil();
		this.ordenCompra = new OrdenCompra();
		
		this.ventanaOrdenCompra.getBtnCancelada().addActionListener(this);
		this.ventanaOrdenCompra.getBtnRecibido().addActionListener(this);
		this.ventanaOrdenCompra.getAgregarComponente_btn().addActionListener(this);
		this.ventanaOrdenCompra.getCancelar_btn().addActionListener(this);
		this.ventanaOrdenCompra.getDecrementoCantComponente_btn().addActionListener(this);
		this.ventanaOrdenCompra.getIncrementoCantComponente_btn().addActionListener(this);
		this.ventanaOrdenCompra.getEliminarComponente_btn().addActionListener(this);
		this.ventanaOrdenCompra.getGuardar_btn().addActionListener(this);
		this.ventanaOrdenCompra.getBtnBuscarProveedor().addActionListener(this);
		this.ventanaOrdenCompra.getBtnVerProveedores().addActionListener(this);
	}
	
	public void inicializar() {
		ventanaOrdenCompra.setVisible(true);
		ventanaOrdenCompra.getComponentes_table().setModel(modelTable);
	}

	private void cargarComboComponentes() {
		for (RepuestoDTO c : ordenCompra.obtenerRepuestos()){
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
			int response = JOptionPane.showConfirmDialog(null, "Ud. va a dar el presupuesto como Recibido la orden de compra. Realmente esta seguro?", "Confirmar",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    if (response == JOptionPane.NO_OPTION) {
		      
		    } else if (response == JOptionPane.YES_OPTION) {
		    	//TODO: agrego la orden de trabajo en estado recibida
		    } else if (response == JOptionPane.CLOSED_OPTION) {
		    	
		    }
		} else if(e.getSource() == this.ventanaOrdenCompra.getBtnCancelada()) {
			int response = JOptionPane.showConfirmDialog(null, "Ud. va a dar el presupuesto como cancelada . Realmente esta seguro?", "Confirmar",
			        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    if (response == JOptionPane.NO_OPTION) {
		      
		    } else if (response == JOptionPane.YES_OPTION) {
		    		
				//TODO: agrego la orden de compra en estado cancelado
		    } else if (response == JOptionPane.CLOSED_OPTION) {
		      
		    }
		} else if(e.getSource() == this.ventanaOrdenCompra.getBtnBuscarProveedor()) {
			buscarProveedor();
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
			
			this.ordenCompra.getOrdenCompraDTO().setImporte_total(Float.parseFloat(this.ventanaOrdenCompra.getValorPresupuestado_txf().getText()));
			this.ordenCompra.getOrdenCompraDTO().setIdusuario(this.usuarioLogueado.getId());
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
		 
		ArrayList<ItemRepuestoDTO> repuestosAgregados = ordenCompra.getListaDeRepuestos();
		
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
			
			for (int i = 0; i < this.ordenCompra.getListaDeRepuestos().size(); i++) {
				if(this.ordenCompra.getListaDeRepuestos().get(i).getDetalle().equals(nombre)) {
					this.ordenCompra.getListaDeRepuestos().remove(i);
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
		RepuestoDTO resp = ordenCompra.buscarRepuesto((String) this.ventanaOrdenCompra.getComponente_ComboBox().getSelectedItem());
		ItemRepuestoDTO itemRepuesto = new ItemRepuestoDTO(-1 ,resp.getId(), resp.getDetalle(),cantidad, resp.getPrecioUnitario(),resp.getPrecioUnitario() * cantidad);
		//me fijo si existe en la tabla
		for (int i = 0; i < ordenCompra.getListaDeRepuestos().size(); i++) {
			if(ordenCompra.getListaDeRepuestos().get(i).getDetalle().equals(resp.getDetalle())) {
				existe = true;
				this.ordenCompra.getListaDeRepuestos().get(i).sumarCantidad(cantidad);
				this.ordenCompra.getListaDeRepuestos().get(i).sumarTotal(resp.getPrecioUnitario() * cantidad);
			}
		}
		if(!existe) {
			ordenCompra.addRepuestoListaDeComponentes(itemRepuesto);
		}
		//actualizo la tabla de repuestos
		actualizarTablaRepuestos();
	}
	
	private void buscarProveedor() {
		String textoingresado = this.ventanaOrdenCompra.getTxtfldNroProveedor().getText();
		if (textoingresado == null || textoingresado.equals("")) {
			JOptionPane.showMessageDialog(this.ventanaOrdenCompra, "Por favor, ingrese un número de proveedor.", null,
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			try {
				int nroproveedor = Integer.parseInt(textoingresado);
				
				ProveedorDAO cdao = this.ordenCompra.getProveedorDAO();
				ProveedorDTO cdto = cdao.find(nroproveedor);
				if (cdto == null) {
					int response = JOptionPane.showConfirmDialog(null, "El proveedor buscado no existe desea buscarlo y/o crear uno nuevo?", "Atencion",
					        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    if (response == JOptionPane.NO_OPTION) {
				      
				    } else if (response == JOptionPane.YES_OPTION) {
				    	
				    	VentanaABMProveedor ventanaABMProveedor = new VentanaABMProveedor();
						ControladorABMProveedor cp = new ControladorABMProveedor(ventanaABMProveedor);
				    	cp.inicializar();
				    	
				    } else if (response == JOptionPane.CLOSED_OPTION) {
				      
				    }
				} else {
					ordenCompra.setProveedorDTO(cdto);
					ordenCompra.actualizarListaMarcas();
					cargarProveedor(cdto);
					cargarComboComponentes();
				}
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(this.ventanaOrdenCompra,
						"El número de proveedor es incorrecto, vuelva a intentarlo. ", null,
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	private void cargarProveedor(ProveedorDTO cdto) {
		this.ventanaOrdenCompra.getNombreRazonSocialTexto_lbl().setText(cdto.getRazonSocial());
		this.ventanaOrdenCompra.getDireccionTexto_lbl().setText(cdto.getDireccion());
		this.ventanaOrdenCompra.getMailTexto_lbl().setText(cdto.getEmail());
	}

	public static void main(String[] args) {
		PerfilDTO perf1 = new PerfilDTO(0,"ADMINISTRATIVO");
		UsuarioDTO user1 = new UsuarioDTO(1, "ROBERTO", "CARLOS", "admin", perf1);
		ControladorOrdenCompra a = new ControladorOrdenCompra(new VentanaOrdenCompra(), user1);
		a.inicializar();
	}
}
