package in.dataman.controller;

import com.fasterxml.jackson.databind.JsonNode;
import in.dataman.entity.Candidate;
import in.dataman.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CandidateController {

    @Autowired
    private CandidateService candidateService;


    @PostMapping("/save-candidate-details")
    public ResponseEntity<?> saveCandidateDetail(@RequestBody JsonNode payload){
        Candidate candidate = new Candidate();
        candidate.setAge(payload.get("age").asInt());
        candidate.setName(payload.get("name").asText());
        candidate.setSalary(payload.get("salary").asDouble());

        return ResponseEntity.ok(candidateService.addCandidateDetails(candidate));
    }

    @GetMapping("/get-all-candidate")
    public ResponseEntity<?>  getAllCandidate(){
        return ResponseEntity.ok(candidateService.getAllCandidateDetails());
    }

    @GetMapping("/get-candidate-by-id")
    public ResponseEntity<?> getCandidateById(@RequestParam Long id){
        Optional<Candidate> candidate = candidateService.findByCandidateId(id);
        if(candidate.isEmpty()){
            return ResponseEntity.ok("SPECIFIED CANDIDATE NOT FOUND");
        }else{
            return ResponseEntity.ok(candidate);
        }
    }



}
