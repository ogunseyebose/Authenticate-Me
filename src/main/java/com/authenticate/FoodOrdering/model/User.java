package com.authenticate.FoodOrdering.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String email;
    private String password;
    private String accountStatus;
    private LocalDateTime dtCreated;
    private LocalDateTime dtModified;


    public User() {
    }

    public User(Long userId, String email, String password, String accountStatus, LocalDateTime dtCreated, LocalDateTime dtModified) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.accountStatus = accountStatus;
        this.dtCreated = dtCreated;
        this.dtModified = dtModified;
    }
}
