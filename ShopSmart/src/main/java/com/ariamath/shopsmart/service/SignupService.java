package com.ariamath.shopsmart.service;
import com.ariamath.shopsmart.entity.PendingUser;
import com.ariamath.shopsmart.entity.Profile;
import com.ariamath.shopsmart.entity.Role;
import com.ariamath.shopsmart.entity.User;
import com.ariamath.shopsmart.exception.PermissionDenied;
import com.ariamath.shopsmart.exception.UsernameAlreadyExists;
import com.ariamath.shopsmart.repository.PendingUserRepository;
import com.ariamath.shopsmart.repository.ProfileRepository;
import com.ariamath.shopsmart.repository.RoleRepository;
import com.ariamath.shopsmart.repository.UserRepository;
import com.ariamath.shopsmart.response.AuthResponse;
import com.ariamath.shopsmart.response.PendingUserResponse;
import com.ariamath.shopsmart.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SignupService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RefreshTokenService refreshTokenService;
    private final PendingUserRepository pendingUserRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProfileRepository profileRepository;

    public PendingUser saveUserAsPending(PendingUser pendingUser) {
        log.info("Pending user {}", pendingUser);
        List<String> usernames = userRepository.getAllUsernames();
        List<String> pendingUsernames = pendingUserRepository.getAllUsernames();
        List<String> toCheck = Stream.concat(usernames.stream(), pendingUsernames.stream()).toList();
        log.info("Usernames {}", toCheck);

        if (toCheck.contains(pendingUser.getUser_name()))
            throw new UsernameAlreadyExists(pendingUser.getUser_name());

        return pendingUserRepository.save(pendingUser);
    }

    public ResponseEntity<AuthResponse> approveUser(String user_name) {

        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("Admin"))){
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
        PendingUser pendingUser = pendingUserRepository.findPendingUserByUsername(user_name).orElseThrow();
        User User = new User(
                pendingUser.getFirstName(),
                pendingUser.getLastName(),
                pendingUser.getEmail(),
                pendingUser.getUser_name(),
                passwordEncoder.encode(pendingUser.getPassword()),
                pendingUser.getRole_name()
        );
        userRepository.save(User);
        User newuser = userRepository.findByUserName(pendingUser.getUser_name());
        Profile profile = new Profile(newuser);
        profileRepository.save(profile);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(pendingUser.getUser_name(), pendingUser.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setMessage("User successfully registered.");
        authResponse.setAccessToken(jwtToken);
        authResponse.setRefreshToken(refreshTokenService.createRefreshToken(User));
        authResponse.setUserId(User.getId());
        authResponse.setUsername(User.getUser_name());
        log.info("authresponse {}",authResponse);

        pendingUserRepository.delete(pendingUser);

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    public ResponseEntity<HttpStatus> denyUser(String user_name) {
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("Admin"))){
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }else{
            PendingUser pendingUser = pendingUserRepository.findPendingUserByUsername(user_name).orElseThrow();
            pendingUserRepository.delete(pendingUser);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

<<<<<<< HEAD
        pendingUser.setRole_name(role.getName());

    }
*/
    public List<PendingUser> getAllPendingUsers() {
        return pendingUserRepository.findAll();
=======
    public List<PendingUserResponse> getAllPendingUsers() {
        List<PendingUser> pu =  pendingUserRepository.findAll();
        List<PendingUserResponse> puResponse = new ArrayList<>();
        for(PendingUser i : pu){
            puResponse.add(new PendingUserResponse(i));
        }
        return puResponse;
>>>>>>> 6a5ea8500265e8e4dc09338fdf3629629fb894ce
    }
}
