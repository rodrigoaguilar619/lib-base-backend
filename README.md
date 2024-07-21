# lib-base-backend
This project was generated with Java JDK 21 and Spring Boot 3

## Project Structure
	- lib.base.backend.modules.web	-> Implementation of web configuration, enabled with the `@WebConfiguration` annotation
		- RestExceptionHandler	-> Global management of exceptions. Error HTTP 500 for internal erros and 422 for logic business
		- SpringFoxConfig 		-> Configuration for Swagger REST controller. URL: `<project_url>/swagger-ui/index.html`
		- PropertiesConfig		-> Definition of properties files: `application_default.properties` (in lib-base-backend) and `application.properties` (main project)
	
	- lib.base.backend.modules.security.jwt -> mplementation of JWT security, enabled with the `@JwtConfiguration` annotation
	- lib.base.backend.modules.catalog	-> Implementation of catalog definition, enabled with the `@CatalogConfiguration` annotation
	- lib.base.backend.web.config.beans
		- DataBaseBeans		-> Configuration of database entity managers for scope and transactions
		- UtilBeans			-> Configuration of base utilities

## How to implement modules in your project

### `Implement Catalog Module.`
	- Create a class to implement the `CatalogDefinition` interface.
	- Define Map of catalog lists
	- Override the `getCatalogsDefinition` method and return the defined Map of catalog lists.
	
Example: 

```java
public class CatalogStockTrackDefinition implements CatalogDefinition {

	@SuppressWarnings("rawtypes")
	Map<String, Class> catalogs = new LinkedHashMap<>();
	
	public CatalogStockTrackDefinition() {
		
		catalogs.put("sector", CatalogSectorEntity.class);
		catalogs.put("broker", CatalogBrokerEntity.class);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Class> getCatalogsDefinition() {
		return catalogs;
	}
}
```