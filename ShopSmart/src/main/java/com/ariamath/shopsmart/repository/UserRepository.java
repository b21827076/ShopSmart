package com.ariamath.shopsmart.repository;

import java.util.List;
import java.util.Optional;

import com.ariamath.shopsmart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT c FROM User c WHERE c.user_name = :username")
	User findByUserName(@Param("username") String user_name);
	
	@Query(value = "SELECT c FROM User c WHERE c.id = :userId", nativeQuery = true)
	Optional<User> findById(@Param("userId") Optional<Long> user_id);

	@Query(value = "SELECT c FROM public.User c WHERE c.user_name = :tempUsername", nativeQuery = true)
	Optional<Object> findUserByUsername(@Param("tempUsername")String tempUsername);

	@Query("SELECT u.user_name FROM User u")
	List<String> getAllUsernames();
}