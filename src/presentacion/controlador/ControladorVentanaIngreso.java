package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import dto.ClienteDTO;
import dto.IngresoDTO;
import dto.MarcaDTO;
import dto.TipoProductoDTO;
import modelo.Ingreso;
import persistencia.dao.ClienteDAO;
import presentacion.reportes.ReporteIngreso;
import presentacion.vista.VentanaIngreso;

public class ControladorVentanaIngreso implements ActionListener {

	private VentanaIngreso ventana_ingreso;
	Ingreso ingreso;
	private List<MarcaDTO> lista_marcas;
	private List<TipoProductoDTO> lista_tiposproductos;
	private ControladorVentanaPrincipal controladorVentanaPrincipal;

	public ControladorVentanaIngreso(VentanaIngreso ventana, Ingreso ingreso,
			ControladorVentanaPrincipal controladorVentanaPrincipal) {
		this.ventana_ingreso = ventana;
		this.ingreso = ingreso;
		this.ventana_ingreso.getBtnBuscarCliente().addActionListener(this);
		this.ventana_ingreso.getBtnAceptar().addActionListener(this);
		this.ventana_ingreso.getBtnCancelar().addActionListener(this);
		this.ventana_ingreso.getEnvioDomicilio().addActionListener(this);
		this.ventana_ingreso.getDireccion_default().addActionListener(this);
		this.ventana_ingreso.getBtnReporteDeIngreso().addActionListener(this);
		this.controladorVentanaPrincipal = controladorVentanaPrincipal;
	}

	public void inicializar() {
		if (ingreso.getId() != -1) {
			ingreso.cargarModeloCompleto();
			this.cargarVentana();
			llenarTablaCliente(ingreso.getCliente());
		} else {
			this.ventana_ingreso.getBtnReporteDeIngreso().setVisible(false);
			this.llenarComboMarcas();
			this.llenarComboTiposProductos();
		}
		this.ventana_ingreso.setVisible(true);
	}

