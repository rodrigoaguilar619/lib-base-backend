package lib.base.backend.modules.security.jwt.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lib.base.backend.modules.security.jwt.entity.CatalogRolEntity;
import lib.base.backend.modules.security.jwt.repository.UserRepositoryImpl;
import lib.base.backend.pojo.rest.security.UserRequestPojo;
import lib.base.backend.pojo.user.UserDataPojo;

@Component
public class UserDataBusiness {
	
	private UserRepositoryImpl userRepository;
	
	@Value("${app.config.security.jwt.skip.auth}")
	private boolean isSkipAuth;
	
	public UserDataBusiness(UserRepositoryImpl userRepository) {
		this.userRepository = userRepository;
	}
	
	public UserDataPojo getUserData(String userName) {
		
		List<CatalogRolEntity> catalogRols = userRepository.findUserRols(userName);
		List<String> userRols = catalogRols.stream().map(CatalogRolEntity::getDescription).toList();
		
		return new UserDataPojo(userName, userRols);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public UserDataPojo executeGetUserData(UserRequestPojo userRequestPojo) {
		
		return getUserData(userRequestPojo.getUserName());
	}
}
