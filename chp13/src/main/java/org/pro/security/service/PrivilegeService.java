package org.pro.security.service;

import org.pro.security.DTO.PrivilegeDTO;
import org.pro.security.entity.Privilege;
import org.pro.security.repository.PrivilegeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PrivilegeService {

    private final PrivilegeRepository privilegeRepository;

    public PrivilegeService(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    public PrivilegeDTO createPrivilege(PrivilegeDTO privilegeDTO) {

        Optional<Privilege> privilegeExist = privilegeRepository.findByName(privilegeDTO.getName());
        if (privilegeExist.isPresent()) {
            System.out.println("exist");
        } else {
            Privilege privilege = new Privilege();
            privilege.setName(privilegeDTO.getName());
            privilegeRepository.save(privilege);
        }
        return privilegeDTO;
    }

    public List<PrivilegeDTO> getAll() {
        List<Privilege> entities = privilegeRepository.findAll();
        List<PrivilegeDTO> dtoList = new ArrayList<>();
        for (Privilege p : entities) {
            PrivilegeDTO dto = new PrivilegeDTO();
            dto.setName(p.getName());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public void updatePrivilege(Integer id, PrivilegeDTO privilegeDTO) {
        Privilege privilege = privilegeRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        privilege.setName(privilegeDTO.getName());

        privilegeRepository.save(privilege);
    }

    public void deletePrivilege(Integer id) {
        privilegeRepository.deleteById(id);
    }

}
