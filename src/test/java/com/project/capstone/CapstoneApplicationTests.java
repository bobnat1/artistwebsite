package com.project.capstone;

import com.project.capstone.model.User;
import com.project.capstone.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.transaction.annotation.Transactional;

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

	@Test
	public void updateRoleTest(){
		userRepository.changeUsersRole(52, 2);
//		Assertions.assertEquals(2, userRepository.findUserByEmail("test@test.com").getUserRoles());
	}


}
