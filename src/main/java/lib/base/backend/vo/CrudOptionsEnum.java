package lib.base.backend.vo;

public enum CrudOptionsEnum {

	UPDATE("update"),
    SAVE("save");

    private final String value;

    CrudOptionsEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
