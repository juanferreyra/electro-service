package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dto.PerfilDTO;
import dto.UsuarioDTO;
import modelo.Perfil;
import modelo.Usuario;
import presentacion.vista.VentanaABMUsuario;

public class ControladorVentanaABMUsuario implements ActionListener {
	
	private Usuario usuario;
	private VentanaABMUsuario ventanaABMUsuario;
	private List<UsuarioDTO> clientes_en_tabla;
	private List<JTextField> txts;
	private List<PerfilDTO> perfiles_en_combo;
	private Perfil perfil;
	
	
	public ControladorVentanaABMUsuario( VentanaABMUsuario ventanaABMUsuario) {
		
		this.ventanaABMUsuario = ventanaABMUsuario;
		this.ventanaABMUsuario.getLimpiar_btn().addActionListener(this);
		this.ventanaABMUsuario.getEliminarItem_btn().addActionListener(this);
		this.ventanaABMUsuario.getGuardar_btn().addActionListener(this);
	}
	
	public void inicializar(){
		
		this.usuario = new Usuario();
		this.perfil = new Perfil();
		this.ventanaABMUsuario.setVisible(true);
		
		this.txts = new ArrayList<JTextField>();
		
		txts.add(this.ventanaABMUsuario.getNick_txt());//0
		txts.add(this.ventanaABMUsuario.getNombre_txt());//1
		txts.add(this.ventanaABMUsuario.getApellido_txt());//2
		txts.add(this.ventanaABMUsuario.getPass_txt());//3
		
		cargarTablaUsuarios();
		cargarCombo();
		mouseClickedOnTable();
		
		this.ventanaABMUsuario.getPerfil_comboBox().setSelectedIndex(-1);
		
	}
	
	

	private void cargarCombo() {
		
		this.perfiles_en_combo = this.usuario.obtenerPerfiles();
		for (int i = 0; i < this.perfiles_en_combo.size(); i++)
		{
				this.ventanaABMUsuario.getPerfil_comboBox().addItem(this.perfiles_en_combo.get(i).getPerfil());
		}
		
		
	}

