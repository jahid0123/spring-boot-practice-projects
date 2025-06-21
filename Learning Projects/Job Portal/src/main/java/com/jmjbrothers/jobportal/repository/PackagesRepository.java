package com.jmjbrothers.jobportal.repository;

import com.jmjbrothers.jobportal.model.CompanyPackage;
import com.jmjbrothers.jobportal.model.Packages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackagesRepository extends JpaRepository<Packages, Long> {


}
