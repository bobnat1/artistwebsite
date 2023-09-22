package com.project.artistwebsite.service;

import com.project.artistwebsite.model.Posts;
import com.project.artistwebsite.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostsServiceImpl implements PostsService {

    @Autowired
    private PostsRepository postsRepository;

    @Override
    public void savePosts(Posts posts) {
        posts.setDateTime(LocalDateTime.now());
        postsRepository.save(posts);

    }
    // Deletes posts
    @Override
    public void deletePosts(Integer postId) {
        postsRepository.deleteById(postId);
    }

    @Override
    public void editPosts(Posts posts) {

    }

    // Shows all posts in the repo
    @Override
    public Iterable<Posts> allPosts() {
        return postsRepository.findAll();
    }
}
