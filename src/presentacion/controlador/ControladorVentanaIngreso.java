package presentacion.controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JOptionPane;
import dto.ClienteDTO;
import dto.IngresoDTO;
import dto.MarcaDTO;
import dto.TipoProductoDTO;
import dto.UsuarioDTO;
import modelo.Ingreso;
import persistencia.dao.ClienteDAO;
import presentacion.reportes.ReporteIngreso;
import presentacion.vista.VentanaABMCliente;
import presentacion.vista.VentanaIngreso;
import presentacion.vista.VentanaVisualizacionClientes;

public class ControladorVentanaIngreso implements ActionListener {

	VentanaIngreso ventana_ingreso;
	Ingreso ingreso;
	private List<MarcaDTO> lista_marcas;
	private List<TipoProductoDTO> lista_tiposproductos;
	private ControladorVentanaPrincipal controladorVentanaPrincipal;
	private UsuarioDTO usuarioLogueado;

	public ControladorVentanaIngreso(VentanaIngreso ventana, Ingreso ingreso,
			ControladorVentanaPrincipal controladorVentanaPrincipal, UsuarioDTO usuario) {
		this.usuarioLogueado = usuario;
		this.ventana_ingreso = ventana;
		this.ingreso = ingreso;
		this.ventana_ingreso.getBtnBuscarCliente().addActionListener(this);
		this.ventana_ingreso.getBtnAceptar().addActionListener(this);
		this.ventana_ingreso.getBtnCancelar().addActionListener(this);
		this.ventana_ingreso.getEnvioDomicilio().addActionListener(this);
		this.ventana_ingreso.getDireccion_nueva().addActionListener(this);
		this.ventana_ingreso.getBtnReporteDeIngreso().addActionListener(this);
		this.ventana_ingreso.getCrearCliente_btn().addActionListener(this);
		this.ventana_ingreso.getNumeroCliente_txf().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				int code = evt.getKeyCode();
				if (code == KeyEvent.VK_ENTER) {
					buscarCliente();
				}
			}
		});
		this.controladorVentanaPrincipal = controladorVentanaPrincipal;
		this.determinarVisibilidadCuadro_DireccionNueva();
		this.determinarVisibilidadCheck_DireccionNueva();
	}

	private void determinarVisibilidadCheck_DireccionNueva() {
		if (this.ventana_ingreso.getEnvioDomicilio().isSelected() == false) {
			this.ventana_ingreso.getDireccion_nueva().setSelected(false);
			this.ventana_ingreso.getDireccion_nueva().setEnabled(false);
			this.ventana_ingreso.getOtraDireccionLabel().setForeground(Color.gray);
		} else {
			this.ventana_ingreso.getDireccion_nueva().setEnabled(true);
			if (this.ventana_ingreso.getDireccion_nueva().isSelected()) {
				this.ventana_ingreso.getDireccion_nueva().setSelected(true);
				this.ventana_ingreso.getOtraDireccionLabel().setForeground(Color.darkGray);
			}
		}
	}

	private void determinarVisibilidadCuadro_DireccionNueva() {
		if (this.ventana_ingreso.getEnvioDomicilio().isSelected()
				&& this.ventana_ingreso.getDireccion_nueva().isSelected()) {
			this.ventana_ingreso.getDireccionNueva_JPanel().setVisible(true);
		} else {
			this.ventana_ingreso.getDireccionNueva_JPanel().setVisible(false);
		}
	}

	public void inicializar() {
		if (ingreso.getId() != -1) {
			ingreso.cargarModeloCompleto();
			this.cargarVentana();
			this.determinarVisibilidadCuadro_DireccionNueva();
			llenarTablaCliente(ingreso.getCliente());
			this.ventana_ingreso.getBtnAceptar().setVisible(false);
			this.ventana_ingreso.getBtnCancelar().setText("Cerrar");
			this.ventana_ingreso.getBtnBuscarCliente().setEnabled(false);
			this.ventana_ingreso.getTextNombreProducto().setEditable(false);
			this.ventana_ingreso.getTextDescripcionFalla().setEditable(false);
			this.ventana_ingreso.getMontoEnvio().setEditable(false);
			this.ventana_ingreso.getTxtNroCliente().setEditable(false);
			this.ventana_ingreso.getEnvioDomicilio().setEnabled(false);
			this.ventana_ingreso.getTxtDireccionNueva().setEditable(false);
			this.ventana_ingreso.getDireccion_nueva().setEnabled(false);
			this.ventana_ingreso.getCrearCliente_btn().setEnabled(false);
		} else {
			this.ventana_ingreso.getBtnReporteDeIngreso().setVisible(false);
			this.llenarComboMarcas();
			this.llenarComboTiposProductos();
		}
		if (this.ventana_ingreso.getEnvioDomicilio().isSelected()) {
			this.ventana_ingreso.getDireccion_nueva().setEnabled(true);
			this.ventana_ingreso.getOtraDireccionLabel().setForeground(Color.darkGray);
		}
		this.ventana_ingreso.setVisible(true);
	}

	private void cargarVentana() {
		this.ventana_ingreso.getTextNombreProducto().setText(ingreso.getIngreso().getDescripcion());
		this.ventana_ingreso.getTextDescripcionFalla().setText(ingreso.getIngreso().getDescripcion_falla());
		this.ventana_ingreso.getComboTiposProductos().addItem(ingreso.getTipoproducto().getDetalle());
		this.ventana_ingreso.getComboMarcas().addItem(ingreso.getMarca().getDetalle());
		this.ventana_ingreso.getEnvioDomicilio().setSelected(ingreso.getIngreso().getEnvio());
		this.ventana_ingreso.getDireccion_nueva().setSelected(ingreso.getIngreso().getEnvio_default());
		this.ventana_ingreso.getTxtDireccionNueva().setText(ingreso.getIngreso().getDireccion_alternativa());
		this.ventana_ingreso.getMontoEnvio().setText(ingreso.getIngreso().getMontoEnvioToString());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.ventana_ingreso.getBtnBuscarCliente()) {

			VentanaVisualizacionClientes visualizador = new VentanaVisualizacionClientes();
			ControladorVisualizacionClientes controladorVisualizador = new ControladorVisualizacionClientes(
					visualizador, this);
			controladorVisualizador.inicializar();

		} else if (e.getSource() == this.ventana_ingreso.getEnvioDomicilio()) {
			this.ventana_ingreso.getDireccion_nueva().setEnabled(true);
			if (this.ventana_ingreso.getEnvioDomicilio().isSelected()) {
				determinarVisibilidadCheck_DireccionNueva();
				this.ventana_ingreso.getOtraDireccionLabel().setForeground(Color.darkGray);
			} else {
				this.ocultarOpcionDireccionAlternativa();
			}
		} else if (e.getSource() == this.ventana_ingreso.getBtnReporteDeIngreso()) {
			ReporteIngreso reporte = new ReporteIngreso(ingreso.getTodos());
			reporte.mostrar();
		} else if (e.getSource() == this.ventana_ingreso.getBtnCancelar()) {
			this.ventana_ingreso.dispose();
		} else if (e.getSource() == this.ventana_ingreso.getBtnAceptar()) {
			Boolean error = false;
			float montoFloat = 0;
			// verifico todos los campos
			// CLIENTE CARGADO
			if (this.ingreso.getCliente() == null) {
				error = true;
				JOptionPane.showMessageDialog(this.ventana_ingreso,
						"Debes ingresar un cliente para continuar. Por favor, vuelva a intentarlo.");

			}

			// NOMBRE DEL PRODUCTO
			String nombre_produ = this.ventana_ingreso.getTextNombreProducto().getText();
			if (nombre_produ.equals("") && !error) {
				error = true;
				JOptionPane.showMessageDialog(this.ventana_ingreso,
						"Debes completar el nombre de producto. Por favor, vuelva a intentarlo.");
			}

			// DESCRIPCION DE FALLA
			String descripcion_falla = this.ventana_ingreso.getTextDescripcionFalla().getText();
			if (descripcion_falla.equals("") && !error) {
				error = true;
				JOptionPane.showMessageDialog(this.ventana_ingreso,
						"La descripcion de la falla est\u00e1 vacia. Por favor, vuelva a intentarlo.");
			}

			if (this.ventana_ingreso.getEnvioDomicilio().isSelected() && !error) {
				// habilitarOpcionDireccionNueva();
				determinarVisibilidadCheck_DireccionNueva();
				// VALIDO DIRECICION DE ENVIO DE CLIENTE
				if (this.ventana_ingreso.getDireccion_nueva().isSelected()) {
					// Me fijo que complete la direccion alternativa y el monto
					String direccionAlternativa = this.ventana_ingreso.getTxtDireccionNueva().getText();
					if (direccionAlternativa.equals("")) {
						error = true;
						JOptionPane.showMessageDialog(this.ventana_ingreso,
								"Si el cliente solicita envio debe completar una direcci\u00f3n. De lo contrario, utilice la direcci\u00f3n del cliente.");
					}
				}
				// VALIDO EL MONTO DE ENVIO
				String monto = this.ventana_ingreso.getMontoEnvio().getText();
				if (monto.equals("") && !error) {
					error = true;
					JOptionPane.showMessageDialog(this.ventana_ingreso,
							"Por favor, ingrese el costo en pesos del env\u00edo.");
				} else if (!error) {
					try {
						montoFloat = Float.parseFloat(this.ventana_ingreso.getMontoEnvio().getText());
					} catch (NumberFormatException nfe) {
						error = true;
						JOptionPane.showMessageDialog(this.ventana_ingreso,
								"El monto no es v\u00e1lido. Por favor, vuelva a intentarlo.");
					}
				}
			}

			if (!error) {
				if ((this.ventana_ingreso.getComboMarcas().getSelectedItem() != null
						&& this.ventana_ingreso.getComboTiposProductos().getSelectedItem() != null)) {
					IngresoDTO ingresoDTO = new IngresoDTO(0, this.ingreso.getCliente().getId(), nombre_produ,
							this.ventana_ingreso.getComboMarcas().getSelectedIndex(),
							this.ventana_ingreso.getComboTiposProductos().getSelectedIndex(), descripcion_falla,
							this.ventana_ingreso.getEnvioDomicilio().isSelected(),
							this.ventana_ingreso.getDireccion_nueva().isSelected(),
							this.ventana_ingreso.getTxtDireccionNueva().getText(), montoFloat, null, 1,
							usuarioLogueado.getId(), this.ingreso.getId());
					this.ingreso.ingr = ingresoDTO;

					Boolean ingreso = this.ingreso.guardarIngreso(usuarioLogueado.getId());

					if (ingreso) {
						this.ventana_ingreso.vaciarTodo();
						this.controladorVentanaPrincipal.cargar_tablaOrdenesTrabajo();
						this.ventana_ingreso.setVisible(false);
					}
				} else {
					JOptionPane.showMessageDialog(this.ventana_ingreso,
							"Disculpe, debe ingresar la marca y el tipo del producto para continuar.");
				}
			}
		} else if (e.getSource() == this.ventana_ingreso.getDireccion_nueva()) {
			this.ventana_ingreso.setTxtDireccionNueva("");
			this.ventana_ingreso.setMontoEnvio("");
			this.determinarVisibilidadCuadro_DireccionNueva();
		} else if (e.getSource() == this.ventana_ingreso.getCrearCliente_btn()) {
			VentanaABMCliente ventClient = new VentanaABMCliente();
			ControladorABMCliente contrClient = new ControladorABMCliente(ventClient);
			contrClient.inicializar();

		}
	}

	private void ocultarOpcionDireccionAlternativa() {
		this.ventana_ingreso.getDireccion_nueva().setSelected(false);
		this.ventana_ingreso.setTxtDireccionNueva("");
		this.ventana_ingreso.setMontoEnvio("");
		this.determinarVisibilidadCheck_DireccionNueva();
		this.determinarVisibilidadCuadro_DireccionNueva();
	}

	void buscarCliente() {
		String textoingresado = this.ventana_ingreso.getTxtNroCliente().getText();
		if (textoingresado == null || textoingresado.equals("")) {
			JOptionPane.showMessageDialog(this.ventana_ingreso, "Por favor, ingrese un n\u00famero de cliente.");
		} else {
			try {
				int nrodoc = Integer.parseInt(textoingresado);
				ClienteDAO cdao = new ClienteDAO();
				ClienteDTO cdto = cdao.findPorNrodoc(nrodoc);
				if (cdto == null) {
					JOptionPane.showMessageDialog(this.ventana_ingreso,
							"El cliente buscado no existe. Por favor, ingrese un valor v\u00e1lido.");
					this.ventana_ingreso.vaciarTodo();
				} else {
					this.ingreso.setCliente(cdto);
					this.llenarTablaCliente(cdto);
				}
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(this.ventana_ingreso,
						"El n\u00famero de cliente es incorrecto, vuelva a intentarlo. ");
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
		this.ventana_ingreso.getLbl_nombre_apellido_cliente().setText(client.getNombre() + ", " + client.getApellido());
		this.ventana_ingreso.getLbl_direccion_cliente().setText(client.getDireccion());
		this.ventana_ingreso.getLbl_telefono_cliente().setText("Telefono: " + client.getTelefono());
		this.ventana_ingreso.getLbl_email_cliente().setText("Email: " + client.getMail());
	}

}
