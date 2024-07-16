package SecureSnap;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.*;
import java.nio.file.Files;

public class Decryption1{
    public static void main(String[] args) throws Exception {
        decryptimage();
    }
        public static void decryptimage() throws Exception{
        try {
            // Retrieve the secret key used for encryption
            SecretKey secretKey = retrieveSecretKey();
            // Read the encrypted image file
            File encryptedFile = new File("C:\\Users\\EVERVITAL\\Desktop\\FINAL\\encrypted-image.jpg");
            byte[] encryptedBytes = new byte[(int) encryptedFile.length()];
            try (FileInputStream inputStream = new FileInputStream(encryptedFile)) {
                inputStream.read(encryptedBytes);
            }

            // Extract the IV from the encrypted file
            byte[] iv = new byte[16]; // IV length is 16 bytes for AES
            System.arraycopy(encryptedBytes, 0, iv, 0, iv.length);

            // Create cipher and initialize with key and IV
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));

            // Decrypt the image
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes, iv.length, encryptedBytes.length - iv.length);

            // Save the decrypted image to a new file
            try (FileOutputStream outputStream = new FileOutputStream("C:\\Users\\EVERVITAL\\Desktop\\FINAL\\decrypted-image.jpg")) {
                outputStream.write(decryptedBytes);
            }

            System.out.println("Decryption completed successfully.");

        } catch (NoSuchAlgorithmException | NoSuchPaddingException |
                InvalidKeyException | IOException | IllegalBlockSizeException | BadPaddingException |
                InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }

    private static SecretKey retrieveSecretKey() throws Exception {
        // Load the key from a file
        File keyFile = new File("C:\\Users\\EVERVITAL\\Desktop\\FINAL\\secret.key");
        byte[] encodedKey = Files.readAllBytes(keyFile.toPath());

        // Create a secret key from the encoded key
        SecretKey secretKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");

        return secretKey;
    }
    
}
