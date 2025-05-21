package com.jmjbrothers.realestateportal.repository;

import com.jmjbrothers.realestateportal.model.BuyPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuyPackageRepository extends JpaRepository<BuyPackage, Long> {

    Optional<BuyPackage> findById(Long id);
    List<BuyPackage> findAllByUserId(Long userId);
}
