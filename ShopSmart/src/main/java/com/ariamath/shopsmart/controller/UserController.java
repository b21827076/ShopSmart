package com.ariamath.shopsmart.controller;
import com.ariamath.shopsmart.entity.User;
import com.ariamath.shopsmart.service.StartupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<User> getUserByID(@PathVariable Long userID) {
        return new ResponseEntity<>(startupService.getUser(userID), HttpStatus.OK);
    }

    @GetMapping("username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(startupService.getUser(username), HttpStatus.OK);
    }
}
