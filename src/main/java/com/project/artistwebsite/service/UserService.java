package com.project.artistwebsite.service;

import com.project.artistwebsite.dto.UserDTO;
import com.project.artistwebsite.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    public User findUserByEmail(String email);

    public void saveUser(UserDTO user);

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    public Iterable<User> getAllUsers();

    public void changeRoles(int userId, int newRoleId);

    public void deleteUser(int userId);

    public void changeUserPassword(String password, String newPassword, String email);

    public void updateUserPrefEmail(boolean getEmails, String email);
}
