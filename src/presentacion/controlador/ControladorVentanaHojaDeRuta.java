package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dto.ClienteDTO;
import dto.FleteDTO;
import dto.HojaDeRutaIngresosDTO;
import dto.IngresoDTO;
import dto.PerfilDTO;
import dto.UsuarioDTO;
import modelo.HojaDeRuta;
import presentacion.vista.VentanaHojaDeRuta;

public class ControladorVentanaHojaDeRuta implements ActionListener {
	
	protected static final String JCheckBox = null;
	private VentanaHojaDeRuta ventanaHojaRuta;
	private ControladorVentanaPrincipal controladorVentanaPrincipal;
	private UsuarioDTO usuarioLogueado;
	private HojaDeRuta hojaDeRuta;
	@SuppressWarnings("unused")
	private Calendar hoy = new GregorianCalendar();

	public ControladorVentanaHojaDeRuta(VentanaHojaDeRuta ventanaHojaRuta,
			ControladorVentanaPrincipal controladorVentanaPrincipal, UsuarioDTO usuario) {
		this.usuarioLogueado = usuario;
		this.ventanaHojaRuta = ventanaHojaRuta;
		this.controladorVentanaPrincipal = controladorVentanaPrincipal;
		this.hojaDeRuta = new HojaDeRuta();
		
		this.ventanaHojaRuta.getBtnBuscarConductor().addActionListener(this);
		this.ventanaHojaRuta.getBtnCargarHoja().addActionListener(this);
		this.ventanaHojaRuta.getBtnGuardar().addActionListener(this);
		this.ventanaHojaRuta.getBtnImprimir().addActionListener(this);
		this.ventanaHojaRuta.getBtnCancelar().addActionListener(this);
		this.ventanaHojaRuta.getBtnBorrarCarga().addActionListener(this);
		this.ventanaHojaRuta.getBtnBorrarCarga().setVisible(false);
		this.ventanaHojaRuta.getBtnImprimir().setVisible(false);
		
		agregarMouseListenerTabla();
	}

	public void inicializar() {

		// Calendar hoy = new GregorianCalendar();
		// ventanaHojaRuta.getFechaIngreso_lbl().setText("Fecha: " +
		// hoy.get(Calendar.DAY_OF_MONTH) +" / "
		// + hoy.get(Calendar.MONTH) +" / "+ hoy.get(Calendar.YEAR));
		ventanaHojaRuta.setVisible(true);
		//cargo el usuario
		
		cargar_tablaOrdenesTrabajoReparadas();
	}
	
