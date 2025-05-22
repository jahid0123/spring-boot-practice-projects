package com.jmjbrothers.dreamtravelsolution.repository;

import com.jmjbrothers.dreamtravelsolution.model.BookingPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingPackageRepository extends JpaRepository<BookingPackage, Long> {


    List<BookingPackage> findAllByUserId(Long id);
}
