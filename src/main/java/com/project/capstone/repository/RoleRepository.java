package com.project.capstone.repository;

import com.project.capstone.model.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<UserRole, Integer> {

    public UserRole findRoleByUserRole(String role);


}
