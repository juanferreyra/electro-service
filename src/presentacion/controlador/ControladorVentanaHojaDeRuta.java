package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dto.RepuestoDTO;
import dto.IngresoLogDTO;
import dto.ItemPresupuestoRepuestoDTO;
import dto.UsuarioDTO;
//import modelo.Email;
import modelo.HojaDeRuta;
import modelo.Ingreso;
import modelo.Presupuesto;
import persistencia.dao.IngresoLogDAO;
import presentacion.vista.VentanaHojaDeRuta;
import presentacion.vista.VentanaPresupuesto;

public class ControladorVentanaHojaDeRuta implements ActionListener {

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
	}

	public void inicializar() {

		// Calendar hoy = new GregorianCalendar();
		// ventanaHojaRuta.getFechaIngreso_lbl().setText("Fecha: " +
		// hoy.get(Calendar.DAY_OF_MONTH) +" / "
		// + hoy.get(Calendar.MONTH) +" / "+ hoy.get(Calendar.YEAR));
		ventanaHojaRuta.setVisible(true);
		// cargo el usuario
		if (hojaDeRuta.getId() == -1) {
			// cargo la hoja de ruta vacia para crear
		} else {
			cargarModelo();
		}
	}

	private void cargarModelo() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	private void validarCampos() {
		// validaria los campos ingresados
	}

	private boolean soloNumeros(String texto) {
		try {
			Integer.parseInt(texto);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean soloFloat(String texto) {
		try {
			Float.parseFloat(texto);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

}
