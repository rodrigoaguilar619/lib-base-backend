package lib.base.backend.utils;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil<T> {
	
	ObjectMapper objectMapper = new ObjectMapper();

	public T mapRequest(HttpServletRequest request, Class<T> clazz) throws IOException {
		
		BufferedReader reader = request.getReader();
        T customRequest = (T) objectMapper.readValue(reader, clazz);
        
        return customRequest;
	}
	
	public String mapEntity(Object object) throws JsonProcessingException {
		
		String jsonString = objectMapper.writeValueAsString(object);
		
		return jsonString;
	}
}
