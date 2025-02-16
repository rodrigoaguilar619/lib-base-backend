package lib.base.backend.modules.security.jwt.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lib.base.backend.modules.security.jwt.entity.CatalogRolEntity;
import lib.base.backend.modules.security.jwt.entity.UserEntity;
import lib.base.backend.modules.security.jwt.entity.UserEntity_;
import lib.base.backend.modules.security.jwt.entity.UserRolEntity;
import lib.base.backend.modules.security.jwt.entity.UserRolEntity_;

@Repository
public class UserRepositoryImpl {

	EntityManager em;
	
	public UserRepositoryImpl(EntityManager em) {
		this.em = em;
	}
	
	public UserEntity findByUserName(String userName) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
		Root<UserEntity> root = cq.from(UserEntity.class);
		
		List<Predicate> predicatesAnd = new ArrayList<>();
		predicatesAnd.add(cb.equal(root.get(UserEntity_.USER_NAME), userName));
		cq.where( predicatesAnd.toArray(new Predicate[0]) );

		List<UserEntity> userEntities = em.createQuery(cq).getResultList();
		
		return userEntities != null && !userEntities.isEmpty() ? userEntities.get(0) : null;
	}
	
	public UserEntity find(String userName, String pwd) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
		Root<UserEntity> root = cq.from(UserEntity.class);
		
		List<Predicate> predicatesAnd = new ArrayList<>();
		predicatesAnd.add(cb.equal(root.get(UserEntity_.USER_NAME), userName));
		predicatesAnd.add(cb.equal(root.get(UserEntity_.PASSWORD), pwd));
		cq.where( predicatesAnd.toArray(new Predicate[0]) );

		List<UserEntity> userEntities = em.createQuery(cq).getResultList();
		
		return userEntities != null && !userEntities.isEmpty() ? userEntities.get(0) : null;
	}
	
	public List<UserEntity> findAllStatusActive() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
		Root<UserEntity> root = cq.from(UserEntity.class);
		
		List<Predicate> predicatesAnd = new ArrayList<>();
		predicatesAnd.add(cb.equal(root.get(UserEntity_.IS_ACTIVE), true));
		cq.where( predicatesAnd.toArray(new Predicate[0]) );

		return em.createQuery(cq).getResultList();
	}
	
	public List<CatalogRolEntity> findUserRols(String userName) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<CatalogRolEntity> cq = cb.createQuery(CatalogRolEntity.class);
		Root<UserRolEntity> root = cq.from(UserRolEntity.class);
		cq.select(root.get(UserRolEntity_.CATALOG_ROL_ENTITY));
		
		Join<UserRolEntity, UserEntity> joinUser = root.join(UserRolEntity_.USER_ENTITY, JoinType.LEFT);
		
		List<Predicate> predicatesAnd = new ArrayList<>();
		predicatesAnd.add(cb.equal(joinUser.get(UserEntity_.USER_NAME), userName));
		cq.where( predicatesAnd.toArray(new Predicate[0]) );

		return em.createQuery(cq).getResultList();
	}
}
