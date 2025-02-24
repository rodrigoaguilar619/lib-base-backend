package lib.base.backend.modules.security.jwt.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserRolEntityPk.class)
public abstract class UserRolEntityPk_ {

	public static SingularAttribute<UserRolEntity, UserRolEntityPk> id;
	public static SingularAttribute<UserRolEntity, UserEntity> userEntity;
	public static SingularAttribute<UserRolEntity, CatalogRolEntity> catalogRolEntity;
}

