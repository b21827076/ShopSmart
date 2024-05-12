package com.ariamath.shopsmart.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProfileUpdateRequest {
    private String profilePicture;
    private String banner;
    private String about;
}
