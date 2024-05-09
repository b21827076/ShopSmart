package com.ariamath.shopsmart.service;

import java.util.List;
import java.util.Optional;

import com.ariamath.shopsmart.entity.User;
import com.ariamath.shopsmart.repository.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
@Service
public class UserService {
	
	private UserRepository userRepository;
	
	public UserService(UserRepository UserRepository) {
		this.userRepository = UserRepository;
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User saveOneUser(User newUser) {
		return userRepository.save(newUser);
	}
	
	public User getOneUserByUserName(String user_name) {
		return userRepository.findByUserName(user_name);
	}
	
	public User getOneUserById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

}