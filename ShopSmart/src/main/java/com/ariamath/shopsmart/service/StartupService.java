package com.ariamath.shopsmart.service;
import com.ariamath.shopsmart.entity.Profile;
import com.ariamath.shopsmart.entity.User;
import com.ariamath.shopsmart.exception.UsernameAlreadyExists;
import com.ariamath.shopsmart.repository.UserRepository;
import com.ariamath.shopsmart.repository.RoleRepository;
import com.ariamath.shopsmart.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j // Logging
public class StartupService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository ;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User getUserByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
    /*
    public User saveUser(User user) {
        // Here we need to check that the username is actually unique and does not already exist
        String temp_username = user.getUser_name();
        Optional<Object> x = userRepository.findUserByUsername(temp_username);
        if(x.isEmpty()){
            Profile profile = new Profile();
            profile.setUser(user);
            profileRepository.save(profile);

            user.setPassword(encoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        else{
            System.out.println(new UsernameAlreadyExists(temp_username));
            return null;
        }
    }
    */
/*
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public void assignRoleToUser(String username, String roleName) {
        User User = userRepository.findByUserName(username);
        Role role = roleRepository.findByName(roleName).orElseThrow();


        User.getRole().add(role);
    }
*/
}
