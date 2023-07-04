package com.project.capstone.service;

import com.project.capstone.dto.UserDTO;
import com.project.capstone.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    public User findUserByEmail(String email);

    public void saveUser(UserDTO user);

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    public Iterable<User> getAllUsers();

    public void changeRoles(int userId, int newRoleId);
}
