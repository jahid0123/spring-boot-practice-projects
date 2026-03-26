package com.jmjbrothers.componentgroupdemo.service;

import com.jmjbrothers.componentgroupdemo.entity.Candidate;
import com.jmjbrothers.componentgroupdemo.repository.AddressRepository;
import com.jmjbrothers.componentgroupdemo.repository.CandidateRepo;
import com.jmjbrothers.componentgroupdemo.repository.EmploymentDetailsRepository;
import com.jmjbrothers.componentgroupdemo.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepo candidateRepo;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private EmploymentDetailsRepository employmentDetailsRepo;


    @Transactional
    public List<Candidate> candidateList() {
        return candidateRepo.findAll();
    }

    @Transactional
    public Candidate save(Candidate candidate) {
        return saveOrUpdate(candidate);

    }

    private Candidate saveOrUpdate(Candidate candidate) {

        Candidate eCandidate = null;

        if (candidate.getCandidateId() == null){
            eCandidate = new Candidate();
        }else {
            eCandidate = candidateRepo.findById(candidate.getCandidateId()).orElse(null);
        }

        if (eCandidate == null) {
            throw new RuntimeException("Candidate is empty......");
        }

        //assert eCandidate != null;
        return saveData(eCandidate);
    }

    private Candidate saveData(Candidate saveCandidate) {

        saveCandidate.setEmail(saveCandidate.getEmail());
        saveCandidate.setLastEducation(saveCandidate.getLastEducation());
        saveCandidate.setPhone(saveCandidate.getPhone());
        saveCandidate.setPerson(personRepository.save(saveCandidate.getPerson()));
        saveCandidate.setAddress(addressRepository.save(saveCandidate.getAddress()));
        saveCandidate.setEmploymentDetails(employmentDetailsRepo.save(saveCandidate.getEmploymentDetails()));

        return candidateRepo.save(saveCandidate);
    }
}
