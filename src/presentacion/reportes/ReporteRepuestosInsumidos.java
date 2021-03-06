package presentacion.reportes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import dto.ReporteRepuestosInsumidosDTO;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReporteRepuestosInsumidos {
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint	reporteLleno;
	
	
	//Recibe la lista de personas para armar el reporte
    public ReporteRepuestosInsumidos(List<ReporteRepuestosInsumidosDTO> datos, String periodo, List<ReporteRepuestosInsumidosDTO> datosGrafico)
    {
    	
    	JRDataSource javaBeansKapitelDS = new JRBeanCollectionDataSource(datos);
    	
    	Map<String, Object> parametersMap = new HashMap<String, Object>();
    	parametersMap.put("FECHA", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
    	parametersMap.put("PERIODO", periodo);
    	parametersMap.put("SUB_DATASOURCE", datosGrafico);
        
        try {
        	this.reporte = (JasperReport) JRLoader.loadObjectFromFile("ReporteRepuestosInsumidos.jasper");
	    	
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
