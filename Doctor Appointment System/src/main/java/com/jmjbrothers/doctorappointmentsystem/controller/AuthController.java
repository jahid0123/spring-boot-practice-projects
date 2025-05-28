package com.jmjbrothers.doctorappointmentsystem.controller;

import com.jmjbrothers.doctorappointmentsystem.dto.AuthRequest;
import com.jmjbrothers.doctorappointmentsystem.dto.AuthResponse;
import com.jmjbrothers.doctorappointmentsystem.dto.RegisterRequest;
import com.jmjbrothers.doctorappointmentsystem.service.interfaces.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}

