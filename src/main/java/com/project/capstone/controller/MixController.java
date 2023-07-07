package com.project.capstone.controller;
import com.project.capstone.model.Mix;
import com.project.capstone.repository.MixRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
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

    @GetMapping("/download-mix/{mixId}")
    public ResponseEntity<Resource> downloadMix(@PathVariable int mixId) throws IOException {
        // Retrieve the Mix object from the database
        Mix mix = mixRepository.findById(mixId).orElseThrow(() -> new FileNotFoundException("Mix not found"));

        // Check if the user is authorized to download the mix
        // Implement your authorization logic here

        // Retrieve the file path from the Mix object
        String filePath = mix.getPath();

        // Create a Resource object from the file path
        Resource fileResource = new FileSystemResource(filePath);

        // Check if the file exists
        if (!fileResource.exists()) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        // Set the appropriate content type
        String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        try {
            contentType = Files.probeContentType(fileResource.getFile().toPath());
        } catch (IOException e) {
            // Handle the exception or use a default content type
        }

        // Return the file as a ResponseEntity
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResource.getFilename() + "\"")
                .body(fileResource);
    }

    @RequestMapping("/main-account")
    public String userMainAccountPage(Model model) {
        Iterable<Mix> mixes = mixRepository.findAll();
        model.addAttribute("mixes", mixes);
        return "HTML-JS-SBA/account-main";
    }
}