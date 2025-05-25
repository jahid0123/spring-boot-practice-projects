package com.jmjbrothers.jobportal.service;

import com.jmjbrothers.jobportal.config.jwt.UserInfoDetails;
import com.jmjbrothers.jobportal.model.Company;
import com.jmjbrothers.jobportal.model.Seeker;
import com.jmjbrothers.jobportal.model.User;
import com.jmjbrothers.jobportal.repository.CompanyRepository;
import com.jmjbrothers.jobportal.repository.SeekerRepository;
import com.jmjbrothers.jobportal.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
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

   /* @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Company> company = companyRepository.findByEmail(email);
        if (company.isPresent())
            return new UnifiedUserDetailsService(companyRepository);

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent())
            return new UnifiedUserDetailsService(userRepository);

        Optional<Seeker> seeker = seekerRepository.findByEmail(email);
        if (seeker.isPresent())
            return new UnifiedUserDetailsService(seekerRepository);

        throw new UsernameNotFoundException("User not found with email: " + email);
    }*/

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Seeker> seeker = seekerRepository.findByEmail(email);
        if (seeker.isPresent()) {
            Seeker s = seeker.get();
            return new UserInfoDetails(s);
        }

        Optional<Company> company = companyRepository.findByEmail(email);
        if (company.isPresent()) {
            Company c = company.get();
            return new UserInfoDetails(c);
        }

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            User u = user.get();
            return new UserInfoDetails(u);
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }

}
