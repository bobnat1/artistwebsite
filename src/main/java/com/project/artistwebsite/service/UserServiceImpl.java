package com.project.artistwebsite.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.project.artistwebsite.dto.UserDTO;
import com.project.artistwebsite.model.User;
import com.project.artistwebsite.model.UserRole;
import com.project.artistwebsite.repository.UserRepository;
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

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder;

    private RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, @Lazy BCryptPasswordEncoder encoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleService = roleService;
    }

    // finds user by email in database
    @Override
    public User findUserByEmail(String email) {

        return userRepository.findUserByEmail(email);
    }

    // saves new user into database
    @Override
    public void saveUser(UserDTO userDTO) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setUserRoles(Arrays.asList(roleService.findRoleByName("ROLE_USER")));

        userRepository.save(user);
        logger.info("User with email " + user.getEmail() + " has signed up");
    }

    public void changeUserPassword(String password, String newPassword, String email) {
        User user = userRepository.findUserByEmail(email);
        if (encoder.matches(password, user.getPassword())) {
            user.setPassword(encoder.encode(newPassword));
            userRepository.save(user);
        }
        else
            throw new IllegalArgumentException("Incorrect Password");
    }

    // finds user email in database for authentication
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = findUserByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getUserRoles()));

    }

    // gets all current users in database
    @Override
    public Iterable<User> getAllUsers() {
        Iterable<User> userList = userRepository.findAll();
        return userList;
    }

    // changes user's role
    @Override
    public void changeRoles(int userId, int newRoleId) {
        userRepository.changeUsersRole(userId, newRoleId);
    }

    // deletes user from database
    @Override
    public void deleteUser(int userId) {
        userRepository.deleteUserFromUserRole(userId);
        userRepository.removeUser(userId);
    }

    // maps user roles for authentication purposes
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<UserRole> userRoles) {

        Collection<? extends GrantedAuthority> mapRoles = userRoles.stream().map(
                role -> new SimpleGrantedAuthority(role.getUserRole()))
                .collect(Collectors.toList());
        return mapRoles;
    }
}
