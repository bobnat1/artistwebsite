package com.project.capstone.controller;

import com.project.capstone.model.Streams;
import com.project.capstone.repository.StreamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StreamsController {


    private StreamsRepository streamsRepository;

    @Autowired
    public StreamsController(StreamsRepository streamsRepository) {
        this.streamsRepository = streamsRepository;
    }

    @RequestMapping("/dj-mix")
    public String djMixPage(Model model) {
        Iterable<Streams> streams = streamsRepository.findAll();
        model.addAttribute("streams", streams);
        return "HTML/offthetop";
    }

}
