package com.jmjbrothers.spring.securtiy.authentication.service;

import com.jmjbrothers.spring.securtiy.authentication.model.CreditPackage;
import com.jmjbrothers.spring.securtiy.authentication.repository.CreditPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreditPackageService {

    @Autowired
    private CreditPackageRepository packageRepository;


    public String addCreditPackage(CreditPackage cPackage) {
        CreditPackage addPackage = packageRepository.save(cPackage);
        if (addPackage != null){
            return "Package save successfully.";
        }
        return "Sorry!! Package not create!";

    }
}
