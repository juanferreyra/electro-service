package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
			
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		
		VentanaABMCliente abm = new VentanaABMCliente();
		ControladorABMCliente c = new ControladorABMCliente(abm);
		c.inicializar();
		
		
	}

}
