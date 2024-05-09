package com.ariamath.shopsmart.repository;
import com.ariamath.shopsmart.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query("select p from Profile p where p.User.user_name=:username")
    Optional<Profile> findProfileByUsername(String username);

    @Query(value = "SELECT c FROM Profile c WHERE c.User.id = :userId")
    Profile getByUserId(@Param("userId") Long user_id);
}
