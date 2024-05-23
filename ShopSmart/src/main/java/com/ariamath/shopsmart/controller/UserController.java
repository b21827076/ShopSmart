package com.ariamath.shopsmart.controller;
import com.ariamath.shopsmart.entity.PendingUser;
import com.ariamath.shopsmart.entity.User;
import com.ariamath.shopsmart.request.ChangePasswordRequest;
import com.ariamath.shopsmart.request.ForgottenPasswordRequest;
import com.ariamath.shopsmart.request.UserUpdateRequest;
import com.ariamath.shopsmart.service.StartupService;
import com.ariamath.shopsmart.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /*@DeleteMapping("/{userId}")
    public void deleteUserByUserId(@PathVariable Long userId){
        userService.deleteUser(userId);
    }
*/
    @DeleteMapping("/{username}")
    public void deleteUserByUserName(@PathVariable String username){
        User user = userService.getOneUserByUserName(username);
        userService.deleteUser(user.getId());
    }


    @PutMapping("/{userId}")
    public void updateUserByUserId(@PathVariable Long userId, @RequestBody UserUpdateRequest userUpdateRequest){
        userService.updateUserByUserId(userId, userUpdateRequest);
    }

    @PutMapping("/changepassword")
    public ResponseEntity changePassword(@RequestBody ChangePasswordRequest changePasswordRequest){
        userService.changePassword(changePasswordRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/forgotpassword")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgottenPasswordRequest forgottenPasswordRequest){
        String password = userService.forgotPassword(forgottenPasswordRequest);
        return new ResponseEntity<>(password,HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

  /*   @GetMapping("/pending-users") pending userlar icin get request
    public ResponseEntity<List<PendingUser>> getPendingUsers() {
        List<PendingUser> pendingUsers = pendingUserService.findAll();
        return new ResponseEntity<>(pendingUsers, HttpStatus.OK);
    } */
}
