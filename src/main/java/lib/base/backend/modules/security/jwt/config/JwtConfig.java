package lib.base.backend.modules.security.jwt.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("${app.security.jwt.package.entity}")
@EnableJpaRepositories("${app.security.jwt.package.repositories}")
@ComponentScan("${app.security.jwt.package.components}")
public class JwtConfig {

}
