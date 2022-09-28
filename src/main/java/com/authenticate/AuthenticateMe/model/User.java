package com.authenticate.AuthenticateMe.model;

import javax.persistence.*;

//@Data
@Entity(name="User")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"email" })})
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
