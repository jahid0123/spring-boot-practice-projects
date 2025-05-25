package com.jmjbrothers.jobportal.controller;

import com.jmjbrothers.jobportal.model.PostPackage;
import com.jmjbrothers.jobportal.service.PostPackageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin")
public class UserController {

    private final PostPackageService postPackageService;

    public UserController(PostPackageService postPackageService) {
        this.postPackageService = postPackageService;
    }

    @PostMapping("/create/post/package")
    public ResponseEntity<?> createPostPackage(@RequestBody PostPackage postPackage){

        PostPackage createPackage = postPackageService.createPostPackage(postPackage);

        return new ResponseEntity<>(createPackage, HttpStatus.CREATED);
    }
}
