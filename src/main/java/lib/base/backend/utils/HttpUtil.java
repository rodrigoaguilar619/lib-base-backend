package lib.base.backend.utils;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil<T> {
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	public HttpUtil() {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public T mapRequest(HttpServletRequest request, Class<T> clazz) throws IOException {
		
		BufferedReader reader = request.getReader();
        return objectMapper.readValue(reader, clazz);
	}
	
	public String mapEntity(Object object) throws JsonProcessingException {
		
		return objectMapper.writeValueAsString(object);
	}
}
