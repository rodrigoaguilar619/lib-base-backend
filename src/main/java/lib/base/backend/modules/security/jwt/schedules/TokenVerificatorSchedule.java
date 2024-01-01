package lib.base.backend.modules.security.jwt.schedules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lib.base.backend.modules.security.jwt.business.UserAuthBusiness;

@Component
@ConditionalOnProperty(name = "app.config.security.jwt.schedule.enabled", havingValue = "true", matchIfMissing = false)
public class TokenVerificatorSchedule {
	
	@Autowired
	UserAuthBusiness userAuthBusiness;
	
	@Value("${app.config.security.jwt.expiration.time}")
	private Long expirationTime;

	@Scheduled(fixedRate = 10000) // Run every ten seconds
    public void tokenExpirationVerificator() {
        System.out.println("Executing scheduled task every ten seconds...");
        userAuthBusiness.executeDeleteTokensExpired(expirationTime);
    }
}
