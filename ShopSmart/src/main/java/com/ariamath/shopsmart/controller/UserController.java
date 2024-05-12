package com.ariamath.shopsmart.controller;
import com.ariamath.shopsmart.entity.User;
import com.ariamath.shopsmart.request.UserUpdateRequest;
import com.ariamath.shopsmart.service.StartupService;
import com.ariamath.shopsmart.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user") //localhost::api/user
@RequiredArgsConstructor
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private StartupService startupService;

    @GetMapping("{userID}")
    public ResponseEntity<User> getUserByID(@PathVariable Long userID) {
        return new ResponseEntity<>(startupService.getUser(userID), HttpStatus.OK);
    }

    @GetMapping("username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(startupService.getUser(username), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserByUserId(@PathVariable Long userId){
        userService.deleteUser(userId);
    }

    @PutMapping("/{userId}")
    public void updateUserByUserId(@PathVariable Long userId, @RequestBody UserUpdateRequest userUpdateRequest){
        userService.updateUserByUserId(userId, userUpdateRequest);
    }
}
