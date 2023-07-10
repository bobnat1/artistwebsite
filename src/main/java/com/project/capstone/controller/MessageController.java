package com.project.capstone.controller;

import com.project.capstone.model.Message;
import com.project.capstone.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MessageController {

    private MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // Brings Authenticated user to message page, loads message object to be saved in later request
    @RequestMapping("/message-dj")
    public String messageScreen(Model model) {
        model.addAttribute("message", new Message());
        return "HTML-JS-SBA/message";
    }

    /*
    When requested, this method takes the Authenticated user's email and saves it to the message object along with the
    message body and finally saves the message to the database
     */
    @PostMapping("/messages")
    public String sendMessage(@ModelAttribute (name = "message") Message message, Authentication authentication) {
        String email = authentication.getName();
        System.out.println(email);
        message.setTime(LocalDateTime.now());
        message.setEmail(email);

        messageRepository.save(message);
        return "redirect:/confirm-message";

    }

    // Brings User role Admin to message management page, loads messages in database for later requests
    @RequestMapping("/message-edit")
    public String checkMessages(Model model){
        Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        return "HTML-JS-SBA/edit-messages";
    }

    // When requested, the message object is deleted from the database
    @RequestMapping("/message-delete")
    public String processChange(@RequestParam("messageId") Integer messageId) {
        messageRepository.deleteById(messageId);
        return "redirect:/message-edit";
    }

}
