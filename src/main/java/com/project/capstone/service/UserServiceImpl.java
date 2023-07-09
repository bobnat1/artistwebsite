package com.project.capstone.service;

import com.project.capstone.dto.UserDTO;
import com.project.capstone.model.User;
import com.project.capstone.model.UserRole;
import com.project.capstone.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder;

    private RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, @Lazy BCryptPasswordEncoder encoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleService = roleService;
    }

    @Override
    public User findUserByEmail(String email) {

        return userRepository.findUserByEmail(email);
    }

    @Override
    public void saveUser(UserDTO userDTO) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setUserRoles(Arrays.asList(roleService.findRoleByName("ROLE_USER")));

        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = findUserByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getUserRoles()));

    }

    @Override
    public Iterable<User> getAllUsers() {
        Iterable<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public void changeRoles(int userId, int newRoleId) {
        userRepository.changeUsersRole(userId, newRoleId);
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteUserFromUserRole(userId);
        userRepository.removeUser(userId);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<UserRole> userRoles) {

        Collection<? extends GrantedAuthority> mapRoles = userRoles.stream().map(
                role -> new SimpleGrantedAuthority(role.getUserRole()))
                .collect(Collectors.toList());
        return mapRoles;
    }
}
