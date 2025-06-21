package com.jmjbrothers.jobportal.service;

import com.jmjbrothers.jobportal.dto.EditUserInfoDto;
import com.jmjbrothers.jobportal.dto.PasswordChangeRequestDto;
import com.jmjbrothers.jobportal.dto.UserRegisterRequestDto;
import com.jmjbrothers.jobportal.model.Seeker;
import com.jmjbrothers.jobportal.model.User;
import com.jmjbrothers.jobportal.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Transactional
    public User editUserInformation(EditUserInfoDto editUserInfoDto) {
        User user = userRepository.findById(editUserInfoDto.getUserId()).orElseThrow(
                () -> new RuntimeException("User not found by id: " + editUserInfoDto.getUserId())
        );

        user.setName(editUserInfoDto.getName());
        user.setPhone(editUserInfoDto.getPhone());

        return userRepository.save(user);
    }

    @Transactional
    public void changePassword(PasswordChangeRequestDto request) {
        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("User not found by id: " + request.getId()));
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    @Transactional
    public User getProfileInformation(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new RuntimeException("User does not exist by id: " + userId);
        }
        return user;
    }


    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
