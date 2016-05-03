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
import presentacion.vista.VentanaIngreso;

public class ControladorVentanaIngreso implements ActionListener {

	private VentanaIngreso ventana_ingreso;
	Ingreso ingreso;
	private List<MarcaDTO> lista_marcas;
	private List<TipoProductoDTO> lista_tiposproductos;

	public ControladorVentanaIngreso(VentanaIngreso ventana, Ingreso ingreso) {
		this.ventana_ingreso = ventana;
		this.ingreso = ingreso;
		this.ventana_ingreso.getBtnBuscarCliente().addActionListener(this);
		this.ventana_ingreso.getBtnAceptar().addActionListener(this);
		this.ventana_ingreso.getBtnCancelar().addActionListener(this);
	}

	public void inicializar() {
		if (ingreso.getId() != -1) {
			ingreso.cargarModeloCompleto();
			this.cargarVentana();
			llenarTablaCliente(ingreso.getCliente());
		} else {
			
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
		this.ventana_ingreso.getDireccionAlternativa().setSelected(ingreso.getIngreso().getEnvio_default());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.ventana_ingreso.getBtnBuscarCliente()) {
			String textoingresado = this.ventana_ingreso.getTxtNroCliente().getText();
			if (textoingresado == null || textoingresado.equals("")) {
				JOptionPane.showMessageDialog(this.ventana_ingreso, "Antes debe ingresar un nro de cliente para buscar",
						"Atencion!", JOptionPane.INFORMATION_MESSAGE);
			} else {
				try{
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
				} catch (NumberFormatException nfe){
					JOptionPane.showMessageDialog(this.ventana_ingreso, "El nro de cliente debe ser numerico! ",
							"Atencion!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} else if (e.getSource() == this.ventana_ingreso.getBtnAceptar()) {
			Boolean error = false;
			// verifico todos los campos
			String nombre_produ = this.ventana_ingreso.getTextNombreProducto().getText();
			if (nombre_produ.equals("")) {
				error = true;
				JOptionPane.showMessageDialog(this.ventana_ingreso, "Debes completar el nombre de producto",
						"Atencion!", JOptionPane.INFORMATION_MESSAGE);
			}

			String descripcion_falla = this.ventana_ingreso.getTextDescripcionFalla().getText();
			if (descripcion_falla.equals("")) {
				error = true;
				JOptionPane.showMessageDialog(this.ventana_ingreso,
						"Debes completar brevemente la descripcion de la falla", "Atencion!",
						JOptionPane.INFORMATION_MESSAGE);
			}

			if (!error) {
				IngresoDTO ingresoDTO = new IngresoDTO(0, this.ingreso.getCliente().getId(), nombre_produ,
						this.ventana_ingreso.getComboMarcas().getSelectedIndex(),
						this.ventana_ingreso.getComboTiposProductos().getSelectedIndex(),
							descripcion_falla,
							this.ventana_ingreso.getEnvioDomicilio().isSelected(), 
							this.ventana_ingreso.getDireccionAlternativa().isSelected(), null, 1, 0);				
				this.ingreso.ingr = ingresoDTO;
				
				this.ingreso.guardarIngreso(0);
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

	private void llenarTablaCliente(ClienteDTO client){
		Object[][] informacionCliente = {{ client.getNombre(), client.getApellido(), client.getDireccion(),client.getMail(), client.getTelefono()}};
		String[] nombreColumnas = { "Nombre", "Apellido", "Direcci�n", "Email", "Tel�fono" };
		this.ventana_ingreso.getClienteTable().setModel(new DefaultTableModel(informacionCliente, nombreColumnas));	
	}

}
