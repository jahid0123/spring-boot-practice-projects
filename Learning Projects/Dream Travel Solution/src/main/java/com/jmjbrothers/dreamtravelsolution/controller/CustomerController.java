package com.jmjbrothers.dreamtravelsolution.controller;


import com.jmjbrothers.dreamtravelsolution.model.BookingPackage;
import com.jmjbrothers.dreamtravelsolution.model.TravelPackage;
import com.jmjbrothers.dreamtravelsolution.model.User;
import com.jmjbrothers.dreamtravelsolution.service.BookingPackageService;
import com.jmjbrothers.dreamtravelsolution.service.TravelPackageService;
import com.jmjbrothers.dreamtravelsolution.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {

    private final TravelPackageService travelPackageService;
    private final BookingPackageService bookingPackageService;

    private final UserService userService;


    public CustomerController(TravelPackageService travelPackageService, BookingPackageService bookingPackageService, UserService userService) {
        this.travelPackageService = travelPackageService;
        this.bookingPackageService = bookingPackageService;
        this.userService = userService;
    }


    @GetMapping("/get/all/package")
    public ResponseEntity<?> getAllTravelPackage(){
        List<TravelPackage> allPackage = travelPackageService.getAllTravelPackage();

        return new ResponseEntity<>(allPackage, HttpStatus.OK);
    }

    @GetMapping("/get/my/booking")
    public ResponseEntity<?> getMyAllBooking(@RequestParam Long id){
        List<BookingPackage> myAllBooking = bookingPackageService.getMyAllBooking(id);

        return new ResponseEntity<>(myAllBooking, HttpStatus.OK);
    }

    @GetMapping("/get/my/info")
    public ResponseEntity<?> getMySelfInformation(@RequestParam Long id){
        User mySelfInformation = userService.getMySelfInformation(id);

        return new ResponseEntity<>(mySelfInformation, HttpStatus.OK);
    }


}
