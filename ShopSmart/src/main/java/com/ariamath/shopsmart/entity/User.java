package com.ariamath.shopsmart.entity;

import javax.persistence.*;

import com.ariamath.shopsmart.repository.RoleRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Table(name = "user", schema = "public")
@Entity
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String user_name;
    @JsonIgnore
    private String password;

    @JoinColumn(name = "role_name",referencedColumnName = "name")
    private String role_name;

    public User(String firstName, String lastName, String email, String username, String password,String role_name) {
        this.first_name = firstName;
        this.last_name = lastName;
        this.email = email;
        this.user_name = username;
        this.password = password;
        this.role_name = role_name;
    }

    public User(Long id, String first_name, String last_name, String email, String user_name, String password, String role) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.user_name = user_name;
        this.password = password;
        this.role_name = role;
        }

}
