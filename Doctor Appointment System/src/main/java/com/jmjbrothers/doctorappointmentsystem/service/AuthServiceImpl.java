package com.jmjbrothers.doctorappointmentsystem.service;


import com.jmjbrothers.doctorappointmentsystem.config.JwtService;
import com.jmjbrothers.doctorappointmentsystem.constants.UserRole;
import com.jmjbrothers.doctorappointmentsystem.dto.AuthRequest;
import com.jmjbrothers.doctorappointmentsystem.dto.AuthResponse;
import com.jmjbrothers.doctorappointmentsystem.dto.RegisterRequest;
import com.jmjbrothers.doctorappointmentsystem.model.User;
import com.jmjbrothers.doctorappointmentsystem.repository.UserRepository;
import com.jmjbrothers.doctorappointmentsystem.service.interfaces.AuthService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepo, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(UserRole.valueOf(request.getRole().toUpperCase()));

        userRepo.save(user);

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token, user.getRole().name());
    }

    public AuthResponse login(AuthRequest request) {
        User user = userRepo.findByEmail(request.getEmail()).orElseThrow();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new BadCredentialsException("Invalid credentials");

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token, user.getRole().name());
    }
}

