package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import dto.MarcaDTO;
import dto.TipoProductoDTO;
import modelo.Ingreso;
import presentacion.vista.VentanaIngreso;

public class ControladorVentanaIngreso implements ActionListener{
	
	private VentanaIngreso ventana_ingreso;
	private Ingreso ingreso;
	private List<MarcaDTO> lista_marcas;
	private List<TipoProductoDTO> lista_tiposproductos;

	public ControladorVentanaIngreso(VentanaIngreso ventana, Ingreso ingreso) {
		this.ventana_ingreso = ventana;
		this.ingreso = ingreso;
		this.ventana_ingreso.getBtnBuscarCliente().addActionListener(this);
	}
	
	public void inicializar()
	{
		if(ingreso.getId()!=-1)
		{
			ingreso.cargarModeloCompleto();
		}
		this.llenarComboMarcas();
		this.llenarComboTiposProductos();
		
		this.ventana_ingreso.getTextNombreProducto().setText(ingreso.getIngreso().getDescripcion());
		this.ventana_ingreso.getTextDescripcionFalla().setText(ingreso.getIngreso().getDescripcion_falla());
		this.ventana_ingreso.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
	
	/***
	 * TODO:Para ingresar los datos del cliente cuando se apreta la lupa --> utilizar el
	 * formato Object[][] informacionTabla = {{ "Cosme", "Fulanito", "Avenida siempre viva 123",
	 * "cosme@hotmail.com","4463-0000" }}; 
	 **/
	
	private void llenarComboMarcas()
	{
		lista_marcas = this.ingreso.obtenerMarcas();
		for (int i = 0; i < lista_marcas.size(); i++)
		{
			this.ventana_ingreso.getComboMarcas().addItem(lista_marcas.get(i).getDetalle());
		}
	}
	
	private void llenarComboTiposProductos()
	{
		lista_tiposproductos = this.ingreso.obtenerTiposProductos();
		for (int i = 0; i < lista_tiposproductos.size(); i++)
		{
			this.ventana_ingreso.getComboTiposProductos().addItem(lista_tiposproductos.get(i).getDetalle());
		}
	}

}
