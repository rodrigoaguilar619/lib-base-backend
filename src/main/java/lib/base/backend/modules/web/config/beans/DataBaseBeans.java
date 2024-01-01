package lib.base.backend.modules.web.config.beans;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import lib.base.backend.persistance.GenericPersistence;
import lib.base.backend.persistance.transaction.GenericJpaPeristanceImpl;

@Configuration
public class DataBaseBeans {
	
	@Autowired
	EntityManager entityManager;
	
	@SuppressWarnings("rawtypes")
	@Bean
	@Primary
	@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public GenericPersistence generateGenericPersistance() {
		return new GenericJpaPeristanceImpl(entityManager);
	}
	
	@SuppressWarnings("rawtypes")
	@Bean("customPersistanceApp")
	@Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public GenericPersistence generateGenericAppPersistance() {
		return new GenericJpaPeristanceImpl(entityManager);
	}
	
	@SuppressWarnings("rawtypes")
	@Bean("customPersistance")
	public GenericPersistence generateCustomGenericPersistence(EntityManagerFactory entityManagerFactory) {
		return new GenericJpaPeristanceImpl(entityManagerFactory);
	}

}
