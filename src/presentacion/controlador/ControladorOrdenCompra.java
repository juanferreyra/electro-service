package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import dto.RepuestoDTO;
import dto.ItemRepuestoDTO;
import dto.ProveedorDTO;
import dto.UsuarioDTO;
import modelo.EmailOrdenDeCompra;
import modelo.OrdenCompra;
import persistencia.dao.ProveedorDAO;
import presentacion.reportes.ReporteOrdenCompra;
import presentacion.vista.VentanaABMProveedor;
import presentacion.vista.VentanaOrdenCompra;

public class ControladorOrdenCompra implements ActionListener{
	
	private VentanaOrdenCompra ventanaOrdenCompra;
	private OrdenCompra ordenCompra;
	private UsuarioDTO usuarioLogueado;
	private DefaultTableModel modelTable = new DefaultTableModel();
	private Integer cantidad = 1;
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
		this.ventanaOrdenCompra.getBtnCargarOrden().addActionListener(this);
		this.ventanaOrdenCompra.getBtnVaciarVentanaOrden().addActionListener(this);
		this.ventanaOrdenCompra.getBtnImprimir().addActionListener(this);
		this.ventanaOrdenCompra.getBtnEnviarEmial().addActionListener(this);
	}
	
	public void inicializar() {
		ventanaOrdenCompra.setVisible(true);
		ventanaOrdenCompra.getBtnVaciarVentanaOrden().setVisible(false);
		this.ventanaOrdenCompra.getBtnCancelada().setVisible(false);
		this.ventanaOrdenCompra.getBtnRecibido().setVisible(false);
		this.ventanaOrdenCompra.getBtnImprimir().setVisible(false);
		ventanaOrdenCompra.getComponentes_table().setModel(modelTable);
	}

	private void cargarComboComponentes() {
		this.ventanaOrdenCompra.getComponente_ComboBox().removeAllItems();
		
		for (RepuestoDTO c : ordenCompra.obtenerRepuestos()){
			this.ventanaOrdenCompra.getComponente_ComboBox().addItem(c.getDetalle());
		}
	}
	
	private void cargarModelo() {
		ordenCompra.cargarVariables();
		cargarProveedor(ordenCompra.getProveedorDTO());
		actualizarTablaRepuestos();
		this.ventanaOrdenCompra.getValorPresupuestado_txf().setText(String.valueOf( ordenCompra.getOrdenCompraDTO().getImporte_total()));
		
		if(!ordenCompra.getOrdenCompraDTO().getEstado().equals("NUEVA")) {
			this.ventanaOrdenCompra.getBtnCancelada().setVisible(false);
			this.ventanaOrdenCompra.getBtnRecibido().setVisible(false);
		} else {
			this.ventanaOrdenCompra.getBtnCancelada().setVisible(true);
			this.ventanaOrdenCompra.getBtnRecibido().setVisible(true);
		}
		
		this.ventanaOrdenCompra.getBtnBuscarProveedor().setEnabled(false);
		this.ventanaOrdenCompra.getAgregarComponente_btn().setEnabled(false);
		this.ventanaOrdenCompra.getBtnVerProveedores().setEnabled(false);
		this.ventanaOrdenCompra.getGuardar_btn().setVisible(false);
		this.ventanaOrdenCompra.getCancelar_btn().setText("Cerrar");
		this.ventanaOrdenCompra.getIncrementoCantComponente_btn().setEnabled(false);
		this.ventanaOrdenCompra.getDecrementoCantComponente_btn().setEnabled(false);
		this.ventanaOrdenCompra.getEliminarComponente_btn().setEnabled(false);
		this.ventanaOrdenCompra.getComponente_ComboBox().setEnabled(false);
		this.ventanaOrdenCompra.getBtnImprimir().setVisible(true);
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
			if(this.ventanaOrdenCompra.getComponente_ComboBox().getSelectedIndex()!= -1) {
				agregarRepuestoATabla();
				ocultarColumnaId();
				sumarTotales();
			} else {

				JOptionPane.showMessageDialog(ventanaOrdenCompra, "No puede agregar sin seleccionar un repuesto ", "Atencion!",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}else if(e.getSource() == this.ventanaOrdenCompra.getEliminarComponente_btn()){
			
			eliminarRepuestoDeTabla();
			
		}else if(e.getSource() == this.ventanaOrdenCompra.getGuardar_btn()){
			
			validarCampos();
			this.ventanaOrdenCompra.getBtnCancelada().setVisible(false);
			this.ventanaOrdenCompra.getBtnRecibido().setVisible(false);
			
		}else if (e.getSource() == this.ventanaOrdenCompra.getCancelar_btn()){
		
			this.ventanaOrdenCompra.dispose();
			
		}else if(e.getSource() == this.ventanaOrdenCompra.getBtnRecibido()) {
			
			int response = JOptionPane.showConfirmDialog(null, "Ud. va a dar el presupuesto como Recibido la orden de compra. Realmente esta seguro?", "Confirmar",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    if (response == JOptionPane.NO_OPTION) {
		      
		    } else if (response == JOptionPane.YES_OPTION) {
		    	
		    	ordenCompra.actualizarEstado("RECIBIDA");
		    	this.ventanaOrdenCompra.getBtnCancelada().setVisible(false);
				this.ventanaOrdenCompra.getBtnRecibido().setVisible(false);
		    	
		    } else if (response == JOptionPane.CLOSED_OPTION) {
		    	
		    }
		} else if(e.getSource() == this.ventanaOrdenCompra.getBtnCancelada()) {
			int response = JOptionPane.showConfirmDialog(null, "Ud. va a dar el presupuesto como cancelada . Realmente esta seguro?", "Confirmar",
			        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    if (response == JOptionPane.NO_OPTION) {
		      
		    } else if (response == JOptionPane.YES_OPTION) {
		    	
		    	ordenCompra.actualizarEstado("CANCELADA");
		    	this.ventanaOrdenCompra.getBtnCancelada().setVisible(false);
				this.ventanaOrdenCompra.getBtnRecibido().setVisible(false);
		    	
		    } else if (response == JOptionPane.CLOSED_OPTION) {
		      
		    }
		} else if(e.getSource() == this.ventanaOrdenCompra.getBtnBuscarProveedor()) {
			buscarProveedor();
		} else if(e.getSource() == this.ventanaOrdenCompra.getBtnVerProveedores()) {
			VentanaABMProveedor ventanaABMProveedor = new VentanaABMProveedor();
			ControladorABMProveedor cp = new ControladorABMProveedor(ventanaABMProveedor);
	    	cp.inicializar();
		} else if(e.getSource() == this.ventanaOrdenCompra.getBtnCargarOrden()) {
			String nroOrdenString = this.ventanaOrdenCompra.getTxtfldCargarOrden().getText();
			try {
				int nroOrden = Integer.parseInt(nroOrdenString);
				
				if(ordenCompra.existeOrdenCompra(nroOrden)){
					ordenCompra.getOrdenCompraDTO().setId(nroOrden);
					cargarModelo();
					this.ventanaOrdenCompra.getBtnVaciarVentanaOrden().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(ventanaOrdenCompra, "No se encontro ninguna hoja de ruta con ese nro", "Atencion!",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(this.ventanaOrdenCompra,
						"El n�mero de proveedor es incorrecto, vuelva a intentarlo. ", null,
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else if(e.getSource() == this.ventanaOrdenCompra.getBtnVaciarVentanaOrden()) {
			
			vaciarCampos();
			this.ventanaOrdenCompra.getBtnVaciarVentanaOrden().setVisible(false);
			this.ventanaOrdenCompra.getBtnBuscarProveedor().setEnabled(true);
			this.ventanaOrdenCompra.getAgregarComponente_btn().setEnabled(true);
			this.ventanaOrdenCompra.getBtnVerProveedores().setEnabled(true);
			this.ventanaOrdenCompra.getGuardar_btn().setVisible(true);
			this.ventanaOrdenCompra.getCancelar_btn().setText("Cancelar");
			this.ventanaOrdenCompra.getIncrementoCantComponente_btn().setEnabled(true);
			this.ventanaOrdenCompra.getDecrementoCantComponente_btn().setEnabled(true);
			this.ventanaOrdenCompra.getEliminarComponente_btn().setEnabled(true);
			this.ventanaOrdenCompra.getComponente_ComboBox().setEnabled(true);
			this.ventanaOrdenCompra.getBtnImprimir().setVisible(false);
			
		} else if(e.getSource() == this.ventanaOrdenCompra.getBtnImprimir()) {
			
			ReporteOrdenCompra reporte = new ReporteOrdenCompra(ordenCompra.getDatosImpresion());
			reporte.mostrar();
			
		}else if(e.getSource() == this.ventanaOrdenCompra.getBtnEnviarEmial()){
			
			EmailOrdenDeCompra emailOrdenDeCompra = new EmailOrdenDeCompra(usuarioLogueado, this.ventanaOrdenCompra);
			emailOrdenDeCompra.start();
			
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

			JOptionPane.showMessageDialog(ventanaOrdenCompra, "Dele al menos ingresar un repuesto a la orden de compra para continuar! ", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
			
		} else if(this.ventanaOrdenCompra.getNombreRazonSocialTexto_lbl().equals("")) {
			
			JOptionPane.showMessageDialog(ventanaOrdenCompra, "Debe ingresar un proveedor para continuar ", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
			
		} else {
			ordenCompra.getOrdenCompraDTO().setImporte_total(Float.parseFloat(this.ventanaOrdenCompra.getValorPresupuestado_txf().getText()));
			ordenCompra.getOrdenCompraDTO().setIdusuario(this.usuarioLogueado.getId());
			
			Boolean guardo = ordenCompra.crearOrdenCompra();
			if(guardo) {
				JOptionPane.showMessageDialog(ventanaOrdenCompra, "Orden de compra Nro "+ordenCompra.getOrdenCompraDTO().getId()+" creada correctamente!", "Atencion!",
						JOptionPane.INFORMATION_MESSAGE);
				vaciarCampos();
			} else {
				JOptionPane.showMessageDialog(ventanaOrdenCompra, "Ocurrio un error al guardar! Por favor verifique los datos ingresados o recomience la orden. Gracias", "Atencion!",
						JOptionPane.ERROR_MESSAGE);
			}
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
			JOptionPane.showMessageDialog(this.ventanaOrdenCompra, "Por favor, ingrese un n�mero de proveedor.", null,
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			try {
				int nroproveedor = Integer.parseInt(textoingresado);
				
				if(this.ventanaOrdenCompra.getComponentes_table().getRowCount() > 0) {
					int response = JOptionPane.showConfirmDialog(null, "Hay repuestos cargados. Si carga un proveedor nuevo se borraran los repuestos ingresados. Esta seguro de continuar la busqueda?", "Atencion",
					        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    if (response == JOptionPane.NO_OPTION) {
				      
				    } else if (response == JOptionPane.YES_OPTION) {
				    	
				    	verificarYCargarProveedor(nroproveedor);
				    	
				    } else if (response == JOptionPane.CLOSED_OPTION) {
				      
				    }
				} else {
					verificarYCargarProveedor(nroproveedor);
				}
			
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(this.ventanaOrdenCompra,
						"El n�mero de proveedor es incorrecto, vuelva a intentarlo. ", null,
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	private void verificarYCargarProveedor(int nroproveedor) {
		
		ProveedorDAO cdao = this.ordenCompra.getProveedorDAO();
		ProveedorDTO cdto = cdao.find(nroproveedor);
		
		
		if (cdto == null) {
			int response2 = JOptionPane.showConfirmDialog(null, "El proveedor buscado no existe desea buscarlo y/o crear uno nuevo?", "Atencion",
			        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    if (response2 == JOptionPane.NO_OPTION) {
		      
		    } else if (response2 == JOptionPane.YES_OPTION) {
		    	
		    	VentanaABMProveedor ventanaABMProveedor = new VentanaABMProveedor();
				ControladorABMProveedor cp = new ControladorABMProveedor(ventanaABMProveedor);
		    	cp.inicializar();
		    	
		    } else if (response2 == JOptionPane.CLOSED_OPTION) {
		      
		    }
		} else {
			vaciarCampos();
			ordenCompra.setProveedorDTO(cdto);
			cargarProveedor(cdto);
			ordenCompra.actualizarListaMarcas();
			cargarComboComponentes();
		}
	}
	
	private void cargarProveedor(ProveedorDTO cdto) {
		this.ventanaOrdenCompra.getNombreRazonSocialTexto_lbl().setText(cdto.getRazonSocial());
		this.ventanaOrdenCompra.getDireccionTexto_lbl().setText(cdto.getDireccion());
		this.ventanaOrdenCompra.getMailTexto_lbl().setText(cdto.getEmailPedidos());
	}
	
	private void vaciarCampos() {
		this.ventanaOrdenCompra.getComponente_ComboBox().removeAllItems();
		this.ventanaOrdenCompra.getNombreRazonSocialTexto_lbl().setText("");
		this.ventanaOrdenCompra.getDireccionTexto_lbl().setText("");
		this.ventanaOrdenCompra.getMailTexto_lbl().setText("");
		this.ventanaOrdenCompra.getValorPresupuestado_txf().setText("");
		this.ordenCompra = new OrdenCompra();
		actualizarTablaRepuestos();
	}
}
