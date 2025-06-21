package com.jmjbrothers.jobportal.controller;

import com.jmjbrothers.jobportal.dto.EditSeekerInfoDto;
import com.jmjbrothers.jobportal.dto.JobApplyDto;
import com.jmjbrothers.jobportal.dto.PasswordChangeRequestDto;
import com.jmjbrothers.jobportal.model.Job;
import com.jmjbrothers.jobportal.model.JobApply;
import com.jmjbrothers.jobportal.model.Seeker;
import com.jmjbrothers.jobportal.model.ShortListed;
import com.jmjbrothers.jobportal.service.JobApplyService;
import com.jmjbrothers.jobportal.service.JobService;
import com.jmjbrothers.jobportal.service.SeekerService;
import com.jmjbrothers.jobportal.service.ShortListedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/seeker")
public class SeekerController {

    private final JobApplyService jobApplyService;
    private final JobService jobService;
    private final SeekerService seekerService;
    private final ShortListedService shortListedService;

    public SeekerController(JobApplyService jobApplyService, JobService jobService, SeekerService seekerService, ShortListedService shortListedService) {
        this.jobApplyService = jobApplyService;
        this.jobService = jobService;
        this.seekerService = seekerService;
        this.shortListedService = shortListedService;
    }

    //Get All posted job
    @GetMapping("/get/all/posted/job")
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

    //Edit seeker information
    @PutMapping("/edit/seeker/information")
    public ResponseEntity<?> editSeekerInformation(@RequestBody EditSeekerInfoDto editSeekerInfoDto){
        Seeker seeker =  seekerService.editSeekerInformation(editSeekerInfoDto);
        return new ResponseEntity<>(seeker, HttpStatus.OK);
    }


    //Change seeker password
    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeRequestDto request) {
        if (request.getCurrentPassword() == null || request.getNewPassword() == null) {
            return ResponseEntity.badRequest().body("Password fields cannot be null");
        }

        seekerService.changePassword(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/profile/information")
    public ResponseEntity<?> getProfileInformation(@RequestParam Long seekerId){

        Seeker seeker = seekerService.getProfileInformation(seekerId);

        return new ResponseEntity<>(seeker, HttpStatus.OK);
    }

    @GetMapping("/my/applied/jobs")
    public ResponseEntity<?> getAppliedJobs(@RequestParam Long seekerId){

        List<JobApply> jobApplies = jobApplyService.getAppliedJobs(seekerId);

        return new ResponseEntity<>(jobApplies, HttpStatus.OK);
    }

    @GetMapping("/my/shortlisted/applied/job")
    public ResponseEntity<?> getBySeeker(@RequestParam Long seekerId) {

        List<ShortListed> shortListedJobList = shortListedService.getShortlistedBySeeker(seekerId);

        return new ResponseEntity<>(shortListedJobList, HttpStatus.OK);
    }

}
