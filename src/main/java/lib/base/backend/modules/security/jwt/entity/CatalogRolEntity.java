package lib.base.backend.modules.security.jwt.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lib.base.backend.entity.generic.GenericCatalogIntEntity;


@Entity
@Table(name="catalog_rol")
public class CatalogRolEntity extends GenericCatalogIntEntity implements Serializable {

	private static final long serialVersionUID = 1L;

}