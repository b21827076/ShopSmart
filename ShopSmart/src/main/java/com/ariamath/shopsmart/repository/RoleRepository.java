package com.ariamath.shopsmart.repository;

import com.ariamath.shopsmart.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String roleName);
    Role findRoleById(Long rolde_id);
}
