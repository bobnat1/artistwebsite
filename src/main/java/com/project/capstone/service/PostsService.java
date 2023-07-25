package com.project.capstone.service;

import com.project.capstone.model.Posts;
import org.springframework.stereotype.Service;


public interface PostsService{

    public void savePosts(Posts posts);

    public void deletePosts(Posts posts);

    public void editPosts(Posts posts);
}
