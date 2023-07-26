package com.project.artistwebsite.service;

import com.project.artistwebsite.model.UserRole;
import com.project.artistwebsite.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // finds role by role name in database
    @Override
    public UserRole findRoleByName(String roleName) {
        return roleRepository.findRoleByUserRole(roleName);
    }
}
