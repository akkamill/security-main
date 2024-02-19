package org.pro.security.controllers;

import org.pro.security.DTO.RolePrivilegeDTO;
import org.pro.security.DTO.UserRoleDTO;
import org.pro.security.service.UserRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRoleController {

    private final UserRoleService userRoleService;


    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }


    @PostMapping("/createRole")
    public ResponseEntity<?> createRole(@RequestBody UserRoleDTO userRoleDTO) {
        return ResponseEntity.ok(userRoleService.createRole(userRoleDTO));
    }

    @GetMapping("/getRoles")
    public ResponseEntity<List<UserRoleDTO>> getAll() {
        List<UserRoleDTO> roleList = userRoleService.getAll();
        return ResponseEntity.ok(roleList);
    }

    @PutMapping("/updateRoles/{id}")
    public ResponseEntity<?> updateRoles(@PathVariable Integer id, @RequestBody UserRoleDTO userRoleDTO) {
        userRoleService.updateRole(id, userRoleDTO);
        return ResponseEntity.ok("Updated");
    }

    @DeleteMapping("/deleteRole")
    public ResponseEntity<?> deleteRole(@RequestParam Integer id) {
        userRoleService.deleteRole(id);
        return ResponseEntity.ok("Deleted");
    }

//-------------------------------------------------------------------------------------------------------------------//

    @PostMapping("/matchPrivilege")
    public ResponseEntity<RolePrivilegeDTO> matchPrivilege(@RequestBody RolePrivilegeDTO rolePrivilegeDTO) {
        return ResponseEntity.ok(userRoleService.matchPrivilege(rolePrivilegeDTO));
    }

    @PutMapping("/updateRolePrivilege")
    public ResponseEntity<RolePrivilegeDTO> updateMatchUser(@RequestBody RolePrivilegeDTO rolePrivilegeDTO) {
        return ResponseEntity.ok(userRoleService.updateMatchPrivilege(rolePrivilegeDTO));
    }

    @DeleteMapping("/deleteRolePrivilege")
    public ResponseEntity<?> deleteMatchPrivilege(Integer roleId, Integer privilegeId) {
        userRoleService.deleteMatchPrivilege(roleId, privilegeId);
        return ResponseEntity.ok("Deleted");
    }


}
