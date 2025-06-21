package com.jmjbrothers.jobportal.service;

import com.jmjbrothers.jobportal.dto.CompanyRegisterRequestDto;
import com.jmjbrothers.jobportal.dto.EditCompanyInfoDto;
import com.jmjbrothers.jobportal.dto.PasswordChangeRequestDto;
import com.jmjbrothers.jobportal.model.Company;
import com.jmjbrothers.jobportal.model.Seeker;
import com.jmjbrothers.jobportal.repository.CompanyRepository;
import com.jmjbrothers.jobportal.repository.SeekerRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
        company.setPhone(request.getPhone());
        company.setAddress(request.getAddress());

        return companyRepository.save(company);
    }


    @Transactional
    public void changePassword(PasswordChangeRequestDto request) {
        Company company = companyRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Company not found by id: " + request.getId()));
        if (!passwordEncoder.matches(request.getCurrentPassword(), company.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect");
        }
        company.setPassword(passwordEncoder.encode(request.getNewPassword()));
        companyRepository.save(company);
    }


    @Transactional
    public Company editCompanyInformation(EditCompanyInfoDto editCompanyInfoDto) {
        Company company = companyRepository.findById(editCompanyInfoDto.getId()).orElse(null);
        if (company == null) {
            throw new RuntimeException("Company does not exist by id: " + editCompanyInfoDto.getId());
        }
        company.setName(editCompanyInfoDto.getName());
        company.setAddress(editCompanyInfoDto.getAddress());
        company.setBusiness(editCompanyInfoDto.getBusiness());
        company.setPhone(editCompanyInfoDto.getPhone());

        return companyRepository.save(company);
    }

    @Transactional
    public Company getProfileInformation(Long companyId) {
        Company company = companyRepository.findById(companyId).orElse(null);
        if (company == null) {
            throw new RuntimeException("Company does not exist by id: " + companyId);
        }
        return company;
    }

    @Transactional
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Transactional
    public void deleteCompanyById(Long id) {
        companyRepository.deleteById(id);
    }
}
