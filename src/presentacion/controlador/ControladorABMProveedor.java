package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import dto.ClienteDTO;
import dto.MarcaDTO;
import dto.ProveedorDTO;
import modelo.Cliente;
import modelo.Proveedor;
import persistencia.dao.MarcaDAO;
import presentacion.vista.VentanaABMProveedor;


public class ControladorABMProveedor implements ActionListener{
	
	private VentanaABMProveedor ventanaABMProveedor;
	private Proveedor proveedor;
	private List<ProveedorDTO> proveedores_en_tabla;
	private List<JTextField> txts;
	private List<MarcaDTO>marcas_en_tabla;
	private MarcaDAO marca;
	
	public ControladorABMProveedor(VentanaABMProveedor ventanaABMProveedor) {
		
		this.ventanaABMProveedor = ventanaABMProveedor;
		this.ventanaABMProveedor.getEliminarItem_btn().addActionListener(this);
		this.ventanaABMProveedor.getLimpiar_btn().addActionListener(this);
		this.ventanaABMProveedor.getGuardar_btn().addActionListener(this);
		
	}
	
	public void inicializar() {

		this.proveedor = new Proveedor();
		this.marca = new MarcaDAO();

		this.ventanaABMProveedor.setVisible(true);

		this.txts = new ArrayList<JTextField>();

		txts.add(this.ventanaABMProveedor.getRazonSocial_txt());// 0
		txts.add(this.ventanaABMProveedor.getCuit_txt());// 1
		txts.add(this.ventanaABMProveedor.getDireccion_txt());// 2
		txts.add(this.ventanaABMProveedor.getEmail_txt());// 3
		txts.add(this.ventanaABMProveedor.getNombreContacto_txt());// 4
		txts.add(this.ventanaABMProveedor.getTelefonoContacto_txt());// 5
		txts.add(this.ventanaABMProveedor.getEmailContacto_txt());// 6
		txts.add(this.ventanaABMProveedor.getEmailPedidos_txt());// 7
		
		cargarTablaProveedores();	
		mouseClickedOnTable();

	}
	
	

	private void mouseClickedOnTable() {
		this.ventanaABMProveedor.getTablaProveedores().addMouseListener(new MouseListener() {

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
					cargarTablaMarcas();

				}

			}
		});
		
	}

	private void cargartxts() {

		int filaSeleccionada = this.ventanaABMProveedor.getTablaProveedores().getSelectedRow();

		this.txts.get(0).setText((String) this.ventanaABMProveedor.getModelProveedores().getValueAt(filaSeleccionada, 1));
		this.txts.get(1).setText(String.valueOf(this.ventanaABMProveedor.getModelProveedores().getValueAt(filaSeleccionada, 2)));
		this.txts.get(2).setText((String)this.ventanaABMProveedor.getModelProveedores().getValueAt(filaSeleccionada, 3));
		this.txts.get(3).setText((String) this.ventanaABMProveedor.getModelProveedores().getValueAt(filaSeleccionada, 4));
		this.txts.get(4).setText((String) this.ventanaABMProveedor.getModelProveedores().getValueAt(filaSeleccionada, 5));
		this.txts.get(5).setText((String.valueOf(this.ventanaABMProveedor.getModelProveedores().getValueAt(filaSeleccionada, 6))));
		this.txts.get(6).setText((String) this.ventanaABMProveedor.getModelProveedores().getValueAt(filaSeleccionada, 7));
		this.txts.get(7).setText((String) this.ventanaABMProveedor.getModelProveedores().getValueAt(filaSeleccionada, 8));
	}
	

	private void cargarTablaProveedores() {

		this.ventanaABMProveedor.getModelProveedores().setRowCount(0);
		this.ventanaABMProveedor.getModelProveedores().setColumnCount(0);
		this.ventanaABMProveedor.getModelProveedores().setColumnIdentifiers(this.ventanaABMProveedor.getNombreColumnas());

		this.proveedores_en_tabla = proveedor.obtenerProveedores();

		for (int i = 0; i < this.proveedores_en_tabla.size(); i++) {

			Object[] fila = { this.proveedores_en_tabla.get(i).getId(), this.proveedores_en_tabla.get(i).getRazonSocial(),
					this.proveedores_en_tabla.get(i).getCuit(), this.proveedores_en_tabla.get(i).getDireccion(),
					this.proveedores_en_tabla.get(i).getEmail(), this.proveedores_en_tabla.get(i).getNombreContacto(),
					this.proveedores_en_tabla.get(i).getTelefonoContacto(), this.proveedores_en_tabla.get(i).getEmailContacto(),
					this.proveedores_en_tabla.get(i).getEmailPedidos()};

			this.ventanaABMProveedor.getModelProveedores().addRow(fila);

		}
		ocultarColumnaId();
		
	}
	
	private void ocultarColumnaId() {
		
		this.ventanaABMProveedor.getTablaProveedores().getColumnModel().getColumn(0).setMaxWidth(0);
		this.ventanaABMProveedor.getTablaProveedores().getColumnModel().getColumn(0).setMinWidth(0);
		this.ventanaABMProveedor.getTablaProveedores().getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		this.ventanaABMProveedor.getTablaProveedores().getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		
	}
	
	private void cargarTablaMarcas() {
		
		int filaSeleccionada = this.ventanaABMProveedor.getTablaProveedores().getSelectedRow();
		
		this.ventanaABMProveedor.getModelMarcas().setRowCount(0);
		this.ventanaABMProveedor.getModelMarcas().setColumnCount(0);
		this.ventanaABMProveedor.getModelMarcas().setColumnIdentifiers(this.ventanaABMProveedor.getNombreColumnasMarcas());

		this.marcas_en_tabla = marca.buscarMarcasPorIdProvedor((int)this.ventanaABMProveedor.getModelProveedores().getValueAt(filaSeleccionada, 0));

		for (int i = 0; i < this.marcas_en_tabla.size(); i++) {

			Object[] fila = { this.marcas_en_tabla.get(i).getId(), this.marcas_en_tabla.get(i).getDetalle(),};

			this.ventanaABMProveedor.getModelMarcas().addRow(fila);

		}
		// oculta columna id marca
		this.ventanaABMProveedor.getTablaMarcas().getColumnModel().getColumn(0).setMaxWidth(0);
		this.ventanaABMProveedor.getTablaMarcas().getColumnModel().getColumn(0).setMinWidth(0);
		this.ventanaABMProveedor.getTablaMarcas().getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		this.ventanaABMProveedor.getTablaMarcas().getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		
		
		
		
	}


	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		VentanaABMProveedor abm = new VentanaABMProveedor();
		ControladorABMProveedor c =new ControladorABMProveedor(abm);
		c.inicializar();
	}

}
