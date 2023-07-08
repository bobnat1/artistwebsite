package com.project.capstone;


import com.project.capstone.repository.RoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoleRepositoryTest {

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void findRoleByUserRoleTest() {
        Assertions.assertDoesNotThrow(() -> roleRepository.findRoleByUserRole("ROLE_ADMIN"));
    }
}
