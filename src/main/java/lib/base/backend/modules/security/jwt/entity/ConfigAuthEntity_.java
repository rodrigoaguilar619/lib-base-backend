package lib.base.backend.modules.security.jwt.entity;

import java.time.LocalDateTime;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ConfigAuthEntity.class)
public abstract class ConfigAuthEntity_ {
	
	private ConfigAuthEntity_() { }

	public static SingularAttribute<ConfigAuthEntity, Integer> idUser;
	public static SingularAttribute<ConfigAuthEntity, String> token;
	public static SingularAttribute<ConfigAuthEntity, UserEntity> userEntity;
	public static SingularAttribute<ConfigAuthEntity, LocalDateTime> dateLogin;
	public static SingularAttribute<ConfigAuthEntity, LocalDateTime> dateRefresh;
}
