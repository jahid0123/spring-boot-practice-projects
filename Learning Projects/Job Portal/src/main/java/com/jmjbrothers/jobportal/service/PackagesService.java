package com.jmjbrothers.jobportal.service;

import com.jmjbrothers.jobportal.model.CompanyPackage;
import com.jmjbrothers.jobportal.model.Packages;
import com.jmjbrothers.jobportal.repository.PackagesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackagesService {

    private final PackagesRepository packagesRepository;

    public PackagesService(PackagesRepository packagesRepository) {
        this.packagesRepository = packagesRepository;
    }



    //Create Package
    @Transactional
    public Packages createPackage(Packages packages) {
        return packagesRepository.save(packages);
    }


    //Create Package
    @Transactional
    public Packages editPackage(Packages packages) {
        Packages existPackage = packagesRepository.findById(packages.getId()).orElse(null);
        if (existPackage == null) {
            throw new RuntimeException("Package does not exist by id: " + packages.getId());
        }
        existPackage.setName(packages.getName());
        existPackage.setPrice(packages.getPrice());
        existPackage.setApplicantViewLimit(packages.getApplicantViewLimit());
        existPackage.setJobPostLimit(packages.getJobPostLimit());

        return packagesRepository.save(existPackage);
    }

    @Transactional
    public List<Packages> getAllPackages() {
        return packagesRepository.findAll();
    }

    @Transactional
    public void deletePackage(Long id) {
        packagesRepository.deleteById(id);
    }
}
