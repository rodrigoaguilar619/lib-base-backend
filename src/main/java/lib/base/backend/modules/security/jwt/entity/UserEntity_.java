package lib.base.backend.modules.security.jwt.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserEntity.class)
public class UserEntity_ {

	public static SingularAttribute<UserEntity, Integer> id;
	public static SingularAttribute<UserEntity, String> userName;
	public static SingularAttribute<UserEntity, String> password;
	public static SingularAttribute<UserEntity, Boolean> isActive;
}
