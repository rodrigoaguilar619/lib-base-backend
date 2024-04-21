package lib.base.backend.modules.security.jwt.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lib.base.backend.modules.security.jwt.entity.UserEntity;
import lib.base.backend.modules.security.jwt.entity.UserEntity_;

@Repository
public class UserRepositoryImpl {

	EntityManager em;
	
	@Autowired
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
}
