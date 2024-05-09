package com.ariamath.shopsmart.request;

import lombok.Data;

@Data
public class PendingUserRequest {
    private String first_name;
    private String last_name;
    private String email;
    private String user_name;
    private String password;
    private String role_name;
}

