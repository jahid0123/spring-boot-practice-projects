package com.jmjbrothers.jobportal.service;

import com.jmjbrothers.jobportal.dto.UserRegisterRequestDto;
import com.jmjbrothers.jobportal.model.User;
import com.jmjbrothers.jobportal.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerNewUser(UserRegisterRequestDto request) {

        User existUser = userRepository.findByEmail(request.getEmail()).orElse(null);

        if (existUser != null) {
            throw new RuntimeException("User already exist by email: " + request.getEmail());
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());


        return userRepository.save(user);
    }
}
