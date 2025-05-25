package com.jmjbrothers.jobportal.service;

import com.jmjbrothers.jobportal.dto.CompanyRegisterRequestDto;
import com.jmjbrothers.jobportal.model.Company;
import com.jmjbrothers.jobportal.model.Seeker;
import com.jmjbrothers.jobportal.repository.CompanyRepository;
import com.jmjbrothers.jobportal.repository.SeekerRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final SeekerRepository seekerRepository;
    private final PasswordEncoder passwordEncoder;


    public CompanyService(CompanyRepository companyRepository, SeekerRepository seekerRepository, PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.seekerRepository = seekerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Company registerNewCompany(CompanyRegisterRequestDto request) {

        Company existCompany = companyRepository.findByEmail(request.getEmail()).orElse(null);

        if (existCompany != null)
            throw new RuntimeException("Company already exist by email: "+request.getEmail());

        Seeker existSeeker = seekerRepository.findByEmail(request.getEmail()).orElse(null);
        if (existSeeker != null){
            throw new RuntimeException("This email already register as a Job Seeker email!");
        }

        Company company = new Company();
        company.setName(request.getName());
        company.setEmail(request.getEmail());
        company.setPassword(passwordEncoder.encode(request.getPassword()));
        company.setBusiness(request.getBusiness());
        company.setContact(request.getPhone());
        company.setAddress(request.getAddress());

        return companyRepository.save(company);
    }
}
