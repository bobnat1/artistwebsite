package com.project.capstone;

import com.project.capstone.model.User;
import com.project.capstone.repository.MixRepository;
import com.project.capstone.repository.UserRepository;
import com.project.capstone.service.MessageServiceImpl;
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

//	@Test
	public void listAllUsers(){
		Iterable<User> users = userRepository.findAll();
		for (User user: users) {
			System.out.println(user.getEmail());
		}
	}

//	@Test
	public void updateRoleTest(){
		userRepository.changeUsersRole(52, 2);
//		Assertions.assertEquals(2, userRepository.findUserByEmail("test@test.com").getUserRoles());
	}

	public void deleteUserTest(){
		userRepository.removeUser(102);
		Assertions.assertNull(userRepository.findUserByEmail("delete@delete.com"));
	}


	public void deleteUserTestTwo(){
		userRepository.delete(userRepository.findUserByEmail("delete@delete.com"));
		Assertions.assertNull(userRepository.findUserByEmail("delete@delete.com"));
	}

//	@Test
	public void deleteMixTest(){
		mixRepository.delete(mixRepository.findMixByMixName("testmix"));
		Assertions.assertNull(mixRepository.findMixByMixName("testmix"));
	}

}
