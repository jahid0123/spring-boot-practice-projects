package com.jmjbrothers.realestateportal.service;


import com.jmjbrothers.realestateportal.constants.Role;
import com.jmjbrothers.realestateportal.dto.RegisterRequest;
import com.jmjbrothers.realestateportal.dto.UserEditDto;
import com.jmjbrothers.realestateportal.dto.UserResponseDto;
import com.jmjbrothers.realestateportal.model.User;
import com.jmjbrothers.realestateportal.model.UserInfoDetails;
import com.jmjbrothers.realestateportal.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserInfoDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userDetails = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with the email: " + email));
        return new UserInfoDetails(userDetails);
    }

    @Transactional
    public User registerNewUser(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Admin already exist with the email: " + request.getEmail());
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setRole(Role.ADMIN);

        return userRepository.save(user);

    }

    @Transactional
    public List<User> getAllUser() {

        return userRepository.findAll();
    }

    @Transactional
    public String deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            userRepository.deleteById(id);
            return "User deleted successfully!";
        }else {
            return "User not found by the id: " + id;
        }
    }

    @Transactional
    public User userFindById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public UserResponseDto getUserById(Long id) {

        User user = userRepository.findById(id).orElseThrow(
                ()-> new UsernameNotFoundException("User not found with this id: "+id));

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPhone(user.getPhone());
        userResponseDto.setCreditBalance(user.getPostBalance());

        return userResponseDto;
    }

    @Transactional
    public ResponseEntity<?> editUserInfoById(UserEditDto userEditDto) {
        User user = userRepository.findById(userEditDto.getUserId()).orElseThrow(
                ()-> new UsernameNotFoundException("user not found with the id "+userEditDto.getUserId()));
        if (user != null){
            user.setName(userEditDto.getName());
            user.setPhone(userEditDto.getPhone());

            userRepository.save(user);

            return ResponseEntity.ok("User updated successfully");
        }else {
            return ResponseEntity.ok("User not updated!!!");
        }
    }

    @Transactional
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Transactional
    public User registerNewSeller(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Seller already exist with the email: " + request.getEmail());
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setRole(Role.SELLER);

        return userRepository.save(user);
    }

    @Transactional
    public User registerNewBuyer(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Buyer already exist with the email: " + request.getEmail());
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setRole(Role.BUYER);

        return userRepository.save(user);
    }

/*    public void changePassword(PasswordChangeRequestDto request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }*/
}

