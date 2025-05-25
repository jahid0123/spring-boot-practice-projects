package com.jmjbrothers.jobportal.service;

import com.jmjbrothers.jobportal.repository.BuyPostPackageRepository;
import org.springframework.stereotype.Service;

@Service
public class BuyPostPackageService {

    private final BuyPostPackageRepository buyPostPackageRepository;

    public BuyPostPackageService(BuyPostPackageRepository buyPostPackageRepository) {
        this.buyPostPackageRepository = buyPostPackageRepository;
    }
}
