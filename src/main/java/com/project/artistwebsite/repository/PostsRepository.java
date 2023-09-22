package com.project.artistwebsite.repository;

import com.project.artistwebsite.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Integer> {

    // Find posts by ID
    public Posts findPostsById(Integer id);
}
