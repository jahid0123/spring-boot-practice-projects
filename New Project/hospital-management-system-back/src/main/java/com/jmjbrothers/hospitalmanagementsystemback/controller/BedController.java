package com.jmjbrothers.hospitalmanagementsystemback.controller;

import com.jmjbrothers.hospitalmanagementsystemback.model.Bed;
import com.jmjbrothers.hospitalmanagementsystemback.repository.BedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bed")
@RequiredArgsConstructor
public class BedController {

    private final BedRepository bedRepository;

    @GetMapping("/available")
    public ResponseEntity<List<Bed>> getAvailableBeds() {
        List<Bed> beds = bedRepository.findByIsOccupiedFalse();
        return ResponseEntity.ok(beds);
    }

    @PostMapping("/add")
    public ResponseEntity<Bed> addBed(@RequestBody Bed bed) {
        bed.setOccupied(false);
        return ResponseEntity.ok(bedRepository.save(bed));
    }
}
