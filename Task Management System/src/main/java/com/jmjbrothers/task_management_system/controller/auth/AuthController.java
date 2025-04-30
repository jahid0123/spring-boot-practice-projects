package com.jmjbrothers.task_management_system.controller.auth;

import com.jmjbrothers.task_management_system.dto.AuthenticationRequest;
import com.jmjbrothers.task_management_system.dto.AuthenticationResponse;
import com.jmjbrothers.task_management_system.dto.SignupRequest;
import com.jmjbrothers.task_management_system.dto.UserDTO;
import com.jmjbrothers.task_management_system.entities.User;
import com.jmjbrothers.task_management_system.repository.UserRepository;
import com.jmjbrothers.task_management_system.service.UserService;
import com.jmjbrothers.task_management_system.service.auth.AuthService;
import com.jmjbrothers.task_management_system.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {
        if (authService.hasUserWithEmail(signupRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists with this email");
        }
        UserDTO createdDTO = authService.signupUser(signupRequest);
        if (createdDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User creation failed");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDTO);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            throw  new BadCredentialsException("Incorrect username or password");
        }

        final UserDetails userDetails = userService.userdetailsService()
                .loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByEmail(authenticationRequest.getEmail());
        final String jwtToken = jwtUtil.generateToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if (optionalUser.isEmpty()) {
           authenticationResponse.setJwt(jwtToken);
           authenticationResponse.setUserId(optionalUser.get().getId());
           authenticationResponse.setUserRole(optionalUser.get().getUserRole());
        }


        return authenticationResponse;
    }
}