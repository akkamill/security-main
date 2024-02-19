package org.pro.security.controllers;

import org.pro.security.DTO.UserDTO;
import org.pro.security.DTO.UserSRoleDTO;
import org.pro.security.entity.UserEntity;
import org.pro.security.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRegister {

    private final UserService userService;

    public UserRegister(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> userList = userService.getAll();
        return ResponseEntity.ok(userList);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        userService.updateUser(id, userDTO);
        return ResponseEntity.ok("Updated");

    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestParam Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("/status/{id}")
    public ResponseEntity<?> userStatus(@PathVariable Integer id) {
        userService.userStatus(id);
        return ResponseEntity.ok("Status changed");
    }

    //----------------------------------------------------------------------------------------------------------------//

    @PostMapping("/match")
    public ResponseEntity<UserSRoleDTO> matchUser(@RequestBody UserSRoleDTO userSRoleDTO) {
        return ResponseEntity.ok(userService.matchUser(userSRoleDTO));
    }

    @GetMapping("/get")
    public ResponseEntity<List<UserEntity>> getAllUsersWithRoles() {
        return ResponseEntity.ok(userService.getAllUsersWithRoles());
    }

    @PutMapping("/updateUserSRole")
    public ResponseEntity<?> updateMatchUser(@RequestBody UserSRoleDTO userSRoleDTO) {
        return ResponseEntity.ok(userService.updateMatchUser(userSRoleDTO));
    }

    @DeleteMapping("/deleteUserSRole")
    public ResponseEntity<?> deleteMatchUser(@RequestParam Integer userId, @RequestParam Integer roleId) {
        userService.deleteMatchUser(userId, roleId);
        return ResponseEntity.ok("Deleted");
    }

//    @PutMapping("/updateUserSRole/{userId}/roles/{roleId}")
//    public ResponseEntity<?> partialUpdateMatchUser(@PathVariable Integer userId, @PathVariable Integer roleId) {
//        userService.partialUpdateMatchUser(userId, roleId);
//        return ResponseEntity.ok("Updated");
//    }

}
