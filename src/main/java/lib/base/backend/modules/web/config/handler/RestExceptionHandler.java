package lib.base.backend.modules.web.config.handler;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lib.base.backend.exception.data.BusinessException;
import lib.base.backend.pojo.rest.GenericResponsePojo;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleCustomException(Exception ex, WebRequest request, HttpServletResponse httpResponse) {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		
		GenericResponsePojo genericResponseDto = new GenericResponsePojo(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error generic", "");
		
		if (ex instanceof BusinessException)
			genericResponseDto = new GenericResponsePojo(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ex.getLocalizedMessage());
		else if (ex instanceof Exception)
			genericResponseDto = new GenericResponsePojo(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error occurred", "");
		
		log.error(ex.getMessage(), ex);
		
		return new ResponseEntity(genericResponseDto, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}