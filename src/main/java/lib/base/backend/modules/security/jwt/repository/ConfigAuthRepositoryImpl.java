package lib.base.backend.modules.security.jwt.repository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lib.base.backend.modules.security.jwt.entity.ConfigAuthEntity;
import lib.base.backend.modules.security.jwt.entity.ConfigAuthEntity_;
import lib.base.backend.modules.security.jwt.entity.UserEntity_;

@Repository
public class ConfigAuthRepositoryImpl {

	EntityManager em;
	
	public ConfigAuthRepositoryImpl(EntityManager em) {
		this.em = em;
	}
	
	public ConfigAuthEntity findByUserName(String userName) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ConfigAuthEntity> cq = cb.createQuery(ConfigAuthEntity.class);
		Root<ConfigAuthEntity> root = cq.from(ConfigAuthEntity.class);
		
		List<Predicate> predicatesAnd = new ArrayList<>();
		predicatesAnd.add(cb.equal(root.get(ConfigAuthEntity_.userEntity).get(UserEntity_.userName), userName));
		
		cq.where( predicatesAnd.toArray(new Predicate[0]) );

		List<ConfigAuthEntity> userEntities = em.createQuery(cq).getResultList();
		
		return userEntities != null && !userEntities.isEmpty() ? userEntities.get(0) : null;
	}
	
	public ConfigAuthEntity findByUserName(String userName, String pwd) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ConfigAuthEntity> cq = cb.createQuery(ConfigAuthEntity.class);
		Root<ConfigAuthEntity> root = cq.from(ConfigAuthEntity.class);
		
		List<Predicate> predicatesAnd = new ArrayList<>();
		predicatesAnd.add(cb.equal(root.get(ConfigAuthEntity_.userEntity).get(UserEntity_.userName), userName));
		predicatesAnd.add(cb.equal(root.get(ConfigAuthEntity_.userEntity).get(UserEntity_.password), pwd));
		
		cq.where( predicatesAnd.toArray(new Predicate[0]) );

		List<ConfigAuthEntity> userEntities = em.createQuery(cq).getResultList();
		
		return userEntities != null && !userEntities.isEmpty() ? userEntities.get(0) : null;
	}
	
	public ConfigAuthEntity findByUserNameToken(String userName, String token) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ConfigAuthEntity> cq = cb.createQuery(ConfigAuthEntity.class);
		Root<ConfigAuthEntity> root = cq.from(ConfigAuthEntity.class);
		
		List<Predicate> predicatesAnd = new ArrayList<>();
		predicatesAnd.add(cb.equal(root.get(ConfigAuthEntity_.userEntity).get(UserEntity_.userName), userName));
		predicatesAnd.add(cb.equal(root.get(ConfigAuthEntity_.token), token));
		
		cq.where( predicatesAnd.toArray(new Predicate[0]) );

		List<ConfigAuthEntity> userEntities = em.createQuery(cq).getResultList();
		
		return userEntities != null && !userEntities.isEmpty() ? userEntities.get(0) : null;
	}
	
	public void deleteTokensExpired(Long expirationTime) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaDelete<ConfigAuthEntity> cd = cb.createCriteriaDelete(ConfigAuthEntity.class);
        Root<ConfigAuthEntity> root = cd.from(ConfigAuthEntity.class);
        
        LocalDateTime dateTokenTop = LocalDateTime.now().minus(Duration.ofMillis(expirationTime));
        
        List<Predicate> predicatesAnd = new ArrayList<>();
		predicatesAnd.add(cb.lessThanOrEqualTo(root.get(ConfigAuthEntity_.dateRefresh), dateTokenTop));
		predicatesAnd.add(cb.greaterThan(root.get(ConfigAuthEntity_.idUser), 0));
		
		cd.where( predicatesAnd.toArray(new Predicate[0]) );

		em.createQuery(cd).executeUpdate();
	}
}
