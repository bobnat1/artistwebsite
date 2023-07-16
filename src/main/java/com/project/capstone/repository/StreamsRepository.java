package com.project.capstone.repository;

import com.project.capstone.model.Streams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamsRepository extends JpaRepository<Streams, Integer> {

    public Streams findStreamsByName(String name);
}
