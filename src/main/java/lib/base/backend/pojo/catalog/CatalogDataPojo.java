package lib.base.backend.pojo.catalog;

import java.util.ArrayList;
import java.util.List;

public class CatalogDataPojo {

	private List<Object> catalogs = new ArrayList<>();

	@SuppressWarnings("rawtypes")
	public List getCatalogs() {
		return catalogs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setCatalogs(List catalogs) {
		this.catalogs = catalogs;
	}
	
}
