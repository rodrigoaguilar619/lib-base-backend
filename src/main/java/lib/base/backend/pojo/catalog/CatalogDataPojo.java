package lib.base.backend.pojo.catalog;

public class CatalogDataPojo {

	public CatalogDataPojo(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	private Integer id;
	
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
