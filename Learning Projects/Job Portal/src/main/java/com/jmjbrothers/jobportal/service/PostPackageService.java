package com.jmjbrothers.jobportal.service;

import com.jmjbrothers.jobportal.model.PostPackage;
import com.jmjbrothers.jobportal.repository.PostPackageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PostPackageService {

    private final PostPackageRepository postPackageRepository;

    public PostPackageService(PostPackageRepository postPackageRepository) {
        this.postPackageRepository = postPackageRepository;
    }


    @Transactional
    public PostPackage createPostPackage(PostPackage postPackage) {

        PostPackage createPackage = postPackageRepository.save(postPackage);




        return createPackage;
    }
}
