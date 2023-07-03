package com.project.capstone.controller;
import com.project.capstone.model.Mix;
import com.project.capstone.repository.MixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/send-mix")
public class MixController {

    private MixRepository mixRepository;

    @Autowired
    public MixController(MixRepository mixRepository) {
        this.mixRepository = mixRepository;
    }

    @PostMapping
    public String uploadMix(@ModelAttribute (name = "mix") Mix mix) {

        mixRepository.save(mix);
        return "redirect:/main-account";

    }

//    @GetMapping("/{recipient}")
//    public List<Message> getMessagesByRecipient(@PathVariable String recipient) {
//        return messageRepository.findMessageByRecipient(recipient);
//    }
}