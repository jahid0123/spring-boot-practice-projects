package com.jmjbrothers.task_management_system.controller.auth;

import com.jmjbrothers.task_management_system.dto.*;
import com.jmjbrothers.task_management_system.entities.User;
import com.jmjbrothers.task_management_system.entities.UserInfoDetails;
import com.jmjbrothers.task_management_system.repository.UserRepository;
import com.jmjbrothers.task_management_system.service.UserServiceImpl;
import com.jmjbrothers.task_management_system.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private  UserServiceImpl authService;
    @Autowired
    private  JwtUtil jwtUtil;
    @Autowired
    private  AuthenticationManager authenticationManager;



    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {
        if (authService.hasUserWithEmail(signupRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists with this email");
        }
        SignupResponse createdDTO = authService.signupUser(signupRequest);
        if (createdDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User creation failed");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDTO);
    }

    @PostMapping("/login")
    public LoginResponse login(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody LoginRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(),
                            authRequest.getPassword()));
            if (authentication.isAuthenticated()) {

                final String token = jwtUtil.createToken(authRequest.getEmail());

                UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();

                User user = userInfoDetails.user();

                LoginResponse authresponse = new LoginResponse();
                authresponse.setJwt(token);
                authresponse.setUserId(user.getId());
                authresponse.setUserRole(user.getUserRole());
                return authresponse;
            } else {
                throw new UsernameNotFoundException("Invalid User or password");
            }
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Invalid User or password");
        }
    }


}