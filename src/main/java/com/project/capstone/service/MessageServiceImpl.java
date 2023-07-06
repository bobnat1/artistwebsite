package com.project.capstone.service;

import com.project.capstone.model.Message;
import com.project.capstone.repository.MessageRepository;
import com.project.capstone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService{

    private MessageRepository messageRepository;

    private RoleService roleService;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, RoleService roleService) {
        this.messageRepository = messageRepository;
        this.roleService = roleService;
    }

    @Override
    public void saveMessage(Message message) {

    }

    public void deleteAllMessages() {

        messageRepository.deleteAll();

    }
}
