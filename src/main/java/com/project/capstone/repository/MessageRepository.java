package com.project.capstone.repository;

import com.project.capstone.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Integer> {
    List<Message> findMessageByRecipient(String recipient);
}
