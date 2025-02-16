package lib.base.backend.modules.security.jwt.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserRolEntityPk.class)
public abstract class UserRolEntity_ {

	public static SingularAttribute<UserRolEntityPk, Integer> idUser;
	public static SingularAttribute<UserRolEntityPk, Integer> idRol;
	public static SingularAttribute<UserRolEntityPk, UserEntity> userEntity;
	public static SingularAttribute<UserRolEntityPk, CatalogRolEntity> catalogRolEntity;

	public static final String ID_USER = "idUser";
	public static final String ID_ROL = "idRol";
	public static final String USER_ENTITY = "userEntity";
	public static final String CATALOG_ROL_ENTITY = "catalogRolEntity";

}

