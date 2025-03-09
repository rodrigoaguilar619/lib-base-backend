package lib.base.backend.modules.security.jwt.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lib.base.backend.modules.security.jwt.entity.CatalogRolEntity_;
import lib.base.backend.modules.security.jwt.entity.UserEntity_;
import lib.base.backend.modules.security.jwt.entity.UserRolEntity;
import lib.base.backend.modules.security.jwt.entity.UserRolEntity_;

@Repository
public class UserRolRepositoryImpl {

	EntityManager em;
	
	public UserRolRepositoryImpl(EntityManager em) {
		this.em = em;
	}
	
	public boolean validateUserHasRol(String userName, int idRol) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<UserRolEntity> root = cq.from(UserRolEntity.class);
		
		List<Predicate> predicatesAnd = new ArrayList<>();
		predicatesAnd.add(cb.equal(root.get(UserRolEntity_.userEntity).get(UserEntity_.userName), userName));
		predicatesAnd.add(cb.equal(root.get(UserRolEntity_.catalogRolEntity).get(CatalogRolEntity_.id), idRol));
		
		cq.select(cb.count(root)).where(predicatesAnd.toArray(new Predicate[0]));
		return em.createQuery(cq).getSingleResult() > 0;
		
	}
}
