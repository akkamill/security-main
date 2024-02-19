package org.pro.security.controllers;

import org.pro.security.DTO.PrivilegeDTO;
import org.pro.security.service.PrivilegeService;
import org.pro.security.service.UserRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PrivilegeController {

    private final PrivilegeService privilegeService;


    public PrivilegeController(PrivilegeService privilegeService, UserRoleService userRoleService) {
        this.privilegeService = privilegeService;
    }

    @PostMapping("/createPrivilege")
    ResponseEntity<PrivilegeDTO> create(@RequestBody PrivilegeDTO privilegeDTO) {
        System.out.println(privilegeDTO);
        return ResponseEntity.ok(privilegeService.createPrivilege(privilegeDTO));
    }

    @GetMapping("/getPrivileges")
    ResponseEntity<List<PrivilegeDTO>> getAll() {
        List<PrivilegeDTO> privilegeList = privilegeService.getAll();
        return ResponseEntity.ok(privilegeList);
    }

    @PutMapping("/updatePrivileges/{id}")
    ResponseEntity<?> updatePrivileges(@PathVariable Integer id, @RequestBody PrivilegeDTO privilegeDTO) {
        privilegeService.updatePrivilege(id, privilegeDTO);
        return ResponseEntity.ok("Updated");
    }


    @DeleteMapping("/deletePrivilege")
    public ResponseEntity<?> deletePrivilege(@RequestParam Integer id) {
        privilegeService.deletePrivilege(id);
        return ResponseEntity.ok("Deleted");
    }


}
