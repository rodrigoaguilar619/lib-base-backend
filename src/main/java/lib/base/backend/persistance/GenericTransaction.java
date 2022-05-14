package lib.base.backend.persistance;

public interface GenericTransaction {

	public void startTransaction();
	
	public void commitTransaction();
	
	public void rollBackTransaction();
	
	public void closeEntityManager();

	public void flush();
	
	public void clear();
}
