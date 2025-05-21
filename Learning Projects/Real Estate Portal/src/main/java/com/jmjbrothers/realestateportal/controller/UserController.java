package com.jmjbrothers.realestateportal.controller;


import com.jmjbrothers.realestateportal.dto.PasswordChangeRequestDto;
import com.jmjbrothers.realestateportal.dto.UserEditDto;
import com.jmjbrothers.realestateportal.dto.UserResponseDto;
import com.jmjbrothers.realestateportal.model.PostPackage;
import com.jmjbrothers.realestateportal.service.PostPackageService;
import com.jmjbrothers.realestateportal.service.UserInfoDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/user")
public class UserController {


    private final UserInfoDetailsService userInfoDetailsService;
    private final PostPackageService creditPackageService;


    @GetMapping("/all/credit/package")
    public ResponseEntity<?> allCreditPackage(){
        List<PostPackage> creditPackage= creditPackageService.allCreditPackage();
        return new ResponseEntity<>(creditPackage, HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity<UserResponseDto> getUserById(@RequestParam Long userId){
        UserResponseDto userResponseDto = userInfoDetailsService.getUserById(userId);
        return ResponseEntity.ok(userResponseDto);

    }


    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeRequestDto request) {
        if (request.getCurrentPassword() == null || request.getNewPassword() == null) {
            return ResponseEntity.badRequest().body("Password fields cannot be null");
        }

        userInfoDetailsService.changePassword(request.getUserId(), request.getCurrentPassword(), request.getNewPassword());
        return ResponseEntity.ok("Password changed successfully");
    }
    /*@PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeRequestDto request) {
        userInfoDetailsService.changePassword(request);
        return ResponseEntity.ok("Password changed successfully.");
    }*/

/*    @GetMapping("/api/user/info")
    public ResponseEntity<UserResponseDto> getUserById(@RequestParam Long userId){
        UserResponseDto userResponseDto = userInfoDetailsService.getUserById(new GetUserInfoDto(userId));
        return ResponseEntity.ok(userResponseDto);
    }*/

    @PutMapping("/edit")
    public ResponseEntity<?> editUserById(@RequestBody UserEditDto userEditDto){
        userInfoDetailsService.editUserInfoById(userEditDto);

        return ResponseEntity.ok(Map.of("message", "Profile updated successfully!"));
    }




}