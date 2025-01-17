package in.dataman.service;

import in.dataman.entity.Candidate;
import in.dataman.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {


    @Autowired
    CandidateRepository candidateRepository;

    public String addCandidateDetails(Candidate candidate){
        Candidate can = candidateRepository.save(candidate);
        System.out.println(can);
        return "Candidate Detail Saved Successfully";
    }

    public List<Candidate> getAllCandidateDetails(){
        return candidateRepository.findAll();
    }

    public Optional<Candidate> findByCandidateId(Long id){
        return candidateRepository.findById(id);
    }


}
