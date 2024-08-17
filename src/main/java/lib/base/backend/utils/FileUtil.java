package lib.base.backend.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import lib.base.backend.exception.data.BusinessException;

public class FileUtil {
	
	public boolean deleteFile(File file) {

        if (file.exists())
            return file.delete();
        else
            return false;
    }
	
	public boolean deleteFile(String path) {
		
		return deleteFile(new File(path));
    }
	
	public String fileToBase64(ByteArrayOutputStream byteArrayOutputStream) {
		
		byte[] bytesEncoded = Base64.getEncoder().encode(byteArrayOutputStream.toByteArray());
		return new String(bytesEncoded);
	}
	
	public String convertFileToBase64(File file) throws BusinessException {
        
		try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            String base64String = Base64.getEncoder().encodeToString(fileContent);

            return base64String;
            
        } catch (IOException ex) {
        	throw new BusinessException("Error convertir file to base64", ex);
        }
    }
	
	public String convertFileToBase64(String filePath) throws BusinessException {
        
		return convertFileToBase64(new File(filePath));
    }
	
	public ByteArrayOutputStream convertFileBase64ToByteArray(String fileBase64) throws BusinessException {
		
		byte[] bytes = Base64.getDecoder().decode(fileBase64.getBytes());
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			byteArrayOutputStream.write(bytes);
		} catch (IOException ex) {
			throw new BusinessException("Error convert base64 to byte array", ex);
		}
		
		return byteArrayOutputStream;
	}

	public String readFile(String path) throws IOException {

		return new String(Files.readAllBytes(Paths.get(path)));
	}
	
	public void storeFile(ByteArrayOutputStream byteArrayOutputStream, String filePath) throws BusinessException {
		
		FileOutputStream fileOutputStream = null;
		
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();

            fileOutputStream = new FileOutputStream(file);
            byteArrayOutputStream.writeTo(fileOutputStream);
            
        } catch (IOException ex) {
        	throw new BusinessException("Error storing file", ex);
        }
	}
}
