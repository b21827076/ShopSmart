package com.ariamath.shopsmart.response;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PendingUserResponse {
    private Long id;
    private String username;
    private String email;
    private String role_name;
}
