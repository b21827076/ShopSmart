package com.ariamath.shopsmart.util;

import com.ariamath.shopsmart.entity.User;

public class RoleUtil {
    public static String ROLE_ADMIN = "Admin";
    public static String ROLE_MERCHANT = "Merchant";
    public static String ROLE_CUSTOMER = "Customer";
    //public static String ROLE_ = "ROLE_";
    //sadfasdfasdfasdfa
    public static boolean hasRole(User User, String role) {
        return User.getRole_name().equals(role);
    }
}