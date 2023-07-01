package com.project.capstone.service;

import com.project.capstone.model.UserRole;

public interface RoleService {

    public UserRole findRoleByName(String roleName);
}
