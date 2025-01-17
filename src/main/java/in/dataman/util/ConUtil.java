package in.dataman.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.dataman.dto.ResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class ConUtil {




    public JsonNode converDtosToJosnNode(){


        ResponseDTO obj = new ResponseDTO();

        obj.setAge(24);
        obj.setData(null);
        obj.setName("Abhay");
        obj.setGender("Male");
        obj.setPrice(12.98);



        // Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Convert the object to JsonNode
        JsonNode jsonNode = objectMapper.valueToTree(obj);

        // Print the JsonNode
        System.out.println(jsonNode.toPrettyString());

        return jsonNode;
    }

    public ResponseDTO convertJsonNodeToDto() throws JsonProcessingException {


        // Example JSON string
        String jsonString = "{\"name\":\"John\",\"age\":25}";

        // Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Convert JSON string to JsonNode
        JsonNode jsonNode = objectMapper.readTree(jsonString);

        // Convert JsonNode to Java object
        ResponseDTO myObject = objectMapper.treeToValue(jsonNode, ResponseDTO.class);

        myObject.setPrice(200.05);

        System.out.println(myObject);

        return myObject;
    }



}
