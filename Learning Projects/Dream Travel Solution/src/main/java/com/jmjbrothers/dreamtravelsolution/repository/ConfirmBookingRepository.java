package com.jmjbrothers.dreamtravelsolution.repository;

import com.jmjbrothers.dreamtravelsolution.model.ConfirmBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmBookingRepository extends JpaRepository<ConfirmBooking, Long> {
}
