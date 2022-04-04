package lib.base.backend.web.enumeratos;

public enum CrudOptionsEnum {

	SAVE(1, "save"),
	UPDATE(2, "update");
	
	private final int id;
	private final String description;
	
	private CrudOptionsEnum(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
	
}
