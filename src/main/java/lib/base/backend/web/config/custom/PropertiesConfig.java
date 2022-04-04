package lib.base.backend.web.config.custom;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@PropertySource("classpath:application.properties")
public class PropertiesConfig {

}
