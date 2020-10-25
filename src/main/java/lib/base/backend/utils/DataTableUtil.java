package lib.base.backend.utils;

import lib.base.backend.pojo.datatable.DataTablePojo;

public class DataTableUtil {

	public int getStartLimit(DataTablePojo dataTablePojo) {
		
		int currentPage = dataTablePojo.getCurrentPage() == 0 ? 1 : dataTablePojo.getCurrentPage();
		
		return (currentPage * dataTablePojo.getRowsPerPage()) - dataTablePojo.getRowsPerPage();
	}
}
