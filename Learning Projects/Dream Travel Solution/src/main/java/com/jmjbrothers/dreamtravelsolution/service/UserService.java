package com.jmjbrothers.dreamtravelsolution.service;

import com.jmjbrothers.dreamtravelsolution.constants.Role;
import com.jmjbrothers.dreamtravelsolution.dto.RegisterRequest;
import com.jmjbrothers.dreamtravelsolution.model.User;
import com.jmjbrothers.dreamtravelsolution.model.UserInfoDetails;
import com.jmjbrothers.dreamtravelsolution.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //constructor
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new UserInfoDetails(userRepository.findByEmail(email).orElse(null));
    }

    //This Method owned by Admin//
    @Transactional
    public List<User> getAllUserByAdmin() {

        return userRepository.findAll();
    }

    //This Method owned by User--
    @Transactional
    public User registerNewUser(RegisterRequest registerRequest) {

        if (userRepository.existsByEmail(registerRequest.getEmail())){
            throw new RuntimeException("User already exist with the id: "+registerRequest.getEmail());
        }

        User user = new User();

        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(Role.CUSTOMER);

        return userRepository.save(user);
    }


    //this Method is owned by admin
    @Transactional
    public String deleteUserByAdmin(Long id) {

        userRepository.deleteById(id);
        if (userRepository.existsById(id)){

            return "User deletion failed!";
        }

        return "User delete successfully";
    }
    //this Method is owned by both admin and customer
    public User getMySelfInformation(Long id) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found by id "+id));

        return user;
    }
}
