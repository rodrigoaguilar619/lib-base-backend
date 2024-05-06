package lib.base.backend.modules.security.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import lib.base.backend.modules.security.jwt.config.filter.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityJwtConfig {

	private JwtAuthorizationFilter jwtAuthorizationFilter;

	@Autowired
	public WebSecurityJwtConfig(JwtAuthorizationFilter jwtAuthorizationFilter) {
		this.jwtAuthorizationFilter = jwtAuthorizationFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
		.cors(Customizer.withDefaults()).csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/**").permitAll())
		.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
		.formLogin(AbstractHttpConfigurer::disable);

		return http.build();
	}
}
