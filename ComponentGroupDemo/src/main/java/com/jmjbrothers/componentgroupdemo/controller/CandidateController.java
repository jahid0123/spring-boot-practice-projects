package com.jmjbrothers.componentgroupdemo.controller;

import com.jmjbrothers.componentgroupdemo.entity.Candidate;
import com.jmjbrothers.componentgroupdemo.entity.Person;
import com.jmjbrothers.componentgroupdemo.service.AddressService;
import com.jmjbrothers.componentgroupdemo.service.CandidateService;
import com.jmjbrothers.componentgroupdemo.service.EmploymentDetailsService;
import com.jmjbrothers.componentgroupdemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private PersonService personService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private EmploymentDetailsService employmentDetailsService;
    @Autowired
    private CandidateService candidateService;

    @GetMapping("/list")
    public ResponseEntity<?> test() {
        List<Candidate> allCandidate = candidateService.candidateList();
        return new ResponseEntity<>(allCandidate, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Candidate candidate) {
        Candidate savedCandidate = candidateService.save(candidate);
        return new ResponseEntity<>(savedCandidate, HttpStatus.OK);
    }

    @PostMapping("/person")
    public ResponseEntity<?> savePerson(@RequestBody Person person) {
        Person savedPerson = personService.save(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.OK);
    }

}
