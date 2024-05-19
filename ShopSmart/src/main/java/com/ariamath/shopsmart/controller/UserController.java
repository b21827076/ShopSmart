package com.ariamath.shopsmart.controller;
import com.ariamath.shopsmart.entity.Purchase;
import com.ariamath.shopsmart.entity.User;
import com.ariamath.shopsmart.request.ChangePasswordRequest;
import com.ariamath.shopsmart.request.ForgottenPasswordRequest;
import com.ariamath.shopsmart.request.UserUpdateRequest;
import com.ariamath.shopsmart.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user") //localhost::api/user

public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userID}")
    public ResponseEntity<User> getUserByID(@PathVariable Long userID) {
        return new ResponseEntity<>(userService.getOneUserById(userID), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userService.getOneUserByUserName(username), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserByUserId(@PathVariable Long userId){
        userService.deleteUser(userId);
    }

    @PutMapping("/{userId}")
    public void updateUserByUserId(@PathVariable Long userId, @RequestBody UserUpdateRequest userUpdateRequest){
        userService.updateUserByUserId(userId, userUpdateRequest);
    }

    @PutMapping("/changepassword")
    public ResponseEntity<HttpStatus> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest){
        userService.changePassword(changePasswordRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/forgotpassword")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgottenPasswordRequest forgottenPasswordRequest){
        String password = userService.forgotPassword(forgottenPasswordRequest);
        return new ResponseEntity<>(password,HttpStatus.OK);
    }
    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/purchaseHistory/{userId}")
    public ResponseEntity<List<Purchase>> getAllPurchaseHistory(@PathVariable Long userId ){
        return new ResponseEntity<>(userService.getAllPurchasesById(userId),HttpStatus.OK);
    }

}

