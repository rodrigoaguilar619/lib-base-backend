package lib.base.backend.persistance.transaction;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import lib.base.backend.persistance.AbstractPersistence;
import lib.base.backend.persistance.GenericPersistence;

public class GenericJpaPeristanceImpl<T> extends AbstractPersistence<EntityManagerFactory> implements GenericPersistence<T> {

	private static final Logger log = LoggerFactory.getLogger(GenericJpaPeristanceImpl.class);
	
	private final ThreadLocal<EntityManager> threadLocal;
	
	public GenericJpaPeristanceImpl(EntityManagerFactory entityManagerFactory) {
		super(entityManagerFactory);
		threadLocal = new ThreadLocal<>();
	}
	
	public GenericJpaPeristanceImpl(EntityManager entityManager) {
		super(null);
		threadLocal = new ThreadLocal<>();
		if (entityManager != null) {
			threadLocal.set(entityManager);
		}
	}

	private void createEntityManager() {

		EntityManager entityManager = threadLocal.get();

		if (entityManager == null) {
			entityManager = entityManagerFactory.createEntityManager();
			threadLocal.set(entityManager);
		}
	}

	protected EntityManager getEntityManager() {

		return threadLocal.get();
	}

	public void startTransaction() {
		createEntityManager();
		getEntityManager().getTransaction().begin();
	}

	public void commitTransaction() {

		getEntityManager().getTransaction().commit();
	}

	public void rollBackTransaction() {
		try {
			if (getEntityManager().getTransaction().isActive())
				getEntityManager().getTransaction().rollback();
		} catch (Exception ex) {
			log.error("Error generic JPA", ex);
		}
	}

	public void closeEntityManager() {

		EntityManager entityManager = threadLocal.get();
		if (entityManager != null && entityManager.isOpen()) {
			entityManager.close();
			threadLocal.remove();
		}
	}

	public Integer save(T entity) {
		getEntityManager().persist(entity);
		return 1;
	}
	
	public void save(List<T> entity) {
		
		for (T t: entity) {
			getEntityManager().persist(t);
		}
	}
	
	public T update(T entity) {
		return getEntityManager().merge(entity);
	}
	
	@Override
	public T findById(Class<T> clazz, T id) {
		return getEntityManager().find(clazz, id);
	}
	
	@Override
	public List<T> findAll(Class<T> clazz) {
		
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> rootEntry = cq.from(clazz);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = getEntityManager().createQuery(all);
        return allQuery.getResultList();
        
	}

	@Override
	public void flush() {
		getEntityManager().flush();
	}
	
	@Override
	public void clear() {
		getEntityManager().clear();
	}
	
	@Override
	public void delete(T entity) {
		getEntityManager().remove(entity);
	}
	
}
