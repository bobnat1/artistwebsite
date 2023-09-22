package com.project.artistwebsite.controller;

import com.project.artistwebsite.model.Posts;
import com.project.artistwebsite.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostsController {

    @Autowired
    PostsService postsService;

    // Allows admin to edit posts
    @RequestMapping("/posts-edit")
    public String postsPage(Model model) {
        Iterable<Posts> post = postsService.allPosts();
        model.addAttribute("posts", new Posts());
        model.addAttribute("post", post);
        return "HTML/edit-posts";
    }

    // Saves posts to repo
    @PostMapping("/send-posts")
    public String sendPosts(@ModelAttribute(name = "posts")Posts posts) {
        postsService.savePosts(posts);

        return "redirect:/posts-edit";
    }

    // Deletes posts from repo
    @RequestMapping("/posts-delete")
    public String processChange(@RequestParam("postId") Integer postId) {
        postsService.deletePosts(postId);
        return "redirect:/posts-edit";
    }
}
