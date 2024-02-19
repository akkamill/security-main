package org.pro.security.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "users_security")
@Data
public class User {

    @Id
    private String username;
    private String password;
    private String code;
}