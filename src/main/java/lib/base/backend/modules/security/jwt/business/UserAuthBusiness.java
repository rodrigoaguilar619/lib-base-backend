package lib.base.backend.modules.security.jwt.business;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lib.base.backend.exception.data.BusinessException;
import lib.base.backend.modules.security.jwt.entity.ConfigAuthEntity;
import lib.base.backend.modules.security.jwt.entity.UserEntity;
import lib.base.backend.modules.security.jwt.pojo.data.GetUserLoggedInDataPojo;
import lib.base.backend.modules.security.jwt.repository.ConfigAuthRepositoryImpl;
import lib.base.backend.modules.security.jwt.repository.UserRepositoryImpl;
import lib.base.backend.modules.security.jwt.util.JwtCryptUtil;
import lib.base.backend.modules.security.jwt.util.JwtUtil;
import lib.base.backend.modules.security.jwt.wrapper.HttpRequestWrapper;
import lib.base.backend.persistance.GenericPersistence;
import lib.base.backend.pojo.rest.security.LoginRequestPojo;
import lib.base.backend.pojo.rest.security.UserRequestPojo;
import lib.base.backend.utils.HttpUtil;

@Component
public class UserAuthBusiness {
	
	private static final Logger log = LoggerFactory.getLogger(UserAuthBusiness.class);
	
	@SuppressWarnings("rawtypes")
	private GenericPersistence genericCustomPersistance;
	
	private UserRepositoryImpl userRepository;
	
	private ConfigAuthRepositoryImpl configAuthRepository;
	
	private JwtUtil jwtUtil;
	
	private JwtCryptUtil jwtCryptUtil;
	
	@SuppressWarnings("rawtypes")
	private HttpUtil httpUtil;
	
	@Value("${app.config.security.jwt.skip.auth}")
	private boolean isSkipAuth;
	
	@Autowired
	public UserAuthBusiness(GenericPersistence<?> genericCustomPersistance, UserRepositoryImpl userRepository, ConfigAuthRepositoryImpl configAuthRepository, JwtUtil jwtUtil, JwtCryptUtil jwtCryptUtil, HttpUtil<?> httpUtil) {
		this.genericCustomPersistance = genericCustomPersistance;
		this.userRepository = userRepository;
		this.configAuthRepository = configAuthRepository;
		this.jwtUtil = jwtUtil;
		this.jwtCryptUtil = jwtCryptUtil;
		this.httpUtil = httpUtil;
	}

	public boolean isUserLoggedIn(String username, String pwd) {
		
        ConfigAuthEntity configAuthEntity = configAuthRepository.findByUserName(username, pwd);
        return configAuthEntity != null;
    }
	
	public UserEntity validateUserLogIn(LoginRequestPojo authRequest) throws BusinessException {
		
		String pwdEncrypt = jwtCryptUtil.encryptPwd(authRequest.getPwd());
		
		if (isUserLoggedIn(authRequest.getUserName(), pwdEncrypt))
    		throw new BusinessException("User with session active");
		
		UserEntity userEntity = userRepository.find(authRequest.getUserName(), pwdEncrypt);
		
		if (userEntity == null)
			throw new BusinessException("User cannot be logged in");
		
		return userEntity;
    }
	
