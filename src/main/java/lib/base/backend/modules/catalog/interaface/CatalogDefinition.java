package lib.base.backend.modules.catalog.interaface;

import java.util.Map;

public interface CatalogDefinition {
	
	@SuppressWarnings("rawtypes")
	public Map<String, Class> getCatalogsDefinition();
}
