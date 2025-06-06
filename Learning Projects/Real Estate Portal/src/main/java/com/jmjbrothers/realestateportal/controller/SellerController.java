package com.jmjbrothers.realestateportal.controller;


import com.jmjbrothers.realestateportal.dto.GetPostedProperty;
import com.jmjbrothers.realestateportal.dto.PropertyPostDto;
import com.jmjbrothers.realestateportal.dto.UpdateMyPostedPropertyDto;
import com.jmjbrothers.realestateportal.model.PropertyPost;
import com.jmjbrothers.realestateportal.service.PropertyPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/seller")
public class SellerController {

    private final PropertyPostService propertyPostService;


//    @PostMapping("/post/property")
//    public ResponseEntity<?> postProperty(@Valid @RequestBody PropertyPostDto propertyPostDto) {
//        try {
//            PropertyPost result = propertyPostService.postProperty(propertyPostDto);
//            return ResponseEntity.ok(result);
//        } catch (RuntimeException e) {
//            System.out.println(propertyPostDto.toString());
//            System.out.println(e);
//            return ResponseEntity.badRequest().body("Something wrong");
//        }
//    }


    @PostMapping(value = "/post/property", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> postProperty(
            @RequestPart("property") @Valid PropertyPostDto propertyPostDto,
            @RequestPart("images") MultipartFile[] imageFiles) throws IOException {

        PropertyPost result = propertyPostService.postProperty(propertyPostDto, imageFiles);
        if (result != null )
            return new ResponseEntity<>(HttpStatus.CREATED);

        return ResponseEntity.badRequest().body("Something went wrong");
    }

    @GetMapping("/all/posted/property")
    public ResponseEntity<?> getAllPostedProperty() {

        List<GetPostedProperty> allProperty = propertyPostService.getAllPostedProperty();
        return ResponseEntity.ok(allProperty);
    }

    @GetMapping("/property/posted/me")
    public ResponseEntity<List<PropertyPost>> getMyPostedProperties(@RequestParam Long id) {
        List<PropertyPost> postedPropertyByMe = propertyPostService.allPropertyPostedByMe(id);

        return new ResponseEntity<>(postedPropertyByMe, HttpStatus.OK);

    }

    @DeleteMapping("/property/posted/delete")
    public ResponseEntity<?> deleteMyPostedProperties(@RequestParam Long id) {
        String deleteMyPostedProperties = propertyPostService.deleteMyPostedProperties(id);

        return new ResponseEntity<>(deleteMyPostedProperties, HttpStatus.OK);

    }

    @PutMapping("/property/posted/update")
    public ResponseEntity<?> updateMyPostedProperties(@RequestBody UpdateMyPostedPropertyDto propertyDto) {
        PropertyPost updateMyPostedProperties = propertyPostService.updateMyPostedProperties(propertyDto);
        return new ResponseEntity<>(updateMyPostedProperties, HttpStatus.OK);

    }


}