	private void cargarVentana() {
		this.ventana_ingreso.getTextNombreProducto().setText(ingreso.getIngreso().getDescripcion());
		this.ventana_ingreso.getTextDescripcionFalla().setText(ingreso.getIngreso().getDescripcion_falla());
		this.ventana_ingreso.getComboTiposProductos().addItem(ingreso.getTipoproducto().getDetalle());
		this.ventana_ingreso.getComboMarcas().addItem(ingreso.getMarca().getDetalle());
		this.ventana_ingreso.getEnvioDomicilio().setSelected(ingreso.getIngreso().getEnvio());
		this.ventana_ingreso.getDireccion_default().setSelected(ingreso.getIngreso().getEnvio_default());
		this.ventana_ingreso.getTxtDireccionAlternativa().setText(ingreso.getIngreso().getDireccion_alternativa());
		this.ventana_ingreso.getMontoEnvio().setText(ingreso.getIngreso().getMontoEnvioToString());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.ventana_ingreso.getBtnBuscarCliente()) {
			String textoingresado = this.ventana_ingreso.getTxtNroCliente().getText();
			if (textoingresado == null || textoingresado.equals("")) {
				JOptionPane.showMessageDialog(this.ventana_ingreso, "Antes debe ingresar un nro de cliente para buscar",
						"Atencion!", JOptionPane.INFORMATION_MESSAGE);
			} else {
				try {
					int nrodoc = Integer.parseInt(textoingresado);
					ClienteDAO cdao = new ClienteDAO();
					ClienteDTO cdto = cdao.findPorNrodoc(nrodoc);
					if (cdto == null) {
						JOptionPane.showMessageDialog(this.ventana_ingreso, "El cliente buscado no existe!",
								"Atencion!", JOptionPane.INFORMATION_MESSAGE);
					} else {
						this.ingreso.setCliente(cdto);
						this.llenarTablaCliente(cdto);
					}
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(this.ventana_ingreso, "El nro de cliente debe ser numerico! ",
							"Atencion!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} else if (e.getSource() == this.ventana_ingreso.getEnvioDomicilio()) {
			if (this.ventana_ingreso.getEnvioDomicilio().isSelected()) {
				this.ventana_ingreso.getDireccion_default().setEnabled(true);
				this.ventana_ingreso.getTxtDireccionAlternativa().setEnabled(true);
				this.ventana_ingreso.getMontoEnvio().setEnabled(true);
			} else {
				this.ventana_ingreso.getDireccion_default().setEnabled(false);
				this.ventana_ingreso.getTxtDireccionAlternativa().setEnabled(false);
				this.ventana_ingreso.getMontoEnvio().setEnabled(false);
			}
		} else if (e.getSource() == this.ventana_ingreso.getBtnReporteDeIngreso()){
			ReporteIngreso reporte = new ReporteIngreso(ingreso.getTodos());
			reporte.mostrar();	
		} else if (e.getSource() == this.ventana_ingreso.getBtnCancelar()) {
			this.ventana_ingreso.dispose();
		}else if (e.getSource() == this.ventana_ingreso.getBtnAceptar()) {
			Boolean error = false;
			float montoFloat = 0;
			// verifico todos los campos
			// CLIENTE CARGADO
			if (this.ingreso.getCliente() == null) {
				error = true;
				JOptionPane.showMessageDialog(this.ventana_ingreso, "Debes ingresar un cliente para continuar!",
						"Atencion!", JOptionPane.INFORMATION_MESSAGE);

			}

			// NOMBRE DEL PRODUCTO
			String nombre_produ = this.ventana_ingreso.getTextNombreProducto().getText();
			if (nombre_produ.equals("") && !error) {
				error = true;
				JOptionPane.showMessageDialog(this.ventana_ingreso, "Debes completar el nombre de producto!",
						"Atencion!", JOptionPane.INFORMATION_MESSAGE);
			}

			// DESCRIPCION DE FALLA
			String descripcion_falla = this.ventana_ingreso.getTextDescripcionFalla().getText();
			if (descripcion_falla.equals("") && !error) {
				error = true;
				JOptionPane.showMessageDialog(this.ventana_ingreso,
						"Debes completar brevemente la descripcion de la falla", "Atencion!",
						JOptionPane.INFORMATION_MESSAGE);
			}

			if (this.ventana_ingreso.getEnvioDomicilio().isSelected() && !error) {
				// VALIDO DIRECICION DE ENVIO DE CLIENTE
				if (this.ventana_ingreso.getDireccion_default().isSelected() == false) {
					// Me fijo que complete la direccion alternativa y el monto
					String direccionAlternativa = this.ventana_ingreso.getTxtDireccionAlternativa().getText();
					if (direccionAlternativa.equals("")) {
						error = true;
						JOptionPane.showMessageDialog(this.ventana_ingreso,
								"Si el cliente solicita envio debe completar una direccion o de lo contrario utilice la direccion del cliente!",
								"Atencion!", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				// VALIDO EL MONTO DE ENVIO
				String monto = this.ventana_ingreso.getMontoEnvio().getText();
				if (monto.equals("") && !error) {
					error = true;
					JOptionPane.showMessageDialog(this.ventana_ingreso, "Por favor ingrese el costo en pesos del envio",
							"Atencion!", JOptionPane.INFORMATION_MESSAGE);
				} else if (!error) {
					try {
						montoFloat = Float.parseFloat(this.ventana_ingreso.getMontoEnvio().getText());
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(this.ventana_ingreso, "El campo Monto debe ser Numerico ",
								"Atencion!", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}

			if (!error) {
				IngresoDTO ingresoDTO = new IngresoDTO(0, this.ingreso.getCliente().getId(), nombre_produ,
						this.ventana_ingreso.getComboMarcas().getSelectedIndex(),
						this.ventana_ingreso.getComboTiposProductos().getSelectedIndex(), descripcion_falla,
						this.ventana_ingreso.getEnvioDomicilio().isSelected(),
						this.ventana_ingreso.getDireccion_default().isSelected(),
						this.ventana_ingreso.getTxtDireccionAlternativa().getText(), montoFloat, null, 1, 0, 0);
				this.ingreso.ingr = ingresoDTO;

				Boolean ingreso = this.ingreso.guardarIngreso(0);

				if (ingreso) {
					// JOptionPane.showMessageDialog(this.ventana_ingreso,
					// "Ingreso Realizado correctamente!", "Atencion!",
					// JOptionPane.INFORMATION_MESSAGE);
					this.ventana_ingreso.vaciarTodo();
					this.controladorVentanaPrincipal.cargar_tablaOrdenesTrabajo();
					this.ventana_ingreso.setVisible(false);
				}
			}
		}
	}

	private void llenarComboMarcas() {
		lista_marcas = this.ingreso.obtenerMarcas();
		for (int i = 0; i < lista_marcas.size(); i++) {
			this.ventana_ingreso.getComboMarcas().addItem(lista_marcas.get(i).getDetalle());
		}
	}

	private void llenarComboTiposProductos() {
		lista_tiposproductos = this.ingreso.obtenerTiposProductos();
		for (int i = 0; i < lista_tiposproductos.size(); i++) {
			this.ventana_ingreso.getComboTiposProductos().addItem(lista_tiposproductos.get(i).getDetalle());
		}
	}

	private void llenarTablaCliente(ClienteDTO client) {
		Object[][] informacionCliente = { { client.getNombre(), client.getApellido(), client.getDireccion(),
				client.getMail(), client.getTelefono() } };
		String[] nombreColumnas = { "Nombre", "Apellido", "Dirección", "Email", "Teléfono" };
		this.ventana_ingreso.getClienteTable().setModel(new DefaultTableModel(informacionCliente, nombreColumnas));
	}

}
