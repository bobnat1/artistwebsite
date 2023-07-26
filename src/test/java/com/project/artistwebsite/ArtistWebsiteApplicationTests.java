package com.project.artistwebsite;

import com.project.artistwebsite.model.User;
import com.project.artistwebsite.repository.MixRepository;
import com.project.artistwebsite.repository.UserRepository;
import com.project.artistwebsite.service.MessageServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ArtistWebsiteApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	MixRepository mixRepository;

	@Autowired
	MessageServiceImpl messageService;

	@Test
	void contextLoads() {
	}

	@Test
	void deleteAllMessagesTest(){
		messageService.deleteAllMessages();
	}

	@ParameterizedTest
	@ValueSource()
	public void listAllUsers(Object object){
		Iterable<User> users = userRepository.findAll();
		for (User user: users) {
			System.out.println(user.getEmail());
		}
	}
}
