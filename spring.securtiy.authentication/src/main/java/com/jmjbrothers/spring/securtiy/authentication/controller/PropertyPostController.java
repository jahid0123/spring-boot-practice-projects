package com.jmjbrothers.spring.securtiy.authentication.controller;

import com.jmjbrothers.spring.securtiy.authentication.dto.GetPostedProperty;
import com.jmjbrothers.spring.securtiy.authentication.dto.PropertyPostDto;
import com.jmjbrothers.spring.securtiy.authentication.model.PropertyPost;
import com.jmjbrothers.spring.securtiy.authentication.service.PropertyPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class PropertyPostController {

    private final PropertyPostService propertyPostService;


    /*@PostMapping("/post/property")
    public ResponseEntity<?> postProperty(@Valid @RequestBody PropertyPostDto propertyPostDto){
        try {
            PropertyPost result = propertyPostService.postProperty(propertyPostDto);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            System.out.println(propertyPostDto.toString());
            System.out.println(e);
            return ResponseEntity.badRequest().body("Something wrong");
        }
    }*/

    @PostMapping("/post/property")
    public ResponseEntity<?> postProperty(
            @RequestPart("data") @Valid PropertyPostDto propertyPostDto,
            @RequestPart("images") MultipartFile[] images) {
        try {
            // Save property with images
            PropertyPost result = propertyPostService.postProperty(propertyPostDto, images);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            System.out.println(propertyPostDto);
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @GetMapping("/all/posted/property")
    public ResponseEntity<?> getAllPostedProperty(){

        List<GetPostedProperty> allProperty = propertyPostService.getAllPostedProperty();
        return ResponseEntity.ok(allProperty);
    }
}
