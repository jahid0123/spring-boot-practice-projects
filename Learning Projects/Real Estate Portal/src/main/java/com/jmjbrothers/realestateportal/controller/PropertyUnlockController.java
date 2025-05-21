package com.jmjbrothers.realestateportal.controller;

import com.jmjbrothers.realestateportal.dto.PropertyInfoRequestDto;
import com.jmjbrothers.realestateportal.model.PropertyInfoRequest;
import com.jmjbrothers.realestateportal.service.PropertyInfoRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/buyer")
public class PropertyUnlockController {

    private final PropertyInfoRequestService propertyUnlockService;

    @PostMapping("/property/request")
    public ResponseEntity<?> propertyUnlock(@RequestBody PropertyInfoRequestDto propertyUnlockDto){
        PropertyInfoRequest propertyUnlock = propertyUnlockService.propertyInfoRequest(propertyUnlockDto);
        if (propertyUnlock == null)
            return ResponseEntity.badRequest().body("Some thing wrong");
        return new ResponseEntity<>(propertyUnlock, HttpStatus.OK);
    }

    @GetMapping("/property/request/me")
    public ResponseEntity<?> getAllPropertyUnlockById(@RequestParam Long id){
        List<PropertyInfoRequest> propertyInfoRequests = propertyUnlockService.allPropertyInfoRequestById(id);
        if (propertyInfoRequests == null){
            return ResponseEntity.badRequest().body("No unlock property yet");
        }
        return new ResponseEntity<>(propertyInfoRequests, HttpStatus.OK);
    }
}