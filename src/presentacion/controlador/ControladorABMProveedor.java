package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dto.MarcaDTO;
import dto.ProveedorDTO;
import dto.ProveedorMarcaDTO;
import modelo.Marca;
import modelo.Proveedor;
import modelo.ProveedorMarca;
import presentacion.vista.VentanaABMMarcaProducto;
import presentacion.vista.VentanaABMProveedor;


public class ControladorABMProveedor implements ActionListener{
	
	private VentanaABMProveedor ventanaABMProveedor;
	private Proveedor proveedor;
	private Marca marca;
	private List<ProveedorDTO> proveedores_en_tabla;
	private List<JTextField> txts;
	private List<MarcaDTO>marcas_en_tabla;
	private ProveedorMarca proveedorMarca;
	private List<MarcaDTO> marcasCombo;
	private List<MarcaDTO> marcasCombo1;

	
	public ControladorABMProveedor(VentanaABMProveedor ventanaABMProveedor) {
		
		this.ventanaABMProveedor = ventanaABMProveedor;
		this.ventanaABMProveedor.getEliminarItem_btn().addActionListener(this);
		this.ventanaABMProveedor.getLimpiar_btn().addActionListener(this);
		this.ventanaABMProveedor.getGuardar_btn().addActionListener(this);
		this.ventanaABMProveedor.getEliminarMarca().addActionListener(this);
		this.ventanaABMProveedor.getAgregarMarca().addActionListener(this);
		this.ventanaABMProveedor.getBtnMarcaNueva().addActionListener(this);

	}
	
	public void inicializar() {

		this.proveedor = new Proveedor();
		
		this.proveedorMarca = new ProveedorMarca();
		
		this.marca = new Marca();

		this.ventanaABMProveedor.setVisible(true);

		this.txts = new ArrayList<JTextField>();

		txts.add(this.ventanaABMProveedor.getRazonSocial_txt());// 0
		txts.add(this.ventanaABMProveedor.getCuit_txt());// 1
		txts.add(this.ventanaABMProveedor.getDireccion_txt());// 2
		txts.add(this.ventanaABMProveedor.getEmail_txt());// 3
		txts.add(this.ventanaABMProveedor.getNombreContacto_txt());// 4
		txts.add(this.ventanaABMProveedor.getTelefonoContacto_txt());// 5
		txts.add(this.ventanaABMProveedor.getEmailContacto_txt());// 6
		txts.add(this.ventanaABMProveedor.getEmailPedidos_txt());// 7
		
		cargarComboMarcas();
		cargarTablaProveedores();	
		mouseClickedOnTable();
		ocultarIdMarcas();

	}
	

	private void cargarComboMarcas() {
		
		this.marcasCombo = marca.obtenerMarcas();
		
		for(int i = 0; i < this.marcasCombo.size(); i++){
			this.ventanaABMProveedor.getAgregarMarca_jcmbox().addItem( this.marcasCombo.get(i).getDetalle() );	
		}
		this.ventanaABMProveedor.getAgregarMarca_jcmbox().setSelectedIndex(0);
		
	}

