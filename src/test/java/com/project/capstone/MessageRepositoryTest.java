package com.project.capstone;

import com.project.capstone.model.Message;
import com.project.capstone.repository.MessageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MessageRepositoryTest {

    @Autowired
    MessageRepository messageRepository;

    @Test
    public void findMessageByEmailTest() {
        Message message = new Message();
        message.setEmail("junit@junit.com");
        messageRepository.save(message);
        Assertions.assertDoesNotThrow(() -> messageRepository.findMessageByEmail("junit@junit.com"));
        messageRepository.delete(message);

    }
}
