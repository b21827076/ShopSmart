package com.ariamath.shopsmart.controller;

import com.ariamath.shopsmart.entity.Profile;
import com.ariamath.shopsmart.repository.ProfileRepository;
import com.ariamath.shopsmart.repository.UserRepository;
import com.ariamath.shopsmart.request.ProfileRequest;
import com.ariamath.shopsmart.response.ProfileResponse;
import com.ariamath.shopsmart.service.StartupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/profile") //localhost::api/user
@RequiredArgsConstructor
@Slf4j
public class ProfileController {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository ;

    @GetMapping("/{username}")
    public ResponseEntity<ProfileResponse> getProfileByUsername(@PathVariable String username){
        Long id = userRepository.findByUserName(username).getId();
        Profile profile = profileRepository.getByUserId(id);
        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.setProfilePicture(profile.getProfilePicture());
        profileResponse.setId(profile.getId());
        profileResponse.setRate(profile.getRating());
        profileResponse.setUser(profile.getUser());
        profileResponse.setAbout(profile.getAbout());
        profileResponse.setBanner(profile.getBanner());
        return new ResponseEntity<>(profileResponse,HttpStatus.OK);
    }


}
