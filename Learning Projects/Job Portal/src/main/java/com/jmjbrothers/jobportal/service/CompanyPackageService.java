package com.jmjbrothers.jobportal.service;

import com.jmjbrothers.jobportal.constants.BillStatus;
import com.jmjbrothers.jobportal.dto.PurchasePackageDto;
import com.jmjbrothers.jobportal.model.Bill;
import com.jmjbrothers.jobportal.model.Company;
import com.jmjbrothers.jobportal.model.CompanyPackage;
import com.jmjbrothers.jobportal.model.Packages;
import com.jmjbrothers.jobportal.repository.BillRepository;
import com.jmjbrothers.jobportal.repository.CompanyPackageRepository;
import com.jmjbrothers.jobportal.repository.CompanyRepository;
import com.jmjbrothers.jobportal.repository.PackagesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CompanyPackageService {

    private final CompanyPackageRepository companyPackageRepository;
    private final CompanyRepository companyRepository;
    private final PackagesRepository packagesRepository;
    private final BillRepository billRepository;

    public CompanyPackageService(CompanyPackageRepository companyPackageRepository, CompanyRepository companyRepository, PackagesRepository packagesRepository, BillRepository billRepository) {
        this.companyPackageRepository = companyPackageRepository;
        this.companyRepository = companyRepository;
        this.packagesRepository = packagesRepository;
        this.billRepository = billRepository;
    }


    @Transactional
    public CompanyPackage purchasePackages(PurchasePackageDto purchasePackageDto) {

        Packages packages = packagesRepository.findById(purchasePackageDto.getPackageId()).orElseThrow(
                ()-> new RuntimeException("Package does not exist by id "+purchasePackageDto.getPackageId())
        );

        Company company = companyRepository.findById(purchasePackageDto.getCompanyId()).orElseThrow(
                ()-> new RuntimeException("Company does not exist by id "+purchasePackageDto.getCompanyId())
        );

        CompanyPackage companyPackage = companyPackageRepository.findCompanyPackageByCompany_Id(purchasePackageDto.getCompanyId());
        if (companyPackage == null) {
            CompanyPackage newCompanyPackage = new CompanyPackage();
            newCompanyPackage.setCompany(company);
            newCompanyPackage.setPack(packages);
            newCompanyPackage.setRemainingPosts(packages.getJobPostLimit());
            newCompanyPackage.setApplicantsViewLimit(packages.getApplicantViewLimit());

            Bill bill = new Bill();
            bill.setCompany(company);
            bill.setPack(packages);
            bill.setStatus(BillStatus.PAID);

            billRepository.save(bill);

            return companyPackageRepository.save(newCompanyPackage);
        }else if (companyPackage.getPack().getId() == packages.getId()){
            companyPackage.setRemainingPosts(companyPackage.getRemainingPosts() + packages.getJobPostLimit());
            companyPackage.setApplicantsViewLimit(companyPackage.getApplicantsViewLimit() + packages.getApplicantViewLimit());
            return getCompanyPackage(packages, company, companyPackage);
        }else{
            companyPackage.setPack(packages);
            companyPackage.setRemainingPosts(packages.getJobPostLimit());
            companyPackage.setApplicantsViewLimit(packages.getApplicantViewLimit());
            return getCompanyPackage(packages, company, companyPackage);
        }

    }

    private CompanyPackage getCompanyPackage(Packages packages, Company company, CompanyPackage companyPackage) {
        companyPackage.setLastPurchaseDate(LocalDate.now());

        Bill bill = new Bill();
        bill.setCompany(company);
        bill.setPack(packages);
        bill.setStatus(BillStatus.PAID);

        billRepository.save(bill);

        return companyPackageRepository.save(companyPackage);
    }

    @Transactional
    public CompanyPackage myPurchasePackages(Long companyId) {

        return companyPackageRepository.findCompanyPackageByCompany_Id(companyId);
    }
}
