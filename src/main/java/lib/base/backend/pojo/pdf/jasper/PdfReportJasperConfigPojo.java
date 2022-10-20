package lib.base.backend.pojo.pdf.jasper;

import java.util.LinkedHashMap;
import java.util.Map;

public class PdfReportJasperConfigPojo {

	private Map<String, Object> parameters = new LinkedHashMap<>();
	
	private Map<String, Object> config = new LinkedHashMap<>();
	
	private String reportPath;
	
	private boolean isFullPath = true;

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public Map<String, Object> getConfig() {
		return config;
	}

	public void setConfig(Map<String, Object> config) {
		this.config = config;
	}

	public String getReportPath() {
		return reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}

	public boolean isFullPath() {
		return isFullPath;
	}

	public void setFullPath(boolean isFullPath) {
		this.isFullPath = isFullPath;
	}

}
