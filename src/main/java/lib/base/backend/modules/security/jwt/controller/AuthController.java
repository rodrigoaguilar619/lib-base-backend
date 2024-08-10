package lib.base.backend.modules.security.jwt.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import lib.base.backend.exception.data.BusinessException;
import lib.base.backend.modules.security.jwt.business.UserAuthBusiness;
import lib.base.backend.modules.security.jwt.pojo.data.GetUserLoggedInDataPojo;
import lib.base.backend.modules.security.jwt.util.JwtUtil;
import lib.base.backend.modules.security.jwt.vo.UriCatalog;
import lib.base.backend.pojo.rest.GenericResponsePojo;
import lib.base.backend.pojo.rest.security.LoginRequestPojo;
import lib.base.backend.pojo.rest.security.UserRequestPojo;
import lib.base.backend.utils.HttpUtil;
import lib.base.backend.utils.RestUtil;

@RestController
public class AuthController {
	
	JwtUtil jwtUtil;
	
	UserAuthBusiness userAuthBusiness;
	
	@Autowired
	public AuthController(JwtUtil jwtUtil, UserAuthBusiness userAuthBusiness) {
		this.jwtUtil = jwtUtil;
		this.userAuthBusiness = userAuthBusiness;
	}

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping(UriCatalog.AUTH_LOGIN)
    public ResponseEntity login(@RequestBody LoginRequestPojo authRequest) throws BusinessException {

    	GetUserLoggedInDataPojo dataPojo = userAuthBusiness.executeUserLogIn(authRequest);
    	
    	return new RestUtil().buildResponseSuccess(dataPojo, "User has been logged in");
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping(UriCatalog.AUTH_LOGOUT)
    public ResponseEntity logout(@RequestBody LoginRequestPojo authRequest, @RequestHeader("Authorization") String authorizationHeader) throws BusinessException {
    	
    	userAuthBusiness.executeSaveUserLogOut(authRequest, authorizationHeader);
    	
    	return new RestUtil().buildResponseSuccess("", "User logged out");
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping(UriCatalog.AUTH_REFRESH)
    public ResponseEntity logout(@RequestBody UserRequestPojo userRequest, @RequestHeader("Authorization") String authorizationHeader) throws BusinessException {
    	
    	userAuthBusiness.executeRefreshToken(userRequest, authorizationHeader);
    	
    	return new RestUtil().buildResponseSuccess("", "User token refresh");
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PostMapping(UriCatalog.AUTH_VALIDATE_SESSION)
	public ResponseEntity validateSession(@RequestBody UserRequestPojo userRequest, @RequestHeader("Authorization") String authorizationHeader, HttpServletResponse response) throws IOException {
		
    	boolean isValid = userAuthBusiness.executeValidateSessionActive(userRequest, authorizationHeader);
    	
    	if (!isValid) {
    		GenericResponsePojo<?> genericResponsePojo = new GenericResponsePojo<>(HttpStatus.UNAUTHORIZED.value(), "Not authorized", "");
			response.setContentType("application/json");
		    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		    response.getOutputStream().write(new HttpUtil().mapEntity(genericResponsePojo).getBytes());
		    
		    return null;
    	}
		
		return new RestUtil().buildResponseSuccess("", "User token is valid");
	}
}