package com.jmjbrothers.jobportal.service;

import com.jmjbrothers.jobportal.dto.JobApplyDto;
import com.jmjbrothers.jobportal.model.CompanyPackage;
import com.jmjbrothers.jobportal.model.Job;
import com.jmjbrothers.jobportal.model.JobApply;
import com.jmjbrothers.jobportal.model.Seeker;
import com.jmjbrothers.jobportal.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class JobApplyService {

    private final JobApplyRepository jobApplyRepository;
    private final SeekerRepository seekerRepository;
    private final CompanyRepository companyRepository;
    private final JobRepository jobRepository;
    private final CompanyPackageRepository companyPackageRepository;


    public JobApplyService(JobApplyRepository jobApplyRepository, SeekerRepository seekerRepository, CompanyRepository companyRepository, JobRepository jobRepository, CompanyPackageRepository companyPackageRepository) {
        this.jobApplyRepository = jobApplyRepository;
        this.seekerRepository = seekerRepository;
        this.companyRepository = companyRepository;
        this.jobRepository = jobRepository;
        this.companyPackageRepository = companyPackageRepository;
    }

    @Transactional
    public JobApply applyJob(JobApplyDto jobApplyDto) {

        Seeker seeker = seekerRepository.findById(jobApplyDto.getSeekerId()).orElseThrow(
                () -> new RuntimeException("Job seeker not found by id "+jobApplyDto.getSeekerId()));

        Job job = jobRepository.findById(jobApplyDto.getJobId()).orElseThrow(
                () -> new RuntimeException("Job not found by id "+jobApplyDto.getJobId()));

        // Prevent duplicate applications
        if (jobApplyRepository.existsByJobAndJobSeeker(job, seeker)) {
            throw new IllegalStateException("You have already applied for this job.");
        }

        JobApply jobApply = new JobApply();
        jobApply.setJobSeeker(seeker);
        jobApply.setJob(job);

        return jobApplyRepository.save(jobApply);
    }


    // Public method to get a list of Seekers by Job ID
    @Transactional
    public List<Seeker> getSeekersByJobId(Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found by id " + jobId));

        // Get the company's most powerful package (with highest applicant view limit)
        CompanyPackage companyPackage = companyPackageRepository.findByCompany_IdAndActiveTrue(job.getCompany().getId());

        if (companyPackage == null) {
            throw new RuntimeException("No active package found for company.");
        }

        Integer companyShowApplicantLimit = companyPackage.getApplicantsViewLimit();

        // Get all applications for this job
        List<JobApply> applications = jobApplyRepository.findAllByJob_Id(jobId);

        // Map applications to seekers and limit to companyShowApplicantLimit
        return applications.stream()
                .sorted(Comparator.comparing(JobApply::getId))
                .map(this::getSeeker)
                .filter(Objects::nonNull)
                .limit(companyShowApplicantLimit)
                .collect(Collectors.toList());
    }


    // Private helper to get Seeker from JobApply
    private Seeker getSeeker(JobApply jobApply) {
        return jobApply != null ? jobApply.getJobSeeker() : null;
    }

    @Transactional
    public List<JobApply> getAppliedJobs(Long seekerId) {
        Seeker seeker = seekerRepository.findById(seekerId)
                .orElseThrow(() -> new RuntimeException("Job seeker not found by id " + seekerId));
        List<JobApply> jobApplies = jobApplyRepository.findAllByJobSeeker_Id(seekerId);
        return jobApplies;
    }

    @Transactional
    public List<JobApply> getAllApplies() {
        return jobApplyRepository.findAll();
    }

    @Transactional
    public void deleteJobApplyById(Long id) {
        jobApplyRepository.deleteById(id);
    }

    @Transactional
    public List<JobApply> getAllAppliesByCompanyId(Long companyId) {
        List<JobApply> applies = jobApplyRepository.findAllByJob_Company_Id(companyId);
        return applies;
    }
}
