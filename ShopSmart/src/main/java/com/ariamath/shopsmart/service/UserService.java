package com.ariamath.shopsmart.service;
import java.util.List;
import java.util.Objects;
import com.ariamath.shopsmart.entity.Purchase;
import com.ariamath.shopsmart.entity.ShoppingCart;
import com.ariamath.shopsmart.entity.User;
import com.ariamath.shopsmart.repository.PurchaseRepository;
import com.ariamath.shopsmart.repository.UserRepository;
import com.ariamath.shopsmart.request.ChangePasswordRequest;
import com.ariamath.shopsmart.request.ForgottenPasswordRequest;
import com.ariamath.shopsmart.request.UserUpdateRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class UserService {

	private PasswordEncoder passwordEncoder;
	private ProfileService profileService;
	private UserRepository userRepository;
	private PurchaseRepository purchaseRepository;
	
	public UserService(UserRepository UserRepository,ProfileService profileService, PasswordEncoder passwordEncoder, PurchaseRepository purchaseRepository) {
		this.userRepository = UserRepository;
		this.profileService = profileService;
		this.passwordEncoder = passwordEncoder;
		this.purchaseRepository = purchaseRepository;
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

	public void changePassword(ChangePasswordRequest changePasswordRequest) {
		User user = userRepository.getById(changePasswordRequest.getUserId());
		user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
		userRepository.save(user);
	}

	public String forgotPassword(ForgottenPasswordRequest forgottenPasswordRequest) {
		User user = userRepository.findByUserName(forgottenPasswordRequest.getUserName());
		if(	Objects.equals(user.getEmail(), forgottenPasswordRequest.getEmail()) &&
				Objects.equals(user.getLast_name(), forgottenPasswordRequest.getLastName()) &&
				Objects.equals(user.getFirst_name(), forgottenPasswordRequest.getFirstName())){
			user.setPassword(passwordEncoder.encode("yenisifre123"));
		}
		return "yenisifre123";
	}
<<<<<<< HEAD
	
=======

	public void addPurchase(ShoppingCart shoppingCart){
		Purchase purchase = new Purchase(shoppingCart);
		purchaseRepository.save(purchase);
	}

	public List<Purchase> getAllPurchasesById(Long userId) {
		return purchaseRepository.getPurchasesById(userId);
	}
>>>>>>> 6a5ea8500265e8e4dc09338fdf3629629fb894ce
}