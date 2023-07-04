package com.project.capstone.controller;

import com.project.capstone.model.Message;
import com.project.capstone.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/messages")
public class MessageController {

    private MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @PostMapping
    public String sendMessage(@ModelAttribute (name = "message") Message message) {

        messageRepository.save(message);
        return "redirect:/confirm-message";

    }

    @GetMapping("/{recipient}")
    public List<Message> getMessagesByRecipient(@PathVariable String recipient) {
        return messageRepository.findMessageByRecipient(recipient);
    }
}
