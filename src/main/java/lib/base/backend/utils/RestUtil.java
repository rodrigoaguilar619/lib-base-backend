package lib.base.backend.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

import lib.base.backend.pojo.rest.GenericResponsePojo;

public class RestUtil<T> {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity buildResponseSuccess(T body, String message) {
		
		GenericResponsePojo genericResponsePojo = new GenericResponsePojo<T>(HttpStatus.OK.value(), message, "");
		genericResponsePojo.setData(body != null ? body : new ObjectMapper().createObjectNode());
		
		return new ResponseEntity(genericResponsePojo, HttpStatus.OK);
	}
	
	public ResponseEntity buildResponseUnprocessable(String message) {
		
		GenericResponsePojo genericResponsePojo = new GenericResponsePojo(HttpStatus.UNPROCESSABLE_ENTITY.value(), message, "");
		
		return new ResponseEntity(genericResponsePojo, HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
