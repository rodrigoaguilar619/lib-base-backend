package lib.base.backend.utils;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import lib.base.backend.exception.data.BusinessException;

public class CryptUtil {
	
	public String encrypt(String message, String secretKey, String algorithm) throws BusinessException {
        
		try {
			// Create a MAC object with algorithm
	        Mac mac = Mac.getInstance(algorithm);
	        // Create a secret key from the provided string
	        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), algorithm);
	        // Initialize the MAC object with the key
	        mac.init(keySpec);
	        // Compute the HMAC hash
	        byte[] hmacHash = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
	        // Encode the hash in Base64 to get the final encrypted string
	        String encryptedMessage = Base64.getEncoder().encodeToString(hmacHash);
	        
	        return encryptedMessage;
		}
		catch (NoSuchAlgorithmException e) {
			throw new BusinessException("Error encrypting with " + algorithm + ", no such algorithm", e);
		}
		catch (InvalidKeyException e) {
			throw new BusinessException("Error encrypting with \" + algorithm + \", invalid key", e);
		}

    }
}
