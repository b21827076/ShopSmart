package com.ariamath.shopsmart.response;

import com.ariamath.shopsmart.entity.User;
import lombok.Data;

@Data
public class AuthResponse {

    String message;
    Long userId;
    String accessToken;
    String refreshToken;
    String username;
    String role;
}