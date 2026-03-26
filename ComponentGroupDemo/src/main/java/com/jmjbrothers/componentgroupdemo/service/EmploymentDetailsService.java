package com.jmjbrothers.componentgroupdemo.service;

import com.jmjbrothers.componentgroupdemo.repository.EmploymentDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmploymentDetailsService {

    @Autowired
    private EmploymentDetailsRepository employmentDetailsRepository;

}
