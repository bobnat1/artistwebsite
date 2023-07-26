package com.project.artistwebsite;

import com.project.artistwebsite.repository.StreamsRepository;
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
