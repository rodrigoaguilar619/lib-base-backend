package lib.base.backend.entity.generic;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class GenericCatalogStringEntity {
	
	@Id
	protected String id;
	
	protected String description;
	
	public GenericCatalogStringEntity() {
	}
	
	public GenericCatalogStringEntity(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
