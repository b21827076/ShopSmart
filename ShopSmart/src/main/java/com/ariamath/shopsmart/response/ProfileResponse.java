package com.ariamath.shopsmart.response;

import com.ariamath.shopsmart.entity.User;
import lombok.Data;

@Data
public class ProfileResponse {

    Long id;
    String profilePicture;
    String banner;
    String about;
    Double rate;
    User user;

}
