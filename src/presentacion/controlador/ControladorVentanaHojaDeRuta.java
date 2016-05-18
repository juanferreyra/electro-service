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
import javax.swing.table.DefaultTableModel;

import dto.ClienteDTO;
import dto.HojaDeRutaIngresosDTO;
import dto.IngresoDTO;
import dto.PerfilDTO;
import dto.UsuarioDTO;
import modelo.HojaDeRuta;
import presentacion.vista.VentanaHojaDeRuta;

public class ControladorVentanaHojaDeRuta implements ActionListener{
	
	protected static final String JCheckBox = null;
	private VentanaHojaDeRuta ventanaHojaRuta;
	private ControladorVentanaPrincipal controladorVentanaPrincipal;
	private UsuarioDTO usuarioLogueado;
	private HojaDeRuta hojaDeRuta;
	@SuppressWarnings("unused")
	private Calendar hoy = new GregorianCalendar();
	
	public ControladorVentanaHojaDeRuta( VentanaHojaDeRuta ventanaHojaRuta,
			ControladorVentanaPrincipal controladorVentanaPrincipal, UsuarioDTO usuario) {
		this.usuarioLogueado = usuario;
		this.ventanaHojaRuta = ventanaHojaRuta;
		this.controladorVentanaPrincipal = controladorVentanaPrincipal;
		this.hojaDeRuta = new HojaDeRuta();
		agregarMouseListenerTabla();
	}
	
	public void inicializar() {
		
		//Calendar hoy = new GregorianCalendar();
		//ventanaHojaRuta.getFechaIngreso_lbl().setText("Fecha: " + hoy.get(Calendar.DAY_OF_MONTH) +" / "
		//+ hoy.get(Calendar.MONTH) +" / "+ hoy.get(Calendar.YEAR));
		ventanaHojaRuta.setVisible(true);
		//cargo el usuario
		if(hojaDeRuta.getId()==-1) {
			cargar_tablaOrdenesTrabajoReparadas();
		} else {
			this.hojaDeRuta.cargarVariables();
			cargarModelo();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	private void cargarModelo() {
		//deberia cargar la tabla con los seleccionados
	}
	
	public void limpiar_tablaOrdenesTrabajo() {
		int largo = ((DefaultTableModel) this.ventanaHojaRuta.getOrdenesDeTrabajo_table().getModel()).getRowCount();

		for (int i = largo - 1; i >= 0; i--) {
			((DefaultTableModel) this.ventanaHojaRuta.getOrdenesDeTrabajo_table().getModel()).removeRow(i);
		}
	}
	
	public void cargar_tablaOrdenesTrabajoReparadas() {
		// Consigo todos los ingresos y genero las filas
		ArrayList<IngresoDTO> ingresos = hojaDeRuta.getIngresoDAO().readReparadosNoReparados();
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
							ingresos.get(i).getDescripcion(), nombreCliente, direccion, new JCheckBox());
		}
	}
	
	private void cargarFila( int nro, Date fecha,
			String producto, String cliente, String direccion, JCheckBox estado) {
		Object[] ingreso = new Object[6];
		ingreso[0] = nro;
		ingreso[1] = fecha.getTime();
		ingreso[2] = producto;
		ingreso[3] = cliente;
		ingreso[4] = direccion;
		ingreso[5] = (JCheckBox) estado;
		
		((DefaultTableModel) this.ventanaHojaRuta.getOrdenesDeTrabajo_table().getModel()).addRow(ingreso);
		
		((DefaultTableModel) this.ventanaHojaRuta.getOrdenesDeTrabajo_table().getModel()).isCellEditable(nro, 0);
		((DefaultTableModel) this.ventanaHojaRuta.getOrdenesDeTrabajo_table().getModel()).isCellEditable(nro, 1);
		((DefaultTableModel) this.ventanaHojaRuta.getOrdenesDeTrabajo_table().getModel()).isCellEditable(nro, 2);
		((DefaultTableModel) this.ventanaHojaRuta.getOrdenesDeTrabajo_table().getModel()).isCellEditable(nro, 3);
		((DefaultTableModel) this.ventanaHojaRuta.getOrdenesDeTrabajo_table().getModel()).isCellEditable(nro, 4);
		
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
						&& columna == 5) {
					if (ventanaHojaRuta.getOrdenesDeTrabajo_table().getSelectedRow() >= 0) {
						
						int seleccionado = ventanaHojaRuta.getOrdenesDeTrabajo_table().getSelectedRow();
						
						int nroIngreso = (int) ventanaHojaRuta.getOrdenesDeTrabajo_table().getValueAt(seleccionado, 0);
						//cambio el check
						Object check = ventanaHojaRuta.getOrdenesDeTrabajo_table().getModel().getValueAt(seleccionado, 5);
						JCheckBox hc = (javax.swing.JCheckBox) check;
						JCheckBox nuevoCheck = new JCheckBox();
						
						if(hc.isSelected()) {
							//seteo el check habilitado
							nuevoCheck.setSelected(false);
							ventanaHojaRuta.getOrdenesDeTrabajo_table().getModel().setValueAt( nuevoCheck, seleccionado, 5);
							//saco de la lista de los seleccionados el ingreso
							for (int i = 0; i < hojaDeRuta.getIngresosEnHoja().size(); i++) {
								if(hojaDeRuta.getIngresosEnHoja().get(i).getIdIngreso()==nroIngreso) {
									hojaDeRuta.getIngresosEnHoja().remove(i);
								}
							}
							
						} else {
							//seteo el check habilitado
							nuevoCheck.setSelected(true);
							ventanaHojaRuta.getOrdenesDeTrabajo_table().getModel().setValueAt( nuevoCheck, seleccionado, 5);
							//ingreso de la lista de seleccionados el ingreso
							HojaDeRutaIngresosDTO nuevo = new HojaDeRutaIngresosDTO(0, hojaDeRuta.getId(), nroIngreso, true, null);
							hojaDeRuta.getIngresosEnHoja().add(nuevo);
						}
					}
				}
			}
		});
	}
	
	private void validarCampos() {
		//validaria los campos ingresados
	}
	
	public static void main(String[] args) {
		PerfilDTO perf3 = new PerfilDTO("JEFE");
		UsuarioDTO user3 = new UsuarioDTO(3, "JOAQUIN", "TELECHEA", "jefe", perf3);
		
		ControladorVentanaHojaDeRuta hojaruta = new ControladorVentanaHojaDeRuta(new VentanaHojaDeRuta(), null, user3);
		
		hojaruta.inicializar();
	}
	
	

}
