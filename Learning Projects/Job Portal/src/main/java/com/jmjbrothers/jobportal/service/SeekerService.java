package com.jmjbrothers.jobportal.service;

import com.jmjbrothers.jobportal.dto.UserRegisterRequestDto;
import com.jmjbrothers.jobportal.model.Company;
import com.jmjbrothers.jobportal.model.Seeker;
import com.jmjbrothers.jobportal.repository.CompanyRepository;
import com.jmjbrothers.jobportal.repository.SeekerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SeekerService {

    private final SeekerRepository seekerRepository;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public SeekerService(SeekerRepository seekerRepository, CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
        this.seekerRepository = seekerRepository;
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Seeker registerNewSeeker(UserRegisterRequestDto request) {

        Seeker existSeeker = seekerRepository.findByEmail(request.getEmail()).orElse(null);
        if (existSeeker != null){
            throw new RuntimeException("Seeker already exist by email: " + request.getEmail());
        }

        Company existCompany = companyRepository.findByEmail(request.getEmail()).orElse(null);

        if (existCompany != null)
            throw new RuntimeException("This email already register as a company email!!");



        Seeker seeker = new Seeker();

        seeker.setName(request.getName());
        seeker.setEmail(request.getEmail());
        seeker.setPassword(passwordEncoder.encode(request.getPassword()));
        seeker.setPhone(request.getPhone());

        return seekerRepository.save(seeker);
    }
}
