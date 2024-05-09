package com.ariamath.shopsmart.service;

import com.ariamath.shopsmart.entity.User;
import com.ariamath.shopsmart.repository.UserRepository;
import com.ariamath.shopsmart.security.JwtUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String user_name) throws UsernameNotFoundException {
        log.info("username {}",user_name);
        User user = userRepository.findByUserName(user_name);
        log.info(user.toString());
        return JwtUserDetails.create(user);
    }

    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).get();
        return JwtUserDetails.create(user);
    }

}