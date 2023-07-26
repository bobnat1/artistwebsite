package com.project.artistwebsite;


import com.project.artistwebsite.repository.UserRepository;
import com.project.artistwebsite.service.RoleService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;

    //test changes role of 1st user in the database
    @ParameterizedTest
    @ValueSource(ints = {2, 1, 2})
    @Order(4)
    public void changeUserRoleTest(int roleId) {
        Assertions.assertDoesNotThrow(() -> userRepository.changeUsersRole(1,roleId));

    }

    //test finds user by email, please enter a valid email previously registered in database
    @Test
    @Order(1)
    public void findUserByEmailTest() {

        Assertions.assertDoesNotThrow(() -> userRepository.findUserByEmail("junit@junit.net"));

    }

    //test deletes user by id in user_roles table, please enter a valid id previously registered in database
    @Test
    @Order(2)
    public void deleteUserFromUserRoleTest(){
        Assertions.assertDoesNotThrow(() -> userRepository.deleteUserFromUserRole(402));
    }

    //test deletes user by id in user table, please enter a valid id previously registered in database and make sure the previous method is run 1st
    @Test
    @Order(3)
    public void removeUserTest() {
        Assertions.assertDoesNotThrow(() -> userRepository.removeUser(402));
    }
}
