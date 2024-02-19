package org.pro.security.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UserSRoleDTO {

    private Integer userId;

    private List<Integer> roleId;

}