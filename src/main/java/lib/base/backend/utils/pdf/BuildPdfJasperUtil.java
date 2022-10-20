package lib.base.backend.utils.pdf;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lib.base.backend.pojo.pdf.jasper.PdfReportJasperConfigPojo;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class BuildPdfJasperUtil {

	public JasperPrint fillReport(String reportUrl, Map<String, Object> parameters, boolean reportUrlFullpath) throws JRException, FileNotFoundException {
		
		JRDataSource dataSource;
		
		JREmptyDataSource jrEmptyDataSource = new JREmptyDataSource();
		dataSource = jrEmptyDataSource;
		
		InputStream report = reportUrlFullpath ? new FileInputStream(reportUrl) : BuildPdfJasperUtil.class.getResourceAsStream(reportUrl);
		
		return JasperFillManager.fillReport(report, parameters, dataSource);
	}
	
	public byte[] reportPdf(List<PdfReportJasperConfigPojo> reports) throws JRException, FileNotFoundException {
		
		List<JasperPrint> combinedReports = new ArrayList<>();
			
		for (PdfReportJasperConfigPojo report: reports) {
			
			Map<String, Object> parametersReport = new LinkedHashMap<>();
			parametersReport.putAll(report.getParameters());
			parametersReport.putAll(report.getConfig());
			
			combinedReports.add(this.fillReport(report.getReportPath(), parametersReport, report.isFullPath()));
		}
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(SimpleExporterInput.getInstance(combinedReports));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		exporter.exportReport();
		
		return outputStream.toByteArray();
	}
}
