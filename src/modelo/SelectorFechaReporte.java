package modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import dto.ReporteFinancieroActivosDTO;
import dto.ReporteFinancieroPasivosDTO;
import dto.ReporteMarcaTipoDTO;
import persistencia.dao.ReporteFinancieroDAO;
import persistencia.dao.ReporteMarcaTipoDAO;

public class SelectorFechaReporte {
	
	private static ReporteFinancieroDAO financieroDAO;
	private static ReporteMarcaTipoDAO marcaTipoDAO;
	private Date inicio;
	private Date fin;
	private String tipoDeFiltro;

	public SelectorFechaReporte() {
		this.inicio = null;
		this.fin = null;
	}
	
	public ArrayList<ReporteFinancieroPasivosDTO> getFinancieroPasivos() {
		financieroDAO = new ReporteFinancieroDAO();
		return financieroDAO.findPasivos(this.inicio, this.fin);
	}
	
	public ArrayList<ReporteFinancieroActivosDTO> getFinancieroActivos() {
		financieroDAO = new ReporteFinancieroDAO();
		return financieroDAO.findActivos(this.inicio, this.fin);
	}
	
	public ArrayList<ReporteMarcaTipoDTO> getTodosReporte2() {
		if(tipoDeFiltro.equals("PorTipoProducto")) {
			return this.getPorTipoProducto();
		} else if(tipoDeFiltro.equals("PorMarca")) {
			return this.getPorMarca();
		} else {
			return this.getPorMarcaTipoProducto();
		}
	}
	
	public ArrayList<ReporteMarcaTipoDTO> getPorTipoProducto() {
		marcaTipoDAO = new ReporteMarcaTipoDAO();
		return marcaTipoDAO.porTipo(this.inicio, this.fin);
	}
	
	public ArrayList<ReporteMarcaTipoDTO> getPorMarca() {
		marcaTipoDAO = new ReporteMarcaTipoDAO();
		return marcaTipoDAO.porMarca(this.inicio, this.fin);
	}
	
	public ArrayList<ReporteMarcaTipoDTO> getPorMarcaTipoProducto() {
		marcaTipoDAO = new ReporteMarcaTipoDAO();
		return marcaTipoDAO.porMarcaTipo(this.inicio, this.fin);
	}
	
	public void setearFechasAnio(GregorianCalendar fecha) {
		inicio = this.getPrimerDiaAnio(fecha);
		fin = this.getUltimoDiaAnio(fecha);
	}
	
	public void setearFechasMes(GregorianCalendar fecha) {
		inicio = this.getPrimerDiaMes(fecha);
		fin = this.getUltimoDiaMes(fecha);
	}
	
	public void setearFechasSemana(GregorianCalendar fecha) {
		inicio = this.getPrimerDiaSemana(fecha);
		fin = this.getUltimoDiaSemana(fecha);
	}
	
	private Date getPrimerDiaSemana(GregorianCalendar fecha) {
		// get today and clear time of day
		Calendar cal = fecha;
		// get start of the week
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		return cal.getTime();
	}

	private Date getUltimoDiaSemana(GregorianCalendar fecha) {
		Calendar cal = fecha;
		// get end of the week
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return	cal.getTime();
	}

	private Date getPrimerDiaMes(GregorianCalendar fecha) {
		Calendar cal = fecha;
		// get start of the month
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	private Date getUltimoDiaMes(GregorianCalendar fecha) {
		Calendar cal = fecha;
		// get end of the month
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	private Date getPrimerDiaAnio(GregorianCalendar fecha) {
		Calendar cal = fecha;
		// get start of the year
		cal.set(Calendar.DAY_OF_YEAR, 1);
		return cal.getTime();
	}

	private Date getUltimoDiaAnio(GregorianCalendar fecha) {
		Calendar cal = fecha;
		// get end of the year
		cal.add(Calendar.YEAR, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}
	
	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public Date getFin() {
		return fin;
	}
	
	public String getTipoDeFiltro() {
		return tipoDeFiltro;
	}

	public void setTipoDeFiltro(String tipoDeFiltro) {
		this.tipoDeFiltro = tipoDeFiltro;
	}
	
	public String fechasToString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return "del "+sdf.format(inicio.getTime()) +" al "+sdf.format(fin.getTime());
	}
}
