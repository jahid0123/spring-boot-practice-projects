package com.jmjbrothers.realestateportal.controller;


import com.jmjbrothers.realestateportal.dto.GetPostedProperty;
import com.jmjbrothers.realestateportal.dto.PropertyPostDto;
import com.jmjbrothers.realestateportal.dto.PropertyUpdateDto;
import com.jmjbrothers.realestateportal.dto.UpdateMyPostedPropertyDto;
import com.jmjbrothers.realestateportal.model.PropertyPost;
import com.jmjbrothers.realestateportal.service.PropertyPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public ResponseEntity<?> getMyPostedProperties(@RequestParam Long id) {
        List<GetPostedProperty> postedPropertyByMe = propertyPostService.allPropertyPostedByMe(id);

        return new ResponseEntity<>(postedPropertyByMe, HttpStatus.OK);

    }

    @DeleteMapping("/posted/property/delete")
    public ResponseEntity<?> deleteMyPostedProperties(@RequestParam Long id) {
        String deleteMyPostedProperties = propertyPostService.deleteMyPostedProperties(id);

        return new ResponseEntity<>(deleteMyPostedProperties, HttpStatus.OK);

    }

    @PutMapping(value = "/posted/property/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateMyPostedProperties(
            @RequestPart("property") PropertyUpdateDto propertyDto,
            @RequestPart(value = "images", required = false) MultipartFile[] images
    ) throws IOException {
        PropertyPost updated = propertyPostService.updateMyPostedProperties(propertyDto, images);
        return ResponseEntity.ok(updated);
    }


//    @GetMapping("/images/{filename:.+}")
//    public ResponseEntity<Resource> serveImage(@PathVariable String filename) {
//        Path imagePath = Paths.get("uploads").resolve(filename).normalize();
//
//        try {
//            Resource resource = new UrlResource(imagePath.toUri());
//            if (resource.exists()) {
//                return ResponseEntity.ok()
//                        .contentType(MediaType.IMAGE_JPEG) // or detect dynamically
//                        .body(resource);
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        } catch (MalformedURLException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }




}