	private void mouseClickedOnTable() {
		this.ventanaABMProveedor.getTablaProveedores().addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 1 es igual a boton izquierdo del mouse
				if (arg0.getButton() == 1) {
					cargartxts();
					cargarTablaMarcas();
					vaciarComboMarcas();
					cargarComboMarcasProveedorSeleccionado();
				}
			}
		});
		
	}
	
	private void vaciarComboMarcas() {
		
		this.ventanaABMProveedor.getAgregarMarca_jcmbox().removeAllItems();
	}
	
	
	private void cargarComboMarcasProveedorSeleccionado() {
		
		this.marcasCombo1 = marca.obtenerMarcas();
		
		for(int i = 0; i < this.marcasCombo1.size(); i++){
			
			if(!isMarcaAgregadaEnTabla(this.marcasCombo.get(i).getDetalle())){
				
			this.ventanaABMProveedor.getAgregarMarca_jcmbox().addItem(this.marcasCombo.get(i).getDetalle());
			}
		}
		
	}

	private boolean isMarcaAgregadaEnTabla(String marca) {
		
		for (int i = 0; i < this.ventanaABMProveedor.getTablaMarcas().getRowCount(); i++){
			
			if(this.ventanaABMProveedor.getModelMarcas().getValueAt(i, 1).equals(marca)){
				
				 return true;
			}
		}
		return false;
	}

	private void cargartxts() {

		int filaSeleccionada = this.ventanaABMProveedor.getTablaProveedores().getSelectedRow();

		this.txts.get(0).setText((String) this.ventanaABMProveedor.getModelProveedores().getValueAt(filaSeleccionada, 1));
		this.txts.get(1).setText(String.valueOf(this.ventanaABMProveedor.getModelProveedores().getValueAt(filaSeleccionada, 2)));
		this.txts.get(2).setText((String)this.ventanaABMProveedor.getModelProveedores().getValueAt(filaSeleccionada, 3));
		this.txts.get(3).setText((String) this.ventanaABMProveedor.getModelProveedores().getValueAt(filaSeleccionada, 4));
		this.txts.get(4).setText((String) this.ventanaABMProveedor.getModelProveedores().getValueAt(filaSeleccionada, 5));
		this.txts.get(5).setText((String.valueOf(this.ventanaABMProveedor.getModelProveedores().getValueAt(filaSeleccionada, 6))));
		this.txts.get(6).setText((String) this.ventanaABMProveedor.getModelProveedores().getValueAt(filaSeleccionada, 7));
		this.txts.get(7).setText((String) this.ventanaABMProveedor.getModelProveedores().getValueAt(filaSeleccionada, 8));
	}
	

	private void cargarTablaProveedores() {

		this.ventanaABMProveedor.getModelProveedores().setRowCount(0);
		this.ventanaABMProveedor.getModelProveedores().setColumnCount(0);
		this.ventanaABMProveedor.getModelProveedores().setColumnIdentifiers(this.ventanaABMProveedor.getNombreColumnas());

		this.proveedores_en_tabla = proveedor.obtenerProveedores();

		for (int i = 0; i < this.proveedores_en_tabla.size(); i++) {

			Object[] fila = { this.proveedores_en_tabla.get(i).getId(), this.proveedores_en_tabla.get(i).getRazonSocial(),
					this.proveedores_en_tabla.get(i).getCuit(), this.proveedores_en_tabla.get(i).getDireccion(),
					this.proveedores_en_tabla.get(i).getEmail(), this.proveedores_en_tabla.get(i).getNombreContacto(),
					this.proveedores_en_tabla.get(i).getTelefonoContacto(), this.proveedores_en_tabla.get(i).getEmailContacto(),
					this.proveedores_en_tabla.get(i).getEmailPedidos()};

			this.ventanaABMProveedor.getModelProveedores().addRow(fila);

		}
		//ocultarColumnaId();
		
	}
	
	@SuppressWarnings("unused")
	private void ocultarColumnaId() {
		
		this.ventanaABMProveedor.getTablaProveedores().getColumnModel().getColumn(0).setMaxWidth(0);
		this.ventanaABMProveedor.getTablaProveedores().getColumnModel().getColumn(0).setMinWidth(0);
		this.ventanaABMProveedor.getTablaProveedores().getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		this.ventanaABMProveedor.getTablaProveedores().getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		
	}
	
	private void cargarTablaMarcas() {
		
		int filaSeleccionada = this.ventanaABMProveedor.getTablaProveedores().getSelectedRow();
		
		this.ventanaABMProveedor.getModelMarcas().setRowCount(0);
		this.ventanaABMProveedor.getModelMarcas().setColumnCount(0);
		this.ventanaABMProveedor.getModelMarcas().setColumnIdentifiers(this.ventanaABMProveedor.getNombreColumnasMarcas());

		this.marcas_en_tabla = proveedor.obtenerMarcasDelProveedor(
				(int)this.ventanaABMProveedor.getModelProveedores().getValueAt(filaSeleccionada, 0));

		for (int i = 0; i < this.marcas_en_tabla.size(); i++) {

			Object[] fila = { this.marcas_en_tabla.get(i).getId(), this.marcas_en_tabla.get(i).getDetalle(),};

			this.ventanaABMProveedor.getModelMarcas().addRow(fila);

		}
		ocultarIdMarcas();
	}
	
	private void ocultarIdMarcas() {
		
		// oculta columna id marca
				this.ventanaABMProveedor.getTablaMarcas().getColumnModel().getColumn(0).setMaxWidth(0);
				this.ventanaABMProveedor.getTablaMarcas().getColumnModel().getColumn(0).setMinWidth(0);
				this.ventanaABMProveedor.getTablaMarcas().getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
				this.ventanaABMProveedor.getTablaMarcas().getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.ventanaABMProveedor.getLimpiar_btn()){// boton limpiar

			this.limpiartxts();
			this.ventanaABMProveedor.getTablaProveedores().clearSelection();
			vaciarTablaMarcas();
			vaciarComboMarcas();
			cargarComboMarcas();
			

		}else if (e.getSource() == this.ventanaABMProveedor.getEliminarItem_btn()) {// boton eliminar

			// si la tabla no esta vacia
			if (this.ventanaABMProveedor.getTablaProveedores().getRowCount() != 0) {

				// si se selecciona una fila
				if (this.ventanaABMProveedor.getTablaProveedores().getSelectedRow() != -1) {

					int filaSeleccionada = this.ventanaABMProveedor.getTablaProveedores().getSelectedRow();
					int id_proveedor_a_eliminar = (int) this.ventanaABMProveedor.getModelProveedores().getValueAt(filaSeleccionada, 0);
					
					//elimino las marcas asociadas al proveedor a eliminar
					this.proveedorMarca.borrarTodos(id_proveedor_a_eliminar);
					
					// elimino el proveedor
					this.proveedor.borrarProveedor(id_proveedor_a_eliminar);
					cargarTablaProveedores();;
					limpiartxts();
					vaciarTablaMarcas();

				} else {

					JOptionPane.showMessageDialog(this.ventanaABMProveedor, "Debe seleccionar un proveedor a eliminar",
							"Atencion!", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {

				JOptionPane.showMessageDialog(this.ventanaABMProveedor, "No hay proveedores a eliminar", "Atencion!",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (e.getSource() == this.ventanaABMProveedor.getGuardar_btn()) {// boton guardar

			// si esta seleccionado de la tabla
			// modificar proveedor
			if (this.ventanaABMProveedor.getTablaProveedores().getSelectedRow() != -1) {

				int filaSeleccionada = this.ventanaABMProveedor.getTablaProveedores().getSelectedRow();

				if (!isTxtsVacios()) {

					if (isTxtsValidos()) {
						// guarda el proveedor
						proveedor.modificarProveedor(obtenerProveedor(
								(int) this.ventanaABMProveedor.getModelProveedores().getValueAt(filaSeleccionada, 0)));
						limpiartxts();
						cargarTablaProveedores();
						
						
						// borro todas las marcas del proveedor de la base
						proveedorMarca.borrarTodos((int) this.ventanaABMProveedor.getModelProveedores().getValueAt(filaSeleccionada, 0));
						
						// guarda las marcas del proveedor y le paso el id del proveedor seleccionado
						insertarMarcasDelProveedor((int) this.ventanaABMProveedor.getModelProveedores().getValueAt(filaSeleccionada, 0));
						
						vaciarTablaMarcas();
						cargarComboMarcas();
						
					}

				} else {

					JOptionPane.showMessageDialog(this.ventanaABMProveedor,
							"No se permiten campos vacios. Por favor, vuelva a intentarlo.","Atencion!",
							JOptionPane.INFORMATION_MESSAGE);
				}

			} else {
				// nuevo Proveedor

				if (!isTxtsVacios()) {

					if (isTxtsValidos()) {
						// agrega proveedor nuevo
						proveedor.AgregarProveedor(obtenerProveedor(0));

						limpiartxts();
						cargarTablaProveedores();
						
						// guarda las marcas del proveedor y le paso el id del proveedor nuevo
						insertarMarcasDelProveedor(proveedor.ultimaIdProveedor());
						
						vaciarTablaMarcas();
						cargarComboMarcas();
					}

				} else {

					JOptionPane.showMessageDialog(this.ventanaABMProveedor,
							"No se permiten campos vacios. Por favor, vuelva a intentarlo.");
				}

			}
			
		}else if(e.getSource() == this.ventanaABMProveedor.getEliminarMarca()){ // boton eliminar marca

			// si la tabla marcas no esta vacia
			if(this.ventanaABMProveedor.getTablaMarcas().getRowCount() != 0){

				// si no selecciono una marca
				if (this.ventanaABMProveedor.getTablaMarcas().getSelectedRow() == -1){

					JOptionPane.showMessageDialog(this.ventanaABMProveedor,
							"Debe seleccionar una marca para eliminar");
				}else{

					int fila = this.ventanaABMProveedor.getTablaMarcas().getSelectedRow();
					this.ventanaABMProveedor.getModelMarcas().removeRow(fila);
					
					vaciarComboMarcas();
					cargarComboMarcasProveedorSeleccionado();
					
					
				}

			}else{

				JOptionPane.showMessageDialog(this.ventanaABMProveedor,
						"no hay marcas para eliminar.");
			}


		}else if(e.getSource() == this.ventanaABMProveedor.getAgregarMarca()){// agrega marca a la tabla marcas
			
			// si el combo no esta vacio	
			if(this.ventanaABMProveedor.getAgregarMarca_jcmbox().getItemCount() != 0){

				// obtengo id y detalle 
				String detalle = (String)ventanaABMProveedor.getAgregarMarca_jcmbox().getSelectedItem();
				
				int idMarca = obtenerIdMarca(detalle);


				Object[] fila = {idMarca,detalle};

				ventanaABMProveedor.getModelMarcas().addRow(fila);

				vaciarComboMarcas();
				cargarComboMarcasProveedorSeleccionado();
			}
		}else if(e.getSource() == this.ventanaABMProveedor.getBtnMarcaNueva()){ // marca nueva
			
			VentanaABMMarcaProducto ventana = new VentanaABMMarcaProducto();
			ControladorABMMarcaProducto marca = new ControladorABMMarcaProducto(ventana);
			marca.inicializar();
			ventana.addWindowListener(new WindowListener() {
				
				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowClosed(WindowEvent e) {
					
					if(ventanaABMProveedor.getTablaMarcas().getSelectedRow() != -1){
						
						vaciarComboMarcas();
						cargarComboMarcas();
						
					}else{
						
						vaciarComboMarcas();
						cargarComboMarcasProveedorSeleccionado();

					}
				}
				
				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
		}
				
	}
	
	private int obtenerIdMarca(String detalle) {
		
		return marca.obtenerIdMarca(detalle);
	}

	private void insertarMarcasDelProveedor(int idProveedor) {
		
		for(int i = 0; i < this.ventanaABMProveedor.getTablaMarcas().getRowCount(); i++){
			
			ProveedorMarcaDTO proveedorMarca = new ProveedorMarcaDTO(0,idProveedor,
					(int)this.ventanaABMProveedor.getTablaMarcas().getModel().getValueAt(i, 0));
			
			this.proveedorMarca.agregar(proveedorMarca);
		
		}
		
		
		
	}

	private ProveedorDTO obtenerProveedor(int id) {
		
		ProveedorDTO proveedorDTO = new ProveedorDTO(
				id,
				this.txts.get(0).getText(), // razon social
				Integer.parseInt(this.txts.get(1).getText()), // cuit
				this.txts.get(2).getText(), // direccion
				this.txts.get(3).getText(), // email
				this.txts.get(4).getText(), // nombre contacto
				this.txts.get(5).getText(), // telefono contacto
				this.txts.get(6).getText(), // email contacto
				this.txts.get(7).getText(), // email pedido
				 1);

		return proveedorDTO;
	}

	private boolean isTxtsValidos() {
		
		boolean ret = true;

		if (!soloNumeros(this.txts.get(1).getText())) { // valida cuit

			JOptionPane.showMessageDialog(this.ventanaABMProveedor,
					"Disculpe, has ingresado un nro de cuit incorrecto.");
			return false;

		} else {

			ret = soloNumeros(this.txts.get(1).getText());
		}

		if (!validarEmail(this.txts.get(3).getText())) { // valida email

			JOptionPane.showMessageDialog(this.ventanaABMProveedor, "Disculpe, has ingresado EMAIL incorrecto.");
			return false;

		} else {

			ret = validarEmail(this.txts.get(3).getText());

		}
		
		if (!validarEmail(this.txts.get(6).getText())) { // valida email Contacto

			JOptionPane.showMessageDialog(this.ventanaABMProveedor, "Disculpe, has ingresado EMAIL CONTACTO incorrecto.");
			return false;

		} else {

			ret = validarEmail(this.txts.get(6).getText());

		}
		
		if (!validarEmail(this.txts.get(7).getText())) { // valida email pedido

			JOptionPane.showMessageDialog(this.ventanaABMProveedor, "Disculpe, has ingresado EMAIL PEDIDO incorrecto.");
			return false;

		} else {

			ret = validarEmail(this.txts.get(7).getText());

		}
		

		return ret;

	}

	private boolean isTxtsVacios() {

		boolean ret = false;

		for (JTextField jt : txts) {

			if (jt.getText().isEmpty()) {

				ret = true;
			}
		}
		return ret;
	}

	private void vaciarTablaMarcas() {
		
		for(int i = this.ventanaABMProveedor.getModelMarcas().getRowCount() - 1; i >= 0; i --){
			this.ventanaABMProveedor.getModelMarcas().removeRow(i);
			
		}
		
	}

	private void limpiartxts() {
		
		for (JTextField jt : txts) {
			jt.setText("");
		}
		
	}
	
	private boolean soloNumeros(String texto) {
		try {
			Integer.parseInt(texto);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean validarEmail(String email) {

		final String patternEemail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		// Compiles the given regular expression into a pattern.
		Pattern pattern = Pattern.compile(patternEemail);

		// Match the given input against this pattern
		Matcher matcher = pattern.matcher(email);

		return matcher.matches();
	}

	public static void main(String[] args) {
	VentanaABMProveedor abm = new VentanaABMProveedor();
	ControladorABMProveedor c =new ControladorABMProveedor(abm);
	 c.inicializar();
	}

}
