package lib.base.backend.pojo.catalog;

import lib.base.backend.pojo.rest.security.UserRequestPojo;

public class CatalogRequestPojo extends UserRequestPojo {
	
	private String catalogName;

	public CatalogRequestPojo() {
		super();
	}
	
	public CatalogRequestPojo(String catalogName) {
		super();
		this.catalogName = catalogName;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
}
