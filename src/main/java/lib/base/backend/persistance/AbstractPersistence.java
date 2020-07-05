package lib.base.backend.persistance;

public abstract class AbstractPersistence<T> {
	
	protected T entityManagerFactory;

	public AbstractPersistence(T entityManagerFactory) {
		super();
		this.entityManagerFactory = entityManagerFactory;
	}

	public T getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(T entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

}
