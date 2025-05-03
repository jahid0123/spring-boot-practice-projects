package com.jmjbrothers.spring.securtiy.authentication.controller;

import com.jmjbrothers.spring.securtiy.authentication.constants.Role;
import com.jmjbrothers.spring.securtiy.authentication.dto.LoginRequest;
import com.jmjbrothers.spring.securtiy.authentication.dto.RegisterRequest;
import com.jmjbrothers.spring.securtiy.authentication.model.User;
import com.jmjbrothers.spring.securtiy.authentication.model.UserInfoDetails;
import com.jmjbrothers.spring.securtiy.authentication.service.JwtService;
import com.jmjbrothers.spring.securtiy.authentication.service.UserInfoDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserInfoDetailsService userInfoDetailsService;



    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(Role.USER);

        userInfoDetailsService.registerNewUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("Account create successfully.");
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
                User user = userInfoDetails.user();

                Map<String, Object> responseData = new HashMap<>();
                responseData.put("access_token", jwtToken);
                responseData.put("token_type", "Bearer");

                Map<String, Object> userData = new HashMap<>();
                userData.put("Name", user.getName());
                userData.put("Email", user.getEmail());
                userData.put("Phone", user.getPhone());
                userData.put("Role", user.getRole());

                responseData.put("user", userData);

                return ResponseEntity.ok(responseData);
            }else {
                throw new UsernameNotFoundException("Invalid User or password");
            }
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
