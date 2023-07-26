package com.project.artistwebsite.service;

import com.project.artistwebsite.model.Message;

public interface MessageService {

    public void saveMessage(Message message);

    public void deleteAllMessages();

    public void deleteMessage(int messageId);

    }
