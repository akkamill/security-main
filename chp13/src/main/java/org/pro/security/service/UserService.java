package org.pro.security.service;

import org.pro.security.DTO.UserDTO;
import org.pro.security.DTO.UserSRoleDTO;
import org.pro.security.entity.UserEntity;
import org.pro.security.entity.UserRoleEntity;
import org.pro.security.repository.UserRepository;
import org.pro.security.repository.UserRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final PasswordEncoder encoder;

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;


    public UserService(PasswordEncoder encoder, UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public UserDTO createUser(UserDTO userDTO) {
        Optional<UserEntity> userExist = userRepository.findByUsername(userDTO.getUsername());
        if (userExist.isPresent()) {
            System.out.println("exist");
        } else {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(userDTO.getUsername());
            userEntity.setPassword(encoder.encode(userDTO.getPassword()));
            userRepository.save(userEntity);
        }
        return userDTO;
    }

    public List<UserDTO> getAll() {
        List<UserEntity> entities = userRepository.findAll();
        List<UserDTO> dtoList = new ArrayList<>();
        for (UserEntity ue : entities) {
            UserDTO dto = new UserDTO();
            dto.setUsername(ue.getUsername());
            dto.setPassword(ue.getPassword());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public void updateUser(Integer id, UserDTO userDTO) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        if (userEntity != null) {
            userEntity.setUsername(userDTO.getUsername());
            userEntity.setPassword(encoder.encode(userDTO.getPassword()));

            userRepository.save(userEntity);
        }
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public void userStatus(Integer id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            UserEntity userEntity = user.get();
            boolean status = userEntity.isActive();
            userEntity.setActive(!status);
            userRepository.save(userEntity);
        }
    }

    // --------------------------------------------------------------------------------------------//

    public UserSRoleDTO matchUser(UserSRoleDTO userSRoleDTO) {
        UserEntity userEntity = userRepository.findById(userSRoleDTO.getUserId()).get();
        List<UserRoleEntity> urList = new ArrayList<>();
        userSRoleDTO.getRoleId().forEach(id -> {
            UserRoleEntity ur = new UserRoleEntity();
            ur.setId(id);
            urList.add(ur);
        });

        userEntity.setRoles(urList);
        userRepository.save(userEntity);

        return userSRoleDTO;

        //        UserSRoleDTO userSRoleDTO = new UserSRoleDTO();
//        userSRoleDTO.setUserId(userEntity.getId());
//        userSRoleDTO.setRoleId(Collections.singletonList(userRoleEntity.getId()));
    }

    public List<UserEntity> getAllUsersWithRoles() {
        return userRepository.findAllUserRolesWithoutPrivileges();
    }


      public UserSRoleDTO updateMatchUser(UserSRoleDTO userSRoleDTO) {

        UserEntity userEntity = userRepository.findById(userSRoleDTO.getUserId()).get();

        List<UserRoleEntity> newRoles = new ArrayList<>();

        userSRoleDTO.getRoleId().forEach(roleId -> {
            UserRoleEntity role = userRoleRepository.findById(roleId).get();
            newRoles.add(role);
        });

        userEntity.setRoles(newRoles);
        userRepository.save(userEntity);

        return userSRoleDTO;
    }


//    public void partialUpdateMatchUser(Integer userId, Integer roleId) {
//        Optional<UserEntity> user = userRepository.findById(userId);
//        if (user.isPresent()) {
//            UserEntity userFound = user.get();
//
//            Optional<UserRoleEntity> userRole = userRoleRepository.findById(roleId);
//            if (userRole.isPresent()) {
//                UserRoleEntity roleFound = userRole.get();

//                List<UserRoleEntity> newRoles = new ArrayList<>();

//                userSRoleDTO.getRoleId().forEach(id -> {
//                    UserRoleEntity ur = new UserRoleEntity();
//                    ur.setId(id);
//                    newRoles.add(ur);
//
//                });
//
//            }
//        }
//    }

//                List<UserRoleEntity> newRoles = new ArrayList<>();
//                newRoles.add(roleFound);
//                userFound.setRoles(newRoles);
//
//                userRepository.save(userFound);


//    public void partialUpdateMatchUser(Integer userId, Integer roleId) {
//        Optional<UserEntity> user = userRepository.findById(userId);
//        if (user.isPresent()) {
//            UserEntity userFound = user.get();
//
//            List<UserRoleEntity> roles = userFound.getRoles();
//            for (UserRoleEntity ur : roles) {
//                if (ur.getId().equals(roleId)) {
//                    ur.setId(roleId);
//                    break;
//                }
//            }
//
//            userRepository.save(userFound);
//        }
//    }

    public void deleteMatchUser(Integer userId, Integer roleId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isPresent()) {
            UserEntity userFound = user.get();

            Optional<UserRoleEntity> userRole = userRoleRepository.findById(roleId);
            if (userRole.isPresent()) {
                UserRoleEntity roleFound = userRole.get();

                userFound.getRoles().remove(roleFound);
                userRepository.save(userFound);

            } else {
                throw new IllegalArgumentException("Role not found");
            }
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
}
