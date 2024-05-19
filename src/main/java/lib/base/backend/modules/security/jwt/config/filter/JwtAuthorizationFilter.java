package lib.base.backend.modules.security.jwt.config.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import lib.base.backend.modules.security.jwt.business.UserAuthBusiness;
import lib.base.backend.modules.security.jwt.util.JwtUtil;
import lib.base.backend.modules.security.jwt.vo.UriCatalog;
import lib.base.backend.modules.security.jwt.wrapper.HttpRequestWrapper;
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
	private boolean isSkipAuth;
	
	private static final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

	@SuppressWarnings("unchecked")
	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
		
		HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
		
		if (isSkipAuth) {
			log.debug("JWT AUTH: skipping authorization");
			filterChain.doFilter(requestWrapper, response);
			return;
		}
		
		String uriWithoutContext = requestWrapper.getRequestURI().substring(requestWrapper.getContextPath().length());
		
		if (uriWithoutContext.startsWith("/api/") || uriWithoutContext.startsWith(UriCatalog.AUTH_REFRESH)) {
			
			String headerAuthorization = requestWrapper.getHeader("Authorization");
			UserRequestPojo userRequestPojo = (UserRequestPojo) httpUtil.mapRequest(requestWrapper, UserRequestPojo.class);
			
			boolean isSessionActive = userAuthBusiness.executeValidateSessionActive(userRequestPojo, headerAuthorization);
			
			if (!isSessionActive) {
				GenericResponsePojo<?> genericResponsePojo = new GenericResponsePojo<>(HttpStatus.UNAUTHORIZED.value(), "Not authorized", "");
				response.setContentType("application/json");
			    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			    response.getOutputStream().write(httpUtil.mapEntity(genericResponsePojo).getBytes());
				return;
			}
		}
		else if (uriWithoutContext.startsWith("/auth/")) {
			log.debug("JWT AUTH: allowing paths under /auth/");
			filterChain.doFilter(requestWrapper, response);
			return;
		}

		filterChain.doFilter(requestWrapper, response);
    }
}
