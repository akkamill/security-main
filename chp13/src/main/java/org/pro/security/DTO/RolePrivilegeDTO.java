package org.pro.security.DTO;

import lombok.Data;

import java.util.List;

@Data
public class RolePrivilegeDTO {

    private Integer roleId;

    private List<Integer> privilegeId;
}