package com.project.capstone.service;

import com.project.capstone.model.Message;
import com.project.capstone.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MessageServiceImpl implements MessageService{

    private UserRepository userRepository;

    private RoleService roleService;


    @Override
    public void saveMessage(Message message) {

    }
}
