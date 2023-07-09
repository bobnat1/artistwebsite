package com.project.capstone;

import com.project.capstone.model.User;
import com.project.capstone.model.UserRole;
import com.project.capstone.service.MessageService;
import com.project.capstone.service.RoleService;
import com.project.capstone.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    MessageService messageService;

    @Test
    public void getAllUsersTest() {
        Iterable<User> users = Assertions.assertDoesNotThrow(() -> userService.getAllUsers());
        for (User user: users) {
            System.out.println(user.getEmail());
        }
    }

    @Test
    public void findRoleByNameTest() {
        UserRole userRole = Assertions.assertDoesNotThrow(() -> roleService.findRoleByName("ROLE_USER"));
        System.out.println(userRole.getUserRole());
    }

    @Test
    public void deleteAllMessagesTest() {
        Assertions.assertDoesNotThrow(() -> messageService.deleteAllMessages());
    }
}
