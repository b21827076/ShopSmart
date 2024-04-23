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

	public User getOneUserById(Optional<Long> user_id) {
		return userRepository.findById(user_id).orElse(null);
	}
	
	public void deleteById(Long userId) {
		try {
		userRepository.deleteById(userId);
		}catch(EmptyResultDataAccessException e) { //user zaten yok, db'den empty result gelmiş
			System.out.println("User "+userId+" doesn't exist"); //istersek loglayabiliriz
		}
	}
	
	public User updateOneUser(Long userId, User newUser) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			User foundUser = user.get();
			foundUser.setUser_name(newUser.getUser_name());
			foundUser.setPassword(newUser.getPassword());
			userRepository.save(foundUser);
			return foundUser;
		}else
			return null;
	}
	
}