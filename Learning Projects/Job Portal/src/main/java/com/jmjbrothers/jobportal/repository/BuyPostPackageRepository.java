package com.jmjbrothers.jobportal.repository;

import com.jmjbrothers.jobportal.model.BuyPostPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyPostPackageRepository extends JpaRepository<BuyPostPackage, Long> {
}
