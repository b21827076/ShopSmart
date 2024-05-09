package com.ariamath.shopsmart.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity to model a user that is not yet approved.
 * New sign-ups will create this model and store it. Admins will be able to fetch this and
 * approve will move this to User, disapprove will delete it.
 */
@Entity
@Table(name = "pending_user",schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PendingUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String user_name;
    private String password;
    private String role_name;


    public PendingUser(Long id, String firstName, String lastName, String email, String username, String password, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.user_name = username;
        this.password = password;
        this.role_name = role.getName();
    }
}
