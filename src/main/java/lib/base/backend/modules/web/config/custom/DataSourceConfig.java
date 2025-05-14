package lib.base.backend.modules.web.config.custom;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

public class DataSourceConfig {

    protected LocalContainerEntityManagerFactoryBean createEntityManagerFactory(EntityManagerFactoryBuilder builder, DataSource dataSource, String[] packages, String persistenceUnitName) {
        return builder.dataSource(dataSource).packages(packages).persistenceUnit(persistenceUnitName).build();
    }
    
    @Bean
	EntityManagerFactoryBuilder entityManagerFactoryBuilder(
	        JpaVendorAdapter jpaVendorAdapter,
	        ObjectProvider<PersistenceUnitManager> persistenceUnitManager) {

	    return new EntityManagerFactoryBuilder(
	            jpaVendorAdapter,
	            new HashMap<>(), // default JPA properties map
	            persistenceUnitManager.getIfAvailable()
	    );
	}
	
	@Bean
	JpaVendorAdapter jpaVendorAdapter() {
	    HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
	    return adapter;
	}
}
