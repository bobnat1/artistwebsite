package com.project.capstone;

import com.project.capstone.repository.StreamsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StreamsRepositoryTest {

    @Autowired
    StreamsRepository streamsRepository;

    @Test
    public void updateCharTest() {
        streamsRepository.addMoreCharsTable();
    }
}
