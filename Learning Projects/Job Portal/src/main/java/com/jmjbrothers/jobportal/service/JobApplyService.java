package com.jmjbrothers.jobportal.service;

import com.jmjbrothers.jobportal.dto.JobApplyDto;
import com.jmjbrothers.jobportal.model.Job;
import com.jmjbrothers.jobportal.model.JobApply;
import com.jmjbrothers.jobportal.model.Seeker;
import com.jmjbrothers.jobportal.repository.CompanyRepository;
import com.jmjbrothers.jobportal.repository.JobApplyRepository;
import com.jmjbrothers.jobportal.repository.JobRepository;
import com.jmjbrothers.jobportal.repository.SeekerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class JobApplyService {

    private final JobApplyRepository jobApplyRepository;
    private final SeekerRepository seekerRepository;
    private final CompanyRepository companyRepository;
    private final JobRepository jobRepository;


    public JobApplyService(JobApplyRepository jobApplyRepository, SeekerRepository seekerRepository, CompanyRepository companyRepository, JobRepository jobRepository) {
        this.jobApplyRepository = jobApplyRepository;
        this.seekerRepository = seekerRepository;
        this.companyRepository = companyRepository;
        this.jobRepository = jobRepository;
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


    // Public method to get list of Seekers by Job ID
    public List<Seeker> getSeekersByJobId(Long jobId) {
        List<JobApply> applications = jobApplyRepository.findAllByJob_Id(jobId);
        return applications.stream()
                .map(this::getSeeker)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    // Private helper to get Seeker from JobApply
    private Seeker getSeeker(JobApply jobApply) {
        return jobApply != null ? jobApply.getJobSeeker() : null;
    }

//    @Transactional
//    public List<Seeker> getMyPostedJobApplies(Long id) {
//
//        List<JobApply> jobApplies = jobApplyRepository.findAllByJob_Id(id);
//
//        return jobApplies.stream().map(JobApply::getJobSeeker).collect(Collectors.toList());
//    }
//
//    private Seeker getJobSeeker(JobApply applyJob) {
//        return applyJob.getJobSeeker();
//    }


//    @Transactional
//    public List<JobApply> getMyPostedJobApplies(Long companyId) {
//
//        List<JobApply> jobApplyedList = jobApplyRepository.findAllByCompanyId(companyId);
//
//        return jobApplyedList;
//    }
}
