package lib.base.backend.exception.data;

import lib.base.backend.exception.BaseException;

public class BusinessException extends BaseException {

	private static final long serialVersionUID = 1L;

	public BusinessException() {
		super();
	}
	
	public BusinessException(String message) {
		super( message );
	}
	
	public BusinessException(Throwable cause) {
		super( cause );
	}
	
	public BusinessException(String message, Throwable cause) {
		super( message, cause );
	}
}
