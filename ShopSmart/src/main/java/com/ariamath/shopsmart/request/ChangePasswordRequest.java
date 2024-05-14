package com.ariamath.shopsmart.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangePasswordRequest {

    Long userId;
    String oldPassword;
    String newPassword;

}
