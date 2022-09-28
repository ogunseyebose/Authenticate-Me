package com.authenticate.AuthenticateMe.model;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userGenerator")
    @SequenceGenerator(name = "userGenerator", sequenceName = "userIdSequence", allocationSize = 1)
    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;

}
