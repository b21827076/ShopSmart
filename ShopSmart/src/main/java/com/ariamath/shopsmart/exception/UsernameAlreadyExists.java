package com.ariamath.shopsmart.exception;

public class UsernameAlreadyExists extends RuntimeException {
    public UsernameAlreadyExists(String username) {
        super("Username " + username + " is already taken.");
    }
}
