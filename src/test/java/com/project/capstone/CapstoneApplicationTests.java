package com.project.capstone;

import com.project.capstone.model.User;
import com.project.capstone.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CapstoneApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void listAllUsers(){
		Iterable<User> users = userRepository.findAll();
		for (User user: users) {
			System.out.println(user.getEmail());
		}
	}

}
