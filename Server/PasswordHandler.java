package Server;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHandler {

    public static String getHash(String password) {
        byte[] hashBytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] data = password.getBytes();
            hashBytes = md.digest(data);

        } catch (NoSuchAlgorithmException e) {
            System.out.println("*happens* Ayyy - PasswordHandler");
        }
        return new String(hashBytes, StandardCharsets.UTF_8);
    }
}
