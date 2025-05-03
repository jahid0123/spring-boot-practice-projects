package com.jmjbrothers.spring.securtiy.authentication.service;

import com.jmjbrothers.spring.securtiy.authentication.model.User;
import com.jmjbrothers.spring.securtiy.authentication.model.UserInfoDetails;
import com.jmjbrothers.spring.securtiy.authentication.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userDetails = userRepository.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("User not found with the email: "+ email));

        return new UserInfoDetails(userDetails);
    }

    @Transactional
    public User registerNewUser(User request) {

        if (userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("User already exist with the email: " + request.getEmail());
        }

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(request);

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
}
