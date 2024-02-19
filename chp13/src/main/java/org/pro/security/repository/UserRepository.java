package org.pro.security.repository;

import org.pro.security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsername(String username);

    @Query("SELECT u FROM users u JOIN FETCH u.roles")
    List<UserEntity> findAllUserRolesWithoutPrivileges();

}

