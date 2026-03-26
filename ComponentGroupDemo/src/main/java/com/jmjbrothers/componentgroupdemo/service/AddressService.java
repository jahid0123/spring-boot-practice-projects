package com.jmjbrothers.componentgroupdemo.service;

import com.jmjbrothers.componentgroupdemo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

}
