package com.jmjbrothers.jobportal.service;

import com.jmjbrothers.jobportal.dto.EditSeekerInfoDto;
import com.jmjbrothers.jobportal.dto.PasswordChangeRequestDto;
import com.jmjbrothers.jobportal.dto.SeekerRegisterRequestDto;
import com.jmjbrothers.jobportal.model.Company;
import com.jmjbrothers.jobportal.model.Seeker;
import com.jmjbrothers.jobportal.repository.CompanyRepository;
import com.jmjbrothers.jobportal.repository.SeekerRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Transactional
    public Seeker registerNewSeeker(SeekerRegisterRequestDto request) {

        Seeker existSeeker = seekerRepository.findByEmail(request.getEmail()).orElse(null);
        if (existSeeker != null) {
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
        seeker.setAddress(request.getAddress());
        seeker.setEducation(request.getEducation());
        seeker.setDesignation(request.getDesignation());
        seeker.setJobExperience(request.getJobExperience());


        return seekerRepository.save(seeker);
    }


    @Transactional
    public Seeker editSeekerInformation(EditSeekerInfoDto request) {

        Seeker seeker = seekerRepository.findById(request.getId()).orElse(null);
        if (seeker == null) {
            throw new RuntimeException("Seeker does not exist by id: " + request.getId());
        }

        seeker.setName(request.getName());
        seeker.setAddress(request.getAddress());
        seeker.setPhone(request.getPhone());
        seeker.setEducation(request.getEducation());
        seeker.setDesignation(request.getDesignation());
        seeker.setJobExperience(request.getJobExperience());

        return seekerRepository.save(seeker);
    }

    @Transactional
    public void changePassword(PasswordChangeRequestDto request) {

        Seeker seeker = seekerRepository.findById(request.getId())
                .orElseThrow(() -> new UsernameNotFoundException("Job Seeker not found by id: " + request.getId()));

        if (!passwordEncoder.matches(request.getCurrentPassword(), seeker.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect");
        }

        seeker.setPassword(passwordEncoder.encode(request.getNewPassword()));
        seekerRepository.save(seeker);
    }

    @Transactional
    public Seeker getProfileInformation(Long seekerId) {

        Seeker seeker = seekerRepository.findById(seekerId).orElse(null);
        if (seeker == null) {
            throw new RuntimeException("Seeker does not exist by id: " + seekerId);
        }
        return seeker;
    }

    @Transactional
    public List<Seeker> getAllSeekers() {
        return seekerRepository.findAll();
    }

    @Transactional
    public void deleteSeekerById(Long id) {
        seekerRepository.deleteById(id);
    }
}
