package in.dataman.util;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Component;


import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

@Getter
@Component
public class KeyGenerator {

    // Getter for the private key (Optional, avoid exposing it in real-world scenarios)
    private PrivateKey privateKey;
    // Getter for the public key
    private PublicKey publicKey;

    /**
     * This method is called automatically after the server starts,
     * generating the KeyPair.
     */
    @PostConstruct
    private void initializeKeys() {
        try {
            KeyPair keyPair = generateRSAKeyPair();
            this.privateKey = keyPair.getPrivate();
            this.publicKey = keyPair.getPublic();

            // Print keys for verification (optional, do not use in production)
            System.out.println("Generated Public Key: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
            System.out.println("Generated Private Key: " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error initializing RSA keys", e);
        }
    }

    /**
     * Method to generate RSA key pair at runtime.
     *
     * @return a newly generated RSA KeyPair.
     */
    private KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // Use 2048-bit RSA keys
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * Method to decrypt an encrypted response using the private key.
     *
     * @param encryptedResponse The encrypted response in Base64.
     * @return The decrypted plain text.
     */
    public String decryptResponse(String encryptedResponse) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] decodedResponse = Base64.getDecoder().decode(encryptedResponse);
        byte[] decryptedBytes = cipher.doFinal(decodedResponse);
        return new String(decryptedBytes);
    }

    /**
     * Method to encrypt a message using the public key.
     *
     * @param message The plain text message.
     * @return The encrypted message in Base64.
     */
    public String encryptMessage(String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }


    /**
     * Encrypt a message using the provided public key.
     *
     * @param message The plain text message.
     * @return The encrypted message in Base64 format.
     * @throws Exception If encryption fails.
     */
    public String encryptWithPublicKey(String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * Decrypt a message using the provided private key.
     *
     * @param encryptedMessage The encrypted message in Base64 format.
     * @return The decrypted plain text message.
     * @throws Exception If decryption fails.
     */
    public String decryptWithPrivateKey(String encryptedMessage) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] decodedMessage = Base64.getDecoder().decode(encryptedMessage);
        byte[] decryptedBytes = cipher.doFinal(decodedMessage);
        return new String(decryptedBytes);
    }


}
