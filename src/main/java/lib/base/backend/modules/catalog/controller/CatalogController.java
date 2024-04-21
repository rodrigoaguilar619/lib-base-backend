package lib.base.backend.modules.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lib.base.backend.exception.BaseException;
import lib.base.backend.modules.catalog.business.CatalogBusiness;
import lib.base.backend.modules.catalog.vo.UriCatalog;
import lib.base.backend.pojo.catalog.CatalogDataPojo;
import lib.base.backend.pojo.catalog.CatalogRequestPojo;
import lib.base.backend.pojo.rest.security.UserRequestPojo;
import lib.base.backend.utils.RestUtil;

@RestController
public class CatalogController {

	CatalogBusiness catalogBusiness;
	
	@SuppressWarnings("rawtypes")
	RestUtil restUtil;
	
	@Autowired
	public CatalogController(CatalogBusiness catalogBusiness, RestUtil<?> restUtil) {
		this.catalogBusiness = catalogBusiness;
		this.restUtil = restUtil;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(path = UriCatalog.API_GET_CATALOG, consumes = "application/json", produces = "application/json")
	public ResponseEntity catalogGetCatalog(@RequestBody CatalogRequestPojo requestPojo) throws BaseException {
		
		CatalogDataPojo reponsePojo = catalogBusiness.executeGetCatalog(requestPojo);
		return restUtil.buildResponseSuccess(reponsePojo, "Catalog getted");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(path = UriCatalog.API_GET_CATALOG_LIST, consumes = "application/json", produces = "application/json")
	public ResponseEntity catalogGetList(@RequestBody UserRequestPojo requestPojo) {
		
		CatalogDataPojo reponsePojo = catalogBusiness.executeGetCatalogList();
		return restUtil.buildResponseSuccess(reponsePojo, "Catalog List getted");
	}
}
