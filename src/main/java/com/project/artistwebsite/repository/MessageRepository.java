package com.project.artistwebsite.repository;

import com.project.artistwebsite.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Integer> {

    // finds message by user's email in database
    List<Message> findMessageByEmail(String email);
}
