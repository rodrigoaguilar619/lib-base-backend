package lib.base.backend.modules.security.jwt.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lib.base.backend.modules.security.jwt.config.filter.JwtAuthorizationFilter;
import lib.base.backend.modules.security.jwt.util.JwtCryptUtil;
import lib.base.backend.modules.security.jwt.util.JwtUtil;
import lib.base.backend.utils.CryptUtil;

@Configuration
public class BeansConfig {

	@Bean
	public JwtUtil buildJwtUtil() {
		return new JwtUtil();
	}
	
	@Bean
	public CryptUtil buildCryptUtil() {
		return new CryptUtil();
	}
	
	@Bean
	public JwtCryptUtil buildJwtCryptUtil() {
		return new JwtCryptUtil(buildCryptUtil());
	}
	
	@Bean
	public JwtAuthorizationFilter buildJwtAuthorizationFilter() {
		return new JwtAuthorizationFilter();
	}
}
