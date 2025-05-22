package com.jmjbrothers.dreamtravelsolution.repository;

import com.jmjbrothers.dreamtravelsolution.model.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelPackageRepository extends JpaRepository<TravelPackage, Long> {
}
