package lib.base.backend.modules.security.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import lib.base.backend.modules.security.jwt.config.filter.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
@Order(1)
public class WebSecurityJwtConfig extends WebSecurityConfigurerAdapter {
	
    private JwtAuthorizationFilter jwtAuthorizationFilter;
	
	@Autowired
	public WebSecurityJwtConfig(JwtAuthorizationFilter jwtAuthorizationFilter) {
		this.jwtAuthorizationFilter = jwtAuthorizationFilter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		 http
		 .cors(Customizer.withDefaults())
		 .csrf().disable()
		 .authorizeRequests()
         	.antMatchers("/**").permitAll()
         	.and()
         .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
         .formLogin().disable(); 
	}
}
