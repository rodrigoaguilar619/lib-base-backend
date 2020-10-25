package lib.base.backend.pojo.datatable;

import java.util.LinkedHashMap;
import java.util.Map;

public class DataTablePojo {

	private Integer currentPage;
	
	private Integer rowsPerPage;
	
	private Map<String, String> filters = new LinkedHashMap<>();

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(Integer rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public Map<String, String> getFilters() {
		return filters;
	}

	public void setFilters(Map<String, String> filters) {
		this.filters = filters;
	}

}
