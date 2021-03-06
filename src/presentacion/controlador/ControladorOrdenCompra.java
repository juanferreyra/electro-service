package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import dto.RepuestoDTO;
import dto.ItemRepuestoDTO;
import dto.MarcaDTO;
import dto.OrdenCompraDTO;
import dto.OrdenCompraRepuestoDTO;
import dto.ProveedorDTO;
import dto.UsuarioDTO;
import modelo.EmailOrdenDeCompra;
import modelo.OrdenCompra;
import persistencia.dao.ProveedorDAO;
import presentacion.reportes.ReporteOrdenCompra;
import presentacion.vista.VentanaABMProveedor;
import presentacion.vista.VentanaOrdenCompra;

public class ControladorOrdenCompra implements ActionListener {

	private VentanaOrdenCompra ventanaOrdenCompra;
	private OrdenCompra ordenCompra;
	private UsuarioDTO usuarioLogueado;
	private DefaultTableModel modelTable = new DefaultTableModel();

	public ControladorOrdenCompra(VentanaOrdenCompra VentanaOrdenCompra, UsuarioDTO usuario) {
		this.ventanaOrdenCompra = VentanaOrdenCompra;
		this.usuarioLogueado = usuario;
		this.ordenCompra = new OrdenCompra();

		this.ventanaOrdenCompra.getBtnCancelada().addActionListener(this);
		this.ventanaOrdenCompra.getBtnRecibido().addActionListener(this);
		this.ventanaOrdenCompra.getAgregarComponente_btn().addActionListener(this);
		this.ventanaOrdenCompra.getCancelar_btn().addActionListener(this);
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
		this.ventanaOrdenCompra.setVisible(true);
		this.ventanaOrdenCompra.getBtnVaciarVentanaOrden().setVisible(false);
		this.ventanaOrdenCompra.getBtnCancelada().setVisible(false);
		this.ventanaOrdenCompra.getBtnRecibido().setVisible(false);
		this.ventanaOrdenCompra.getBtnImprimir().setVisible(false);
		this.ventanaOrdenCompra.getBtnEnviarEmial().setVisible(false);
		this.ventanaOrdenCompra.getComponentes_table().setModel(modelTable);
		cargarCombo();
		clickEnCombo();
	}

