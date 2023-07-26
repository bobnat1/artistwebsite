package com.project.capstone.repository;

import com.project.capstone.model.Streams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StreamsRepository extends JpaRepository<Streams, Integer> {

    public Streams findStreamsByName(String name);

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE posts MODIFY COLUMN body VARCHAR(500)", nativeQuery = true)
    public int addMoreCharsTable();
}
