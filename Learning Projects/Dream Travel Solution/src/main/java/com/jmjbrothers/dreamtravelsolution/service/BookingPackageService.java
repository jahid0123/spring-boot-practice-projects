package com.jmjbrothers.dreamtravelsolution.service;

import com.jmjbrothers.dreamtravelsolution.constants.Status;
import com.jmjbrothers.dreamtravelsolution.dto.BookingPackageDto;
import com.jmjbrothers.dreamtravelsolution.dto.UpdateBookingDto;
import com.jmjbrothers.dreamtravelsolution.model.BookingPackage;
import com.jmjbrothers.dreamtravelsolution.model.TravelPackage;
import com.jmjbrothers.dreamtravelsolution.model.User;
import com.jmjbrothers.dreamtravelsolution.repository.BookingPackageRepository;
import com.jmjbrothers.dreamtravelsolution.repository.TravelPackageRepository;
import com.jmjbrothers.dreamtravelsolution.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingPackageService {

    private final BookingPackageRepository bookingPackageRepository;
    private final UserRepository userRepository;
    private final TravelPackageRepository travelPackageRepository;

    public BookingPackageService(BookingPackageRepository bookingPackageRepository, UserRepository userRepository, TravelPackageRepository travelPackageRepository) {
        this.bookingPackageRepository = bookingPackageRepository;
        this.userRepository = userRepository;
        this.travelPackageRepository = travelPackageRepository;
    }

    //this method owned by customer
    @Transactional
    public BookingPackage bookingPackage(BookingPackageDto bookingPackageDto) {
        Long userId = bookingPackageDto.getUserId();
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UsernameNotFoundException("User not found by id "+bookingPackageDto.getUserId()));

        Long packageId = bookingPackageDto.getPackageId();
        TravelPackage travelPackage = travelPackageRepository.findById(packageId).orElseThrow(
                () -> new RuntimeException("Package not found by id " + bookingPackageDto.getPackageId()));

        if (user != null && travelPackage != null){

            BookingPackage bookingPackage = new BookingPackage();

            bookingPackage.setUser(user);
            bookingPackage.setTravelPackage(travelPackage);
            bookingPackage.setTransport(bookingPackageDto.getTransport());



            return bookingPackageRepository.save(bookingPackage);

        }
        return null;
    }

    //This method owned by customer
    @Transactional
    public String deleteBookingById(Long id) {
        bookingPackageRepository.deleteById(id);
        if (bookingPackageRepository.existsById(id)){
            return "Booking not canceled!";
        }
        return "Booking canceled successfully.";
    }

    //This method owned by admin
    @Transactional
    public List<BookingPackage> getAllBooking() {

        List<BookingPackage> bookingPackages = bookingPackageRepository.findAll();

        return bookingPackages;
    }

    //This method owned by admin
    @Transactional
    public BookingPackage UpdateBooking(UpdateBookingDto updateBookingDto) {

        BookingPackage bookingPackage = bookingPackageRepository.findById(updateBookingDto.getBookingId()).orElseThrow(
                ()-> new RuntimeException("Booking is not found by id "+updateBookingDto.getBookingId()));

        if (bookingPackage != null){
            bookingPackage.setStatus(updateBookingDto.getStatus());
            bookingPackage.setTransport(updateBookingDto.getTransport());

            return bookingPackageRepository.save(bookingPackage);
        }

        return null;
    }

    //this methods owned by customer
    @Transactional
    public List<BookingPackage> getMyAllBooking(Long id) {

        List<BookingPackage> myBookingList = bookingPackageRepository.findAllByUserId(id);

        return myBookingList;
    }
}
