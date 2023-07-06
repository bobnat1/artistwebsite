package com.project.capstone.controller;
import com.project.capstone.model.Mix;
import com.project.capstone.repository.MixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MixController {

    private MixRepository mixRepository;

    @Autowired
    public MixController(MixRepository mixRepository) {
        this.mixRepository = mixRepository;
    }

    @RequestMapping("/post-mix")
    public String uploadMix(Model model) {
        model.addAttribute("mix", new Mix());
        Iterable<Mix> mixes = mixRepository.findAll();
        model.addAttribute("mixes", mixes);
        return "HTML-JS-SBA/mix-page";
    }
    @PostMapping("/send-mix")
    public String uploadMix(@ModelAttribute (name = "mix") Mix mix) {

        mixRepository.save(mix);
        return "redirect:/post-mix";

    }

    @RequestMapping("/delete-mix")
    public String deleteMix(@RequestParam("mixName") String mixName) {
        mixRepository.delete(mixRepository.findMixByMixName(mixName));
        return "redirect:/post-mix";
    }
}