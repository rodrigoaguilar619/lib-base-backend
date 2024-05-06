package lib.base.backend.modules.security.jwt.entity;

import java.sql.Date;

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
	public static SingularAttribute<ConfigAuthEntity, Date> dateLogin;
	public static SingularAttribute<ConfigAuthEntity, Date> dateRefresh;
	
	public static final String ID_USER = "idUser";
	public static final String TOKEN = "token";
	public static final String USER_ENTITY = "userEntity";
	public static final String DATE_LOGIN = "dateLogin";
	public static final String DATE_REFRESH = "dateRefresh";
}
