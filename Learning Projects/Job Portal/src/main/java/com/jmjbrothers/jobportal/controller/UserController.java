package com.jmjbrothers.jobportal.controller;

import com.jmjbrothers.jobportal.dto.EditSeekerInfoDto;
import com.jmjbrothers.jobportal.dto.EditUserInfoDto;
import com.jmjbrothers.jobportal.dto.PasswordChangeRequestDto;
import com.jmjbrothers.jobportal.model.*;
import com.jmjbrothers.jobportal.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin")
public class UserController {

    private final PackagesService packageService;
    private final SeekerService seekerService;
    private final UserService userService;
    private final JobService jobService;
    private final JobApplyService jobApplyService;
    private final CompanyService companyService;
    private final BillService billService;

    public UserController(PackagesService packageService, SeekerService seekerService, UserService userService, JobService jobService, JobApplyService jobApplyService, CompanyService companyService, BillService billService) {
        this.packageService = packageService;
        this.seekerService = seekerService;
        this.userService = userService;
        this.jobService = jobService;
        this.jobApplyService = jobApplyService;
        this.companyService = companyService;
        this.billService = billService;
    }

    //Create packages
    @PostMapping("/create/package")
    public ResponseEntity<?> createPostPackage(@RequestBody Packages packages){
        Packages packages1 = packageService.createPackage(packages);
        return new ResponseEntity<>(packages1, HttpStatus.CREATED);
    }

    //Edit packages
    @PutMapping("/edit/package")
    public ResponseEntity<?> editPackage(@RequestBody Packages packages){
        Packages packages1 = packageService.editPackage(packages);
        return new ResponseEntity<>(packages1, HttpStatus.ACCEPTED);
    }

    //Edit user information
    @PutMapping("/edit/user/information")
    public ResponseEntity<?> editUserInformation(@RequestBody EditUserInfoDto editUserInfoDto){
        User user =  userService.editUserInformation(editUserInfoDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //Edit user information
    @DeleteMapping("/delete/packagebyid")
    public ResponseEntity<?> deletePackage(@RequestParam Long id){
        packageService.deletePackage(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //Change seeker password
    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeRequestDto request) {
        if (request.getCurrentPassword() == null || request.getNewPassword() == null) {
            return ResponseEntity.badRequest().body("Password fields cannot be null");
        }

        userService.changePassword(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/profile/information")
    public ResponseEntity<?> getProfileInformation(@RequestParam Long userId){
        User user = userService.getProfileInformation(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping("/all/jobs")
    public ResponseEntity<?> getJobs() {
        List<Job> jobList = jobService.getAllPostedJob();
        return new ResponseEntity<>(jobList, HttpStatus.OK);
    }

    @GetMapping("/all/applies")
    public ResponseEntity<?> getAllApplies(){
        List<JobApply> jobApplies = jobApplyService.getAllApplies();
        return new ResponseEntity<>(jobApplies, HttpStatus.OK);
    }

    @GetMapping("/all/packages")
    public ResponseEntity<?> getAllPackages(){
        List<Packages> allPackages = packageService.getAllPackages();
        return new ResponseEntity<>(allPackages, HttpStatus.OK);
    }

    @GetMapping("/all/companies")
    public ResponseEntity<?> getAllCompanies(){
        List<Company> companies = companyService.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/all/seekers")
    public ResponseEntity<?> getAllSeekers(){
        List<Seeker> seekers = seekerService.getAllSeekers();
        return new ResponseEntity<>(seekers, HttpStatus.OK);
    }

    @GetMapping("/all/users")
    public ResponseEntity<?> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/delete/job")
    public ResponseEntity<?> deleteJob(@RequestParam Long id){
        jobService.deleteJobById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/application")
    public ResponseEntity<?> deleteApplication(@RequestParam Long id){
        jobApplyService.deleteJobApplyById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all/bills")
    public ResponseEntity<?> getAllBills(){
        List<Bill> bills = billService.getAllBills();
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    @DeleteMapping("/delete/seekerbyid")
    public ResponseEntity<?> deleteSeeker(@RequestParam Long id){
        seekerService.deleteSeekerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/companybyid")
    public ResponseEntity<?> deleteCompany(@RequestParam Long id){
        companyService.deleteCompanyById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/adminbyid")
    public ResponseEntity<?> deleteAdmin(@RequestParam Long id){
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
