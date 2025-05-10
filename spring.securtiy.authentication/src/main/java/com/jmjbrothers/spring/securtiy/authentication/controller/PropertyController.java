package com.jmjbrothers.spring.securtiy.authentication.controller;

import com.jmjbrothers.spring.securtiy.authentication.dto.PropertyDto;
import com.jmjbrothers.spring.securtiy.authentication.model.Property;
import com.jmjbrothers.spring.securtiy.authentication.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class PropertyController {

    private final PropertyService propertyService;


    @PostMapping("/create/property")
    public ResponseEntity<?> createProperty(@RequestBody PropertyDto propertyDto ){
        Property addProperty = propertyService.createProperty(propertyDto);

        return new ResponseEntity<>(addProperty, HttpStatus.CREATED);
    }
}
