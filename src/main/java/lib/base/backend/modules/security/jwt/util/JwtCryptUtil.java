package lib.base.backend.modules.security.jwt.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import lib.base.backend.exception.data.BusinessException;
import lib.base.backend.utils.CryptUtil;

public class JwtCryptUtil {
	
	@Autowired
	CryptUtil cryptUtil;
	
	@Value("${app.config.security.jwt.secret.key}")
    private String SECRET_KEY;
	
	@Value("${app.config.security.jwt.crypt.algorithm}")
    private String CRYPT_ALGORITHM;

	public String encryptPwd(String pwd) throws BusinessException {
		
		return cryptUtil.encrypt(pwd, SECRET_KEY, CRYPT_ALGORITHM);
	}
}
