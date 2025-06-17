package com.jmjbrothers.jobportal.controller;

import com.jmjbrothers.jobportal.dto.EditCompanyInfoDto;
import com.jmjbrothers.jobportal.dto.EditSeekerInfoDto;
import com.jmjbrothers.jobportal.dto.JobPostDto;
import com.jmjbrothers.jobportal.dto.PasswordChangeRequestDto;
import com.jmjbrothers.jobportal.model.Company;
import com.jmjbrothers.jobportal.model.Job;
import com.jmjbrothers.jobportal.model.JobApply;
import com.jmjbrothers.jobportal.model.Seeker;
import com.jmjbrothers.jobportal.service.CompanyService;
import com.jmjbrothers.jobportal.service.JobApplyService;
import com.jmjbrothers.jobportal.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/company")
public class CompanyController {

    private final JobService jobService;
    private final JobApplyService jobApplyService;
    private final CompanyService companyService;


    public CompanyController(JobService jobService, JobApplyService jobApplyService, CompanyService companyService) {
        this.jobService = jobService;
        this.jobApplyService = jobApplyService;
        this.companyService = companyService;
    }

    @PostMapping("/job/post")
    public ResponseEntity<?> jobPost(@RequestBody JobPostDto jobPostDto){
        Job job = jobService.jobPost(jobPostDto);

        return new ResponseEntity<>(job, HttpStatus.CREATED);

    }

    @GetMapping("/get/my/all/posted/job")
    public ResponseEntity<?> getMyPostedJobs(@RequestParam Long id){

        List<Job> companyJobs = jobService.getMyPostedJob(id);

        return new ResponseEntity<>(companyJobs, HttpStatus.OK);
    }

    @GetMapping("/get/my/all/posted/job/apply")
    public ResponseEntity<?> getMyPostedJobsApplication(@RequestParam Long jobId) {
        List<Seeker> seekers = jobApplyService.getSeekersByJobId(jobId);
        return ResponseEntity.ok(seekers);
    }

    //Edit company information
    @PutMapping("/edit/company/information")
    public ResponseEntity<?> editSeekerInformation(@RequestBody EditCompanyInfoDto editCompanyInfoDto){
        Company company =  companyService.editCompanyInformation(editCompanyInfoDto);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }


    //Change company password
    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeRequestDto request) {
        if (request.getCurrentPassword() == null || request.getNewPassword() == null) {
            return ResponseEntity.badRequest().body("Password fields cannot be null");
        }

        companyService.changePassword(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
