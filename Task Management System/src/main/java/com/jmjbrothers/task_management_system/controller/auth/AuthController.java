package com.jmjbrothers.task_management_system.controller.auth;

import com.jmjbrothers.task_management_system.dto.SignupRequest;
import com.jmjbrothers.task_management_system.dto.UserDTO;
import com.jmjbrothers.task_management_system.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
public class AuthController {

    private AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody SignupRequest signupRequest) {

        if (authService.hasUserWithEmail(signupRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User already exist with this email!");
        }
        UserDTO createUserDTO = authService.signupUser(signupRequest);
        if (createUserDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not created!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createUserDTO);


    }
}
