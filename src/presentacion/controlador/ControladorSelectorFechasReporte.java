package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

import dto.ReporteFinancieroActivosDTO;
import dto.ReporteFinancieroPasivosDTO;
import dto.ReporteMarcaTipoDTO;
import dto.ReporteRepuestosInsumidosDTO;
import modelo.SelectorFechaReporte;
import presentacion.reportes.ReporteFinanciero;
import presentacion.reportes.ReporteMarcaTipo;
import presentacion.reportes.ReporteRepuestosInsumidos;
import presentacion.vista.VentanaSelectorFechasReporte;

public class ControladorSelectorFechasReporte  implements ActionListener {
	
	private VentanaSelectorFechasReporte ventanaSelectorReportes;
	private SelectorFechaReporte modelReporte;
	private int tipoReporte;
	
	public ControladorSelectorFechasReporte(int tipoReporteSeleccionado, VentanaSelectorFechasReporte ventanaSelector){
		this.ventanaSelectorReportes = ventanaSelector;
		this.ventanaSelectorReportes.getRdbtnAnual().addActionListener(this);
		this.ventanaSelectorReportes.getRdbtnMensual().addActionListener(this);
		this.ventanaSelectorReportes.getRdbtnSemanal().addActionListener(this);
		this.ventanaSelectorReportes.getRdbtnEntreFechas().addActionListener(this);
		this.ventanaSelectorReportes.getRdbtnPorMarca().addActionListener(this);
		this.ventanaSelectorReportes.getRdbtnTipoproducto().addActionListener(this);
		this.ventanaSelectorReportes.getRdbtnPorMarcaY().addActionListener(this);
		this.ventanaSelectorReportes.getBtnGenerarReporte().addActionListener(this);
		
		this.modelReporte = new SelectorFechaReporte();
		/* @Tipos de reporte
		 * #1 = De Ventas por fecha
		 * #2 = De Reparaciones
		 * #3 = De repuestos mas insumidos
		 */
		this.tipoReporte = tipoReporteSeleccionado;
	}
	
