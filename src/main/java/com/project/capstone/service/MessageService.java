package com.project.capstone.service;

import com.project.capstone.model.Message;

public interface MessageService {

    public void saveMessage(Message message);

    public void deleteAllMessages();

    public void deleteMessage(int messageId);

    }
