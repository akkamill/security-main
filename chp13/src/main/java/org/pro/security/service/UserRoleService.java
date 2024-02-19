package org.pro.security.service;

import org.pro.security.DTO.RolePrivilegeDTO;
import org.pro.security.DTO.UserRoleDTO;
import org.pro.security.entity.Privilege;
import org.pro.security.entity.UserRoleEntity;
import org.pro.security.repository.PrivilegeRepository;
import org.pro.security.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserRoleService {

    private final UserRoleRepository repository;

    private final PrivilegeRepository privilegeRepository;


    public UserRoleService(UserRoleRepository repository, PrivilegeRepository privilegeRepository) {
        this.repository = repository;
        this.privilegeRepository = privilegeRepository;
    }

    public UserRoleDTO createRole(UserRoleDTO userRoleDTO) {
        Optional<UserRoleEntity> roleExist = repository.findByName(userRoleDTO.getName());
        if (roleExist.isPresent()) {
            System.out.println("exist");
        } else {
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setName(userRoleDTO.getName());
            repository.save(userRoleEntity);
        }
        return userRoleDTO;
    }

    public List<UserRoleDTO> getAll() {
        List<UserRoleEntity> entities = repository.findAll();
        List<UserRoleDTO> dtoList = new ArrayList<>();
        for (UserRoleEntity ure : entities) {
            UserRoleDTO dto = new UserRoleDTO();
//            dto.setId(ure.getId());
            dto.setName(ure.getName());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public void updateRole(Integer id, UserRoleDTO userRoleDTO) {
        UserRoleEntity userRoleEntity = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        if (userRoleDTO != null) {
            userRoleEntity.setName(userRoleDTO.getName());

            repository.save(userRoleEntity);
        }
    }

    public void deleteRole(Integer id) {
        repository.deleteById(id);
    }

    // --------------------------------------------------------------------------------------------//

    public RolePrivilegeDTO matchPrivilege(RolePrivilegeDTO rolePrivilegeDTO) {
        UserRoleEntity userRoleEntity = repository.findById(rolePrivilegeDTO.getRoleId()).get();
        List<Privilege> plList = new ArrayList<>();
        rolePrivilegeDTO.getPrivilegeId().forEach(id -> {
            Privilege ur = new Privilege();
            ur.setId(id);
            plList.add(ur);
        });
        userRoleEntity.setPrivileges(plList);
        repository.save(userRoleEntity);
        return rolePrivilegeDTO;
    }

    public RolePrivilegeDTO updateMatchPrivilege(RolePrivilegeDTO rolePrivilegeDTO) {

        UserRoleEntity userRoleEntity = repository.findById(rolePrivilegeDTO.getRoleId()).get();

        List<Privilege> newPrivilege = new ArrayList<>();

        rolePrivilegeDTO.getPrivilegeId().forEach(privilegeId -> {
            Privilege privilege = privilegeRepository.findById(privilegeId).get();
            newPrivilege.add(privilege);
        });

        userRoleEntity.setPrivileges(newPrivilege);
        repository.save(userRoleEntity);

        return rolePrivilegeDTO;
    }

    public void deleteMatchPrivilege(Integer roleId, Integer privilegeId) {
        Optional<UserRoleEntity> role = repository.findById(roleId);
        if (role.isPresent()) {
            UserRoleEntity roleFound = role.get();

            Optional<Privilege> privilege = privilegeRepository.findById(privilegeId);
            if (privilege.isPresent()) {
                Privilege privilegeFound = privilege.get();

                roleFound.getPrivileges().remove(privilegeFound);
                repository.save(roleFound);
            }
        }
    }

}
