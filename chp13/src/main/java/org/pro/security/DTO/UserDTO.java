package org.pro.security.DTO;

import lombok.Data;

@Data
public class UserDTO {

    private String username;

    private String password;

    private boolean active;

}
