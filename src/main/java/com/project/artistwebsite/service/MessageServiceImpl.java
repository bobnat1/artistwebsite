package com.project.artistwebsite.service;

import com.project.artistwebsite.model.Message;
import com.project.artistwebsite.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    // saves message into database, currently not in use
    @Override
    public void saveMessage(Message message) {

    }

    // deletes all messages in database
    public void deleteAllMessages() {

        messageRepository.deleteAll();

    }

    // deletes message by id in database
    public void deleteMessage(int messageId) {

        messageRepository.deleteById(messageId);
    }
}
