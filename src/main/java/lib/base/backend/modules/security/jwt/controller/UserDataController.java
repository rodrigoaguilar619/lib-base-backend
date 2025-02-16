package lib.base.backend.modules.security.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lib.base.backend.exception.data.BusinessException;
import lib.base.backend.modules.security.jwt.business.UserDataBusiness;
import lib.base.backend.modules.security.jwt.vo.UriCatalog;
import lib.base.backend.pojo.rest.security.UserRequestPojo;
import lib.base.backend.pojo.user.UserDataPojo;
import lib.base.backend.utils.RestUtil;

@RestController
public class UserDataController {
	
	UserDataBusiness userDataBusiness;
	
	public UserDataController(UserDataBusiness userDataBusiness) {
		this.userDataBusiness = userDataBusiness;
	}

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping(path = UriCatalog.API_USER_DATA_GET, consumes = "application/json", produces = "application/json")
    public ResponseEntity getUserData(@RequestBody UserRequestPojo requestPojo) throws BusinessException {

    	UserDataPojo dataPojo = userDataBusiness.executeGetUserData(requestPojo);
    	
    	return new RestUtil().buildResponseSuccess(dataPojo, "User data getted");
    }
}