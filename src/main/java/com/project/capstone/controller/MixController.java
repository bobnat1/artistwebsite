package com.project.capstone.controller;
import com.project.capstone.model.Mix;
import com.project.capstone.repository.MixRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
public class MixController {

    private MixRepository mixRepository;

    private ResourceLoader resourceLoader;

    @Autowired
    public MixController(MixRepository mixRepository, ResourceLoader resourceLoader) {
        this.mixRepository = mixRepository;
        this.resourceLoader = resourceLoader;
    }




    @RequestMapping("/post-mix")
    public String uploadMix(Model model) {
        model.addAttribute("mix", new Mix());
        Iterable<Mix> mixes = mixRepository.findAll();
        model.addAttribute("mixes", mixes);
        return "HTML-JS-SBA/mix-page";
    }
    @PostMapping("/send-mix")
    public String uploadMix(@ModelAttribute (name = "mix") Mix mix, @RequestParam("mp3File")MultipartFile file, @RequestParam("mixName") String mixName) throws IOException {

        String fileName = file.getOriginalFilename();
        String uploadDirectory = System.getProperty("user.home") + "/mixes";

        String filePath = uploadDirectory + "/" + fileName;
        File dest = new File(filePath);

        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        file.transferTo(dest);

        mix.setMixName(mixName);
        mix.setPath(filePath);
        mixRepository.save(mix);
        return "redirect:/post-mix";

    }

    @RequestMapping("/delete-mix")
    public String deleteMix(@RequestParam("mixName") String mixName) {
        mixRepository.delete(mixRepository.findMixByMixName(mixName));
        return "redirect:/post-mix";
    }
}