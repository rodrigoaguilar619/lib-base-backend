package lib.base.backend.modules.security.jwt.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import lib.base.backend.exception.data.BusinessException;
import lib.base.backend.utils.CryptUtil;

public class JwtCryptUtil {
	
	CryptUtil cryptUtil;
	
	@Autowired
	public JwtCryptUtil(CryptUtil cryptUtil) {
		this.cryptUtil = cryptUtil;
	}
	
	@Value("${app.config.security.jwt.secret.key}")
    private String secretKey;
	
	@Value("${app.config.security.jwt.crypt.algorithm}")
    private String cryptAlgorithm;
	
	

	public String encryptPwd(String pwd) throws BusinessException {
		
		return cryptUtil.encrypt(pwd, secretKey, cryptAlgorithm);
	}
}
