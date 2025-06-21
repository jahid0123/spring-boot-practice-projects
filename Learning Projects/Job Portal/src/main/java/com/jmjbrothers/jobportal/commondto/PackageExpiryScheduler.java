package com.jmjbrothers.jobportal.commondto;

import com.jmjbrothers.jobportal.model.CompanyPackage;
import com.jmjbrothers.jobportal.repository.CompanyPackageRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class PackageExpiryScheduler {


    private  final CompanyPackageRepository companyPackageRepo;

    public PackageExpiryScheduler(CompanyPackageRepository companyPackageRepo) {
        this.companyPackageRepo = companyPackageRepo;
    }

    @Scheduled(cron = "0 0 0 * * ?") // Every day at midnight
    public void expirePackages() {
        List<CompanyPackage> packages = companyPackageRepo.findAll();
        for (CompanyPackage cp : packages) {
            if (cp.getActive() && cp.getExpiryDate().isBefore(LocalDate.now())) {
                cp.setActive(false);
                cp.setRemainingPosts(0);
                cp.setApplicantsViewLimit(0);
                companyPackageRepo.save(cp);
            }
        }
    }
}

