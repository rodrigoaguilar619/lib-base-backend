package lib.base.backend.pojo.datatable;

public class DataTablePojo<T> {

	private Integer currentPage;
	
	private Integer rowsPerPage;
	
	private T filters;

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

	public T getFilters() {
		return filters;
	}

	public void setFilters(T filters) {
		this.filters = filters;
	}

}
