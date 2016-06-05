
package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import dto.IngresoDTO;
import dto.UsuarioDTO;
import modelo.Ingreso;
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

public class ControladorVentanaPrincipal implements ActionListener {

	private VentanaPrincipal principal;
	private IngresoDAO ingresoDAO;
	private ClienteDAO clienteDAO;
	private EstadoDAO estadoDAO;
	private UsuarioDAO usuarioDAO;
	private UsuarioDTO usuarioLogueado;

	public ControladorVentanaPrincipal(VentanaPrincipal principal, UsuarioDTO usuario) {

		this.principal = principal;
		this.usuarioLogueado = usuario;
		this.principal.getIngresarProducto_btn().addActionListener(this);
		this.principal.getPresupuestar_btn().addActionListener(this);
		this.principal.getReparacion_btn().addActionListener(this);
		this.principal.getBtnElaborarHojaDe().addActionListener(this);
		this.principal.getBtnOrdenDeCompra().addActionListener(this);
		this.principal.getDeslogueo().addActionListener(this);
		this.agregarMouseListenerTabla(this);
	}

	public void iniciar() {
		ingresoDAO = new IngresoDAO();
		clienteDAO = new ClienteDAO();
		estadoDAO = new EstadoDAO();
		usuarioDAO = new UsuarioDAO();
		this.adecuarVentanaPrincipal();
		this.cargar_tablaOrdenesTrabajo();
	}

	private void adecuarVentanaPrincipal() {

		if (this.usuarioLogueado.getIdperfil() == 2) {
			// Visualizacion modo administrativo
			this.principal.getPresupuestar_btn().setVisible(false);
			this.principal.getReparacion_btn().setVisible(false);
			this.principal.setVisible(true);

		} else if (this.usuarioLogueado.getIdperfil() == 3) {
			// Visualizacion modo tecnico
			this.principal.getIngresarProducto_btn().setVisible(false);
			this.principal.getBtnElaborarHojaDe().setVisible(false);
			this.principal.getBtnOrdenDeCompra().setVisible(false);
			this.principal.setVisible(true);

		} else if (this.usuarioLogueado.getIdperfil() == 1) {
			// Visualizacion modo jefe/admin
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
			String nombreCompletoTecnicoAsignado = "";
			if (ingresos.get(i).getTecnico_asignado() != 0) {
				nombreCompletoTecnicoAsignado = usuarioDAO.find(ingresos.get(i).getTecnico_asignado()).getNombre() + " "
						+ usuarioDAO.find(ingresos.get(i).getTecnico_asignado()).getApellido();

			}
			this.cargarFila(i, new JLabel(new ImageIcon(VentanaPrincipal.class.getResource("/document-text.png"))),
					ingresos.get(i).getId(), ingresos.get(i).getFecha_creacion(), ingresos.get(i).getDescripcion(),
					clienteDAO.find(ingresos.get(i).getIdcliente()).getNombre(),
					(ingresos.get(i).getEnvio()) ? "SI" : "NO",
					new JLabel(new ImageIcon(VentanaPrincipal.class.getResource("/document-text.png"))),
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
		if (this.usuarioLogueado.getIdperfil() == 2 && (estado.equals("NUEVO") || estado.equals("INFORMADO")
				|| estado.equals("IRREPARABLE") || estado.equals("REPARADO") || estado.equals("RECHAZADO")
				|| estado.equals("AVISO DE RETIRO") || estado.equals("RETIRADO") || estado.equals(""))) {// HARDCODEO

			((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).addRow(ingreso);

		} else if (this.usuarioLogueado.getIdperfil() == 3 && (estado.equals("NUEVO") || estado.equals("ACEPTADO")
				|| estado.equals("EN REPARACION") || estado.equals("PRESUPUESTANDO") || estado.equals("REPARADO"))) {// HARDCODEO

			((DefaultTableModel) this.principal.getOrdenesDeTrabajo_table().getModel()).addRow(ingreso);
		} else if (this.usuarioLogueado.getIdperfil() == 1) {
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
					new Ingreso(), this);

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
							"No es posible visualizar el detalle de reparaci�n. Por favor, seleccione una orden en estado 'ASIGNADO'.");
				}
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
		}
	}

	private void agregarMouseListenerTabla(ControladorVentanaPrincipal controladorVentanaPrincipal) {
		this.principal.getOrdenesDeTrabajo_table().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				@SuppressWarnings("unused")
				int fila = principal.getOrdenesDeTrabajo_table().rowAtPoint(e.getPoint());
				int columna = principal.getOrdenesDeTrabajo_table().columnAtPoint(e.getPoint());

				// Preguntamos si hicimos clic sobre la celda que contiene el
				// bot�n "Ver Orden"
				if (principal.getOrdenesDeTrabajo_table().getModel().getColumnClass(columna).equals(JLabel.class)
						&& columna == 0) {
					if (principal.getOrdenesDeTrabajo_table().getSelectedRow() >= 0) {
						int nroIngreso = (int) principal.getOrdenesDeTrabajo_table()
								.getValueAt(principal.getOrdenesDeTrabajo_table().getSelectedRow(), 1);
						IngresoDTO ingresoDTO = ingresoDAO.find(nroIngreso);
						Ingreso ingreso = new Ingreso();
						ingreso.setId(ingresoDTO.getId());

						ControladorVentanaIngreso controladorVentanaIngreso = new ControladorVentanaIngreso(
								new VentanaIngreso(), ingreso, controladorVentanaPrincipal);

						controladorVentanaIngreso.inicializar();
					}
				}
				// Preguntamos si hicimos clic sobre la celda que contiene el
				// bot�n "Presupuesto"
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
}
