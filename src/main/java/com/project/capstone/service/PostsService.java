package com.project.capstone.service;

import com.project.capstone.model.Posts;
import org.springframework.stereotype.Service;


public interface PostsService{

    public void savePosts(Posts posts);

    public void deletePosts(Integer postId);

    public void editPosts(Posts posts);

    public Iterable<Posts> allPosts();
}
