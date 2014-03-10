package com.mytutos.maven.parent.jasper1;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	InputStream inputStream = new FileInputStream ("test_jasper.jrxml");
    	
    	DataBeanMaker dataBeanMaker = new DataBeanMaker();
    	ArrayList<DataBean> dataBeanList = dataBeanMaker.getDataBeanList();
    	
    	JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
    	
    	Map parameters = new HashMap();
    	
    	JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
    	JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
    	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
    	
    	// 1- export to PDF
    	JasperExportManager.exportReportToPdfFile(jasperPrint, "c:/Downloads/ciroot/reports/test_jasper.pdf"); 
    	// 2- export to HTML
    	JasperExportManager.exportReportToHtmlFile(jasperPrint,"c:/Downloads/ciroot/reports/test_jasper.html" );
    	// 3- export to Excel sheet
    	JRXlsExporter exporter = new JRXlsExporter();
    	exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
    	exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "c:/Downloads/ciroot/reports/test_jasper.xls" );
    	exporter.exportReport();
    	
    	// 4- export to word file
    	JRDocxExporter exporter1 = new JRDocxExporter();
    	exporter1.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
       	exporter1.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "c:/Downloads/ciroot/reports/test_jasper.docx" );
    	exporter1.exportReport(); 
    	
    	
        System.out.println( "Hello World!" );
    }
}
