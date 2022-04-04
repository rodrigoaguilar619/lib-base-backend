package lib.base.backend.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

import lib.base.backend.pojo.rest.GenericResponseDto;

public class RestUtil<T> {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity buildResponseSuccess(T body, String message) {
		
		GenericResponseDto genericResponseDto = new GenericResponseDto<T>(HttpStatus.OK.value(), message, "");
		genericResponseDto.setData(body != null ? body : new ObjectMapper().createObjectNode());
		
		return new ResponseEntity(genericResponseDto, HttpStatus.OK);
	}
}
