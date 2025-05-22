package com.jmjbrothers.dreamtravelsolution.controller;

import com.jmjbrothers.dreamtravelsolution.dto.BookingPackageDto;
import com.jmjbrothers.dreamtravelsolution.model.BookingPackage;
import com.jmjbrothers.dreamtravelsolution.service.BookingPackageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/customer")
public class BookingPackageController {

    private final BookingPackageService bookingPackageService;

    public BookingPackageController(BookingPackageService bookingPackageService) {
        this.bookingPackageService = bookingPackageService;
    }

    @PostMapping("/create/booking")
    public ResponseEntity<?> createBooking(@RequestBody BookingPackageDto bookingPackageDto){

        BookingPackage bookingPackage = bookingPackageService.bookingPackage(bookingPackageDto);

        return new ResponseEntity<>(bookingPackage, HttpStatus.CREATED);
    }

    @PostMapping("/delete/booking")
    public ResponseEntity<?> deleteBookingById(@RequestParam Long id){

        String deleteBookingById = bookingPackageService.deleteBookingById(id);

        return new ResponseEntity<>(deleteBookingById, HttpStatus.OK);
    }
}
