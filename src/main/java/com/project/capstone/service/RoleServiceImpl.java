package com.project.capstone.service;

import com.project.capstone.model.UserRole;
import com.project.capstone.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public UserRole findRoleByName(String roleName) {
        return roleRepository.findRoleByUserRole(roleName);
    }
}
