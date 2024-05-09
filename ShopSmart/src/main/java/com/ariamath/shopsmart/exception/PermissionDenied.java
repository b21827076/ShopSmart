package com.ariamath.shopsmart.exception;

import lombok.Getter;

@Getter
public class PermissionDenied{
    private String error;
    public PermissionDenied(String username) {
        this.error = ("Username:" + username + " doesn't have permission");
    }
}
