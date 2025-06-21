package com.jmjbrothers.jobportal.repository;

import com.jmjbrothers.jobportal.model.CompanyPackage;
import com.jmjbrothers.jobportal.model.Packages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyPackageRepository extends JpaRepository<CompanyPackage, Long> {

    CompanyPackage findCompanyPackageByCompany_Id(Long companyId);

    CompanyPackage findByCompany_IdAndActiveTrue(Long id);
}
