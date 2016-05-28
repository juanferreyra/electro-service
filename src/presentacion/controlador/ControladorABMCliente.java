package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dto.ClienteDTO;
import modelo.Cliente;
import presentacion.vista.VentanaABMCliente;

public class ControladorABMCliente implements ActionListener {

	private Cliente cliente;
	private VentanaABMCliente ventanaABMCliente;
	private List<ClienteDTO> clientes_en_tabla;
	private List<JTextField> txts;

	public ControladorABMCliente(VentanaABMCliente ventanaABMCliente) {

		this.ventanaABMCliente = ventanaABMCliente;
		this.ventanaABMCliente.getEliminarItem_btn().addActionListener(this);
		this.ventanaABMCliente.getGuardar_btn().addActionListener(this);
		this.ventanaABMCliente.getLimpiar_btn().addActionListener(this);
	}

	public void inicializar() {

		this.cliente = new Cliente();

		this.ventanaABMCliente.setVisible(true);

		this.txts = new ArrayList<JTextField>();

		txts.add(this.ventanaABMCliente.getNombre_txt());// 0
		txts.add(this.ventanaABMCliente.getApellido_txt());// 1
		txts.add(this.ventanaABMCliente.getDocumento_txt());// 2
		txts.add(this.ventanaABMCliente.getLocalidad_txt());// 3
		txts.add(this.ventanaABMCliente.getDireccion_txt());// 4
		txts.add(this.ventanaABMCliente.getTelefono_txt());// 5
		txts.add(this.ventanaABMCliente.getEmail_txt());// 6

		cargarTablaClientes();
		mouseClickedOnTable();

	}

