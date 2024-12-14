package lib.base.backend.modules.catalog.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lib.base.backend.exception.BaseException;
import lib.base.backend.exception.data.BusinessException;
import lib.base.backend.modules.catalog.interaface.CatalogDefinition;
import lib.base.backend.persistance.GenericPersistence;
import lib.base.backend.pojo.catalog.CatalogDataPojo;
import lib.base.backend.pojo.catalog.CatalogPojo;
import lib.base.backend.pojo.catalog.CatalogRequestPojo;
import lib.base.backend.utils.CatalogUtil;

@Component
public class CatalogBusiness {

	protected GenericPersistence<?> genericCustomPersistance;
	
	CatalogDefinition catalogDefinition;
	
	CatalogUtil catalogUtil = new CatalogUtil();
	
	public CatalogBusiness(GenericPersistence<?> genericCustomPersistance, CatalogDefinition catalogDefinition) {
		this.genericCustomPersistance = genericCustomPersistance;
		this.catalogDefinition = catalogDefinition;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<CatalogPojo> getCatalog(String catalogName) throws BaseException {
		
		Map<String, Class> catalogs = catalogDefinition.getCatalogsDefinition();
		Class clazz = catalogs.get(catalogName);
		
		if (clazz == null)
			throw new BusinessException("Catalog " + catalogName + " not found");
		
		List<?> catalogList = genericCustomPersistance.findAll(clazz);
		
		return catalogUtil.getCatalog(clazz, catalogList);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public CatalogDataPojo executeGetCatalog(CatalogRequestPojo requestPojo) throws BaseException {
		
		List<CatalogPojo> catalogListPojo = getCatalog(requestPojo.getCatalogName());
		
		CatalogDataPojo responsePojo = new CatalogDataPojo();
		responsePojo.setCatalogs(catalogListPojo);
		
		return responsePojo;
	}
	
	public CatalogDataPojo executeGetCatalogList() {
		
		List<String> catalogListPojo = new ArrayList<>(catalogDefinition.getCatalogsDefinition().keySet());
		
		CatalogDataPojo responsePojo = new CatalogDataPojo();
		responsePojo.setCatalogs(catalogListPojo);
		
		return responsePojo;
	}
}
