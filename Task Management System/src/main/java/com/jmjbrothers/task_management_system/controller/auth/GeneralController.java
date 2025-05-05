package com.jmjbrothers.task_management_system.controller.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/secure")
public class GeneralController {


    @GetMapping("/welcome")
    public String welcome(){
        return "Hi! You are got it. Surprised!";
    }
}
