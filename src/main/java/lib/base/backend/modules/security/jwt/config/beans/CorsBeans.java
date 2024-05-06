package lib.base.backend.modules.security.jwt.config.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsBeans {
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		
	    return new WebMvcConfigurer() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/**")
	                    .allowedOrigins("*")
	                    .allowedMethods("*")
	                    .allowedOriginPatterns("*");
	                    //.allowCredentials(true);
	        }
	    };
	}
}
