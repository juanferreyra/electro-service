package presentacion.reportes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import dto.ReporteFinancieroActivosDTO;
import dto.ReporteFinancieroPasivosDTO;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReporteFinanciero
{
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint	reporteLleno;
	
	
	//Recibe la lista de personas para armar el reporte
    public ReporteFinanciero(List<ReporteFinancieroPasivosDTO> pasivos, List<ReporteFinancieroActivosDTO> activos, float totalPasivos, float totalActivos, String periodo)
    {
    	
    	JRDataSource javaBeansKapitelDS = new JRBeanCollectionDataSource(pasivos);
    	
    	Map<String, Object> parametersMap = new HashMap<String, Object>();
    	parametersMap.put("FECHA", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
    	parametersMap.put("PERIODO", periodo);
    	parametersMap.put("SUB_DATASOURCE", activos);
    	parametersMap.put("SUB_DATASOURCE2", pasivos);
    	parametersMap.put("VALOR_PASIVOS", totalPasivos);
    	parametersMap.put("VALOR_ACTIVOS", totalActivos);
        
        try {
        	this.reporte = (JasperReport) JRLoader.loadObjectFromFile("ReporteFinanciero.jasper");
	    	
			this.reporteLleno = JasperFillManager.fillReport(reporte, parametersMap, javaBeansKapitelDS);
		} catch (JRException e) {
			e.printStackTrace();
		}
    }       
    
    public void mostrar()
	{
		this.reporteViewer = new JasperViewer(this.reporteLleno,false);
		this.reporteViewer.setVisible(true);
	}
   
}	