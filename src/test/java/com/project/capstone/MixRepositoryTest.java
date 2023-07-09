package com.project.capstone;


import com.project.capstone.model.Mix;
import com.project.capstone.repository.MixRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MixRepositoryTest {

    @Autowired
    MixRepository mixRepository;

    //test finds mix object by mixName
    @Test
    public void findMixByMixNameTest() {
        Mix mix = new Mix();
        mix.setMixName("test");
        mixRepository.save(mix);
        Assertions.assertDoesNotThrow(() -> mixRepository.findMixByMixName("test"));
        mixRepository.delete(mix);
    }
}
