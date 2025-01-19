package in.dataman.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import in.dataman.util.ConUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConTestController {


    @Autowired
    ConUtil conUtil;

    @GetMapping("/get-code")
    public String welcome() throws JsonProcessingException {

        conUtil.converDtosToJosnNode();
        conUtil.convertJsonNodeToDto();
        System.out.println("jai shree krishna");
        System.out.println("Hare krishna hare krishna krishna krishna Hare Hare, Hare Ram Hare Ram Ram Ram Hare Hare");
        return "jai shri Ram...!";
    }

}
