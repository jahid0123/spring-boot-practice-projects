package com.jmjbrothers.realestateportal.controller;


import com.jmjbrothers.realestateportal.dto.PostedPropertyResponseDto;
import com.jmjbrothers.realestateportal.dto.PropertyPostUpdateDto;
import com.jmjbrothers.realestateportal.model.PostPackage;
import com.jmjbrothers.realestateportal.model.PropertyPost;
import com.jmjbrothers.realestateportal.model.User;
import com.jmjbrothers.realestateportal.service.PostPackageService;
import com.jmjbrothers.realestateportal.service.PropertyPostService;
import com.jmjbrothers.realestateportal.service.UserInfoDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {


    private final UserInfoDetailsService userInfoDetailsService;
    private final PostPackageService packageService;
    private final PropertyPostService propertyPostService;
    private final PostPackageService creditPackageService;


    //Get all user access control by Admin
    @GetMapping("/alluser")
    public List<User> getAllUser() {
        return userInfoDetailsService.getAllUser();
    }

    //Delete user by id access control by admin
    @DeleteMapping("/delete/user")
    public String deleteUserById(@RequestParam Long id) {
        return userInfoDetailsService.deleteUserById(id);
    }

    //Credit Package Creation by the Admin
    @PostMapping("/add/package")
    public ResponseEntity<?> addCreditPackage(@RequestBody PostPackage cPackage) {
        PostPackage creditPackage = packageService.addCreditPackage(cPackage);
        return new ResponseEntity<>(creditPackage, HttpStatus.CREATED);
    }

    @PutMapping("/edit/package")
    public ResponseEntity<?> updatePackage(@RequestBody PostPackage editBody){

        PostPackage creditPackage = creditPackageService.updatePackage(editBody);

        return new ResponseEntity<>(creditPackage, HttpStatus.OK);
    }

    @DeleteMapping("/delete/package")
    public ResponseEntity<?> deletePackage(@RequestParam Long id){

        String deletePackage = creditPackageService.deletePackageById(id);

        return new ResponseEntity<>(deletePackage, HttpStatus.OK);
    }


    @GetMapping("/all/posted/property")
    public ResponseEntity<?> getAllPostedProperty() {

        List<PostedPropertyResponseDto> allProperty = propertyPostService.allPostedProperty();
        return ResponseEntity.ok(allProperty);
    }

    @PutMapping("/update/posted/property")
    public ResponseEntity<?> updatePostedProperty(@RequestBody PropertyPostUpdateDto post) {

        PropertyPost allProperty = propertyPostService.updatePostedProperty(post);
        return ResponseEntity.ok(allProperty);
    }

}

