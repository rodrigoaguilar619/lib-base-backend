package lib.base.backend.modules.security.jwt.config.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import lib.base.backend.modules.security.jwt.business.UserAuthBusiness;
import lib.base.backend.modules.security.jwt.util.JwtUtil;
import lib.base.backend.pojo.rest.GenericResponsePojo;
import lib.base.backend.pojo.rest.security.UserRequestPojo;
import lib.base.backend.utils.HttpUtil;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
	
	@Autowired
	UserAuthBusiness userAuthBusiness;
	
	@SuppressWarnings("unused")
	@Autowired
	private JwtUtil jwtUtil;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private HttpUtil httpUtil;
	
	@Value("${app.config.security.jwt.skip.auth}")
	private Boolean isSkipAuth;
	
	private static final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

	@SuppressWarnings("unchecked")
	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
		
		if (isSkipAuth) {
			log.debug("JWT AUTH: skipping authorization");
			filterChain.doFilter(request, response);
			return;
		}
		
		String uriWithoutContext = request.getRequestURI().substring(request.getContextPath().length());
		
		if (uriWithoutContext.startsWith("/auth/")) {
			log.debug("JWT AUTH: allowing paths under /auth/");
			filterChain.doFilter(request, response);
			return;
		}
		else {
			String headerAuthorization = request.getHeader("Authorization");
			UserRequestPojo userRequestPojo = (UserRequestPojo) httpUtil.mapRequest(request, UserRequestPojo.class);
			boolean isSessionActive = userAuthBusiness.executeValidateAndRefreshToken(userRequestPojo, headerAuthorization);
			log.debug("JWT AUTH: token session not active");
			if (!isSessionActive) {
				GenericResponsePojo<?> genericResponsePojo = new GenericResponsePojo<Object>(HttpStatus.UNAUTHORIZED.value(), "Not authorized", "");
				response.setContentType("application/json");
			    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			    response.getOutputStream().write(httpUtil.mapEntity(genericResponsePojo).getBytes());
				return;
			}
		}

		filterChain.doFilter(request, response);
    }
}
