package com.jmjbrothers.realestateportal.controller;


import com.jmjbrothers.realestateportal.dto.PropertyDto;
import com.jmjbrothers.realestateportal.model.Property;
import com.jmjbrothers.realestateportal.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class PropertyController {

    private final PropertyService propertyService;


    @PostMapping("/create/property/admin")
    public ResponseEntity<?> createProperty(@RequestBody PropertyDto propertyDto ){
        Property addProperty = propertyService.createProperty(propertyDto);

        return new ResponseEntity<>(addProperty, HttpStatus.CREATED);
    }
}

