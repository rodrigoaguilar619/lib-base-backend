package lib.base.backend.persistance;

import java.util.List;

public interface GenericCrud<T> {

	public Integer save(T entity);
	
	public T update(T entity);
	
	public T findById(Class<T> clazz, T id);
	
	public List<T> findAll(Class<T> clazz);
}
