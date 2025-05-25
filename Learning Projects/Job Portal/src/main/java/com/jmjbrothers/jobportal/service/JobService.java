package com.jmjbrothers.jobportal.service;

import com.jmjbrothers.jobportal.dto.JobPostDto;
import com.jmjbrothers.jobportal.model.Company;
import com.jmjbrothers.jobportal.model.Job;
import com.jmjbrothers.jobportal.repository.CompanyRepository;
import com.jmjbrothers.jobportal.repository.JobRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    public JobService(JobRepository jobRepository, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }


    @Transactional
    public Job jobPost(JobPostDto jobPostDto) {
        Company company = companyRepository.findById(jobPostDto.getCompanyId()).orElseThrow(
                ()-> new RuntimeException("Company is not exists by id "+jobPostDto.getCompanyId()));

        Job job = new Job();
        job.setCompany(company);
        job.setJobTitle(jobPostDto.getJobTitle());
        job.setJobDescription(jobPostDto.getJobDescription());
        job.setJobRequirement(jobPostDto.getJobRequirement());
        job.setJobResponsibilities(jobPostDto.getJobResponsibilities());
        job.setCompensationBenefit(jobPostDto.getCompensationBenefit());
        job.setWorkPlace(jobPostDto.getWorkPlace());
        job.setEmploymentStatus(jobPostDto.getEmploymentStatus());
        job.setJobLocation(jobPostDto.getJobLocation());

        return jobRepository.save(job);
    }

    @Transactional
    public List<Job> getAllPostedJob() {
        List<Job> allJobs = jobRepository.findAll();
        return allJobs;
    }

    @Transactional
    public List<Job> getMyPostedJob(Long id) {
        List<Job> getAllJobsById = jobRepository.findAllByCompanyId(id);

        return getAllJobsById;
    }
}
