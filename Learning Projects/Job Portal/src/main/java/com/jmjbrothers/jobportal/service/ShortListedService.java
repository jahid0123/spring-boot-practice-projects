package com.jmjbrothers.jobportal.service;


import com.jmjbrothers.jobportal.dto.CreateShortlistDto;
import com.jmjbrothers.jobportal.model.Company;
import com.jmjbrothers.jobportal.model.JobApply;
import com.jmjbrothers.jobportal.model.ShortListed;
import com.jmjbrothers.jobportal.repository.CompanyRepository;
import com.jmjbrothers.jobportal.repository.JobApplyRepository;
import com.jmjbrothers.jobportal.repository.ShortListedRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShortListedService {

    private final ShortListedRepository shortListedRepository;
    private final JobApplyRepository jobApplyRepository;
    private final CompanyRepository companyRepository;

    public ShortListedService(ShortListedRepository shortListedRepository, JobApplyRepository jobApplyRepository, CompanyRepository companyRepository) {
        this.shortListedRepository = shortListedRepository;
        this.jobApplyRepository = jobApplyRepository;
        this.companyRepository = companyRepository;
    }

    @Transactional
    public ShortListed shortlistCandidate(CreateShortlistDto createShortlistDto) {



        if (shortListedRepository.existsByJobApply_IdAndCompany_Id(createShortlistDto.getJobApplyId(), createShortlistDto.getCompanyId())) {
            throw new RuntimeException("This candidate is already shortlisted.");
        }

        JobApply jobApply = jobApplyRepository.findById(createShortlistDto.getJobApplyId())
                .orElseThrow(() -> new RuntimeException("Job Application not found"));

        Company company = companyRepository.findById(createShortlistDto.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        ShortListed shortListed = new ShortListed();
        shortListed.setJobApply(jobApply);
        shortListed.setCompany(company);
        shortListed.setInterviewDate(LocalDateTime.parse(createShortlistDto.getInterviewDate()));

        return shortListedRepository.save(shortListed);
    }

    @Transactional
    public List<ShortListed> getShortlistedByCompany(Long companyId) {
        return shortListedRepository.findByCompany_Id(companyId);
    }

    @Transactional
    public List<ShortListed> getShortlistedByJob(Long jobId) {
        return shortListedRepository.findByJobApply_Job_Id(jobId);
    }

    @Transactional
    public void deleteShortlisted(Long id) {
        shortListedRepository.deleteById(id);
    }

    @Transactional
    public List<ShortListed> getShortlistedBySeeker(Long seekerId) {
        return shortListedRepository.findByJobApply_JobSeeker_Id(seekerId);
    }
}