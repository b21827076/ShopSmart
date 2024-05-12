package com.ariamath.shopsmart.service;

import com.ariamath.shopsmart.entity.Profile;
import com.ariamath.shopsmart.repository.ProfileRepository;
import com.ariamath.shopsmart.request.ProfileUpdateRequest;
import com.ariamath.shopsmart.response.ProfileResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private UserService userService;
    private ProfileRepository profileRepository;

    public ProfileService(UserService userService, ProfileRepository profileRepository) {
        this.userService = userService;
        this.profileRepository = profileRepository;
    }

    public Profile getProfileById(Long userId) {
        return(profileRepository.getByUserId(userId));
    }

    public void updateProfile(Long userId, ProfileUpdateRequest profileUpdateRequest) {
        Profile profile = profileRepository.getByUserId(userId);
        profile.setProfilePicture(profileUpdateRequest.getProfilePicture());
        profile.setBanner(profileUpdateRequest.getBanner());
        profile.setAbout(profileUpdateRequest.getAbout());
        profileRepository.save(profile);
    }

    public void deleteProfile(Long userId){
        Profile profile = profileRepository.getByUserId(userId);
        profileRepository.delete(profile);
    }
}
