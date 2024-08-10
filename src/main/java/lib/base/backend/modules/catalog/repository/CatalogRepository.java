package lib.base.backend.modules.catalog.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;

@Repository
public class CatalogRepository {
	
	EntityManager em;
	
	@Autowired
	public CatalogRepository(EntityManager em) {
		this.em = em;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteCatalog(Class clazz, Object id) {
		
        CriteriaBuilder cb = em.getCriteriaBuilder();
 
        CriteriaDelete<?> delete = cb.createCriteriaDelete(clazz);
        Root<?> e = delete.from(clazz);
 
        delete.where(cb.equal(e.get("id"), id));
        this.em.createQuery(delete).executeUpdate();
    }
}
