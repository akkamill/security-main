package com.spring.security.chp2.entity;

import com.spring.security.chp2.EncryptionAlgorithm;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name="app_user")
@Data
public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private EncryptionAlgorithm algorithm;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Authority> authorities;

    // Omitted getters and setters
}