package com.jmjbrothers.jobportal.service;

import com.jmjbrothers.jobportal.repository.JobRepository;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


}
