package in.dataman.controller;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.dataman.util.AESUtil;
import in.dataman.util.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.HashMap;

@RestController
public class KeyControllers {

    @Autowired
    private AESUtil keyGenerator;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/test-encryption")
    public String testEncryption() throws Exception {
        String message = "jai shree krishna!";

        // Encrypt the message
        String encryptedMessage = keyGenerator.encryptMessage(message);

        // Decrypt the message
        String decryptedMessage = keyGenerator.decryptResponse(encryptedMessage);

        return "Encrypted: " + encryptedMessage + "\nDecrypted: " + decryptedMessage;
    }

    @GetMapping("/test-response")
    public ResponseEntity<?> sendJson() throws Exception {
        String message = "jai shree krishna!";

        // Encrypt the message
        String encryptedMessage = keyGenerator.encryptMessage(message);

        // Decrypt the message
        String decryptedMessage = keyGenerator.decryptResponse(encryptedMessage);
        HashMap<String, String> response = new HashMap<>();
        response.put("encryptedMessage", encryptedMessage);
        response.put("decryptedMessage", decryptedMessage);

        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to encrypt a JSON request.
     *
     * @param jsonNode The input JSON payload.
     * @return The encrypted cipher as a response.
     */
    @PostMapping("/encrypt-request")
    public ResponseEntity<?> encryptJson(@RequestBody JsonNode jsonNode) {
        try {
            // Convert JsonNode to a string
            String jsonString = objectMapper.writeValueAsString(jsonNode);

            // Encrypt the JSON string
            String encryptedMessage = keyGenerator.encryptMessage(jsonString);

            HashMap<String, String> response = new HashMap<>();

            response.put("encryptedResponse", encryptedMessage);

            // Return the encrypted string
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error encrypting JSON: " + e.getMessage());
        }
    }

    /**
     * Endpoint to decrypt an encrypted response from a JSON request.
     *
     * @param request The JSON object containing the "encryptedResponse" field.
     * @return The original JSON as a response.
     */
    @PostMapping("/decrypt-request")
    public ResponseEntity<JsonNode> decryptJson(@RequestBody JsonNode request) {
        try {
            // Extract the "encryptedResponse" field from the request
            String encryptedMessage = request.get("encryptedResponse").asText();

            // Decrypt the encrypted message
            String decryptedMessage = keyGenerator.decryptResponse(encryptedMessage);

            // Convert the decrypted string back to a JsonNode
            JsonNode jsonNode = objectMapper.readTree(decryptedMessage);



            // Return the JSON as the response
            return ResponseEntity.ok(jsonNode);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(objectMapper.createObjectNode().put("error", "Error decrypting message: " + e.getMessage()));
        }
    }
}

