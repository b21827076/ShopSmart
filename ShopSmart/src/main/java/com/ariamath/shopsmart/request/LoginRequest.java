package com.ariamath.shopsmart.request;

import lombok.Data;

/**
 * POJO to serialize a login request object.
 */
@Data
public class LoginRequest {
    private String user_name;
    private String password;

}
