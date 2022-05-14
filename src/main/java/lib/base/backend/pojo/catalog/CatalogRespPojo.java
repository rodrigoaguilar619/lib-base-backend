package lib.base.backend.pojo.catalog;

import java.util.ArrayList;
import java.util.List;

public class CatalogRespPojo {

	private List<CatalogDataPojo> catalogs = new ArrayList<>();

	public List<CatalogDataPojo> getCatalogs() {
		return catalogs;
	}

	public void setCatalogs(List<CatalogDataPojo> catalogs) {
		this.catalogs = catalogs;
	}
	
}