	private void clickEnCombo() {
		this.ventanaOrdenCompra.getListaOrdenesCompras_cmb().addItemListener(new ItemListener() {

			@SuppressWarnings("static-access")
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == e.SELECTED) {

					ventanaOrdenCompra.getTxtfldCargarOrden()
							.setText((String) ventanaOrdenCompra.getListaOrdenesCompras_cmb().getSelectedItem());

					validarYCargarOrden();
				}
			}
		});

	}

	private void cargarCombo() {

		ArrayList<OrdenCompraDTO> listaOrdenesDeCompra = ordenCompra.readAll();

		this.ventanaOrdenCompra.getListaOrdenesCompras_cmb().removeAllItems();

		for (OrdenCompraDTO ordenes : listaOrdenesDeCompra) {
			this.ventanaOrdenCompra.getListaOrdenesCompras_cmb().addItem(String.valueOf(ordenes.getId()));
		}

		this.ventanaOrdenCompra.getListaOrdenesCompras_cmb().setSelectedIndex(-1);

	}

	private void cargarComboComponentes() {
		this.ventanaOrdenCompra.getComponente_ComboBox().removeAllItems();

		for (RepuestoDTO c : ordenCompra.obtenerRepuestos()) {
			this.ventanaOrdenCompra.getComponente_ComboBox().addItem(c.getDetalle());
		}
	}

	private void cargarModelo() {
		ordenCompra.cargarVariables();
		cargarProveedor(ordenCompra.getProveedorDTO());
		actualizarTablaRepuestos();
		mostrarColumnaCantidadReal();

		if (!ordenCompra.getOrdenCompraDTO().getEstado().equals("NUEVA")) {
			this.ventanaOrdenCompra.getBtnCancelada().setVisible(false);
			this.ventanaOrdenCompra.getBtnRecibido().setVisible(false);
			this.ventanaOrdenCompra.getValorPresupuestado_txf()
					.setText(String.valueOf(ordenCompra.getOrdenCompraDTO().getImporte_total()));
		} else {
			this.ventanaOrdenCompra.getBtnCancelada().setVisible(true);
			this.ventanaOrdenCompra.getBtnRecibido().setVisible(true);
			this.ventanaOrdenCompra.getValorPresupuestado_txf().setText("");
		}

		this.ventanaOrdenCompra.getBtnBuscarProveedor().setEnabled(false);
		this.ventanaOrdenCompra.getAgregarComponente_btn().setEnabled(false);
		this.ventanaOrdenCompra.getBtnVerProveedores().setEnabled(false);
		this.ventanaOrdenCompra.getGuardar_btn().setVisible(false);
		this.ventanaOrdenCompra.getCancelar_btn().setText("Cerrar");
		this.ventanaOrdenCompra.getEliminarComponente_btn().setEnabled(false);
		this.ventanaOrdenCompra.getComponente_ComboBox().setEnabled(false);
		this.ventanaOrdenCompra.getBtnImprimir().setVisible(true);
		this.ventanaOrdenCompra.getBtnEnviarEmial().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.ventanaOrdenCompra.getAgregarComponente_btn()) {
			if (this.ventanaOrdenCompra.getComponente_ComboBox().getSelectedIndex() != -1) {
				agregarRepuestoATabla();
				ocultarColumnaId();
				ocultarColumnaCantidadReal();
			} else {

				JOptionPane.showMessageDialog(ventanaOrdenCompra, "Por favor, seleccione un repuesto para continuar. ");
			}
		} else if (e.getSource() == this.ventanaOrdenCompra.getEliminarComponente_btn()) {

			eliminarRepuestoDeTabla();

		} else if (e.getSource() == this.ventanaOrdenCompra.getGuardar_btn()) {

			validarCampos();
			this.ventanaOrdenCompra.getBtnCancelada().setVisible(false);
			this.ventanaOrdenCompra.getBtnRecibido().setVisible(false);

		} else if (e.getSource() == this.ventanaOrdenCompra.getCancelar_btn()) {

			this.ventanaOrdenCompra.dispose();

		} else if (e.getSource() == this.ventanaOrdenCompra.getBtnRecibido()) {

			int response = JOptionPane.showConfirmDialog(null,
					"Se actualizaran las cantidades reales recibidas al dar la orden como 'RECIBIDA'. � Esta seguro de los cambios?",
					"Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.NO_OPTION) {

			} else if (response == JOptionPane.YES_OPTION) {

				Boolean error = false;

				Float importe = null;

				ArrayList<ItemRepuestoDTO> listaCantidades = new ArrayList<ItemRepuestoDTO>();

				try {

					importe = Float.parseFloat(this.ventanaOrdenCompra.getValorPresupuestado_txf().getText());

				} catch (Exception e3) {
					JOptionPane.showMessageDialog(ventanaOrdenCompra,
							"Debe completar el campo importe para continuar.");
					error = true;
				}

				for (int i = 0; i < this.ventanaOrdenCompra.getComponentes_table().getRowCount(); i++) {
					try {
						int idRepuesto = Integer.parseInt(
								this.ventanaOrdenCompra.getComponentes_table().getModel().getValueAt(i, 0).toString());
						int cantidadReal = Integer.parseInt(
								this.ventanaOrdenCompra.getComponentes_table().getModel().getValueAt(i, 4).toString());

						ItemRepuestoDTO item = new ItemRepuestoDTO(0, idRepuesto, "", cantidadReal, null, null);

						listaCantidades.add(item);

					} catch (NumberFormatException e2) {

						if (!error) {
							JOptionPane.showMessageDialog(ventanaOrdenCompra,
									"Al parecer la cantidad real  del producto "
											+ this.ventanaOrdenCompra.getComponentes_table().getModel().getValueAt(i, 2)
													.toString()
											+ " no es numerico. Por favor, ingrese una nueva cantidad para continuar.");
						}
						error = true;
					}
				}

				if (!error) {
					Boolean actualizoCantidades = ordenCompra.actualizarCantidadesReales(listaCantidades);
					Boolean actualizoStock = ordenCompra.actualizarStockRepuestos(listaCantidades);
					Boolean actualizoImporte = ordenCompra.actualizarMontoFinal(importe);
					ordenCompra.actualizarEstado("RECIBIDA");
					this.ventanaOrdenCompra.getBtnCancelada().setVisible(false);
					this.ventanaOrdenCompra.getBtnRecibido().setVisible(false);

					if (actualizoCantidades && actualizoStock && actualizoImporte) {
						JOptionPane.showMessageDialog(ventanaOrdenCompra, "El stock se ha actualizado correctamente.");
					} else {
						JOptionPane.showMessageDialog(ventanaOrdenCompra,
								"Ha ocurrido un error al actualizar las cantidades o el stock.");
					}
				}

			} else if (response == JOptionPane.CLOSED_OPTION) {

			}
		} else if (e.getSource() == this.ventanaOrdenCompra.getBtnCancelada()) {
			int response = JOptionPane.showConfirmDialog(null, "� Esta seguro que desea cancelar el presupuesto?",
					"Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.NO_OPTION) {

			} else if (response == JOptionPane.YES_OPTION) {

				ordenCompra.actualizarEstado("CANCELADA");
				this.ventanaOrdenCompra.getBtnCancelada().setVisible(false);
				this.ventanaOrdenCompra.getBtnRecibido().setVisible(false);

			} else if (response == JOptionPane.CLOSED_OPTION) {

			}
		} else if (e.getSource() == this.ventanaOrdenCompra.getBtnBuscarProveedor()) {
			buscarProveedor();
		} else if (e.getSource() == this.ventanaOrdenCompra.getBtnVerProveedores()) {

			VentanaABMProveedor ventanaABMProveedor = new VentanaABMProveedor();
			ControladorABMProveedor cp = new ControladorABMProveedor(ventanaABMProveedor);
			cp.inicializar();

			ventanaABMProveedor.addWindowListener(new WindowListener() {

				@Override
				public void windowOpened(WindowEvent e) {
				}

				@Override
				public void windowIconified(WindowEvent e) {
				}

				@Override
				public void windowDeiconified(WindowEvent e) {
				}

				@Override
				public void windowDeactivated(WindowEvent e) {
				}

				@Override
				public void windowClosing(WindowEvent e) {
				}

				@Override
				public void windowClosed(WindowEvent e) {

					if (ventanaABMProveedor.getTablaProveedores().getSelectedRow() != -1) {

						int filaSeleccionada = ventanaABMProveedor.getTablaProveedores().getSelectedRow();

						int nroProveedor = (int) ventanaABMProveedor.getModelProveedores().getValueAt(filaSeleccionada,
								0);

						ProveedorDAO cdao = ordenCompra.getProveedorDAO();
						ProveedorDTO cdto = cdao.find(nroProveedor);

						vaciarCampos();
						ordenCompra.setProveedorDTO(cdto);
						cargarProveedor(cdto);
						ordenCompra.actualizarListaMarcas();
						cargarComboComponentes();
					}
				}

				@Override
				public void windowActivated(WindowEvent e) {
				}
			});

		} else if (e.getSource() == this.ventanaOrdenCompra.getBtnCargarOrden()) {

			validarYCargarOrden();

		} else if (e.getSource() == this.ventanaOrdenCompra.getBtnVaciarVentanaOrden()) {

			vaciarCampos();
			this.ventanaOrdenCompra.getBtnVaciarVentanaOrden().setVisible(false);
			this.ventanaOrdenCompra.getBtnBuscarProveedor().setEnabled(true);
			this.ventanaOrdenCompra.getAgregarComponente_btn().setEnabled(true);
			this.ventanaOrdenCompra.getBtnVerProveedores().setEnabled(true);
			this.ventanaOrdenCompra.getGuardar_btn().setVisible(true);
			this.ventanaOrdenCompra.getCancelar_btn().setText("Cancelar");
			this.ventanaOrdenCompra.getEliminarComponente_btn().setEnabled(true);
			this.ventanaOrdenCompra.getComponente_ComboBox().setEnabled(true);
			this.ventanaOrdenCompra.getBtnImprimir().setVisible(false);
			this.ventanaOrdenCompra.getBtnEnviarEmial().setVisible(false);
			this.ventanaOrdenCompra.getBtnRecibido().setVisible(false);
			this.ventanaOrdenCompra.getBtnCancelada().setVisible(false);

		} else if (e.getSource() == this.ventanaOrdenCompra.getBtnImprimir()) {

			ReporteOrdenCompra reporte = new ReporteOrdenCompra(ordenCompra.getDatosImpresion());
			reporte.mostrar();

		} else if (e.getSource() == this.ventanaOrdenCompra.getBtnEnviarEmial()) {

			EmailOrdenDeCompra emailOrdenDeCompra = new EmailOrdenDeCompra(usuarioLogueado, this.ventanaOrdenCompra);
			emailOrdenDeCompra.start();

		}
	}

	private void validarYCargarOrden() {

		String nroOrdenString = this.ventanaOrdenCompra.getTxtfldCargarOrden().getText();
		try {
			int nroOrden = Integer.parseInt(nroOrdenString);

			if (ordenCompra.existeOrdenCompra(nroOrden)) {
				ordenCompra.getOrdenCompraDTO().setId(nroOrden);
				cargarModelo();
				this.ventanaOrdenCompra.getBtnVaciarVentanaOrden().setVisible(true);
			} else {
				JOptionPane.showMessageDialog(ventanaOrdenCompra,
						"No se ha encontrado ninguna hoja de ruta con ese nro. Por favor, vuelva a intentarlo.",
						"Atencion!", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this.ventanaOrdenCompra,
					"El nro. de proveedor es incorrecto. Vuelva a intentarlo. ", null, JOptionPane.INFORMATION_MESSAGE);
		}

	}

	private void ocultarColumnaId() {

		this.ventanaOrdenCompra.getComponentes_table().getColumnModel().getColumn(0).setMaxWidth(0);
		this.ventanaOrdenCompra.getComponentes_table().getColumnModel().getColumn(0).setMinWidth(0);
		this.ventanaOrdenCompra.getComponentes_table().getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		this.ventanaOrdenCompra.getComponentes_table().getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
	}

	private void ocultarColumnaCantidadReal() {
		this.ventanaOrdenCompra.getComponentes_table().getColumnModel().getColumn(4).setMaxWidth(0);
		this.ventanaOrdenCompra.getComponentes_table().getColumnModel().getColumn(4).setMinWidth(0);
		this.ventanaOrdenCompra.getComponentes_table().getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
		this.ventanaOrdenCompra.getComponentes_table().getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
	}

	private void mostrarColumnaCantidadReal() {
		this.ventanaOrdenCompra.getComponentes_table().getColumnModel().getColumn(4).setMaxWidth(100);
		this.ventanaOrdenCompra.getComponentes_table().getColumnModel().getColumn(4).setMinWidth(100);
		this.ventanaOrdenCompra.getComponentes_table().getTableHeader().getColumnModel().getColumn(4).setMaxWidth(100);
		this.ventanaOrdenCompra.getComponentes_table().getTableHeader().getColumnModel().getColumn(4).setMinWidth(100);
	}

	private void validarCampos() {

		if (this.ventanaOrdenCompra.getComponentes_table().getRowCount() == 0) {

			JOptionPane.showMessageDialog(ventanaOrdenCompra,
					"Por favor, debe ingresar al menos un repuesto para continuar. ", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);

		} else if (this.ventanaOrdenCompra.getNombreRazonSocialTexto_lbl().equals("")) {

			JOptionPane.showMessageDialog(ventanaOrdenCompra, "Por favor, debe ingresar un proveedor para continuar. ",
					"Atencion!", JOptionPane.INFORMATION_MESSAGE);

		} else {

			ordenCompra.getOrdenCompraDTO().setIdusuario(this.usuarioLogueado.getId());

			Boolean guardo = ordenCompra.crearOrdenCompra();
			if (guardo) {
				JOptionPane.showMessageDialog(ventanaOrdenCompra,
						"Orden de compra nro. " + ordenCompra.getOrdenCompraDTO().getId() + " creada correctamente.",
						"Atencion!", JOptionPane.INFORMATION_MESSAGE);

				// envia email al guardar la orden de compra
				EmailOrdenDeCompra emailOrdenDeCompra = new EmailOrdenDeCompra(usuarioLogueado,
						this.ventanaOrdenCompra);
				emailOrdenDeCompra.start();
				cargarCombo();

				vaciarCampos();

			} else {

				JOptionPane.showMessageDialog(ventanaOrdenCompra,
						"Ha ocurrido un error al guardar. Por favor, verifique los datos ingresados o vuelva a generar la orden.",
						"Atencion!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void actualizarTablaRepuestos() {

		modelTable = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int fila, int columna) {
				if (columna == 4) {
					return true;
				} else {
					return false;
				}
			}
		};

		modelTable.setColumnIdentifiers(ventanaOrdenCompra.getComponentes_nombreColumnas());

		ArrayList<ItemRepuestoDTO> repuestosAgregados = ordenCompra.getListaDeRepuestos();

		for (int i = 0; i < repuestosAgregados.size(); i++) {

			MarcaDTO marca = ordenCompra.buscarMarca(repuestosAgregados.get(i).getIdrepuesto());

			int cantidadReal = repuestosAgregados.get(i).getCantidad();

			if (!(ordenCompra.getOrdenCompraDTO().getId() == -1)) {
				if (!ordenCompra.getOrdenCompraDTO().getEstado().equals("NUEVA")) {

					OrdenCompraRepuestoDTO repuestoEnOrden = ordenCompra
							.getRepuestoEnOrden(repuestosAgregados.get(i).getIdrepuesto());
					cantidadReal = repuestoEnOrden.getCantidad_real();
				}
			}
			if (marca != null) {
				Object[] fila = { repuestosAgregados.get(i).getIdrepuesto(), marca.getDetalle(),
						repuestosAgregados.get(i).getDetalle(), repuestosAgregados.get(i).getCantidad(), cantidadReal };

				modelTable.addRow(fila);
			}
			modelTable.isCellEditable(i, 0);
			modelTable.isCellEditable(i, 1);
			modelTable.isCellEditable(i, 2);
			modelTable.isCellEditable(i, 3);

		}

		this.ventanaOrdenCompra.getComponentes_table().setModel(modelTable);

		ocultarColumnaId();
		ocultarColumnaCantidadReal();
	}

	private void eliminarRepuestoDeTabla() {

		if (this.ventanaOrdenCompra.getComponentes_table().getSelectedRow() != -1) {

			int seleccionado = ventanaOrdenCompra.getComponentes_table().getSelectedRow();
			String nombre = ventanaOrdenCompra.getComponentes_table().getValueAt(seleccionado, 2).toString();

			for (int i = 0; i < this.ordenCompra.getListaDeRepuestos().size(); i++) {

				if (this.ordenCompra.getListaDeRepuestos().get(i).getDetalle().equals(nombre)) {

					this.ordenCompra.getListaDeRepuestos().remove(i);
				}
			}

			actualizarTablaRepuestos();
		} else {

			JOptionPane.showMessageDialog(ventanaOrdenCompra, "Por favor, seleccione una fila para eliminar. ",
					"Atencion!", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void agregarRepuestoATabla() {

		modelTable.setColumnIdentifiers(ventanaOrdenCompra.getComponentes_nombreColumnas());
		Boolean existe = false;
		// agrego el repuesto al objeto Presupuesto con su lista
		int cantidad = (int) ventanaOrdenCompra.getSpinner().getValue();
		RepuestoDTO resp = ordenCompra
				.buscarRepuesto((String) this.ventanaOrdenCompra.getComponente_ComboBox().getSelectedItem());

		ItemRepuestoDTO itemRepuesto = new ItemRepuestoDTO(-1, resp.getId(), resp.getDetalle(), cantidad, null, null);
		// me fijo si existe en la tabla
		for (int i = 0; i < ordenCompra.getListaDeRepuestos().size(); i++) {
			if (ordenCompra.getListaDeRepuestos().get(i).getDetalle().equals(resp.getDetalle())) {
				existe = true;
				this.ordenCompra.getListaDeRepuestos().get(i).sumarCantidad(cantidad);
			}
		}
		if (!existe) {
			ordenCompra.addRepuestoListaDeComponentes(itemRepuesto);
		}
		// actualizo la tabla de repuestos
		actualizarTablaRepuestos();
	}

	private void buscarProveedor() {
		String textoingresado = this.ventanaOrdenCompra.getTxtfldNroProveedor().getText();
		if (textoingresado == null || textoingresado.equals("")) {
			JOptionPane.showMessageDialog(this.ventanaOrdenCompra, "Por favor, ingrese un numero de proveedor.", null,
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			try {
				int nroproveedor = Integer.parseInt(textoingresado);

				if (this.ventanaOrdenCompra.getComponentes_table().getRowCount() > 0) {
					int response = JOptionPane.showConfirmDialog(null,
							"Existen repuestos cargados. Si desea cargar un proveedor nuevo se borraran los repuestos ingresados. � Esta seguro que quiere continuar?",
							"Atencion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
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
						"El numero de proveedor es incorrecto. Vuelva a intentarlo. ", null,
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	private void verificarYCargarProveedor(int nroproveedor) {

		ProveedorDAO cdao = this.ordenCompra.getProveedorDAO();
		ProveedorDTO cdto = cdao.find(nroproveedor);

		if (cdto == null) {
			int response2 = JOptionPane.showConfirmDialog(null,
					"El proveedor buscado no existe. � Desea buscarlo o crear uno nuevo?", "Atencion",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response2 == JOptionPane.NO_OPTION) {

			} else if (response2 == JOptionPane.YES_OPTION) {

				VentanaABMProveedor ventanaABMProveedor = new VentanaABMProveedor();
				ControladorABMProveedor cp = new ControladorABMProveedor(ventanaABMProveedor);
				cp.inicializar();

				ventanaABMProveedor.addWindowListener(new WindowListener() {

					@Override
					public void windowOpened(WindowEvent e) {
					}

					@Override
					public void windowIconified(WindowEvent e) {
					}

					@Override
					public void windowDeiconified(WindowEvent e) {
					}

					@Override
					public void windowDeactivated(WindowEvent e) {
					}

					@Override
					public void windowClosing(WindowEvent e) {
					}

					@Override
					public void windowClosed(WindowEvent e) {

						if (ventanaABMProveedor.getTablaProveedores().getSelectedRow() != -1) {

							int filaSeleccionada = ventanaABMProveedor.getTablaProveedores().getSelectedRow();

							int nroProveedor = (int) ventanaABMProveedor.getModelProveedores()
									.getValueAt(filaSeleccionada, 0);

							ProveedorDAO cdao = ordenCompra.getProveedorDAO();
							ProveedorDTO cdto = cdao.find(nroProveedor);

							vaciarCampos();
							ordenCompra.setProveedorDTO(cdto);
							cargarProveedor(cdto);
							ordenCompra.actualizarListaMarcas();
							cargarComboComponentes();
						}
					}

					@Override
					public void windowActivated(WindowEvent e) {
					}
				});

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
		if (cdto != null) {
			this.ventanaOrdenCompra.getNombreRazonSocialTexto_lbl().setText(cdto.getRazonSocial());
			this.ventanaOrdenCompra.getDireccionTexto_lbl().setText(cdto.getDireccion());
			this.ventanaOrdenCompra.getMailTexto_lbl().setText(cdto.getEmailPedidos());
		}
	}

	private void vaciarCampos() {
		this.ventanaOrdenCompra.getComponente_ComboBox().removeAllItems();
		this.ventanaOrdenCompra.getNombreRazonSocialTexto_lbl().setText("");
		this.ventanaOrdenCompra.getDireccionTexto_lbl().setText("");
		this.ventanaOrdenCompra.getMailTexto_lbl().setText("");
		this.ventanaOrdenCompra.getValorPresupuestado_txf().setText("");
		this.ventanaOrdenCompra.getTxtfldCargarOrden().setText("");
		this.ventanaOrdenCompra.getListaOrdenesCompras_cmb().setSelectedIndex(-1);
		this.ordenCompra = new OrdenCompra();
		actualizarTablaRepuestos();
	}
}
