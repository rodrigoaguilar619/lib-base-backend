package lib.base.backend.modules.security.jwt.wrapper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class HttpRequestWrapper extends HttpServletRequestWrapper {

    private byte[] cachedBody;
    private MultipartHttpServletRequest multipartRequest;

    public HttpRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        if (isMultipartContent(request)) {
            StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
            this.multipartRequest = multipartResolver.resolveMultipart(request);
            this.cachedBody = readMultipartRequestBodyAsJson();
        } else {
            InputStream requestInputStream = request.getInputStream();
            this.cachedBody = StreamUtils.copyToByteArray(requestInputStream);
        }
    }
    
    private byte[] readMultipartRequestBodyAsJson() throws IOException {
    	
        Map<String, Object> jsonMap = new HashMap<>();

        // Add form fields to the JSON map
        Map<String, String[]> parameterMap = multipartRequest.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            if (entry.getValue().length == 1) {
                jsonMap.put(entry.getKey(), entry.getValue()[0]);
            } else {
                jsonMap.put(entry.getKey(), entry.getValue());
            }
        }

        // Add files to the JSON map
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
            MultipartFile file = entry.getValue();
            Map<String, Object> fileData = new HashMap<>();
            fileData.put("filename", file.getOriginalFilename());
            fileData.put("contentType", file.getContentType());
            fileData.put("size", file.getSize());
            fileData.put("content", file.getBytes());
            jsonMap.put(entry.getKey(), fileData);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(jsonMap);

        return jsonString.getBytes(StandardCharsets.UTF_8);
    }

    private boolean isMultipartContent(HttpServletRequest request) {
        return request.getContentType() != null && request.getContentType().toLowerCase().startsWith("multipart/");
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new CachedBodyServletInputStream(this.cachedBody);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.cachedBody);
        return new BufferedReader(new InputStreamReader(byteArrayInputStream));
    }

    @Override
    public String getParameter(String name) {
        if (multipartRequest != null) {
            return multipartRequest.getParameter(name);
        }
        return super.getParameter(name);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        if (multipartRequest != null) {
            return multipartRequest.getParameterMap();
        }
        return super.getParameterMap();
    }

    @Override
    public Enumeration<String> getParameterNames() {
        if (multipartRequest != null) {
            return multipartRequest.getParameterNames();
        }
        return super.getParameterNames();
    }

    @Override
    public String[] getParameterValues(String name) {
        if (multipartRequest != null) {
            return multipartRequest.getParameterValues(name);
        }
        return super.getParameterValues(name);
    }
}