	private void mouseClickedOnTable() {
		
		this.ventanaABMUsuario.getTablaUsuario().addMouseListener(new MouseListener() {

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

				}
			}

		});
		
	}
	
	private void cargartxts() {
		
		int filaSeleccionada = this.ventanaABMUsuario.getTablaUsuario().getSelectedRow();
		
		this.txts.get(0).setText((String) this.ventanaABMUsuario.getModelUsuario().getValueAt(filaSeleccionada, 1));
		this.txts.get(1).setText(String.valueOf(this.ventanaABMUsuario.getModelUsuario().getValueAt(filaSeleccionada, 2)));
		this.txts.get(2).setText(String.valueOf(this.ventanaABMUsuario.getModelUsuario().getValueAt(filaSeleccionada, 3)));
		this.txts.get(3).setText(String.valueOf(this.ventanaABMUsuario.getModelUsuario().getValueAt(filaSeleccionada, 4)));
		
		// carga de combo
		PerfilDTO perfilSeleccionado = new PerfilDTO(obtenerPerfil((int)this.ventanaABMUsuario.getModelUsuario().getValueAt(filaSeleccionada, 5))); 
		
		this.ventanaABMUsuario.getPerfil_comboBox().setSelectedItem(perfilSeleccionado.getPerfil());
		
		

	}

	private void cargarTablaUsuarios() {
		
		this.ventanaABMUsuario.getModelUsuario().setRowCount(0);
		this.ventanaABMUsuario.getModelUsuario().setColumnCount(0);
		this.ventanaABMUsuario.getModelUsuario().setColumnIdentifiers(this.ventanaABMUsuario.getNombreColumnas());

		this.clientes_en_tabla = usuario.obtenerUsuarios();

		for (int i = 0; i < this.clientes_en_tabla.size(); i++) {

			Object[] fila = { this.clientes_en_tabla.get(i).getId(),
					this.clientes_en_tabla.get(i).getNick(),
					this.clientes_en_tabla.get(i).getNombre(),
					this.clientes_en_tabla.get(i).getApellido(),
					buscarPefil(this.clientes_en_tabla.get(i).getIdperfil()),
					this.clientes_en_tabla.get(i).getIdperfil()} ;

			this.ventanaABMUsuario.getModelUsuario().addRow(fila);
			
			((DefaultTableModel) this.ventanaABMUsuario.getTablaUsuario().getModel()).isCellEditable(i, 0);
			((DefaultTableModel) this.ventanaABMUsuario.getTablaUsuario().getModel()).isCellEditable(i, 1);
			((DefaultTableModel) this.ventanaABMUsuario.getTablaUsuario().getModel()).isCellEditable(i, 2);
			((DefaultTableModel) this.ventanaABMUsuario.getTablaUsuario().getModel()).isCellEditable(i, 3);
			((DefaultTableModel) this.ventanaABMUsuario.getTablaUsuario().getModel()).isCellEditable(i, 4);
			((DefaultTableModel) this.ventanaABMUsuario.getTablaUsuario().getModel()).isCellEditable(i, 5);
			
			
			
		}

		ocultarColumnaId();
		ocultarColumnaIdPerfil();
		
	}
	
	private PerfilDTO obtenerPerfil(int index) {

		PerfilDTO perfil = usuario.buscarPerfil(index);

		return perfil;
	}


	private String buscarPefil(int index) {
		
		PerfilDTO perfil = usuario.buscarPerfil(index);
		
		return perfil.getPerfil();
	}

	private void ocultarColumnaId() {
		
		this.ventanaABMUsuario.getTablaUsuario().getColumnModel().getColumn(0).setMaxWidth(0);
		this.ventanaABMUsuario.getTablaUsuario().getColumnModel().getColumn(0).setMinWidth(0);
		this.ventanaABMUsuario.getTablaUsuario().getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		this.ventanaABMUsuario.getTablaUsuario().getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		
	}
	
	private void ocultarColumnaIdPerfil() {
		
		this.ventanaABMUsuario.getTablaUsuario().getColumnModel().getColumn(5).setMaxWidth(0);
		this.ventanaABMUsuario.getTablaUsuario().getColumnModel().getColumn(5).setMinWidth(0);
		this.ventanaABMUsuario.getTablaUsuario().getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
		this.ventanaABMUsuario.getTablaUsuario().getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.ventanaABMUsuario.getLimpiar_btn()) { // boton limpiar

			this.limpiartxts();
			this.ventanaABMUsuario.getTablaUsuario().clearSelection();
			
		} else if (e.getSource() == this.ventanaABMUsuario.getEliminarItem_btn()) { // boton eliminar

			// si la tabla no esta vacia
			if (this.ventanaABMUsuario.getTablaUsuario().getRowCount() != 0) {

				// si se selecciona una fila
				if (this.ventanaABMUsuario.getTablaUsuario().getSelectedRow() != -1) {

					int filaSeleccionada = this.ventanaABMUsuario.getTablaUsuario().getSelectedRow();
					int id_cliente_a_eliminar = (int) this.ventanaABMUsuario.getModelUsuario()
							.getValueAt(filaSeleccionada, 0);

					this.usuario.borrarUsuario(id_cliente_a_eliminar);

					cargarTablaUsuarios();;
					limpiartxts();

				} else {

					JOptionPane.showMessageDialog(this.ventanaABMUsuario, "Debe seleccionar uno  a eliminar");
				}
			} else {

				JOptionPane.showMessageDialog(this.ventanaABMUsuario, "No hay Usuarios a eliminar");
			}
				
		} else if (e.getSource() == this.ventanaABMUsuario.getGuardar_btn()) { // boton guardar

			// si esta seleccionado de la tabla
			// modificar cliente
			if (this.ventanaABMUsuario.getTablaUsuario().getSelectedRow() != -1) {

				int filaSeleccionada = this.ventanaABMUsuario.getTablaUsuario().getSelectedRow();

				if (!isTxtsVacios()) {


					usuario.modificarUsuario(obteneUsuario(
							(int) this.ventanaABMUsuario.getModelUsuario().getValueAt(filaSeleccionada, 0)));

					limpiartxts();
					cargarTablaUsuarios();

				} else {

					JOptionPane.showMessageDialog(this.ventanaABMUsuario,
							"No se permiten campos vacios. Vuelva a intentarlo.");
				}

			} else {
				// nuevo cliente


				if (!isTxtsVacios()) {

					usuario.agregarUsuario((obteneUsuario(0)));

					limpiartxts();
					cargarTablaUsuarios();


				} else {

					JOptionPane.showMessageDialog(this.ventanaABMUsuario,
							"No se permiten campos vacios. Vuelva a intentarlo.");
				}
			}
		}

	}

	
	private UsuarioDTO obteneUsuario(int id) {
		
		UsuarioDTO usuarioDTO = new UsuarioDTO(
				id,
				this.txts.get(0).getText(),// nick
				this.txts.get(1).getText(), // nombre
				this.txts.get(2).getText(), // apellido
				this.txts.get(3).getText(), // password
				obtenerIdPerfil());

		return usuarioDTO;
		
	}

	
	private int obtenerIdPerfil() {
		
		String perfilEnCombo = (String)this.ventanaABMUsuario.getPerfil_comboBox().getSelectedItem();
		
		PerfilDTO  ret = this.perfil.obtenerPerfilPorDetalle(perfilEnCombo);
		
		int idPerfil = ret.getId();
		
		return idPerfil;
		
	}

	private boolean isTxtsVacios() {
		
		boolean ret = false;

		for (JTextField jt : this.txts) {

			if (jt.getText().isEmpty()) {

				ret = true;
			}
		}
		return ret;
	}

	private void limpiartxts() {

		for (JTextField jt : txts) {
			jt.setText("");

		}
		
		this.ventanaABMUsuario.getPerfil_comboBox().setSelectedIndex(-1);

		
	}
	
	

	public static void main(String[] args) {
		VentanaABMUsuario abm = new VentanaABMUsuario();
		ControladorVentanaABMUsuario c = new ControladorVentanaABMUsuario(abm);
		c.inicializar();
		
	}


}
