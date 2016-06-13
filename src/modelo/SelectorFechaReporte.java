package modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SelectorFechaReporte {
	
	private Date inicio;
	private Date fin;
	
	public SelectorFechaReporte() {
		this.inicio = null;
		this.fin = null;
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
		//SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		//sdf.format(cal.getTime());
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
	
	public String fechasToString() {
		return "Fecha inicio:"+inicio.toString() +" Fecha Fin:"+fin.toString();	
	}
}
