package com.project.capstone.controller;

import com.project.capstone.model.Message;
import com.project.capstone.model.Posts;
import com.project.capstone.repository.PostsRepository;
import com.project.capstone.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class PostsController {

    @Autowired
    PostsService postsService;

    @RequestMapping("/posts-edit")
    public String postsPage(Model model) {
        Iterable<Posts> post = postsService.allPosts();
        model.addAttribute("posts", new Posts());
        model.addAttribute("post", post);
        return "HTML/edit-posts";
    }
    @PostMapping("/send-posts")
    public String sendPosts(@ModelAttribute(name = "posts")Posts posts) {
        postsService.savePosts(posts);

        return "redirect:/posts-edit";
    }

    @RequestMapping("/posts-delete")
    public String processChange(@RequestParam("postId") Integer postId) {
        postsService.deletePosts(postId);
        return "redirect:/posts-edit";
    }
}
