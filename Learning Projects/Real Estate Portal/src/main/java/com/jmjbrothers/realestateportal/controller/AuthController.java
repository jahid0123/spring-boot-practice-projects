package com.jmjbrothers.realestateportal.controller;


import com.jmjbrothers.realestateportal.dto.LoginRequest;
import com.jmjbrothers.realestateportal.dto.RegisterRequest;
import com.jmjbrothers.realestateportal.model.User;
import com.jmjbrothers.realestateportal.model.UserInfoDetails;
import com.jmjbrothers.realestateportal.service.JwtService;
import com.jmjbrothers.realestateportal.service.UserInfoDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final UserInfoDetailsService userInfoDetailsService;



    @PostMapping("/seller/register")
    public ResponseEntity<?> registerSeller(@RequestBody RegisterRequest request) {
        User user = userInfoDetailsService.registerNewSeller(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @PostMapping("/buyer/register")
    public ResponseEntity<?> registerBuyer(@RequestBody RegisterRequest request) {
        User user = userInfoDetailsService.registerNewBuyer(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @PostMapping("/role/admin/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        User user = userInfoDetailsService.registerNewUser(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
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
                responseData.put("token", jwtToken);
                responseData.put("id", user.getId());
                responseData.put("role", user.getRole());
                //responseData.put("token_type", "Bearer");


//                Map<String, Object> userData = new HashMap<>();
//                userData.put("Name", user.getName());
//                userData.put("Email", user.getEmail());
//                userData.put("Phone", user.getPhone());
//                userData.put("Role", user.getRole());
//
//                responseData.put("user", userData);

                return ResponseEntity.ok(responseData);
            }else {
                throw new UsernameNotFoundException("Invalid User or password");
            }
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }


    @GetMapping("/images/{filename:.+}")
    public ResponseEntity<Resource> serveImage(@PathVariable String filename) {
        Path imagePath = Paths.get("uploads").resolve(filename).normalize();

        try {
            Resource resource = new UrlResource(imagePath.toUri());
            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) // or detect dynamically
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
