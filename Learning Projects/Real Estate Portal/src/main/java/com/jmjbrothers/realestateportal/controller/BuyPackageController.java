package com.jmjbrothers.realestateportal.controller;


import com.jmjbrothers.realestateportal.dto.BuyPackageDto;
import com.jmjbrothers.realestateportal.dto.PurchaseHistoryDto;
import com.jmjbrothers.realestateportal.model.BuyPackage;
import com.jmjbrothers.realestateportal.service.BuyPackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/seller")
public class BuyPackageController {

    private final BuyPackageService transactionService;


    @PostMapping("/buy/package")
    public ResponseEntity<?> buyPackage(@RequestBody BuyPackageDto transactionDto){
        BuyPackage ct = transactionService.buyPackage(transactionDto);
        return new ResponseEntity<>(ct, HttpStatus.ACCEPTED);
    }

    @GetMapping("/buy/package/me")
    public ResponseEntity<?> getCreditPurchaseHistoryById(@RequestParam Long id){
        List<PurchaseHistoryDto> allPurchaseHistory = transactionService.allPropertyUnlockById(id);
        if (allPurchaseHistory == null){
            return ResponseEntity.badRequest().body("No unlock property yet");
        }
        return new ResponseEntity<>(allPurchaseHistory, HttpStatus.OK);
    }

    @GetMapping("/buy/package/all")
    public ResponseEntity<?> getAllCreditPurchaseHistory(){
        List<PurchaseHistoryDto> allPurchaseHistory = transactionService.allPropertyUnlock();
        if (allPurchaseHistory == null){
            return ResponseEntity.badRequest().body("No unlock property yet");
        }
        return new ResponseEntity<>(allPurchaseHistory, HttpStatus.OK);
    }



}