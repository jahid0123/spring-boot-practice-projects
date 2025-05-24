package com.jmjbrothers.jobportal.service;

import com.jmjbrothers.jobportal.dto.UserRegisterRequestDto;
import com.jmjbrothers.jobportal.model.Seeker;
import com.jmjbrothers.jobportal.repository.SeekerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SeekerService {

    private final SeekerRepository seekerRepository;
    private final PasswordEncoder passwordEncoder;

    public SeekerService(SeekerRepository seekerRepository, PasswordEncoder passwordEncoder) {
        this.seekerRepository = seekerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Seeker registerNewSeeker(UserRegisterRequestDto request) {

        Seeker existSeeker = seekerRepository.findByEmail(request.getEmail()).orElse(null);
        if (existSeeker != null){
            throw new RuntimeException("Seeker already exist by email: " + request.getEmail());
        }

        Seeker seeker = new Seeker();

        seeker.setName(request.getName());
        seeker.setEmail(request.getEmail());
        seeker.setPassword(passwordEncoder.encode(request.getPassword()));
        seeker.setPhone(request.getPhone());

        return seekerRepository.save(seeker);
    }
}
