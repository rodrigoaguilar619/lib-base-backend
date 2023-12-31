package lib.base.backend.modules.security.jwt.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ConfigAuthEntity.class)
public abstract class ConfigAuthEntity_ {

	public static volatile SingularAttribute<ConfigAuthEntity, Integer> idUser;
	public static volatile SingularAttribute<ConfigAuthEntity, String> token;
	public static volatile SingularAttribute<ConfigAuthEntity, UserEntity> userEntity;
	
	public static final String ID_USER = "idUser";
	public static final String TOKEN = "token";
	public static final String USER_ENTITY = "userEntity";
}
