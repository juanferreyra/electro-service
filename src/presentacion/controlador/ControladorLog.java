package presentacion.controlador;

import java.util.List;
import javax.swing.table.DefaultTableModel;

import dto.EstadoDTO;
import dto.IngresoLogDTO;
import modelo.Ingreso;
import persistencia.dao.EstadoDAO;
import persistencia.dao.IngresoLogDAO;
import presentacion.vista.VentanaLog;

public class ControladorLog {

	private VentanaLog ventanaLog;
	private List<IngresoLogDTO> registrosLog_en_tabla;
	private IngresoLogDAO ingresoLogDAO;
	private Ingreso ingreso;
	private EstadoDAO estadoDAO;

	public ControladorLog(VentanaLog ventanaLog, Ingreso ingreso) {

		this.ventanaLog = ventanaLog;
		this.ingreso = ingreso;

	}

	public void inicializar() {

		this.ingresoLogDAO = new IngresoLogDAO();
		this.estadoDAO = new EstadoDAO();

		cargarTablaLog();

	}

	private void cargarTablaLog() {

		this.ventanaLog.getModelLog().setRowCount(0);
		this.ventanaLog.getModelLog().setColumnCount(0);
		this.ventanaLog.getModelLog().setColumnIdentifiers(this.ventanaLog.getNombreColumnas());

		this.registrosLog_en_tabla = ingresoLogDAO.readAllExtenso(this.ingreso.getId());

		for (int i = 0; i < this.registrosLog_en_tabla.size(); i++) {
			if (this.registrosLog_en_tabla.get(i).getIdingreso() == this.ingreso.getId()) {
				EstadoDTO estado = estadoDAO.find(this.registrosLog_en_tabla.get(i).getIdestado());
				Object[] fila = { estado.getDetalle(), this.registrosLog_en_tabla.get(i).getFechaLarga() };

				this.ventanaLog.getModelLog().addRow(fila);

				((DefaultTableModel) this.ventanaLog.getTablaLog().getModel()).isCellEditable(i, 0);
				((DefaultTableModel) this.ventanaLog.getTablaLog().getModel()).isCellEditable(i, 1);
				((DefaultTableModel) this.ventanaLog.getTablaLog().getModel()).isCellEditable(i, 2);
				((DefaultTableModel) this.ventanaLog.getTablaLog().getModel()).isCellEditable(i, 3);
				((DefaultTableModel) this.ventanaLog.getTablaLog().getModel()).isCellEditable(i, 4);
				((DefaultTableModel) this.ventanaLog.getTablaLog().getModel()).isCellEditable(i, 5);

			}
		}

	}
	//
	// public static void main(String[] args) {
	// Ingreso i = new Ingreso();
	// i.setId(22);
	// VentanaLog abm = new VentanaLog();
	// ControladorLog c = new ControladorLog(abm, i);
	// c.inicializar();
	//
	// }

}
