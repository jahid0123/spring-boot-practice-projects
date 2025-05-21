package com.jmjbrothers.realestateportal.service;


import com.jmjbrothers.realestateportal.model.PostPackage;
import com.jmjbrothers.realestateportal.repository.PostPackageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostPackageService {

    @Autowired
    private PostPackageRepository packageRepository;


    public PostPackage addCreditPackage(PostPackage cPackage) {
        PostPackage addPackage = packageRepository.save(cPackage);
        if (addPackage == null){
            throw new RuntimeException("Credit package not added!");
        }
        return addPackage;

    }

    public List<PostPackage> allCreditPackage() {

        return packageRepository.findAll();
    }

    @Transactional
    public PostPackage updatePackage(PostPackage postPackage) {

        PostPackage updatedPostPackage = packageRepository.findById(postPackage.getId()).orElse(null);

        updatedPostPackage.setName(postPackage.getName());
        updatedPostPackage.setNumberOfPost(postPackage.getNumberOfPost());
        updatedPostPackage.setPackagePrice(postPackage.getPackagePrice());

        return updatedPostPackage;

    }

    @Transactional
    public String deletePackageById(Long id) {
        packageRepository.deleteById(id);

        return "Delete package successfully.";
    }
}

