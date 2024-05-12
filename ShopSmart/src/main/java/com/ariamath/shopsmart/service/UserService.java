package com.ariamath.shopsmart.service;

import java.util.List;
import java.util.Optional;

import com.ariamath.shopsmart.entity.Profile;
import com.ariamath.shopsmart.entity.User;
import com.ariamath.shopsmart.repository.ProfileRepository;
import com.ariamath.shopsmart.repository.UserRepository;
import com.ariamath.shopsmart.request.UserUpdateRequest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
@Service
public class UserService {

	private ProfileService profileService;
	private UserRepository userRepository;
	
	public UserService(UserRepository UserRepository,ProfileService profileService) {
		this.userRepository = UserRepository;
		this.profileService = profileService;
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
	public void deleteUser(Long userId){
		userRepository.deleteById(userId);
		profileService.deleteProfile(userId);
	}
	public void updateUserByUserId(Long userId, UserUpdateRequest userUpdateRequest) {
		User user = userRepository.getById(userId);
		user.setUser_name(userUpdateRequest.getUser_name());
		user.setEmail(userUpdateRequest.getEmail());
		user.setFirst_name(userUpdateRequest.getFirst_name());
		user.setLast_name(userUpdateRequest.getLast_name());
		userRepository.save(user);
	}
}