package com.project.capstone;

import com.project.capstone.model.User;
import com.project.capstone.repository.MixRepository;
import com.project.capstone.repository.UserRepository;
import com.project.capstone.service.MessageServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CapstoneApplicationTests {

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
