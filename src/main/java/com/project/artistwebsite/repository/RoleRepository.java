package com.project.artistwebsite.repository;

import com.project.artistwebsite.model.UserRole;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RoleRepository extends CrudRepository<UserRole, Integer> {
    // finds role in database by role name
    public UserRole findRoleByUserRole(String role);

    // adds ROLE_USER into the database
    @Modifying
    @Transactional
    @Query(value = "insert into user_role (id, user_role) values (1, 'ROLE_USER')", nativeQuery = true)
    public int addUserRoleToTable();

    // adds ROLE_ADMIN into the database
    @Modifying
    @Transactional
    @Query(value = "insert into user_role (id, user_role) values (2, 'ROLE_ADMIN')", nativeQuery = true)
    public int addAdminRoleToTable();
}
