package com.ariamath.shopsmart.response;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PendingUserResponse {
    private String username;
    private String email;
    private String role_name;
}
