package com.ariamath.shopsmart.controller;

import com.ariamath.shopsmart.entity.PendingUser;
import com.ariamath.shopsmart.request.LoginRequest;
import com.ariamath.shopsmart.request.PendingUserRequest;
import com.ariamath.shopsmart.response.PendingUserResponse;
import com.ariamath.shopsmart.service.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class SignupController {
    /*
    private final SignupService signupService;
    @PostMapping
    public void savePendingUser(@RequestBody PendingUserRequest pendingUserRequest) {
        PendingUser user = new PendingUser(
                null,
                pendingUserRequest.getFirst_name(),
                pendingUserRequest.getLast_name(),
                pendingUserRequest.getEmail(),
                pendingUserRequest.getUsername(),
                pendingUserRequest.getPassword(),
                pendingUserRequest.getRole_name()
        );
        signupService.saveUserAsPending(user);
        signupService.assignRoleToPendingUser(user.getUsername(), pendingUserRequest.getRole_name());
    }

    @PostMapping("/approve") //api/signup/approve
    public void approveUser(@RequestBody LoginRequest approveUserRequest) {
        signupService.approveUser(approveUserRequest.getUsername());
    }

    @PostMapping("/deny") //api/signup/approve
    public void denyUser(@RequestBody LoginRequest approveUserRequest) {
        signupService.denyUser(approveUserRequest.getUsername());
    }

    @GetMapping("")
    public List<PendingUserResponse> getPendingUsers() {
        return signupService.getAllPendingUsers();
    }

     */
}

