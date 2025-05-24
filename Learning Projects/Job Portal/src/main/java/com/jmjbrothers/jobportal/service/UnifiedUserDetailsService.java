package com.jmjbrothers.jobportal.service;

import com.jmjbrothers.jobportal.config.jwt.UserInfoDetails;
import com.jmjbrothers.jobportal.model.Company;
import com.jmjbrothers.jobportal.model.Seeker;
import com.jmjbrothers.jobportal.model.User;
import com.jmjbrothers.jobportal.repository.CompanyRepository;
import com.jmjbrothers.jobportal.repository.SeekerRepository;
import com.jmjbrothers.jobportal.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnifiedUserDetailsService implements UserDetailsService {


    private final CompanyRepository companyRepository;

    private final UserRepository userRepository;

    private final SeekerRepository seekerRepository;

    public UnifiedUserDetailsService(CompanyRepository companyRepository, UserRepository userRepository, SeekerRepository seekerRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.seekerRepository = seekerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Company> company = companyRepository.findByEmail(email);
        if (company.isPresent())
            return new CompanyInfoDetails(company.get());

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent())
            return new UserInfoDetails(user.get());

        Optional<Seeker> seeker = seekerRepository.findByEmail(email);
        if (seeker.isPresent())
            return new SeekerInfoDetails(seeker.get());

        throw new UsernameNotFoundException("User not found with email: " + email);
    }

}
