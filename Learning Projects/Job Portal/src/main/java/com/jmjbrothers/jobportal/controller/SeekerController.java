package com.jmjbrothers.jobportal.controller;

import com.jmjbrothers.jobportal.dto.JobApplyDto;
import com.jmjbrothers.jobportal.model.Job;
import com.jmjbrothers.jobportal.model.JobApply;
import com.jmjbrothers.jobportal.service.JobApplyService;
import com.jmjbrothers.jobportal.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/seeker")
public class SeekerController {

    private final JobApplyService jobApplyService;
    private final JobService jobService;

    public SeekerController(JobApplyService jobApplyService, JobService jobService) {
        this.jobApplyService = jobApplyService;
        this.jobService = jobService;
    }

    //Get All posted job
    @GetMapping("/get/posted/job")
    public ResponseEntity<?> getAllPostedJob(){
        List<Job> getAllJobs = jobService.getAllPostedJob();

        return new ResponseEntity<>(getAllJobs, HttpStatus.OK);
    }

    //Apply for the job
    @PostMapping("/apply/job")
    public ResponseEntity<?> applyJob(@RequestBody JobApplyDto jobApplyDto){

        JobApply jobApply = jobApplyService.applyJob(jobApplyDto);

        return new ResponseEntity<>(jobApply, HttpStatus.CREATED);
    }


}
