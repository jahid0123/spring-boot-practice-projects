package com.jmjbrothers.spring.securtiy.authentication.controller;

import com.jmjbrothers.spring.securtiy.authentication.dto.PropertyDto;
import com.jmjbrothers.spring.securtiy.authentication.model.CreditPackage;
import com.jmjbrothers.spring.securtiy.authentication.model.Property;
import com.jmjbrothers.spring.securtiy.authentication.model.User;
import com.jmjbrothers.spring.securtiy.authentication.service.CreditPackageService;
import com.jmjbrothers.spring.securtiy.authentication.service.PropertyService;
import com.jmjbrothers.spring.securtiy.authentication.service.UserInfoDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {


    private final UserInfoDetailsService userInfoDetailsService;
    private final CreditPackageService packageService;

    private final PropertyService propertyService;


    @PostMapping("/create/property/admin")
    public ResponseEntity<?> createProperty(@RequestBody PropertyDto propertyDto ){
        Property addProperty = propertyService.createProperty(propertyDto);

        return new ResponseEntity<>(addProperty, HttpStatus.CREATED);
    }


    //Get all user access control by Admin
    @GetMapping("/alluser")
    public List<User> getAllUser() {
        return userInfoDetailsService.getAllUser();
    }

    //Delete user by id access control by admin
    @DeleteMapping("/delete")
    public String deleteUserById(@RequestParam Long id) {
        return userInfoDetailsService.deleteUserById(id);
    }

    //Credit Package Creation by the Admin
    @PostMapping("/add/credit/package")
    public ResponseEntity<?> addCreditPackage(@RequestBody CreditPackage cPackage) {
        CreditPackage creditPackage = packageService.addCreditPackage(cPackage);
        return new ResponseEntity<>(creditPackage, HttpStatus.CREATED);
    }

}
