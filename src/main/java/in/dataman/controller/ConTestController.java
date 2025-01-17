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
        return "jai shri Ram...!";
    }

}