	private void cargarModelo() {
		if(hojaDeRuta.getHojaRuta().getId()!=-1) {
			this.ventanaHojaRuta.getTxtfldNombreConductor().setText(this.hojaDeRuta.getFlete().getNombre());
			this.ventanaHojaRuta.getTxtfldMovil().setText(this.hojaDeRuta.getFlete().getModelo());
			this.ventanaHojaRuta.getTxtfldTelefono().setText(this.hojaDeRuta.getFlete().getTelefono());
			this.ventanaHojaRuta.getTxtfldPatente().setText(this.hojaDeRuta.getFlete().getPatente());
			
			ArrayList<IngresoDTO> ingresos = this.hojaDeRuta.getIngresosSeleccionadosEnHoja();

			ObtenerFilas(ingresos);
		} else {
			JOptionPane.showMessageDialog(this.ventanaHojaRuta, "La hoja que intenta buscar no existe!", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void limpiar_tablaOrdenesTrabajo() {
		int largo = ((DefaultTableModel) this.ventanaHojaRuta.getOrdenesDeTrabajo_table().getModel()).getRowCount();

		for (int i = largo - 1; i >= 0; i--) {
			((DefaultTableModel) this.ventanaHojaRuta.getOrdenesDeTrabajo_table().getModel()).removeRow(i);
		}
	}
	
	public void cargar_tablaOrdenesTrabajoReparadas() {
		// Consigo todos los ingresos y genero las filas
		ArrayList<IngresoDTO> ingresos = hojaDeRuta.getIngresosReparados();
		ObtenerFilas(ingresos);
	}

	private void ObtenerFilas(ArrayList<IngresoDTO> ingresos) {
		limpiar_tablaOrdenesTrabajo();
		
		for (int i = 0; i <= ingresos.size()-1; i++) {
			//preparo el nombre del cliente
			ClienteDTO cliente = hojaDeRuta.getClienteDAO().find(ingresos.get(i).getIdcliente());
			String nombreCliente = cliente.getNombre()+" "+cliente.getApellido();
			String direccion = cliente.getDireccion();
			//preparo la direccion del cliente
			if(ingresos.get(i).getEnvio_default()) {
				direccion = ingresos.get(i).getDireccion_alternativa();
			}
			
			this.cargarFila(ingresos.get(i).getId(),ingresos.get(i).getFecha_creacion(),
							ingresos.get(i).getDescripcion(), nombreCliente, cliente.getLocalidad(), direccion, new JCheckBox());
		}
	}
	
	private void cargarFila( int nro, Date fecha,
			String producto, String cliente, String localidad, String direccion, JCheckBox estado) {
		Object[] ingreso = new Object[7];
		ingreso[0] = nro;
		ingreso[1] = fecha.getTime();
		ingreso[2] = producto;
		ingreso[3] = cliente;
		ingreso[4] = localidad;
		ingreso[5] = direccion;
		ingreso[6] = (JCheckBox) estado;
		
		((DefaultTableModel) this.ventanaHojaRuta.getOrdenesDeTrabajo_table().getModel()).addRow(ingreso);
		
		((DefaultTableModel) this.ventanaHojaRuta.getOrdenesDeTrabajo_table().getModel()).isCellEditable(nro, 0);
		((DefaultTableModel) this.ventanaHojaRuta.getOrdenesDeTrabajo_table().getModel()).isCellEditable(nro, 1);
		((DefaultTableModel) this.ventanaHojaRuta.getOrdenesDeTrabajo_table().getModel()).isCellEditable(nro, 2);
		((DefaultTableModel) this.ventanaHojaRuta.getOrdenesDeTrabajo_table().getModel()).isCellEditable(nro, 3);
		((DefaultTableModel) this.ventanaHojaRuta.getOrdenesDeTrabajo_table().getModel()).isCellEditable(nro, 4);
		((DefaultTableModel) this.ventanaHojaRuta.getOrdenesDeTrabajo_table().getModel()).isCellEditable(nro, 5);
		
	}

	private void agregarMouseListenerTabla() {
		this.ventanaHojaRuta.getOrdenesDeTrabajo_table().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				@SuppressWarnings("unused")
				int fila = ventanaHojaRuta.getOrdenesDeTrabajo_table().rowAtPoint(e.getPoint());
				int columna = ventanaHojaRuta.getOrdenesDeTrabajo_table().columnAtPoint(e.getPoint());

				// Preguntamos si hicimos clic sobre la celda que contiene el
				// check
				if (ventanaHojaRuta.getOrdenesDeTrabajo_table().getModel().getColumnClass(columna).equals(JCheckBox.class)
						&& columna == 6) {
					if (ventanaHojaRuta.getOrdenesDeTrabajo_table().getSelectedRow() >= 0) {
						
						int seleccionado = ventanaHojaRuta.getOrdenesDeTrabajo_table().getSelectedRow();
						
						int nroIngreso = (int) ventanaHojaRuta.getOrdenesDeTrabajo_table().getValueAt(seleccionado, 0);
						//cambio el check
						Object check = ventanaHojaRuta.getOrdenesDeTrabajo_table().getModel().getValueAt(seleccionado, 6);
						JCheckBox hc = (javax.swing.JCheckBox) check;
						JCheckBox nuevoCheck = new JCheckBox();
						
						if(hc.isSelected()) {
							//seteo el check habilitado
							nuevoCheck.setSelected(false);
							ventanaHojaRuta.getOrdenesDeTrabajo_table().getModel().setValueAt( nuevoCheck, seleccionado, 6);
							//saco de la lista de los seleccionados el ingreso
							for (int i = 0; i < hojaDeRuta.getIngresosEnHoja().size(); i++) {
								if(hojaDeRuta.getIngresosEnHoja().get(i).getIdIngreso()==nroIngreso) {
									hojaDeRuta.getIngresosEnHoja().remove(i);
								}
							}
							
						} else {
							//seteo el check habilitado
							nuevoCheck.setSelected(true);
							ventanaHojaRuta.getOrdenesDeTrabajo_table().getModel().setValueAt( nuevoCheck, seleccionado, 6);
							//ingreso de la lista de seleccionados el ingreso
							HojaDeRutaIngresosDTO nuevo = new HojaDeRutaIngresosDTO(0, hojaDeRuta.getId(), nroIngreso, true, null);
							hojaDeRuta.getIngresosEnHoja().add(nuevo);
						}
					}
				}
			}
		});
	}
	
