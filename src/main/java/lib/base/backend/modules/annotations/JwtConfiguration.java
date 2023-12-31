package lib.base.backend.modules.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;
import lib.base.backend.modules.security.jwt.config.JwtConfig;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(JwtConfig.class)
public @interface JwtConfiguration {
}
