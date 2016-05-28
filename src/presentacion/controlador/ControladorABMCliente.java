package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JOptionPane;

import dto.ClienteDTO;
import modelo.Cliente;
import presentacion.vista.VentanaABMCliente;

public class ControladorABMCliente implements ActionListener{
	
	private Cliente cliente ;
	private VentanaABMCliente ventanaABMCliente;
	private List<ClienteDTO> clientes_en_tabla;
	
	
	public ControladorABMCliente(VentanaABMCliente ventanaABMCliente){
		
		this.ventanaABMCliente = ventanaABMCliente;
		this.ventanaABMCliente.getCancelar_btn().addActionListener(this);
		this.ventanaABMCliente.getEliminarItem_btn().addActionListener(this);
		this.ventanaABMCliente.getGuardar_btn().addActionListener(this);
		
	}
	
	
	public void inicializar(){

		this.cliente = new Cliente();

		this.ventanaABMCliente.setVisible(true);

		cargarTablaClientes();
		mouseClickedOnTable();

	}

	private void mouseClickedOnTable() {
		
		this.ventanaABMCliente.getTablaClientes().addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 1 es igual a boton izquierdo del mouse
				if(arg0.getButton() == 1 ){
					cargartxts();
					
				}

			}

			
		});
	}
	
	private void cargartxts() {
		
		int filaSeleccionada = this.ventanaABMCliente.getTablaClientes().getSelectedRow();
		
		this.ventanaABMCliente.getNombre_txt().setText((String)this.ventanaABMCliente.getModelClientes().getValueAt(filaSeleccionada, 1));
		this.ventanaABMCliente.getApellido_txt().setText((String)this.ventanaABMCliente.getModelClientes().getValueAt(filaSeleccionada, 2));
		this.ventanaABMCliente.getDocumento_txt().setText(String.valueOf(this.ventanaABMCliente.getModelClientes().getValueAt(filaSeleccionada, 3)));
		this.ventanaABMCliente.getLocalidad_txt().setText((String)this.ventanaABMCliente.getModelClientes().getValueAt(filaSeleccionada, 4));
		this.ventanaABMCliente.getDireccion_txt().setText((String)this.ventanaABMCliente.getModelClientes().getValueAt(filaSeleccionada, 5));
		this.ventanaABMCliente.getTelefono_txt().setText((String.valueOf(this.ventanaABMCliente.getModelClientes().getValueAt(filaSeleccionada, 6))));
		this.ventanaABMCliente.getEmail_txt().setText((String)this.ventanaABMCliente.getModelClientes().getValueAt(filaSeleccionada, 7));
	}


	private void cargarTablaClientes() {

		this.ventanaABMCliente.getModelClientes().setRowCount(0);
		this.ventanaABMCliente.getModelClientes().setColumnCount(0);
		this.ventanaABMCliente.getModelClientes().setColumnIdentifiers(this.ventanaABMCliente.getNombreColumnas());

		this.clientes_en_tabla = cliente.obtenerClientes();


		for (int i = 0; i < this.clientes_en_tabla.size(); i ++)
		{

			Object[] fila = {this.clientes_en_tabla.get(i).getId(), 
					this.clientes_en_tabla.get(i).getNombre(), 
					this.clientes_en_tabla.get(i).getApellido(),
					this.clientes_en_tabla.get(i).getNroDoc(),
					this.clientes_en_tabla.get(i).getLocalidad(),
					this.clientes_en_tabla.get(i).getDireccion(),
					this.clientes_en_tabla.get(i).getTelefono(),
					this.clientes_en_tabla.get(i).getMail()};

			this.ventanaABMCliente.getModelClientes().addRow(fila);
			
			ocultarColumnaId();

		}


	}

	private void ocultarColumnaId() {

		this.ventanaABMCliente.getTablaClientes().getColumnModel().getColumn(0).setMaxWidth(0);
		this.ventanaABMCliente.getTablaClientes().getColumnModel().getColumn(0).setMinWidth(0);
		this.ventanaABMCliente.getTablaClientes().getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		this.ventanaABMCliente.getTablaClientes().getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.ventanaABMCliente.getCancelar_btn()){
			
			this.ventanaABMCliente.dispose();
			
		}else if (e.getSource() == this.ventanaABMCliente.getEliminarItem_btn()){
			
			// si la tabla no esta vacía
			if(this.ventanaABMCliente.getTablaClientes().getRowCount() != 0){

				// si se seleccionó una fila
				if(this.ventanaABMCliente.getTablaClientes().getSelectedRow() != -1){

					int filaSeleccionada = this.ventanaABMCliente.getTablaClientes().getSelectedRow();
					int id_cliente_a_eliminar = (int)this.ventanaABMCliente.getModelClientes().getValueAt(filaSeleccionada, 0);


					cliente.borrarCliente(id_cliente_a_eliminar);
					cargarTablaClientes();
					limpiartxts();

				}else{

					JOptionPane.showMessageDialog(this.ventanaABMCliente, "Debe seleccionar un cliente a eliminar", "Atencion!",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}else{
				
				JOptionPane.showMessageDialog(this.ventanaABMCliente, "No hay clientes a eliminar", "Atencion!",
						JOptionPane.INFORMATION_MESSAGE);
			}

		}

		

	}
	
	

	private void limpiartxts() {
		
		this.ventanaABMCliente.getNombre_txt().setText("");
		this.ventanaABMCliente.getApellido_txt().setText("");
		this.ventanaABMCliente.getDocumento_txt().setText("");
		this.ventanaABMCliente.getLocalidad_txt().setText("");
		this.ventanaABMCliente.getDireccion_txt().setText("");
		this.ventanaABMCliente.getTelefono_txt().setText("");
		this.ventanaABMCliente.getEmail_txt().setText("");
		
	}


	public static void main(String[] args) {

		VentanaABMCliente abm = new VentanaABMCliente();
		ControladorABMCliente c = new ControladorABMCliente(abm);
		c.inicializar();


	}

}