	private boolean validarCampos() {
		boolean error = false;
		if(this.ventanaHojaRuta.getTxtfldNombreConductor().getText().equals("")) { 
			JOptionPane.showMessageDialog(this.ventanaHojaRuta, "Debes seleccionr un Fletero", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
			error = true;
		}
		
		if(this.hojaDeRuta.getIngresosSeleccionadosEnHoja().size()==0 && error==false) { 
			JOptionPane.showMessageDialog(this.ventanaHojaRuta, "Debes seleccionr al menos una orden de trabajo para su envio", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
			error = true;
		}
		
		return !error;
	}
	
	private boolean soloNumeros(String texto){
		try { 
			Integer.parseInt(texto); 
			return true; 
		} catch (Exception e) { 
			return false; 
		}
	}
	
	private void cargarFlete(int nro) {
		
		this.hojaDeRuta.cargarFlete(nro);
		
		FleteDTO flete = this.hojaDeRuta.getFlete();
		
		if(flete==null) {
			JOptionPane.showMessageDialog(this.ventanaHojaRuta, "No se encontro ningun conductor con ese numero", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			this.ventanaHojaRuta.getTxtfldNombreConductor().setText(flete.getNombre());
			this.ventanaHojaRuta.getTxtfldMovil().setText(flete.getModelo());
			this.ventanaHojaRuta.getTxtfldPatente().setText(flete.getPatente());
			this.ventanaHojaRuta.getTxtfldTelefono().setText(flete.getTelefono());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == this.ventanaHojaRuta.getBtnCargarHoja()) {
			String nroCarga = this.ventanaHojaRuta.getTxtfldCargarHoja().getText();
			
			if(soloNumeros(nroCarga)) {
				int id = Integer.parseInt(nroCarga);
				if(this.hojaDeRuta.existeHojaDeRuta(id)){
					this.hojaDeRuta.setId(id);
					this.hojaDeRuta.cargarVariables();
					cargarModelo();
					this.ventanaHojaRuta.getBtnBuscarConductor().setEnabled(false);
					this.ventanaHojaRuta.getTxtflBuscarConductor().setEnabled(false);
					this.ventanaHojaRuta.getBtnGuardar().setVisible(false);
					this.ventanaHojaRuta.getBtnCancelar().setText("Cerrar");
					this.ventanaHojaRuta.getBtnBorrarCarga().setVisible(true);
					this.ventanaHojaRuta.getBtnImprimir().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(this.ventanaHojaRuta, "No se encontro ninguna hoja de ruta con ese nro", "Atencion!",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			} else {
				JOptionPane.showMessageDialog(this.ventanaHojaRuta, "Campo para cargar la hoja debe ser numerico ", "Atencion!",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
		} else if(e.getSource() == this.ventanaHojaRuta.getBtnBorrarCarga()) {
			this.ventanaHojaRuta.getTxtfldCargarHoja().setText("");
			this.hojaDeRuta.setId(-1);
			this.hojaDeRuta.cargarVariables();
			cargar_tablaOrdenesTrabajoReparadas();
			
			this.ventanaHojaRuta.getBtnBuscarConductor().setEnabled(true);
			this.ventanaHojaRuta.getTxtflBuscarConductor().setEnabled(true);
			this.ventanaHojaRuta.getBtnGuardar().setVisible(true);
			this.ventanaHojaRuta.getBtnCancelar().setText("Cancelar");
			this.ventanaHojaRuta.getBtnBorrarCarga().setVisible(false);
			this.ventanaHojaRuta.getTxtfldMovil().setText("");
			this.ventanaHojaRuta.getTxtfldNombreConductor().setText("");
			this.ventanaHojaRuta.getTxtfldPatente().setText("");
			this.ventanaHojaRuta.getTxtfldTelefono().setText("");
			this.ventanaHojaRuta.getBtnImprimir().setVisible(false);
			
		} else if(e.getSource() == this.ventanaHojaRuta.getBtnCancelar()) {
			this.ventanaHojaRuta.dispose();
			this.controladorVentanaPrincipal.cargar_tablaOrdenesTrabajo();
		} else if(e.getSource() == this.ventanaHojaRuta.getBtnBuscarConductor()) {
			String nroConductor = this.ventanaHojaRuta.getTxtflBuscarConductor().getText();
			if(soloNumeros(nroConductor)) {
				int nroConductorInt = Integer.parseInt(nroConductor);
				cargarFlete(nroConductorInt);
			} else {
				JOptionPane.showMessageDialog(this.ventanaHojaRuta, "Campo para buscar el fletero debe ser numerico", "Atencion!",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else if(e.getSource() == this.ventanaHojaRuta.getBtnGuardar()) {
			if(validarCampos()) {
				boolean entro = this.hojaDeRuta.guardarModelo(usuarioLogueado);
				if(!entro) {
					JOptionPane.showMessageDialog(this.ventanaHojaRuta, "Ocurrio un error al guardar la hoja de ruta!", "Atencion!",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this.ventanaHojaRuta, "Hoja de ruta guadada Correctamente!", "Atencion!",
							JOptionPane.INFORMATION_MESSAGE); 
					//vacio todos los datos o muestro la impresion
					this.ventanaHojaRuta.getBtnImprimir().setVisible(true);
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		PerfilDTO perf3 = new PerfilDTO("JEFE");
		UsuarioDTO user3 = new UsuarioDTO(3, "JOAQUIN", "TELECHEA", "jefe", perf3);
		
		ControladorVentanaHojaDeRuta hojaruta = new ControladorVentanaHojaDeRuta(new VentanaHojaDeRuta(), null, user3);
		
		hojaruta.inicializar();
	}

}