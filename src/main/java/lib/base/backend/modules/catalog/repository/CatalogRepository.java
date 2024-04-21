package lib.base.backend.modules.catalog.repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
