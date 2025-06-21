package com.jmjbrothers.jobportal.service;

import com.jmjbrothers.jobportal.dto.JobPostDto;
import com.jmjbrothers.jobportal.model.Company;
import com.jmjbrothers.jobportal.model.CompanyPackage;
import com.jmjbrothers.jobportal.model.Job;
import com.jmjbrothers.jobportal.repository.CompanyPackageRepository;
import com.jmjbrothers.jobportal.repository.CompanyRepository;
import com.jmjbrothers.jobportal.repository.JobRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;
    private final CompanyPackageRepository companyPackageRepository;

    public JobService(JobRepository jobRepository, CompanyRepository companyRepository, CompanyPackageRepository companyPackageRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
        this.companyPackageRepository = companyPackageRepository;
    }


    @Transactional
    public Job jobPost(Long id, JobPostDto jobPostDto) {

        Company company = companyRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Company is not exists by id "+id));

        CompanyPackage companyPackage = companyPackageRepository.findCompanyPackageByCompany_Id(id);
        if (companyPackage != null && companyPackage.getRemainingPosts() > 0 && companyPackage.getActive() == true) {

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
            companyPackage.setRemainingPosts(companyPackage.getRemainingPosts() - 1);
            companyPackageRepository.save(companyPackage);

            return jobRepository.save(job);
        }else if (companyPackage != null && companyPackage.getRemainingPosts() == 0 && companyPackage.getActive() == true){
            throw new RuntimeException("You have reached your post limit for this company: "+company.getName()+". Please purchase package to increase your post limit.");
        } else if (companyPackage == null) {
            throw new RuntimeException("Company does not have any package. Please purchase package to post job.");
        }else {
            return null;
        }
    }

    @Transactional
    public List<Job> getAllPostedJob() {
        List<Job> allJobs = jobRepository.findAll();
        return allJobs;
    }

    @Transactional
    public List<Job> getMyPostedJob(Long companyId) {
        List<Job> getAllJobsByCompanyId = jobRepository.findAllByCompanyId(companyId);

        return getAllJobsByCompanyId;
    }

    @Transactional
    public void deleteJobById(Long id) {
        jobRepository.deleteById(id);
    }

    public Job editJob(Long id, JobPostDto jobPostDto) {
        Job job = jobRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Job not found by id "+id));
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
}
