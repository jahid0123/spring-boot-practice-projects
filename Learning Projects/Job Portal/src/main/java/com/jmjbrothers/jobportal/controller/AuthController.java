package com.jmjbrothers.jobportal.controller;

import com.jmjbrothers.jobportal.config.jwt.JwtService;
import com.jmjbrothers.jobportal.config.jwt.UserInfoDetails;
import com.jmjbrothers.jobportal.dto.CompanyRegisterRequestDto;
import com.jmjbrothers.jobportal.dto.LoginRequest;
import com.jmjbrothers.jobportal.dto.SeekerRegisterRequestDto;
import com.jmjbrothers.jobportal.dto.UserRegisterRequestDto;
import com.jmjbrothers.jobportal.commondto.PortalUser;
import com.jmjbrothers.jobportal.model.Company;
import com.jmjbrothers.jobportal.model.Seeker;
import com.jmjbrothers.jobportal.model.User;
import com.jmjbrothers.jobportal.service.CompanyService;
import com.jmjbrothers.jobportal.service.SeekerService;
import com.jmjbrothers.jobportal.service.UnifiedUserDetailsService;
import com.jmjbrothers.jobportal.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UnifiedUserDetailsService userInfoDetailsService;
    private final UserService userService;
    private final CompanyService companyService;
    private final SeekerService seekerService;


    public AuthController(JwtService jwtService, AuthenticationManager authenticationManager, UnifiedUserDetailsService userInfoDetailsService, UserService userService, CompanyService companyService, SeekerService seekerService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userInfoDetailsService = userInfoDetailsService;
        this.userService = userService;
        this.companyService = companyService;
        this.seekerService = seekerService;
    }


    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequestDto request) {

        User user = userService.registerNewUser(request);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/seeker/register")
    public ResponseEntity<?> registerSeeker(@RequestBody SeekerRegisterRequestDto request) {

        Seeker seeker = seekerService.registerNewSeeker(request);

        return new ResponseEntity<>(seeker, HttpStatus.CREATED);
    }


    @PostMapping("/company/register")
    public ResponseEntity<?> registerCompany(@RequestBody CompanyRegisterRequestDto request) {

        Company company = companyService.registerNewCompany(request);

        return new ResponseEntity<>(company, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            if (authentication.isAuthenticated()) {
                final String jwtToken = jwtService.createToken(loginRequest.getEmail());
                UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
                PortalUser user = userInfoDetails.user();

                Map<String, Object> responseData = new HashMap<>();
                responseData.put("token", jwtToken);
                responseData.put("id", user.getId());
                responseData.put("role", user.getRole());

                /*responseData.put("token_type", "Bearer");

                Map<String, Object> userData = new HashMap<>();
                userData.put("Name", user.getName());
                userData.put("Email", user.getEmail());
                userData.put("Phone", user.getPhone());
                userData.put("Role", user.getRole());

                responseData.put("user", userData);*/

                return ResponseEntity.ok(responseData);
            }else {
                throw new UsernameNotFoundException("Invalid User or password");
            }
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