	public void inicializar() {
		
		this.ventanaSelectorReportes.getAnio_Anual().setEnabled(true);
		this.ventanaSelectorReportes.getAnio_mensual().setEnabled(false);
		this.ventanaSelectorReportes.getMes_mensual().setEnabled(false);
		this.ventanaSelectorReportes.getFecha_semana().setEnabled(false);
		this.ventanaSelectorReportes.getDesde_entrefechas().setEnabled(false);
		this.ventanaSelectorReportes.getHasta_entrefechas().setEnabled(false);
		
		if(this.tipoReporte!=2){
			this.ventanaSelectorReportes.getRdbtnPorMarca().setVisible(false);
			this.ventanaSelectorReportes.getRdbtnTipoproducto().setVisible(false);
			this.ventanaSelectorReportes.getRdbtnPorMarcaY().setVisible(false);
		}
		
		this.ventanaSelectorReportes.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == this.ventanaSelectorReportes.getRdbtnAnual()) {
			
			this.ventanaSelectorReportes.getAnio_Anual().setEnabled(true);
			this.ventanaSelectorReportes.getAnio_mensual().setEnabled(false);
			this.ventanaSelectorReportes.getMes_mensual().setEnabled(false);
			this.ventanaSelectorReportes.getFecha_semana().setEnabled(false);
			this.ventanaSelectorReportes.getDesde_entrefechas().setEnabled(false);
			this.ventanaSelectorReportes.getHasta_entrefechas().setEnabled(false);
			
		} else if(e.getSource() == this.ventanaSelectorReportes.getRdbtnEntreFechas()) {

			this.ventanaSelectorReportes.getAnio_Anual().setEnabled(false);
			this.ventanaSelectorReportes.getAnio_mensual().setEnabled(false);
			this.ventanaSelectorReportes.getMes_mensual().setEnabled(false);
			this.ventanaSelectorReportes.getFecha_semana().setEnabled(false);
			this.ventanaSelectorReportes.getDesde_entrefechas().setEnabled(true);
			this.ventanaSelectorReportes.getHasta_entrefechas().setEnabled(true);
		
		} else if(e.getSource() == this.ventanaSelectorReportes.getRdbtnMensual()) {
			
			this.ventanaSelectorReportes.getAnio_Anual().setEnabled(false);
			this.ventanaSelectorReportes.getAnio_mensual().setEnabled(true);
			this.ventanaSelectorReportes.getMes_mensual().setEnabled(true);
			this.ventanaSelectorReportes.getFecha_semana().setEnabled(false);
			this.ventanaSelectorReportes.getDesde_entrefechas().setEnabled(false);
			this.ventanaSelectorReportes.getHasta_entrefechas().setEnabled(false);
			
		} else if(e.getSource() == this.ventanaSelectorReportes.getRdbtnSemanal()){
			
			this.ventanaSelectorReportes.getAnio_Anual().setEnabled(false);
			this.ventanaSelectorReportes.getAnio_mensual().setEnabled(false);
			this.ventanaSelectorReportes.getMes_mensual().setEnabled(false);
			this.ventanaSelectorReportes.getFecha_semana().setEnabled(true);
			this.ventanaSelectorReportes.getDesde_entrefechas().setEnabled(false);
			this.ventanaSelectorReportes.getHasta_entrefechas().setEnabled(false);

		} else if(e.getSource() == this.ventanaSelectorReportes.getBtnGenerarReporte()) {
			
			boolean error = false;
			
			if(this.ventanaSelectorReportes.getRdbtnAnual().isSelected()) {
				//valido
				if(this.ventanaSelectorReportes.getAnio_Anual().getValue()<1900){
					error = true;
					JOptionPane.showMessageDialog(ventanaSelectorReportes, "El año espeficificado no es valido.", "Atencion!",
							JOptionPane.INFORMATION_MESSAGE);
					
				} else {
					GregorianCalendar fecha = new GregorianCalendar();
					fecha.set(this.ventanaSelectorReportes.getAnio_Anual().getValue(), 1, 1);
					this.modelReporte.setearFechasAnio(fecha);
				}
			} else if(this.ventanaSelectorReportes.getRdbtnMensual().isSelected()) {
				if(this.ventanaSelectorReportes.getAnio_mensual().getValue()<1900){
					error = true;
					JOptionPane.showMessageDialog(ventanaSelectorReportes, "El año espeficificado no es valido.", "Atencion!",
							JOptionPane.INFORMATION_MESSAGE);
					
				} else {
					GregorianCalendar fecha = new GregorianCalendar();
					fecha.set(this.ventanaSelectorReportes.getAnio_mensual().getValue(),
							this.ventanaSelectorReportes.getMes_mensual().getMonth(), 1);
					this.modelReporte.setearFechasMes(fecha);
				}
			} else if(this.ventanaSelectorReportes.getRdbtnSemanal().isSelected()) {
				if(this.ventanaSelectorReportes.getFecha_semana().getDate()==null){
					error = true;
					JOptionPane.showMessageDialog(ventanaSelectorReportes, "Debe seleccionar una fecha de la semana que desea!.", "Atencion!",
							JOptionPane.INFORMATION_MESSAGE);
					
				} else {
					GregorianCalendar fecha = new GregorianCalendar();
					fecha.setTime(this.ventanaSelectorReportes.getFecha_semana().getDate());
					
					this.modelReporte.setearFechasSemana(fecha);
				}
			} else if(this.ventanaSelectorReportes.getRdbtnEntreFechas().isSelected()) {
				if(this.ventanaSelectorReportes.getDesde_entrefechas().getDate()==null || this.ventanaSelectorReportes.getHasta_entrefechas()==null){
					error = true;
					JOptionPane.showMessageDialog(ventanaSelectorReportes, "Debe seleccionar ambas fechas para continuar!.", "Atencion!",
							JOptionPane.INFORMATION_MESSAGE);
					
				} else {
					this.modelReporte.setInicio(this.ventanaSelectorReportes.getDesde_entrefechas().getDate());
					this.modelReporte.setFin(this.ventanaSelectorReportes.getHasta_entrefechas().getDate());
				}
			}
			
			if(!error) {
				
				/* Recuerdo
				 * @Tipos de reporte
				 * #1 = De Ventas por fecha
				 * #2 = De Reparaciones
				 * #3 = De repuestos mas insumidos
				 */
				
				if(this.tipoReporte == 1) {
					
					ArrayList<ReporteFinancieroPasivosDTO> pasivos = this.modelReporte.getFinancieroPasivos();
					float totalPasivos = 0;
					for (int i = 0; i < pasivos.size(); i++) {
						totalPasivos+=pasivos.get(i).getValor_orden();
					}
					
					ArrayList<ReporteFinancieroActivosDTO> activos = this.modelReporte.getFinancieroActivos();
					float totalActivos = 0;
					for (int a = 0; a < activos.size(); a++) {
						totalActivos+=activos.get(a).getValor_total();
					}
					ReporteFinanciero reporte = new ReporteFinanciero(pasivos, activos, totalPasivos, totalActivos, this.modelReporte.fechasToString());
					
					reporte.mostrar();
					
				} else if(this.tipoReporte == 2){
					
					if(this.ventanaSelectorReportes.getRdbtnTipoproducto().isSelected()) {
						//por tipo de producto
						this.modelReporte.setTipoDeFiltro("PorTipoProducto");
						
					} else if(this.ventanaSelectorReportes.getRdbtnPorMarca().isSelected()) {
						//por marca de producto
						this.modelReporte.setTipoDeFiltro("PorMarca");
						
					} else if(this.ventanaSelectorReportes.getRdbtnPorMarcaY().isSelected()) {
						//por marca y tipo
						this.modelReporte.setTipoDeFiltro("PorMarcaTipoProducto");
					} else {
						JOptionPane.showMessageDialog(ventanaSelectorReportes, "Debe seleccionar un criterio de filtro para el informe.", "Atencion!",
								JOptionPane.INFORMATION_MESSAGE);
					}
					
					ArrayList<ReporteMarcaTipoDTO> a = this.modelReporte.getTodosReporte2_10();
					ArrayList<ReporteMarcaTipoDTO> b = this.modelReporte.getTodosReporte2_20();
					ReporteMarcaTipo reporte = new ReporteMarcaTipo(a,this.modelReporte.fechasToString(),b);
					reporte.mostrar();
					
				} else if(this.tipoReporte == 3){
					
					//TODO: repuestos mas utilizados
					ArrayList<ReporteRepuestosInsumidosDTO> utilizadosAl10 = this.modelReporte.getMasInsumidos10();
					ArrayList<ReporteRepuestosInsumidosDTO> utilizadosAl20 = this.modelReporte.getMasInsumidos20();
					
					ReporteRepuestosInsumidos reporte = new ReporteRepuestosInsumidos(utilizadosAl10, this.modelReporte.fechasToString(),utilizadosAl20);
					reporte.mostrar();
				}
				
				this.ventanaSelectorReportes.dispose();
			}
		}
	}
}
