package lib.base.backend.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import lib.base.backend.exception.BaseException;
import lib.base.backend.pojo.catalog.CatalogDataPojo;

public class CatalogUtil {
	
	public static String upperCaseFirst(String val) {
      char[] arr = val.toCharArray();
      arr[0] = Character.toUpperCase(arr[0]);
      return new String(arr);
   }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<CatalogDataPojo> getCatalog(Class clazz, List<?> catalogList) throws BaseException {
		
		List<CatalogDataPojo> catalogDataPojos = new ArrayList<CatalogDataPojo>();
		
		try {
			
			for (Object catalog: catalogList) {
				
				Method methodId = clazz.getDeclaredMethod("getId");
				Method methodDescription = clazz.getDeclaredMethod("getDescription");
				
				Integer fieldValueId = (Integer) methodId.invoke(catalog);
				String fieldValueDescription = (String) methodDescription.invoke(catalog);
				
				CatalogDataPojo catalogDataPojo = new CatalogDataPojo(fieldValueId, fieldValueDescription);
				catalogDataPojos.add(catalogDataPojo);
			}
		}
		catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
			throw new BaseException("Error building catalog", ex);
		}
		
		return catalogDataPojos;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<CatalogDataPojo> getCatalog(Class clazz, List<?> catalogList, String fieldId, String fieldDescription) throws BaseException {
		
		List<CatalogDataPojo> catalogDataPojos = new ArrayList<CatalogDataPojo>();
		
		try {
			
			for (Object catalog: catalogList) {
				
				Method methodId = clazz.getDeclaredMethod("get" + upperCaseFirst(fieldId));
				Method methodDescription = clazz.getDeclaredMethod("get" + upperCaseFirst(fieldDescription));
				
				Integer fieldValueId = (Integer) methodId.invoke(catalog);
				String fieldValueDescription = (String) methodDescription.invoke(catalog);
				
				CatalogDataPojo catalogDataPojo = new CatalogDataPojo(fieldValueId, fieldValueDescription);
				catalogDataPojos.add(catalogDataPojo);
			}
		}
		catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
			throw new BaseException("Error building catalog", ex);
		}
		
		return catalogDataPojos;
	}
}
