package lib.base.backend.modules.web.config.custom;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({@PropertySource("classpath:application.properties")})
public class PropertiesConfig {

}
