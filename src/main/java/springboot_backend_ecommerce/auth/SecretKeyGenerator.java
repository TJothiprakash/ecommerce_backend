package springboot_backend_ecommerce.auth;

import java.security.SecureRandom;
import java.util.Base64;

public  class SecretKeyGenerator {
    public static String main() {
        byte[] key = new byte[32]; // 256-bit key
        new SecureRandom().nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        System.out.println("Generated Secret Key: " + encodedKey);
        return encodedKey;
    }
}