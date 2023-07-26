package com.project.artistwebsite.service;

import com.project.artistwebsite.model.UserRole;

public interface RoleService {

    public UserRole findRoleByName(String roleName);
}
