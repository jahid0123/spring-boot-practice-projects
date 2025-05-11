package com.jmjbrothers.spring.securtiy.authentication.controller;

import com.jmjbrothers.spring.securtiy.authentication.model.CreditPackage;
import com.jmjbrothers.spring.securtiy.authentication.model.User;
import com.jmjbrothers.spring.securtiy.authentication.service.CreditPackageService;
import com.jmjbrothers.spring.securtiy.authentication.service.UserInfoDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {

    @Autowired
    private UserInfoDetailsService userInfoDetailsService;
    @Autowired
    private CreditPackageService packageService;


    @GetMapping("/alluser")
    public List<User> getAllUser() {
        return userInfoDetailsService.getAllUser();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable Long id) {
        return userInfoDetailsService.deleteUserById(id);
    }

    //Credit Package Creation by the User whose role is Admin
    @PostMapping("/add/creditpackage")
    public String addCreditPackage(@RequestBody CreditPackage cPackage) {

        return packageService.addCreditPackage(cPackage);
    }

}
