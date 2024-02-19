package org.pro.security.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Otp {
    @Id
    private String username;
    private String code;
    // Omitted getters and setters
}