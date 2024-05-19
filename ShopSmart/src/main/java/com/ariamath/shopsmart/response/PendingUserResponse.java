package com.ariamath.shopsmart.response;
import com.ariamath.shopsmart.entity.PendingUser;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PendingUserResponse {
    private String username;
    private String email;
    private String role_name;

    public PendingUserResponse(PendingUser pendingUser){
        this.username = pendingUser.getUser_name();
        this.email = pendingUser.getEmail();
        this.role_name = pendingUser.getRole_name();
    }
}
