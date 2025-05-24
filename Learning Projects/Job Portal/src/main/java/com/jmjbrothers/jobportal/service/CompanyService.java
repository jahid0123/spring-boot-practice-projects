package com.jmjbrothers.jobportal.service;

import com.jmjbrothers.jobportal.dto.CompanyRegisterRequestDto;
import com.jmjbrothers.jobportal.model.Company;
import com.jmjbrothers.jobportal.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;


    public CompanyService(CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Company registerNewCompany(CompanyRegisterRequestDto request) {

        Company existCompany = companyRepository.findByEmail(request.getEmail()).orElse(null);

        if (existCompany != null)
            throw new RuntimeException("Company already exist by email: "+request.getEmail());

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
