
package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dto.ClienteDTO;
import dto.IngresoDTO;
import dto.InsumoStockDTO;
import dto.UsuarioDTO;
import modelo.Ingreso;
import modelo.Stock;
import persistencia.dao.ClienteDAO;
import persistencia.dao.EstadoDAO;
import persistencia.dao.IngresoDAO;
import persistencia.dao.UsuarioDAO;
import presentacion.vista.VentanaHojaDeRuta;
import presentacion.vista.VentanaIngreso;
import presentacion.vista.VentanaOrdenCompra;
import presentacion.vista.VentanaPresupuesto;
import presentacion.vista.VentanaPrincipal;
import presentacion.vista.VentanaReparacion;
import presentacion.vista.VentanaSelectorFechasReporte;
import presentacion.vista.VentanaStock;

public class ControladorVentanaPrincipal implements ActionListener {

	private VentanaPrincipal principal;
	private IngresoDAO ingresoDAO;
	private ClienteDAO clienteDAO;
	private EstadoDAO estadoDAO;
	private UsuarioDAO usuarioDAO;
	private UsuarioDTO usuarioLogueado;
	private Stock stock;

	public ControladorVentanaPrincipal(VentanaPrincipal principal, UsuarioDTO usuario) {

		this.principal = principal;
		this.usuarioLogueado = usuario;
		this.principal.getIngresarProducto_btn().addActionListener(this);
		this.principal.getPresupuestar_btn().addActionListener(this);
		this.principal.getReparacion_btn().addActionListener(this);
		this.principal.getBtnElaborarHojaDe().addActionListener(this);
		this.principal.getBtnOrdenDeCompra().addActionListener(this);
		this.principal.getDeslogueo().addActionListener(this);
		this.principal.getOrdenCompra().addActionListener(this);
		this.principal.getStock().addActionListener(this);
		this.principal.getVenta().addActionListener(this);
		this.principal.getReparado().addActionListener(this);
		this.principal.getMasInsumido().addActionListener(this);
		this.agregarMouseListenerTabla(this, usuarioLogueado);
	}

	public void iniciar() {
		ingresoDAO = new IngresoDAO();
		clienteDAO = new ClienteDAO();
		estadoDAO = new EstadoDAO();
		usuarioDAO = new UsuarioDAO();
		stock = new Stock();
		this.adecuarVentanaPrincipal();
		this.cargar_tablaOrdenesTrabajo();
		this.actualizar_AvisoFaltante();
		this.principal.setLblUsuario(this.usuarioLogueado.getNick().toUpperCase());
	}

	private void adecuarVentanaPrincipal() {

		if (this.usuarioLogueado.getIdperfil() == 2) {
			// Visualizacion modo administrativo
			this.principal.getPresupuestar_btn().setVisible(false);
			this.principal.getReparacion_btn().setVisible(false);
			this.principal.setVisible(true);

		} else if (this.usuarioLogueado.getIdperfil() == 3) {
			// Visualizacion modo tecnico
			this.principal.getScrollPaneAviso().setVisible(false);
			this.principal.getIngresarProducto_btn().setVisible(false);
			this.principal.getBtnElaborarHojaDe().setVisible(false);
			this.principal.getBtnOrdenDeCompra().setVisible(false);
			this.principal.setVisible(true);

		} else if (this.usuarioLogueado.getIdperfil() == 1) {
			// Visualizacion modo jefe/admin
			this.principal.getScrollPaneAviso().setVisible(false);
			this.principal.setVisible(true);
		} else {

		}
	}

