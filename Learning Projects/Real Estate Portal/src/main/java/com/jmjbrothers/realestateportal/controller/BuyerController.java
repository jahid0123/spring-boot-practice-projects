package com.jmjbrothers.realestateportal.controller;

import com.jmjbrothers.realestateportal.dto.GetPostedProperty;
import com.jmjbrothers.realestateportal.dto.PropertyInfoRequestDto;
import com.jmjbrothers.realestateportal.model.PropertyInfoRequest;
import com.jmjbrothers.realestateportal.service.PropertyInfoRequestService;
import com.jmjbrothers.realestateportal.service.PropertyPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/buyer")
public class BuyerController {

    private final PropertyInfoRequestService propertyUnlockService;
    private final PropertyPostService propertyPostService;

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


    @GetMapping("/all/posted/property")
    public ResponseEntity<?> getAllPostedProperty() {

        List<GetPostedProperty> allProperty = propertyPostService.getAllPostedProperty();
        return ResponseEntity.ok(allProperty);
    }


    @GetMapping("/property/image/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Path file = Paths.get("uploads").resolve(filename).normalize();
            Resource resource = new UrlResource(file.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }

            // Try to determine the content type
            String contentType = Files.probeContentType(file);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}