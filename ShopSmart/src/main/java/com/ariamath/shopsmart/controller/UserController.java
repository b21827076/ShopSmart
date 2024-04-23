package com.ariamath.shopsmart.controller;
import com.ariamath.shopsmart.entity.User;
import com.ariamath.shopsmart.service.StartupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/user") //localhost::api/user
@RequiredArgsConstructor
public class UserController {
    private final StartupService startupService;

    @GetMapping("{userID}")
    public User getUserByID(@PathVariable Long userID) {
        return startupService.getUser(userID);
    }

    @GetMapping("username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return startupService.getUser(username);
    }
}
