package com.project.artistwebsite.service;

import com.project.artistwebsite.model.Posts;


public interface PostsService{

    public void savePosts(Posts posts);

    public void deletePosts(Integer postId);

    public void editPosts(Posts posts);

    public Iterable<Posts> allPosts();
}