	public UserEntity validateUserLogInNotPwd(LoginRequestPojo authRequest) throws BusinessException {
		
		UserEntity userEntity = userRepository.findByUserName(authRequest.getUserName());
		
		if (userEntity == null)
			throw new BusinessException("User cannot be logged in");
		
		return userEntity;
    }
	
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor = Exception.class)
    public GetUserLoggedInDataPojo executeUserLogIn(LoginRequestPojo authRequest) throws BusinessException {
		
		UserEntity userEntity;
		
		if (isSkipAuth) {
			log.debug("USER AUTH: skiping user auth with password");
			userEntity = validateUserLogInNotPwd(authRequest);
		}
		else
			userEntity = validateUserLogIn(authRequest);
    	
        ConfigAuthEntity configAuthEntity = configAuthRepository.findByUserName(authRequest.getUserName());
        
        if (configAuthEntity == null) {
        	
            configAuthEntity = new ConfigAuthEntity();
            configAuthEntity.setIdUser(userEntity.getId());
            configAuthEntity.setDateLogin(new Date());
            configAuthEntity.setDateRefresh(new Date());
        }
        
        String token = jwtUtil.generateToken(authRequest.getUserName());
        
        configAuthEntity.setToken(token);
        genericCustomPersistance.save(configAuthEntity);
        
        GetUserLoggedInDataPojo dataPojo = new GetUserLoggedInDataPojo();
    	dataPojo.setToken(token);
    	dataPojo.setUserName(authRequest.getUserName());
    	
    	return dataPojo;
    }
	
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor = Exception.class)
    public void executeSaveUserLogOut(LoginRequestPojo authRequest, String authorizationHeader) throws BusinessException {
		
		String token = jwtUtil.extractToken(authorizationHeader);
		
		if (token == null)
			throw new BusinessException("Invalid or missing token");
    	
        ConfigAuthEntity configAuthEntity = configAuthRepository.findByUserNameToken(authRequest.getUserName(), token);
        
        if (configAuthEntity == null)
        	throw new BusinessException("User not logged in");
        
        genericCustomPersistance.delete(configAuthEntity);
    }
	
	@Transactional(rollbackFor = Exception.class)
    public boolean executeValidateToken(UserRequestPojo authRequest, String authorizationHeader) {
		
		String token = jwtUtil.extractToken(authorizationHeader);
		
		if (token == null)
			return false;
    	
        ConfigAuthEntity configAuthEntity = configAuthRepository.findByUserNameToken(authRequest.getUserName(), token);
        
        return configAuthEntity != null;
    }
	
	@SuppressWarnings("unchecked")
	public boolean validateSessionActive(HttpRequestWrapper requestWrapper) throws IOException {
		
		String headerAuthorization = requestWrapper.getHeader("Authorization");
		UserRequestPojo userRequestPojo = (UserRequestPojo) httpUtil.mapRequest(requestWrapper, UserRequestPojo.class);
		return executeValidateToken(userRequestPojo, headerAuthorization);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor = Exception.class)
    public boolean executeValidateAndRefreshToken(UserRequestPojo authRequest, String authorizationHeader) {
		
		String token = jwtUtil.extractToken(authorizationHeader);
		
		if (token == null)
			return false;
    	
        ConfigAuthEntity configAuthEntity = configAuthRepository.findByUserNameToken(authRequest.getUserName(), token);
        
		if (configAuthEntity == null)
			return false;
        
        configAuthEntity.setDateRefresh(new Date());
        genericCustomPersistance.save(configAuthEntity);
        
        return configAuthEntity != null;
    }
	
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor = Exception.class)
    public GetUserLoggedInDataPojo executeRefreshToken(UserRequestPojo authRequest, String authorizationHeader) throws BusinessException {
		
		String token = jwtUtil.extractToken(authorizationHeader);
		
		if (token == null)
			throw new BusinessException("Token not found on header");
    	
        ConfigAuthEntity configAuthEntity = configAuthRepository.findByUserNameToken(authRequest.getUserName(), token);
        
        if (configAuthEntity == null)
        	throw new BusinessException("Token has expired");
        
        configAuthEntity.setDateRefresh(new Date());
        genericCustomPersistance.save(configAuthEntity);
        
        GetUserLoggedInDataPojo dataPojo = new GetUserLoggedInDataPojo();
        dataPojo.setToken(token);
        
        return dataPojo;
    }
	
	@Transactional(rollbackFor = Exception.class)
    public void executeDeleteTokensExpired(Long expirationTime) {
		
		if (expirationTime == null || expirationTime == 0) {
			log.debug("USER AUTH: expiration time not configured");
			return;
		}
		
		configAuthRepository.deleteTokensExpired(expirationTime);
    }
	
	@Transactional(rollbackFor = Exception.class)
	public boolean executeValidateSessionActive(HttpServletRequest request) throws IOException {
		
		HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
		
		return validateSessionActive(requestWrapper);
	}
}
