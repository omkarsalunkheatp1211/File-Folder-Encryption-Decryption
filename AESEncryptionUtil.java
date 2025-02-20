
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryptionUtil {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final byte[] IV = "1234567890123456".getBytes();  // 16-byte static IV

    // Correct method signature: Returns SecretKeySpec instead of SecretKey
    public static SecretKeySpec getKeyFromPassword(String password) throws Exception {
        byte[] keyBytes = password.getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        keyBytes = sha.digest(keyBytes);
        return new SecretKeySpec(keyBytes, 0, 16, ALGORITHM);
    }

    public static byte[] encrypt(byte[] data, String password) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec key = getKeyFromPassword(password);
        IvParameterSpec ivSpec = new IvParameterSpec(IV);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] encryptedData, String password) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec key = getKeyFromPassword(password);
        IvParameterSpec ivSpec = new IvParameterSpec(IV);
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        return cipher.doFinal(encryptedData);
    }
}
