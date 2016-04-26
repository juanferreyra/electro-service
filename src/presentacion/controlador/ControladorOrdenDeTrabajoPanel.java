package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import presentacion.vista.OrdenDeTrabajoPanel;

public class ControladorOrdenDeTrabajoPanel implements ActionListener {

	private OrdenDeTrabajoPanel ordenDeTrabajoPanel;

	public ControladorOrdenDeTrabajoPanel(OrdenDeTrabajoPanel ordenDeTrabajoPanel) {
		this.ordenDeTrabajoPanel = ordenDeTrabajoPanel;
		this.ordenDeTrabajoPanel.getGenerarDocumentos_Btn().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == (this.ordenDeTrabajoPanel.getGenerarDocumentos_Btn())) {
			if (validaCargaDeDatos()) {
				// Generar reportes ticket cliente y Comprobante tecnico
			}
		}
	}

	public void setIDOrdenDeTrabajo() {
		// Conseguir el ID de BD para una orden de trabajo.
		// Este es el nro de la orden de trabajo generada
		int id_OrdenDeTrabajo = 0;// hardcodeo
		this.ordenDeTrabajoPanel.setId_OrdenDeTrabajo_txf(id_OrdenDeTrabajo);
	}

	public void setFechaOrdenDeTrabajo() {
		// this.ordenDeTrabajoPanel.setFecha_OrdenDeTrabajo(fecha_OrdenDeTrabajo);
	}

	public boolean validaCargaDeDatos() {
		boolean valida = false;

		String nroCliente = this.ordenDeTrabajoPanel.getNroCliente_txf();
		String producto = this.ordenDeTrabajoPanel.getProducto_txf();
		String marca = (String) this.ordenDeTrabajoPanel.getMarca_jCBox().getSelectedItem();
		String tipoProducto = (String) this.ordenDeTrabajoPanel.getTipoProducto_jCBox().getSelectedItem();
		String descripcionFalla = this.ordenDeTrabajoPanel.getDescripcionFalla();

		if (!nroCliente.equals("") && !producto.equals("") && !marca.equals("") && !tipoProducto.equals("")
				&& !descripcionFalla.equals("")) {
			valida = true;
		} else {
			JOptionPane.showMessageDialog(null, "Todos los campos son requeridos, por favor, vuelva a intentarlo.");
		}

		return valida;
	}

	public OrdenDeTrabajoPanel getOrdenDeTrabajoPanel() {
		return ordenDeTrabajoPanel;
	}

	// Falta metodo para carga de MARCAS en combo box.
	// Falta metodo para carga de TIPO CONTACTO en combo box.

}
