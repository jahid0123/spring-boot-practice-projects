package com.jmjbrothers.spring.securtiy.authentication.controller;

<<<<<<< HEAD
import com.jmjbrothers.spring.securtiy.authentication.model.Property;
import com.jmjbrothers.spring.securtiy.authentication.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping("/add/property")
    public ResponseEntity<?> addProperty(@RequestBody Property property){

        return null;

=======
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
>>>>>>> 681f6ebccd4978394829766acdd7b7ec0de65f08
    }
}
