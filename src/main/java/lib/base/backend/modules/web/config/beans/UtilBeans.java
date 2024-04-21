package lib.base.backend.modules.web.config.beans;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import lib.base.backend.utils.CatalogUtil;
import lib.base.backend.utils.DataUtil;
import lib.base.backend.utils.HttpUtil;
import lib.base.backend.utils.JpaUtil;
import lib.base.backend.utils.ReadFileUtil;
import lib.base.backend.utils.RestUtil;
import lib.base.backend.utils.date.DateFormatUtil;
import lib.base.backend.utils.date.DateUtil;

@Configuration
public class UtilBeans {
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public DateFormatUtil generateDateFormatUtil() {
		return new DateFormatUtil();
	}
	
	@Bean
	public DateUtil generateDateUtil() {
		return new DateUtil();
	}

	@Bean
	public CatalogUtil buildCatalogUtil() {
		return new CatalogUtil();
	}
	
	@Bean
	public JpaUtil buildJpaUtil() {
		return new JpaUtil();
	}
	
	@Bean
	public ReadFileUtil buildReadFileUtil() {
		return new ReadFileUtil();
	}
	
	@SuppressWarnings("rawtypes")
	@Bean
	public HttpUtil buildHttpUtil() {
		return new HttpUtil();
	}
	
	@SuppressWarnings("rawtypes")
	@Bean
	public RestUtil buildRestUti() {
		return new RestUtil();
	}
	
	@Bean
	public DataUtil buildDataUtil() {
		return new DataUtil();
	}
}
