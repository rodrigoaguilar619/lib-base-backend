package lib.base.backend.utils;

import org.slf4j.Logger;
import org.springframework.core.env.Environment;

import lib.base.backend.exception.data.BusinessException;

public class LogPrintUtil {

	public void logPrintPropertieLoad(Logger log, Environment env, String propertieName, boolean optional, boolean isSensitiveInfo) throws BusinessException {
		
		String propertieValue = env.getProperty(propertieName);
		
		if (propertieValue == null && !optional)
			throw new BusinessException("Propertie " + propertieName + " is required but not found");
		else if (propertieValue == null)
			log.warn("{} is not found", propertieName);
		else if (isSensitiveInfo)
			log.info("{} is loaded", propertieName);
		else
			log.info("{}={}", propertieName, propertieValue);
	}
}
