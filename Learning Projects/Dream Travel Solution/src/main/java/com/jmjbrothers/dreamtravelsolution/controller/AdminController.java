package com.jmjbrothers.dreamtravelsolution.controller;

import com.jmjbrothers.dreamtravelsolution.dto.UpdateBookingDto;
import com.jmjbrothers.dreamtravelsolution.model.BookingPackage;
import com.jmjbrothers.dreamtravelsolution.model.ConfirmBooking;
import com.jmjbrothers.dreamtravelsolution.model.TravelPackage;
import com.jmjbrothers.dreamtravelsolution.model.User;
import com.jmjbrothers.dreamtravelsolution.service.BookingPackageService;
import com.jmjbrothers.dreamtravelsolution.service.ConfirmBookingService;
import com.jmjbrothers.dreamtravelsolution.service.TravelPackageService;
import com.jmjbrothers.dreamtravelsolution.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {

    private final UserService userService;
    private final TravelPackageService travelPackageService;
    private final BookingPackageService bookingPackageService;

    private final ConfirmBookingService confirmBookingService;

    public AdminController(UserService userService, TravelPackageService travelPackageService, BookingPackageService bookingPackageService, ConfirmBookingService confirmBookingService) {
        this.userService = userService;
        this.travelPackageService = travelPackageService;
        this.bookingPackageService = bookingPackageService;
        this.confirmBookingService = confirmBookingService;
    }

    //Get All User By Admin
    @GetMapping("/all/users")
    public ResponseEntity<?> getAllUserByAdmin() {

        List<User> allUser = userService.getAllUserByAdmin();

        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }


    //Delete User by admin with id
    @GetMapping("/delete/user")
    public ResponseEntity<?> deleteUserByAdmin(@RequestParam Long id) {

        String deleteUser = userService.deleteUserByAdmin(id);

        return new ResponseEntity<>(deleteUser, HttpStatus.OK);
    }


    @PostMapping("/create/package")
    public ResponseEntity<?> createTravelPackage(@RequestBody TravelPackage travelPackage){

        TravelPackage createPackage = travelPackageService.createTravelPackage(travelPackage);

        return new ResponseEntity<>(createPackage, HttpStatus.CREATED);
    }


    @PutMapping("/update/package")
    public ResponseEntity<?> updateTravelPackage(@RequestBody TravelPackage travelPackage){

        TravelPackage createPackage = travelPackageService.updateTravelPackage(travelPackage);

        return new ResponseEntity<>(createPackage, HttpStatus.CREATED);
    }

    @PutMapping("/delete/package")
    public ResponseEntity<?> deleteTravelPackage(@RequestParam Long id){

        String deletePackage = travelPackageService.deleteTravelPackage(id);

        return new ResponseEntity<>(deletePackage, HttpStatus.OK);
    }

    @GetMapping("/get/all/booking")
    public ResponseEntity<?> getAllBooking(){
        List<BookingPackage> bookingPackages = bookingPackageService.getAllBooking();

        return new ResponseEntity<>(bookingPackages, HttpStatus.OK);
    }

    @GetMapping("/update/booking")
    public ResponseEntity<?> updateBooking(@RequestBody UpdateBookingDto updateBookingDto){
        BookingPackage updateBooking = bookingPackageService.UpdateBooking(updateBookingDto);

        return new ResponseEntity<>(updateBooking, HttpStatus.OK);
    }

    @PostMapping("/confirm/booking")
    public ResponseEntity<?> deleteBookingById(@RequestParam Long id){

        ConfirmBooking confirmBooking = confirmBookingService.confirmBooking(id);

        return new ResponseEntity<>(confirmBooking, HttpStatus.OK);
    }

}
