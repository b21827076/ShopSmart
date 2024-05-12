package com.ariamath.shopsmart.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserUpdateRequest {
    private String first_name;
    private String last_name;
    private String email;
    private String user_name;
}
