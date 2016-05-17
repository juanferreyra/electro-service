package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dto.UsuarioDTO;
import modelo.Ingreso;
import presentacion.vista.VentanaReparacion;

public class ControladorReparacion implements ActionListener {

	private VentanaReparacion ventanaReparacion;
	private Ingreso ingreso;

	public ControladorReparacion(VentanaReparacion ventanaReparacion, Ingreso ingreso, UsuarioDTO usuarioLogueado) {
		this.ventanaReparacion = ventanaReparacion;
		this.ingreso = ingreso;
	}

	public void inicializar() {
		setearDatosIngreso();
		// cargaComboBoxComponentes();
		// cargarComponentes();
		// setearEstadoReparable();
		this.ventanaReparacion.setVisible(true);
	}

	private void setearDatosIngreso() {
		this.ventanaReparacion.setTipoTexto_lbl(ingreso.getTipoproducto().getDetalle());
		this.ventanaReparacion.setMarcaTexto_lbl(ingreso.getMarca().getDetalle());
		this.ventanaReparacion.setNombreProductoTexto_lbl(ingreso.ingr.getDescripcion());
		this.ventanaReparacion.getDescripcionFalla_txtArea().setText(ingreso.getIngreso().getDescripcion_falla());
	}

	// private void cargarComboComponentes() {
	// for (RepuestoDTO c : presupuesto.obtenerRepuestos()) {
	// this.ventanaPresupuesto.getComponente_ComboBox().addItem(c.getDetalle());
	// }
	// }

	@Override
	public void actionPerformed(ActionEvent e) {
		// if (e.getSource() == this.ventanaReparacion) {
		// }
	}

}
