package com.ariamath.shopsmart.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgottenPasswordRequest {
    String email;
    String userName;
    String firstName;
    String lastName;
}
