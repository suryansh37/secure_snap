package SecureSnap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Encryption1 {

    public static void main(String[] args) {
        encryptImage();
        
    }
    public static void encryptImage(){
        try {
            // Generate a secure AES key.
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256); // You can adjust the key size (128, 192, or 256)
            SecretKey key = keyGenerator.generateKey();

            // Save the key to a file
            saveKeyToFile(key, "C:\\Users\\EVERVITAL\\Desktop\\FINAL\\secret.key");

            // Generate a random IV for encryption.
            SecureRandom secureRandom = new SecureRandom();
            byte[] iv = new byte[16]; // AES block size is 128 bits (16 bytes)
            secureRandom.nextBytes(iv);

            // Create the cipher.
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // Initialize the cipher with the key and the initialization vector.
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));

            // Encrypt the image.
            File image = new File("C:\\Users\\EVERVITAL\\Desktop\\FINAL\\sample.png");
            FileInputStream is = new FileInputStream(image);
            byte[] ciphertext = cipher.doFinal(is.readAllBytes());
            is.close();

            // Write the IV and encrypted image to a file.
            File encryptedImage = new File("C:\\Users\\EVERVITAL\\Desktop\\FINAL\\encrypted-image.jpg");
            try (FileOutputStream os = new FileOutputStream(encryptedImage)) {
                os.write(iv);
                os.write(ciphertext);
            }

            System.out.println("Encryption completed successfully.");

        } catch (NoSuchAlgorithmException | NoSuchPaddingException |
                InvalidKeyException | IOException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }
    
    private static void saveKeyToFile(SecretKey key, String filePath) throws IOException {
        byte[] encodedKey = key.getEncoded();
        Path keyPath = Path.of(filePath);
        Files.write(keyPath, encodedKey);
    }
}
