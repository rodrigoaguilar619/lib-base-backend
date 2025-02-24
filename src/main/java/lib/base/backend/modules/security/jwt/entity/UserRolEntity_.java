package lib.base.backend.modules.security.jwt.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserRolEntityPk.class)
public abstract class UserRolEntity_ {

	public static SingularAttribute<UserRolEntityPk, Integer> idUser;
	public static SingularAttribute<UserRolEntityPk, Integer> idRol;
	public static SingularAttribute<UserRolEntity, UserEntity> userEntity;
	public static SingularAttribute<UserRolEntity, CatalogRolEntity> catalogRolEntity;

}

