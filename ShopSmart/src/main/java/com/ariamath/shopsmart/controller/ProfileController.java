package com.ariamath.shopsmart.controller;

import com.ariamath.shopsmart.entity.Profile;
import com.ariamath.shopsmart.request.ProfileUpdateRequest;
import com.ariamath.shopsmart.service.ProfileService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/profile") //localhost::api/user
@RequiredArgsConstructor
@AllArgsConstructor
@Slf4j
public class ProfileController {
    private ProfileService profileService;

    @GetMapping("/{userId}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long userId){
        return new ResponseEntity<>(profileService.getProfileById(userId),HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public void updateProfileById(@PathVariable Long userId, @RequestBody ProfileUpdateRequest profileUpdateRequest){
        profileService.updateProfile(userId, profileUpdateRequest);
    }

}
