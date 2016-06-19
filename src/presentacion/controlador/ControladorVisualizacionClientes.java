package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import dto.ClienteDTO;
import modelo.Cliente;
import presentacion.vista.VentanaVisualizacionClientes;

public class ControladorVisualizacionClientes implements ActionListener {

	private Cliente cliente;
	private VentanaVisualizacionClientes ventanaVisualizacionClientes;
	private List<ClienteDTO> clientes_en_tabla;
	private ControladorVentanaIngreso controladorVentanaIngreso;

	public ControladorVisualizacionClientes(VentanaVisualizacionClientes ventanaVisualizacionClientes,
			ControladorVentanaIngreso controladorVentanaIngreso) {

		this.ventanaVisualizacionClientes = ventanaVisualizacionClientes;

		this.controladorVentanaIngreso = controladorVentanaIngreso;
	}

	public void inicializar() {

		this.cliente = new Cliente();

		this.ventanaVisualizacionClientes.setVisible(true);
		cargarTablaClientes();
		mouseClickedOnTable();

	}

	private void mouseClickedOnTable() {

		this.ventanaVisualizacionClientes.getTablaClientes().addMouseListener(new MouseListener() {

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
					if (ventanaVisualizacionClientes.getTablaClientes().getSelectedRow() != -1) {
						controladorVentanaIngreso.ventana_ingreso.getTxtNroCliente()
								.setText(ventanaVisualizacionClientes.getTablaClientes().getValueAt(
										ventanaVisualizacionClientes.getTablaClientes().getSelectedRow(), 3) + "");
						controladorVentanaIngreso.buscarCliente();
					}
					ventanaVisualizacionClientes.dispose();
				}

			}

		});
	}

	private void cargarTablaClientes() {

		this.ventanaVisualizacionClientes.getModelClientes().setRowCount(0);
		this.ventanaVisualizacionClientes.getModelClientes().setColumnCount(0);
		this.ventanaVisualizacionClientes.getModelClientes()
				.setColumnIdentifiers(this.ventanaVisualizacionClientes.getNombreColumnas());

		this.clientes_en_tabla = cliente.obtenerClientes();

		for (int i = 0; i < this.clientes_en_tabla.size(); i++) {

			Object[] fila = { this.clientes_en_tabla.get(i).getId(), this.clientes_en_tabla.get(i).getNombre(),
					this.clientes_en_tabla.get(i).getApellido(), this.clientes_en_tabla.get(i).getNroDoc(),
					this.clientes_en_tabla.get(i).getLocalidad(), this.clientes_en_tabla.get(i).getDireccion(),
					this.clientes_en_tabla.get(i).getTelefono(), this.clientes_en_tabla.get(i).getMail() };

			this.ventanaVisualizacionClientes.getModelClientes().addRow(fila);

		}
		ocultarColumnaId();
	}

	private void ocultarColumnaId() {

		this.ventanaVisualizacionClientes.getTablaClientes().getColumnModel().getColumn(0).setMaxWidth(0);
		this.ventanaVisualizacionClientes.getTablaClientes().getColumnModel().getColumn(0).setMinWidth(0);
		this.ventanaVisualizacionClientes.getTablaClientes().getTableHeader().getColumnModel().getColumn(0)
				.setMaxWidth(0);
		this.ventanaVisualizacionClientes.getTablaClientes().getTableHeader().getColumnModel().getColumn(0)
				.setMinWidth(0);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	// public static void main(String[] args) {
	//
	// VentanaVisualizacionClientes abm = new VentanaVisualizacionClientes();
	// ControladorVisualizacionClientes c = new
	// ControladorVisualizacionClientes(abm);
	// c.inicializar();
	//
	// }

}
