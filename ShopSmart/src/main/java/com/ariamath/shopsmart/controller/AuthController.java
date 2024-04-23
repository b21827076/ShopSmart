package com.ariamath.shopsmart.controller;

import com.ariamath.shopsmart.entity.PendingUser;
import com.ariamath.shopsmart.entity.RefreshToken;
import com.ariamath.shopsmart.entity.User;
import com.ariamath.shopsmart.repository.UserRepository;
import com.ariamath.shopsmart.request.ApproveUserRequest;
import com.ariamath.shopsmart.request.LoginRequest;
import com.ariamath.shopsmart.request.PendingUserRequest;
import com.ariamath.shopsmart.request.RefreshRequest;
import com.ariamath.shopsmart.response.AuthResponse;
import com.ariamath.shopsmart.response.PendingUserResponse;
import com.ariamath.shopsmart.security.JwtTokenProvider;
import com.ariamath.shopsmart.security.JwtUserDetails;
import com.ariamath.shopsmart.service.RefreshTokenService;
import com.ariamath.shopsmart.service.SignupService;
import com.ariamath.shopsmart.service.UserDetailsServiceImpl;
import com.ariamath.shopsmart.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
    private UserRepository userRepository;
    private UserDetailsServiceImpl userDetailsService;
    private SignupService signupService;
    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    private RefreshTokenService refreshTokenService;

    public AuthController(AuthenticationManager authenticationManager, SignupService signupService,UserRepository userRepository,UserDetailsServiceImpl userDetailsService,
                          UserService userService, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.refreshTokenService = refreshTokenService;
        this.signupService = signupService;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
        JwtUserDetails x = (JwtUserDetails) userDetailsService.loadUserByUsername(loginRequest.getUser_name());
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUser_name(), loginRequest.getPassword(),x.getAuthorities());
        log.info("authoken {}",authToken);
        Authentication auth = authenticationManager.authenticate(authToken);
        log.info("\nINFO3\n");
        SecurityContextHolder.getContext().setAuthentication(auth);
        log.info("\nINFO4\n");
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        log.info("\nINFO5\n");
        User user = userService.getOneUserByUserName(loginRequest.getUser_name());
        log.info("user {}", user);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken("Bearer " + jwtToken);
        authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
        authResponse.setUserId(user.getId());
        return authResponse;
    }
/*
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody LoginRequest registerRequest) {
        AuthResponse authResponse = new AuthResponse();
        if(userService.getOneUserByUserName(registerRequest.getUsername()) != null) {
            authResponse.setMessage("Username already in use.");
            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUser_name(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userService.saveOneUser(user);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(registerRequest.getUsername(), registerRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);

        authResponse.setMessage("User successfully registered.");
        authResponse.setAccessToken("Bearer " + jwtToken);
        authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
        authResponse.setUserId(user.getId());
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }
 */
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> savePendingUser(@RequestBody PendingUserRequest pendingUserRequest) {
        AuthResponse authResponse = new AuthResponse();
        if(userService.getOneUserByUserName(pendingUserRequest.getUser_name()) != null) {
            authResponse.setMessage("Username already in use.");
            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
        }else{
            PendingUser user = new PendingUser(
                    null,
                    pendingUserRequest.getFirst_name(),
                    pendingUserRequest.getLast_name(),
                    pendingUserRequest.getEmail(),
                    pendingUserRequest.getUser_name(),
                    pendingUserRequest.getPassword(),
                    pendingUserRequest.getRole_name()
            );
            signupService.saveUserAsPending(user);
            authResponse.setMessage("User successfully added to pending users.");
            authResponse.setUserId(user.getId());
            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        }
        //signupService.assignRoleToPendingUser(user.getUser_name(), pendingUserRequest.getRole_name());
    }

    @PostMapping("/approve") //api/signup/approve
    public void approveUser(@RequestBody ApproveUserRequest approveUserRequest) {
        signupService.approveUser(approveUserRequest.getUser_name());
    }

    @PostMapping("/deny") //api/signup/deny
    public void denyUser(@RequestBody ApproveUserRequest approveUserRequest) {
        signupService.denyUser(approveUserRequest.getUser_name());
    }

    @GetMapping("")
    public List<PendingUserResponse> getPendingUsers() {
        return signupService.getAllPendingUsers();
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest refreshRequest) {
        AuthResponse response = new AuthResponse();
        RefreshToken token = refreshTokenService.getByUser(refreshRequest.getUserId());
        if(token.getToken().equals(refreshRequest.getRefreshToken()) &&
                !refreshTokenService.isRefreshExpired(token)) {

            User user = token.getUser();
            String jwtToken = jwtTokenProvider.generateJwtTokenByUserId(user.getId());
            response.setMessage("token successfully refreshed.");
            response.setAccessToken("Bearer " + jwtToken);
            response.setUserId(user.getId());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("refresh token is not valid.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

    }
}