	public void limpiar_tablaOrdenesTrabajo() {

		int largo = ((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).getRowCount();

		for (int i = largo - 1; i >= 0; i--) {
			((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).removeRow(i);
		}

	}

	public void cargar_tablaOrdenesTrabajo() {
		// Consigo todos los ingresos y genero las filas
		ArrayList<IngresoDTO> ingresos = ingresoDAO.readAll();
		ObtenerFilas(ingresos);

	}

	private void ObtenerFilas(ArrayList<IngresoDTO> ingresos) {
		limpiar_tablaOrdenesTrabajo();
		for (int i = 0; i <= ingresos.size() - 1; i++) {
			UsuarioDTO usuarioDTO = usuarioDAO.find(ingresos.get(i).getTecnico_asignado());
			String nombreCompletoTecnicoAsignado = "";
			if (usuarioDTO != null) {
				nombreCompletoTecnicoAsignado = (usuarioDTO.getNombre());
			}
			ClienteDTO clienteDTO = clienteDAO.find(ingresos.get(i).getIdcliente());
			String nombreCliente = clienteDTO.getNombre() + " " + clienteDTO.getApellido();
			this.cargarFila(i, new JLabel(new ImageIcon(VentanaPrincipal.class.getResource("/ingreso.png"))),
					ingresos.get(i).getId(), ingresos.get(i).getFecha_creacion(), ingresos.get(i).getDescripcion(),
					nombreCliente, (ingresos.get(i).getEnvio()) ? "SI" : "NO",
					new JLabel(new ImageIcon(VentanaPrincipal.class.getResource("/money.png"))),
					nombreCompletoTecnicoAsignado, estadoDAO.find(ingresos.get(i).getEstado()).getDetalle());
		}
	}

	private void cargarFila(int fila, JLabel botonIngreso, int id, Date fecha, String descripcion, String cliente,
			String envio, JLabel botonPresupuesto, String tecnico_asignado, String estado) {

		Object[] ingreso = new Object[9];
		ingreso[0] = (JLabel) botonIngreso;
		ingreso[1] = id;
		ingreso[2] = fecha;
		ingreso[3] = descripcion;
		ingreso[4] = cliente;
		ingreso[5] = envio;
		ingreso[6] = (JLabel) botonPresupuesto;
		ingreso[7] = tecnico_asignado;
		ingreso[8] = estado;

		// Agrego fila solo en caso de que lo permita el perfil (hardcodeo luego
		// modificar)
		if (this.usuarioLogueado.getIdperfil() == 2
				&& (estado.equals("NUEVO") || estado.equals("INFORMADO") || estado.equals("IRREPARABLE")
						|| estado.equals("REPARADO") || estado.equals("RECHAZADO") || estado.equals("AVISO DE RETIRO")
						|| estado.equals("RETIRADO") || estado.equals("") || estado.equals("PRESUPUESTADO"))) {// ADMINISTRATIVO

			((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).addRow(ingreso);

		} else if (this.usuarioLogueado.getIdperfil() == 3 && (estado.equals("NUEVO") || estado.equals("ACEPTADO")
				|| estado.equals("EN REPARACION") || estado.equals("PRESUPUESTANDO") || estado.equals("REPARADO"))) {// TECNICO

			((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).addRow(ingreso);
		} else if (this.usuarioLogueado.getIdperfil() == 1) {// ADMINISTRADOR
			((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).addRow(ingreso);
		}

		// Establezco que no se pueda editar pero si seleccionar una fila
		((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).isCellEditable(fila, 0);
		((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).isCellEditable(fila, 1);
		((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).isCellEditable(fila, 2);
		((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).isCellEditable(fila, 3);
		((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).isCellEditable(fila, 4);
		((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).isCellEditable(fila, 5);
		((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).isCellEditable(fila, 6);
		((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).isCellEditable(fila, 7);
		((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).isCellEditable(fila, 8);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.principal.getIngresarProducto_btn()) {
			ControladorVentanaIngreso controladorVentanaIngreso = new ControladorVentanaIngreso(new VentanaIngreso(),
					new Ingreso(), this, this.usuarioLogueado);

			controladorVentanaIngreso.inicializar();

		} else if (e.getSource() == this.principal.getPresupuestar_btn()) {

			if (this.principal.getOrdenesDeTrabajo_table().getSelectedRow() >= 0) {
				int nroIngreso = (int) this.principal.getOrdenesDeTrabajo_table()
						.getValueAt(this.principal.getOrdenesDeTrabajo_table().getSelectedRow(), 1);
				String estado = (String) this.principal.getOrdenesDeTrabajo_table()
						.getValueAt(this.principal.getOrdenesDeTrabajo_table().getSelectedRow(), 8);
				if (estado.equals("NUEVO")) {

					Ingreso ing = new Ingreso();
					ing.setId(nroIngreso);
					ing.cargarModeloCompleto();

					ControladorPresupuesto controladorPresupuesto = new ControladorPresupuesto(new VentanaPresupuesto(),
							ing, this, usuarioLogueado);
					controladorPresupuesto.inicializar();
				} else {
					JOptionPane.showMessageDialog(null,
							"No es posible presupuestar el registro seleccionado. Por favor, seleccione una orden en estado 'NUEVO'.");
				}
			}else{
				JOptionPane.showMessageDialog(principal, "Debe seleccionar una orden de trabajo en estado 'NUEVO' para continuar.");
			}
		} else if (e.getSource() == this.principal.getReparacion_btn()) {

			if (this.principal.getOrdenesDeTrabajo_table().getSelectedRow() >= 0) {
				int nroIngreso = (int) this.principal.getOrdenesDeTrabajo_table()
						.getValueAt(this.principal.getOrdenesDeTrabajo_table().getSelectedRow(), 1);
				String estado = (String) this.principal.getOrdenesDeTrabajo_table()
						.getValueAt(this.principal.getOrdenesDeTrabajo_table().getSelectedRow(), 8);
				if (estado.equals("ASIGNADO")) {

					Ingreso ing = new Ingreso();
					ing.setId(nroIngreso);
					ing.cargarModeloCompleto();

					ControladorReparacion controladorReparacion = new ControladorReparacion(new VentanaReparacion(),
							this, ing, usuarioLogueado);
					controladorReparacion.inicializar();
				} else {
					JOptionPane.showMessageDialog(null,
							"No es posible visualizar el detalle de reparación. Por favor, seleccione una orden en estado 'ASIGNADO'.");
				}
			}else{
				JOptionPane.showMessageDialog(principal, "Debe seleccionar una orden de trabajo en estado 'ASIGNADO' para continuar.");
			}

		} else if (e.getSource() == this.principal.getBtnElaborarHojaDe()) {
			ControladorVentanaHojaDeRuta hojaruta = new ControladorVentanaHojaDeRuta(new VentanaHojaDeRuta(), this,
					usuarioLogueado);
			hojaruta.inicializar();
		} else if (e.getSource() == this.principal.getBtnOrdenDeCompra()) {
			ControladorOrdenCompra a = new ControladorOrdenCompra(new VentanaOrdenCompra(), usuarioLogueado);
			a.inicializar();
		} else if (e.getSource() == this.principal.getDeslogueo()) {

			this.principal.setVisible(false);

			ControladorVentanaLogin controlLogin = new ControladorVentanaLogin();
			controlLogin.getPantalla();
		} else if (e.getSource() == this.principal.getOrdenCompra()) {

			VentanaOrdenCompra ventanaOdenCompra = new VentanaOrdenCompra();
			ControladorOrdenCompra c = new ControladorOrdenCompra(ventanaOdenCompra, usuarioLogueado);
			c.inicializar();

		} else if (e.getSource() == this.principal.getStock()) {

			VentanaStock stock = new VentanaStock();
			ControladorVentanaStock c = new ControladorVentanaStock(stock, usuarioLogueado);
			c.inicializar();

			stock.addWindowListener(new WindowListener() {

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
					actualizar_AvisoFaltante();
				}

				@Override
				public void windowActivated(WindowEvent e) {
				}
			});

		} else if (e.getSource() == this.principal.getVenta()) {
			ControladorSelectorFechasReporte a = new ControladorSelectorFechasReporte(1,
					new VentanaSelectorFechasReporte());
			a.inicializar();
		} else if (e.getSource() == this.principal.getReparado()) {
			ControladorSelectorFechasReporte a = new ControladorSelectorFechasReporte(2,
					new VentanaSelectorFechasReporte());
			a.inicializar();
		} else if (e.getSource() == this.principal.getMasInsumido()) {
			ControladorSelectorFechasReporte a = new ControladorSelectorFechasReporte(3,
					new VentanaSelectorFechasReporte());
			a.inicializar();
		}
	}

	private void agregarMouseListenerTabla(ControladorVentanaPrincipal controladorVentanaPrincipal,
			UsuarioDTO usuario) {
		this.principal.getOrdenesDeTrabajo_table().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				@SuppressWarnings("unused")
				int fila = principal.getOrdenesDeTrabajo_table().rowAtPoint(e.getPoint());
				int columna = principal.getOrdenesDeTrabajo_table().columnAtPoint(e.getPoint());

				// Preguntamos si hicimos clic sobre la celda que contiene el
				// botï¿½n "Ver Orden"
				if (principal.getOrdenesDeTrabajo_table().getModel().getColumnClass(columna).equals(JLabel.class)
						&& columna == 0) {
					if (principal.getOrdenesDeTrabajo_table().getSelectedRow() >= 0) {
						int nroIngreso = (int) principal.getOrdenesDeTrabajo_table()
								.getValueAt(principal.getOrdenesDeTrabajo_table().getSelectedRow(), 1);
						IngresoDTO ingresoDTO = ingresoDAO.find(nroIngreso);
						Ingreso ingreso = new Ingreso();
						ingreso.setId(ingresoDTO.getId());

						ControladorVentanaIngreso controladorVentanaIngreso = new ControladorVentanaIngreso(
								new VentanaIngreso(), ingreso, controladorVentanaPrincipal, usuario);

						controladorVentanaIngreso.inicializar();
					}
				}
				// Preguntamos si hicimos clic sobre la celda que contiene el
				// botï¿½n "Presupuesto"
				if (principal.getOrdenesDeTrabajo_table().getModel().getColumnClass(columna).equals(JLabel.class)
						&& columna == 6) {
					if (principal.getOrdenesDeTrabajo_table().getSelectedRow() >= 0) {
						String estado = (String) principal.getOrdenesDeTrabajo_table()
								.getValueAt(principal.getOrdenesDeTrabajo_table().getSelectedRow(), 8);
						if (!estado.equals("NUEVO")) {
							int nroIngreso = (int) principal.getOrdenesDeTrabajo_table()
									.getValueAt(principal.getOrdenesDeTrabajo_table().getSelectedRow(), 1);
							Ingreso ingreso = new Ingreso();
							ingreso.setId(nroIngreso);
							ingreso.cargarModeloCompleto();

							ControladorPresupuesto controladorPresupuesto = new ControladorPresupuesto(
									new VentanaPresupuesto(), ingreso, controladorVentanaPrincipal, usuarioLogueado);
							controladorPresupuesto.inicializar();
						} else {
							JOptionPane.showMessageDialog(null,
									"Disculpe, la orden de trabajo seleccionada no se encuentra presupuestada.");
						}
					}
				}
			}
		});
	}

	public void limpiar_tablaAvisoFaltante() {

		int largo = ((DefaultTableModel) this.principal.getTable_avisoFaltante().getModel()).getRowCount();

		for (int i = largo - 1; i >= 0; i--) {
			((DefaultTableModel) this.principal.getTable_avisoFaltante().getModel()).removeRow(i);
		}

	}

	public void actualizar_AvisoFaltante() {
		limpiar_tablaAvisoFaltante();
		ArrayList<InsumoStockDTO> insumos = stock.getFiltrado();
		for (int i = 0; i <= insumos.size() - 1; i++) {
			cargar_FilaTablaAvisoFaltante(insumos.get(i).getNombre());
		}

	}

	private void cargar_FilaTablaAvisoFaltante(String insumo) {
		String[] dato = { insumo };
		((DefaultTableModel) this.principal.getTable_avisoFaltante().getModel()).addRow(dato);

	}

}
