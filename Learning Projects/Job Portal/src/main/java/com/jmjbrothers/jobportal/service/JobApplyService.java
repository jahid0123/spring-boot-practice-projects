package com.jmjbrothers.jobportal.service;

import com.jmjbrothers.jobportal.repository.JobApplyRepository;
import org.springframework.stereotype.Service;

@Service
public class JobApplyService {

    private final JobApplyRepository jobApplyRepository;


    public JobApplyService(JobApplyRepository jobApplyRepository) {
        this.jobApplyRepository = jobApplyRepository;
    }
}
