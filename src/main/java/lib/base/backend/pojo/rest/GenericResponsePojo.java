package lib.base.backend.pojo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GenericResponsePojo<T> {
	
	private Integer code;
	
	private String message;
	
	private String messageTecnicSupport;
	
	public GenericResponsePojo(Integer code, String message, String messageTecnicSupport) {
		super();
		this.code = code;
		this.message = message;
		this.messageTecnicSupport = messageTecnicSupport;
	}

	@SuppressWarnings("unchecked")
	private T data = (T) new ObjectMapper().createObjectNode();

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageTecnicSupport() {
		return messageTecnicSupport;
	}

	public void setMessageTecnicSupport(String messageTecnicSupport) {
		this.messageTecnicSupport = messageTecnicSupport;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
