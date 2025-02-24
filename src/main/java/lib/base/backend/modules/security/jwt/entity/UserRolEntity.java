package lib.base.backend.modules.security.jwt.entity;

import java.io.Serializable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "user_rol")
public class UserRolEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private UserRolEntityPk id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user", insertable = false, updatable = false)
	private UserEntity userEntity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_rol", insertable = false, updatable = false)
	private CatalogRolEntity catalogRolEntity;
}
