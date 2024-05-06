package lib.base.backend.modules.security.jwt.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserEntity.class)
public class UserEntity_ {

	public static volatile SingularAttribute<ConfigAuthEntity, Integer> id;
	public static volatile SingularAttribute<ConfigAuthEntity, String> userName;
	public static volatile SingularAttribute<ConfigAuthEntity, String> password;
	public static volatile SingularAttribute<ConfigAuthEntity, Boolean> isActive;
	
	public static final String ID = "id";
	public static final String USER_NAME = "userName";
	public static final String PASSWORD = "password";
	public static final String IS_ACTIVE = "isActive";
}
