package com.ariamath.shopsmart.repository;
import com.ariamath.shopsmart.entity.PendingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PendingUserRepository extends JpaRepository<PendingUser, Long> {

    @Query(value = "SELECT c FROM PendingUser c WHERE c.user_name = :username")
    Optional<PendingUser> findPendingUserByUsername(@Param("username") String user_name);

    @Query("select user_name from PendingUser ")
    List<String> getAllUsernames();

}