	private void mouseClickedOnTable() {

		this.ventanaABMCliente.getTablaClientes().addMouseListener(new MouseListener() {

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

				}

			}

		});
	}

	private void cargartxts() {

		int filaSeleccionada = this.ventanaABMCliente.getTablaClientes().getSelectedRow();

		this.txts.get(0).setText((String) this.ventanaABMCliente.getModelClientes().getValueAt(filaSeleccionada, 1));
		this.txts.get(1).setText((String) this.ventanaABMCliente.getModelClientes().getValueAt(filaSeleccionada, 2));
		this.txts.get(2)
				.setText(String.valueOf(this.ventanaABMCliente.getModelClientes().getValueAt(filaSeleccionada, 3)));
		this.txts.get(3).setText((String) this.ventanaABMCliente.getModelClientes().getValueAt(filaSeleccionada, 4));
		this.txts.get(4).setText((String) this.ventanaABMCliente.getModelClientes().getValueAt(filaSeleccionada, 5));
		this.txts.get(5)
				.setText((String.valueOf(this.ventanaABMCliente.getModelClientes().getValueAt(filaSeleccionada, 6))));
		this.txts.get(6).setText((String) this.ventanaABMCliente.getModelClientes().getValueAt(filaSeleccionada, 7));
	}

	private void cargarTablaClientes() {

		this.ventanaABMCliente.getModelClientes().setRowCount(0);
		this.ventanaABMCliente.getModelClientes().setColumnCount(0);
		this.ventanaABMCliente.getModelClientes().setColumnIdentifiers(this.ventanaABMCliente.getNombreColumnas());

		this.clientes_en_tabla = cliente.obtenerClientes();

		for (int i = 0; i < this.clientes_en_tabla.size(); i++) {

			Object[] fila = { this.clientes_en_tabla.get(i).getId(), this.clientes_en_tabla.get(i).getNombre(),
					this.clientes_en_tabla.get(i).getApellido(), this.clientes_en_tabla.get(i).getNroDoc(),
					this.clientes_en_tabla.get(i).getLocalidad(), this.clientes_en_tabla.get(i).getDireccion(),
					this.clientes_en_tabla.get(i).getTelefono(), this.clientes_en_tabla.get(i).getMail() };

			this.ventanaABMCliente.getModelClientes().addRow(fila);

		}
		ocultarColumnaId();
	}

	private void ocultarColumnaId() {

		this.ventanaABMCliente.getTablaClientes().getColumnModel().getColumn(0).setMaxWidth(0);
		this.ventanaABMCliente.getTablaClientes().getColumnModel().getColumn(0).setMinWidth(0);
		this.ventanaABMCliente.getTablaClientes().getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		this.ventanaABMCliente.getTablaClientes().getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.ventanaABMCliente.getEliminarItem_btn()) {

			// si la tabla no esta vacia
			if (this.ventanaABMCliente.getTablaClientes().getRowCount() != 0) {

				// si se selecciona una fila
				if (this.ventanaABMCliente.getTablaClientes().getSelectedRow() != -1) {

					int filaSeleccionada = this.ventanaABMCliente.getTablaClientes().getSelectedRow();
					int id_cliente_a_eliminar = (int) this.ventanaABMCliente.getModelClientes()
							.getValueAt(filaSeleccionada, 0);

					this.cliente.borrarCliente(id_cliente_a_eliminar);
					cargarTablaClientes();
					limpiartxts();

				} else {

					JOptionPane.showMessageDialog(this.ventanaABMCliente, "Debe seleccionar un cliente a eliminar",
							"Atencion!", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {

				JOptionPane.showMessageDialog(this.ventanaABMCliente, "No hay clientes a eliminar", "Atencion!",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} else if (e.getSource() == this.ventanaABMCliente.getLimpiar_btn()) {

			this.limpiartxts();
			this.ventanaABMCliente.getTablaClientes().clearSelection();
		} else if (e.getSource() == this.ventanaABMCliente.getGuardar_btn()) {

			// si esta seleccionado de la tabla
			// modificar cliente
			if (this.ventanaABMCliente.getTablaClientes().getSelectedRow() != -1) {

				int filaSeleccionada = this.ventanaABMCliente.getTablaClientes().getSelectedRow();

				if (!isTxtsVacios()) {

					if (isTxtsValidos()) {

						cliente.modificarCliente(obtenerCliente(
								(int) this.ventanaABMCliente.getModelClientes().getValueAt(filaSeleccionada, 0)));

						limpiartxts();
						cargarTablaClientes();
					}

				} else {

					JOptionPane.showMessageDialog(this.ventanaABMCliente,
							"No se permiten campos vacíos. Por favor, vuelva a intentarlo.");
				}

			} else {
				// nuevo cliente

				if (!isTxtsVacios()) {

					if (isTxtsValidos()) {

						cliente.agregarCliente(obtenerCliente(0));

						limpiartxts();
						cargarTablaClientes();
					}

				} else {

					JOptionPane.showMessageDialog(this.ventanaABMCliente,
							"No se permiten campos vacíos. Por favor, vuelva a intentarlo.");
				}

			}
		}
	}

	private ClienteDTO obtenerCliente(int id) { // crea clienteDTO a partir de
												// los txts

		ClienteDTO clienteDTO = new ClienteDTO(

				id, Integer.parseInt(this.txts.get(2).getText()), // documento
				this.txts.get(0).getText(), // nombre
				this.txts.get(1).getText(), // apellido
				this.txts.get(3).getText(), // localidad
				this.txts.get(4).getText(), // direccion
				this.txts.get(5).getText(), // telefono
				this.txts.get(6).getText(), // email
				null, 0);

		return clienteDTO;
	}

	private void limpiartxts() {

		for (JTextField jt : txts) {
			jt.setText("");

		}
	}

	private boolean isTxtsValidos() {

		boolean ret = true;

		if (!soloNumeros(this.txts.get(2).getText())) { // valida documento

			JOptionPane.showMessageDialog(this.ventanaABMCliente,
					"Disculpe, has ingresado un nro de documento incorrecto.");
			return false;

		} else {

			ret = soloNumeros(this.txts.get(2).getText());
		}

		if (!soloNumeros(this.txts.get(5).getText())) { // valida telefono

			JOptionPane.showMessageDialog(this.ventanaABMCliente,
					"Disculpe, has ingresado un nro de telefono incorrecto.");
			return false;

		} else {

			ret = soloNumeros(this.txts.get(5).getText());
		}

		if (!validarEmail(this.txts.get(6).getText())) { // valida email

			JOptionPane.showMessageDialog(this.ventanaABMCliente, "Disculpe, has ingresado un mail incorrecto.");
			return false;

		} else {

			ret = validarEmail(this.txts.get(6).getText());

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

		VentanaABMCliente abm = new VentanaABMCliente();
		ControladorABMCliente c = new ControladorABMCliente(abm);
		c.inicializar();

	}

}
