package com.project.artistwebsite.controller;

import com.project.artistwebsite.model.Streams;
import com.project.artistwebsite.repository.StreamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "HTML/offthetop2";
    }

    @RequestMapping("/streams-edit")
    public String adminStreamPage(Model model) {
        Iterable<Streams> streams = streamsRepository.findAll();
        model.addAttribute("streams", new Streams());
        model.addAttribute("stream", streams);
        return "HTML/edit-streams";
    }

    @PostMapping("/streams-send")
    public String addStreams(@ModelAttribute(name = "streams") Streams streams) {
        streamsRepository.save(streams);
        return "redirect:/streams-edit";
    }

    @RequestMapping("/streams-delete")
    public String processChange(@RequestParam("streamId") Integer streamId) {
        streamsRepository.deleteById(streamId);
        return "redirect:/streams-edit";
    }

}
