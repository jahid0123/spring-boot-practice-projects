package com.jmjbrothers.dreamtravelsolution.service;

import com.jmjbrothers.dreamtravelsolution.constants.Status;
import com.jmjbrothers.dreamtravelsolution.model.BookingPackage;
import com.jmjbrothers.dreamtravelsolution.model.ConfirmBooking;
import com.jmjbrothers.dreamtravelsolution.repository.BookingPackageRepository;
import com.jmjbrothers.dreamtravelsolution.repository.ConfirmBookingRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ConfirmBookingService {

    private final ConfirmBookingRepository confirmBookingRepository;
    private final BookingPackageRepository bookingPackageRepository;


    public ConfirmBookingService(ConfirmBookingRepository confirmBookingRepository, BookingPackageRepository bookingPackageRepository) {
        this.confirmBookingRepository = confirmBookingRepository;
        this.bookingPackageRepository = bookingPackageRepository;
    }

    //This method owned by Admin
    @Transactional
    public ConfirmBooking confirmBooking(Long id) {
        BookingPackage bookingPackage = bookingPackageRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Booking package not found by id "+id));

        if (bookingPackage != null){
            ConfirmBooking confirmBooking = new ConfirmBooking();
            confirmBooking.setUser(bookingPackage.getUser());
            confirmBooking.setTravelPackage(bookingPackage.getTravelPackage());
            confirmBooking.setStatus(bookingPackage.getStatus());
            confirmBooking.setTransport(bookingPackage.getTransport());
                bookingPackage.setStatus(Status.APPROVED);
            confirmBooking.setStatus(bookingPackage.getStatus());

            bookingPackageRepository.deleteById(bookingPackage.getId());

            return confirmBookingRepository.save(confirmBooking);
        }

        return null;
    }
}
