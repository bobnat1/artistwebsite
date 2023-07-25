package com.project.capstone.repository;

import com.project.capstone.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Integer> {
    public Posts findPostsById(Integer id);
}
