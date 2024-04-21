package lib.base.backend.modules.security.jwt.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lib.base.backend.modules.security.jwt.entity.ConfigAuthEntity;
import lib.base.backend.modules.security.jwt.entity.ConfigAuthEntity_;
import lib.base.backend.modules.security.jwt.entity.UserEntity_;

@Repository
public class ConfigAuthRepositoryImpl {

	EntityManager em;
	
	@Autowired
	public ConfigAuthRepositoryImpl(EntityManager em) {
		this.em = em;
	}
	
	public ConfigAuthEntity findByUserName(String userName) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ConfigAuthEntity> cq = cb.createQuery(ConfigAuthEntity.class);
		Root<ConfigAuthEntity> root = cq.from(ConfigAuthEntity.class);
		
		List<Predicate> predicatesAnd = new ArrayList<>();
		predicatesAnd.add(cb.equal(root.get(ConfigAuthEntity_.userEntity).get(UserEntity_.USER_NAME), userName));
		
		cq.where( predicatesAnd.toArray(new Predicate[0]) );

		List<ConfigAuthEntity> userEntities = em.createQuery(cq).getResultList();
		
		return userEntities != null && !userEntities.isEmpty() ? userEntities.get(0) : null;
	}
	
	public ConfigAuthEntity findByUserName(String userName, String pwd) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ConfigAuthEntity> cq = cb.createQuery(ConfigAuthEntity.class);
		Root<ConfigAuthEntity> root = cq.from(ConfigAuthEntity.class);
		
		List<Predicate> predicatesAnd = new ArrayList<>();
		predicatesAnd.add(cb.equal(root.get(ConfigAuthEntity_.userEntity).get(UserEntity_.USER_NAME), userName));
		predicatesAnd.add(cb.equal(root.get(ConfigAuthEntity_.userEntity).get(UserEntity_.PASSWORD), pwd));
		
		cq.where( predicatesAnd.toArray(new Predicate[0]) );

		List<ConfigAuthEntity> userEntities = em.createQuery(cq).getResultList();
		
		return userEntities != null && !userEntities.isEmpty() ? userEntities.get(0) : null;
	}
	
	public ConfigAuthEntity findByUserNameToken(String userName, String token) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ConfigAuthEntity> cq = cb.createQuery(ConfigAuthEntity.class);
		Root<ConfigAuthEntity> root = cq.from(ConfigAuthEntity.class);
		
		List<Predicate> predicatesAnd = new ArrayList<>();
		predicatesAnd.add(cb.equal(root.get(ConfigAuthEntity_.userEntity).get(UserEntity_.USER_NAME), userName));
		predicatesAnd.add(cb.equal(root.get(ConfigAuthEntity_.token), token));
		
		cq.where( predicatesAnd.toArray(new Predicate[0]) );

		List<ConfigAuthEntity> userEntities = em.createQuery(cq).getResultList();
		
		return userEntities != null && !userEntities.isEmpty() ? userEntities.get(0) : null;
	}
	
	public void deleteTokensExpired(Long expirationTime) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaDelete<ConfigAuthEntity> cd = cb.createCriteriaDelete(ConfigAuthEntity.class);
        Root<ConfigAuthEntity> root = cd.from(ConfigAuthEntity.class);
        
        Date dateTokenTop = new Date(System.currentTimeMillis() - expirationTime);
        
        List<Predicate> predicatesAnd = new ArrayList<>();
		predicatesAnd.add(cb.lessThanOrEqualTo(root.get(ConfigAuthEntity_.DATE_REFRESH), dateTokenTop));
		predicatesAnd.add(cb.greaterThan(root.get(ConfigAuthEntity_.ID_USER), 0));
		
		cd.where( predicatesAnd.toArray(new Predicate[0]) );

		em.createQuery(cd).executeUpdate();
	}
}
