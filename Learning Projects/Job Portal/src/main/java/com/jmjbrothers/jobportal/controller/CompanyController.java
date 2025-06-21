package com.jmjbrothers.jobportal.controller;

import com.jmjbrothers.jobportal.dto.*;
import com.jmjbrothers.jobportal.model.*;
import com.jmjbrothers.jobportal.repository.CompanyPackageRepository;
import com.jmjbrothers.jobportal.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/company")
public class CompanyController {

    private final JobService jobService;
    private final JobApplyService jobApplyService;
    private final CompanyService companyService;
    private final CompanyPackageService companyPackageService;
    private final ShortListedService shortListedService;
    private final PackagesService packagesService;
    private final BillService billService;


    public CompanyController(JobService jobService, JobApplyService jobApplyService, CompanyService companyService, CompanyPackageService companyPackageService, ShortListedService shortListedService, PackagesService packagesService, BillService billService) {
        this.jobService = jobService;
        this.jobApplyService = jobApplyService;
        this.companyService = companyService;
        this.companyPackageService = companyPackageService;
        this.shortListedService = shortListedService;
        this.packagesService = packagesService;
        this.billService = billService;
    }

    @PostMapping("/job/post")
    public ResponseEntity<?> jobPost(@RequestParam Long id, @RequestBody JobPostDto jobPostDto) {
        Job job = jobService.jobPost(id, jobPostDto);
        if (job == null) {
            return new ResponseEntity<>("You didn't purchase any package or you didn't have any remaining post limit.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(job, HttpStatus.CREATED);

    }

    @PutMapping("/edit/job")
    public ResponseEntity<?> editJob(@RequestParam Long id, @RequestBody JobPostDto jobPostDto) {
        Job job = jobService.editJob(id, jobPostDto);
        if (job == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(job, HttpStatus.CREATED);

    }

    @DeleteMapping("/delete/jobbyid")
    public ResponseEntity<String> deleteJobById(@RequestParam Long id) {
        jobService.deleteJobById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get/my/all/posted/job")
    public ResponseEntity<?> getMyPostedJobs(@RequestParam Long companyId) {

        List<Job> companyJobs = jobService.getMyPostedJob(companyId);

        return new ResponseEntity<>(companyJobs, HttpStatus.OK);
    }

    @GetMapping("/get/my/all/posted/job/apply")
    public ResponseEntity<?> getMyPostedJobsApplication(@RequestParam Long jobId) {
        List<Seeker> seekers = jobApplyService.getSeekersByJobId(jobId);
        return ResponseEntity.ok(seekers);
    }

    @GetMapping("/get/my/posted/job/applies/by")
    public ResponseEntity<?> getMyPostedJobsApplicationByCompany(@RequestParam Long companyId) {
        List<JobApply> applies = jobApplyService.getAllAppliesByCompanyId(companyId);
        return ResponseEntity.ok(applies);
    }

    @GetMapping("/profile/information")
    public ResponseEntity<?> getProfileInformation(@RequestParam Long companyId) {
        Company company = companyService.getProfileInformation(companyId);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    //Edit company information
    @PutMapping("/edit/profile/information")
    public ResponseEntity<?> editSeekerInformation(@RequestBody EditCompanyInfoDto editCompanyInfoDto) {
        Company company = companyService.editCompanyInformation(editCompanyInfoDto);
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

    @GetMapping("/all/packages")
    public ResponseEntity<?> getAllPackages(){
        List<Packages> allPackages = packagesService.getAllPackages();
        return new ResponseEntity<>(allPackages, HttpStatus.OK);
    }

    @PostMapping("/purchase/packages")
    public ResponseEntity<?> purchasePackages(@RequestBody PurchasePackageDto purchasePackageDto) {

        CompanyPackage companyPackage = companyPackageService.purchasePackages(purchasePackageDto);
        return new ResponseEntity<>(companyPackage, HttpStatus.CREATED);
    }

    @GetMapping("/my/purchase/packages")
    public ResponseEntity<?> myPurchasePackages(@RequestParam Long companyId) {

        CompanyPackage companyPackage = companyPackageService.myPurchasePackages(companyId);
        return new ResponseEntity<>(companyPackage, HttpStatus.OK);
    }


    @PostMapping("/create/shortlist")
    public ResponseEntity<?> shortlist(
            @RequestBody CreateShortlistDto createShortlistDto) {
        ShortListed shortListed = shortListedService.shortlistCandidate(createShortlistDto);
        return new ResponseEntity<>(shortListed, HttpStatus.CREATED);
    }


    @GetMapping("/by-company")
    public ResponseEntity<?> getByCompany(@RequestParam Long companyId) {
        List<ShortListed> shortListed = shortListedService.getShortlistedByCompany(companyId);
        return new ResponseEntity<>(shortListed, HttpStatus.OK);
    }

    @GetMapping("/by-job")
    public ResponseEntity<?> getByJob(@RequestParam Long jobId) {
        List<ShortListed> shortListed = shortListedService.getShortlistedByJob(jobId);
        return new ResponseEntity<>(shortListed, HttpStatus.OK);
    }

    @DeleteMapping("/delete/shortlist")
    public ResponseEntity<String> deleteShortlist(@RequestParam Long id) {
        shortListedService.deleteShortlisted(id);
        return ResponseEntity.ok("Shortlisted record deleted");
    }

    @GetMapping("/get/my/bills")
    public ResponseEntity<?> getMyAllBills(@RequestParam Long companyId) {
        List<Bill> bills = billService.getMyAllBills(companyId);
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }


}
