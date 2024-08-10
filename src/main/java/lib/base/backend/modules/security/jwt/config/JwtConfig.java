package lib.base.backend.modules.security.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lib.base.backend.modules.security.jwt.business.UserAuthBusiness;
import lib.base.backend.modules.security.jwt.config.filter.JwtAuthorizationFilter;
import lib.base.backend.utils.HttpUtil;

@SuppressWarnings("rawtypes")
@EntityScan("${app.security.jwt.package.entity}")
@EnableJpaRepositories("${app.security.jwt.package.repositories}")
@ComponentScan("${app.security.jwt.package.components}")
public class JwtConfig {
	
	private UserAuthBusiness userAuthBusiness;
	
	private HttpUtil httpUtil = new HttpUtil();
	
	@Autowired
	public JwtConfig(UserAuthBusiness userAuthBusiness) {
		super();
		this.userAuthBusiness = userAuthBusiness;
	}

	@Bean
	public JwtAuthorizationFilter buildJwtAuthorizationFilter() {
		return new JwtAuthorizationFilter(userAuthBusiness, httpUtil);
	}
}
