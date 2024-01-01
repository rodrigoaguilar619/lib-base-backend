package lib.base.backend.modules.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@PropertySources({@PropertySource("classpath:application_default.properties")})
@EnableJpaRepositories("${app.web.package.repositories}")
@ComponentScan("${app.web.package.components}")
public class WebConfig {

}
