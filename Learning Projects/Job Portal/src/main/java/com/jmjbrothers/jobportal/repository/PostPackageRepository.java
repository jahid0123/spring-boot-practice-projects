package com.jmjbrothers.jobportal.repository;

import com.jmjbrothers.jobportal.model.PostPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostPackageRepository extends JpaRepository<PostPackage, Long> {


}
